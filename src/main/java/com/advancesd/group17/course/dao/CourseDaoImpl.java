package com.advancesd.group17.course.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.database.DatabaseConfig;

public class CourseDaoImpl implements CourseDao {

	@Override
	public String getuserrolebybannerid(String bannerid) {

		String rolename = null;

		try
		{
			Connection conn = DatabaseConfig.getInstance().getConnection();
			
	    	String query = "{CALL getuserrolebybannerid(?)}";
	    	
		    CallableStatement st = conn.prepareCall(query);
		    
	    	st.setString(1, bannerid);
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
			Connection conn = DatabaseConfig.getInstance().getConnection();
		   
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
			
		}
		return crs;
	}

	@Override
	public List<Course> getcoursesbybannerid(String bannerid) {
		
		List<Course> crs = new ArrayList<>(); 

		try
		{
			Connection conn = DatabaseConfig.getInstance().getConnection();
		   
		    String query = "{CALL getcoursesbybannerid(?)}";
		    
		    CallableStatement st = conn.prepareCall(query);
		    
		    st.setString(1, bannerid);
		    
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
		}
		
		return crs;
	}
}
