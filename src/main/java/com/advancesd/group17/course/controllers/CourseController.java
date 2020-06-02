package com.advancesd.group17.course.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.model.Course;
import com.advancesd.group17.course.services.CourseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static com.advancesd.group17.utils.Constants.REDIRECT;
import static com.advancesd.group17.utils.Constants.ADMIN_HOME_PAGE;


@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		log.info("Entered CourseController.deleteCourse");
		
		courseService.deleteCourse(id);
		
		log.info("Exiting CourseController.deleteCourse");
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/courselist")
	public List<Course> courseList() {
		log.info("Entered CourseController.courseList");
		return courseService.listOfCourses();
	}
	
	@PostMapping(path = "/add/{courseName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addCourse(@PathVariable(required = true) String courseName, @RequestParam HashMap<String, Object> inputMap) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Entered CourseController.addCourse with model " + gson.toJson(inputMap));

		courseService.addCourse(courseName, inputMap);
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/details/{courseId}")
	public String viewCourseDetails(@PathVariable Integer courseId, Model model) {
		log.info("Entered CourseController.viewCourseDetails");
		Course course = courseService.courseDetails(courseId);
		model.addAttribute("course", course);
		log.info("Exiting with course details {}", course);
		return "courseDetails";
		
	}

}
