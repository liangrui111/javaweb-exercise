package com.atguigu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
//        if("login".equals(action)){
//            //处理登录
//            this.login(req,resp);
//        }else if("regist".equals(action)){
//            //处理注册
//            this.regist(req,resp);
//        }

//进一步优化。使用反射优化大量if-else。（因为以后可能有添加用户、绑定手机号、修改密码等很多功能）
        try {
            // 获取 action 业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
                    HttpServletResponse.class);//方法参数
            // System.out.println(method);
            // 调用目标业务 方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
}
