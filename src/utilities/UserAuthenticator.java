package utilities;

import java.sql.*;

public class UserAuthenticator {
	private String url = "jdbc:mysql://127.0.0.1"; // MySQL server address
	private String driver = "com.mysql.jdbc.Driver";
	
	private String username = "";
	private String password = "";
	private String dbName = "";
	
	private String error_msg;
	
	private boolean loggedIn = false;
	private String userID;
	
	public boolean login(String inUser, String inPass) throws Exception
	{
		Class.forName(driver).newInstance();
		
		Connection dbConn = DriverManager.getConnection(url + dbName, username, password);
		
		Statement st = dbConn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS loginCount, username, password, id FROM `accounts` "
										+ "WHERE username='"+inUser+"' "
												+ "AND password='"+inPass+"'");
		
		if(rs.next() && rs.getInt("loginCount") == 1)
		{
			loggedIn = true;
			userID = "USER_ID:"+rs.getInt("id");
			
			return true;
			
		}
		else
		{
			error_msg = "Wrong username and/or password";
			return false;
		}
	}
	
	public String getErrorMsg()
	{
		return error_msg;
	}
	
	public boolean isLoggedIn()
	{
		return loggedIn;
	}
	
	public String getUserID()
	{
		return userID;
	}
	
	
}
