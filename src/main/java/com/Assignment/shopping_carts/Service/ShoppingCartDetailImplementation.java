package com.Assignment.shopping_carts.Service;

import java.util.List;
import java.util.Optional;

import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import com.Assignment.shopping_carts.Repository.ShoppingCartDetailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartDetailImplementation implements ShoppingCartDetailInterface {
	@Autowired
	private ShoppingCartDetailRepository cartRepo;
	
	@Override
    //add product from productList to shoppingCart
	public void addProductToCart(int customerId, int productId, int quantity) {
		ShoppingCartDetail id = new ShoppingCartDetail();
        id.setCustomerId(customerId);
        id.setProductId(productId);
        //check whether cart have the same product yet
		Optional<ShoppingCartDetail> opt = cartRepo.findByCustomerIdAndProductId(customerId, productId);

		if (opt.isPresent()) {//if have, then just update the quantity
			ShoppingCartDetail detail = opt.get();
			detail.setQuantity(detail.getQuantity() + quantity);
			cartRepo.save(detail);
		}
		else {//if not, create a new product with setted quantity in cart
			ShoppingCartDetail detail = new ShoppingCartDetail();
            detail.setCustomerId(customerId);
            detail.setProductId(productId);
            detail.setQuantity(quantity);
            cartRepo.save(detail);
		}
	}
    @Override
    //return all product in shopping cart
    public List<ShoppingCartDetail> showCart(int customerId) {
        return cartRepo.findByCustomerId(customerId);
    }

    @Override
    //let user change quantity directly in shoppingCart page
    public void updateQuantity(int productId, int customerId, int newQuantity) {
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        product.setQuantity(newQuantity);
        cartRepo.save(product);
    }

    @Override
    // one product quantity add one in shoppingCart page
    public void addOne(int customerId, int productId) {
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        product.setQuantity(product.getQuantity() + 1);
        cartRepo.save(product);
    }

    @Override
    // one product quantity minus one in shoppingCart page
    public void deleteOne(int customerId, int productId) {
        ShoppingCartDetail product = cartRepo.findByCustomerIdAndProductId(customerId, productId).get();
        int newQuantity = product.getQuantity() - 1;
        if (newQuantity > 0) {
            product.setQuantity(newQuantity);
            cartRepo.save(product);
        }
        else {
            cartRepo.delete(product);
        }
    }

    @Override
    //remove one product from shopping cart in shoppingCart page
    public void removeProduct(int customerId, int productId) {
        cartRepo.deleteByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    //remove all products from shopping cart in shoppingCart page
    public void clearCart(int customerId) {
        cartRepo.deleteByCustomerId(customerId);
    }

    @Override
    //calculate the total select price automatically in shoppingCart page
    public double sumTotal(int customerId) {
        List<ShoppingCartDetail> products = cartRepo.findByCustomerId(customerId);
        double totalPrice = 0.0;
        if (products.isEmpty()) {
            totalPrice = 0.0;
        }
        else{
            for (ShoppingCartDetail product : products) {
                totalPrice += product.getSubPrice();
            }
        }
        return totalPrice;
    }
}
