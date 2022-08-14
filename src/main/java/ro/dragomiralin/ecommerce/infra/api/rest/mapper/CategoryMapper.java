package ro.dragomiralin.ecommerce.infra.api.rest.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CategoryCreateReq;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreateReq categoryCreateReq);
}
