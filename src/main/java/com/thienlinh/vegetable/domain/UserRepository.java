package com.thienlinh.vegetable.domain;

import com.thienlinh.vegetable.model.User;

public interface UserRepository {
    void addUser(User user);

    User findByEmail(String email);

    void deleteUser(User user);

    User updateUser(User user);
}
