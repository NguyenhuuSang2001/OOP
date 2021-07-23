package airline.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Flight_search extends JFrame{  //Second Frame

    private final JTextField flightCode;
    
    public static void main(String[] args){		 
		Flight_search test;
		test = new Flight_search(1440, 900, new IdUser());
		test.setVisible(true);
	}
    
    public Flight_search(int widthScreen, int heightScreen, IdUser id){
    	setTitle("Search");
        int width = 200, height = 200;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((widthScreen-width)/2, (heightScreen-height)/2, width, height);
		setLayout(null);
		setVisible(true);

		JLabel NewLabel = new JLabel("");
		NewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("Image/search.jpg")));
		NewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NewLabel.setBounds(0, 0, 240, 200);
		add(NewLabel);

		int code = 200;
		flightCode = new JTextField("Enter Flight Code");
		flightCode.setFont(new Font("Times", Font.PLAIN, 20));
		flightCode.setBounds(20, 20, code, 30);
		flightCode.setHorizontalAlignment(SwingConstants.CENTER);
		flightCode.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
		flightCode.setToolTipText("Enter Flight Code");
		NewLabel.add(flightCode);

		flightCode.selectAll();
		flightCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flightCode.selectAll();
			}

		});
		flightCode.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){
					show(flightCode.getText(), id);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) { }
		});
		flightCode.requestFocusInWindow();


		JButton bttsearch = new JButton("Search");
		bttsearch.setBounds(20, 110, 100, 30);
		bttsearch.setBackground(Color.BLUE);
		bttsearch.setForeground(Color.BLACK);
		bttsearch.setHorizontalAlignment(SwingConstants.CENTER);
		btExit ex = new btExit(130, 110);
		ex.action(this);
		NewLabel.add(ex);
		
		bttsearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	bttsearch.setBackground(Color.BLUE);
            	show(flightCode.getText(), id);
            }
        });

		NewLabel.add(bttsearch);

		setSize(240,200);
		setVisible(true);

	}

    public void show(String code_flight, IdUser id){
		try {
			conn c = new conn();
			Statement statement = c.getConnection().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery("select * from " +
					"flightdata where ID = '"+ code_flight + "'");
//			System.out.println(resultSet.getRow());
			if (resultSet.next()) {
				resultSet.beforeFirst();
//				System.out.println(resultSet.toString());
				new ResultFlightSeach(resultSet, id);
			}
			else{
				JPanel panel = new JPanel();
				int n = JOptionPane.showConfirmDialog(panel, "Không tìm thấy chuyến bay phù hợp !",
						"Thông báo", JOptionPane.DEFAULT_OPTION);
				add(panel);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}