package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        Connection con = JdbcUtils.getConnection();
        System.out.println(con);

    }
}
