package ro.dragomiralin.ecommerce.domain.service.ports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;

import java.util.Optional;

public interface CategoryPort {

    Page<Category> list(PageRequest pageRequest);

    Optional<Category> get(long id);

    long save(Category category);

}
