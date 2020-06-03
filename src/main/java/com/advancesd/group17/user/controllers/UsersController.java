package com.advancesd.group17.user.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.services.CourseServiceImpl;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.dao.UserDaoImpl;

@Controller
public class UsersController {

	@GetMapping("/home")
	public String homepage(@RequestParam("bannerid") String bannerid, Model model) {
		boolean isStudent = false;
		boolean isTA = false;
		boolean isGuest = false;
		boolean isInstructor = false;

		UserDao userDao = new UserDaoImpl();
		CourseDao courseDao = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();

		List<CourseAndRole> coursesandroles = new ArrayList<>();
		List<Course> allcourses = new ArrayList<>();
		List<String> userroles = userDao.getUserRoleByBannerId(bannerid);
		coursesandroles = lc.getCoursesAndRolesByBannerId(bannerid, courseDao);

		for (String role : userroles) {
			if (role.equals("Student")) {
				isStudent = true;
			} else if (role.equals("TA")) {
				isTA = true;
			} else if (role.equals("Instructor")) {
				isInstructor = true;
			} else if (role.equals("Guest")) {
				isGuest = true;
				allcourses = lc.getAllCourses(courseDao);
			}
		}

		model.addAttribute("coursesandroles", coursesandroles);
		model.addAttribute("allcourses", allcourses);
		model.addAttribute("bannerid", bannerid);
		model.addAttribute("isStudent", isStudent);
		model.addAttribute("isTA", isTA);
		model.addAttribute("isGuest", isGuest);
		model.addAttribute("isInstructor", isInstructor);

		return "Home";
	}
}
