package lucia.krausova.task.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lucia.krausova.task.entities.Category;
import lucia.krausova.task.entities.Product;
import lucia.krausova.task.mappers.CategoryMapper;
import lucia.krausova.task.mappers.ProductMapper;
import lucia.krausova.task.model.ProductDTO;
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

    private final ProductMapper productMapper;

    @Override
    public Boolean saveProduct(ProductDTO productDTO) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(productDTO.getCategoryId());
        Category category = categoryRepositoryById.get();

        Product product = productMapper.productDtoToProduct(productDTO);

        product.setCategory(category);
        category.addProduct(product);
        categoryRepository.flush();
        productRepository.save(product);
        return true;
    }

    @Override
    public Optional<ProductDTO> getProductById(Integer id) {
        return Optional.ofNullable(productMapper.productToProductDto(productRepository.findById(id).orElse(null)));
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
    public Optional<ProductDTO> updateProductById(Integer id, ProductDTO updatedProductDTO) {
        AtomicReference<Optional<ProductDTO>> atomicReference = new AtomicReference<>();
        productRepository.findById(id).ifPresentOrElse(existingProduct -> {
            existingProduct.setName(updatedProductDTO.getName());
            existingProduct.setPrice(updatedProductDTO.getPrice());
            productRepository.save(existingProduct);
            atomicReference.set(Optional.of(productMapper.productToProductDto(existingProduct)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

}
