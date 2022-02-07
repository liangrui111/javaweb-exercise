package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    //密码不希望别人看见 所以用dopost而不是doget

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        // 2、检查 验证码是否正确 === 写死,要求验证码为:abcde
        if ("abcde".equalsIgnoreCase(code)) {
            //正确
            // 3、检查 用户名是否可用
            if (userService.existUserName(username)) {
                System.out.println("用户名[" + username + "]已存在!");
                //把错误信息，和回显的表单项信息，保存到request域中********
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            // 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 用户名可用
                // 调用 Sservice 保存到数据库
                userService.registerUser(new User(null, username, password, email));
               // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //验证码错误
            System.out.println("验证码[" + code + "]错误");
           //把错误信息，和回显的表单项信息，保存到request域中********
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
