package ro.dragomiralin.ecommerce.domain.payment.port;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentDO;

import java.util.List;
import java.util.Optional;

public interface PaymentPort {

    PaymentDO save(PaymentDO paymentDO);

    Optional<PaymentDO> findById(long id);

    void delete(long id);

    PageDO<PaymentDO> list(int page, int size);

    List<PaymentDO> list();

}
