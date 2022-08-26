package ro.dragomiralin.domain.product;

import ro.dragomiralin.domain.page.PageDO;

import java.util.Optional;

public interface ProductPort {

    PageDO<ProductDO> list(int page, int size);

    Optional<ProductDO> get(long id);

    long save(ProductDO product);

    void delete(long id);

}
