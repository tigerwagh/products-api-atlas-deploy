package com.example.products.controller;

import com.example.products.entity.Product;
import com.example.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct()
    {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable String id)
    {
        return productService.getProdutById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product)
    {
        productService.getProdutById(id).orElseThrow();
        product.setId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id)
    {
        productService.getProdutById(id).orElseThrow();
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/costly-products")
    public List<Product> getCostlyProducts(@RequestParam double price)
    {
        return productService.findByPriceGreaterThan(price);
    }
    @GetMapping("/category-cheap")
    public List<Product> getCheapProduct(@RequestParam String category, @RequestParam double maxPrice)
    {
        return productService.findByCategoryAndPriceLessThan(category,maxPrice);
    }

    @GetMapping("/price-range")
    public List<Product> getProductInRange(@RequestParam double minPrice, @RequestParam double maxPrice)
    {
        return productService.findByPriceRangeSorted(minPrice,maxPrice);
    }
}
