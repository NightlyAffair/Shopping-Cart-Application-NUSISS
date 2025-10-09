package com.Assignment.shopping_carts.Controller;


import com.Assignment.shopping_carts.InterfaceMethods.ShoppingCartDetailInterface;
import com.Assignment.shopping_carts.Model.ShoppingCartDetail;
import jakarta.servlet.http.HttpSession;
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
    public String addToCart( HttpSession session,@RequestParam int productId, @RequestParam int quantity, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        cartService.addProductToCart(customerId, productId, quantity);
        model.addAttribute("productId", productId);
        model.addAttribute("message", "Add to Cart Successfully!");
        return "productDetail";
    }

    @GetMapping("/view")
    public String showCart( HttpSession session, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        List<ShoppingCartDetail> products = cartService.showCart(customerId);
        double totalPrice = cartService.sumTotal(customerId);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("customerId", customerId);
        return "shoppingCart"; // 对应购物车页面
    }

    @PostMapping("/plus")
    public String increment(HttpSession session, @RequestParam int productId, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        cartService.addOne(customerId, productId);

        return showCart(session, model);
    }

    @PostMapping("/minus")
    public String decrement(HttpSession session, @RequestParam int productId, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        cartService.deleteOne(customerId, productId);
        return showCart(session, model);
    }

    @PostMapping("/remove")
    public String removeItem(HttpSession session, @RequestParam int productId, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        cartService.removeProduct(customerId, productId);
        return showCart(session, model);
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session, Model model) {
        int customerId = (int) session.getAttribute("customerId");
        cartService.clearCart(customerId);
        return showCart(session, model);
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        return showCart(session, model);
    }
}
