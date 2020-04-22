package client;

import rpc.RPCService;

import javax.swing.*;

// 客户端类
public class Client {
//    private final static String CLASS_PATH = "server.";
//    private final static String host = "localhost";
//    private final static int port = 8000;

//    private static JFrame frame = new JFrame();

    public static void main(String[] args) throws Exception {
        RPCService service;
        String result;

        Login.build(new JFrame());

//        service = new RPCServiceImpl();
//        result = service.request("你好！");
//        System.out.println("本地执行结果为："+result);

//        service = DynamicProxyFactory.getProxy(RPCService.class, host, port);
//        result = service.request("你好！");
//        System.out.println("动态代理+网络封装方式的远程执行结果为："+result);

    }

}
