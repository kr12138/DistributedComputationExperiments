package client;

import rpc.RPCService;

import javax.swing.*;

public class ATM {
    private final static String host = "localhost";
    private final static int port = 8000;

    private JTextArea screen;
    private JPanel panel1;
    private JButton 返回Button;
    private JButton 查询Button;
    private JTextField password;
    private JLabel 插卡Button;
    private JButton 取款Button;
    private JTextField textField2;
    private JButton 退卡Button;
    private JButton 存款Button;
    private JButton 登录Button;

    public ATM() {
        返回Button.addActionListener(e -> {
            RPCService service = DynamicProxyFactory.getProxy(RPCService.class, host, port);
            String result = service.request("你好！");
            System.out.println("动态代理+网络封装方式的远程执行结果为："+result);
            screen.setText(screen.getText()+"\n"+result);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM");
        frame.setContentPane(new ATM().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void build(JFrame frame) {
        frame.setTitle("ATM");
        frame.setContentPane(new ATM().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//    public static void build() {
//
//    }
}
