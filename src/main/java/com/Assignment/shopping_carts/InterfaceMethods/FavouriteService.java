package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;

import java.util.List;
/**
 * Favourites Service
 * Author: YH
 * Date: 2025-10-05
 * Modifier by : YH
 * Last Modified by : YH
 * Last Modified: 2025-10-10 18:00
 */

public interface FavouriteService {
    public List<Favourites> findByCustomerId(int customerId);
    public String saveFavourites(int customerId, int productId);
    public List<Product> findFavouriteProductsByCustomerId(int customerId);

    public long countFavouritesByProductId(int productId);

    //public boolean existsByCustomerIdAndProductId(int customerId, int productId);
}
