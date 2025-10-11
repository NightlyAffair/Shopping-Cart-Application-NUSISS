package com.Assignment.shopping_carts.Service;

import com.Assignment.shopping_carts.InterfaceMethods.CategoryService;
import com.Assignment.shopping_carts.Model.Category;
import com.Assignment.shopping_carts.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
