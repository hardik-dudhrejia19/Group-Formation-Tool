package com.advancesd.group17.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.login.model.Course;

public class DaoCourses implements IDaoCourses {

	@Override
	public String getuserrole(Integer userid) {

		String rolename = null;

		try
		{
			String myUrl = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_17_DEVINT";
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "CSCI5308_17_DEVINT_USER", "CSCI5308_17_DEVINT_17284");
			
	    	String query = "{CALL getuserrole(?)}";
	    	
		    CallableStatement st = conn.prepareCall(query);
		    
	    	st.setInt(1, userid);
	    	ResultSet rs = st.executeQuery();
	    	
	    	if(rs.next())
	    	{
		    	rolename = rs.getString("role_name");
			    st.close();
		    	return rolename;
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		return rolename;
		
	}

	@Override
	public List<Course> getallcourses() {
		
	    List<Course> crs = new ArrayList<>(); 

		try
		{
			String myUrl = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_17_DEVINT";
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "CSCI5308_17_DEVINT_USER", "CSCI5308_17_DEVINT_17284");
		   
		    String query = "{CALL getallcourses()}";
		    
		    CallableStatement st = conn.prepareCall(query);
		    
		    ResultSet rs = st.executeQuery();
		    		    		    
		    while(rs.next())
		    {
		    	Course c = new Course();
		    	c.setCoursename(rs.getString("course_name"));
		    	crs.add(c);
		    }
		    st.close();

		    return crs;
		   
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return crs;
		}
		
	}

	@Override
	public List<Course> getcoursesbyuserid(Integer userid) {
		
		List<Course> crs = new ArrayList<>(); 

		try
		{
			String myUrl = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_17_DEVINT";
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "CSCI5308_17_DEVINT_USER", "CSCI5308_17_DEVINT_17284");
		   
		    String query = "{CALL getcoursesbyuserid(?)}";
		    
		    CallableStatement st = conn.prepareCall(query);
		    
		    st.setInt(1, userid);
		    
		    ResultSet rs = st.executeQuery();
		    		    		    
		    while(rs.next())
		    {
		    	Course c = new Course();
		    	c.setCoursename(rs.getString("course_name"));
		    	crs.add(c);
		    }
		    
		    st.close();

		    return crs;
		   
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return crs;
		}
	}
}
