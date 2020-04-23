package client;

import rpc.DynamicProxyFactory;

import javax.swing.*;

// 客户端类
public class Client {

    public static void main(String[] args) throws Exception {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            ObjectNode n0 = mapper.createObjectNode();
//            n0.put("host", "localhost");
//            n0.put("port", 8000);
//            ObjectNode n1 = mapper.createObjectNode();
//            n1.put("host", "localhost");
//            n1.put("port", 8848);
//            System.out.println(n0.toString());
//            ArrayNode arrayNode = mapper.createArrayNode();
//            arrayNode.add (n0);
//            arrayNode.add (n1);
//            System.out.println (mapper.writeValueAsString (arrayNode));
//            FileOutputStream fos = new FileOutputStream("./url.cfg");
//            fos.write(arrayNode.toString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        DynamicProxyFactory.init();
        new ATM(new JFrame());

    }

}
