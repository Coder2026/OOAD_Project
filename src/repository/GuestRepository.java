package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Guest;
import model.User;

public class GuestRepository {
	
    public static List<User> getGuests() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role = 'Guest'";
        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new Guest(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static List<User> getGuests(String eventId) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        List<User> participants = new ArrayList<>();
        String query = "SELECT users.* FROM User users INNER JOIN Invitation invitations ON users.user_id = invitations.user_id WHERE invitations.event_id = ? and users.role = 'Guest'"
        		+ "AND invitations.status = 'Accepted'";

        try {
            PreparedStatement ps = db.preparedStatement(query);
            if (ps != null) {
                ps.setString(1, eventId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    participants.add(new Guest(
                        rs.getString("user_id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        null,
                        null
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }
}
