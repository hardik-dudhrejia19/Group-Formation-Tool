package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.dao.InstructorDaoImpl;
import com.advancesd.group17.user.models.User;
import com.advancesd.group17.user.services.InstructorService;
import com.advancesd.group17.user.services.InstructorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static com.advancesd.group17.utils.Constants.*;

public class CourseServiceImpl implements CourseService {

	private static final Logger log=LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Override
	public List<Course> getAllCourses(CourseDao cd) {
		log.info("Entered CourseServiceImpl.getAllCourses");
		
		return cd.getAllCourses();
	}

	@Override
	public Course addCourse(String courseName, HashMap<String, Object> courseParameters) {
		log.info("Entered CourseServiceImpl.addCourse");
		String instructorBannerId = null;
		String courseDesc = null;
		
		if (courseParameters.containsKey(COURSE_DESC_FIELD)) {
			courseDesc = (String) courseParameters.get(COURSE_DESC_FIELD);
		}
		Integer courseCredits = Integer.parseInt(courseParameters.get(COURSE_CREDITS_FIELD).toString());
		if (courseParameters.containsKey(INSTRUCTOR_FIELD)) {
			instructorBannerId = courseParameters.get(INSTRUCTOR_FIELD).toString();
		}
		
		Course course = new Course();
		course.setCourseCredits(courseCredits);
		course.setCourseDesc(courseDesc);
		course.setCourseName(courseName);
		CourseDao courseDao = new CourseDaoImpl();
		Course addedCourse = courseDao.addNewCourse(course);
		if (addedCourse != null && instructorBannerId != null) {
			InstructorService instructorService = new InstructorServiceImpl();
			InstructorDao instructorDao = new InstructorDaoImpl();
			instructorService.addInstructor(instructorBannerId, addedCourse.getCourseId(), instructorDao);
		}
		return course;
	}
	
	@Override
	public Course courseDetails(Integer courseId) {
		log.info("Entered CourseServiceImpl.courseDetails");
		
		CourseDao courseDao = new CourseDaoImpl();
		Course course = courseDao.getCourseDetails(courseId);
		if (course != null) {
			InstructorDao instructorDao = new InstructorDaoImpl();
			InstructorService instructorService = new InstructorServiceImpl();
			User instructor = instructorService.getCourseInstructor(course.getCourseId(), instructorDao);
			course.setInstructor(instructor);
		}
		log.info("Course Details" + course);
		return course;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		log.info("Entered CourseServiceImpl.deleteCourse");
		CourseDao courseDao = new CourseDaoImpl();
		Boolean courseDeleted = courseDao.deleteCourse(courseId);
		log.info("Course Deleted" + courseDeleted);
		return courseDeleted;
	}
	
	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid, CourseDao courseDao) {
		log.info("Entered CourseServiceImpl.getCoursesAndRolesByBannerId");
		return courseDao.getCoursesAndRolesByBannerId(bannerid);
	}

	@Override
	public String getCourseByCourseId(int courseid, CourseDao courseDao) {
		log.info("Entered CourseServiceImpl.getCourseByCourseId");
		
		return courseDao.getCourseByCourseId(courseid);
	}

	@Override
	public boolean assignTa(int courseid, String bannerid, CourseDao courseDao) {
		log.info("Entered CourseServiceImpl.assignTa");
		
		return courseDao.assignTa(courseid, bannerid);
	}

	@Override
	public List<String> getUserRoleByBannerId(String bannerid, CourseDao cd) {
		return cd.getUserRoleByBannerid(bannerid);
	}

}
