package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Optional<Product> getProdutById(String id)
    {
        return productRepository.findById(id);
    }
    public void deleteProductById(String id)
    {
         productRepository.deleteById(id);
    }

    public List<Product> findByPriceGreaterThan(double price)
    {
        return productRepository.findByPriceGreaterThan(price);
    }
    public  List<Product> findByCategoryAndPriceLessThan(String category, double price)
    {
        return productRepository.findByCategoryAndPriceLessThan(category,price);
    }

    public  List<Product> findCheapProductByCategory(String category,double maxPrice)
    {
        return productRepository.findCheapProductByCategory(category,maxPrice);
    }
    public  List<Product> findByPriceRangeSorted(double minPrice, double maxPrice)
    {
        return productRepository.findByPriceRangeSorted(minPrice,maxPrice);
    }
}
