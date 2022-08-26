package ro.dragomiralin.domain.category;

import ro.dragomiralin.domain.page.PageDO;


public interface CategoryService {

    PageDO<CategoryDO> list(int page, int size);

    CategoryDO get(long id);

    long add(CategoryDO category);

    void delete(long id);
}
