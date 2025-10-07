package com.Assignment.shopping_carts.Repository;

import com.Assignment.shopping_carts.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
