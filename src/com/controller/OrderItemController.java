package com.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.OrderItem;
import com.service.OrderItemService;

@Controller
public class OrderItemController {
	@Autowired
	private OrderItemService ois;
	@RequestMapping("/ois_test")
	public String ois_test(OrderItem oi)
	{
		int f=ois.saveOrderItem(oi);
		System.out.println(f);
		return null;
	}
}
