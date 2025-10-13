package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Favourites Repo Interface
 * Author: YH
 * Date: 2025-10-05
 * Modifier by : YH
 * Last Modified by : YH
 * Last Modified: 2025-10-07 18:00
 */

public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {
    public List<Favourites> findByCustomerId(int customerId);

    //joins product and fav table to get products that exists ONLY in favourites.
    @Query("SELECT p FROM Product p JOIN Favourites f " +
            "ON p.productId = f.productId WHERE f.customerId = :customerId")  //:customerid filters by specific customerid
    List<Product> findFavouriteProductsByCustomerId(@Param("customerId") int customerId); //maps custid to query param, then return list

    //check if a product is already favourited by a customer, counts No. of rows that exists with custid and productid
    //returns true if at least 1 row, if not false. then filter for specific user & product
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Favourites f WHERE f.customerId = :customerId AND f.productId = :productId ")
    public boolean existsByCustomerIdAndProductId(@Param("customerId")int customerId, @Param("productId") int productId);

    public void deleteByCustomerId(int customerId);

    public void deleteByCustomerIdAndProductId(int customerId, int productId);


    //count how many customers favourited a product
    long countFavouritesByProductId(int productId);
}
