package ro.dragomiralin.repository.product.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.domain.page.PageDO;
import ro.dragomiralin.domain.product.ProductDO;
import ro.dragomiralin.domain.product.ProductPort;
import ro.dragomiralin.repository.product.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

    @Override
    public PageDO<ProductDO> list(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<ProductDO> get(long id) {
        return productRepository.findById(id);
    }

    @Override
    public long save(ProductDO productDO) {
        var product =
        var p = productRepository.save(product);
        return p.getId();
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
