package com.advancesd.group17.course.dao;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.models.NewStudent;
import com.advancesd.group17.database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getAllCourses() {
		List<Course> allcourses = new ArrayList<>();
		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL getallcourses()}");
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Course course = new Course();
				course.setId(rs.getInt(1));
				course.setCoursename(rs.getString(2));
				course.setCoursedescription(rs.getString(3));
				course.setCredits(rs.getInt(4));
				allcourses.add(course);
			}
			statement.close();
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return null;
		}
		return allcourses;
	}

	@Override
	public List<String> getUserRoleByBannerid(String bannerid) {

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
		}
		catch (SQLException ex) {
            ex.printStackTrace();
        }
		return rolename;
	}

	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid) {

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
	public boolean isAlreadyUser(String bannerid)
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
			return false;
		}
		return isalreadyuser;
	}

	@Override
	public String getCourseByCourseId(int courseid)
	{
		String coursename = "";
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

	@Override
	public boolean assignTa(int courseid, String bannerid) {
		int rowsaffected = 0;
		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL assignTA(?,?)}");
			statement.setInt(1,courseid);
			statement.setString(2,bannerid);
			rowsaffected = statement.executeUpdate();
			statement.close();
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
		if(rowsaffected == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents) {
		try
		{
			Connection connection = DatabaseConfig.getInstance().getConnection();
			CallableStatement statement = connection.prepareCall("{CALL enrollusertocourse(?,?,?,?,?)}");

			for(NewStudent student: newstudents)
			{
				statement.setInt(1,courseid);
				statement.setString(2,student.getFirstName());
				statement.setString(3,student.getLastName());
				statement.setString(4,student.getBannerId());
				statement.setString(5,student.getEmail());
				statement.executeQuery();
			}
			connection.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}

}