package airline.management.system;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn extends JFrame{

    public SignIn() {

    }

    public SignIn(int option, final IdUser id){

            getContentPane().setBackground(Color.WHITE);
            setTitle("Sign in");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setAlwaysOnTop(true);
            getContentPane().setLayout(null);

            new Text_Label().Show(60, 20, "User name", this);
            TextField userName = new Text_Function().Input(200, 20, false, this);
            new Text_Label().Show(60, 50, "Password", this);
            TextField password = new Text_Function().Input(200, 50, true, this);

            btExit ex = new btExit(10, 90);
            add(ex);
            ex.action(this);

            JButton bttSignIn = new JButton("Sign in");
            bttSignIn.setBounds(250, 90, 150, 30);
            bttSignIn.setForeground(Color.BLACK);
            add(bttSignIn);

            JButton bttRegister = new JButton("Register");
            bttRegister.setBounds(95, 90, 150, 30);
            bttRegister.setForeground(Color.BLACK);
            add(bttRegister);

            conn c = new conn();
            bttSignIn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)  {
                            ResultSet resultSet = null;
                            try {
                                resultSet = c.getStatement().executeQuery("select * from account " +
                                        "where UserName = '" + userName.getText() + "'");
                            } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                            }
                            try {
                                    if(resultSet.next()) {
                                        if (BCrypt.checkpw(password.getText(), resultSet.getString(3))) {
                                            id.value =  Integer.parseInt(resultSet.getString(1));
                                            setVisible(false);
                                            resultSet.close();
                                            c.close();
//                                            System.out.println("id = "+id.value);
//                                            System.out.println("Dang nhap thanh cong !");
                                            dispose();

                                            if(option==1){
                                                new PersonalInformation(id.value);
                                            }
                                            else if(option==2){
                                                new TicketInfor(id.value);

                                            }
                                        }
                                        else {
                                            JPanel panel = new JPanel();
                                            int n = JOptionPane.showConfirmDialog(panel, "Mật khẩu không đúng !",
                                                    "Thông báo", JOptionPane.DEFAULT_OPTION);
                                            add(panel);
                                        }
                                    }
                                    else {
                                        JPanel panel = new JPanel();
                                        int n = JOptionPane.showConfirmDialog(panel, "Tên đăng nhập không đúng !",
                                                "Thông báo", JOptionPane.DEFAULT_OPTION);
                                        add(panel);
                                    }
                            } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                            }
                    }
            });

        bttRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register(id);
            }
        });
            setSize(400,150);
            setVisible(true);
            setLocation(400,200);
	}

        
    public static void main(String[] args){
        IdUser id = new IdUser();
        id.value = 1;
        new SignIn(1, id);
    }   
}