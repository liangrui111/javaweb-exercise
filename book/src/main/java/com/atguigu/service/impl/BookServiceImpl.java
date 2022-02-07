package com.atguigu.service.impl;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.dao.impl.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public class BookServiceImpl implements BookService{
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bookDao.queryById(id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> bookList = bookDao.queryBooks();
        return bookList;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        // 设置当前页码
        page.setPageNo(pageNo);
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItem(items);
        return page;
    }
}
