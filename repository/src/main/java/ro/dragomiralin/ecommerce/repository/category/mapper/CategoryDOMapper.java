package ro.dragomiralin.ecommerce.repository.category.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDOMapper {

    CategoryDO toCategoryDO(Category category);

    Category toCategory(CategoryDO categoryDO);

    PageDO<CategoryDO> toPageDO(Page<Category> page);

}
