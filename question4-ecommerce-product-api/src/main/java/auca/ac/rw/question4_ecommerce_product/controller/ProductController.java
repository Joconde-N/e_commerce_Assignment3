package auca.ac.rw.question4_ecommerce_product.controller;

import auca.ac.rw.question4_ecommerce_product.model.Product;
import auca.ac.rw.question4_ecommerce_product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;


    @PostMapping(value = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewProduct(@RequestBody Product product) {
        String saveProduct = productService.addNewProduct(product);

        if(saveProduct.equals("Product added successfully")){
            return new ResponseEntity<>(saveProduct, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(saveProduct, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts(){
        List<Product> prods = productService.getAllProducts();
        if(prods.isEmpty()){
            return new ResponseEntity<>("There's no product found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(prods, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@PathVariable Long productId){
        Product product = productService.getProductById(productId);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Product with ID: "+ productId + "was not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product prod){
        Product existingProduct = productService.getProductById(productId);
        if(existingProduct != null){
            existingProduct.setName(prod.getName());
            existingProduct.setDescription(prod.getDescription());
            existingProduct.setPrice(prod.getPrice());
            existingProduct.setCategory(prod.getCategory());
            existingProduct.setStockQuantity(prod.getStockQuantity());
            existingProduct.setBrand(prod.getBrand());

            productService.addNewProduct(existingProduct);
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product with ID: "+ productId + "was not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        Product existingProduct = productService.getProductById(productId);
        if(existingProduct != null){
            productService.deleteProduct(productId);
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product with ID: "+ productId + "was not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/searchByCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProductsByCategory(@RequestParam String category){
        List<Product> products = productService.searchByCategory(category);
        if(products != null){
            return new ResponseEntity<>(products, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("No products found in category: " + category, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/searchByPriceAndBrand", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByPriceAndBrand(@RequestParam Double price, @RequestParam String brand){
        
        List<Product> products = productService.searchByPriceAndBrand(price, brand);
        if(products != null){
            return new ResponseEntity<>(products, HttpStatus.FOUND);

        }else{
            return new ResponseEntity<>("No products with Price: " + price + " and brand: " + brand, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/brand/{brand}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No products found for brand: " + brand, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByKeyword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProductsByKeyword(@RequestParam String keyword) {
        List<Product> products = productService.searchProductsByKeyword(keyword);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No products found for keyword: " + keyword, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/price-range", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {

        if (min > max) {
            return new ResponseEntity<>("Min price cannot be greater than Max price", HttpStatus.CONFLICT);
        }

        List<Product> products = productService.getProductsByPriceRange(min, max);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No products found in price range: " + min + " - " + max, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/in-stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsInStock() {
        List<Product> products = productService.getProductsInStock();

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No products currently in stock", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{productId}/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStockQuantity(@PathVariable Long productId, @RequestParam int quantity) {

        if (quantity < 0) {
            return new ResponseEntity<>("Stock quantity cannot be negative", HttpStatus.CONFLICT);
        }

        Product updated = productService.updateStockQuantity(productId, quantity);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product with ID: " + productId + " was not found", HttpStatus.NOT_FOUND);
        }
    }
    
}
