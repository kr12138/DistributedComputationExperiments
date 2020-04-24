package server;

import dao.Account;
import dao.AccountDAOImpl;
import dao.DAOException;
import rpc.RPCService;

// 远程服务
public class RPCServiceImpl implements RPCService {

    @Override
    public String hello(String s) {
        return ("本服务收到发来的信息："+s);
    }

    @Override
    public int login(String name, long password) throws DAOException {
        Account a = new AccountDAOImpl().findByName(name);
        return a.getPassword() == password ? 1 : -a.getWrongCount();
    }

    @Override
    public long query(String name) throws DAOException {
        Account a = new AccountDAOImpl().findByName(name);
        return a.getSavings();
    }

    @Override
    public long save(String name, long cash) throws DAOException {
        AccountDAOImpl dao = new AccountDAOImpl();
        if (dao.updateSavingsByName(name, cash))
            return dao.findByName(name).getSavings();
        else
            return -1;
    }

    @Override
    public long withdraw(String name, long cash) throws DAOException {
        AccountDAOImpl dao = new AccountDAOImpl();
        long old = dao.findByName(name).getSavings();
        if (old >= cash) {
            dao.updateSavingsByName(name, -cash);
            return old - cash;
        } else
            return -1;
    }

}