import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInsert {

    // JDBC connection method
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "");
    }

    // Insert a new user into the 'users' table
    public static boolean insertUser(String username, String email, String password) {
        try (Connection connection = connect()) {
            String sql = "INSERT INTO logintable (username, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Returns true if the user was inserted successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Example of using the insertUser method
        if (insertUser("JohnDoe", "john.doe@example.com", "password123")) {
            System.out.println("User inserted successfully");
        } else {
            System.out.println("User insertion failed");
        }
    }
}
