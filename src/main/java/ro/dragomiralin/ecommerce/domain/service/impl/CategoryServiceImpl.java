package ro.dragomiralin.ecommerce.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.domain.service.ports.CategoryPort;
import ro.dragomiralin.ecommerce.infra.api.rest.error.ResourceNotFoundException;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryPort categoryPort;

    @Override
    public Page<Category> list(PageRequest pageRequest) {
        return categoryPort.list(pageRequest);
    }

    @Override
    public Category get(long id) {
        return categoryPort.get(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public long add(Category category) {
        return categoryPort.save(category);
    }
}
