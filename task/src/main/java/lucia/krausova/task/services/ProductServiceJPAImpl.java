package lucia.krausova.task.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lucia.krausova.task.entities.Category;
import lucia.krausova.task.entities.Product;
import lucia.krausova.task.repositories.CategoryRepository;
import lucia.krausova.task.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceJPAImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Boolean saveProduct(Integer uuid, Product product) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(uuid);
        Category category = categoryRepositoryById.get();
        product.setCategory(category);
        category.addProduct(product);
        categoryRepository.flush();
        productRepository.save(product);
//        categoryRepository.save(category);
        return true;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return Optional.ofNullable(productRepository.findById(id).orElse(null));
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (productRepository.existsById(id)) {
            Optional<Product> productOptional = productRepository.findById(id);
            Category category = productOptional.get().getCategory();
            category.removeProduct(productOptional.get());
            categoryRepository.flush();
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Product> updateProductById(Integer id, Product updatedProduct) {
        AtomicReference<Optional<Product>> atomicReference = new AtomicReference<>();
        productRepository.findById(id).ifPresentOrElse(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            productRepository.save(existingProduct);
            atomicReference.set(Optional.of(existingProduct));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

}
