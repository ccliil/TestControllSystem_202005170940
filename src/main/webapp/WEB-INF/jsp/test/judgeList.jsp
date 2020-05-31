<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>判断题列表</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/test/judge/list" method="post" class="form-inline" style="padding: 10px;">
    <div class="form-group">
        <select class="form-control" name="find" id="findWay">
            <option value="majorid">题类</option>
            <option value="uid">出题人</option>
        </select>
    </div>
    <div class="form-group">
        <select class="form-control" name="value" id="majorValue">
            <c:forEach items="${listMajor}" var="major">
                <option value="${major.mid}">${major.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <select class="form-control" name="value" id="userValue">
            <c:forEach items="${listUser}" var="user">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <input class="btn btn-info" type="submit" id="find" name="name" value="查找"/>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
</form>
<table class="table table-striped table-bordered table-hover" id="news-lis">
    <tr>
        <td>题目</td>
        <td>答案</td>
        <td>分数</td>
        <td>题类</td>
        <td>出题人</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="judge">
        <tr>
            <td>${judge.account}</td>
            <c:if test="${judge.answer==1}">
                <td>√</td>
            </c:if>
            <c:if test="${judge.answer==0}">
                <td>×</td>
            </c:if>
            <td>${judge.score}</td>
            <td>${judge.major.name}</td>
            <td>${judge.user.name}</td>
            <td><a href="/test/judge/update?jid=${judge.jid}">修改</a>&nbsp;&nbsp;<a
                    href="/test/judge/delete?jid=${judge.jid}">删除</a></td>
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
<script>
    function changSelect() {
        if ("majorid" == $("#findWay").val()) {
            $("#userValue").attr("disabled", "disabled");
            $("#majorValue").removeAttr("disabled");
        } else if ("uid" == $("#findWay").val()) {
            $("#majorValue").attr("disabled", "disabled");
            $("#userValue").removeAttr("disabled");
        }
    }

    $(document).ready(function () {
        changSelect();
        $("#findWay").change(function () {
            changSelect();
        });
    });


</script>

</html>