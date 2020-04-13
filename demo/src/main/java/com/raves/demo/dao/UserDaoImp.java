package com.raves.demo.dao;


import com.raves.demo.model.Role;
import com.raves.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    //
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager.createQuery("select user from User user left join fetch user.roles where user.username=:name")
                .setParameter("name", name).getSingleResult();
        return user;
    }


    @Override
    public List<Role> allUsersRole() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}
