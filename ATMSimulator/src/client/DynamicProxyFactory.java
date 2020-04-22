package client;

import rpc.RemoteCaller;

import java.lang.reflect.*;

// 动态代理类
public class DynamicProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<T> classType, final String host, final int port) {

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
}

