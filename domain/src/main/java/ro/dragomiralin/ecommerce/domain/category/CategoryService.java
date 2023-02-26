package ro.dragomiralin.ecommerce.domain.category;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

public interface CategoryService {

    PageDO<CategoryDO> list(int page, int size);

    CategoryDO get(long id);

    CategoryDO add(CategoryDO category);

    void delete(long id);
}
