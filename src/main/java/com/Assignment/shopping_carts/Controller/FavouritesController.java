package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Repository.FavouritesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Favourites Controller Class
 * Author: yh
 * Date: 2025-10-04
 * participants : yh  /  jason zhou
 * Last Modified by : jason zhou
 * Last Modified: 2025-10-13 21:00
 */

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouriteService favService;

    public FavouritesController(FavouriteService favService) {
        this.favService = favService;
    }

    //gets customerid from sesssion. then get list of products, add list to model, return view fav
    @GetMapping
    public String showFavourites(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if(customerId == null) {
            return "redirect:/login";
            //customerId = 1;
            // session.setAttribute("customerId", customerId);
        } model.addAttribute("favourites", favService.findFavouriteProductsByCustomerId(customerId));
        model.addAttribute("customerId", customerId);
        return "favourites";
    }

    //toggle button to update heart icon.
    @PostMapping("/save")  //read productId from thymeleaf.
    @ResponseBody //return string directly instead of view. (for the return portion below)
    public String saveFavourite(@RequestParam int productId, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
            //customerId = 1;
            //session.setAttribute("customerId", customerId);
        }
        return favService.saveFavourites(customerId, productId); //toggles fav status
        //return "redirect:/displayProducts/details/" + productId;
    }

    //Get all favourite items for a customer
    @GetMapping("/customer")
    public String getFavourites(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            //session.setAttribute("customerId", customerId);
            return "redirect:/Log";
        }
        List<Product> FavProducts = favService
                .findFavouriteProductsByCustomerId(customerId);
        model.addAttribute("favourites", FavProducts);
        return "favourites";
    }

    //for clear all products button, one shot delete all.
    @PostMapping("/clear")
    public String deleteAllFavourites(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            //session.setAttribute("customerId", customerId);
            return "redirect:/Log";
        }
        favService.deleteByCustomerId(customerId);
        return "redirect:/favourites";
    }

    //this is for trash bin icon, deletes only 1 product
    @PostMapping("/remove-product")
    public String deleteSingleFavourites(@RequestParam int productId, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            //session.setAttribute("customerId", customerId);
            return "redirect:/Log";
        }
        favService.deleteByCustomerIdAndProductId(customerId, productId);
        return "redirect:/favourites";
    }

    @GetMapping("/status/{productId}")
    @ResponseBody //returns true or false
    public boolean checkFavStatus(@PathVariable int productId, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            // session.setAttribute("customerId", customerId);
            return false;
        }
        return favService.isProductFavourited(customerId,productId);
    }



    @GetMapping("/resume")
    public String resumeFavourite(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        String productIdStr = (String) session.getAttribute("pendingProductId");

        if (customerId != null && productIdStr != null) {
            int productId = Integer.parseInt(productIdStr);
            favService.saveFavourites(customerId, productId);
            session.removeAttribute("pendingActionType");
            session.removeAttribute("pendingProductId");
            session.removeAttribute("pendingQuantity");
            session.removeAttribute("redirectAfterLogin");
            return "redirect:/products/details/" + productId;
        }
        return "redirect:/products/page";
    }
}



/*
    @GetMapping("/CustomerId/{customerId}")
    public List<Favourites> findByCustomerId(@PathVariable("customerId")
                                       int customerId, Model model) {
        return favService.findByCustomerId(customerId);
    } */