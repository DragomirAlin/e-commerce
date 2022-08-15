package ro.dragomiralin.ecommerce.infra.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ListResponse;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;
import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<Category>>> list(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        System.out.println(user);
        var categoriesPage = categoryService.list(PageRequest.of(page, size));
        var customResponse = CustomResponse.list(ListResponse.build(categoriesPage));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Category>> get(@PathVariable long id) {
        var category = categoryService.get(id);
        var customResponse = CustomResponse.single(category);
        return ResponseEntity.ok(customResponse);
    }

}
