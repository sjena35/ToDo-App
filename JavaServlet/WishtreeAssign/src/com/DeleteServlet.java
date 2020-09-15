package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Deletetodotask")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out= response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		Connection con=null;
		PreparedStatement stm=null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
				
				stm=con.prepareStatement("delete from taskinfo where taskid="+id);
				stm.executeUpdate();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("todolist.html");
		
		
	}
       
    
}
