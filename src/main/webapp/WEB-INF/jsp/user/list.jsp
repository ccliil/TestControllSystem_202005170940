<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户统计页面</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>

<body style="padding:1%;">
<form action="/user/list" method="post" class="form-inline" style="padding: 10px;">
    <input type="hidden" name="operation" id="operation"/>
    <div class="form-group">
        <select name="find" class="form-control">
            <option value="username">账号</option>
            <option value="name">姓名</option>
        </select>
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="value" placeholder="请输入你要查找的值"/>
    </div>
    <div class="form-group">
        <input class="btn" type="submit" id="find" name="name" value="查找"/>
        <input class="btn" type="submit" id="add" name="add" value="添加用户"/>
    </div>
</form>
<table style="text-align: center" class="table table-striped table-hover table-bordered" id="news-lis">
    <tr>
        <td>账号</td>
        <td>姓名</td>
        <td>电话号码</td>
        <td>专业</td>
        <td>身份</td>
        <td>操作</td>
    </tr>
    <%--<c:forEach begin="0" end="2" var="i">--%>
    <%--<c:set var="user" value="${list[i]}"/>--%>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.tel}</td>
            <td>${user.major.name}</td>
            <td>${user.identify}</td>
            <td><a href="/user/updateUser?id=${user.id}">修改</a>&nbsp;&nbsp;<a
                    href="/user/deleteUser?id=${user.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li><a href="list?indexPage=1">首页</a></li>
        <c:forEach begin="1" end="${pages}" var="p">
            <li><a href="list?indexPage=${p}">${p}</a></li>
        </c:forEach>
        <li><a href="list?indexPage=${pages}">尾页</a></li>
    </ul>
</nav>


</body>
<script>

    $(document).ready(function () {
        $("#find").click(function () {
            $("#operation").val($("#find").val());

        });
        $("#add").click(function () {
            $("#operation").val($("#add").val());

        });
    });
</script>
</html>