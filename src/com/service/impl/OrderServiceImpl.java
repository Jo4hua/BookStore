package com.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.po.Book;
import com.po.Cart;
import com.po.CartItem;
import com.po.Order;
import com.po.OrderItem;
import com.service.BookService;
import com.service.OrderItemService;
import com.service.OrderService;
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao od;
	@Override
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		return this.od.saveOrder(order);
	}

}
