package ro.dragomiralin.repository.category.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.domain.category.CategoryDO;
import ro.dragomiralin.domain.category.CategoryPort;
import ro.dragomiralin.domain.page.PageDO;
import ro.dragomiralin.repository.category.mapper.CategoryDOMapper;
import ro.dragomiralin.repository.category.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryPort {
    private final CategoryDOMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public PageDO<CategoryDO> list(int page, int size) {
        var categories = categoryRepository.findAll(PageRequest.of(page, size));
        return null;
    }

    @Override
    public Optional<CategoryDO> get(long id) {
        var optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(mapper::toCategoryDO);
    }

    @Override
    public long save(CategoryDO categoryDO) {
        var category = mapper.toCategory(categoryDO);
        var c = categoryRepository.save(category);
        return c.getId();
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
