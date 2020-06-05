package com.advancesd.group17.course.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.MockCourseDao;
import com.advancesd.group17.user.models.NewStudent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileImportServiceTest {

    static CourseDao courseDao = null;

    CourseServiceImpl cs = new CourseServiceImpl();
    FileImportServiceImpl fileService = new FileImportServiceImpl();

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @BeforeAll
    public static void setUp() {
        log.info("Setting the environment");
        courseDao = new MockCourseDao();
    }

    @AfterEach
    public void tearDown() {
        log.info("Resetting the environment");
        courseDao = new MockCourseDao();
    }

    @Test
    void enrollStudentsToCourseTest() {
        List<NewStudent> newtudents = new ArrayList<NewStudent>();
        NewStudent student = new NewStudent();
        student.setBannerId("B00555698");
        student.setFirstName("mockfname");
        student.setLastName("mocklname");
        student.setEmail("mock@gmai.com");
        newtudents.add(student);
        assertTrue(courseDao.enrollStudentsToCourse(1, newtudents));
    }

    @Test
    void getFileExtensionTest() {
        assertEquals("csv", fileService.getFileExtension("data.csv"));
        assertEquals("csv", fileService.getFileExtension("data.csv.csv"));
        assertNotEquals(".pdf", fileService.getFileExtension("courses.csv"));
        assertNotEquals("jpg", fileService.getFileExtension("file.csv"));
    }


    @Test
    void getNewStudentsTest()
    {
    }
}