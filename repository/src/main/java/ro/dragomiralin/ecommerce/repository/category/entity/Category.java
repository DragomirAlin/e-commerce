package ro.dragomiralin.ecommerce.repository.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author Dragomir Alin
 * @since 1.0
 */
@Data
@Entity
@Table(name = "categories")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the category
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * List of products in this category
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @JsonIgnore
    private List<Product> products;

}