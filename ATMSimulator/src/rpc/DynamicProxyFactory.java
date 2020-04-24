package rpc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ProxyHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;

// 代理类工厂
public class DynamicProxyFactory {
    private static String[] hosts;
    private static int[] ports;
    private static int round;
    private static int rounds;
    public static int getRounds() {
        return rounds;
    }
    public static int[] getPorts() {
        return ports;
    }

    public static void init() {
        try {
            File f = new File("./url.cfg");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode cfg = mapper.readTree(f);
            rounds = cfg.size();
            hosts = new String[rounds];
            ports = new int[rounds];
            for (int i=0; i<rounds; ++i) {
                hosts[i] = cfg.get(i).get("host").asText();
                ports[i] = cfg.get(i).get("port").asInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // for ATM()
    @SuppressWarnings("unchecked")
    private static <T> T getProxy(final Class<T> classType, final String host, final int port) {
        InvocationHandler handler = (proxy, method, args) -> { // invoke(Object, Method, Object[])
            Connector connector = null;
            try {
                connector = new Connector(host, port);
                RemoteCaller call = new RemoteCaller(classType.getName(), method.getName(), method.getParameterTypes(), args);
                connector.send(call);
                call = (RemoteCaller) connector.receive();
                return call.getResult();
            } finally {
                if (connector != null) connector.close();
            }
        };

        System.out.println("代理开始执行");
        return (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class<?>[]{classType}, handler);
    }

    // for ATM()
    private static <T> T getProxy(final Class<T> classType) {
        ++round;
        round %= rounds;
        return getProxy(classType, hosts[round], ports[round]);
    }

    // for ATM()
    public static RPCService getService() throws Exception {
        RPCService test = null;
        for (int i=0; i<rounds; ++i)
            try {
                test = getProxy(RPCService.class);
                if (test.hello("服务器你好吗") == null)
                    test = null;
                else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (test == null)
            throw new Exception();
        return test;
    }

    // for aop
    @SuppressWarnings("unchecked")
    public static <T> T getService(final Class<T> classType, Object obj) {
        return (T) Proxy.newProxyInstance(classType.getClassLoader(),
                new Class<?>[]{classType},
                new ProxyHandler((RPCService) obj));
    }
}

