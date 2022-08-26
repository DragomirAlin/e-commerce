package ro.dragomiralin.domain.product;

import ro.dragomiralin.domain.page.PageDO;

public interface ProductService {

    PageDO<ProductDO> list(int size, int page);

    ProductDO get(long id);

    long add(ProductDO product);

    void delete(long id);

}
