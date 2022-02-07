package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;

    static{

        //创建数据库连接池
        try {
            Properties properties = new Properties();
            //读取jdbc.properties
            //InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //这里使用getResourceAsStream的方法获取不到文件，查了网上的方法并不可行，感觉文件放的位置并没有错******
            FileInputStream is = new FileInputStream(new File("D:\\workspace_idea1\\javaweb_book\\book\\src\\main\\java\\jdbc.properties"));
            //从流中加载数据
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 获取数据库连接池中的连接
     * 返回null为失败
     */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


    /**
     * 关闭连接放回数据库连接池
     */
    public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
