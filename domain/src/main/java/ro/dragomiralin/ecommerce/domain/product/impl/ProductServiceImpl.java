package ro.dragomiralin.ecommerce.domain.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.product.error.ProductNotFoundException;
import ro.dragomiralin.ecommerce.domain.product.port.ProductPort;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCTS_CACHE_KEY = "products";
    public static final String PRODUCT_CACHE_KEY = "product";
    private final ProductPort productPort;

    @Override
    @Cacheable(PRODUCTS_CACHE_KEY)
    public PageDO<ProductDO> list(int page, int size) {
        return productPort.list(page, size);
    }

    @Override
    @Cacheable(PRODUCT_CACHE_KEY)
    public ProductDO get(long id) {
        return productPort.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_KEY, allEntries = true)
    public ProductDO add(ProductDO product) {
        return productPort.save(product);
    }

    @Override
    @CacheEvict(value = PRODUCTS_CACHE_KEY, allEntries = true)
    public void delete(long id) {
        var product = get(id);
        productPort.delete(product.getId());
    }
}
