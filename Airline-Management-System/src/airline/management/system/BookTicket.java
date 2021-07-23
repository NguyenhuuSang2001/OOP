package airline.management.system;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookTicket extends JFrame{
    public static void main(String[] args){
        new BookTicket("1234", 3, new IdUser());
    }

    public BookTicket(String idFlight, int type, IdUser id){

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Book Ticket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setAlwaysOnTop(true);
        setAutoRequestFocus(true);

        if(type==0)
            BookTicket_0(idFlight, id);
        else if (type==1)
            BookTicket_1(idFlight, id);
        else
            BookTicket_2(idFlight, id);
            
	}

    public void BookTicket_0(String idFlight, IdUser id){

        int y = 0 , x1 = 60, x2 = 200;

        JLabel idFlightShow = new JLabel("ID FLIGHT : "+ idFlight);
        idFlightShow.setBounds(0, 0, 400, 35);
        idFlightShow.setFont(new Font("Times", Font.PLAIN, 20));
        idFlightShow.setHorizontalAlignment(SwingConstants.CENTER);
        add(idFlightShow);

        JLabel typeTicket = new JLabel("Type ticket : Adults ");
        typeTicket.setBounds(0, y+=40, 400, 35);
        typeTicket.setFont(new Font("Times", Font.PLAIN, 20));
        typeTicket.setHorizontalAlignment(SwingConstants.CENTER);
        add(typeTicket);


        new Text_Label().Show(x1, y += 40, "Full name", this);
        JTextField textName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "CMND", this);
        JTextField textCMND = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Phone numbers", this);
        JTextField textPhoneNumbers = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "City", this);
        JTextField textCity = new Text_Function().JInput(x2, y, this);

        new Text_Label().Show(x1, y+=40, "Gender", this);
        String sex[] = {"Male", "Female"};
        JComboBox gender = new JComboBox(sex);
        gender.setBounds(x2, y, 150, 27);
        add(gender);

        new Text_Label().Show(x1, y+=40, "Birthday", this);
        JTextField textBirthday = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Email", this);
        JTextField textEmail = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Luggage (Kg)", this);
        JTextField textLuggage = new Text_Function().JInput(x2, y, this);

        btExit ex = new btExit(x1, y+=80);
        add(ex);
        ex.action(this);

        JButton bttFinish = new JButton("Finish");
        bttFinish.setBounds(x2, y, 150,30);

        bttFinish.setForeground(Color.BLACK);
        add(bttFinish);

        bttFinish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if (!textName.getText().isEmpty()){
                    String id_ticket = id.value +"-1-" + java.time.LocalTime.now().toString() ;
                    String sql2 = "insert into ticketdata " +
                            "values ('" + id_ticket + "', '"+id.value + "', '" + idFlight + "', '1')";

                    String sql1 = "insert into AdultTicket values (" ;
                    sql1 += "'" + id_ticket + "'";
                    sql1 += ",'" + textName.getText() + "'";
                    sql1 += ",'" + textCMND.getText() + "'";
                    sql1 += ",'" + textPhoneNumbers.getText() + "'";
                    sql1 += ",'" + textCity.getText() + "'";
                    sql1 += ",'" + gender.getSelectedItem().toString() + "'";
                    sql1 += ",'" + textBirthday.getText() + "'";
                    sql1 += ",'" + textEmail.getText() + "'";
                    sql1 += ",'" + textLuggage.getText() + "'";
                    sql1 += ")";


                    //System.out.println(sql1);
                    //System.out.println(sql2);
                    JPanel panel = (JPanel) getContentPane();
                    int n = JOptionPane.showConfirmDialog(panel, "Xác nhận đặt vé",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if(n==JOptionPane.YES_NO_OPTION) {
                        dispose();
                        conn c = new conn();
                        try {
                            c.getStatement().executeUpdate(sql2);
                            c.getStatement().executeUpdate(sql1);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        //System.out.println("Dang ki thanh cong!");

                    }
                }
            }
        });

        setSize(400,550);
        setVisible(true);
        setLocation(520,200);

    }

    public void BookTicket_1(String idFlight, IdUser id){

        int y = 0 , x1 = 60, x2 = 200;

        JLabel idFlightShow = new JLabel("ID FLIGHT : "+ idFlight);
        idFlightShow.setBounds(0, 0, 400, 35);
        idFlightShow.setFont(new Font("Times", Font.PLAIN, 20));
        idFlightShow.setHorizontalAlignment(SwingConstants.CENTER);
        add(idFlightShow);

        JLabel typeTicket = new JLabel("Type ticket : Children");
        typeTicket.setBounds(0, y+=40, 400, 35);
        typeTicket.setFont(new Font("Times", Font.PLAIN, 20));
        typeTicket.setHorizontalAlignment(SwingConstants.CENTER);
        add(typeTicket);


        new Text_Label().Show(x1, y += 40, "Full name", this);
        JTextField textName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Gender", this);

        String sex[] = {"Male", "Female"};
        JComboBox gender = new JComboBox(sex);
        gender.setBounds(x2, y, 150, 27);
        add(gender);

        new Text_Label().Show(x1, y+=40, "Birthday", this);
        JTextField textBirthday = new Text_Function().JInput(x2, y, this);

        new Text_Label().Show(x1+100, y+=40, "Guardian", this);
        new Text_Label().Show(x1, y += 40, "Full name", this);
        JTextField textName1 = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "CMND", this);
        JTextField textCMND = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Phone numbers", this);
        JTextField textPhoneNumbers = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Address", this);
        JTextField textAddress = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Luggage (Kg)", this);
        JTextField textLuggage = new Text_Function().JInput(x2, y, this);

        btExit ex = new btExit(x1, y+=80);
        add(ex);
        ex.action(this);

        JButton bttFinish = new JButton("Finish");
        bttFinish.setBounds(x2, y, 150,30);

        bttFinish.setForeground(Color.BLACK);
        add(bttFinish);

        bttFinish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if (!textName.getText().isEmpty()){
                    String id_ticket = id.value +"-2-" + java.time.LocalTime.now().toString();
                    String sql2 = "insert into ticketdata " +
                            "values ('" + id_ticket + "', '"+id.value + "', '" + idFlight + "', '2')";

                    String sql1 = "insert into ChildrenTicket values (" ;
                    sql1 += "'" + id_ticket + "'";
                    sql1 += ",'" + textName.getText() + "'";
                    sql1 += ",'" + gender.getSelectedItem().toString() + "'";
                    sql1 += ",'" + textBirthday.getText() + "'";
                    sql1 += ",'" + textName1.getText() + "'";
                    sql1 += ",'" + textCMND.getText() + "'";
                    sql1 += ",'" + textPhoneNumbers.getText() + "'";
                    sql1 += ",'" + textAddress.getText() + "'";
                    sql1 += ",'" + textLuggage.getText() + "'";
                    sql1 += ")";

                    //System.out.println(sql1);
                    //System.out.println(sql2);
                    JPanel panel = (JPanel) getContentPane();
                    int n = JOptionPane.showConfirmDialog(panel, "Xác nhận đặt vé",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if(n==JOptionPane.YES_NO_OPTION) {
                        dispose();
                        conn c = new conn();
                        try {
                            c.getStatement().executeUpdate(sql2);
                            c.getStatement().executeUpdate(sql1);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        //System.out.println("Dang ki thanh cong!");

                    }
                }
            }
        });

        setSize(400,550);
        setVisible(true);
        setLocation(520,200);

    }

    public void BookTicket_2(String idFlight, IdUser id){

        int y = 0 , x1 = 60, x2 = 200;

        JLabel idFlightShow = new JLabel("ID FLIGHT : "+ idFlight);
        idFlightShow.setBounds(0, 0, 400, 35);
        idFlightShow.setFont(new Font("Times", Font.PLAIN, 20));
        idFlightShow.setHorizontalAlignment(SwingConstants.CENTER);
        add(idFlightShow);

        JLabel typeTicket = new JLabel("Type ticket : Baby");
        typeTicket.setBounds(0, y+=40, 400, 35);
        typeTicket.setFont(new Font("Times", Font.PLAIN, 20));
        typeTicket.setHorizontalAlignment(SwingConstants.CENTER);
        add(typeTicket);


        new Text_Label().Show(x1, y += 40, "Full name", this);
        JTextField textName = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y+=40, "Gender", this);

        String sex[] = {"Male", "Female"};
        JComboBox gender = new JComboBox(sex);
        gender.setBounds(x2, y, 150, 27);
        add(gender);

        new Text_Label().Show(x1, y+=40, "Birthday", this);
        JTextField textBirthday = new Text_Function().JInput(x2, y, this);

        new Text_Label().Show(x1+100, y+=40, "Guardian", this);
        new Text_Label().Show(x1, y += 40, "Full name", this);
        JTextField textName1 = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "CMND", this);
        JTextField textCMND = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Phone numbers", this);
        JTextField textPhoneNumbers = new Text_Function().JInput(x2, y, this);
        new Text_Label().Show(x1, y += 40, "Address", this);
        JTextField textAddress = new Text_Function().JInput(x2, y, this);

        btExit ex = new btExit(x1, y+=80);
        add(ex);
        ex.action(this);

        JButton bttFinish = new JButton("Finish");
        bttFinish.setBounds(x2, y, 150,30);

        bttFinish.setForeground(Color.BLACK);
        add(bttFinish);

        bttFinish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if (!textName.getText().isEmpty()){
                    String id_ticket = id.value +"-3-" + java.time.LocalTime.now().toString();
                    String sql2 = "insert into ticketdata " +
                            "values ('" + id_ticket + "', '"+id.value + "', '" + idFlight + "', '3')";

                    String sql1 = "insert into BabyTicket values (" ;
                    sql1 += "'" + id_ticket + "'";
                    sql1 += ",'" + textName.getText() + "'";
                    sql1 += ",'" + gender.getSelectedItem().toString() + "'";
                    sql1 += ",'" + textBirthday.getText() + "'";
                    sql1 += ",'" + textName1.getText() + "'";
                    sql1 += ",'" + textCMND.getText() + "'";
                    sql1 += ",'" + textPhoneNumbers.getText() + "'";
                    sql1 += ",'" + textAddress.getText() + "'";
                    sql1 += ")";

                    //System.out.println(sql1);
                    //System.out.println(sql2);
                    JPanel panel = (JPanel) getContentPane();
                    int n = JOptionPane.showConfirmDialog(panel, "Xác nhận đặt vé",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if(n==JOptionPane.YES_NO_OPTION) {
                        dispose();
                        conn c = new conn();
                        try {
                            c.getStatement().executeUpdate(sql2);
                            c.getStatement().executeUpdate(sql1);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        //System.out.println("Dang ki thanh cong!");

                    }
                }
            }
        });

        setSize(400,550);
        setVisible(true);
        setLocation(520,200);

    }

}

