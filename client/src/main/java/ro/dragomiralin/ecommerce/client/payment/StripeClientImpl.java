package ro.dragomiralin.ecommerce.client.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.payment.port.StripeClient;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.math.BigDecimal;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class StripeClientImpl implements StripeClient {
    private final StripeClientProperties properties;

    @PostConstruct
    public void setStripeApiKey() {
        Stripe.apiKey = properties.getSecretKey();
    }

    @Override
    public PaymentResponseDO createPayment(PaymentDO paymentDO) {
        try {
            Charge charge = Charge.create(ChargeCreateParams.builder()
                    .setCustomer(getOrCreateStripeCustomer(paymentDO.getOrder().getUserDO()).getId())
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

    private Customer getOrCreateStripeCustomer(UserDO userDO) throws StripeException {
        String query = "metadata[\"userId\"]:\"%s\"";
        CustomerSearchParams searchParams =
                CustomerSearchParams
                        .builder()
                        .setQuery(query.formatted(userDO.getId()))
                        .build();

        CustomerSearchResult result = Customer.search(searchParams);

        Customer customer;
        if (Objects.nonNull(result) && result.getData().size() > 0) {
            customer = result.getData().get(0);
        } else {
            customer = Customer.create(
                    CustomerCreateParams.builder()
                            .setEmail(userDO.getEmail())
                            .setName(userDO.getFullName())
                            .putMetadata(UserDO.Fields.USER_ID, String.valueOf(userDO.getId()))
                            .build()
            );
        }
        return customer;
    }

}
