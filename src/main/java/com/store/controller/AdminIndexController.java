package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	@RequestMapping("/index")
	public String index() {
	
		 return "/admin/index";
		 }
	@RequestMapping("/home")
	public String home() {
	
		 return "/admin/home";
		 }
	@RequestMapping("/top")
	public String top() {
	
		 return "/admin/top";
		 }
	@RequestMapping("/left")
	public String left() {
	
		 return "/admin/left";
		 }
	@RequestMapping("/welcome")
	public String welcome() {
	
		 return "/admin/welcome";
		 }
	@RequestMapping("/bottom")
	public String bottom() {
	
		 return "/admin/bottom";
		 }
}
