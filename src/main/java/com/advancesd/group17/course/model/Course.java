package com.advancesd.group17.course.model;

import java.util.List;

public class Course {
	
	private int courseId;
	private String courseName;
	private String courseDesc;
	private Integer courseCredits;
	private List<Integer> instructors;
	
	public Course() {
		super();
	}

	public Course(int courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public Course(int courseId, String courseName, String courseDesc, Integer courseCredits, List<Integer> instructor) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.courseCredits = courseCredits;
		this.instructors = instructor;
		
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public Integer getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(Integer courseCredits) {
		this.courseCredits = courseCredits;
	}

	public List<Integer> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Integer> instructors) {
		this.instructors = instructors;
	}

}
