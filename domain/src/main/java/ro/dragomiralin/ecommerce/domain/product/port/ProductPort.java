package ro.dragomiralin.ecommerce.domain.product.port;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.Optional;

public interface ProductPort {

    PageDO<ProductDO> list(int page, int size);

    Optional<ProductDO> get(Long id);

    Long save(ProductDO productDO);

    void delete(Long id);

}
