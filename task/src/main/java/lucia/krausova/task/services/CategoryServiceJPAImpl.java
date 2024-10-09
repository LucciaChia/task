package lucia.krausova.task.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lucia.krausova.task.mappers.CategoryMapper;
import lucia.krausova.task.model.CategoryDTO;
import lucia.krausova.task.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceJPAImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public Optional<CategoryDTO> getCategoryById(Integer id) {
        return Optional.ofNullable(categoryMapper
                .categoryToCategoryDto(categoryRepository.findById(id).orElse(null)));
    }

    @Override
    public Boolean saveCategory(CategoryDTO categoryDto) {
        categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDto));
        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
