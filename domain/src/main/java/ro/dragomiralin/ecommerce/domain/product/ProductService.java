package ro.dragomiralin.ecommerce.domain.product;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

public interface ProductService {

    PageDO<ProductDO> list(int size, int page);

    ProductDO get(long id);

    ProductDO add(ProductDO product);

    void delete(long id);

}
