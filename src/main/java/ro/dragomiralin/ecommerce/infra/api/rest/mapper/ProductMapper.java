package ro.dragomiralin.ecommerce.infra.api.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ProductCreateReq;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Category;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categories", source = "categs")
    Product toProduct(ProductCreateReq productCreateReq, List<Category> categs);
}
