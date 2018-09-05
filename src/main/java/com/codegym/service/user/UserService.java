package com.codegym.service.user;

import com.codegym.model.User;

public interface UserService {

    User findUserByName(String name);

    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
