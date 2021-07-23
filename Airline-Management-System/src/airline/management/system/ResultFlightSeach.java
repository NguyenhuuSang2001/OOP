package airline.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultFlightSeach extends JFrame{

    private final JTable table_info;

    public static void main(String[] args){
		ResultFlightSeach test;
		ResultSet resultSet = null;
		test = new ResultFlightSeach(resultSet, new IdUser());
		test.setVisible(true);
	}

    public ResultFlightSeach(ResultSet resultSet, IdUser id){
    	int widthScreen = 1440, heightScreen = 900;
    	setTitle("Flights Information");
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setFont(new Font("Luminari", Font.PLAIN, 13));

        int width = 860, height = 550;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((widthScreen-width)/2, (heightScreen-height)/2, width, height);
		setLayout(null);
		setVisible(true);

		JLabel FlightDetails = new JLabel("FLIGHTS INFORMATION");
		FlightDetails.setFont(new Font("Times", Font.PLAIN,  31));
		FlightDetails.setHorizontalAlignment(SwingConstants.CENTER);
		FlightDetails.setForeground(new Color(100, 149, 237));
		FlightDetails.setBounds(0, 20, width, 35);
		add(FlightDetails);

		table_info = new JTable();
        table_info.setBackground(Color.WHITE);
        table_info.setRowHeight(30);
		table_info.setBounds(0, 0, 860, 470);

        JScrollPane pane = new JScrollPane(table_info);
		pane.setBounds(20, 90, 860, 470);
        pane.setBackground(Color.WHITE);
        add(pane);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(900,650);
		setVisible(true);
		setLocation(200,200);

		JButton bookTicket = new JButton("Book");
		bookTicket.setBounds(width-250, height+30, 100, 30);
		bookTicket.setBackground(Color.lightGray);
		bookTicket.setVisible(true);
		add(bookTicket);

		table_info.setModel(DbUtils.resultSetToTableModel(resultSet));
		table_info.setFont(new Font("Times", Font.PLAIN, 20));
		table_info.setDefaultEditor(Object.class, null);



		bookTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table_info.getSelectedRow());
				if(table_info.getSelectedRow()>=0) {
					String idFlight = "";
					try {
						resultSet.absolute(table_info.getSelectedRow() + 1);
						idFlight = resultSet.getString(1);
//						System.out.println(idFlight);
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
					if(id.value==0){
						new SignIn(0, id);
					}else{
						String []options = {"Người lớn", "Trẻ em", "Em bé"};
						int type = JOptionPane.showOptionDialog(null, "Chọn loại vé cần đặt :",
								"Choose", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
								options, options[0]);
						new BookTicket(idFlight, type, id);
					}
				}
			}
		});

		btExit ex = new btExit((width-150), height+30);
		ex.action(this);
		add(ex);

    }

}