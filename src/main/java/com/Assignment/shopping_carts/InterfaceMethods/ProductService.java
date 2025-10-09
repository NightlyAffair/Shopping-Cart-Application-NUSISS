package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * ProductService Interface
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Updated for product details
 * Last Modified: 2025-10-09
 */

public interface ProductService {

    // Find product by ID (for product details page)
    Product getProductById(int productId);

    // Search by keyword
    List<Product> searchByKeyword(String keyword);

    // Find by category
    List<Product> findByCategory(Category category);

    // Sort by price ascending
    List<Product> findAllByOrderByUnitPriceAsc();

    // Sort by price descending
    List<Product> findAllByOrderByUnitPriceDesc();

    // Sort by highest average rating
    List<Product> findByRatingDesc();

    // Filter by category + keyword
    List<Product> findByCategoryAndKeyword(Integer categoryId, String keyword);

    List<Product> findByCategoryAndKeywordOrderByRating(Integer categoryId, String keyword);

    List<Product> findProductsByCategorySort(Integer categoryId, String keyword, String sort);

}