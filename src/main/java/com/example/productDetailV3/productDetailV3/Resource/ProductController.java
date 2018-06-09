package com.example.productDetailV3.productDetailV3.Resource;

import com.example.productDetailV3.productDetailV3.Document.Product;
import com.example.productDetailV3.productDetailV3.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.NotFoundException;


@RestController
@RequestMapping("/product")
@ApplicationPath("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientConsumer  clientConsumer;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Product product){
        productRepository.save(product);
    }

    @RequestMapping(value = "/{id}")
    public Product read(@PathVariable String id) throws org.json.simple.parser.ParseException{

        String Name = (String) clientConsumer.covertJSON(id);
       // product.setName(Name);
        System.out.println(Name);
       // Optional<Product> p = clientConsumer.find(id,Name);
        Product product = productRepository.findById(id).orElseThrow(()-> new NotFoundException());
        product.setName(Name);
        return product;

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody() Product product, @PathVariable String id){
        productRepository.save(product);
    }
}
