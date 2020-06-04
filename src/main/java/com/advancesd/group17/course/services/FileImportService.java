package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.models.NewStudent;

import java.util.List;

public interface FileImportService {
    public List<NewStudent> readFile(byte[] bytes);

    public boolean enrollStudentsToCourse(int courseid, List<NewStudent> newstudents, CourseDao cd);

    public String getFileExtension(String filename);

    public boolean sendMail(String email, String bannerid, String password,String coursename) ;

    List<NewStudent> getNewStudents(List<NewStudent> newstudents, UserDao ud);
}
