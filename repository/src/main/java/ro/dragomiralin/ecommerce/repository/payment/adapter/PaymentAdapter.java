package ro.dragomiralin.ecommerce.repository.payment.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.port.PaymentPort;
import ro.dragomiralin.ecommerce.repository.payment.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {
    private final PaymentRepository paymentRepository;

    @Override
    public long save(PaymentDO paymentDO) {
        return 0;
    }

    @Override
    public Optional<PaymentDO> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
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
