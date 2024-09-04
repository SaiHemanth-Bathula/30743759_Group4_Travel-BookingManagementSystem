package com.travel.dao;

import com.travel.databaseconnection.DatabaseConnection;
import com.travel.utils.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {
    public void addDestination(Destination destination) {
        String sql = "INSERT INTO Destination (name, location, description, price_per_day) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, destination.getName());
            stmt.setString(2, destination.getLocation());
            stmt.setString(3, destination.getDescription());
            stmt.setDouble(4, destination.getPricePerDay());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Destination getDestination(int id) {
        String sql = "SELECT * FROM Destination WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Destination destination = new Destination();
                destination.setDestinationId(rs.getInt("destination_id"));
                destination.setName(rs.getString("name"));
                destination.setLocation(rs.getString("location"));
                destination.setDescription(rs.getString("description"));
                destination.setPricePerDay(rs.getDouble("price_per_day"));
                return destination;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateDestination(Destination destination) {
        String sql = "UPDATE Destination SET name = ?, location = ?, description = ?, price_per_day = ? WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, destination.getName());
            stmt.setString(2, destination.getLocation());
            stmt.setString(3, destination.getDescription());
            stmt.setDouble(4, destination.getPricePerDay());
            stmt.setInt(5, destination.getDestinationId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDestination(int id) {
        String sql = "DELETE FROM Destination WHERE destination_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Destination> getAllDestinations() {
        String sql = "SELECT * FROM Destination";
        List<Destination> destinations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Destination destination = new Destination();
                destination.setDestinationId(rs.getInt("destination_id"));
                destination.setName(rs.getString("name"));
                destination.setLocation(rs.getString("location"));
                destination.setDescription(rs.getString("description"));
                destination.setPricePerDay(rs.getDouble("price_per_day"));
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }
}
