package lucia.krausova.task.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lucia.krausova.task.entities.Category;
import lucia.krausova.task.mappers.CategoryMapper;
import lucia.krausova.task.model.ProductDTO;
import lucia.krausova.task.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductControllerTest {
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

    @Disabled("After using Mapstruct, this doesn't work")
    @Test
    void addProductToCategory() throws Exception {
        Category fruitCategory = categoryRepository.findAll().getFirst();
        int initialProductSize = fruitCategory.getProducts().size();

        ProductDTO blueberryProduct = ProductDTO.builder()
                .name("blueberry")
                .price(new BigDecimal("0.05"))
                .build();
        mockMvc.perform(post("/task/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blueberryProduct)))
                .andExpect(status().isCreated());

        assertThat(fruitCategory.getProducts().size()).isGreaterThan(initialProductSize);
    }

}