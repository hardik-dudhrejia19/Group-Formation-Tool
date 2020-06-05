package com.advancesd.group17.auth.dao;

import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.user.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDaoImpl implements AuthDao{
	
	@Override
	public boolean loginAuthentication(User usr)
	{
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL userauthentication(?,?)}");	
		)
		{
			st.setString(1, usr.getBannerId());
		    st.setString(2, usr.getPassword());
		    
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
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }	
	}

	@Override
	public boolean isAlreadyUser(User u) 
	{	
		try
		(	
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL isalreadyuser(?)}");
		)    
		{
			st.setString(1, u.getBannerId());
		    
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
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }			
	}

	@Override
	public boolean registeruser(User u) 
	{
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL createuser(?,?,?,?,?)}");
		)
		{    
		    st.setString(1, u.getBannerId());
		    st.setString(2, u.getFirstName());
		    st.setString(3, u.getLastName());
		    st.setString(4, u.getEmail());
		    st.setString(5, u.getPassword());
		    
		    st.executeQuery();
		    
			st.close();
		 
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
		
		return true;
	}

	@Override
	public boolean checkuserbyemail(User u) {
		
		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL checkuserbyemail(?)}");
		)
		{    
		    st.setString(1, u.getEmail());
		    
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
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}

	@Override
	public User getusercred(String banner) {
		
	    User u = new User();

		try
		(	
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL getusercred(?)}");
		)    
		{
			st.setString(1, banner);
		    
		    ResultSet rs = st.executeQuery();
		    		    
		    if(rs.next())
		    {
		    	u.setEmail(rs.getString("user_email"));
		    	u.setPassword(rs.getString("user_password"));
			    st.close();
		    }
		    st.close();
		    return u;
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return u;
        }			
	}
}