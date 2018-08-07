package com.he.service;

import com.he.entity.LoginTicket;

public interface LoginTicketService {

    public void addTicket(LoginTicket ticket);

    LoginTicket selectByTicket(String ticket);

    void updateStatus(String ticket, int status);
}
