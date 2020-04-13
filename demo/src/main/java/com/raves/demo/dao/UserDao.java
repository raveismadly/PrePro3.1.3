package com.raves.demo.dao;


import com.raves.demo.model.Role;
import com.raves.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserById(int id);

    User getUserByName(String name);

    List<Role> allUsersRole();

    Role getRoleById(int id);
}
