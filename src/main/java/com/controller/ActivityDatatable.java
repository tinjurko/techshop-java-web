package com.controller;

import com.repository.LoginHistoryRepository;
import com.shop.dbo.LoginHistory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ActivityDatatable implements Serializable {

    @Inject
    private LoginHistoryRepository loginHistoryRepository;

    public List<LoginHistory> getLoginActivity() {
        return loginHistoryRepository.findAll();
    }
}
