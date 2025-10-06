package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import java.util.List;

/**
 * ProductService Interface
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by :
 * Last Modified:
 */

public interface ProductService {

    // Find all
    List<Product> findAllBy(Product product);

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
}
