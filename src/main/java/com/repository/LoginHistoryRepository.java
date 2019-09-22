package com.repository;

import com.shop.dbo.LoginHistory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LoginHistoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void saveLogin(LoginHistory loginHistory) {
        entityManager.persist(loginHistory);
    }

    public List<LoginHistory> findAll() {
        return entityManager.createQuery("SELECT e FROM LoginHistory e", LoginHistory.class).getResultList();
    }
}
