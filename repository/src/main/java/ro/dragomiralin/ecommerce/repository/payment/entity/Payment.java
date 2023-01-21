package ro.dragomiralin.ecommerce.repository.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * @author Dragomir Alin
 * @since 1.0
 */
@Data
@Entity
@Table(name = "payments")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The amount of the payment
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * Currency of the payment
     */
    @Column(name = "currency")
    private PaymentCurrency currency;

    /**
     * The order to which the payment belongs
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order;

}
