package com.store.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.model.Cart;
import com.store.model.CartItem;
import com.store.model.Product;
import com.store.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	ProductService productservice;
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	
	@Autowired
	HttpSession httpSession;

	@RequestMapping("add2cart")
	public ModelAndView add2cart() {
		
		   ModelAndView mv = new ModelAndView();
		  	try {
				String pid = request.getParameter("pid");
				int count = Integer.parseInt(request.getParameter("count"));
				
				Product product = productservice.getById(pid);
				CartItem cartItem = new CartItem(product, count);
				
				Cart cart = getCart(request);
				
				cart.add2cart(cartItem);
				
				response.sendRedirect(request.getContextPath()+"/cart/cart1");
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", "加入购物车失败");
				mv.setViewName("jsp/msg");
				return mv;
			}
		  	
		  	
		return null;
	}
	@RequestMapping("remove")
	public ModelAndView remove() throws Exception  {
		ModelAndView mv = new ModelAndView();
		String pid = request.getParameter("pid");
		getCart(request).removeFromCart(pid);
	
			response.sendRedirect(request.getContextPath()+"/cart/cart1");
		
		return null;
	}
	@RequestMapping("/clear")
	public ModelAndView clear() throws Exception {
		ModelAndView mv = new ModelAndView();

		getCart(request).clearCart();
		response.sendRedirect(request.getContextPath()+"/cart/cart1");
		return null;
	}
	@RequestMapping("/cart1")
	public ModelAndView cart1() {
		  ModelAndView mv = new ModelAndView();
			mv.setViewName("jsp/cart");

		return mv;
	}
	@RequestMapping("getCart")
	public Cart getCart(HttpServletRequest request){
		Cart cart =(Cart) request.getSession().getAttribute("cart");
		
		if(cart == null ) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
			return cart;			
					
		
	}
}

