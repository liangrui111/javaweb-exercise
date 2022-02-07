package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.impl.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();

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
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
