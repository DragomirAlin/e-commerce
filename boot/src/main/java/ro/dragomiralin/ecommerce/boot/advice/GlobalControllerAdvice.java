package ro.dragomiralin.ecommerce.boot.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.category.error.CategoryNotFoundException;
import ro.dragomiralin.ecommerce.domain.common.error.ResourceNotFoundException;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.product.error.ProductNotFoundException;
import ro.dragomiralin.ecommerce.domain.user.error.UserAlreadyExistsException;

import jakarta.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<CustomResponse> handleAccessDeniedException(AccessDeniedException ex) {
        var error = ErrorItem.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(CustomResponse.error(error), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ConstraintViolationException.class, PSQLException.class, DataIntegrityViolationException.class})
    public ResponseEntity<CustomResponse> handle(Exception e) {
        ErrorItem error = ErrorItem.builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(CustomResponse.error(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, BindException.class, ValidationException.class, UserAlreadyExistsException.class})
    public ResponseEntity<CustomResponse> handleAssertValidationException(Exception e) {
        log.info("Validation failed for request.", e);
        ErrorItem customError = ErrorItem.builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(CustomResponse.error(customError), HttpStatus.BAD_REQUEST);
    }


    @SuppressWarnings("rawtypes")
    @ExceptionHandler({ShoppingCartItemException.class, ResourceNotFoundException.class, ProductNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<CustomResponse> handleNotFound(Exception e) {
        ErrorItem error = ErrorItem.builder()
                .code(404)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(CustomResponse.error(error), HttpStatus.NOT_FOUND);
    }

    

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CustomResponse> problem(final Throwable e) {
        log.error("Unhandled exception with message: {}", e.getMessage(), e);
        return new ResponseEntity(CustomResponse
                .error(ErrorItem.builder()
                        .code(500)
                        .message(e.getMessage())
                        .build()
                ), HttpStatus.INTERNAL_SERVER_ERROR);    }


    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class ErrorItem {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private int code;
        private String message;
    }

    public static class ErrorResponse {
        private List<ErrorItem> errors = new ArrayList<>();

        public List<ErrorItem> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorItem> errors) {
            this.errors = errors;
        }

        public void addError(ErrorItem error) {
            this.errors.add(error);
        }

    }
}
