package com.atguigu.test;

import com.atguigu.dao.impl.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;


public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();


    Book book = new Book();

    @Test
    public void addBook() {
        book.setName("xxx");
        book.setAuthor("xxx");
        book.setPrice(new BigDecimal(9999));
        book.setSales(100000);
        book.setStock(0);
        bookDao.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(20);
    }

    @Test
    public void update() {
        book.setId(21);
        book.setName("lll");
        bookDao.update(book);
    }

    @Test
    public void queryById() {
        Book book1 = bookDao.queryById(1);
        System.out.println(book1);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        System.out.println(books);
    }
}