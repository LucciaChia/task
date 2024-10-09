package lucia.krausova.task.mappers;

import lucia.krausova.task.entities.Category;
import lucia.krausova.task.model.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    Category categoryDtoToCategory(CategoryDTO categoryDTO);

    CategoryDTO categoryToCategoryDto(Category category);

}
