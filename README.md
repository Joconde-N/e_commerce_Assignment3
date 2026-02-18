# E-Commerce Product API

## Description
This project implements a REST API for managing an e-commerce product catalog using Spring Boot.

It supports:
- Full CRUD operations
- Searching products by keyword (name or description)
- Filtering products by brand
- Filtering products within a price range
- Viewing products that are in stock
- Updating product stock quantity

Filtering and searching logic are implemented in the Service layer using standard Java loops and conditional statements.

## How to Run
1. Open the project in your IDE.
2. Run the Spring Boot main application class.
3. API base URL:
   http://localhost:8080

## Endpoints

### Get all products
GET /api/products

### Get product by ID
GET /api/products/{productId}

### Get products by category
GET /api/products/searchByCategory?category=Electronics

### Get products by brand
GET /api/products/brand/{brand}

Example:
GET /api/products/brand/TechBrand

### Search products by keyword
GET /api/products/search?keyword=laptop

### Filter by price range
GET /api/products/price-range?min=50&max=300

### Get in-stock products
GET /api/products/in-stock

### Add product
POST /api/products/addProduct

Example JSON body:
{
  "productId": 1,
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1200,
  "category": "Electronics",
  "stockQuantity": 10,
  "brand": "TechBrand"
}

### Update product
PUT /api/products/{productId}

### Update stock quantity
PATCH /api/products/{productId}/stock?quantity=10

### Delete product
DELETE /api/products/{productId}

## Testing
All endpoints were tested using Postman.
Each endpoint was verified for successful responses and error handling cases.
