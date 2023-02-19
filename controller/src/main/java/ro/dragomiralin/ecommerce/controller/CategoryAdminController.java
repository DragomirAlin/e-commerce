package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.CategoryDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.CategoryDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CategoryCreateReq;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryAdminController implements BaseController {
    private final CategoryDTOMapper mapper;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CustomResponse<CategoryDTO>> add(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CategoryCreateReq categoryCreateReq) {
        var category = mapper.toCategory(categoryCreateReq);
        var createdCategory = categoryService.add(category);
        return ResponseEntity.ok(CustomResponse.single(mapper.toCategoryDTO(createdCategory)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
