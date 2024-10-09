package lucia.krausova.task.mappers;

import lucia.krausova.task.entities.Product;
import lucia.krausova.task.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product productDtoToProduct(ProductDTO productDTO);

    ProductDTO productToProductDto(Product product);
}
