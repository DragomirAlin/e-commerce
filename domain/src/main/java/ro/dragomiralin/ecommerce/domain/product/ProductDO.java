package ro.dragomiralin.ecommerce.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.category.CategoryDO;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDO {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Set<CategoryDO> categories;
}
