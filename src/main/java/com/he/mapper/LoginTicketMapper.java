package com.he.mapper;

import com.he.entity.LoginTicket;
import org.apache.ibatis.annotations.Param;

public interface LoginTicketMapper {

    public void addTicket(LoginTicket ticket);

    LoginTicket selectByTicket(String ticket);

    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

}
