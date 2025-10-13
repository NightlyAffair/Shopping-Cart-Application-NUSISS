/**
 * ProductController Class
 * Authors: Glenn Min, Sheng Qi, Nithvin
 * Date: 2025-10-02
 * Last Modified by: YH
 * New Updates: fav button
 * Last Modified: 2025-10-11
 */

package com.Assignment.shopping_carts.Controller;

import com.Assignment.shopping_carts.Service.ProductServiceImpl;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Service.CategoryServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    // Displays the main product page aka HOME page
    @GetMapping
    public String viewAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "0") Integer categoryId,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "nameAsc") String sort,
            Model model
    ) {
        // Determine sort order from UI parameter
        Sort sortOrder = productService.sortEnum(sort);

        // Create pageable request (10 products per page)
        Pageable pageable = PageRequest.of(pageNumber, 10, sortOrder);

        // Fetch paginated, filtered, and sorted products
        Page<Product> page = productService.getProductsPaginated(categoryId, keyword, pageable);

        // Fetch all categories for dropdown
        List<Category> categories = categoryService.getAllCategories();

        // Pass data to the Thymeleaf model
        model.addAttribute("products", page.getContent());
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("currentKeyword", keyword);
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "displayProducts";
    }

    // Pagination
    @GetMapping("/page")
    public String paginateProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "0") Integer categoryId,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "nameAsc") String sort,
            Model model
    ) {
        return viewAllProducts(pageNumber, categoryId, keyword, sort, model);
    }

    // View Product Details
    @GetMapping("/details/{id}")
    public String viewProductDetails(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        return "detailsProducts";
    }

    // Add to Cart
    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("productId") int productId, HttpSession session) {
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        if (cart == null) cart = new java.util.ArrayList<>();
        if (!cart.contains(productId)) {
            cart.add(productId);
        }
        session.setAttribute("cart", cart);
        return "redirect:/products";
    }

    // Go to Cart
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<Integer> cart = (List<Integer>) session.getAttribute("cart");
        List<Product> cartProducts = (cart != null && !cart.isEmpty())
                ? productService.getAllProducts().stream()
                .filter(p -> cart.contains(p.getProductId()))
                .toList()
                : List.of();
        model.addAttribute("cartProducts", cartProducts);
        return "shoppingCart";
    }

   // Add or Remove to Favourites
    @PostMapping("/favorite/{id}")
    @ResponseBody
    public String toggleFavorite(@PathVariable int id, HttpSession session) {
        List<Integer> favorites = (List<Integer>) session.getAttribute("favorites");
        if (favorites == null) favorites = new java.util.ArrayList<>();

        if (favorites.contains(id)) {
            favorites.remove(Integer.valueOf(id));
            session.setAttribute("favorites", favorites);
            return "removed";
        } else {
            favorites.add(id);
            session.setAttribute("favorites", favorites);
            return "added";
        }
    }

    // View Favourites
    @GetMapping("/favorites")
    public String viewFavorites(Model model, HttpSession session) {
        List<Integer> favorites = (List<Integer>) session.getAttribute("favorites");
        List<Product> favoriteProducts = (favorites != null && !favorites.isEmpty())
                ? productService.getAllProducts().stream()
                .filter(p -> favorites.contains(p.getProductId()))
                .toList()
                : List.of();
        model.addAttribute("favorites", favoriteProducts);
        return "favorites";
    }

    // View Purchase History
    @GetMapping("/history")
    public String viewPurchaseHistory(Model model, HttpSession session) {
        return "orderHistory";
    }
}
