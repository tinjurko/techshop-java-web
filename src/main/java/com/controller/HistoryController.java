package com.controller;

import com.repository.OrderHistoryRepository;
import com.shop.dbo.OrderHistory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class HistoryController implements Serializable {

    @Inject
    OrderHistoryRepository orderHistoryRepository;

    @Inject
    StatelessSessionContext statelessSessionContext;

    public List<OrderHistory> getHistoryOrders() {
        return orderHistoryRepository.queryAllByUser(statelessSessionContext.getCurrentUsername());
    }

    public List<OrderHistory> getHistoryOrdersByEveryone() {
        return orderHistoryRepository.queryAll();
    }
}
