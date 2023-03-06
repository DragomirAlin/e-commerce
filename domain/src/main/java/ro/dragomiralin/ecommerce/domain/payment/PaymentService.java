package ro.dragomiralin.ecommerce.domain.payment;

import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.CreatedPaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;


public interface PaymentService {

    CreatedPaymentDO createPayment(OrderDO orderDO);

    PaymentDO processPayment(PaymentDO paymentDO);

    PaymentDO getById(long id);

}
