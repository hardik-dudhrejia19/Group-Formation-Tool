package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.dao.UserDaoImpl;
import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.auth.services.SignupServiceImpl;

@Controller
public class SignupController {

	@GetMapping("/signup")
	public String signup(Model model)
	{
		return "signup";
	}
	
	@PostMapping("/signup")
	public String submitsignup(@RequestParam("confirmpassowrd") String passwordConfirm, User usr, Model model)
	{
		UserDao dl = new UserDaoImpl();
		SignupServiceImpl s = new SignupServiceImpl();
		
		if (usr.getBannerId() == null || usr.getBannerId().isEmpty() || "".equals(usr.getBannerId()) ||
            usr.getEmail() == null || usr.getEmail().isEmpty() || "".equals(usr.getEmail()) ||
            usr.getFirstName() == null || usr.getFirstName().isEmpty() || "".equals(usr.getFirstName()) ||
            usr.getLastName() == null || usr.getLastName().isEmpty() || "".equals(usr.getLastName()) ||
            usr.getPassword() == null || usr.getPassword().isEmpty() || "".equals(usr.getPassword()) ||
            !usr.getPassword().equals(passwordConfirm) 
           )
		{
			model.addAttribute("invalidData", true);
			return "signup";
        }
		else
		{
			if(s.IsAlreadyUser(usr,dl))
			{
				model.addAttribute("alreadyuserexist",true);
				return "signup"; 
			}
			
			s.registeruser(usr, dl);
			
			model.addAttribute("registrationsuccess",true);
			
			return "signup";
		}
	}
	
}
