import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
*
*/
private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "");

            // Prepare SQL statement for user login
            String sql = "SELECT * FROM logintable WHERE email=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                // Execute the SQL statement
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // User login successful
                    response.sendRedirect("./snake.html"); // Redirect to main.html
                } else {
                    // User login failed
                    PrintWriter out = response.getWriter();
                    out.println("<html><body><script>alert('Invalid email or password'); window.location.href='./index.html';</script></body></html>");
                }
            }

            // Close the database connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception, you may choose to log it or handle it as needed
            response.getWriter().println("Error during login");
        }
    }
}
