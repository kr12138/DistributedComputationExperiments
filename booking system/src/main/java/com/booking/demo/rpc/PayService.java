package com.booking.demo.rpc;

import com.booking.demo.entity.User;

public interface PayService extends RPCService{
    boolean login(long id, String password); // 登录
    User query(long id); // 查信息
    MyResponse save(long id, long cash); // 存钱，返回新余额
    MyResponse withdraw(long id, long cash); // 取钱，返回新余额
}
