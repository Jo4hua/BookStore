package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.po.Book;
import com.po.Cart;
import com.po.CartItem;
import com.po.Page;
import com.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bs;
	@RequestMapping("/all_book")
	public String all_book(Model model)
	{
		List<Book> lb=bs.queryBooks();
		model.addAttribute("books", lb);
		return "manager/book_manager";
	}
	@RequestMapping("/add_book")
	public String add_book(Book book,Integer pageNo,Model model,String action)
	{
		System.out.println(action);
		Integer f;
		if(action.equals("add"))
			 f=bs.addBook(book);
		else
			 f=bs.updateBook(book);
		return "forward:book_page?pageNo="+pageNo;
	}
	@RequestMapping("/delete_book")
	public String delete_book(Integer id,Integer pageNo)
	{
		int f=bs.deleteBookById(id);
		return "forward:book_page?pageNo="+pageNo;
	}
	@RequestMapping("/get_book")
	public String get_book(Integer id,Model model)
	{
		Book book=bs.queryBookById(id);
		model.addAttribute("book",book);
		return "manager/book_edit";
	}
	@RequestMapping("/book_page")
	public String get_page(Integer pageNo,Integer pageSize,Model model)
	{
		if(pageSize==null)pageSize=4;
		if(pageNo==null)pageNo=1;
		Page<Book> page=bs.page(pageNo, pageSize);
		page.setUrl("book_page?");
		model.addAttribute("page",page);
		return "manager/book_manager";
	}
	@RequestMapping("/index_page")
	public String index_page(Integer pageNo,Integer pageSize,Integer min,Integer max,Model model)
	{
		if(pageSize==null)pageSize=4;
		System.out.println("cpageNo="+pageNo);
		System.out.println("cpageSize="+pageSize);
		if(pageNo==null||pageNo==0)pageNo=1;
		System.out.println("cpageNo="+pageNo);
		if(min==null)min=0;
		if(max==null)max=Integer.MAX_VALUE;
		Page<Book> page=bs.pageByPrice(pageNo, pageSize, min, max);
		page.setUrl("index_page?min="+min+"&max="+max+"&");
		model.addAttribute("page",page);
		return "forward:/index.jsp";
	}
	@RequestMapping("/add_item")
	public void add_Item(Integer id,HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws IOException
	{
		Book book=bs.queryBookById(id);
		CartItem ci=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null)
		{
			cart = new Cart();
			session.setAttribute("cart",cart);
		}
		cart.addItem(ci);
		session.setAttribute("lastName", ci.getName());
		System.out.println(ci.getName());
		resp.sendRedirect(req.getHeader("Referer"));
	}
	@RequestMapping("/dele_item")
	public void dele_item(Integer id,HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws IOException
	{
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart!=null)
		{
			cart.deleteItem(id);
			resp.sendRedirect(req.getHeader("Referer"));
		}
	}
	@RequestMapping("/clear_item")
	public void clear_item(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart != null) {
		// 清空购物车
		cart.clear();
		// 重定向回原来购物车展示页面
		resp.sendRedirect(req.getHeader("Referer"));
		}

	}
	@RequestMapping("/update_item")
	public void update_item(Integer id,Integer count,HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if(cart!=null)
		{
			cart.updateCount(id,count);
			resp.sendRedirect(req.getHeader("Referer"));
		}
	}
}
