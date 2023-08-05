package ro.dragomiralin.ecommerce.domain.payment.port;

import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

public interface StripeClient {

    PaymentResponseDO createPayment(UserDO userDO, PaymentDO paymentDO);

    PaymentResponseDO completePayment(String paymentId);

    PaymentResponseDO refundPayment(String paymentId);

}
