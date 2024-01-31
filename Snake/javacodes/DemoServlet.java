import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DemoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
 
    public DemoServlet() {
        super();
    }

  /* 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    response.getWriter().append("").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name=request.getParameter("name");
    String email=request.getParameter("email");
    String password=request.getParameter("password");

    //System.out.println(str);
    Jdbc obj=new Jdbc(name,email,password);
      obj.store();
       response.sendRedirect("signin.html");

    //response.getWriter().append(str);
    doGet(request, response);
  }
}