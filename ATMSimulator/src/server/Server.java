package server;

import rpc.RemoteCaller;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.*;
import java.util.*;

// 服务端类
public class Server {
    private final static String CLASS_PATH = "rpc.";
    private final static int port = 8000;
    // 存放远程对象的缓存
    private Map<String, Object> remoteObjects = new HashMap<>() ;
    // 注册服务：把一个远程对象放到缓存中
    public void register(String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
    }

    // 暴露服务，创建基于流的Socket,并在8000 端口监听
    public void exportService() throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println(" 服务器启动......");

        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        // 接收客户发送的Call 对象
        RemoteCaller remotecallobj = (RemoteCaller) ois.readObject();

        System.out.println(remotecallobj);
        // 调用相关对象的方法
        System.out.println("calling......");
        remotecallobj = invoke(remotecallobj);
        // 向客户发送包含了执行结果的remotecallobj 对象
        oos.writeObject(remotecallobj);
        ois.close();
        oos.close();
        socket.close();
        serverSocket.close();
    }

    public RemoteCaller invoke(RemoteCaller call) {
        Object result = null;
        try {
            String className = call.getClassName();
            Class<?> classType = Class.forName(className);
            String methodName = call.getMethodName();
            Class<?>[] paramTypes = call.getParamTypes();
            Object[] params = call.getParams();
            Method method = classType.getMethod(methodName, paramTypes);
            // 从hashmap缓存中取出相关的远程对象Object
            Object remoteObject = remoteObjects.get(className);
            if (remoteObject == null) {
                throw new Exception(className+" 的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
                System.out.println("远程调用结束:remotObject:"+remoteObject.toString()+",params:"+Arrays.toString(params));
            }
        } catch (Exception e) {
            System.out.println("错误："+e.getMessage());
        }
        call.setResult(result);

        return call;
    }
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        // 把事先创建的RemoteServceImpl 对象加人到服务器的缓存中
        // 在服务注册中心注册服务
        for(;;) {
        server.register(CLASS_PATH+"RPCService", new RPCServiceImpl());
        server.exportService(); // 打开网络端口，接受外部请求，执行服务功能，返回结果
        }
    }

}