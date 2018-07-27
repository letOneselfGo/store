package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {
	
	@Autowired
	ProductService productservice;
	@Autowired
	HttpServletRequest request;
	@Autowired
	CategoryService categoryService;
	@Autowired
	HttpServletResponse response;
	/**
	 * 已上架商品查询
	 * @return
	 */
		@RequestMapping("/findAll")
		public ModelAndView findAll() {
			ModelAndView mv = new ModelAndView();
			List<Product> list = productservice.findAll();
			mv.addObject("list", list);
			mv.setViewName("admin/product/list");

			return  mv;
		}
		
}
