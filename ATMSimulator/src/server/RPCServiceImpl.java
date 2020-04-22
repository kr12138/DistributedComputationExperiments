package server;

import rpc.RPCService;

// 远程服务
public class RPCServiceImpl implements RPCService {

    @Override
    public String request(String s) {
        return ("本服务收到发来的信息："+s);
    }

}