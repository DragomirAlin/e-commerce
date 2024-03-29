package ro.dragomiralin.ecommerce.repository.payment.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.port.PaymentPort;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;
import ro.dragomiralin.ecommerce.repository.payment.mapper.PaymentDOMapper;
import ro.dragomiralin.ecommerce.repository.payment.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {
    private final PaymentDOMapper mapper;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public PaymentDO save(PaymentDO paymentDO) {
        var payment = mapper.toPayment(paymentDO);
        var createdPayment = paymentRepository.save(payment);
        return mapper.toPaymentDO(createdPayment);
    }

    @Override
    public Optional<PaymentDO> findById(long id) {
        return paymentRepository.findById(id).map(mapper::toPaymentDO);
    }

    @Override
    public void delete(long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PageDO<PaymentDO> list(int page, int size) {
        return null;
    }

    @Override
    public List<PaymentDO> list() {
        return null;
    }
}
