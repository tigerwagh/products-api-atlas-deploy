package com.example.products.repository;

import com.example.products.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByCategoryAndPriceLessThan(String category, double price);

    @Query("{'category':?0, 'price':{$lt: ?1} }")
    List<Product> findCheapProductByCategory(String category,double maxPrice);

    @Query(value = "{'price': {$gte:?0, $lte: ?1}}",sort="{'price':1}")
    List<Product> findByPriceRangeSorted(double minPrice, double maxPrice);

}
