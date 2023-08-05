package ro.dragomiralin.ecommerce.repository.payment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Enumerated(EnumType.STRING)
    private PaymentCurrency currency;

    /**
     * External payment id
     */
    @Column(name = "external_payment_id")
    private String externalPaymentId;

    /**
     * The external invoice id of the payment
     */
    @Column(name = "external_invoice_id")
    private String externalInvoiceId;

    /**
     * The order to which the payment belongs
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
