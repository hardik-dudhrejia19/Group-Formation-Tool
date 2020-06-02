package com.advancesd.group17.course.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.model.Course;
import com.advancesd.group17.database.DatabaseConfig;

public class CourseDaoImpl implements CourseDao{
	
	public static Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Override
	public List<Course> getAllCourses() {
		Connection connection = null;
		List<Course> courseList = new ArrayList<Course>();
		try {
			connection = createDbConnection();
			CallableStatement stmt = connection.prepareCall("{CALL getallcourses}");
			ResultSet rs = stmt.executeQuery();
			log.info("" + rs);
			while(rs.next()) {
				Course course = new Course(rs.getInt("course_id"), rs.getString("course_name"));
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
	public Course addNewCourse(Course course) {
		Connection connection = null;
		
		try {
			connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			CallableStatement stmt = connection.prepareCall("{CALL createcourse(?, ?, ?)}");
			stmt.setString(1, course.getCourseName());
			stmt.setString(2,  course.getCourseDesc());
			stmt.setInt(3,  course.getCourseCredits());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Course addedCourse = new Course();
				addedCourse.setCourseId(rs.getInt("course_id"));
				addedCourse.setCourseName(rs.getString("course_name"));
				addedCourse.setCourseCredits(rs.getInt("course_credits"));
				addedCourse.setCourseDesc(rs.getString("course_desc"));
				return addedCourse;
			}
			connection.close();
			return null;
			
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
			
			ResultSet resultSet = stmt.executeQuery();
			connection.close();
			if (resultSet.next()) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
			
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return Boolean.FALSE;
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

	@Override
	public Course getCourseDetails(Integer courseId) {
		try {
			Connection connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			CallableStatement stmt = connection.prepareCall("{CALL getcoursedetailbyid(?)}");
			stmt.setInt(1, courseId);
			connection.close();
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseCredits(rs.getInt("course_credits"));
				course.setCourseDesc(rs.getString("course_desc"));
				return course;
			}
			
		} catch (Exception e) {
			
		}
		return null;
	}
	

}
