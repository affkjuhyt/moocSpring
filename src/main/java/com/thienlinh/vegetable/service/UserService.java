package com.thienlinh.vegetable.service;

import com.thienlinh.vegetable.model.User;

public interface UserService {
    void addUser(User user);
    User findByEmail(String email);
    void deleteUser(User user);
    User updateUser(User user);
}
