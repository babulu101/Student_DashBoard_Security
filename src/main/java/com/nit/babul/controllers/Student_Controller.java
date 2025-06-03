package com.nit.babul.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nit.babul.entity.Student;
import com.nit.babul.service.Student_Ser;

@Controller
@RequestMapping("/student")
public class Student_Controller {
	@Autowired
	private Student_Ser ser;
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute Student student,Model model) {
		int id=ser.save(student);
		model.addAttribute("msg","Student ID::"+id+" Created Successfully");
		return "register";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
	
	
	
	
	
	
}
