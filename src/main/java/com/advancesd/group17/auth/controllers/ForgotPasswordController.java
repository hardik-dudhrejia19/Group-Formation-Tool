package com.advancesd.group17.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPasswordController {

	@GetMapping("/forgotpassword")
	public String Forgotpassword(Model model)
	{
		return "forgotpassword";
	}
}
