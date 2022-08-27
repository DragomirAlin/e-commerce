package ro.dragomiralin.ecommerce.repository.category.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.domain.category.CategoryDO;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDOMapper {

    CategoryDO toCategoryDO(Category category);

    Category toCategory(CategoryDO categoryDO);

}
