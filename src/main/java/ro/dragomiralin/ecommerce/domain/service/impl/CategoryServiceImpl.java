package ro.dragomiralin.ecommerce.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.domain.service.ports.CategoryPort;
import ro.dragomiralin.ecommerce.infra.persistence.domain.Category;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryPort categoryPort;

    @Override
    public Page<Category> list(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Category get(long id) {
        return null;
    }

    @Override
    public long add(Category category) {
        return 0;
    }
}
