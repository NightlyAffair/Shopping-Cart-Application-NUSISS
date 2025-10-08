package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.Model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);
    boolean hasReview(int productId, int customerId, int orderId);
    List<Review> getReviewsForProduct(int productId);
    Double getAverageRatingForProduct(int productId);
}
