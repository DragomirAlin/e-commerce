package ro.dragomiralin.ecommerce.domain.category.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.Set;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDO {
    private Long id;
    private String name;
    private Set<ProductDO> products;
}
