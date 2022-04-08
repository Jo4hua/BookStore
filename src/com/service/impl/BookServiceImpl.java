package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.po.Book;
import com.po.Page;
import com.service.BookService;
@Service
@Transactional
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bd;
	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		return this.bd.addBook(book);
	}

	@Override
	public int deleteBookById(Integer id) {
		// TODO Auto-generated method stub
		return this.bd.deleteBookById(id);
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		return this.bd.updateBook(book);
	}

	@Override
	public Book queryBookById(Integer id) {
		// TODO Auto-generated method stub
		return this.bd.queryBookById(id);
	}

	@Override
	public List<Book> queryBooks() {
		// TODO Auto-generated method stub
		return this.bd.queryBooks();
	}

	@Override
	public Page<Book> page(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Page<Book> page=new Page<Book>();
		page.setPageSize(pageSize);
		Integer pageTotalCount =bd.queryForPageTotalCount();
		page.setPageTotalCount(pageTotalCount);
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal+=1;
			}
		page.setPageTotal(pageTotal);
		page.setPageNo(pageNo);
		int begin=(page.getPageNo()-1)*pageSize;
		List<Book> items=bd.queryForPageItems(begin, pageSize);
		page.setItems(items);
		return page;
	}

	@Override
	public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
		// TODO Auto-generated method stub
		Page<Book> page=new Page<Book>();
		page.setPageSize(pageSize);
		Integer pageTotalCount =bd.queryForPageTotalCountByPrice(min, max);
		page.setPageTotalCount(pageTotalCount);
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal+=1;
			}
		page.setPageTotal(pageTotal);
		page.setPageNo(pageNo);
		System.out.println("pageNo="+page.getPageNo());
		int begin=(page.getPageNo()-1)*pageSize;
		List<Book> items=bd.queryForPageItemsByPrice(begin, pageSize, min, max);
		page.setItems(items);
		return page;
	}

}
