package ro.dragomiralin.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
public record CategoryDTO(String id,
                          String name,
                          @JsonIgnore List<ProductDTO> products) {
}

