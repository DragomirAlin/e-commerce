package ro.dragomiralin.ecommerce.domain.service.ports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

import java.util.Optional;

public interface ProductPort {

    Page<Product> list(PageRequest pageRequest);

    Optional<Product> get(long id);

    long save(Product product);

}
