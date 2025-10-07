package com.Assignment.shopping_carts.InterfaceMethods;

import java.util.List;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;

public interface ShoppingCartDetailInterface {
	
	//show all products in cart
	//void showCart(int customerId);
	//add product to cart
	void addProductToCart(int productId, int customerId, int quantity);
	
	List<ShoppingCartDetail> viewCart(int customerId);
	//update quantity for product in Cart
	//void updateQuantity(int productId, int customerID, int newQuantity);
	//remove product in cart 
	//void removeProduct(int productId, int customerID);
	//Calculate the total price for select product
	//double sumTotal(int customerID);
	
}
