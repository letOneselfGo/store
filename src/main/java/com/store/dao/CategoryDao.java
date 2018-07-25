package com.store.dao;

import java.util.List;

import com.store.model.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;


	Category findCnameByCid(Integer cid);

}
