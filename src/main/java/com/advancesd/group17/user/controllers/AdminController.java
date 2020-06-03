package com.advancesd.group17.user.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.dao.InstructorDaoImpl;
import com.advancesd.group17.user.models.User;
import com.advancesd.group17.user.services.AdminService;
import com.advancesd.group17.user.services.InstructorService;
import com.advancesd.group17.user.services.InstructorServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/home")
	public String adminHomePage(Model model) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Entered AdminController.adminHomePage with model " + gson.toJson(model));
		adminService.createHomePage(model);
		log.info("Exiting from AdminController.adminHomePage");
		return "adminHomePage";
		
	}
	
	@GetMapping("/addCourse")
	public String addCourseDetailsPage(@RequestParam(required = true) String courseName, Model model) {
		log.info("Entered AdminController.addCourseDetailsPage");
		model.addAttribute("courseName", courseName);
		model.addAttribute("course", new Course());
		
		InstructorDao instructordao = new InstructorDaoImpl();
		InstructorService instructorservice = new InstructorServiceImpl();
				
		List<User> userlist = instructorservice.listUsersforinstructor(instructordao);
		
		model.addAttribute("userlistforinstructor", userlist);
		
		log.info("Exiting from AdminController.addCourseDetailsPage");
		
		return "courseInstructor";
	}	

}
