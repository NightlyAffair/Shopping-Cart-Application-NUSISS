package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ProductService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductServiceImpl Class
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by :
 * Last Modified:
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Find all
    @Override
    public List<Product> findAllBy(Product product) {
        return productRepository.findAllBy(product);
    }

    // Search by keyword
    @Override
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    // Find by category
    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    // Sort by price ascending
    @Override
    public List<Product> findAllByOrderByUnitPriceAsc() {
        return productRepository.findAllByOrderByUnitPriceAsc();
    }

    // Sort by price descending
    @Override
    public List<Product> findAllByOrderByUnitPriceDesc() {
        return productRepository.findAllByOrderByUnitPriceDesc();
    }

    // Sort by rating
    @Override
    public List<Product> findByRatingDesc() {
        return productRepository.findByRatingDesc();
    }

    // Filter by category + keyword
    @Override
    public List<Product> findByCategoryAndKeyword(Integer categoryId, String keyword) {
        return productRepository.findByCategoryAndKeyword(categoryId, keyword);
    }
}