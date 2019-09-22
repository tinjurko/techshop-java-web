package com.controller;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class StatelessSessionContext {

    @Resource
    private SessionContext sessionContext;

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public String getCurrentUsername() {
        return sessionContext.getCallerPrincipal().getName();
    }
}
