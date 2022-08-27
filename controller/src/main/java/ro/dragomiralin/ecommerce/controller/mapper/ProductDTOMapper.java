package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.dragomiralin.ecommerce.controller.dto.PageDTO;
import ro.dragomiralin.ecommerce.controller.dto.ProductDTO;
import ro.dragomiralin.ecommerce.controller.request.ProductCreateReq;
import ro.dragomiralin.ecommerce.domain.category.CategoryDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.ProductDO;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductDTOMapper {

    @Mapping(target = "categories", source = "categs")
    ProductDO toProduct(ProductCreateReq productCreateReq, List<CategoryDO> categs);

    ProductDTO toProductDTO(ProductDO product);

    PageDTO<ProductDTO> toPageDTO(PageDO<ProductDO> page);
}
