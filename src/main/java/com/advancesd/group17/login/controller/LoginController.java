package com.advancesd.group17.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.advancesd.group17.login.DaoLogin;
import com.advancesd.group17.login.IDaoLogin;
import com.advancesd.group17.login.model.User;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model)
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String submitLogin(@ModelAttribute(name="user") User usr, Model model)
	{
		IDaoLogin dl = new DaoLogin();
		String page = usr.serviceLogin(usr.getBannerID(), usr.getPassword(),dl);
		
		if("login".equals(page))
			model.addAttribute("invalidcred",true);
		
		return page;
	}
	
}
