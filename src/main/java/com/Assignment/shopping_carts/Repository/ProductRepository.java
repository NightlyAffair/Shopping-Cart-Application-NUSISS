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

/**
 * ProductRepository Interface
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Sheng Qi, Nithvin(Pagination), Updated for averageRating
 * Last Modified: 2025-10-09
 */

public interface ProductRepository extends JpaRepository<Product,Integer> {

    // Search by Keyword
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.productName) LIKE %:keyword% OR " +
            "LOWER(p.description) LIKE %:keyword%")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    // Find by Category
    List<Product> findByCategory(Category category);

    // Sort by Price Asc
    List<Product> findAllByOrderByUnitPriceAsc();

    // Sort by Price Desc
    List<Product> findAllByOrderByUnitPriceDesc();

    // Sort by highest avg rating (NOW USES STORED FIELD - MUCH FASTER!)
    List<Product> findAllByOrderByAverageRatingDesc();

    @Query("SELECT p FROM Product p LEFT JOIN p.reviews r GROUP BY p.productId ORDER BY AVG(r.rating) DESC")
    List<Product> findByRatingDescCalculated();

    // Category & Search
    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND " +
            "(:keyword IS NULL OR " +
            "LOWER(p.productName) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%)")
    List<Product> findByCategoryAndKeyword(@Param("categoryId") Integer categoryId,
                                           @Param("keyword") String keyword);

    // Sort by rating using stored field (FASTER)
    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId = 0 OR p.category.categoryId = :categoryId) " +
            "AND (:keyword IS NULL OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY p.averageRating DESC")
    List<Product> findByCategoryAndKeywordOrderByRating(@Param("categoryId") Integer categoryId,
                                                        @Param("keyword") String keyword);

    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId = 0 OR p.category.categoryId = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR p.productName LIKE CONCAT('%', :keyword, '%'))")
    List<Product> findProductsByCategorySort(@Param("categoryId") Integer categoryId,
                                             @Param("keyword") String keyword,
                                             Sort sort);

    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId = 0 OR p.category.categoryId = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR p.productName LIKE CONCAT('%', :keyword, '%'))")
    Page<Product> findByCategoryAndKeywordPaginated(@Param("categoryId") Integer categoryId,
                                                    @Param("keyword") String keyword,
                                                    Pageable pageable);

}