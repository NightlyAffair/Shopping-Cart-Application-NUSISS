package com.Assignment.shopping_carts.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Assignment.shopping_carts.Service.ReviewService;
import com.Assignment.shopping_carts.Model.Review;

@RestController
@RequestMapping("/PurchaseHistory/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Receive customer review
    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        try{
            Review savedReview = reviewService.saveReview(review);
            return new ResponseEntity<>("success", HttpStatus.OK);
    } catch (Exception e){
       System.out.println(e.getMessage());
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
