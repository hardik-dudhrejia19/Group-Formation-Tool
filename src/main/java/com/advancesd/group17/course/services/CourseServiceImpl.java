package com.advancesd.group17.course.services;

import static com.advancesd.group17.utils.Constants.COURSE_CREDITS_FIELD;
import static com.advancesd.group17.utils.Constants.COURSE_DESC_FIELD;
import static com.advancesd.group17.utils.Constants.INSTRUCTOR_FIELD;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.dao.InstructorDaoImpl;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.models.NewStudent;
import com.advancesd.group17.user.models.User;
import com.advancesd.group17.user.services.InstructorService;
import com.advancesd.group17.user.services.InstructorServiceImpl;

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
	public List<NewStudent> readFile(byte[] bytes) {
		log.info("Entered CourseServiceImpl.readFile");
		
		List<NewStudent> newstudents = new ArrayList<>();
		try {
			ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {
				
				NewStudent student = new NewStudent();
	
					String[] fields = line.split(",");

					if(fields.length!=0)
					{
						student.setFirstName(fields[0]);
						student.setLastName(fields[1]);
						student.setBannerId(fields[2]);
						student.setEmail(fields[3]);
		
						newstudents.add(student);
					}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		log.info("Exiting CourseServiceImpl.readFile");
		return newstudents;
	}

	@Override
	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents, CourseDao cd) {
		return cd.enrollStudentsToCourse(courseid, newstudents);
	}

	@Override
	public String getFileExtension(String filename) {
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		return extension;
	}

	@Override
	public boolean sendMail(String email, String bannerid, String password, String coursename) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("advsdcgrp17@gmail.com", "grp17@2020");
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("advsdcgrp17@gmail.com", false));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Credentials Details");

			msg.setContent("Welcome to the course of " + coursename + "\n" + "Your Banner ID is: " + bannerid + "\n"
					+ "Your password is: " + password, "text/html");

			Transport.send(msg);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

		return true;
	}
	
	@Override
	public List<NewStudent> getNewStudents(List<NewStudent> newstudents, UserDao ud) {
		List<NewStudent> sendmailtostudents = new ArrayList<>();

		for (NewStudent student : newstudents) {
			if (!ud.isAlreadyUser(student.getBannerId()) && !ud.isEmailExist(student.getEmail())) {
				sendmailtostudents.add(student);
			}
		}
		return sendmailtostudents;
	}

	@Override
	public List<String> getUserRoleByBannerId(String bannerid, CourseDao cd) {
		return cd.getUserRoleByBannerid(bannerid);
	}

}
