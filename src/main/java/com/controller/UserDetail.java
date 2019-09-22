package com.controller;

import com.repository.UserRepository;
import com.shop.dbo.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class UserDetail implements Serializable {

    @Inject
    UserRepository userRepository;

    @Inject
    StatelessSessionContext statelessSessionContext;

    public Boolean getUserAdmin() {
        User user = userRepository.find(statelessSessionContext.getCurrentUsername());
        if(user == null) {
            return false;
        } else {
            return user.getRole().equals("admin");
        }
    }
}
