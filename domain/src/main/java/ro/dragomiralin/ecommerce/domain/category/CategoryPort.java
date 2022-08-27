package ro.dragomiralin.ecommerce.domain.category;

import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.Optional;

public interface CategoryPort {

    PageDO<CategoryDO> list(int page, int size);

    Optional<CategoryDO> get(long id);

    long save(CategoryDO category);

    void delete(long id);

}
