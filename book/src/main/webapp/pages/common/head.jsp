<%--
  Created by IntelliJ IDEA.
  User: 梁睿
  Date: 2021/10/25
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--注意：base标签需要动态获取，否则跳转的时候固定按照localhost去找，而访问者的本机上是没有该资源的--%>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>
<%=basePath%>
<!--写 base 标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

