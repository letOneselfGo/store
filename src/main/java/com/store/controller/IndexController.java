package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Product;
import com.store.model.User;
import com.store.service.ProductService;
import com.store.util.SessionUtil;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private ProductService productService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@Autowired
	HttpSession httpSession;

	/*
	 * �?热商品，�?新商品展�?
	 */
	@RequestMapping("/index")
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView();
		
		User attribute= SessionUtil.getInstance().getSession(httpSession.getId());
       
//		User attribute  = (User) request.getAttribute("user");
//		if(session !=null) {
//			attribute =	(User)  session.getAttribute("user");
//		}
		
	
		
		System.out.println( httpSession.getId());
		
		List<Product> hotList = productService.findHot();
		List<Product> newList = productService.findNew();
		if(attribute != null) {
			mv.addObject("user",attribute);
		}
		mv.addObject("hotList", hotList);
		mv.addObject("newList", newList);
		mv.setViewName("jsp/index");
		return mv;

	}

}
