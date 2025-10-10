package com.Assignment.shopping_carts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Assignment.shopping_carts.Model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.productId = :productId")
	Double findAverageRatingByProductId(@Param("productId") int productId);

	@Query("SELECT COUNT(r) FROM Review r WHERE r.productId = :productId")
	Long countReviewsByProductId(@Param("productId") int productId);

	@Query("SELECT r FROM Review r WHERE r.productId = :productId")
	List<Review> findByProductId(@Param("productId") int productId);

	@Query("SELECT r FROM Review r WHERE r.productId = :productId AND r.customerId = :customerId AND r.orderId = :orderId")
	Optional<Review> findByProductIdAndCustomerIdAndOrderId(@Param("productId") int productId, @Param("customerId") int customerId, @Param("orderId") int orderId);
}