package lucia.krausova.task.services;

import lucia.krausova.task.model.ProductDTO;

import java.util.Optional;

public interface ProductService {

    Boolean saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> getProductById(Integer id);

    Boolean deleteById(Integer id);

    Optional<ProductDTO> updateProductById(Integer productId, ProductDTO updatedProductDTO);
}
