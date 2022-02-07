package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.impl.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private BookService bookService=new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
// 获取请求的参数 商品编号
        String ids=req.getParameter("id")==""?"1":req.getParameter("id");
        int id = Integer.parseInt(ids);
// 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        System.out.println(book);
// 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        System.out.println(cartItem);
// 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();

            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
// 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }



}
