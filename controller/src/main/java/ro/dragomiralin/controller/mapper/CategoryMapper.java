package ro.dragomiralin.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.controller.dto.CategoryCreateReq;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreateReq categoryCreateReq);
}
