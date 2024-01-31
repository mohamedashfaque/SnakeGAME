import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ScoreServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreServlet() {
        super();
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  response.getWriter().append("").append(request.getContextPath());
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String email=request.getParameter("email");
  String score=request.getParameter("score");
  String highscore=request.getParameter("highscore");
 
  Score obj1=new Score(email,score,highscore);
  obj1.store();
  doGet(request, response);
 }
}