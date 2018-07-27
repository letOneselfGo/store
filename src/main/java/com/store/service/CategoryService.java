package com.store.service;

import java.util.List;

import com.store.model.Category;

public interface CategoryService {

	String findAll() throws Exception ;

	Category findCnameByCid(Integer cid);

	List<Category> findList() throws Exception;

	void save(Category c);

}
