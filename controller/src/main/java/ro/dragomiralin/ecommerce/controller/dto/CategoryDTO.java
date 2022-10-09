package ro.dragomiralin.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private List<ProductDTO> products;
}
