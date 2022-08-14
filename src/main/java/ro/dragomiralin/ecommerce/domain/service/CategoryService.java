package ro.dragomiralin.ecommerce.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;


public interface CategoryService {

    Page<Category> list(PageRequest pageRequest);

    Category get(long id);

    long add(Category category);

}
