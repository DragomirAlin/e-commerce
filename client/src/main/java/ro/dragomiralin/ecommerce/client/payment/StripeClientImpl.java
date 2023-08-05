package ro.dragomiralin.ecommerce.client.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.payment.port.StripeClient;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public PaymentResponseDO createPayment(UserDO userDO, PaymentDO paymentDO) {
        try {
            Session session = Session.create(SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/success")
                    .setCancelUrl("http://localhost:8080/cancel")
                    .setCurrency("eur")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("eur")
                                            .setUnitAmountDecimal(paymentDO.getAmount().setScale(2, RoundingMode.CEILING))
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("T-shirt")
                                                            .build())
                                            .build())
                                    .build())
                    .build());

            return PaymentResponseDO.builder()
                    .paymentExternalId(session.getId())
                    .paymentUri(session.getUrl())
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
