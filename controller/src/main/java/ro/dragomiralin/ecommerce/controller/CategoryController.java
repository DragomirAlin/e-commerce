package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.CategoryDTO;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.CategoryDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDTOMapper mapper;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<CategoryDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        var categoriesPageDO = categoryService.list(page, size);
        var categoriesPageDTO = mapper.toPageDTO(categoriesPageDO);

        var customResponse = CustomResponse.list(ListResponse.build(categoriesPageDTO));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<CategoryDTO>> get(@PathVariable long id) {
        var categoryDO = categoryService.get(id);
        var categoryDTO = mapper.toCategoryDTO(categoryDO);
        var customResponse = CustomResponse.single(categoryDTO);
        return ResponseEntity.ok(customResponse);
    }

}
