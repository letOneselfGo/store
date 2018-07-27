package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Order;
import com.store.service.OrderService;
@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController {
	@Autowired
	OrderService orderservice;
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@RequestMapping("findAllByState")
	public ModelAndView findAllByState() {
		 ModelAndView mv = new ModelAndView();
		 String state = request.getParameter("state");
		 List<Order> list = orderservice.findAllByState(state);
			mv.addObject("list", list);
			mv.setViewName("admin/order/list");
		 
		 return mv;
	}
	

}
