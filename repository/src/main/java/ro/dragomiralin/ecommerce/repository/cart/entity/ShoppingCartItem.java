package ro.dragomiralin.ecommerce.repository.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

/**
 * @author Dragomir Alin
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "shopping_cart")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The product id.
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * The quantity of the product in the cart
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * The user that owns the cart
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
