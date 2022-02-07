package com.atguigu.dao.impl;

import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao{
    @Override
    public int addBook(Book book) {
        String sql="insert into book.t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?);";
        return this.update(sql,book.getName(),book.getAuthor(),book.getPrice()
        ,book.getSales(),book.getStock(),book.getImgPath());

    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from book.t_book where id=?;";
        return this.update(sql,id);

    }

    @Override
    public int update(Book book) {
        String sql="update book.t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?;";
        return this.update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales()
        ,book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryById(Integer id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` as imgPath from book.t_book where id=?;";
        return this.queryForOne(Book.class,sql,id);

    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * ,`img_path` as imgPath from book.t_book;";
        return this.queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from book.t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }
}
