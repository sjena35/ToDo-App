package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Deleteuser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int uid;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		uid=Integer.parseInt(request.getParameter("id"));
		
		Delete();
		request.getRequestDispatcher("adminaction").forward(request, response);
	}
	
	public static void Delete() {

		Connection con=null;
		Statement stm=null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
				
				stm=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=stm.executeQuery("select * from userinfo");
				
				while(rs.next()) {
					if(uid==rs.getInt("userid")) {
						
						if(rs.getString("status").equals("active")) {
							rs.updateString(8, "deactive");
							rs.updateRow();
						}
						
						else {
							rs.updateString(8, "active");
							rs.updateRow();
						}
					}
				}
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
       
   

}
