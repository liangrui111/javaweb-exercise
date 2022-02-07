package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.webUtils;
import com.google.code.kaptcha.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService=new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = webUtils.copyParamToBean(req, new User());//把参数注入user****


        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(user);
        // 如果等于 null,说明登录 失败!
        if (loginUser == null) {
            // 跳回登录页面
            //把错误信息，和回显的表单项信息，保存到request域中********
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //将用户放入session域中以便前端显示用户名
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面 login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        // 2、检查 验证码是否正确 === 写死,要求验证码为:abcde
        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
       // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        if (token!=null&&token.equalsIgnoreCase(code)) {
            //正确
            // 3、检查 用户名是否可用
            if (userService.existUserName(username)) {
                System.out.println("用户名[" + username + "]已存在!");
                //把错误信息，和回显的表单项信息，保存到request域中********
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

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
