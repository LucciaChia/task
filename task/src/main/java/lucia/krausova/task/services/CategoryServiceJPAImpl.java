package lucia.krausova.task.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lucia.krausova.task.entities.Category;
import lucia.krausova.task.repositories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceJPAImpl implements CategoryService {

    // it doesn't work if it's not final
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return Optional.ofNullable(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public Boolean saveCategory(Category category) {
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {

        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
