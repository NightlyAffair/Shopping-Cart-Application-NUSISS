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
 * Last Modified: 2025-10-07 18:00
 */

public interface FavouriteService {
    public List<Favourites> findByCustomerId(int customerId);
    public boolean toggleFavourite(int customerId, int productId);
    public List<String> getFavouriteProducts(int customerId);
    public List<Product> getFavouriteProducts(int productId);

    public long countFavouritesByProductId(int customerId, int productId);

    //public boolean existsByCustomerIdAndProductId(int customerId, int productId);
    //private boolean addFavourite(int customerId, int productId);
    //private void deleteFavourite(int customerId, int productId);
}
