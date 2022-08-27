package ro.dragomiralin.ecommerce.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.category.error.CategoryNotFoundException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryPort categoryPort;

    @Override
    public PageDO<CategoryDO> list(int page, int size) {
        return categoryPort.list(page, size);
    }

    @Override
    public CategoryDO get(long id) {
        return categoryPort.get(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public long add(CategoryDO category) {
        return categoryPort.save(category);
    }

    @Override
    public void delete(long id) {
        var category = get(id);
        categoryPort.delete(category.getId());
    }
}
