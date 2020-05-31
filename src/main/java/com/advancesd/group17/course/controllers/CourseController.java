package com.advancesd.group17.course.controllers;

import java.util.List;

import com.advancesd.group17.course.models.CourseAndRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;

@Controller
public class CourseController {

	@GetMapping("/home")
	public String homepage(@RequestParam("bannerid") String bannerid, Model model)
	{
		boolean isStudent = false;
		boolean isTAInstructor = false;
		boolean isGuest = false;

		CourseDao cd = new CourseDaoImpl();
		CourseService lc = new CourseService();

		List<CourseAndRole> listofcourses = lc.getcoursesandrolesbybannerid(bannerid, cd);
		List<String> userroles = lc.getuserrolebybannerid(bannerid,cd);

		for(String roles: userroles)
		{
			if(roles.equals("Student"))
			{
				isStudent = true;
			}
			else if(roles.equals("TA") || roles.equals("Instructor"))
			{
				isTAInstructor = true;
			}
		}
		
		model.addAttribute("coursesandroles", listofcourses);
		model.addAttribute("bannerid", bannerid);
		model.addAttribute("isStudent",isStudent);
		model.addAttribute("isTAInstructor",isTAInstructor);
		
		return "Home";
	}

	@GetMapping("/home/description")
	public String infopage(@RequestParam("course") String course,
						   @RequestParam("role") String role,
						   Model model)
	{
		model.addAttribute("coursename", course);
		model.addAttribute("role",role);
		return "coursedescription";
	}

}
