package ro.dragomiralin.ecommerce.infra.persistence.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity(name = "products_categories")
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "product_id", nullable = false)
    private long productId;
    @Column(name = "category_id", nullable = false)
    private long categoryId;

}
