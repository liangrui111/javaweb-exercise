package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.impl.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询全部图书
        List<Book> bookList = bookService.queryBooks();
        //全部图书保存到request域中
        req.setAttribute("bookList",bookList);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        int id = Integer.parseInt(ids);
        bookService.deleteBookById(id);

        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page");

    }



    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        int id = Integer.parseInt(ids);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为 Book 对象
        Book book = webUtils.copyParamToBean(req,new Book());
        // 2、调用 BookService.updateBook( book );修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        // 地址：/工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = webUtils.copyParamToBean(req, new Book());
        bookService.addBook(book);
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);//转发会有bug
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+(Integer.parseInt(req.getParameter("pageNo"))+1));//注意这里的/只表示到端口号
    }



    //用于分页
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        String pageNostr=null;
        if(req.getParameter("pageNo")==null){
            pageNostr="1";
        }else{
            pageNostr=req.getParameter("pageNo");
        }
        String pageSizestr=null;
        if(req.getParameter("pageSize")==null){
            pageSizestr="4";
        }else{
            pageSizestr=req.getParameter("pageSize");
        }
        int pageNo = Integer.parseInt(pageNostr);
        int pageSize = Integer.parseInt(pageSizestr);
       //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo,pageSize);
       //3 保存 Page 对象到 Request 域中
        req.setAttribute("page",page);
       //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
