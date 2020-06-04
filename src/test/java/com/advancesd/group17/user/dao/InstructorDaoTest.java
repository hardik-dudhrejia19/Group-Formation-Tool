package com.advancesd.group17.user.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.user.mock.InstructorMockDao;

public class InstructorDaoTest {

	static InstructorDao instrucorDao = null;
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

	@BeforeAll
	public static void setUp() {
		log.info("Setting the environment");
		instrucorDao = new InstructorMockDao();
	}
	
	@Test
	void assignInstructorTest() {
		instrucorDao.assignInstructor("B1", 1);
		assertEquals("B1", instrucorDao.getCourseInstructor(1).getBannerId()); 
	}
	
	@Test 
	void getCourseInstructorTest() {
		instrucorDao.assignInstructor("B1", 1 );
		assertEquals("B1", instrucorDao.getCourseInstructor(1).getBannerId());
	}

}
