package ro.dragomiralin.ecommerce.domain.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.PaymentService;
import ro.dragomiralin.ecommerce.domain.payment.domain.*;
import ro.dragomiralin.ecommerce.domain.payment.port.PaymentPort;
import ro.dragomiralin.ecommerce.domain.payment.port.StripeClient;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentPort paymentPort;
    private final StripeClient stripeClient;

    @Override
    public CreatedPaymentDO createPayment(OrderDO orderDO) {
        var totalToPay = orderDO.getOrderItems().stream()
                .map(orderItemDO -> BigDecimal.valueOf(orderItemDO.getQuantity()).multiply(orderItemDO.getProductDO().getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var paymentDO = paymentPort.save(PaymentDO.builder()
                .status(PaymentDOStatus.PENDING)
                .amount(totalToPay)
                .currency(PaymentDOCurrency.EUR)
                .order(orderDO)
                .build());

        PaymentResponseDO paymentResponseDO = stripeClient.createPayment(paymentDO);

        if (paymentResponseDO.success()) {
            paymentDO.setExternalPaymentId(paymentResponseDO.paymentExternalId());
            paymentDO.setStatus(PaymentDOStatus.SUCCESS);
        } else {
            paymentDO.setStatus(PaymentDOStatus.FAILED);
        }

        paymentDO = paymentPort.save(paymentDO);

        return CreatedPaymentDO.builder()
                .paymentDO(paymentDO)
                .paymentResponseDO(paymentResponseDO)
                .build();
    }

    @Override
    public PaymentDO processPayment(PaymentDO paymentDO) {
        return null;
    }

    @Override
    public PaymentDO getById(long id) {
        return null;
    }

}
