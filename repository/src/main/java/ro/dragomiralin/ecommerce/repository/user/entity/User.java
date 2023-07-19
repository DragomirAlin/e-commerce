package ro.dragomiralin.ecommerce.repository.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCartItem;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

import java.util.List;

/**
 * @author Dragomir Alin
 * @since 1.0
 */
@Data
@Entity
@Table(name = "users")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The Identifier for the user from the authentication service
     */
    @Column(unique = true)
    private String sub;

    /**
     * The first name of the user
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the user
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The email of the user
     */
    @Column(unique = true)
    private String email;

    /**
     * The shopping cart items of the user
     */
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<ShoppingCartItem> items;

    /**
     * The orders of the user
     */
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Order> orders;
}
