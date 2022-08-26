package ro.dragomiralin.repository.category.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.domain.category.CategoryDO;
import ro.dragomiralin.repository.category.entity.Category;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryDOMapper {

    CategoryDO toCategoryDO(Category category);

    Category toCategory(CategoryDO categoryDO);

}
