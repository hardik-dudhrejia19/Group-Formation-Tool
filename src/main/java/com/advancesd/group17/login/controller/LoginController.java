package com.advancesd.group17.login.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.login.DaoCourses;
import com.advancesd.group17.login.DaoAuthentication;
import com.advancesd.group17.login.IDaoCourses;
import com.advancesd.group17.login.IDaoAuthentication;
import com.advancesd.group17.login.model.Course;
import com.advancesd.group17.login.model.User;
import com.advancesd.group17.login.services.servicelogin;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model)
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String submitLogin(User usr, Model model)
	{
		IDaoAuthentication dl = new DaoAuthentication();
		servicelogin s = new servicelogin();
		
		Integer id = s.serviceLogin(usr.getBannerID(), usr.getPassword(),dl);
		
		if(id!=-1)
		{
			return "redirect:/home?userid="+id;
		}
		else
		{
			model.addAttribute("invalidcred",true);
			return "login";
		}
	}
	
	@GetMapping("/home")
	public String homepage(@RequestParam("userid") Integer userid, Model model)
	{
		IDaoCourses dc = new DaoCourses();		
		servicelogin s = new servicelogin();
		
		List<Course> listofcourses = s.listcourses(userid, dc);
		
		model.addAttribute("courses", listofcourses);
		model.addAttribute("userid", userid);
		
		return "Home";
	}
	
}
