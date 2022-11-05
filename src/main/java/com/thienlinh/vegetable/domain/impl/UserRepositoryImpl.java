package com.thienlinh.vegetable.domain.impl;

import com.thienlinh.vegetable.domain.UserRepository;
import com.thienlinh.vegetable.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User findByEmail(String email) {
        Session session=sessionFactory.openSession();
        User user = session.get(User.class, email);
        session.close();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User updateUser(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        User userByEmail = session.get(User.class, user.getEmail());

        session.getTransaction().commit();
        session.close();
        return userByEmail;
    }
}
