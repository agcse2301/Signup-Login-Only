package com.ayushi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ayushi","ayushi");   
			String sql = "select * from details where email='"+email+"' and password='"+pass+"'";
			Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        if(rs.next())
	        {
	        	RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
	    		rd.forward(request, response);
	        }
	        else {
	        	RequestDispatcher rd=request.getRequestDispatcher("error.html");
	    		rd.forward(request, response);
	        }
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	}

}
