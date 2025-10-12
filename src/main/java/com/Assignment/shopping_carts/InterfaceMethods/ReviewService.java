package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Review;

import java.util.List;

public interface ReviewService {
    public Review saveReview(Review review);
    public boolean hasReview(int productId, int customerId, int orderId);
    public List<Review> getReviewsForProduct(int productId);
    // public Double getAverageRatingForProduct(int productId);
}
