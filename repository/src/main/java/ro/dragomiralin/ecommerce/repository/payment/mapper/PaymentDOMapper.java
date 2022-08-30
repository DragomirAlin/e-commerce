package ro.dragomiralin.ecommerce.repository.payment.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.repository.payment.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentDOMapper {

    PaymentDO toPaymentDO(Payment payment);

    Payment toPayment(PaymentDO paymentDO);
}
