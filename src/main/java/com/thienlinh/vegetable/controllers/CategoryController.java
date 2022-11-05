package com.thienlinh.vegetable.controllers;

import com.thienlinh.vegetable.model.Category;
import com.thienlinh.vegetable.model.User;
import com.thienlinh.vegetable.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SuppressWarnings({"java:S1905"})
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/list")
    public String getList(Model model, HttpSession session){
        List<Category> categoryList = categoryService.getCategories();

        model.addAttribute("fruits", categoryList.get(0));
        model.addAttribute("vegetables", categoryList.get(1));
        model.addAttribute("logged", (User) session.getAttribute("logged"));
        model.addAttribute("user", (User) session.getAttribute("user"));

        return "category";
    }
}
