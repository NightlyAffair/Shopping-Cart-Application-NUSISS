package com.Assignment.shopping_carts.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Assignment.shopping_carts.Model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}