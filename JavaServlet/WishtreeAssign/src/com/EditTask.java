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


@WebServlet("/edittask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String editname;
	static String editdesc;
	static int tid;
	
	public static void getTid(int x) {
		tid=x;
	}
       
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	
    	PrintWriter out= response.getWriter();
    	
    	editname=request.getParameter("tname");
    	editdesc=request.getParameter("desc");
    	
    	Update();
    	
    	response.sendRedirect("todolist.html");
    	
    }
    
    
    public static void Update() {
    	
    	Connection con=null;
 	   Statement stm=null;
 	   ResultSet rs= null;
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
			
				stm=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
				
				rs=stm.executeQuery("select * from taskinfo");
				
				while(rs.next()) {
					if(tid==rs.getInt(1)) {
						rs.updateString(3, editname);
						rs.updateString(4, editdesc);
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
    }
}
