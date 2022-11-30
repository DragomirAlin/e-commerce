package ro.dragomiralin.ecommerce.repository.product.adapter;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.product.port.ProductPort;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;
import ro.dragomiralin.ecommerce.repository.product.mapper.ProductDOMapper;
import ro.dragomiralin.ecommerce.repository.product.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {
    private final ProductDOMapper mapper;
    private final SessionFactory sessionFactory;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public PageDO<ProductDO> list(int page, int size) {
        var q = """
                SELECT p FROM Product p
                """;

        var productsPage = getCurrentSession().createQuery(q, Product.class);
        productsPage.setFirstResult(page);
        productsPage.setMaxResults(size);
        var products = productsPage.getResultList();

        return PageDO.<ProductDO>builder()
                .content(products.stream().map(mapper::toProductDO).toList())
                .number(page)
                .size(size)
                .totalElements(productsPage.getMaxResults())
                .totalPages(productsPage.getMaxResults() / size)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDO> get(long id) {
        var q = """
                SELECT p FROM Product p
                WHERE p.id = :id
                """;

        var productQuery = getCurrentSession().createQuery(q, Product.class);
        productQuery.setParameter("id", id);

        return Optional.of(productQuery.getSingleResult())
                .map(mapper::toProductDO);
    }

    @Override
    public ProductDO save(ProductDO productDO) {
        var product = mapper.toProduct(productDO);
        var q = """ 
                INSERT INTO Product (id, name, price, quantity)      
                 """;

        var productQuery = getCurrentSession().createQuery(q, Product.class);

        var createdProduct = productRepository.save(product);
        return mapper.toProductDO(createdProduct);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
}
