package airline.management.system;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.security.Key;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register extends JFrame{
    public static void main(String[] args){
        new Register(new IdUser());
    }

    public Register(IdUser id){
        final boolean[] flag = {true, true, true};
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Register account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        int y = 0 , x1 = 60, x2 = 200;
        new Text_Label().Show(x1, y+=40, "Name", this);
        JTextField textName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "User Name", this);
        JTextField textUserName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Password", this);
        JTextField textPassword = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Confirm Password", this);
        JTextField textPassword_ = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Address", this);
        JTextField textAddress = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Email", this);
        JTextField textEmail = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Phonenumber", this);
        JTextField textPhonenumber = new Text_Function().JInput(x2, y, this);

        new Text_Label().Show(x1, y+=40, "Gender", this);

        String sex[] = {"Male", "Female"};
        JComboBox gender = new JComboBox(sex);
        gender.setBounds(x2, y, 150, 27);
        add(gender);
        conn c = new conn();

        textUserName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyTyped(KeyEvent e) {
                String useName = textUserName.getText() + e.getKeyChar();
                try {
                    ResultSet resultSet = c.getStatement().executeQuery("select * from account" +
                            " where UserName = '" + useName + "'");
                    if(resultSet.next()) {
                        textUserName.setBackground(new Color(255, 102, 18));
                        textUserName.setToolTipText("Ten nay da ton tai");
                        flag[0] = false;
                    }
                    else {
                        textUserName.setBackground(Color.white);
                        textUserName.setToolTipText("");
                        flag[0] = true;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String useName = textUserName.getText() ;
                try {
                    ResultSet resultSet = c.getStatement().executeQuery("select * from account" +
                            " where UserName = '" + useName + "'");
                    if(resultSet.next()) {
                        textUserName.setBackground(new Color(255, 102, 18));
                        textUserName.setToolTipText("Ten nay da ton tai");
                        flag[0] = false;
                    }
                    else {
                        textUserName.setBackground(Color.white);
                        textUserName.setToolTipText("");
                        flag[0] = true;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        textPassword_.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyTyped(KeyEvent e) {
                if(!(textPassword_.getText() + e.getKeyChar()).equalsIgnoreCase(textPassword.getText())) {
                    textPassword_.setBackground(new Color(255, 102, 18));
                    flag[1] = false;
                }
                else {
                    textPassword_.setBackground(Color.white);
                    flag[1] = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!textPassword_.getText().equalsIgnoreCase(textPassword.getText())) {
                    textPassword_.setBackground(new Color(255, 102, 18));
                    flag[1] = false;
                }
                else {
                    textPassword_.setBackground(Color.white);
                    flag[1] = true;
                }
            }
        });

        textPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyTyped(KeyEvent e) {
                if(!(textPassword.getText() + e.getKeyChar()).equalsIgnoreCase(textPassword_.getText())) {
                    textPassword_.setBackground(new Color(255, 102, 18));
                    flag[1] = false;
                }
                else {
                    textPassword_.setBackground(Color.white);
                    flag[1] = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!textPassword.getText().equalsIgnoreCase(textPassword_.getText())) {
                    textPassword_.setBackground(new Color(255, 102, 18));
                    flag[1] = false;
                }
                else {
                    textPassword_.setBackground(Color.white);
                    flag[1] = true;
                }
            }
        });

        btExit ex = new btExit(x1, y+=40);
        add(ex);
        ex.action(this);

        JButton bttSave = new JButton("Register");
        bttSave.setBounds(x2, y, 150, 30);
        bttSave.setForeground(Color.BLACK);
        add(bttSave);

        bttSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                flag[2] = true;
                flag[2] = flag[2] && !textName.getText().isEmpty();
                flag[2] = flag[2] && !textUserName.getText().isEmpty();
                flag[2] = flag[2] && !textPassword.getText().isEmpty();
                flag[2] = flag[2] && !textEmail.getText().isEmpty();
                flag[2] = flag[2] && !textAddress.getText().isEmpty();
                flag[2] = flag[2] && !textPhonenumber.getText().isEmpty();
                if (flag[0] && flag[1] && flag[2]){
//                    System.out.println("Dang ki thanh cong!");
                    conn c = new conn();
                    try {
                        c.getStatement().executeUpdate("insert into account(UserName, Password) " +
                                "values ('"+ textUserName.getText() +"', '"
                                + BCrypt.hashpw(textPassword.getText(), BCrypt.gensalt(4))+"')");
                        ResultSet resultSet = c.getStatement().executeQuery("select ID from account " +
                                "where UserName = '"+ textUserName.getText() +"'");
                        if(resultSet.next()) {
                            id.value = resultSet.getInt(1);
                            c.getStatement().executeUpdate("insert into InformationUser"
                                    + " values ('"+id.value+"', '"+ textName.getText()+"', '"+ textAddress.getText()
                                    + "', '"+textEmail.getText()+"', '"+textPhonenumber.getText()+"', '"
                                    + gender.getSelectedItem().toString()+"')");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dispose();
                }
                else {
                    JPanel panel = new JPanel();
                    int n = JOptionPane.showConfirmDialog(panel, "Quý khách vui lòng điền đầy đủ và đúng các trường thông tin !",
                            "Thông báo", JOptionPane.DEFAULT_OPTION);
                    add(panel);
//                    System.out.println(flag[0] +" "+ flag[1]);
                }
            }
        });

        setSize(400,y+=80);
        setVisible(true);
        setLocation(520,x2);
        setResizable(false);
	}
        
}