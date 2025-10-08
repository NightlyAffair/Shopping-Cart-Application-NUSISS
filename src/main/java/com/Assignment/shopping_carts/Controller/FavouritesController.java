package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
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
    public Boolean toggleFavourite(@RequestParam int customerId,
                                   @RequestParam int productId) {
        return favService.toggleFavourite(customerId, productId);
    }

    //Get all favourite items for a customer
    @GetMapping("/customer/{customerId}")
    public String getFavourites(@PathVariable int customerId, Model model) {
        List<Product> FavProducts = favService
                .findFavouriteProductsByCustomerId(customerId);
        model.addAttribute("FavProducts", FavProducts);
        return "favourites";
    }

}
