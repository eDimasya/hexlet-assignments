package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> index() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().forEach(product ->
                products.add(productMapper.map(product)));
        return products;
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        return productMapper.map(
                productRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Product with id " + id + " not found"))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO data) {
        Product product = productMapper.map(data);
        product = productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO data) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " not found"));
        productMapper.update(data, product);
        productRepository.save(product);
        return productMapper.map(product);
    }
    // END
}
