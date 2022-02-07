package com.atguigu.dao.impl;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int update(Book book);
    public Book queryById(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);
}
