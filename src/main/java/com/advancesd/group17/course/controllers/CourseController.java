package com.advancesd.group17.course.controllers;

import java.util.List;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.course.models.CourseAndRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/home/student/description")
	public String studentinfopage(@RequestParam("courseid") int courseid, Model model)
	{
		CourseDao cd = new CourseDaoImpl();
		CourseService lc = new CourseService();
		String coursename = lc.getcoursebycourseid(courseid,cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",false);
		return "coursedescription";
	}

	@GetMapping("/home/ta/description")
	public String tainfopage(@RequestParam("courseid") int courseid, Model model)
	{

		CourseDao cd = new CourseDaoImpl();
		CourseService lc = new CourseService();
		String coursename = lc.getcoursebycourseid(courseid,cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",true);
		return "coursedescription";
	}

	@PostMapping("/home/ta/description")
    public String addTa(
    		@RequestParam("bannerid") String bannerid,
			@RequestParam("courseid") int courseid,
			Model model)
    {

    	boolean exsistinguser;
	    CourseDao cd = new CourseDaoImpl();
        CourseService lc = new CourseService();
		String coursename = lc.getcoursebycourseid(courseid,cd);

        exsistinguser = lc.isalreadyuser(bannerid,cd);
        if(exsistinguser)
        {
			model.addAttribute("statusmessage","TA added successfully");
		}
        else
		{
			model.addAttribute("statusmessage","User with the entered banner id does not exists");
		}
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",true);

		return "coursedescription";

    }

}
