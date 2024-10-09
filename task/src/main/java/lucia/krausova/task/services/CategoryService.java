package lucia.krausova.task.services;

import lucia.krausova.task.entities.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> getCategoryById(Integer id);

    Boolean saveCategory(Category category);

    boolean deleteById(Integer id);
}
