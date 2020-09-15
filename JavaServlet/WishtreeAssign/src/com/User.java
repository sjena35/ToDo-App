package com;

public class User {
	
	int uid;
	String fname;
	String lname;
	String pass;
	String uemail;
	String gender;
	String status;
	public User(int uid, String fname, String lname,String uemail, String pass, String gender, String status) {
		
		this.status=status;
		this.uemail=uemail;
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.pass = pass;
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", lname=" + lname + ", pass=" + pass + ", gender=" + gender
				+ "]";
	}
	
	
	
	
	
	
	

}
