package ro.dragomiralin.ecommerce.repository.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
