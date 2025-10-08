package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ProductService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductServiceImpl Class
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Sheng Qi
 * Last Modified: 2025-10-07 10:30
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    @Override
    public List<Product> findByCategoryAndKeywordOrderByRating(Integer categoryId, String keyword) {
        return productRepository.findByCategoryAndKeywordOrderByRating(categoryId, keyword);
    }

    @Override
    public List<Product> findProductsByCategorySort(Integer categoryId, String keyword, String sort) {
        Sort sortObj = null;
        switch (sort !=null ? sort : "nameAsc") {
            case "priceAsc":
                sortObj = Sort.by(Sort.Direction.ASC, "unitPrice");
                break;
            case "priceDesc":
                sortObj = Sort.by(Sort.Direction.DESC, "unitPrice");
                break;
            case "nameAsc":
                sortObj = Sort.by(Sort.Direction.ASC, "productName");
                break;
        }
        return productRepository.findProductsByCategorySort(categoryId, keyword, sortObj);
    }
}