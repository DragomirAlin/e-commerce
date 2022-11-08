package ro.dragomiralin.ecommerce.domain.category.port;

import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.Optional;

public interface CategoryPort {

    PageDO<CategoryDO> list(int page, int size);

    Optional<CategoryDO> get(long id);

    CategoryDO save(CategoryDO category);

    void delete(long id);

}
