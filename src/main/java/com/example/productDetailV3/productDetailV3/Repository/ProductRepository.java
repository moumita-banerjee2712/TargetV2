package com.example.productDetailV3.productDetailV3.Repository;

import com.example.productDetailV3.productDetailV3.Document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends MongoRepository<Product,String>,CrudRepository<Product,String>{


}
