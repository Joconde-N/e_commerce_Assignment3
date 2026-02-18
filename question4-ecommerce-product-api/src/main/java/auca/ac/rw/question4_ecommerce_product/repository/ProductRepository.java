package auca.ac.rw.question4_ecommerce_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import auca.ac.rw.question4_ecommerce_product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);  

    // List<Product> findByBrand(String brand);

    List<Product> findByPriceAndBrand(Double price, String brand);

    // List<Product> findByNameStartsWith(String name);

    // List<Product> findByPriceGreaterThan(Double price);

    // List<Product> findByPriceBetween(Double fistPrice, Double lastPrice);
    
}
