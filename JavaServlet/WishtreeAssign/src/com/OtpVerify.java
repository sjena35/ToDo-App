package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/verification")
public class OtpVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static int otp;
	
	public static void getOtp(int i) {
		otp=i;
	}
       
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
	   PrintWriter out= response.getWriter();
	   String uotp=request.getParameter("otp");
	   if(otp==Integer.parseInt(uotp)) {
		   Signup.insert();
		   response.sendRedirect("index.html");
	   }
	   
	   else {
		   response.sendRedirect("otpwrong.html");
	   }
   }

}
