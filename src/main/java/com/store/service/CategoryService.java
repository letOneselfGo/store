package com.store.service;

import com.store.model.Category;

public interface CategoryService {

	String findAll() throws Exception ;

	Category findCnameByCid(Integer cid);

}
