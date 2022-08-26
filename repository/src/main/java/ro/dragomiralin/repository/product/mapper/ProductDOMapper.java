package ro.dragomiralin.repository.product.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.domain.product.ProductDO;
import ro.dragomiralin.repository.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductDOMapper {

    Product toProduct(ProductDO productDO);

    ProductDO toProductDO(Product product);
}
