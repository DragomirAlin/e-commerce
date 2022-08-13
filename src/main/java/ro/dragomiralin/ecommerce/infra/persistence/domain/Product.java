package ro.dragomiralin.ecommerce.infra.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Product name is required.")
    private String name;
    private String description;
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<ProductCategory> categories;
}