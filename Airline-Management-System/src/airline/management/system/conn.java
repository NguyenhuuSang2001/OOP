package airline.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conn{
    private Connection connection = null;
    private Statement statement = null;

    public Statement getStatement() {
        return statement;
    }

    public conn(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Airline","root","123456a@");
            statement = connection.createStatement();
//            System.out.println("Ket noi thanh cong !");
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(){
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        conn c = new conn();
    }
}  