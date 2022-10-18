package ro.dragomiralin.ecommerce.domain.product;

import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

public interface ProductService {

    PageDO<ProductDO> list(int size, int page);

    ProductDO get(Long id);

    Long add(ProductDO product);

    void delete(Long id);

}
