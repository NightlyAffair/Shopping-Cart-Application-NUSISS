//package com.Assignment.shopping_carts.InterfaceMethods;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
//import com.Assignment.shopping_carts.Model.compositeKey.ShoppingCartDetailId;
//import com.Assignment.shopping_carts.Repository.ShoppingCartDetailRepository;
//
//import jakarta.transaction.Transactional;
//
//@Service
//@Transactional
//public class ShoppingCartDetailImplementation implements ShoppingCartDetailInterface{
//	@Autowired
//	private ShoppingCartDetailRepository cartRepo;
//
//	@Override
//	public void addProductToCart(int customerId, int productId, int quantity) {
//
//		Optional<ShoppingCartDetail> opt = cartRepo.findByCustomerIdAndProductId(customerId, productId);
//
//		if (opt.isPresent()) {
//			ShoppingCartDetail detail = opt.get();
//			detail.setQuantity(detail.getQuantity() + quantity);
//			cartRepo.save(detail);
//		}
//		else {
//			ShoppingCartDetail detail = new ShoppingCartDetail();
//			cartRepo.save(detail);
//		}
//	}
//}
