package com.booking.demo.rpc;

public interface RPCService {
    default String heartbeat(String s) {
        return (" Response to " + s + ": I' m alive!");
    }
//    int login(String name, long password); // 登录
//    long query(String name); // 查余额
//    long save(String name, long cash); // 存钱
//    long withdraw(String name, long cash); // 取钱
}
