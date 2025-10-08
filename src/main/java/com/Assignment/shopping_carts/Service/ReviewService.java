package com.Assignment.shopping_carts.InterfaceMethods;

import com.Assignment.shopping_carts.Model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);
    boolean hasReview(int productId, int customerId, int orderId);
    List<Review> getReviewsForProduct(int productId);
    Double getAverageRatingForProduct(int productId);
}
