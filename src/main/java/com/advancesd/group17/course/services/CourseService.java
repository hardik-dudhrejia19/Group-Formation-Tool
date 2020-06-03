package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.models.NewStudent;

import java.util.HashMap;
import java.util.List;

public interface CourseService {

    public List<Course> getAllCourses(CourseDao cd);
    
    public Boolean deleteCourse(Integer id);
    
    public Course courseDetails(Integer courseId);
    
    public Course addCourse(String courseName, HashMap<String, Object> courseParameters);

    public List<CourseAndRole> getCoursesAndRolesByBannerId(String bannerid, CourseDao cd);

    public String getCourseByCourseId(int courseid, CourseDao cd);

    public boolean assignTa(int courseid, String bannerid, CourseDao cd);

    public List<NewStudent> readFile(byte[] bytes);

    public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents, CourseDao cd);

    public String getFileExtension(String filename);

    public boolean sendMail(String email, String bannerid, String password,String coursename) ;
    
	List<NewStudent> getNewStudents(List<NewStudent> newstudents, UserDao ud);


}
