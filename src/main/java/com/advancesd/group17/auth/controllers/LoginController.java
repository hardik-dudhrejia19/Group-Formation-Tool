package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.dao.UserDaoImpl;
import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.auth.services.LoginServiceImpl;

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
		UserDao dl = new UserDaoImpl();
		LoginServiceImpl s = new LoginServiceImpl();
				
		if(usr.getBannerId() == null || usr.getBannerId().isEmpty() || "".equals(usr.getBannerId()) ||
           usr.getPassword() == null || usr.getPassword().isEmpty() || "".equals(usr.getPassword()) )
		{
			model.addAttribute("invalidData", true);
			return "login";
		}
		
		boolean authentication = s.userauthentication(usr,dl);

		if(!authentication)
		{
			model.addAttribute("invalidcred",true);
			return "login"; 
		}
		
		if(s.Isadmin(usr.getBannerId(), dl))
		{
			return "redirect:/admin?bannerid="+usr.getBannerId();
		}
		else
		{
			return "redirect:/home?bannerid="+usr.getBannerId();
		}
	}
	
}
