package client;

import rpc.RPCService;

import javax.swing.*;

public class Login {
    private final static String host = "localhost";
    private final static int port = 8000;

    private JLabel screen;
    private JPanel panel1;
    private JButton 返回Button;
    private JButton 查询Button;
    private JTextField password;
    private JLabel 插卡Button;
    private JButton 取款Button;
    private JTextField card;
    private JButton 退卡Button;
    private JButton 存款Button;
    private JButton 登录Button;

    public Login() {
        返回Button.setEnabled(false);
        查询Button.setEnabled(false);
        插卡Button.setEnabled(false);
        取款Button.setEnabled(false);
        退卡Button.setEnabled(false);
        存款Button.setEnabled(false);
//        登录Button.setEnabled(false);

        登录Button.addActionListener(e -> {
            RPCService service = DynamicProxyFactory.getProxy(RPCService.class, host, port);
            ATM.build(new JFrame());
            String result = service.request("你好！");
            System.out.println("动态代理+网络封装方式的远程执行结果为："+result);
            screen.setText(result);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void build(JFrame frame) {
        frame.setTitle("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//    public static void build() {
//
//    }
}
