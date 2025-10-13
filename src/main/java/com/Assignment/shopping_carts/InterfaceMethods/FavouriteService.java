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
    //add to fav if its not already added. otherwise add it
    public String saveFavourites(int customerId, int productId);
    //fetch full product attributes, like name, descrpition, image url
    public List<Product> findFavouriteProductsByCustomerId(int customerId);
    //removes all records of customer whole list removed.
    public void deleteByCustomerId(int customerId);
    //removes SINGLE product of customer, so need product ID.
    public void deleteByCustomerIdAndProductId(int customerId, int productId);
    //checks if its alryd favuourited, return true if yes, false if no.
    public boolean isProductFavourited(int customerId, int productId);

    public long countFavouritesByProductId(int productId);

}
