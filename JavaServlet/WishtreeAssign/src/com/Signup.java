package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String fname;
	static String lname;
	static String uemail;
	static String upassword;
	static String gender;
	static String uaccess="nonadmin";
	static String cupass;
	
	
	
	static boolean flagcheck=false;
       
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	PrintWriter out1=response.getWriter();
    	fname= request.getParameter("fname");
    	lname= request.getParameter("lname");
    	uemail=request.getParameter("email");
    	upassword=request.getParameter("pwd");
    	gender=request.getParameter("gender");
    	cupass = request.getParameter("pass");
    	boolean flag=false;
    	checkEmail(uemail);
    	
    	if(upassword.equals(cupass)) {
    		flag=true;
    	}
    	
//    	if(flagcheck==true) {
//    		response.sendRedirect("alreadyExist.html");
//
//    	}
//    	else {
    	
    	if(flag==true) {
    		if(flagcheck==false) {
    		int r= new Random().nextInt(999999);
    		OtpVerify.getOtp(r);
    		EmailNotifiaction.sendbday(uemail, String.valueOf(r));
    		
    		response.sendRedirect("otpverification.html");
    		}
    		else {
    			response.sendRedirect("alreadyExist.html");
    		}
    	
//    	insert();
//    	
//    	response.sendRedirect("index.html");
    	}
    	
    	else {
    		response.sendRedirect("samePass.html");
    	}
    //	}
    	

    }
    
    public static void insert() {
    	
    	Connection con=null;
    	PreparedStatement stm=null;
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
				stm=con.prepareStatement("insert into userinfo values(?,?,?,?,?,?,?,?,?)");
				stm.setString(2,fname);
				stm.setString(3,lname);
				PreparedStatement stm1=con.prepareStatement("select max(userid) from userinfo");
				ResultSet rs=stm1.executeQuery();
				int s=0;
				while(rs.next()) {
					s=rs.getInt(1)+1;
				}
				
				
				Statement stm2=con.createStatement();
				ResultSet rs2=stm2.executeQuery("select curdate()");
				
				while(rs2.next()) {
					System.out.println(rs2.getDate(1));
					Date d=rs2.getDate(1);
					stm.setDate(9, d);
				}
				
				
				stm.setInt(1, s);
				stm.setString(4,uemail);
				stm.setString(5, upassword);
				stm.setString(6, gender);
				stm.setString(7, uaccess);
				stm.setString(8, "active");
				
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
    
    public static void checkEmail(String uemail1) {
    	Connection con= null;
    	PreparedStatement stm= null;
    	ResultSet rs= null;
    	System.out.println("Inside2");
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdatabase","root","root");
				
				stm=con.prepareStatement("select * from userinfo where useremail=?");
				stm.setString(1, uemail1);
				rs=stm.executeQuery();
				if(rs.next()) {
					flagcheck=true;
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
//    	
}
