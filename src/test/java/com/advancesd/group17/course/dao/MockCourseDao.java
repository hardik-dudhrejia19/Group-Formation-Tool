package com.advancesd.group17.course.dao;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.models.NewStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockCourseDao implements CourseDao {

	private static final Logger log = LoggerFactory.getLogger(MockCourseDao.class);
	List<Course> courseList;

	public MockCourseDao() {
		courseList = new ArrayList<Course>();

		Course course = new Course();
		course.setCourseName("Adv SDC");
		course.setCourseId(1);
		course.setCourseCredits(3);
		courseList.add(course);

		Course course2 = new Course();
		course2.setCourseName("Adv Web");
		course2.setCourseId(2);
		course2.setCourseCredits(3);
		courseList.add(course2);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseList;
	}

	@Override
	public Course addNewCourse(Course course) {
		courseList.add(course);
		return course;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		int initialCourseListSize = courseList.size();
		courseList = courseList.stream().filter(course -> course.getCourseId() != courseId)
				.collect(Collectors.toList());

		return courseList.size() != initialCourseListSize ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public Course getCourseDetails(Integer courseId) {
		log.info("Course list size " + courseList.size() + "course Id" + courseId);
		List<Course> filteredCourseList = courseList.stream().filter(course -> course.getCourseId() == courseId)
				.collect(Collectors.toList());

		if (CollectionUtils.isEmpty(filteredCourseList)) {
			log.info("Course List empty");
			return null;
		}
		return filteredCourseList.get(0);
	}

	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid) {
		List<CourseAndRole> crs = new ArrayList<>();
		if ("B00835071".equals(bannerid)) {
			CourseAndRole cr = new CourseAndRole();
			cr.setCourseid(1);
			cr.setCoursename("Advance SDC");
			cr.setRole("Student");
			crs.add(cr);
		}
		return crs;
	}

	@Override
	public String getCourseByCourseId(int courseid) {
		String coursename = "";
		if (courseid == 1) {
			coursename = "Advance SDC";
		}
		if (courseid == 3) {
			coursename = "Machine Learning";
		}
		return coursename;
	}

	@Override
	public boolean assignTa(int courseid, String bannerid) {
		boolean assign = false;
		if (courseid == 1 & bannerid.equals("B00835071")) {
			assign = false;
		}

		if (courseid == 3 & bannerid.equals("B00835071")) {
			assign = true;
		}
		return assign;
	}

	@Override
	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents) {
		if (courseid == 1 && newstudents.get(0).getBannerId().equals("B00555698")) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getUserRoleByBannerid(String bannerid) {
		List<String> list = new ArrayList<>();
		if ("B00836202".equals(bannerid)) {
			list.add("TA");
			return list;
		}
		if ("B00000000".equals(bannerid)) {
			list.add("Guest");
			return list;
		}
		return null;
	}

}
