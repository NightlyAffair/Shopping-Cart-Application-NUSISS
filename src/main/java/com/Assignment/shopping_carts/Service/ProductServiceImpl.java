package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ProductService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductServiceImpl Class
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Sheng Qi, Nithvin(Pagination), Updated for product details
 * Last Modified: 2025-10-09
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get product by ID
    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
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
        // Now uses the stored averageRating field - much faster!
        return productRepository.findAllByOrderByAverageRatingDesc();
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
        Sort sortObj;
        switch (sort != null ? sort : "nameAsc") {
            case "priceAsc":
                sortObj = Sort.by(Sort.Direction.ASC, "unitPrice");
                break;
            case "priceDesc":
                sortObj = Sort.by(Sort.Direction.DESC, "unitPrice");
                break;
            case "nameAsc":
                sortObj = Sort.by(Sort.Direction.ASC, "productName");
                break;
            case "nameDesc":
                sortObj = Sort.by(Sort.Direction.DESC, "productName");
                break;
            case "rating":
                sortObj = Sort.by(Sort.Direction.DESC, "averageRating");
                break;
            default:
                sortObj = Sort.by(Sort.Direction.ASC, "productName");
        }
        return productRepository.findProductsByCategorySort(categoryId, keyword, sortObj);
    }

    public Page<Product> getPage(Integer pageNumber, Integer pageSize, Integer categoryId, String keyword, String sort) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sortEnum(sort));
        return productRepository.findByCategoryAndKeywordPaginated(categoryId, keyword, pageRequest);
    }

    public Sort sortEnum(String sort) {
        switch (sort != null ? sort : "nameAsc") {
            case "priceAsc":
                return Sort.by(Sort.Direction.ASC, "unitPrice");
            case "priceDesc":
                return Sort.by(Sort.Direction.DESC, "unitPrice");
            case "nameAsc":
                return Sort.by(Sort.Direction.ASC, "productName");
            case "nameDesc":
                return Sort.by(Sort.Direction.DESC, "productName");
            case "rating":
                return Sort.by(Sort.Direction.DESC, "averageRating");
            default:
                return Sort.by(Sort.Direction.ASC, "productName");
        }
    }
}