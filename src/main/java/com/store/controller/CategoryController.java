package com.store.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.service.CategoryService;


@Controller
@RequestMapping("/category")

public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
   /**
    * �?有商品分类查�?
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
	public String findAll() throws Exception{

    //	ModelAndView mv = new ModelAndView();
    	String value = categoryService.findAll();
      //System.out.println(value);
		return  value;
	}
}
