package com.service;

import java.util.List;


import com.po.Book;
import com.po.Page;

public interface BookService {
	public int addBook(Book book);
	public int deleteBookById(Integer id);
	public int updateBook(Book book);
	public Book queryBookById(Integer id);
	public List<Book> queryBooks();
	public Page<Book> page(int pageNo, int pageSize); 
	public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
