package pk2;
import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.*;
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.ServletConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import javafx.print.Printer;

  

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	public String db_url = "jdbc:mysql://localhost/j2ee";
    public String db_user = "root"; 
    public String PASSWORD = "1123654";
    
    public LoginServlet(){
    	super();
    }
    
    
    
public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
        //response.getWriter().append("served :").append(request.getContextPath());
        //response.setContentType("text/html");  
    
    PrintWriter out = response.getWriter();  
          
    String user=request.getParameter("user");  
    String pass=request.getParameter("pswd");  
    
    out.close(); 
     
     
    try
    {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection(db_url,db_user,PASSWORD);
	  	Statement stmt=conn.createStatement();
	  	String valsql = "SELECT user_name,pswd FROM reg_users WHERE user_name == user";
	  	ResultSet rs = stmt.executeQuery(valsql);
	  	 
	  	 while(rs.next()){
	  		 String dbuser =rs.getString("user_name");
	  		 String pswd2 =rs.getString("pswd");
	  		if(user.matches("")&& (pass.matches(""))){  
	  	        RequestDispatcher rd=request.getRequestDispatcher("home.html");  
	  	        rd.forward(request,response);  
	  	    }
	  		else{  
	  	        out.println("Sorry username or password error");  
	  	        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
	  	        rd.include(request,response);  
	  	    }
	  	 }
    }
    catch (ClassNotFoundException e)
    {
  	  e.printStackTrace();
    }
	catch(SQLException sql){
		sql.printStackTrace();
	}
	}
 
private PrintWriter append(String string) {
	// TODO Auto-generated method stub
	return null;
	
}  
} 