package ro.dragomiralin.ecommerce.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.domain.service.ports.ProductPort;
import ro.dragomiralin.ecommerce.infra.api.rest.error.ResourceNotFoundException;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductPort productPort;

    @Override
    public Page<Product> list(PageRequest pageRequest) {
        return productPort.list(pageRequest);
    }

    @Override
    public Product get(long id) {
        return productPort.get(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public long add(Product product) {
        return productPort.save(product);
    }

    @Override
    public void delete(long id) {
        var product = get(id);
        productPort.delete(product.getId());
    }
}
