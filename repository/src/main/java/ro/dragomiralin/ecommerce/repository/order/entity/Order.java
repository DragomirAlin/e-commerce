package ro.dragomiralin.ecommerce.repository.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ro.dragomiralin.ecommerce.repository.payment.entity.Payment;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author Dragomir Alin
 * @since 1.0
 */

@Getter
@Setter
@Builder
@Table(name = "orders")
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Status of the order
     */
    @Column(name = "status")
    private OrderStatus status;

    /**
     * Date when the order was created
     */
    @Column(name = "ordered_date")
    private Date orderedDate;

    /**
     * Some additional information about the order
     */
    @Column(name = "customer_comments")
    private String customerComments;

    /**
     * Items of the order
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private List<OrderItem> items;

    /**
     * Payments of the order
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Payment> payments;

    /**
     * User who created the order
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
