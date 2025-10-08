package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.InterfaceMethods.CategoryService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductController Class
 * Author: Glenn Min
 * Date: 2025-10-06 12:00
 * Modifier by : Sheng Qi
 * Last Modified: 2025-10-07 10:30
 */


@Controller
@RequestMapping("/products")
public class ProductController {


    private ProductServiceImpl productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductServiceImpl productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/list")
    public String getProductsByCategory(@RequestParam(value = "categoryId", defaultValue = "0", required = false) Integer categoryId,
                                        @RequestParam(value = "keyword", required = false) String keyword,
                                        @RequestParam(value = "sort", defaultValue = "nameAsc", required = false) String sort, Model model) {

        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("keyword", keyword);

        List<Product> products = new ArrayList<>();
        if (categoryId == null) {
            categoryId = 0;
        }
        if(keyword == null || keyword.trim().isEmpty()) {
            keyword = null;
        }

        if ("rating".equals(sort)) {
            products = productService.findByCategoryAndKeywordOrderByRating(categoryId, keyword);
        } else {
//            products = productService.findProductsByCategorySort(categoryId, keyword,sort);
            System.out.println("categoryId=" + categoryId + ", keyword=" + keyword);
            products = productService.findProductsByCategorySort(categoryId, keyword, sort);
            System.out.println("Found products: " + products.size());
        }

        model.addAttribute("products", products);


        return "products/list";
    }
}
