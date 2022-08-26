package ro.dragomiralin.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.domain.category.CategoryDO;

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
