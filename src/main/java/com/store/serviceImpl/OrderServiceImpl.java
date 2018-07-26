package com.store.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.store.dao.OrderDao;
import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.PageBean;
import com.store.service.OrderService;

@Service
public class OrderServiceImpl  implements OrderService{
    @Resource
    OrderDao orderdao;
    @Resource(name="transactionManager")
	private DataSourceTransactionManager transactionManager;

	@Override
	@Transactional
	public void save(Order order) {
	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			try {
				//逻辑代码，可以写上你的逻辑处理代码
				orderdao.save(order);
			for (OrderItem	 oi	 : order.getItems()) {
				orderdao.saveItem(oi);
			}
				transactionManager.commit(status);
			} catch (Exception e) {
				e.printStackTrace();
				transactionManager.rollback(status);
			}

	}

	@Override
	public void update(Order order) {
      orderdao.update(order);		
	}

	@Override
	public Order getById(String oid) {
		
		return orderdao.getById(oid);
	}

	@Override
	public List<Map<String, Object>> getList(String oid) {
		
		return orderdao.getList(oid);
	}

	@Override
	public List<Order> findMyOrdersByPage(Map<String, Object> m) {
		
		return orderdao.findMyOrdersByPage(m);
	}

	@Override
	public List<String> getByuid(String uid) {
		return orderdao.getByuid(uid);
	}


	
}
