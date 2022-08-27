package ro.dragomiralin.ecommerce.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.error.ProductNotFoundException;

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
    public long add(ProductDO product) {
        return productPort.save(product);
    }

    @Override
    public void delete(long id) {
        var product = get(id);
        productPort.delete(product.getId());
    }
}
