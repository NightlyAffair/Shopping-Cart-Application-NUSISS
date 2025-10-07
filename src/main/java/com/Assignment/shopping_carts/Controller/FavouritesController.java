package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.FavouriteService;
import com.Assignment.shopping_carts.Model.Favourites;
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

@RestController
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouriteService favService;

    public FavouritesController(FavouriteService favService) {
        this.favService = favService;
    }

    @PostMapping("/toggle")
    public Boolean toggleFavourite(@RequestParam int customerId,
                                   @RequestParam int productId) {
        return favService.toggleFavourite(customerId, productId);
    }

    //Get all favourite items for a customer
    @GetMapping("/customer/{customerId}")
    public List<Favourites> getFavourites(@PathVariable("customerId")
                                              int customerId) {
        return favService.findByCustomerId(customerId);
    }



}
