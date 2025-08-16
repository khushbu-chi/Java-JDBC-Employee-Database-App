import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {
    private static final String url="jdbc:mysql://127.0.0.1:3306/employee_details";
    private static final String username="root";
    private static final String password="root";
    public static Connection getConnection() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
