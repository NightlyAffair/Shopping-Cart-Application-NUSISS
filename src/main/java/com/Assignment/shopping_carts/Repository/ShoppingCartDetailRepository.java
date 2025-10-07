package com.Assignment.shopping_carts.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import com.Assignment.shopping_carts.Model.compositeKey.ShoppingCartDetailId;

public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, ShoppingCartDetailId>{
	//check one product is in cart or not
	Optional<ShoppingCartDetail> findByCustomerIdAndProductId(int customerId, int productId);

	//check all products for the customer
	List<ShoppingCartDetail> findByCustomerID(int customerId);
	
	//delete one product
	List<ShoppingCartDetail> deleteByCustomerIdAndProductId(int customerId, int productId);
	
	//delete all product
	List<ShoppingCartDetail> deleteByCustomerId(int customerId);
	
	//update quantity in cart
	List<ShoppingCartDetail> updateQuantity(int customerId, int productId, int newQuantity);
}
