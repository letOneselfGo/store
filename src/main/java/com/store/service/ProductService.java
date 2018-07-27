package com.store.service;

import java.util.List;
import java.util.Map;

import com.store.model.Category;
import com.store.model.PageBean;
import com.store.model.Product;

public interface ProductService {

	List<Product> findHot();

	List<Product> findNew();
	
	Product getById(String pid);

	List<Product> findByPage(Map<String, Object> m);

	List<Product> selectByList(Map<String, Object> m);

	Integer findCid(String pid);

	List<Product> findAll();


}
