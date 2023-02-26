package ro.dragomiralin.ecommerce.domain.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

public interface ProductService {

    @Cacheable("products")
    PageDO<ProductDO> list(int size, int page);

    @Cacheable("product")
    ProductDO get(long id);

    @CacheEvict(value = "products", allEntries = true)
    ProductDO add(ProductDO product);

    @CacheEvict(value = "products", allEntries = true)
    void delete(long id);

}
