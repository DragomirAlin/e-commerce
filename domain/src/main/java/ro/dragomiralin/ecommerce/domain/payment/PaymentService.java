package ro.dragomiralin.ecommerce.domain.payment;

import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;


public interface PaymentService {

    PaymentDO createPayment(OrderDO orderDO);

    PaymentDO processPayment(PaymentDO paymentDO);

    PaymentDO getById(long id);

}
