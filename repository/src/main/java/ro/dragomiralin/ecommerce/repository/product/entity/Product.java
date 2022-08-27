package ro.dragomiralin.ecommerce.repository.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "products")
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
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "products_categories", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> categories = new HashSet<>();
}