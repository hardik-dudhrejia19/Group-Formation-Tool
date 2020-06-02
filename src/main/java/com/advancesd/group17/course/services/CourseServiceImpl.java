package com.advancesd.group17.course.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.model.Course;

import static com.advancesd.group17.utils.Constants.COURSE_DESC_FIELD;
import static com.advancesd.group17.utils.Constants.COURSE_CREDITS_FIELD;

@Service
public class CourseServiceImpl implements CourseService{
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CourseServiceImpl.class);

	@Override
	public Course addCourse(String courseName, HashMap<String, Object> courseParameters) {
		String courseDesc = (String) courseParameters.get(COURSE_DESC_FIELD);
		Integer courseCredits = Integer.parseInt(courseParameters.get(COURSE_CREDITS_FIELD).toString());
		
		Course course = new Course();
		course.setCourseCredits(courseCredits);
		course.setCourseDesc(courseDesc);
		course.setCourseName(courseName);
		CourseDao courseDao = new CourseDaoImpl();
		courseDao.addNewCourse(course);
		return course;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		CourseDao courseDao = new CourseDaoImpl();
		Boolean courseDeleted = courseDao.deleteCourse(courseId);
		LOGGER.info("Course Deleted" + courseDeleted);
		return courseDeleted;
	}

	@Override
	public List<Course> listOfCourses() {
		
		CourseDao courseDao = new CourseDaoImpl();
		
		return courseDao.getAllCourses();
	}

	@Override
	public Course courseDetails(Integer courseId) {
		CourseDao courseDao = new CourseDaoImpl();
		Course course = courseDao.getCourseDetails(courseId);
		LOGGER.info("Course Details" + course);
		return course;
	}

}
