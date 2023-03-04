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
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.CategoryDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController implements BaseController {
    private final CategoryDTOMapper mapper;
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories"),
    })
    @GetMapping
    public ResponseEntity<CustomResponse<List<CategoryDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        var categoriesPageDO = categoryService.list(page, size);
        var categoriesPageDTO = mapper.toPageDTO(categoriesPageDO);

        var customResponse = CustomResponse.list(ListResponse.build(categoriesPageDTO));
        return ResponseEntity.ok(customResponse);
    }

    @Operation(summary = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<CategoryDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var categoryDO = categoryService.get(id);
        var categoryDTO = mapper.toCategoryDTO(categoryDO);
        var customResponse = CustomResponse.single(categoryDTO);
        return ResponseEntity.ok(customResponse);
    }

}
