package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assignment.shopping_carts.Repository.ReviewRepository;
import com.Assignment.shopping_carts.Model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}