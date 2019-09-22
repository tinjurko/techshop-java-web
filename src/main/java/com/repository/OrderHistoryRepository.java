package com.repository;

import com.shop.dbo.OrderHistory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderHistoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void saveOrder(OrderHistory orderHistory) {
        entityManager.persist(orderHistory);
    }

    public List<OrderHistory> queryAllByUser(String username) {
        return entityManager.createQuery("SELECT new com.shop.dbo.OrderHistory(e.products, e.price, e.paymentMethod, e.orderTime) FROM OrderHistory e WHERE e.user.id = :username", OrderHistory.class).setParameter("username", username).getResultList();
    }

    public List<OrderHistory> queryAll() {
        return entityManager.createQuery("SELECT new com.shop.dbo.OrderHistory(e.user, e.products, e.price, e.paymentMethod, e.orderTime) FROM OrderHistory e", OrderHistory.class).getResultList();
    }
}
