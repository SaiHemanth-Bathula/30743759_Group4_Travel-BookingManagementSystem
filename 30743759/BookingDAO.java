import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public void createBooking(Booking booking) {
        String sql = "INSERT INTO Booking (destination_id, customer_id, booking_date, start_date, end_date, total_cost, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getDestinationId());
            stmt.setInt(2, booking.getCustomerId());
            stmt.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.setDate(4, new java.sql.Date(booking.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(booking.getEndDate().getTime()));
            stmt.setDouble(6, booking.getTotalCost());
            stmt.setString(7, booking.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int id) {
        String sql = "SELECT * FROM Booking WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setDestinationId(rs.getInt("destination_id"));
                booking.setCustomerId(rs.getInt("customer_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setTotalCost(rs.getDouble("total_cost"));
                booking.setStatus(rs.getString("status"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBooking(Booking booking) {
        String sql = "UPDATE Booking SET destination_id = ?, customer_id = ?, booking_date = ?, start_date = ?, end_date = ?, total_cost = ?, status = ? WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getDestinationId());
            stmt.setInt(2, booking.getCustomerId());
            stmt.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.setDate(4, new java.sql.Date(booking.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(booking.getEndDate().getTime()));
            stmt.setDouble(6, booking.getTotalCost());
            stmt.setString(7, booking.getStatus());
            stmt.setInt(8, booking.getBookingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelBooking(int id) {
        String sql = "UPDATE Booking SET status = 'cancelled' WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM Booking";
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setDestinationId(rs.getInt("destination_id"));
                booking.setCustomerId(rs.getInt("customer_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setTotalCost(rs.getDouble("total_cost"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
