package ro.dragomiralin.ecommerce.repository.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

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
    @Column(name = "product_id")
    private String productId;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
