package com.raves.demo.service;

import com.raves.demo.dao.UserDao;
import com.raves.demo.model.Role;
import com.raves.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;

    @Autowired
    public void getUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public List<Role> allUsersRole() {
        return userDao.allUsersRole();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public Role getRoleById(int id) {
        return userDao.getRoleById(id);
    }

    @Override
    public void setRole(User user, Long id) {
//        System.out.println(role.getRole());
        if (id == 1) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        }
        if (id == 2) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        }
    }
}
