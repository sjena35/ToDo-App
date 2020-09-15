package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	static String uemail;
	static String upass;
	static Boolean flag=false;
	static Boolean flag1=false;
	static String fname;
	static String lname;
	static int id;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
 		flag=false;
 		flag1=false;
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		
		uemail=request.getParameter("uemail");
		upass=request.getParameter("pass");
		UserCheck();
		
		if(flag==true) {
			ToDoServlet.setUid(id);
			response.sendRedirect("todolist.html");
		}
		
		else if(flag1==true) {//for admin page
			adminServlet.getUid(id);
			request.getRequestDispatcher("adminaction").forward(request, response);
		}
		else {
	
			response.sendRedirect("alertPassword.html");
		
		}
		
	}
	
	public static void UserCheck() {
		
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
				
				stm=con.createStatement();
				
				rs=stm.executeQuery("select * from userinfo");
				
				while(rs.next()) {
					
					if(rs.getString("useremail").equals(uemail) && rs.getString("userpassword").equals(upass) && rs.getString("status").equals("active")) {
						if(rs.getString("useraccess").equals("nonadmin")) {
						flag=true;
						fname=rs.getString("firstname");
						lname=rs.getString("lastname");
						id=rs.getInt("userid");
						break;
						}
						
						else if(rs.getString("useraccess").equals("admin")) {
							flag1=true;
							fname=rs.getString("firstname");
							lname=rs.getString("lastname");
							id=rs.getInt("userid");
							break;
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
		
		finally {
			try {
				con.close();
				stm.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}

   

