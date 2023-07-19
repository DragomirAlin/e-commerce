package ro.dragomiralin.ecommerce.repository.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * @author Dragomir Alin
 * @since 1.0
 */
@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    /**
     * The quantity of the product in the order
     */
    @NotNull
    @Column(name = "quantity")
    private int quantity;

    /**
     * The order to which the product belongs
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    /**
     * The product in the order
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

