package com.advancesd.group17.auth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.database.DatabaseConfig;

public class UserDaoImpl implements UserDao{
	
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
			    conn.close();
		    	return false;
		    }
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }	
	}

	@Override
	public boolean isalreadyuser(User u) 
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
			    conn.close();
		    	return true;
		    }
		    else
		    {
			    st.close();
			    conn.close();
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
			conn.close();
		 
		}
		catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

		return true;
	}

}