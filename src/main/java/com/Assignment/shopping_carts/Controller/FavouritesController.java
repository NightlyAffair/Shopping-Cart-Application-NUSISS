package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.FavouritesRepository;
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
 * Last Modified: 2025-10-10 18:00
 */

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouriteService favService;

    public FavouritesController(FavouriteService favService) {
        this.favService = favService;
    }
/*
    @GetMapping("/CustomerId/{customerId}")
    public List<Favourites> findByCustomerId(@PathVariable("customerId")
                                       int customerId, Model model) {
        return favService.findByCustomerId(customerId);
    } */

    @GetMapping
    public String showFavourites(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if(customerId == null) {
            //return "redirect:/Log";
            customerId = 1;
            session.setAttribute("customerId", customerId);
        } model.addAttribute("favourites", favService.findFavouriteProductsByCustomerId(customerId));
        return "favourites";
    }

    @PostMapping("/save")
    public String saveFavourite(@RequestParam int productId, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            //return "redirect:/Log";

            customerId = 1;
            session.setAttribute("customerId", customerId);
        }
        String result = favService.saveFavourites(customerId, productId);
        return "redirect:/displayProducts/details/" + productId;
    }

    //Get all favourite items for a customer
    @GetMapping("/customer")
    public String getFavourites(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            //return "redirect:/Log";
            customerId = 1;
            session.setAttribute("customerId", customerId);
        }
        List<Product> FavProducts = favService
                .findFavouriteProductsByCustomerId(customerId);
        model.addAttribute("favourites", FavProducts);
        return "favourites";
    }

    @PostMapping("/clear")
    public String deleteFavourites(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            //return "redirect:/Log";
            customerId = 1;
            session.setAttribute("customerId", customerId);
        }
        favService.deleteByCustomerId(customerId);
        return "redirect:/favourites";
    }

}
