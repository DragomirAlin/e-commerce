package ro.dragomiralin.ecommerce.infra.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CategoryCreateReq;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ListResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.mapper.CategoryMapper;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CustomResponse<Long>> add(@RequestBody CategoryCreateReq categoryCreateReq) {
        var category = categoryMapper.toCategory(categoryCreateReq);
        return ResponseEntity.ok(CustomResponse.single(categoryService.add(category)));
    }

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<Category>>> list(@RequestParam int page, @RequestParam int size) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
