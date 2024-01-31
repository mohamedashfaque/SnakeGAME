import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Score {
		String dbUrl="jdbc:mysql://localhost:3306/scoredb";
	    String dbUname="root";
	    String dbPassword="";
	    String dbDriver="com.mysql.cj.jdbc.Driver";
	    String email,score,highscore;
	   
//		public Score() {
//			super();
//		}

		public Score(String email,String score,String highscore){
			  this.email=email;    
			  this.score=score;
			  this.highscore=highscore;
			 
		  }
	 
      void store(){

    	  Connection con = null ;
	   		try {
				Class.forName(dbDriver);  //class not found exception
				con = DriverManager.getConnection(dbUrl,dbUname,dbPassword);   //sql Exception
				String sql = "insert into scoretable (email,score,highscore)"
						+ "values('"+email+"','"+score+"','"+highscore+"')";			
				Statement s = con.createStatement();
				s.execute(sql);
			   
			   	con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
      }
}