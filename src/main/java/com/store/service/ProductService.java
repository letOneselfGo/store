package com.store.service;

import java.util.List;

import com.store.model.Product;

public interface ProductService {

	List<Product> findHot();

	List<Product> findNew(); 

}
