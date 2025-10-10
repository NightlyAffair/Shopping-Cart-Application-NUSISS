package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.FavouritesRepository;
import com.Assignment.shopping_carts.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Favourites serv impl
 * Author: YH
 * Date: 2025-10-05
 * Modifier by : YH
 * Last Modified by : YH
 * Last Modified: 2025-10-07 11:00
 */

@Service
@Transactional
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouritesRepository favRepository;
    private final ProductRepository productRepository;

    public FavouriteServiceImpl(FavouritesRepository favRepository, ProductRepository productRepository) {
        this.favRepository = favRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Favourites> findByCustomerId(int customerId) {
        return favRepository.findByCustomerId(customerId);
    }

    //allow a user to add or remove a product from their favourites list.
    //when customer click button, check if its alrdy favourited. if yes, unfavourite. otherwise favourite it.
    @Override
    @Transactional
    public boolean toggleFavourite(int customerId, int productId) {
        if(favRepository.existsByCustomerIdAndProductId(customerId, productId)) {
            favRepository.deleteByCustomerIdAndProductId(customerId, productId);
            return false;
        } else {
            Favourites favouriteSave = new Favourites();
            favouriteSave.setCustomerId(customerId);
            favouriteSave.setProductId(productId);
            favRepository.save(favouriteSave);
            return true;
        }
    }

    @Override
    public List<Product> findFavouriteProductsByCustomerId(int customerId) {
        return favRepository.findFavouriteProductsByCustomerId(customerId);
    }

    @Override
    public long countFavouritesByProductId(int productId) {
        return favRepository.countFavouritesByProductId(productId);
    } //counts how many times product item is favourited already. optional extra feature.
}



    /*
    //if you want to check if customer favourited which product.
    @Override
    public boolean existsByCustomerIdAndProductId(int customerId, int productId) {
        return favRepository.existsByCustomerIdAndProductId(customerId, productId);
    } //return true if exists.
     */

/*
    @Transactional
    //extra method done previously as back up to the toggle. add a favourite if it doesn’t exist.
    public boolean addFavourite(int customerId, int productId) {
        //checks if exist. save new fav if it doesnt exist.
        if(!favRepository.existsByCustomerIdAndProductId(customerId, productId)) {
            Favourites favouriteSave = new Favourites();
            favouriteSave.setCustomerId(customerId);
            favouriteSave.setProductId(productId);
            favRepository.save(favouriteSave);
            return true;
        } return false;
    }

    @Transactional
    public void deleteFavourite(int customerId, int productId) {
        favRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }
*/