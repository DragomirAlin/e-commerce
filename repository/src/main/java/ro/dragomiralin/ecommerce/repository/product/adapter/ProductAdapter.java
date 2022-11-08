package ro.dragomiralin.ecommerce.repository.product.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.product.port.ProductPort;
import ro.dragomiralin.ecommerce.repository.product.mapper.ProductDOMapper;
import ro.dragomiralin.ecommerce.repository.product.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {
    private final ProductDOMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public PageDO<ProductDO> list(int page, int size) {
        var productPage = productRepository.findAll(PageRequest.of(page, size));
        return mapper.toPageDO(productPage);
    }

    @Override
    public Optional<ProductDO> get(long id) {
        var optionalProduct = productRepository.findById(id);
        return optionalProduct.map(mapper::toProductDO);
    }

    @Override
    public long save(ProductDO productDO) {
        var product = mapper.toProduct(productDO);
        var p = productRepository.save(product);
        return p.getId();
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
