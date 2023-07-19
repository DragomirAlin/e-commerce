package ro.dragomiralin.ecommerce.domain.payment.port;

import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;

public interface StripeClient {

    PaymentResponseDO createPayment(PaymentDO paymentDO);

    PaymentResponseDO completePayment(String paymentId);

    PaymentResponseDO refundPayment(String paymentId);

}
