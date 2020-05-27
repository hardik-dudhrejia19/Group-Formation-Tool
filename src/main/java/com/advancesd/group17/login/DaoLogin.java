package com.advancesd.group17.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DaoLogin implements IDaoLogin{
	
	@Override
	public String loginAuthentication(String bid,String pass)
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
		    
		    while(rs.next())
			{
		    	Integer userid = rs.getInt("user_id");
		    	
		    	String query2 = "{CALL getuserrole(?)}";
		    	st = conn.prepareCall(query2);
			    
		    	st.setInt(1, userid);
		    	ResultSet rs2 = st.executeQuery();
		    	
		    	while(rs2.next())
		    	{
			    	Integer roleid = rs2.getInt("role_id");
			    			    	
			    	if(roleid==1)
			    	{
			    		return "Admin";
			    	}
			    	else
					{
			    		return "Home";
					}
		    	}
			}

		    st.close();
		 
		    return "login";
		   
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return "login";
		}
		
	}
}
