package ro.dragomiralin.ecommerce.domain.category;

import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

public interface CategoryService {

    PageDO<CategoryDO> list(int page, int size);

    CategoryDO get(Long id);

    Long add(CategoryDO category);

    void delete(Long id);
}
