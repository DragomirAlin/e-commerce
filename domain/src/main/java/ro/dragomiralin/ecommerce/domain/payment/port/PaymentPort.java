package ro.dragomiralin.ecommerce.domain.payment.port;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;

import java.util.List;
import java.util.Optional;

public interface PaymentPort {

    Long save(PaymentDO paymentDO);

    Optional<PaymentDO> findById(Long id);

    void delete(Long id);

    PageDO<PaymentDO> list(int page, int size);

    List<PaymentDO> list();

}
