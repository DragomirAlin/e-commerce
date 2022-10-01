package ro.dragomiralin.ecommerce.domain.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDO {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<CategoryDO> categories;
}
