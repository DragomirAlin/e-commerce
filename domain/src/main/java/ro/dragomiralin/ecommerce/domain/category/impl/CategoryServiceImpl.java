package ro.dragomiralin.ecommerce.domain.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.category.error.CategoryNotFoundException;
import ro.dragomiralin.ecommerce.domain.category.port.CategoryPort;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    public static final String CATEGORIES_CACHE_KEY = "categories";
    public static final String CATEGORY_CACHE_KEY = "category";
    private final CategoryPort categoryPort;

    @Override
    @Cacheable(CATEGORIES_CACHE_KEY)
    public PageDO<CategoryDO> list(int page, int size) {
        return categoryPort.list(page, size);
    }

    @Override
    @Cacheable(CATEGORY_CACHE_KEY)
    public CategoryDO get(long id) {
        return categoryPort.get(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    @CacheEvict(value = CATEGORIES_CACHE_KEY, allEntries = true)
    public CategoryDO add(CategoryDO category) {
        return categoryPort.save(category);
    }

    @Override
    @CacheEvict(value = CATEGORIES_CACHE_KEY, allEntries = true)
    public void delete(long id) {
        var category = get(id);
        categoryPort.delete(category.getId());
    }
}
