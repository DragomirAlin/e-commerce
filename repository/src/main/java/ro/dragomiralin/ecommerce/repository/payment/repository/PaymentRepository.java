package ro.dragomiralin.ecommerce.repository.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
