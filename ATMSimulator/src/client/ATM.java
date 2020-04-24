package client;

import dao.DAOException;
import rpc.DynamicProxyFactory;
import rpc.RPCService;

import javax.swing.*;

public class ATM {
    private JPanel panel1;
    private JLabel screen;
    private JButton 查询Button;
    private JButton 存款Button;
    private JButton 取款Button;
    private JButton 返回Button;
    private JButton 退卡Button;
    private JButton 登录Button;
    private JLabel 输入数字Label;
    private JTextField number;
    private JLabel 插卡Label;
    private JTextField card;
    private JLabel 现金Label;
    private JTextField cash;

    @Deprecated
    private JFrame getFrame() {
        return (JFrame) panel1.getParent().getParent().getParent();
    }

    public ATM() throws Exception {
        new ATM(new JFrame());
    }
    public ATM(JFrame frame) throws Exception {
        RPCService service = DynamicProxyFactory.getService();
        wait(frame);

        返回Button.addActionListener(E -> {
            String hint = screen.getText();
            if (hint != null && hint.substring(0, 4).equals("登录失败"))
                login(frame);
            else
                main(frame);
        });
        退卡Button.addActionListener(E -> login(frame));
        登录Button.addActionListener(E -> {
            String name = card.getText();
            long password = Long.parseLong(number.getText());
            wait(frame);
            try {
                int t = service.login(name, password);
                if (t > 0)
                    main(frame);
                else if (t > -3)
                    hint(frame, "登录失败，还有"+(2+t)+"次机会");
                else
                    hint(frame, "登录失败，账号已被冻结");
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
        查询Button.addActionListener(E -> {
            String name = card.getText();
            wait(frame);
            try {
                long t = service.query(name);
                if (t >= 0)
                    hint(frame, "查询成功，当前账户余额为："+t);
                else
                    hint(frame, "查询失败"+t);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
        存款Button.addActionListener(E -> {
            if (cash.isEnabled()) {
                long l = Long.parseLong(cash.getText());
                String name = card.getText();
                try {
                    long t = service.save(name, l);
                    if (t >= 0)
                        hint(frame, "存款成功，当前账户余额为："+t);
                    else
                        hint(frame, "存款失败");
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            } else
                save(frame);
        });
        取款Button.addActionListener(E -> {
            if (number.isEnabled()) {
                long l = Long.parseLong(number.getText());
                String name = card.getText();
                try {
                    long t = service.withdraw(name, l);
                    if (t >= 0)
                        hint(frame, "取款成功，当前账户余额为："+t);
                    else
                        hint(frame, "取款失败");
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            } else
                withdraw(frame);
        });

        login(frame);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void wait(JFrame frame) {
        frame.setTitle("Waiting...");
        screen.setText("");
//        screen.setEnabled(false);
        返回Button.setEnabled(false);
        查询Button.setEnabled(false);
        number.setText("");
        number.setEnabled(false);
        插卡Label.setEnabled(false);
        取款Button.setEnabled(false);
        card.setEnabled(false);
        退卡Button.setEnabled(false);
        存款Button.setEnabled(false);
        登录Button.setEnabled(false);
        cash.setEnabled(false);
        cash.setText("");
        现金Label.setEnabled(false);
        输入数字Label.setEnabled(false);
        输入数字Label.setText("输入数字");
    }
    public void login(JFrame frame) {
        wait(frame);
        frame.setTitle("Login");
        screen.setText("欢迎，请插卡，输入密码，再点击登录");
        登录Button.setEnabled(true);
        插卡Label.setEnabled(true);
        card.setEnabled(true);
        card.setText("8848");
        输入数字Label.setText("输入密码");
        输入数字Label.setEnabled(true);
        number.setEnabled(true);
        number.setText("123");
    }
    public void main(JFrame frame) {
        wait(frame);
        frame.setTitle("ATM");
        screen.setText("欢迎，请选择需要的服务");
        查询Button.setEnabled(true);
        存款Button.setEnabled(true);
        取款Button.setEnabled(true);
        退卡Button.setEnabled(true);
    }
    public void hint(JFrame frame, String hint) {
        wait(frame);
        frame.setTitle("hint");
        screen.setText(hint);
        返回Button.setEnabled(true);
    }
    public void save(JFrame frame) {
        wait(frame);
        frame.setTitle("save");
        screen.setText("请塞入现金，再点击存款");
        存款Button.setEnabled(true);
        现金Label.setEnabled(true);
        cash.setEnabled(true);
        返回Button.setEnabled(true);
    }
    public void withdraw(JFrame frame) {
        wait(frame);
        frame.setTitle("withdraw");
        screen.setText("请输入金额，再点击取款");
        取款Button.setEnabled(true);
        输入数字Label.setEnabled(true);
        输入数字Label.setText("输入金额");
        number.setEnabled(true);
        返回Button.setEnabled(true);
    }

}
