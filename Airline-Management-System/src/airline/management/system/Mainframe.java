package airline.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mainframe extends JFrame{

    private IdUser id = new IdUser();
    public static void main(String[] args) {
        new Mainframe().setVisible(true);
    }
    
    public Mainframe() {
        super("AIRLINE");
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Lấy kích thước màn hình hiển thị
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JLabel NewLabel = new JLabel("");
        NewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("Image/vnairlines.jpg")));
        NewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NewLabel.setBounds(0, 0, screenSize.width, screenSize.height);
        add(NewLabel);

        JLabel AirlineManagementSystem = new JLabel("Welcome To The Online Flight Booking System");
	    AirlineManagementSystem.setForeground(Color.BLUE);
        AirlineManagementSystem.setFont(new Font("Times", Font.PLAIN, 36));
        AirlineManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
        AirlineManagementSystem.setBounds(300, 30, screenSize.width-600, 55);
        NewLabel.add(AirlineManagementSystem);


        FlightSearch flightSeach = new FlightSearch(id);
        NewLabel.add(flightSeach);
        flightSeach.Show();
//        flightSeach.Hidden();
        JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
		
        JMenu AirlineSystem = new JMenu("Airline System");
        AirlineSystem.setForeground(Color.BLUE);
	    menuBar.add(AirlineSystem);
		
        JMenuItem FlightDetails = new JMenuItem("Flight Search");
        AirlineSystem.add(FlightDetails);
        JMenuItem perInf = new JMenuItem("Personal Information");
        AirlineSystem.add(perInf);

		
        JMenuItem SectorDetails_1 = new JMenuItem("Payment Details");
        AirlineSystem.add(SectorDetails_1);

        JMenuItem Logout = new JMenuItem("Logout");
        AirlineSystem.add(Logout);

        JMenuItem Exit = new JMenuItem("Exit");
        AirlineSystem.add(Exit);

        JMenu Ticket = new JMenu("Ticket");
        Ticket.setForeground(Color.RED);
        JMenuItem ticketInfor = new JMenuItem("Ticket Information");
        Ticket.add(ticketInfor);

	    menuBar.add(Ticket);
		
        FlightDetails.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                        new Flight_search(860, 523, id);
                }
        });

        perInf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.value==0){
                    SignIn signIn = new SignIn(1, id);
//                    System.out.println(id.value);
                }else
                    new PersonalInformation(id.value);
            }
        });
		
        SectorDetails_1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    new Payment_Details();
                } catch (Exception e) {
                            e.printStackTrace();
                }
            }
	    });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.value = 0;
            }
             });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ticketInfor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.value==0){
                    new SignIn(2, id);
                }else
                    new TicketInfor(id.value);
            }
        });

        setSize(1950,1090);
	    setVisible(true);
    }

}
