import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreDAO {

    // JDBC database URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/scoredb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public void insertScore(String email, int score, int highscore) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO scoretable (email, score, highscore) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);  // Fix: Use 'name' instead of 'email'
                preparedStatement.setInt(2, score);
                preparedStatement.setInt(3, highscore);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
