package lucia.krausova.task.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lucia.krausova.task.exceptions.ElementNotFoundException;
import lucia.krausova.task.mappers.CategoryMapper;
import lucia.krausova.task.model.CategoryDTO;
import lucia.krausova.task.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CategoryControllerTest {

    @Autowired
    CategoryController categoryController;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryMapper categoryMapper;

    MockMvc mockMvc;

    @BeforeEach
    void initialSetup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Rollback
    @Transactional
    @Test
    void addCategory() throws Exception {
        CategoryDTO categoryDto = CategoryDTO.builder()
                .name("vegetables")
                .build();

        mockMvc.perform(post("/task/category")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isCreated());
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() throws Exception {
        CategoryDTO existingCategory = categoryMapper.categoryToCategoryDto(categoryRepository.findAll().get(1));

        mockMvc.perform(delete("/task/category/" + existingCategory.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingCategory)))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteByIdNotFound() {
        assertThrows(ElementNotFoundException.class, () -> categoryController.deleteById(123));
    }
}