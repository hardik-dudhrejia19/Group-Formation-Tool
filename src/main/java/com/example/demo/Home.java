package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Home {
	
	@GetMapping("/")
	public String home()
	{
		return "Home Page";
	}
	
	@GetMapping("/Home")
	public String Home()
	{
		System.out.println("Hello");
		return "Hello";
	}
}
