package ro.dragomiralin.ecommerce.infra.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.ports.ProductPort;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;
import ro.dragomiralin.ecommerce.infra.persistence.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> list(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Product> get(long id) {
        return productRepository.findById(id);
    }

    @Override
    public long save(Product product) {
        var p = productRepository.save(product);
        return p.getId();
    }
}
