package com.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.model.Product;
import com.store.service.ProductService;
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productservice;
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@Autowired
	HttpSession httpSession;
	@RequestMapping("findByPage")
	public ModelAndView findByPage(@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request,Model model) {
		ModelAndView mv= new ModelAndView();
		try {
			String cid = request.getParameter("cid");
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("cid", cid);
			PageHelper.startPage(page, 12);
			List<Product> productList = productservice.selectByList(m);
			PageInfo<Product> pageInfo = new PageInfo<Product>(productList);
			mv.addObject("cid", cid);
			mv.addObject("page",pageInfo);
			mv.addObject("productList",productList);
			mv.setViewName("jsp/product_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject("msg", "分类展示错误�?");
			mv.setViewName("jsp/msg");
		}
		return mv;
	}
	

	@RequestMapping("getById")
	public ModelAndView getById() {
		ModelAndView mv= new ModelAndView();
		String pid = request.getParameter("pid");
		Product pd =productservice.getById(pid);
		mv.addObject("bean", pd);
		mv.setViewName("jsp/product_list");
		return mv;
	}


}
