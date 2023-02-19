package ro.dragomiralin.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category Admin", description = "Category Admin API")
public class CategoryAdminController implements BaseController {
    private final CategoryDTOMapper mapper;
    private final CategoryService categoryService;

    @Operation(summary = "Create category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the category"),
    })
    @PostMapping
    public ResponseEntity<CustomResponse<CategoryDTO>> add(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CategoryCreateReq categoryCreateReq) {
        var category = mapper.toCategory(categoryCreateReq);
        var createdCategory = categoryService.add(category);
        return ResponseEntity.ok(CustomResponse.single(mapper.toCategoryDTO(createdCategory)));
    }

    @Operation(summary = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the category"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
