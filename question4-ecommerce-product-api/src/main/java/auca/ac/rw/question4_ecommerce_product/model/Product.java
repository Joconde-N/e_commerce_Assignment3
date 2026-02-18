package auca.ac.rw.question4_ecommerce_product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    private  Long productId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "brand")
    private String brand;

    public Product(){}

    public Product(Long productId, String name, String description, Double price, String category, int stockQuantity, String brand){
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.brand = brand;

    }

    public Long getProductId(){
        return productId;
    }
    public void setProductId(Long productId){
        this.productId = productId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public int getStockQuantity(){
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    
}
