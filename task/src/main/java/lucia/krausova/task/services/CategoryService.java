package lucia.krausova.task.services;

import lucia.krausova.task.model.CategoryDTO;

import java.util.Optional;

public interface CategoryService {

    Optional<CategoryDTO> getCategoryById(Integer id);

    Boolean saveCategory(CategoryDTO category);

    Boolean deleteById(Integer id);
}
