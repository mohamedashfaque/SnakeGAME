import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Jdbc {
    String dbUrl="jdbc:mysql://localhost:3306/logindb";
      String dbUname="root";
      String dbPassword="";
      String dbDriver="com.mysql.cj.jdbc.Driver";
      String name,email,password;
      Jdbc(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;
        ;
      }
   
      void store(){
        Connection con = null;
         try {
        Class.forName(dbDriver);  //class not found exception
        con = DriverManager.getConnection(dbUrl,dbUname,dbPassword);   //sql Exception
        String sql = "insert into logintable (name,email,password)"
            + "values('"+name+"','"+email+"','"+password+"')";      
        Statement s = con.createStatement();
        s.execute(sql);
         
           con.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      }
}
