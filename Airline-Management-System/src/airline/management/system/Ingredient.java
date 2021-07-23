package airline.management.system;

import airline.management.system.structData.SearchConditions;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class IdUser{
    public int value;
    public IdUser(){}
}

class btExit extends JButton{
    public btExit(int x, int y){
        super("Exit");
        setSize(80, 30);
        setLocation(x, y);
        setHorizontalAlignment(CENTER);
        setForeground(Color.BLACK);
    }

    public void action(JFrame jFrame){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
    }

    public void action(JLabel jLabel){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel.setVisible(false);
            }
        });
    }
}

class Text_Function {
    public JTextField JInput(int x, int y, Frame frame){
        JTextField input = new JTextField();
        input.setBounds(x, y, 150, 27);
        input.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(input);
        return input;
    }

    public JTextField JInput(int x, int y, JLabel jLabel){
        JTextField input = new JTextField();
        input.setBounds(x, y, 150, 27);
        input.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.add(input);
        return input;
    }

    public TextField Input(int x, int y, boolean encode, Frame frame){
        TextField input = new TextField();
        input.setBounds(x, y, 150, 27);
        if(encode)
            input.setEchoChar('*');
        frame.add(input);
        return input;
    }

    public TextField Input(int x, int y, boolean encode, JLabel jLabel){
        TextField input = new TextField();
        input.setBounds(x, y, 150, 27);
        if(encode)
            input.setEchoChar('*');
        jLabel.add(input);
        return input;
    }
}

class Text_Label {
    public void Show(int x, int y, String text, Frame frame){
        JLabel show = new JLabel(text + " :");
        show.setFont(new Font("Times", Font.PLAIN, 17));
        show.setBounds(x, y, 135, 27);
        frame.add(show);
    }

    public void Show(int x, int y,int width, int height, String text, Frame frame){
        JLabel show = new JLabel(text + " :");
        show.setFont(new Font("Times", Font.PLAIN, 17));
        show.setBounds(x, y, width, height);
        frame.add(show);
    }

    public JLabel Show(int x, int y,int width, int height, String text){
        JLabel show = new JLabel(text);
        show.setFont(new Font("Times", Font.PLAIN, 17));
        show.setBounds(x, y, width, height);
        return show;
    }

    public void Show(int x, int y, String text, JLabel jLabel){
        JLabel show = new JLabel(text + " :");
        show.setFont(new Font("Times", Font.PLAIN, 17));
        show.setBounds(x, y, 135, 27);
        jLabel.add(show);
    }

}


class FlightSearch extends JLabel{
    JButton Search;
    SearchConditions searchConditions;

