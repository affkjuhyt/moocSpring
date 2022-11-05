package com.thienlinh.vegetable.domain;

import com.thienlinh.vegetable.model.Category;

import java.util.List;


public interface CategoryRepository {

    List<Category> getCategories();
}
