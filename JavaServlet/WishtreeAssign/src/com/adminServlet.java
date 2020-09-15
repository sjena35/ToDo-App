package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminaction")
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static HttpServletRequest req;
	static HttpServletResponse res;
	static int uid;
	
	public static void getUid(int x) {
		uid=x;
	}
	
	
	
	static PrintWriter out;
       
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    	
    	req=request;
    	res=response;
    	out=res.getWriter();
    	
    	
    	UserList();
    	UserResp();
    }
    
    public static void UserResp() throws ServletException, IOException {
    	res.setContentType("text/html");
 	    out.println("<!DOCTYPE html>");
 		out.println("<html>");
 		out.println("<head>");
 		out.println("<title>View List</title>");
 		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
 		out.println("<link rel='stylesheet' href='style.css'/>");
 		out.println("</head>");
 		out.println("<body>");
 		req.getRequestDispatcher("adminpage.html").include(req, res);
 		out.println("<div class='container'>");
 		out.print("<h1 style=\"text-align: center;\">View Userlist</h1>");
 		//out.println("<form action='complete' method='get'>");
 		out.println("<table class='table table-bordered table-striped'>");
 		out.print("<tr><th>User Name</th><th>Email</th><th>Gender</th><th>Status</th><th>Delete</th></tr>");
 		
 		
 		for(User bean:ToDoServlet.userList){
 			
 			out.print("<tr><td>"+bean.fname+" "+bean.lname+"</td><td>"+bean.uemail+"</td><td>"+bean.gender+"</td><td>"+bean.status+"</td><td><button class='btn btn-warning'><a href='Deleteuser?id="+bean.uid+"'>Active/Deactive</a></button></td></tr>");
 			
 			
 		}
 		out.println("</table>");
 		
 		out.println("<br>");
 		//out.println("<button type='submit' class=\"btn btn-success\">Complete Task</button>");
 		
 		//out.println("</form>");
 		out.println("</div>");
 		out.println("</body>");
 		out.println("</html>");
 		
 		out.close();
    }
    
    public static void UserList() {
    	
    	Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		ToDoServlet.userList= new ArrayList<>();
		
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
					
					stm=con.createStatement();
					rs=stm.executeQuery("select * from userinfo");
					
					while(rs.next()) {
						
						if(uid!=rs.getInt(1)) {
						
						ToDoServlet.userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(8)));
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
