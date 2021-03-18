package helper.db;

import java.net.SocketAddress;

import java.sql.*;
import java.util.ArrayList;

import helper.info.*;
import java.util.*;

public class MYSQLdb {
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "admin";
	private String passwd = "euiti2019";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection conn;
	
	public MYSQLdb() {
		try {
			Class.forName(this.driver).newInstance();
			this.conn = DriverManager.getConnection(this.url,this.user,this.passwd);
			System.out.println("Connected to the DB");
		}
		catch(Exception e){
			System.out.println("Exception "+ e.getMessage());
		}
	}
	
	public void setUserInfo(String email,String password,String username) {
		String query = "insert into mezutaula.users values ('" + email + "', '" + password + "', '" + username + "');";
		System.out.println("\tDB query: "+ query);
		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate("Query succesfull !!!!");
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public String getUsername(String email, String password) {
		String query = "select username from mezutaula.users where email = '" + email + "' and password = '" + password;
		System.out.println("\tDB query: "+ query);
		String username = null;
		
		try {
			Statement st = this.conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				username = rs.getString("username");
			}
			System.out.println("\tQuery succesfull !!!");
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		return username;
		
	}
	
	public void setMessageInfo(String message, String username) {
		String query = "insert into mezutaula.messages values ('0', '" + message + "', '" + username + "')";
		System.out.println("\tDB query: "+ query);
		
		try {
			Statement st = this.conn.createStatement();
			st.executeUpdate(query);
			System.out.println("\tQuery succesfull !!!");
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public ArrayList<MessageInfo> getAllMessages(){
		String query = "select * from mezutaula.messages";
		System.out.println("\tDB query: "+ query);
		ArrayList<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();
		
		try {
			Statement st = this.conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				messageInfoList.add(new MessageInfo(rs.getString("message"),rs.getString("message")));
			}
			System.out.println("\tQuery succesfull !!!");
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		return messageInfoList;
	}

}
