package Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton logBtn, regBtn;
    JPasswordField p1;
    LoginForm() {
        JFrame frame = new JFrame("Login Form");
        l1 = new JLabel("Login Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        logBtn = new JButton("Login");
        regBtn = new JButton("Register");

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(60, 70, 200, 30);
        l3.setBounds(60, 110, 200, 30);
        tf1.setBounds(150, 70, 200, 30);
        p1.setBounds(150, 110, 200, 30);
        logBtn.setBounds(150, 160, 100, 30);
        regBtn.setBounds(150, 200, 150, 30);

        frame.add(l1);
        frame.add(l2);
        frame.add(tf1);
        frame.add(l3);
        frame.add(p1);
        frame.add(logBtn);
        frame.add(regBtn);

        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String uname = tf1.getText();
                String pass = p1.getText();
                if(uname.equals("Usuario") && pass.equals("123"))
                {
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Incorrect login or password",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegisterForm reg = new RegisterForm();


            }
        });
    }
    public void actionPerformed(ActionEvent ae)
    {

    }


    public static void main(String[] args) {
        new LoginForm();
    }


}
