package com.advancesd.group17.course.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.database.DatabaseConfig;
import com.advancesd.group17.user.models.NewStudent;

public class CourseDaoImpl implements CourseDao {
	
	public static Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Override
	public List<Course> getAllCourses() {
		log.info("Entered CourseDaoImpl.getAllCourses");

		Connection connection = null;
		List<Course> courseList = new ArrayList<Course>();
		try {
			connection = createDbConnection();
			CallableStatement stmt = connection.prepareCall("{CALL getallcourses}");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.info("Course: " + rs.getString("course_name"));
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseDesc(rs.getString("course_desc"));
				course.setCourseCredits(rs.getInt("course_credits"));
				courseList.add(course);
			}
			connection.close();
			if (CollectionUtils.isEmpty(courseList)) {
				return null;
			}
			return courseList;

		} catch (Exception e) {
			log.error("Error occured " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid) {

		List<CourseAndRole> crsrole = new ArrayList<>();

		try
		{
			Connection connection = createDbConnection();
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
	public Course addNewCourse(Course course) {
		Connection connection = null;

		try {
			connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			CallableStatement stmt = connection.prepareCall("{CALL createcourse(?, ?, ?)}");
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getCourseDesc());
			stmt.setInt(3, course.getCourseCredits());

			ResultSet rs = stmt.executeQuery();
			
			stmt = connection.prepareCall("{CALL getcoursebyname(?)}");
			stmt.setString(1, course.getCourseName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				Course addedCourse = new Course();
				addedCourse.setCourseId(rs.getInt("course_id"));
				addedCourse.setCourseName(rs.getString("course_name"));
				addedCourse.setCourseCredits(rs.getInt("course_credits"));
				addedCourse.setCourseDesc(rs.getString("course_desc"));
				connection.close();
				return addedCourse;

			} else {
				connection.close();
				return null;
			}
			
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Boolean deleteCourse(Integer courseId) {
		Connection connection = null;

		try {

			connection = createDbConnection();
			if (connection == null) {
				return Boolean.FALSE;
			}
			CallableStatement stmt = connection.prepareCall("{CALL deletecoursebyid(?)}");
			stmt.setInt(1, courseId);
			stmt.execute();
			stmt.executeQuery();
			connection.close();
			return Boolean.TRUE;

		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	@Override
	public Course getCourseDetails(Integer courseId) {
		log.info("Entered CourseDaoImpl.getCourseDetails with courseId: " + courseId);
		try {
			Connection connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			Course course = null;
			CallableStatement stmt = connection.prepareCall("{CALL getcoursedetailbyid(?)}");
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				log.info("Course details found");
				course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseCredits(rs.getInt("course_credits"));
				course.setCourseDesc(rs.getString("course_desc"));
			} else {
				log.info("Course details are null");
			}
			connection.close();
			return course;

		} catch (Exception e) {

		}
		return null;
	}
	

	@Override
	public String getCourseByCourseId(int courseId)
	{
		Course course = getCourseDetails(courseId);
		if (course == null) {
			return  null;
		} else {
			return course.getCourseName();
		}
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
	
	
	Connection createDbConnection() {
		Connection connection = null;
		try {
			connection = DatabaseConfig.getInstance().getConnection();
			if (connection == null) {
				log.info("Connection null");
			} else {
				log.info("Connection established");
			}
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return connection;

	}

}