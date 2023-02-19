package ro.dragomiralin.ecommerce.domain.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.PaymentService;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDOCurrency;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDOStatus;
import ro.dragomiralin.ecommerce.domain.payment.port.PaymentPort;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentPort paymentPort;

    @Override
    public PaymentDO createPayment(OrderDO orderDO) {
        var totalToPay = orderDO.getOrderItems().stream()
                .map(orderItemDO -> BigDecimal.valueOf(orderItemDO.getQuantity()).multiply(orderItemDO.getProduct().getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var paymentDO = paymentPort.save(PaymentDO.builder()
                .status(PaymentDOStatus.PENDING)
                .amount(totalToPay)
                .currency(PaymentDOCurrency.EUR)
                .order(orderDO)
                .build());

        //TODO: call the payment gateway

        return null;
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
