import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UpdateMaxScoreServlet")
public class UpdateMaxScoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateMaxScoreServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        int currentScore = Integer.parseInt(request.getParameter("score"));

        // Get the current maxscore from the database
        int maxScore = getMaxScore(email);

        // Update maxscore if the current score is greater
        if (currentScore > maxScore) {
            updateMaxScore(email, currentScore);
        }

        // You can send a response back to the client if needed
        response.getWriter().write("MaxScore updated successfully!");
    }

    private int getMaxScore(String email) {
        // Modify the database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/logindb";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String sql = "SELECT maxscore FROM scoretable WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("maxscore");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Default value if no maxscore found
        return 0;
    }

    private void updateMaxScore(String email, int newMaxScore) {
        // Modify the database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/scoredb";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String sql = "UPDATE scoretable SET maxscore = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, newMaxScore);
                stmt.setString(2, email);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
