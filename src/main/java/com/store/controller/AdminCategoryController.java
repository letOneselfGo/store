package com.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Category;
import com.store.service.CategoryService;
import com.store.util.UUIDUtils;

@Controller
@RequestMapping("/adminCategory")
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
	 ModelAndView mv = new	ModelAndView();
	try {
		List<Category> list = categoryService.findList();
		mv.addObject("list", list);
		mv.setViewName("admin/category/list");
	} catch (Exception e) {
		e.printStackTrace();
		mv.addObject("msg", "查找分类名称异常");
		mv.setViewName("jsp/msg");
	}
		return mv;
	}
	
	@RequestMapping("/add")
	public String add() {
		return "/admin/category/add";
	}
	
	@RequestMapping("/save")
	public ModelAndView save() {
		ModelAndView mv = new ModelAndView();
		try {
			Category c = new Category();
			c.setCid(UUIDUtils.getId());
			c.setCname(request.getParameter("cname"));
			categoryService.save(c);
			response.sendRedirect(request.getContextPath()+"/adminCategory/findAll");
		} catch (IOException e) {
			e.printStackTrace();
			mv.addObject("msg", "保存商品分类名称失败");
			mv.setViewName("jsp/msg");
			return mv;
		}
		return null;
		
	}

}
