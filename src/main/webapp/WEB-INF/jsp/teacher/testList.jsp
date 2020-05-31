<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>试卷数据展示</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/testList" method="post">
    <c:if test="${list}!=null">
        <h2>${list.get(0).major.name}专业的所有考卷</h2>
    </c:if>
</form>
<table class="table table-bordered table-striped table-hover">
    <tr>
        <td>试卷名称</td>
        <td>适用专业</td>
        <td>创建人</td>
        <td>创建时间</td>
        <td>是否被启用</td>
        <td>试卷总分</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="test">
        <tr>
            <td>${test.tname}</td>
            <td>${test.major.name}</td>
            <td>${test.user.name}</td>
            <td>${test.createTime}</td>
            <c:if test="${test.isUsed==0}">
                <td>试卷未被启用</td>
            </c:if>
            <c:if test="${test.isUsed==1}">
                <td>试卷已启用</td>
            </c:if>
            <td>${test.totalScore}</td>
            <td>
                <a href="/teacher/testUpdate?tid=${test.tid}">修改</a>&nbsp;&nbsp;
                <a href="/teacher/testDelete?tid=${test.tid}">删除</a>&nbsp;&nbsp;
                <a href="/teacher/testLook?tid=${test.tid}">查看</a>
            </td>
        </tr>
    </c:forEach>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="list?indexPage=1">首页</a>
        </li>
        <c:forEach begin="1" end="${pages}" var="p">
            <li>
                <a href="list?indexPage=${p}">${p}</a>
            </li>
        </c:forEach>
        <li>
            <a href="list?indexPage=${pages}">尾页</a>
        </li>
    </ul>
</nav>

</body>
</html>
