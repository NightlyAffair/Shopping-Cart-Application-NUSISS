package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Favourites;
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

    @Query("Select p.productName from Product p WHERE p.productId IN :ids")
    public List<String> findNameByProductId(@Param("ids")List<Integer> ids);

    @Query("select p.productName FROM Product p JOIN Favourites f " +
            "ON p.productId = f.productId WHERE f.customer = :customerId")
    List<String> findFavouriteProductNamesByProductId(@Param("customerId") int customerId);

    //check if a product is already favourited by a customer
    public boolean existsByCustomerIdAndProductId(Integer customerId, Integer productId);

    public void deleteByCustomerIdAndProductId(int customerId, int productId);

    //count how many customers favourited a product
    long countFavouritesByProductId(int productId);
}
