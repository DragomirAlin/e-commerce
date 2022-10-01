package ro.dragomiralin.ecommerce.repository.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDOMapper {

    @Mapping(target = "products", ignore = true)
    CategoryDO toCategoryDO(Category category);

    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryDO categoryDO);

    PageDO<CategoryDO> toPageDO(Page<Category> page);

}
