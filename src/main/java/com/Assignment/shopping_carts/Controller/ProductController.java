package com.Assignment.shopping_carts.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Assignment.shopping_carts.InterfaceMethods.CategoryService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Model.Product;
import com.Assignment.shopping_carts.Model.Review;
import com.Assignment.shopping_carts.Service.ProductServiceImpl;
import com.Assignment.shopping_carts.Service.ReviewServiceImpl;

@Controller
@RequestMapping("/displayProducts")
public class ProductController {

    private final ProductServiceImpl productService;
    private final CategoryService categoryService;
    private final ReviewServiceImpl reviewService;

    @Autowired
    public ProductController(ProductServiceImpl productService,
                             CategoryService categoryService,
                             ReviewServiceImpl reviewService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String showProducts() {
        return "redirect:/displayProducts/page"; // Fixed redirect path
    }

    @GetMapping("/page")
    public String getProductsPaginated(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {

        if (categoryId == null) {
            categoryId = 0;
        }

        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;
        } else if (keyword != null) {
            keyword = keyword.trim();
        }

        Page<Product> productPage = productService.getPage(pageNumber, pageSize, categoryId, keyword, sort);

        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageIndex", pageNumber + 1);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());

        // Preserve current filter state for form
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("currentKeyword", keyword);
        model.addAttribute("currentSort", sort);

        return "displayProducts";
    }

    @GetMapping("/details/{productId}")
    public String getProductDetails(@PathVariable int productId, Model model) {
        // Get product details
        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isEmpty()) {
            return "redirect:/displayProducts/page"; // Fixed redirect path
        }

        Product product = productOptional.get();

        // Get reviews for this product
        List<Review> reviews = reviewService.getReviewsForProduct(productId);

        // Get average rating (or use the stored averageRating from product)
        Double averageRating = product.getAverageRating();

        model.addAttribute("product", product); // Changed from Optional to Product
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", averageRating != null ? averageRating : 0.0);

        return "detailsProducts";
    }
}