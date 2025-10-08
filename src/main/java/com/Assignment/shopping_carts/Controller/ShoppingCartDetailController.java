package com.Assignment.shopping_carts.Controller;


import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;
import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RequestMapping("/shoppingCartDetail")
@Controller
public class ShoppingCartDetailController {
    @Autowired
    private ShoppingCartDetailInterface cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam int customerId, @RequestParam int productId, @RequestParam int quantity, Model model) {
        cartService.addProductToCart(customerId, productId, quantity);
        model.addAttribute("productId", productId);
        model.addAttribute("message", "Add to Cart Successfully!");
        return "productDetail";
    }

    @GetMapping("/view")
    public String showCart(@RequestParam int customerId, Model model) {
        List<ShoppingCartDetail> products = cartService.showCart(customerId);
        double totalPrice = cartService.sumTotal(customerId);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("customerId", customerId);
        return "shoppingCart"; // 对应购物车页面
    }

    @PostMapping("/plus")
    public String increment(@RequestParam int customerId, @RequestParam int productId, Model model) {
        cartService.addOne(customerId, productId);
        return showCart(customerId, model);
    }

    @PostMapping("/minus")
    public String decrement(@RequestParam int customerId, @RequestParam int productId, Model model) {
        cartService.deleteOne(customerId, productId);
        return showCart(customerId, model);
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam int customerId, @RequestParam int productId, Model model) {
        cartService.removeProduct(customerId, productId);
        return showCart(customerId, model);
    }

    @PostMapping("/clear")
    public String clearCart(@RequestParam int customerId, Model model) {
        cartService.clearCart(customerId);
        return showCart(customerId, model);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam int customerId, Model model) {
        return showCart(customerId, model);
    }
}
