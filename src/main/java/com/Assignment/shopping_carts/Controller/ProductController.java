package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ProductController Class
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by :
 * Last Modified:
 */


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewProducts(@RequestParam Integer categoryId,
                               @RequestParam String keyword,
                               @RequestParam String sort,
                               Model model) {
        List<Product> products;
        if ("priceAsc".equalsIgnoreCase(sort)) {
            products = productService.findAllByOrderByUnitPriceAsc();
        } else if ("priceDesc".equalsIgnoreCase(sort)) {
            products = productService.findAllByOrderByUnitPriceDesc();
        } else if ("rating".equalsIgnoreCase(sort)) {
            products = productService.findByRatingDesc();
        } else if (categoryId != null || (keyword != null && !keyword.isEmpty())) {
            products = productService.findByCategoryAndKeyword(categoryId, keyword);
        } else {
            products = productService.findAllBy(null);
        }

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sort);

        return "product/list";
    }
}
