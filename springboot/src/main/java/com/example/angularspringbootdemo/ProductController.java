package com.example.angularspringbootdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

        @Autowired
	    private ProductRepository productRepo;

      @GetMapping("/product")
      public List<Product> getAllProducts() {
      return productRepo.findAll();
      		}

       @GetMapping("/product/{id}")
      		public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
      				throws ResourceNotFoundException {
         Product product = productRepo.findById(productId)
      					.orElseThrow(() -> new ResourceNotFoundException("not found for this id :: " + productId));
      			return ResponseEntity.ok().body(product);
       }

       @PostMapping(value = "/product")
 	   public ResponseEntity<Product> postStudent(@RequestBody Product product) {
 	    try {
        Product _product = productRepo.save(product);
 	      return new ResponseEntity<>(_product, HttpStatus.CREATED);
 	    } catch (Exception e) {
 	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
 	    }
 	  }

       @PutMapping("/product/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
	         @Validated @RequestBody Product productDetails) throws ResourceNotFoundException {

	        Product product = productRepo.findById(productId)
	        .orElseThrow(() -> new ResourceNotFoundException("not found for this id :: " + productId));


         product.setName(productDetails.getName());
         product.setPrice(productDetails.getPrice());

	        final Product updatedProduct = productRepo.save(product);
	        return ResponseEntity.ok(updatedProduct);
	    }

	   @DeleteMapping("/product/{id}")
	    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
	         throws ResourceNotFoundException {

        Product product = productRepo.findById(productId)
         .orElseThrow(() -> new ResourceNotFoundException("not found for this id :: " + productId));

	        productRepo.delete(product);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }


}
