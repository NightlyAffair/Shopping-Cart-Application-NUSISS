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
	List<ShoppingCartDetail> findByCustomerId(int customerId);
	
	//delete one product
	void deleteByCustomerIdAndProductId(int customerId, int productId);
	
	//delete all product
	void deleteByCustomerId(int customerId);

}
