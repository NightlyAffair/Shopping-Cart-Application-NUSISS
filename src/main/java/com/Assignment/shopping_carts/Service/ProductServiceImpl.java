package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ProductService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Model.Review;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProductsByKeyword(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> searchByCategoryAndKeyword(Integer categoryId, String keyword) {
        return productRepository.findByCategoryAndKeyword(categoryId, keyword);
    }

    @Override
    public List<Product> getAllProductsSortedByPriceAsc() {
        return productRepository.findAllByOrderByUnitPriceAsc();
    }

    @Override
    public List<Product> getAllProductsSortedByPriceDesc() {
        return productRepository.findAllByOrderByUnitPriceDesc();
    }

    @Override
    public List<Product> getAllProductsSortedByRating() {
        return productRepository.findAllByOrderByAverageRatingDesc();
    }

    @Override
    public List<Product> getProductsByCategoryWithSort(Integer categoryId, String keyword, Sort sort) {
        return productRepository.findProductsByCategorySort(categoryId, keyword, sort);
    }

    @Override
    public Page<Product> getProductsPaginated(Integer categoryId, String keyword, Pageable pageable) {
        return productRepository.findByCategoryAndKeywordPaginated(categoryId, keyword, pageable);
    }

    @Override
    public void updateProductAverageRating(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null && product.getReviews() != null) {
            double avg = product.getReviews().stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            product.setAverageRating(avg);
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getProductsSortedByRatingCalculated() {
        return productRepository.findByRatingDescCalculated();
    }

    // Helper method for pagination with sorting
    public Page<Product> getPage(Integer pageNumber, Integer pageSize, Integer categoryId, String keyword, String sort) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sortEnum(sort));
        return productRepository.findByCategoryAndKeywordPaginated(categoryId, keyword, pageRequest);
    }

    // Simple sort converter
    public Sort sortEnum(String sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "productName"); // Default: alphabetical
        }

        switch (sort) {
            case "priceAsc":
                return Sort.by(Sort.Direction.ASC, "unitPrice");
            case "priceDesc":
                return Sort.by(Sort.Direction.DESC, "unitPrice");
            case "rating":
                return Sort.by(Sort.Direction.DESC, "averageRating");
            default:
                return Sort.by(Sort.Direction.ASC, "productName");
        }
    }
}