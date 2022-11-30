package ro.dragomiralin.ecommerce.repository.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.repository.payment.entity.Payment;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
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

    private OrderStatus status;

    @Column(name = "ordered_date")
    private Date orderedDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;

    private long userId;

    @Column(name = "customer_comments")
    private String customerComments;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Payment> payments;
}
