package exercise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDTO> index() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().forEach(product ->
                products.add(productMapper.map(product)));
        return products;
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        return productMapper.map(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductCreateDTO data) {
        try {
            Product product = productMapper.map(data);
            productRepository.save(product);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productMapper.map(product));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO data) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Product with id " + id + " not found"));
            productMapper.update(data, product);
            productRepository.save(product);
            return ResponseEntity.ok().body(productMapper.map(product));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found"));
        product.getCategory().getProducts().remove(product);
        productRepository.delete(product);
    }
    // END
}
