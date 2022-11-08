package ro.dragomiralin.ecommerce.domain.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDO {
    private long id;
    private String name;
    @JsonIgnore
    private List<ProductDO> products;
}
