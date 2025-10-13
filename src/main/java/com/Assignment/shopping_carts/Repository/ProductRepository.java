/**
 * ProductRepository Interface
 * Authors: Glenn Min, Sheng Qi, Nithvin
 * Date: 2025-10-02
 * Last Modified by: Glenn Min
 * New Updates: minor bug fixes
 * Last Modified: 2025-10-11
 */

package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Search Keyword
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    // Filter category
    List<Product> findByCategory(Category category);

    // Sort price asc
    List<Product> findAllByOrderByUnitPriceAsc();

    // Sort price desc
    List<Product> findAllByOrderByUnitPriceDesc();

    // Sort the highest avg Ratings
    List<Product> findAllByOrderByAverageRatingDesc();

    // Calculate avg Ratings
    @Query("SELECT p FROM Product p LEFT JOIN p.reviews r " +
            "GROUP BY p.productId ORDER BY AVG(r.rating) DESC")
    List<Product> findByRatingDescCalculated();

    // Category + Keyword Search
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId = 0 OR p.category.categoryId = :categoryId) AND " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Product> findByCategoryAndKeyword(@Param("categoryId") Integer categoryId,
                                           @Param("keyword") String keyword);

    // Search Order By Rating
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId = 0 OR p.category.categoryId = :categoryId) AND " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY p.averageRating DESC")
    List<Product> findByCategoryAndKeywordOrderByRating(@Param("categoryId") Integer categoryId,
                                                        @Param("keyword") String keyword);

    // Category + Keyword + Sort
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId = 0 OR p.category.categoryId = :categoryId) AND " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Product> findProductsByCategorySort(@Param("categoryId") Integer categoryId,
                                             @Param("keyword") String keyword,
                                             Sort sort);

    // Main paginated version for browsing pages
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId = 0 OR p.category.categoryId = :categoryId) AND " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Product> findByCategoryAndKeywordPaginated(@Param("categoryId") Integer categoryId,
                                                    @Param("keyword") String keyword,
                                                    Pageable pageable);
}
