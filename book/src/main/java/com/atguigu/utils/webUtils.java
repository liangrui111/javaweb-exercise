package com.atguigu.utils;

import com.atguigu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;


public class webUtils {

    public  static <T> T copyParamToBean(HttpServletRequest req,T bean){
        try {

            System.out.println("注入前:"+bean);
            /**
             * 把所有请求的参数都封装到user中
             * 使用第三方jar包BeanUtils
             *
             * 注意：req最好改成Map value,populate那里直接传value 因为request只在web层可用 这样就和web
             * 层耦合死了
             */
            BeanUtils.populate(bean,req.getParameterMap());
            System.out.println("注入后:"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;

    }
}
