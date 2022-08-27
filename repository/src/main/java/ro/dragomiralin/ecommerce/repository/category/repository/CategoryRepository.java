package ro.dragomiralin.ecommerce.repository.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
