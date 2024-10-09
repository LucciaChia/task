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
    @PostMapping(value = "/{category_uuid}")
    public ResponseEntity addProduct(@PathVariable("category_uuid") Integer uuid, @RequestBody Product product) {
        productService.saveProduct(uuid, product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT (create or update)
    @PutMapping(value = "/{product_uuid}")
    public ResponseEntity updateCustomerByID(@PathVariable("product_uuid") Integer uuid,
                                             @RequestBody Product updatedProduct){

        if (productService.updateProductById(uuid, updatedProduct).isEmpty()){
            throw new ElementNotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    // DELETE
    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity deleteById(@PathVariable("uuid") Integer id) {
        if (!productService.deleteById(id)) {
            throw new ElementNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // GET (request a resource) implemented for double-checks purposes
    @GetMapping(value = "/{uuid}")
    public Product getProductById(@PathVariable("uuid") Integer id) {
        return productService.getProductById(id).orElseThrow(ElementNotFoundException::new);

    }

}
