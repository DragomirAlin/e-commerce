package ro.dragomiralin.ecommerce.domain.product;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

public interface ProductService {

    PageDO<ProductDO> list(int size, int page);

    ProductDO get(long id);

    long add(ProductDO product);

    void delete(long id);

}
