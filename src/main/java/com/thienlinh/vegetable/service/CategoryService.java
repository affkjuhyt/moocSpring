package com.thienlinh.vegetable.service;

import com.thienlinh.vegetable.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
}
