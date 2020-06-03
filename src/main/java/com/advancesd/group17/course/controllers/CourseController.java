package com.advancesd.group17.course.controllers;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.models.CourseAndRole;
import com.advancesd.group17.course.models.NewStudent;
import com.advancesd.group17.course.services.CourseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

	@GetMapping("/home")
	public String homepage(@RequestParam("bannerid") String bannerid, Model model)
	{
		boolean isStudent = false;
		boolean isTA = false;
		boolean isGuest = false;
        boolean isInstructor = false;

		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();

		List<CourseAndRole> coursesandroles = new ArrayList<>();
		List<Course> allcourses = new ArrayList<>();
		List<String> userroles = lc.getUserRoleByBannerId(bannerid,cd);
        coursesandroles = lc.getCoursesAndRolesByBannerId(bannerid, cd);

        for(String role: userroles)
		{
			if(role.equals("Student"))
			{
                isStudent = true;
			}
			else if(role.equals("TA"))
			{
				isTA = true;
			}
			else if(role.equals("Instructor"))
			{
                 isInstructor = true;
            }
			else if(role.equals("Guest"))
			{
				isGuest = true;
				allcourses = lc.getAllCourses(cd);
			}
		}
		
		model.addAttribute("coursesandroles", coursesandroles);
		model.addAttribute("allcourses",allcourses);
		model.addAttribute("bannerid", bannerid);
		model.addAttribute("isStudent",isStudent);
		model.addAttribute("isTA",isTA);
		model.addAttribute("isGuest",isGuest);
		model.addAttribute("isInstructor",isInstructor);
		
		return "Home";
	}

	@GetMapping("/home/student/description")
	public String studentinfopage(@RequestParam("courseid") int courseid, Model model)
	{
		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();
		String coursename = lc.getCourseByCourseId(courseid,cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",false);
		return "coursedescription";
	}

	@GetMapping("/home/ta/description")
	public String tainfopage(@RequestParam("courseid") int courseid, Model model)
	{

		CourseDao cd = new CourseDaoImpl();
		CourseServiceImpl lc = new CourseServiceImpl();
		String coursename = lc.getCourseByCourseId(courseid,cd);

		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",true);
		return "coursedescription";
	}

	@PostMapping("/home/description/addta")
    public String addTa(
    		@RequestParam("bannerid") String bannerid,
			@RequestParam("courseid") int courseid,
			Model model)
    {
    	boolean exsistinguser;
	    CourseDao cd = new CourseDaoImpl();
        CourseServiceImpl lc = new CourseServiceImpl();
		String coursename = lc.getCourseByCourseId(courseid,cd);

        exsistinguser = lc.isAlreadyUser(bannerid,cd);
        if(exsistinguser)
        {
            boolean rowsaffected = lc.assignTa(courseid,bannerid,cd);
            if(rowsaffected)
            {
                model.addAttribute("addtapass", "TA added successfully");
            }
            else
            {
                model.addAttribute("addtafail", "The user is already either a TA or a student in this course");
            }
		}
        else
		{
			model.addAttribute("addtafail","User with the entered banner id does not exists");
		}
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",true);
		return "coursedescription";
    }


    @PostMapping("/home/description/upload")
	public String uploadfile(@RequestParam("file")MultipartFile file,
							 @RequestParam("courseid") int courseid, Model model) throws IOException {


		CourseServiceImpl lc = new CourseServiceImpl();
		CourseDao cd = new CourseDaoImpl();
		List<NewStudent> newstudents;
		List<NewStudent> sendmailtostudents;
		boolean isValid;

		String coursename = lc.getCourseByCourseId(courseid,cd);
		model.addAttribute("courseid", courseid);
		model.addAttribute("coursename", coursename);
		model.addAttribute("isTA",true);

		String filename = file.getOriginalFilename();
		String extension = lc.getFileExtension(filename);

		if(!extension.equals("csv"))
		{
			model.addAttribute("uploadstatusfail","Please upload a CSV file only");
			return "coursedescription";
		}


		byte[] bytes = file.getBytes();
		newstudents = lc.readFile(bytes);
		sendmailtostudents = lc.getNewStudents(newstudents,cd);
		isValid = lc.enrollStudentsToCourse(courseid,newstudents,cd);

		for(NewStudent student: sendmailtostudents)
		{
		    lc.sendMail(student.getEmail(),student.getBannerId(),student.getBannerId(),coursename);
		}
		if(isValid)
		{
			model.addAttribute("uploadstatuspass","Students registered successfully");
		}
		else
		{
			model.addAttribute("uploadstatusfail","Error registering the students");
		}

		return "coursedescription";
	}

}
