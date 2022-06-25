package com.ayushi;
import java.sql.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:xe","ayushi","ayushi");  
			Statement stmt=con.createStatement();  
			String sql = "INSERT INTO details VALUES ('"+name+"','"+email+"','"+pass+"')";
	        stmt.executeUpdate(sql) ; 
			con.close();  
			request.setAttribute("client_name", name);
			RequestDispatcher rd=request.getRequestDispatcher("welcome.html");
			rd.forward(request, response);
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		RequestDispatcher rd=request.getRequestDispatcher("Main.html");
		rd.forward(request, response);
		
	}

}
