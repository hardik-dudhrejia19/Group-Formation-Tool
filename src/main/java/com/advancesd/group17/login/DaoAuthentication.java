package com.advancesd.group17.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DaoAuthentication implements IDaoAuthentication{
	
	@Override
	public boolean loginAuthentication(String bid,String pass)
	{
		try
		{
			String myUrl = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_17_DEVINT";
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "CSCI5308_17_DEVINT_USER", "CSCI5308_17_DEVINT_17284");
		   
		    String query = "{CALL userauthentication(?,?)}";
		    
		    CallableStatement st = conn.prepareCall(query);
		    
		    st.setString(1, bid);
		    st.setString(2, pass);
		    
		    ResultSet rs = st.executeQuery();
		    		    
		    if(rs.next())
		    {
			    st.close();
		    	return true;
		    }
		    else
		    {
			    st.close();
		    	return false;
		    }
		   
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return false;
		}
		
	}
	
	@Override
	public Integer getuserid(String bid) {
		
		try
		{
			String myUrl = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_17_DEVINT";
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "CSCI5308_17_DEVINT_USER", "CSCI5308_17_DEVINT_17284");
		   
		    String query = "{CALL getuseridbybanner(?)}";
		    
		    CallableStatement st = conn.prepareCall(query);
		    
		    st.setString(1, bid);
		    
		    ResultSet rs = st.executeQuery();
		    
		    Integer userid = 0;
		    
		    if(rs.next())
		    {
		    	userid = rs.getInt("user_id");			    
		    }
		    
		    st.close();
		    return userid;
		    
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return 0;
		}
		
	}

}
