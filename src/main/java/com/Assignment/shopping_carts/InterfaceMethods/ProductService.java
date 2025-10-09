package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * ProductService Interface
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Updated for product details
 * Last Modified: 2025-10-09
 */

public interface ProductService {

    // Basic CRUD Operations
    Product createProduct(Product product);
    Optional<Product> getProductById(int productId);
    List<Product> getAllProducts();
    Product updateProduct(int productId, Product product);
    void deleteProduct(int productId);

    // Search Operations
    List<Product> searchProductsByKeyword(String keyword);
    List<Product> getProductsByCategory(Category category);
    List<Product> searchByCategoryAndKeyword(Integer categoryId, String keyword);

    // Sorting Operations
    List<Product> getAllProductsSortedByPriceAsc();
    List<Product> getAllProductsSortedByPriceDesc();
    List<Product> getAllProductsSortedByRating();
    List<Product> getProductsByCategoryWithSort(Integer categoryId, String keyword, Sort sort);

    // Pagination
    Page<Product> getProductsPaginated(Integer categoryId, String keyword, Pageable pageable);

    // Rating Management
    void updateProductAverageRating(int productId);
    List<Product> getProductsSortedByRatingCalculated();
}