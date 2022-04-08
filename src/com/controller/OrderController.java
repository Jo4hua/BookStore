package com.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Book;
import com.po.Cart;
import com.po.CartItem;
import com.po.Order;
import com.po.OrderItem;
import com.po.User;
import com.service.BookService;
import com.service.OrderItemService;
import com.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService os;
	@Autowired
	private OrderItemService ois;
	@Autowired
	private BookService bs;
	@RequestMapping("/creat_order")
	public String createOrder(HttpSession session)
	{
		Cart cart=(Cart) session.getAttribute("cart");
		User user=(User) session.getAttribute("user");
		if(user==null)
		{
			return "user/login";
		}
		String orderId = System.currentTimeMillis()+""+user.getId();
		DateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Order order = new Order(orderId,sdf.format(new Date()),cart.getTotalPrice(),
				0,user.getId());
		os.saveOrder(order);
		for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new
					OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
			ois.saveOrderItem(orderItem);
			Book book = bs.queryBookById(cartItem.getId());
			book.setSales( book.getSales() + cartItem.getCount() );
			book.setStock( book.getStock() - cartItem.getCount() );
			bs.updateBook(book);
		}
		cart.clear();
		session.setAttribute("orderId",orderId);
		return "cart/checkout";
	}
}
