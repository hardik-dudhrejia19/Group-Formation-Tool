package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.dao.UserDaoImpl;
import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.auth.services.ForgotPasswordService;
import com.advancesd.group17.auth.services.ForgotPasswordServiceImpl;

@Controller
public class ForgotPasswordController {

	@GetMapping("/forgotpassword")
	public String Forgotpassword(Model model)
	{
		return "forgotpassword";
	}
	
	@PostMapping("/forgotpassword")
	public String SubmitEmail(User user, Model model)
	{
		UserDao ud = new UserDaoImpl();
		ForgotPasswordService fp = new ForgotPasswordServiceImpl();
		
		if(user.getBannerId() == null || user.getBannerId().isEmpty() || "".equals(user.getBannerId()) )
		{
			model.addAttribute("invalidData",true);
		}
		else
		{
			if(fp.checkuserbybanner(user, ud))
			{
				if(fp.mailsent(user.getBannerId(), ud))
				{
					model.addAttribute("success",true);
				}
				else
				{
					model.addAttribute("failure",true);
				}
			}
			else
			{
				model.addAttribute("invalidcred",true);
			}
		}

		return "forgotpassword";
	}
}