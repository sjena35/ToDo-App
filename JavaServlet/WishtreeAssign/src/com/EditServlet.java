package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Edittodotask")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String tname;
	static String desc;
	static int id;
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		id=Integer.parseInt(request.getParameter("id"));
		EditTask.getTid(id);
		Search();
		
		response.setContentType("text/html");
		   out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>View List</title>");
			out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
			out.println("<link rel='stylesheet' href='style.css'/>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\" style=\"padding: 50px\">\r\n" + 
					"<div class=\"row\">\r\n" + 
					"<div class=\"col-sm-4\"></div>\r\n" + 
					"<div class=\"col-sm-3\">\r\n" + 
					"<form action=\"edittask\" method=\"get\">\r\n" + 
					"TaskName :<input type=\"text\" name=\"tname\" style=\"display: block\" value='"+tname+"'><br>\r\n" + 
					"Description :<input type=\"text\" name=\"desc\" style=\"display: block\" value='"+desc+"'><br>\r\n" + 
					"<button type=\"submit\" class=\"btn btn-primary btn-block\">Update</button>\r\n" + 
					"</form>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"</body>\r\n" + 
					"</html>");
		
		
		
		
   }
	
	public static void Search() {
		
		for(int i=0;i<ToDoServlet.taskList.size();i++) {
			if(ToDoServlet.taskList.get(i).taskid==id) {
				tname=ToDoServlet.taskList.get(i).taskname;
				desc=ToDoServlet.taskList.get(i).description;
			}
		}
	}
}
