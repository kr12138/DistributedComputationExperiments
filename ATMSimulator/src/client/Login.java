package client;

import dao.DAOException;
import rpc.DynamicProxyFactory;
import rpc.RPCService;

import javax.swing.*;

public class Login {
    private final static String host = "localhost";
    private final static int port = 8000;

    private JLabel screen;
    private JPanel panel1;
    private JButton 返回Button;
    private JButton 查询Button;
    private JTextField number;
    private JButton 取款Button;
    private JTextField card;
    private JButton 退卡Button;
    private JButton 存款Button;
    private JButton 登录Button;
    private JTextField cash;
    private JLabel 现金Label;
    @Deprecated
    public Login() {
        返回Button.setEnabled(false);
        查询Button.setEnabled(false);
        取款Button.setEnabled(false);
        退卡Button.setEnabled(false);
        存款Button.setEnabled(false);
        现金Label.setEnabled(false);
        cash.setEnabled(false);

        card.setText("8848");
        number.setText("123");

//        登录Button.addActionListener(E -> {
//            RPCService service = DynamicProxyFactory.getProxy(RPCService.class, host, port);
////            service.hello(card.getText()+" 用户想要登录");
//            String name = card.getText();
//            long password = Long.parseLong(number.getText());
//            try {
//                int t = service.login(name, password);
////                if (t > 0)
////                    new ATM(new JFrame());
////                    ATM.main(new JFrame());
//            } catch (DAOException e) {
//                e.printStackTrace();
//            }
//        });
        退卡Button.addActionListener(E -> {
            JFrame frame = (JFrame) panel1.getParent().getParent().getParent();
            frame.dispose();
        });
    }
    @Deprecated
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
