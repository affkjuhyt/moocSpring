package com.thienlinh.vegetable.service.impl;

import com.thienlinh.vegetable.domain.CategoryRepository;
import com.thienlinh.vegetable.model.Category;
import com.thienlinh.vegetable.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }
}
