package ro.dragomiralin.ecommerce.domain.payment;

import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.CreatedPaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;


public interface PaymentService {

    CreatedPaymentDO createPayment(UserDO userDO, OrderDO orderDO);

    PaymentDO processPayment(PaymentDO paymentDO);

    PaymentDO getById(long id);

}
