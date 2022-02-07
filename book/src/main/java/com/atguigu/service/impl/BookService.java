package com.atguigu.service.impl;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);
}
