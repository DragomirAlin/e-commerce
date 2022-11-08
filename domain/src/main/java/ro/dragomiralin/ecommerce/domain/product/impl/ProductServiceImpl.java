package ro.dragomiralin.ecommerce.domain.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.product.error.ProductNotFoundException;
import ro.dragomiralin.ecommerce.domain.product.port.ProductPort;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductPort productPort;

    @Override
    public PageDO<ProductDO> list(int page, int size) {
        return productPort.list(page, size);
    }

    @Override
    public ProductDO get(long id) {
        return productPort.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public ProductDO add(ProductDO product) {
        return productPort.save(product);
    }

    @Override
    public void delete(long id) {
        var product = get(id);
        productPort.delete(product.getId());
    }
}
