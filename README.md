# Question 4 – E-Commerce Product API

## Description
This project implements a REST API for an e-commerce product catalog.
It supports pagination, filtering, searching, stock management,
and full CRUD operations for products.

## How to Run
1. Open the project in your IDE.
2. Run the Spring Boot main application class.
3. API base URL:
   http://localhost:8080

## Endpoints

### Get all products
GET /api/products

### Get products with pagination
GET /api/products?page=1&limit=5

### Get product by ID
GET /api/products/{productId}

### Get products by category
GET /api/products/category/Electronics

### Get products by brand
GET /api/products/brand/TechBrand

### Search products
GET /api/products/search?keyword=laptop

### Filter by price range
GET /api/products/price-range?min=50&max=300

### Get in-stock products
GET /api/products/in-stock

### Add product
POST /api/products

### Update product
PUT /api/products/{productId}

### Update stock quantity
PATCH /api/products/{productId}/stock?quantity=10

### Delete product
DELETE /api/products/{productId}

## Testing
All endpoints were tested using Postman.
A Postman collection is included in the repository.
