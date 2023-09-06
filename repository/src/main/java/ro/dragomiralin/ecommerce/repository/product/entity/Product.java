package ro.dragomiralin.ecommerce.repository.product.entity;

import lombok.*;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Dragomir Alin
 * @since 1.0
 */
@Data
@Entity
@Table(name = "products")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the product
     */
    @NotNull(message = "Product name is required.")
    @Column(name = "name")
    private String name;

    /**
     * The description of the product
     */
    @NotNull(message = "Product description is required.")
    @Column(name = "description")
    private String description;

    /**
     * The price of the product
     */
    @NotNull(message = "Product price is required.")
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Categories of the product
     */
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
            })
    @JoinTable(name = "products_categories", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "category_id", referencedColumnName = "id")})
    @ToString.Exclude
    private List<Category> categories;
}

