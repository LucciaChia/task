package lucia.krausova.task.services;

import lucia.krausova.task.entities.Product;

import java.util.Optional;

public interface ProductService {

    Boolean saveProduct(Integer categoryId, Product product);

    Optional<Product> getProductById(Integer id);

    Boolean deleteById(Integer id);

    Optional<Product> updateProductById(Integer productId, Product updatedProduct);
}
