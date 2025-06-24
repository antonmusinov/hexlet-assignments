package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private static final String ERR_MSG = "Product with id %s not found";

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERR_MSG.formatted(id)));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product update) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ERR_MSG.formatted(id)));
        product.setPrice(update.getPrice());
        product.setTitle(update.getTitle());

        return productRepository.save(product);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
