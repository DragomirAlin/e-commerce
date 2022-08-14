package ro.dragomiralin.ecommerce.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
