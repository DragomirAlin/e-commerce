package ro.dragomiralin.ecommerce.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.domain.service.ports.ProductPort;
import ro.dragomiralin.ecommerce.infra.persistence.domain.Product;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductPort productPort;

    @Override
    public Page<Product> list(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Product get(long id) {
        return null;
    }

    @Override
    public long add(Product product) {
        return 0;
    }
}
