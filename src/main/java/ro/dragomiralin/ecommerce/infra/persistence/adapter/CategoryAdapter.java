package ro.dragomiralin.ecommerce.infra.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.ports.CategoryPort;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;
import ro.dragomiralin.ecommerce.infra.persistence.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryPort {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> list(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Category> get(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public long save(Category category) {
        var c = categoryRepository.save(category);
        return c.getId();
    }
}
