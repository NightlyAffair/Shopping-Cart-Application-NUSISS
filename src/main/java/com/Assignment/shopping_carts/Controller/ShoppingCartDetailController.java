package com.Assignment.shopping_carts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailImplementation;
import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;

@RequestMapping("/shoppingCartDetail")
@Controller
public class ShoppingCartDetailController {
	@Autowired
	private ShoppingCartDetailInterface cartService;
	
	@Autowired
	public void setCartService(ShoppingCartDetailImplementation cartServiceImpl) {
		this.cartService = cartServiceImpl;
	}
	
	@GetMapping("")
	public String listShoppingCartDetail() {
		return "shoppingCartDetailList";
	}
	
	@PostMapping("/addFromProductPage")
	public String addFromProductPage(@RequestParam("CustomerId") int customerId, @RequestParam("ProductId") int productId, @RequestParam("Quantity") int quantity) {
		cartService.addProductToCart(customerId, productId, quantity);
		return "";
	}
}
