package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminlogout")
public class AdminLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
	   PrintWriter out= response.getWriter();
	   
	   response.sendRedirect("index.html");
   }
}
