package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   static String tname;
   static String tdesc;
   static String status;
   static int uid;
   
  static ArrayList<Task> taskList=new ArrayList<>();
  static ArrayList<User> userList= new ArrayList<>();
   
   public static void setUid(int i) {
	   uid=i;
   }
   
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
	   PrintWriter out=response.getWriter();
	   
	   tname=request.getParameter("tname");
	   tdesc=request.getParameter("desc");
	   status="Incomplete";
	   
	   if(!(tname.equals(null) || tname.equals(""))) {
	   TaskInsert();
	   }
	   GetTask();
	   int cnt=1;
	   
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
		
		
		for(Task bean:taskList){
			
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


	   
   
   
   public static void TaskInsert() {
	   Connection con=null;
	   PreparedStatement stm= null;
	  
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
			
			stm=con.prepareStatement("insert into taskinfo values(?,?,?,?,?,?) ");
			int tid=0;
			Statement stm1=con.createStatement();
			ResultSet rs=stm1.executeQuery("select max(taskid) from taskinfo");
			while(rs.next()) {
				tid=rs.getInt(1)+1;
			}
			
			Statement stm2=con.createStatement();
			ResultSet rs2=stm2.executeQuery("select curdate()");
			
			while(rs2.next()) {
				System.out.println(rs2.getDate(1));
				Date d=rs2.getDate(1);
				stm.setDate(6, d);
			}

			
			stm.setInt(1, tid);
			stm.setInt(2, uid);
			stm.setString(3,tname);
			stm.setString(4, tdesc);
			stm.setString(5, status);
			stm.executeUpdate();
			
		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   }
   
   public static void GetTask() {
	   taskList=new ArrayList<>();
	   Connection con=null;
	   Statement stm=null;
	   ResultSet rs= null;
	   
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
			
			stm=con.createStatement();
			rs=stm.executeQuery("select * from taskinfo");
			
			while(rs.next()) {
				if(uid==rs.getInt("userid")) {
				taskList.add(new Task(rs.getInt("taskid"),rs.getString("taskname"),rs.getString("description"), rs.getString("status")));
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
