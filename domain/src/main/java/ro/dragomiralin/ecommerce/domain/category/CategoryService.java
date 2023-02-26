package ro.dragomiralin.ecommerce.domain.category;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

public interface CategoryService {

    @Cacheable("categories")
    PageDO<CategoryDO> list(int page, int size);

    @Cacheable("category")
    CategoryDO get(long id);

    @CacheEvict(value = "categories", allEntries = true)
    CategoryDO add(CategoryDO category);

    @CacheEvict(value = "categories", allEntries = true)
    void delete(long id);
}
