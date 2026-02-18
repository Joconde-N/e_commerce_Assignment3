package auca.ac.rw.question4_ecommerce_product.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.question4_ecommerce_product.model.Product;
import auca.ac.rw.question4_ecommerce_product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String addNewProduct(Product product){

        Optional<Product> existProduct = productRepository.findById(product.getProductId());

        if(existProduct.isPresent()){
            return "Product with id " + product.getProductId() + "already exists";
        } else{
            productRepository.save(product);
            return "Product added successfully";
        } 
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else{
            return null;
        }   
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Optional<Product> existProduct = productRepository.findById(id);
        if(existProduct.isPresent()){
            Product product = existProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setBrand(updatedProduct.getBrand());

            return productRepository.save(product);
        } else{
            return null;
        }
    }

    public Product deleteProduct(Long id){
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()){
            Product product = existingProduct.get();
            productRepository.delete(product);
            return product;
        }else{
            return null;
        }
    }

    public List<Product> searchByCategory(String categoryName){
        List<Product> products = productRepository.findByCategory(categoryName);

        if(products != null && !products.isEmpty()){
            return products;
        }else{
            return null;
        }
    }

    public List<Product> searchByPriceAndBrand(Double price, String brand){
        List<Product> products = productRepository.findByPriceAndBrand(price, brand);

        if(products != null && !products.isEmpty()){
            return products;
        }else{
            return null;
        }
    }

    public List<Product> getProductsByBrand(String brand) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> results = new ArrayList<>();

        for (Product p : allProducts) {
            if (p.getBrand() != null && p.getBrand().equalsIgnoreCase(brand)) {
                results.add(p);
            }
        }

        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }

    public List<Product> searchProductsByKeyword(String keyword) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> results = new ArrayList<>();

        String key = keyword.toLowerCase();

        for (Product p : allProducts) {

            String name;
            if (p.getName() == null) {
                name = "";
            } else {
                name = p.getName().toLowerCase();
            }

            String desc;
            if (p.getDescription() == null) {
                desc = "";
            } else {
                desc = p.getDescription().toLowerCase();
            }

            if (name.contains(key) || desc.contains(key)) {
                results.add(p);
            }
        }

        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }


    public List<Product> getProductsByPriceRange(Double min, Double max) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> results = new ArrayList<>();

        for (Product p : allProducts) {
            if (p.getPrice() != null && p.getPrice() >= min && p.getPrice() <= max) {
                results.add(p);
            }
        }

        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }

    public List<Product> getProductsInStock() {
        List<Product> allProducts = productRepository.findAll();
        List<Product> results = new ArrayList<>();

        for (Product p : allProducts) {
            if (p.getStockQuantity() > 0) {
                results.add(p);
            }
        }

        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }

    public Product updateStockQuantity(Long productId, int quantity) {
        Optional<Product> existProduct = productRepository.findById(productId);

        if (existProduct.isPresent()) {
            Product product = existProduct.get();
            product.setStockQuantity(quantity);
            return productRepository.save(product);
        } else {
            return null;
        }
    }




    
}
