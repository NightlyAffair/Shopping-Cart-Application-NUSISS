package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Favourites Controller Class
 * Author: yh
 * Date: 2025-10-04
 * Modifier by : yh
 * Last Modified by : yh
 * Last Modified: 2025-10-07 18:00
 */

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouriteService favService;

    public FavouritesController(FavouriteService favService) {
        this.favService = favService;
    }

    @PostMapping("/toggle")
    @ResponseBody
    public Boolean toggleFavourite(@RequestParam int productId, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return false;
        }
        return favService.toggleFavourite(customerId, productId);
    }

    //Get all favourite items for a customer
    @GetMapping("/customer")
    public String getFavourites(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            //temp, hardcorded customerid for testing
            customerId = 1;
            session.setAttribute("customerId", customerId);
            //return "redirect:/Log";
        }
        List<Product> FavProducts = favService
                .findFavouriteProductsByCustomerId(customerId);
        model.addAttribute("favourites", FavProducts);
        return "favourites";
    }

}
