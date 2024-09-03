import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS bookings (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                    "customer_name VARCHAR(100), " +
                                    "destination VARCHAR(100), " +
                                    "booking_date DATE)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Table 'bookings' created successfully.");
            }

            // View table
            String viewTableSQL = "SELECT * FROM bookings";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(viewTableSQL)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String customerName = rs.getString("customer_name");
                    String destination = rs.getString("destination");
                    String bookingDate = rs.getDate("booking_date").toString();
                    System.out.println("ID: " + id + ", Customer Name: " + customerName + 
                                       ", Destination: " + destination + ", Booking Date: " + bookingDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}