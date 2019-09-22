package com.repository;

import com.shop.dbo.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public User find(String username) {
        return entityManager.find(User.class, username);
    }

    public void createUser(User user) { entityManager.persist(user); }
}
