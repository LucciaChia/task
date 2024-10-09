package lucia.krausova.task.controllers;

import lombok.RequiredArgsConstructor;
import lucia.krausova.task.entities.Product;
import lucia.krausova.task.exceptions.ElementNotFoundException;
import lucia.krausova.task.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task/product")
public class ProductController {

    private final ProductService productService;

    // POST (create)
    @PostMapping(value = "/{categoryId}")
    public ResponseEntity addProduct(@PathVariable("categoryId") Integer categoryId, @RequestBody Product product) {
        productService.saveProduct(categoryId, product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT (create or update)
    @PutMapping(value = "/{productId}")
    public ResponseEntity updateCustomerByID(@PathVariable("productId") Integer productId,
                                             @RequestBody Product updatedProduct){

        if (productService.updateProductById(productId, updatedProduct).isEmpty()){
            throw new ElementNotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    // DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        if (!productService.deleteById(id)) {
            throw new ElementNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // GET (request a resource) implemented for double-checks purposes
    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id).orElseThrow(ElementNotFoundException::new);

    }

}
