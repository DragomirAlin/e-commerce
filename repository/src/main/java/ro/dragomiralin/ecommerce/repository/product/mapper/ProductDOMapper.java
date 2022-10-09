package ro.dragomiralin.ecommerce.repository.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.repository.category.entity.Category;
import ro.dragomiralin.ecommerce.repository.category.mapper.CategoryDOMapper;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", uses = {CategoryDOMapper.class})
public interface ProductDOMapper {

    Product toProduct(ProductDO productDO);

    ProductDO toProductDO(Product product);

    PageDO<ProductDO> toPageDO(Page<Product> page);

}
