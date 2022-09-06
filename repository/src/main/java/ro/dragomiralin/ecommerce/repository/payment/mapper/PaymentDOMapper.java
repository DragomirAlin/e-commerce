package ro.dragomiralin.ecommerce.repository.payment.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.repository.payment.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentDOMapper {

    PaymentDO toPaymentDO(Payment payment);

    Payment toPayment(PaymentDO paymentDO);

    PageDO<PaymentDO> toPageDO(Page<Payment> page);
}
