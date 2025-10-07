package com.Assignment.shopping_carts.InterfaceMethods;

import java.util.List;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;

public interface ShoppingCartDetailInterface {
	//add product to cart
	void addProductToCart(int customerId, int productId, int quantity);

    //show all cart products
    List<ShoppingCartDetail> showCart(int customerId);

    //input new quantity directly
    void updateQuantity(int productId, int customerID, int newQuantity);

    //Add one
    void addOne(int customerId, int productId);

    //delete one
    void deleteOne(int customerId, int productId);

    //remove the select products from cart
    void removeProduct(int customerId, int productId);

    //clear all product from cart
    void clearCart(int customerId);

	//Calculate the total price for select product
	double sumTotal(int customerId);
	
}
