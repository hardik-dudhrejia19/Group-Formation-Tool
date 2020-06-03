package com.advancesd.group17.course.controllers;

import static com.advancesd.group17.utils.Constants.ADMIN_HOME_PAGE;
import static com.advancesd.group17.utils.Constants.REDIRECT;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.course.services.CourseServiceImpl;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.dao.UserDaoImpl;
import com.advancesd.group17.user.models.NewStudent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		log.info("Entered CourseController.deleteCourse");
		CourseService courseService = new CourseServiceImpl();
		courseService.deleteCourse(id);
		
		log.info("Exiting CourseController.deleteCourse");
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/courselist")
	public List<Course> courseList() {
		log.info("Entered CourseController.courseList");
		CourseService courseService = new CourseServiceImpl();
		CourseDao courseDao = new CourseDaoImpl();
		return courseService.getAllCourses(courseDao);
	}
	
	@PostMapping(path = "/add/{courseName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addCourse(@PathVariable(required = true) String courseName, @RequestParam HashMap<String, Object> inputMap) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Entered CourseController.addCourse with model " + gson.toJson(inputMap));
		
		CourseService courseService = new CourseServiceImpl();
		courseService.addCourse(courseName, inputMap);
		return REDIRECT + ADMIN_HOME_PAGE;
	}
	
	@GetMapping("/details/{courseId}")
	public String viewCourseDetails(@PathVariable Integer courseId, Model model) {
		log.info("Entered CourseController.viewCourseDetails");
		CourseService courseService = new CourseServiceImpl();
		Course course = courseService.courseDetails(courseId);
		model.addAttribute("course", course);
		log.info("Exiting with course details {}", course);
		return "courseDetails";
		
	}


	@GetMapping("/description")
	public String studentinfopage(@RequestParam("courseid") int courseid, Model model) {
		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();
		String coursename = lc.getCourseByCourseId(courseid, cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", false);
		return "coursedescription";
	}

	@GetMapping("/ta/description")
	public String tainfopage(@RequestParam("courseid") int courseid, Model model) {

		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();
		String coursename = lc.getCourseByCourseId(courseid, cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", true);
		return "coursedescription";
	}

	@PostMapping("/description/addta")
	public String addTa(@RequestParam("bannerid") String bannerid, @RequestParam("courseid") int courseid,
			Model model) {
		boolean exsistinguser;
		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();
		UserDao userDao = new UserDaoImpl();
		String coursename = lc.getCourseByCourseId(courseid, cd);

		exsistinguser = userDao.isAlreadyUser(bannerid);
		if (exsistinguser) {
			boolean rowsaffected = lc.assignTa(courseid, bannerid, cd);
			if (rowsaffected) {
				model.addAttribute("addtapass", "TA added successfully");
			} else {
				model.addAttribute("addtafail", "The user is already either a TA or a student in this course");
			}
		} else {
			model.addAttribute("addtafail", "User with the entered banner id does not exists");
		}
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", true);
		return "coursedescription";
	}

	@PostMapping("/description/upload")
	public String uploadfile(@RequestParam("file") MultipartFile file, @RequestParam("courseid") int courseid,
			Model model) throws IOException {

		CourseServiceImpl lc = new CourseServiceImpl();
		CourseDao cd = new CourseDaoImpl();
		UserDao userDao = new UserDaoImpl();
		List<NewStudent> newstudents;
		List<NewStudent> sendmailtostudents;
		boolean isValid;

		String coursename = lc.getCourseByCourseId(courseid, cd);
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", true);

		String filename = file.getOriginalFilename();
		String extension = lc.getFileExtension(filename);

		if (!extension.equals("csv")) {
			model.addAttribute("uploadstatusfail", "Please upload a CSV file only");
			return "coursedescription";
		}

		if(file.getBytes().length==0)
		{
			model.addAttribute("uploadstatusfail", "Cannot upload Empty file");
			return "coursedescription";	
		}
		
		byte[] bytes = file.getBytes();
		newstudents = lc.readFile(bytes);
		sendmailtostudents = lc.getNewStudents(newstudents, userDao);
		isValid = lc.enrollStudentsToCourse(courseid, newstudents, cd);

		for (NewStudent student : sendmailtostudents) {
			lc.sendMail(student.getEmail(), student.getBannerId(), student.getBannerId(), coursename);
		}
		if (isValid) {
			model.addAttribute("uploadstatuspass", "Students registered successfully");
		} else {
			model.addAttribute("uploadstatusfail", "Error registering the students");
		}

		return "coursedescription";
	}

}
