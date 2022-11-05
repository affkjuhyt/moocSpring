package com.thienlinh.vegetable.domain.impl;

import com.thienlinh.vegetable.domain.CategoryRepository;
import com.thienlinh.vegetable.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Categories").getResultList();
        } catch (Exception e) {
            session.close();
            return null;
        }
    }
}
