package com.Assignment.shopping_carts.Service;


import com.Assignment.shopping_carts.Model.Customer;
import com.Assignment.shopping_carts.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assignment.shopping_carts.Repository.ReviewRepository;
import com.Assignment.shopping_carts.InterfaceMethods.ReviewService;
import com.Assignment.shopping_carts.Model.Review;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean hasReview(int productId, int customerId, int orderId) {
        return reviewRepository.findByProductIdAndCustomerIdAndOrderId(productId, customerId, orderId).isPresent();
    }

    @Override
    public List<Review> getReviewsForProduct(int productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Double getAverageRatingForProduct(int productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }

    @Override
    public String getCustomerNameById(int customerId) {
        Customer target = customerRepository.findById(customerId);
        return target.getUserName();
    }

}