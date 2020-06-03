package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.auth.services.LoginServiceImpl;
import com.advancesd.group17.user.models.User;

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
		AuthDao dl = new AuthDaoImpl();
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
			return "redirect:/admin/home";
		}
		else
		{
			return "redirect:/home?bannerid="+usr.getBannerId();
		}
	}
	
}
