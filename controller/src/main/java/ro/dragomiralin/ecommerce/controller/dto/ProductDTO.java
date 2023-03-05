package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDTO(String id,
                         String name,
                         String description,
                         BigDecimal price,
                         List<CategoryDTO> categories) {
}
