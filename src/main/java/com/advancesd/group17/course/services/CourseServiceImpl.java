package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.models.NewStudent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class CourseServiceImpl implements CourseService{

	@Override
	public List<Course> getAllCourses(CourseDao cd) {
		return cd.getAllCourses();
	}

	@Override
	public List<String> getUserRoleByBannerId(String bannerid, CourseDao cd)
	{
		return cd.getUserRoleByBannerid(bannerid);
	}

	@Override
	public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid, CourseDao cd)
	{
		return cd.getCoursesAndRolesByBannerId(bannerid);
	}

	@Override
	public boolean isAlreadyUser(String bannerid , CourseDao cd)
	{
		return cd.isAlreadyUser(bannerid);
	}

	@Override
	public String getCourseByCourseId(int courseid, CourseDao cd)
	{
		return cd.getCourseByCourseId(courseid);
	}

	@Override
	public boolean assignTa(int courseid, String bannerid, CourseDao cd)
	{
		return cd.assignTa(courseid,bannerid);
	}

	@Override
	public List<NewStudent> readFile(byte[] bytes)
	{
		List<NewStudent> newstudents = new ArrayList<>();
		try {
			ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {
				NewStudent student = new NewStudent();

				String[] fields = line.split(",");
				student.setFirstName(fields[0]);
				student.setLastName(fields[1]);
				student.setBannerId(fields[2]);
				student.setEmail(fields[3]);

				newstudents.add(student);
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return newstudents;
	}

	@Override
	public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents, CourseDao cd)
	{
		return cd.enrollStudentsToCourse(courseid,newstudents);
	}

	@Override
	public String getFileExtension(String filename)
	{
		String extension =  filename.substring(filename.lastIndexOf(".") + 1);
		return extension;
	}

	@Override
	public List<NewStudent> getNewStudents(List<NewStudent> newstudents,CourseDao cd) {
		List<NewStudent> sendmailtostudents = new ArrayList<>();

		for(NewStudent student: newstudents)
		{
			if(!cd.isAlreadyUser(student.getBannerId()))
			{
				sendmailtostudents.add(student);
			}
		}
		return sendmailtostudents;
	}

	@Override
	public boolean sendMail(String email, String bannerid, String password,String coursename) {
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

			msg.setContent("Welcome to the course of "+coursename+"\n" +
					"Your Banner ID is: "+bannerid + "\n" +
					"Your password is: "+password, "text/html");

			Transport.send(msg);
		}
		catch (MessagingException ex)
		{
			ex.printStackTrace();
		}

		return true;
	}

}
