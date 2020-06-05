package com.advancesd.group17.user.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.mock.InstructorMockDao;

public class InstructorServiceTest {
	
	@Test
	public void addInstructorTest() {
		
		InstructorService instructorService = new InstructorServiceImpl();
		InstructorDao instructorDao = new InstructorMockDao();
		instructorService.addInstructor("B1", 1, instructorDao);
		assertEquals("B1", instructorService.getCourseInstructor(1, instructorDao).getBannerId());
		
	}
	
	@Test
	public void getCourseInstructorTest() {
		InstructorService instructorService = new InstructorServiceImpl();
		InstructorDao instructorDao = new InstructorMockDao();
		instructorService.addInstructor("B2", 1, instructorDao);
		assertEquals("B2", instructorService.getCourseInstructor(1, instructorDao).getBannerId());
	}

}
