package lucia.krausova.task.controllers;

import lombok.RequiredArgsConstructor;
import lucia.krausova.task.exceptions.ElementNotFoundException;
import lucia.krausova.task.model.ProductDTO;
import lucia.krausova.task.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/task/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity addProductToCategory(@RequestBody ProductDTO productDto) {
        productService.saveProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity updateCustomerByID(@PathVariable("productId") Integer productId,
                                             @RequestBody ProductDTO updatedProductDTO){

        if (productService.updateProductById(productId, updatedProductDTO).isEmpty()){
            throw new ElementNotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        if (!productService.deleteById(id)) {
            throw new ElementNotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ProductDTO getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id).orElseThrow(ElementNotFoundException::new);

    }

}
