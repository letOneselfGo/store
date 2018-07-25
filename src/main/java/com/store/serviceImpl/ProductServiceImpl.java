package com.store.serviceImpl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.store.dao.CategoryDao;
import com.store.dao.ProductDao;
import com.store.model.Category;
import com.store.model.Product;
import com.store.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao ProDao;

    /**
     * 热门商品查询
     */
	@Override
	public List<Product> findHot() {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("pflag",0);
		m.put("is_hot",1);
		return ProDao.findHot( m);
	}
	/**
	 * �?新商品查�?
	 */

	@Override
	public List<Product> findNew() {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("pflag", 0);
		return ProDao.findNew(m);
	}
	
	/**
	 * 分页查询
	 */
	
//	public List<Product> findByPage(String cid) {
//		int totalRecord =  ProDao.getTotalRecord(cid);
//		PageBean<Product> pb = new PageBean<>();
//		Map<String,Object> m = new HashMap<String,Object>();
//		
//
//		return pb;
//	}
	@Override
	public Product getById(String pid) {
		return ProDao.getById(pid);
	}


	@Override
	public List<Product> findByPage(Map<String, Object> m) {
		return ProDao.findByPage(m);
	}
	@Override
	public List<Product> selectByList(Map<String, Object> m) {
		return ProDao.selectByList(m);
	}
	@Override
	public Integer findCid(String pid) {

		return  ProDao.findCid(pid);
	}
}
