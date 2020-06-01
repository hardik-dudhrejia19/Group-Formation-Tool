package com.advancesd.group17.course.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.database.DatabaseConfig;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<String> getuserrolebybannerid(String bannerid) {

		List<String> rolename = new ArrayList<>();

		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL getuserrolebybannerid(?)}");
		)
		{
			st.setString(1, bannerid);
	    	ResultSet rs = st.executeQuery();
	    	
	    	while(rs.next())
	    	{
		    	rolename.add(rs.getString("role_name"));
			}
	    	st.close();
	    	conn.close();
		}
		catch (SQLException ex) {
            ex.printStackTrace();
        }


		return rolename;
	}

	@Override
	public List<Course> getallcourses() {
		
	    List<Course> crs = new ArrayList<>();

		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL getallcourses()}");
		)
		{
		    ResultSet rs = st.executeQuery();

		    while(rs.next())
		    {
		    	Course c = new Course();
		    	c.setCoursename(rs.getString("course_name"));
		    	crs.add(c);
		    }
		    st.close();
		    conn.close();

		    return crs;

		}
		catch (SQLException ex) {
            ex.printStackTrace();
        }

		return crs;
	}

	@Override
	public List<Course> getcoursesbybannerid(String bannerid) {
		
		List<Course> crs = new ArrayList<>();

		try
		(
			Connection conn = DatabaseConfig.getInstance().getConnection();
		    CallableStatement st = conn.prepareCall("{CALL getcoursesbybannerid(?)}");
		)
		{
		    st.setString(1, bannerid);

		    ResultSet rs = st.executeQuery();

		    while(rs.next())
		    {
		    	Course c = new Course();
		    	c.setCoursename(rs.getString("course_name"));
		    	crs.add(c);
		    }

		    st.close();
		    conn.close();

		    return crs;

		}
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return crs;
	}

	@Override
	public List<CourseAndRole> getcoursesandrolesbybannerid(String bannerid) {

		List<CourseAndRole> crsrole = new ArrayList<>();

		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL getcoursesandrolesbybannerid(?)}");
			statement.setString(1,bannerid);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				CourseAndRole cr = new CourseAndRole();
				cr.setCourseid(rs.getInt(1));
				cr.setCoursename(rs.getString(2));
				cr.setRole(rs.getString(3));
				crsrole.add(cr);
			}
			statement.close();
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return crsrole;
	}



	@Override
	public boolean isalreadyuser(String bannerid)
	{
		boolean isalreadyuser = false;
		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL isalreadyuser(?)}");
			statement.setString(1, bannerid);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				isalreadyuser = true;
			}
			statement.close();
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return isalreadyuser;
	}

	@Override
	public String getcoursebycourseid(int courseid)
	{
		String coursename="";
		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL getcoursenamebycourseid(?)}");
			statement.setInt(1,courseid);
			ResultSet rs = statement.executeQuery();
			rs.next();
			coursename = rs.getString(1);
			statement.close();
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return coursename;
	}
}
