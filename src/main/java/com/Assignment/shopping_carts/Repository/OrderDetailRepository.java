package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