    public FlightSearch(IdUser id){
        setBackground(Color.WHITE);
        setLayout(null);
        setIcon(new ImageIcon(ClassLoader.getSystemResource("Image/flight_search.jpg")));

        int y = 50;
        new Text_Label().Show(10, y, "Điểm đi", this);
        String[] items1 = {"Hà Nội","Hải Phòng", "Thanh Hóa", "Vân Đồn", "Điện Biên", "Đà Nẵng", "Huế", "Vinh", "Đồng Hới", "Tuy Hòa", "Chu Lai", "Hồ Chí Minh", "Nha Trang", "Đà Lạt", "Quy Nhơn", "Phú Quốc", "Cần Thơ", "Buôn Ma Thuột", "Pleiku", "Côn Đảo", "Rạch Giá", "Cà Mau"};
        JComboBox placeToGo = new JComboBox(items1);
        placeToGo.setBounds(90, y, 150, 27);
        add(placeToGo);

        new Text_Label().Show(260, y, "Điểm đến", this);
        String[] items2 = {"Hà Nội","Hải Phòng", "Thanh Hóa", "Vân Đồn", "Điện Biên", "Đà Nẵng", "Huế", "Vinh", "Đồng Hới", "Tuy Hòa", "Chu Lai", "Hồ Chí Minh", "Nha Trang", "Đà Lạt", "Quy Nhơn", "Phú Quốc", "Cần Thơ", "Buôn Ma Thuột", "Pleiku", "Côn Đảo", "Rạch Giá", "Cà Mau"};
        JComboBox destination = new JComboBox(items2);
        destination.setBounds(350, y, 150, 27);
        add(destination);

        new Text_Label().Show(520, y, "Hãng hàng không", this);
        String[] items3 = {"Tất cả", "Vietnam Airlines", "Vietjet Air", "Jetstar Pacific", "Bamboo Airways"};
        JComboBox airlines = new JComboBox(items3);
        airlines.setBounds(660, y, 190, 27);
        add(airlines);

        new Text_Label().Show(60, y+=40, "Ngày bay", this);
        String[] items4 = {"","01/07/2021","02/07/2021","03/07/2021","04/07/2021","05/07/2021","06/07/2021","07/07/2021","08/07/2021","09/07/2021",
                "10/07/2021","11/07/2021","12/07/2021","13/07/2021","14/07/2021","15/07/2021","16/07/2021","17/07/2021","18/07/2021","19/07/2021",
                "20/07/2021", "21/07/2021","22/07/2021","23/07/2021","24/07/2021","25/07/2021","26/07/2021","27/07/2021","28/07/2021","29/07/2021","30/07/2021"};
        JComboBox date = new JComboBox(items4);
        date.setBounds(150, y, 150, 27);
        add(date);

        new Text_Label().Show(400, y, "Xuất phát", this);
        String[] times_bay = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00"};
        JComboBox timeStart = new JComboBox(times_bay);
        timeStart.setBounds(500, y, 100, 27);
        add(timeStart);
        new Text_Label().Show(610, y, "đến", this);
        JComboBox timeEnd = new JComboBox(times_bay);
        timeEnd.setBounds(650, y, 100, 27);
        add(timeEnd);

        int x = 110;
        String [] numbers = {"0", "1", "2", "3", "4", "5"};
        new Text_Label().Show(x, y+=40,"Người lớn", this);
        JComboBox number1 = new JComboBox(numbers);////
        number1.setBounds(x+=90, y, 60, 27);
        add(number1);
        new Text_Label().Show(x+=70, y,"Trẻ em", this);
        JComboBox number2 = new JComboBox(numbers);////
        number2.setBounds(x+=65, y, 60, 27);
        add(number2);
        new Text_Label().Show(x+=70, y,"Em bé", this);
        JComboBox number3 = new JComboBox(numbers);////
        number3.setBounds(x+=65, y, 60, 27);
        add(number3);

        Search = new JButton("Tìm kiếm");
        Search.setBounds(680, y+=10, 100, 30);
        add(Search);


        Search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                searchConditions = new SearchConditions();
                searchConditions.setPlaceToGo((String) placeToGo.getSelectedItem());
                searchConditions.setDestination((String) destination.getSelectedItem() );
                searchConditions.setDate((String) date.getSelectedItem());
                searchConditions.setTimeStart1( timeStart.getSelectedItem().toString());
                searchConditions.setTimeStart2(timeEnd.getSelectedItem().toString());
                String numbers[] = new String[3];
                numbers[0] = number1.getSelectedItem().toString();
                numbers[1] = number2.getSelectedItem().toString();
                numbers[2] = number3.getSelectedItem().toString();
                searchConditions.setNumber(numbers);
                searchConditions.setAirlines(airlines.getSelectedItem().toString());

                conn c = new conn();
                String sql = "select * from flightdata where " + searchConditions.toString();
//                System.out.println(sql);
                try {
                    Statement statement = c.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet resultSet = statement.executeQuery(sql);
//                    System.out.println(resultSet.getRow());
                    if (resultSet.next()) {
                        resultSet.beforeFirst();
//                        System.out.println(resultSet.toString());
                        new ResultFlightSeach(resultSet, id);
                    }
                    else{
                        JPanel panel = new JPanel();
                        int n = JOptionPane.showConfirmDialog(panel, "Không tìm thấy chuyến bay phù hợp !", "Thông báo", JOptionPane.DEFAULT_OPTION);
                        add(panel);
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        setSize(860,y+=40);
        setLocation(300,150);
    }
    public void Show(){
        setVisible(true);
    }
    public void Hidden(){
        setVisible(false);
    }

}
