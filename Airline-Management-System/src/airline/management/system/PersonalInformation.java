package airline.management.system;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInformation extends JFrame {
    public static void main(String[] args){
        new PersonalInformation(1);
    }

    public PersonalInformation(int idUser){
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Thông tin cá nhân");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        int y = 0 , x1 = 60, x2 = 200;
        new Text_Label().Show(x1, y+=40, "Name", this);
        JTextField textName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "User Name", this);
        JTextField textUserName = new Text_Function().JInput(x2, y, this);
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
        ResultSet resultSet = null;
        try {
            resultSet = c.getStatement().executeQuery("select * from informationUser " +
                    "where id = " + idUser);
            if(resultSet.next()){
                textName.setText(resultSet.getString(2));
                textName.setEditable(false);
                textAddress.setText(resultSet.getString(3));
                textAddress.setEditable(false);
                textEmail.setText(resultSet.getString(4));
                textEmail.setEditable(false);
                textPhonenumber.setText(resultSet.getString(5));
                textPhonenumber.setEditable(false);
                gender.setSelectedItem(resultSet.getString(6));
                gender.setEnabled(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            resultSet = c.getStatement().executeQuery("select * from account where id = "+idUser);
            if(resultSet.next()){
                textUserName.setText(resultSet.getString(2));
                textUserName.setEditable(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btExit ex = new btExit(x1, y+50);
        add(ex);
        ex.action(this);

        JButton btEdit = new JButton("Edit");
        btEdit.setBounds(x2, y+50, 150, 30);
        btEdit.setForeground(Color.BLACK);
        add(btEdit);

        int finalY = y;
        btEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                btEdit.setVisible(false);
                JButton btsave = new JButton("Save");
                btsave.setBounds(x2, finalY +50, 150, 30);
                btsave.setForeground(Color.BLACK);
                add(btsave);

                textName.setEditable(true);
                textAddress.setEditable(true);
                textEmail.setEditable(true);
                textPhonenumber.setEditable(true);
                gender.setEnabled(true);

                btsave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel panel = (JPanel) getContentPane();
                        int n = JOptionPane.showConfirmDialog(panel, "Xác nhận cập nhật thông tin",
                                "Confirm", JOptionPane.YES_NO_OPTION);
                        if(n==JOptionPane.YES_NO_OPTION) {
                            dispose();
                            try {
                                c.getStatement().executeUpdate("update informationUser set"
                                        + " Name = '"+textName.getText()+"', Address = '"+ textAddress.getText()
                                        +"', Email = '"+ textEmail.getText() +"', Phonenumber = '"+ textPhonenumber.getText()
                                        +"', Gender = '"+gender.getSelectedItem().toString()+"' where id = " + idUser );
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
//                            System.out.println("Thay đổi thông tin thành công!");
                            setVisible(false);
                            dispose();
                        }
                }});
            }
        });

        setSize(400,375);
        setVisible(true);
        setLocation(520,x2);
        setResizable(false);
    }

}