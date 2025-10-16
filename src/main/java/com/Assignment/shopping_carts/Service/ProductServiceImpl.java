/**
 * ProductServiceImpl Class
 * Authors: Glenn Min, Sheng Qi, Nithwin
 * Date: 2025-10-02
 * Last Modified by: Glenn Min
 * New Updates: minor bug fixes
 * Last Modified: 2025-10-11
 */

package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ProductService;
import com.Assignment.shopping_carts.InterfaceMethods.ReviewService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Model.Review;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ReviewService reviewService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ReviewService reviewService) {
        this.productRepository = productRepository;
        this.reviewService = reviewService;
    }

    // Create
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    private void populateProductDetails(Product product) {
        List<Review> reviews = reviewService.getReviewsForProduct(product.getProductId());
        if(reviews != null) {
            reviews.forEach(review ->
                    review.setCustomerName(reviewService
                            .getCustomerNameById(review.getCustomerId())));
        }
        Double averageRating = reviewService.getAverageRatingForProduct(product.getProductId());
        product.setAverageRating(averageRating != null ? Math.floor(averageRating) : 0.0);
        product.setReviews(reviews);
    }

    // Read
    @Override
    public Optional<Product> getProductById(int productId) {
       return productRepository.findById(productId).map(p -> {
           populateProductDetails(p);
           return p;
       });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Update
    @Override
    @Transactional
    public Product updateProduct(int productId, Product product) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        product.setProductId(productId);
        return productRepository.save(product);
    }

    // Delete
    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    // Searching
    @Override
    public List<Product> searchProductsByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.searchByKeyword(keyword.toLowerCase().trim());
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> searchByCategoryAndKeyword(Integer categoryId, String keyword) {
        return productRepository.findByCategoryAndKeywordOrderByRating(categoryId, keyword);
    }

    // Sorting
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

    // Pagination
    @Override
    public Page<Product> getProductsPaginated(Integer categoryId, String keyword, Pageable pageable) {
        Page<Product> page = productRepository.findByCategoryAndKeywordPaginated(categoryId, keyword, pageable);
        return page;
    }

    @Override
    public List<Product> getPopulatedProductDetails(Page<Product> page) {
        return page.getContent().stream().map(product -> {
            populateProductDetails(product);
            return product;
        }).toList();
    }

    // Rating
    @Override
    @Transactional
    public void updateProductAverageRating(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null && product.getReviews() != null && !product.getReviews().isEmpty()) {
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

    // Sort Enum
    public Sort sortEnum(String sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "productName");
        }
        switch (sort) {
            case "nameAsc":
                return Sort.by(Sort.Direction.ASC, "productName");
            case "nameDesc":
                return Sort.by(Sort.Direction.DESC, "productName");
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