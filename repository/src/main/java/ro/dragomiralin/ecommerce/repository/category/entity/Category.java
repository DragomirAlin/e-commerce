package ro.dragomiralin.ecommerce.repository.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    @JsonIgnore
    private List<Product> products;

}