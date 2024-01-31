import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperations {

    private static final String JDBC_URL = "jdbc:mysql://your_database_url:3306/scoredb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public static void storeScoresInDatabase(int score, int maxScore) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String insertQuery = "INSERT INTO scores (score, max_score) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, score);
                preparedStatement.setInt(2, maxScore);
                preparedStatement.executeUpdate();
            }
        }
    }
}
