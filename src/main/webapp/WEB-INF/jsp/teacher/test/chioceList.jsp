<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>选择题列表</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/chioce/list" method="post" class="form-inline" style="padding: 10px;">
    <div class="form-group">
        <strong >出题人：</strong>
    </div>
    <div class="form-group">
        <select class="form-control" name="uid" id="userValue">
            <c:forEach items="${listUser}" var="user">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <input class="form-control" type="submit" id="find" name="name" value="查找"/>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
</form>
<table class="table table-bordered table-striped table-hover" id="news-lis">
    <tr>
        <td>题目</td>
        <td>A选项</td>
        <td>B选项</td>
        <td>C选项</td>
        <td>D选项</td>
        <td>答案</td>
        <td>分数</td>
        <td>题类</td>
        <td>出题人</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="chioce">
        <tr>
            <td>${chioce.account}</td>
            <td>${chioce.aitem}</td>
            <td>${chioce.bitem}</td>
            <td>${chioce.citem}</td>
            <td>${chioce.ditem}</td>
            <td>${chioce.answer}</td>
            <td>${chioce.score}</td>
            <td>${chioce.major.name}</td>
            <td>${chioce.user.name}</td>
            <td><a href="/teacher/chioce/update?cid=${chioce.cid}">修改</a>&nbsp;&nbsp;<a
                    href="/teacher/chioce/delete?cid=${chioce.cid}">删除</a></td>
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