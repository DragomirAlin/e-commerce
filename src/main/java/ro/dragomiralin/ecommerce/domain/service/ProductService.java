package ro.dragomiralin.ecommerce.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

public interface ProductService {

    Page<Product> list(PageRequest pageRequest);

    Product get(long id);

    long add(Product product);

}
