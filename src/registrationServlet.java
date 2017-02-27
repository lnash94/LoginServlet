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

@WebServlet("/RegistrationServlet")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String db_url = "jdbc:mysql://localhost/j2ee";
    public String db_user = "root"; 
    public String PASSWORD = "1123654";
	private Boolean isMatch ;

       
    public registrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response, Boolean True, Boolean False) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//getting registration form
		//response.getWriter()append("served :").append(request.getContextPath());
		response.setContentType("text/html");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String sex = request.getParameter("gender");
		String city = request.getParameter("city");
		String user_name = request.getParameter("user");
		String pswd = request.getParameter("pswd");
		
		PrintWriter out = response.getWriter();
		out.close();
		try
	    {
	  	 Class.forName("com.mysql.jdbc.Driver");
	  	 Connection conn = DriverManager.getConnection(db_url,db_user,PASSWORD);
	  	 Statement stmt=conn.createStatement();
	  	 
	  	 
	  	 String valsql = "SELECT user_name FROM reg_users WHERE user_name == user";
	  	 ResultSet rs = stmt.executeQuery(valsql);
	  	 
	  	 //check that user name is in database.
	  	 
	  	 Boolean isMatch = true;
	  	 while(rs.next()){
	  		 String dbuser =rs.getString("user_name");

	  		 if (user_name.matches(dbuser)){
	  			 
	  			 isMatch = false;
	  			 
	  		 }
	  		 else{
	  			
	  			isMatch= true;
	  			 
	  		 }
	  	 }
	  	 if(isMatch==false){
	  		out.println("Sorry this user name already had.");
 			RequestDispatcher rd = request.getRequestDispatcher("registration.html");
 			rd.forward(request,response);
	  		 
	  	 }
	  	 else{
	  		String insert ="INSERT INTO reg_user(fname,lname,sex,city,user_name,pswd)VALUES('"+fname+"','"+lname+"','"+sex+"','"+city+"','"+user_name+"','"+pswd+"')";	 
		  	stmt.executeQuery(insert);
		  	response.sendRedirect("insert_msg.html");
		  	 
	  		RequestDispatcher rd = request.getRequestDispatcher("home.html");
  			rd.forward(request,response); 
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


