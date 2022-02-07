<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

	<%--静态包含 base标签、css样式、jquery文件--%>.
	<%@include file="/pages/common/head.jsp"%>

	<Script type="text/javascript">
		$(function () {
// 给加入购物车按钮绑定单击事件
			$("button.addToCart").click(function () {
				/**
				 * 在事件响应的 function 函数 中，有一个 this 对象，这个 this 对象，是当前正在响应事件的 dom 对象
				 * @type {jQuery}
				 */
				var bookId = $(this).attr("bookId");
				location.href = "http://localhost:8080/book/cartServlet?action=addItem&id=" + bookId;
			});
		});
	</Script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
		

			<div>
				<c:if test="${empty sessionScope.user}">
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<a href="pages/user/login.jsp">${sessionScope.user.username}</a> |
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="" method="get">
					价格：<input id="min" type="text" name="min" value=""> 元 - 
						<input id="max" type="text" name="max" value=""> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>

			<c:forEach items="${requestScope.page.item}" var="item">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="static/img/default.jpg" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名：</span>
						<span class="sp2">${item.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者：</span>
						<span class="sp2">${item.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${item.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${item.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${item.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${item.id} "class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>


		<div id="page_nav">
			<%--页码大于1才显示上一页--%>
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="client/bookServlet?action=page&pageNo=1">首页</a>
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>

			<%--页码输出的开始--%>
			<c:choose>
				<%--情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>
				<%--情况 2：总页码大于 5 的情况--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
						<c:when test="${requestScope.page.pageNo <= 3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<%--小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
						<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<%--小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
							<c:set var="end" value="${requestScope.page.pageNo+2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<c:if test="${i != requestScope.page.pageNo}">
					<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--页码输出的结束--%>
			<%--如果是最后一页，那么不显示下一页和末页--%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
			到第<input value="4" name="pn" id="pn_input"/>页
			<input id="searchPage" type="button" value="确定">


		</div>

	</div>
	
<%--	<div id="bottom">--%>
<%--		<span>--%>
<%--			尚硅谷书城.Copyright &copy;2015--%>
<%--		</span>--%>
<%--	</div>--%>

	<%--静态包含 页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>