package com.Assignment.shopping_carts.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.FavouritesRepository;

import jakarta.transaction.Transactional;
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

    @Autowired
    public FavouriteServiceImpl(FavouritesRepository favRepository) {
        this.favRepository = favRepository;
    }

    @Override
    public List<Favourites> findByCustomerId(int customerId) {
        return favRepository.findByCustomerId(customerId);
    }

    //allow a user to add or remove a product from their favourites list.
    //when customer click button, check if its alrdy favourited. if yes, unfavourite. otherwise favourite it.
    @Override
    @Transactional
    public String saveFavourites(int customerId, int productId) {
        if(favRepository.existsByCustomerIdAndProductId(customerId, productId)) {
            favRepository.deleteByCustomerIdAndProductId(customerId, productId);
            System.out.println("Like remove from favourites ");
            return "Removed from favourites";
        }
        Favourites favouriteSave = new Favourites();
        favouriteSave.setCustomerId(customerId);
        favouriteSave.setProductId(productId);
        favRepository.save(favouriteSave);
        System.out.println("Added to favourites!");
        return "Added to favourites!";
    }

    @Override
    public List<Product> findFavouriteProductsByCustomerId(int customerId) {
        return favRepository.findFavouriteProductsByCustomerId(customerId);
    }

    public void deleteByCustomerId(int customerId){
        favRepository.deleteByCustomerId(customerId);
    }

    @Override
    public void deleteByCustomerIdAndProductId(int customerId, int productId) {
        favRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }


    @Override
    public boolean isProductFavourited(int customerId, int productId) {
        return favRepository.existsByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public long countFavouritesByProductId(int productId) {
        return favRepository.countFavouritesByProductId(productId);
    } //counts how many times product item is favourited already. optional extra feature.
}