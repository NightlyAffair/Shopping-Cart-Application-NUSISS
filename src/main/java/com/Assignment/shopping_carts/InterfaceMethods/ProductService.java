/**
 * ProductService Interface
 * Authors: Glenn Min
 * Date: 2025-10-06
 * Last Modified by: Glenn Min
 * New Updates: minor bug fixes
 * Last Modified: 2025-10-11
 */

package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // Basic CRUD
    Product createProduct(Product product);
    Optional<Product> getProductById(int productId);
    List<Product> getAllProducts();
    Product updateProduct(int productId, Product product);
    void deleteProduct(int productId);

    // Search
    List<Product> searchProductsByKeyword(String keyword);
    List<Product> getProductsByCategory(Category category);
    List<Product> searchByCategoryAndKeyword(Integer categoryId, String keyword);

    // Sorting
    List<Product> getAllProductsSortedByPriceAsc();
    List<Product> getAllProductsSortedByPriceDesc();
    List<Product> getAllProductsSortedByRating(); // Uses stored averageRating
    List<Product> getProductsByCategoryWithSort(Integer categoryId, String keyword, Sort sort);

    // Pagination
    Page<Product> getProductsPaginated(Integer categoryId, String keyword, Pageable pageable);

    // Rating Management
    void updateProductAverageRating(int productId);
    List<Product> getProductsSortedByRatingCalculated(); // Calculates from reviews
}