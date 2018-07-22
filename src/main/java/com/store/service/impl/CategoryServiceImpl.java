package com.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.dao.CategoryDao;
import com.store.model.Category;
import com.store.service.CategoryService;
import com.store.util.JsonUtil;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource
	private CategoryDao categoryDao;
	/**
	 * 查询所有商品列表
	 */

	public String findAll() throws Exception {
		List<Category> list = findList();
		if(list != null && list.size() > 0) {
			return  JsonUtil.list2json(list);
		}
		
		return null;
	}
   /**
    * 后台查询分类列表
    * @return
    * @throws Exception
    */
	private List<Category> findList() throws Exception {
		return categoryDao.findAll();
	}

}
