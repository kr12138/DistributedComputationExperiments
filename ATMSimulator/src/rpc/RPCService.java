package rpc;

import dao.DAOException;

// 服务接口
public interface RPCService {

    String hello(String s); // 服务功能测试
    int login(String name, long password) throws DAOException; // 登录
    long query(String name) throws DAOException; // 查余额
    long save(String name, long cash) throws DAOException; // 存钱
    long withdraw(String name, long cash) throws DAOException; // 取钱
}
