package server;

import dao.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aspects {
    private Method method;
    private Object[] args;
    private Object result;
    static AccountDAOImpl dao = new AccountDAOImpl();
    public void setMethod(Method method) {
        this.method = method;
    }
    public void setArgs(Object[] args) {
        this.args = args;
    }
    public void setResult(Object result) {
        this.result = result;
    }

    public void before() {
        System.out.println(" default method before "+method);
    }
    public int check() throws DAOException {
        System.out.println(" check before "+method);
        // public int login(String name, long password)
        String name = (String) args[0];
        Account a = dao.findByName(name);
        if (a.getWrongCount() >= 3)
            return -a.getWrongCount();
        return 1;
    }
    public Object count() throws DAOException {
        System.out.println(" count after "+method);
        // public int login(String name, long password)
        String name = (String) args[0];
        if ((int)result > 0)
            dao.clearWrongCountByName(name);
        else
            dao.addWrongCountByName(name);
        return result;
    }
    public Object log() throws DAOException {
        System.out.println(" log after "+method);
        // public long withdraw(String name, long cash) throws DAOException
        if ((long) result < 0)
            return result;
        String name = (String) args[0];
        long taken = (long) args[1];
        long after = (long) result;
        WithdrawLog log = new WithdrawLog();
        log.setName(name);
        log.setBefore(after+taken);
        log.setAfter(after);
        log.setTime(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
        new WithdrawLogDAOImpl().insert(log);
        System.out.println(log.toString());
        return result;
    }
}
