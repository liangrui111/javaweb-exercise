<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含 base标签、css样式、jquery文件--%>.
	<%@include file="/pages/common/head.jsp"%>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
	${sessionScope}
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
<%--			<div>--%>
<%--				<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>--%>
<%--				<a href="pages/order/order.jsp">我的订单</a>--%>
<%--				<a href="index.jsp">注销</a>&nbsp;&nbsp;--%>
<%--				<a href="index.jsp">返回</a>--%>
<%--			</div>--%>
		<!--静态包含 登录成功后的菜单-->
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.items}">
				<%--如果购物车空的情况--%>
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--如果购物车非空的情况--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>${entry.value.count}</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a href="#">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
		<%--如果购物车非空才输出页面的内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
<span class="cart_span">购物车中共有<span
		class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span
						class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="#">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</div>
		</c:if>

<%--		<div class="cart_info">--%>
<%--			<span class="cart_span">购物车中共有<span class="b_count">4</span>件商品</span>--%>
<%--			<span class="cart_span">总金额<span class="b_price">90.00</span>元</span>--%>
<%--			<span class="cart_span"><a href="#">清空购物车</a></span>--%>
<%--			<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>--%>
<%--		</div>--%>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>