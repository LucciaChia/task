package lucia.krausova.task.controllers;

import lombok.RequiredArgsConstructor;
import lucia.krausova.task.exceptions.ElementNotFoundException;
import lucia.krausova.task.model.CategoryDTO;
import lucia.krausova.task.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity addCategory(@RequestBody CategoryDTO category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity deleteById(@PathVariable("uuid") Integer uuid) {
        if (!categoryService.deleteById(uuid)) {
            throw new ElementNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // request a resource - implemented for double-checks purposes
    @GetMapping(value = "/{uuid}")
    public CategoryDTO getCategoryById(@PathVariable("uuid") Integer uuid){
        return categoryService.getCategoryById(uuid).orElseThrow(ElementNotFoundException::new);
    }
}
