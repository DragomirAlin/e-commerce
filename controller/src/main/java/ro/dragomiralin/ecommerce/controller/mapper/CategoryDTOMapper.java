package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.CategoryDTO;
import ro.dragomiralin.ecommerce.controller.dto.PageDTO;
import ro.dragomiralin.ecommerce.controller.request.CategoryCreateReq;
import ro.dragomiralin.ecommerce.domain.category.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

@Mapper(componentModel = "spring")
public interface CategoryDTOMapper {

    CategoryDO toCategory(CategoryCreateReq categoryCreateReq);

    CategoryDTO toCategoryDTO(CategoryDO categoryDO);

    PageDTO<CategoryDTO> toPageDTO(PageDO<CategoryDO> page);
}
