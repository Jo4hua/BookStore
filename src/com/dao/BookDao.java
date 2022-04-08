package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.po.Book;

public interface BookDao {
	public int addBook(Book book);
	public int deleteBookById(Integer id);
	public int updateBook(Book book);
	public Book queryBookById(Integer id);
	public List<Book> queryBooks();
	public Integer queryForPageTotalCount();
	public List<Book> queryForPageItems(@Param("begin")int begin, @Param("pageSize")int pageSize);
	public Integer queryForPageTotalCountByPrice(@Param("min")int min, @Param("max")int max);
	public List<Book> queryForPageItemsByPrice(@Param("begin")int begin, @Param("pageSize")int pageSize, @Param("min")int min, @Param("max")int max);
}
