package com.Assignment.shopping_carts.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Assignment.shopping_carts.Service.ReviewServiceImpl;
import com.Assignment.shopping_carts.Model.Review;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewService;

    // Receive customer review, only if not already reviewed for this product/order/customer
    @PostMapping("/add/{productId}/{customerId}/{orderId}")
    public ResponseEntity<String> addReview(@PathVariable int productId, @PathVariable int customerId, @PathVariable int orderId, @RequestBody Review review) {
        if (reviewService.hasReview(productId, customerId, orderId)) {
            return new ResponseEntity<>("Review already exists for this product/order.", HttpStatus.BAD_REQUEST);
        }
        try {
             // Set the composite key fields from path variables (REQUIRED)
           review.setProductId(productId);
           review.setCustomerId(customerId);
           review.setOrderId(orderId);
           
            System.out.println("About to save review with IDs set");
            reviewService.saveReview(review);
            System.out.println("Review saved successfully");
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all reviews for a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable int productId) {
        List<Review> reviews = reviewService.getReviewsForProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Get average rating for a product
    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<Double> getAverageRatingForProduct(@PathVariable int productId) {
        Double avg = reviewService.getAverageRatingForProduct(productId);
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }
}
