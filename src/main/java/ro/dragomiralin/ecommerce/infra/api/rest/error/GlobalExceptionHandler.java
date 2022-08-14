package ro.dragomiralin.ecommerce.infra.api.rest.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomResponse> handle(ConstraintViolationException e) {
        ErrorResponse errors = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            ErrorItem error = new ErrorItem();
            error.setCode(400);
            error.setMessage(violation.getMessage());
            errors.addError(error);
        }

        return new ResponseEntity<>(CustomResponse.error(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class, BindException.class, ValidationException.class})
    public ResponseEntity<CustomResponse> handleAssertValidationException(Exception e) {
        log.info("Validation failed for request.", e);
        ErrorItem customError = ErrorItem.builder()
                .code(400)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(CustomResponse.error(customError), HttpStatus.BAD_REQUEST);
    }


    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse> handle(ResourceNotFoundException e) {
        ErrorItem error = ErrorItem.builder()
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(CustomResponse.error(error), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> genericException(Exception e) {
        log.error("Unhandled exception with message: {}", e.getMessage(), e);
        return new ResponseEntity(CustomResponse
                .error(ErrorItem.builder()
                        .code(500)
                        .message(e.getMessage())
                        .build()
                ), HttpStatus.INTERNAL_SERVER_ERROR);
    }


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
