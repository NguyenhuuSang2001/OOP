package airline.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketInfor extends JFrame{
    public static void main(String[] args){
        new TicketInfor(1);
    }

    public TicketInfor(int idUser){

        String typeTableTicket[] = {"AdultTicket", "ChildrenTicket", "BabyTicket"}, IdTicket = "";
        getContentPane().setBackground(Color.WHITE);
        setTitle("Tickets Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);



        int y = 0 , x1 = 60, x2 = 200;
        new Text_Label().Show(x1, y+=20,"Id Ticket", this);
        conn c = new conn();
        ArrayList <String> id =  new ArrayList<>();
        ArrayList <String> idFlight = new ArrayList<>();
        ArrayList <Integer> type = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = c.getStatement().executeQuery("select * from Ticketdata where IdUser = '" + idUser +"'");
            while (resultSet.next()){
                id.add(resultSet.getString(1));
                idFlight.add(resultSet.getString(3));
                type.add(Integer.parseInt(resultSet.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JComboBox IdTickets = new JComboBox(id.toArray());
        IdTickets.setBounds(x2, y, 150, 30);
        add(IdTickets);

        if(IdTickets.getItemCount()==0){
            setVisible(false);
            dispose();
            JPanel panel = new JPanel();
            int n = JOptionPane.showConfirmDialog(panel, "Quý khách chưa book vé !",
                    "Thông báo", JOptionPane.DEFAULT_OPTION);
            add(panel);
        }

        ArrayList<JLabel> List = new ArrayList<>();
        for(int i=0; i<10; ++i){
            JLabel jLabel = new JLabel();
            jLabel.setBounds(x1, y+=40, 200, 27);
            List.add(jLabel);
            add(List.get(i));
        }

        try {
            resultSet = c.getStatement().executeQuery("select * from "
                    + typeTableTicket[type.get(IdTickets.getSelectedIndex())-1] + " where IdTicket = '"
                    + IdTickets.getSelectedItem().toString() + "'");
            if(resultSet.next()){
                String text_ = "ID flight : " + idFlight.get(IdTickets.getSelectedIndex());
                List.get(0).setText(text_);
                ResultSetMetaData r = resultSet.getMetaData();
                for(int i=1; i<r.getColumnCount(); ++i){
                    String text = r.getColumnName(i+1) + " : " + resultSet.getString(i+1);
                    List.get(i).setText(text);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        IdTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(IdTickets.getSelectedIndex());
                for (int i=0; i<10; ++i)
                    List.get(i).setText("");
                if(IdTickets.getItemCount()==0  || IdTickets.getSelectedIndex()==-1){
                    setVisible(false);
                    dispose();
                    JPanel panel_ = new JPanel();
                    int n_ = JOptionPane.showConfirmDialog(panel_, "Quý khách đã hủy hết vé !",
                            "Thông báo", JOptionPane.DEFAULT_OPTION);
                    add(panel_);
                }
                try {
                    ResultSet resultSet = c.getStatement().executeQuery("select * from "
                            + typeTableTicket[type.get(IdTickets.getSelectedIndex())-1] + " where IdTicket = '"
                            + IdTickets.getSelectedItem().toString() + "'");
                    if(resultSet.next()){
                        String text_ = "ID flight : " + idFlight.get(IdTickets.getSelectedIndex());
                        List.get(0).setText(text_);
                        ResultSetMetaData r = resultSet.getMetaData();
                        for(int i=1; i<r.getColumnCount(); ++i){
                            String text = r.getColumnName(i+1) + " : " + resultSet.getString(i+1);
                            List.get(i).setText(text);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        btExit ex = new btExit(300, 420);
        add(ex);
        ex.action(this);

        JButton bttCancel = new JButton("Cancel ticket");
        bttCancel.setBounds(x1, 420, 150, 30);
        bttCancel.setForeground(Color.BLACK);
        add(bttCancel);

        bttCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JPanel panel = (JPanel) getContentPane();
                int n = JOptionPane.showConfirmDialog(panel, "Xác nhận hủy vé", "Confirm", JOptionPane.YES_NO_OPTION);
                if(n==JOptionPane.YES_NO_OPTION) {
                    conn c = new conn();
                    try {
                        c.getStatement().executeUpdate("Delete from " + typeTableTicket[type.get(IdTickets.getSelectedIndex())-1] + " where IdTicket = '" + IdTickets.getSelectedItem().toString() + "'");
                        c.getStatement().executeUpdate("Delete from Ticketdata where ID = '" + IdTickets.getSelectedItem().toString() + "'");
                        type.remove(IdTickets.getSelectedIndex());
                        idFlight.remove(IdTickets.getSelectedIndex());
                        IdTickets.removeItemAt(IdTickets.getSelectedIndex());
                        if(IdTickets.getItemCount()==0  || IdTickets.getSelectedIndex()==-1){
                            setVisible(false);
                            dispose();
                            JPanel panel_ = new JPanel();
                            int n_ = JOptionPane.showConfirmDialog(panel, "Quý khách đã hủy hết vé !",
                                    "Thông báo", JOptionPane.DEFAULT_OPTION);
                            add(panel);
                        }
                        else
                            IdTickets.setSelectedIndex(0);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
//                    System.out.println("Hủy vé thành công!");
                }
            }
        });

        setSize(400,500);
        setVisible(true);
        setLocation(520,x2);
	}
        
}
