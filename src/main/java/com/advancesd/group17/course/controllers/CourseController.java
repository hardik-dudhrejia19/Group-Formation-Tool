package com.advancesd.group17.course.controllers;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.course.services.CourseServiceImpl;
import com.advancesd.group17.course.services.FileImportService;
import com.advancesd.group17.course.services.FileImportServiceImpl;
import com.advancesd.group17.user.dao.UserDao;
import com.advancesd.group17.user.dao.UserDaoImpl;
import com.advancesd.group17.user.models.NewStudent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.advancesd.group17.utils.Constants.ADMIN_HOME_PAGE;
import static com.advancesd.group17.utils.Constants.REDIRECT;

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
	public String studentInfoPage(@RequestParam("courseid") int courseid, Model model) {
		log.info("Entered CourseController.studentInfoPage");
		CourseDao courseDao = new CourseDaoImpl();
		CourseService courseService = new CourseServiceImpl();
		String coursename = courseService.getCourseByCourseId(courseid, courseDao);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", false);
		return "coursedescription";
	}

	@GetMapping("/ta/description")
	public String taInfoPage(@RequestParam("courseid") int courseid, Model model) {
		log.info("Entered CourseController.taInfoPage");
		CourseDao courseDao = new CourseDaoImpl();
		CourseService courseService = new CourseServiceImpl();
		String coursename = courseService.getCourseByCourseId(courseid, courseDao);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", true);
		return "coursedescription";
	}

	@PostMapping("/description/addta")
	public String addTa(@RequestParam("bannerid") String bannerid, @RequestParam("courseid") int courseid,
			Model model) {
		log.info("Entered CourseController.addTa");
		boolean isExistingUser;
		CourseDao courseDao = new CourseDaoImpl();
		CourseService courseService = new CourseServiceImpl();
		UserDao userDao = new UserDaoImpl();

		String coursename = courseService.getCourseByCourseId(courseid, courseDao);
		isExistingUser = userDao.isAlreadyUser(bannerid);

		if (isExistingUser) {
			boolean rowsaffected = courseService.assignTa(courseid, bannerid, courseDao);
			if (rowsaffected) {
				model.addAttribute("addtapass", "TA added successfully");
			} else {
				model.addAttribute("addtafail", "The user is already either a TA or a student in this course or is a guest");
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
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("courseid") int courseid,
							 Model model) throws IOException {

		log.info("Entered CourseController.uploadFile");
		CourseService courseService = new CourseServiceImpl();
		FileImportService fileService = new FileImportServiceImpl();
		CourseDao courseDao = new CourseDaoImpl();
		UserDao userDao = new UserDaoImpl();
		List<NewStudent> newStudents;
		List<NewStudent> sendMailToStudents;
		boolean isValid;

		String coursename = courseService.getCourseByCourseId(courseid, courseDao);
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA", true);

		String filename = file.getOriginalFilename();
		String extension = fileService.getFileExtension(filename);

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
		newStudents = fileService.readFile(bytes);
		sendMailToStudents = fileService.getNewStudents(newStudents, userDao);
		isValid = fileService.enrollStudentsToCourse(courseid, newStudents, courseDao);

		for (NewStudent student : sendMailToStudents) {
			fileService.sendMail(student.getEmail(), student.getBannerId(), student.getBannerId(), coursename);
		}
		if (isValid) {
			model.addAttribute("uploadstatuspass", "Students registered successfully");
		} else {
			model.addAttribute("uploadstatusfail", "Error registering the students");
		}

		return "coursedescription";

	}

}
