package ro.dragomiralin.ecommerce.repository.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private BigDecimal amount;

    private PaymentCurrency currency;

    @JoinColumn(name = "py_order")
    @OneToOne(cascade = CascadeType.MERGE)
    private Order order;



}
