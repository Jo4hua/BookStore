package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OrderItemDao;
import com.po.OrderItem;
import com.service.OrderItemService;
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemDao oid;
	@Override
	public int saveOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return this.oid.saveOrderItem(orderItem);
	}

}
