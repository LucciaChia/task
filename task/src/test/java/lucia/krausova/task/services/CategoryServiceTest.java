package lucia.krausova.task.services;

import lucia.krausova.task.entities.Category;
import lucia.krausova.task.repositories.CategoryRepository;
import lucia.krausova.task.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepository;

    @Rollback
    @Transactional
    @Test
    void saveCategory() {
        Category category = Category.builder()
                .name("vegetables")
                .build();
        categoryService.saveCategory(category);

        assertThat(categoryRepository.findAll().size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void deleteCategoryWithoutProductsById() {
        Category category = Category.builder()
                .name("vegetables")
                .build();
        categoryService.saveCategory(category);
        categoryService.deleteById(category.getId());

        assertThat(categoryRepository.findAll().size()).isEqualTo(2);

    }

    @Rollback
    @Transactional
    @Test
    void deleteCategoryWithProductsById() {

        int initialProductSize = productRepository.findAll().size();

        Integer categoryId = categoryRepository.findAll().get(1).getId();
        categoryRepository.deleteById(categoryId);

        int productSizeAfterCategoryDelete = productRepository.findAll().size();

        Optional<Category> categoryRepositoryById = categoryRepository.findById(categoryId);

        assertFalse(categoryRepositoryById.isPresent());
        assertThat(productSizeAfterCategoryDelete).isLessThan(initialProductSize);

    }
}