package com.advancesd.group17.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancesd.group17.user.mock.UserMockDao;

public class UserDaoTest {

	static UserDao userDao = null;
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

	@BeforeAll
	public static void setUp() {
		log.info("Setting the environment");
		userDao = new UserMockDao();
	}

	@Test
	void getUserRoleByBannerIdCase1Test() {
		log.info("Running getUserRoleByBannerIdCase1Test");
		List<String> roles = new ArrayList<>();
		roles.add("TA");
		assertEquals(roles, userDao.getUserRoleByBannerId("B00836202"));
	}

	@Test
	void getUserRoleByBannerIdCase2Test() {
		log.info("Running getUserRoleByBannerIdCase2Test");
		List<String> roles = new ArrayList<>();
		roles.add("TA");
		assertNotEquals("Guest", userDao.getUserRoleByBannerId("B00000000"));
	}

	@Test
	void isAlreadyUserCase1Test() {
		log.info("Running isAlreadyUserCase1Test");
		assertTrue(userDao.isAlreadyUser("B00835071"));
	}

	@Test
	void isAlreadyUserCase2Test() {
		log.info("Running isAlreadyUserCase1Test");
		assertFalse(userDao.isAlreadyUser("B99999999"));
	}

}
