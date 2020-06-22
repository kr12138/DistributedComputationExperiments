package com.booking.demo.server;

import com.booking.demo.rpc.RPCService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private String beforeMethod = "";
    private String afterMethod = "";

    private RPCService object;
    private ArrayNode list;

    public ProxyHandler(RPCService object){
        this.object = object;
    }

    private void getAspect(String method) {
        try {
            list = (ArrayNode) new ObjectMapper().readTree(new File("./aops.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        beforeMethod = "";
        afterMethod = "";
        for (int i=0; i<list.size(); ++i) {
            JsonNode node = list.get(i);
            if (node.get("method").asText().equals(method))
                if (node.get("position").asText().equals("before"))
                    beforeMethod = node.get("aspect").asText();
                else
                    afterMethod = node.get("aspect").asText();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        getAspect(method.getName());

        Class<Aspects> cls = Aspects.class;
        // 处理before方法
        if (beforeMethod != null && beforeMethod.length() > 0) {
            Method m = cls.getMethod(beforeMethod);
            Aspects o = cls.newInstance();
            o.setMethod(m);
            o.setArgs(args);
            if ((int) m.invoke(o) < 0)
                return m.invoke(o);
        }

        // 处理目标方法
        Object result = method.invoke(object, args);

        // 处理after方法
        if (afterMethod != null && afterMethod.length() > 0) {
            Method m = cls.getMethod(afterMethod);
            Aspects o = cls.newInstance();
            o.setMethod(m);
            o.setArgs(args);
            o.setResult(result);
            result = m.invoke(o);
        }

        return result;
    }
}
