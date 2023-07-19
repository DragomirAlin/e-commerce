package ro.dragomiralin.ecommerce.client.payment;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.payment.port.StripeClient;

import java.math.BigDecimal;


@Slf4j
@Service
@RequiredArgsConstructor
public class StripeClientImpl implements StripeClient {
    private final StripeClientProperties properties;

    @PostConstruct
    public void setStripeApiKey(){
        Stripe.apiKey = properties.getSecretKey();
    }

    @Override
    public PaymentResponseDO createPayment(PaymentDO paymentDO) {
        try {
            Charge charge = Charge.create(ChargeCreateParams.builder()
                            .setCurrency(paymentDO.getCurrency().name())
                            .setAmount(paymentDO.getAmount().multiply(BigDecimal.valueOf(100)).longValue())
                    .build());

            return PaymentResponseDO.builder()
                    .paymentExternalId(charge.getId())
                    .paymentUri(charge.getReceiptUrl())
                    .build();
        } catch (Exception e) {
            log.error("Error while creating payment {}", paymentDO.getId(), e);
            return PaymentResponseDO.builder()
                    .success(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public PaymentResponseDO completePayment(String paymentId) {
        return null;
    }

    @Override
    public PaymentResponseDO refundPayment(String paymentId) {
        return null;
    }

}
