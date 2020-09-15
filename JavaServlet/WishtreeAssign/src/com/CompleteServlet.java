package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CompleteServlet")
public class CompleteServlet extends HttpServlet {
	
	static String taskcomplete[];
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		taskcomplete=request.getParameterValues("taskcompleted");
		
		CompleteUpdate();
		
		   response.setContentType("text/html");
		   out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>View List</title>");
			out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
			out.println("<link rel='stylesheet' href='style.css'/>");
			out.println("</head>");
			out.println("<body>");
			request.getRequestDispatcher("todolist.html").include(request, response);
			out.println("<div class='container'>");
			out.print("<h1 style=\"text-align: center;\">"+Login.fname+" "+"View Todolist</h1>");
			out.println("<form action='complete' method='get'>");
			out.println("<table class='table table-bordered table-striped'>");
			out.print("<tr><th></th><th>Task Name</th><th>Description</th><th>Status</th><th>Edit</th><th>Delete</th>");
			
			
			for(Task bean:ToDoServlet.taskList){
				
				out.print("<tr><td><input type='checkbox' name='taskcompleted' value='"+bean.taskid+"' /></td><td>"+bean.taskname+"</td><td>"+bean.description+"</td><td>"+bean.status+"</td><td><a href='Edittodotask?id="+bean.taskid+"'>Edit</a></td><td><a href='Deletetodotask?id="+bean.taskid+"'>Delete</a></td></tr>");
				
				
			}
			out.println("</table>");
			
			out.println("<br>");
			out.println("<button type='submit' class=\"btn btn-success\">Complete Task</button>");
			
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
			out.close();
		
	}
	
	public static void CompleteUpdate() {
		
		Connection con= null;
		Statement stm=null;
		ResultSet rs= null;

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	try {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
		
		stm=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		
		rs=stm.executeQuery("select * from taskinfo");
		
		while(rs.next()) {
			
			int tid=rs.getInt("taskid");
			boolean flag=false;
			for(int i=0;i<taskcomplete.length;i++) {
				if(tid==Integer.parseInt(taskcomplete[i])) {
					flag=true;
					break;
				}
			}
			
			
			if(flag) {
				rs.updateString(5, "completed");
				rs.updateRow();
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

ToDoServlet.GetTask();

			
	}
}
