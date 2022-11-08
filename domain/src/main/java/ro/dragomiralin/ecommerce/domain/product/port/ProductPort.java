package ro.dragomiralin.ecommerce.domain.product.port;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.Optional;

public interface ProductPort {

    PageDO<ProductDO> list(int page, int size);

    Optional<ProductDO> get(long id);

    long save(ProductDO productDO);

    void delete(long id);

}
