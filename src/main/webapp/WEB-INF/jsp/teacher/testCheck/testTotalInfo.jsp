<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>考卷情况</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="padding: 1%;text-align: center">
<div><h3>考卷情况一览</h3></div>
<div id="right">
    <table class="table table-hover table-bordered table-striped">
        <tr>
            <c:forEach items="${listTestMes}" var="testMessage">
                <td>${testMessage.tname}考生总数</td>
                <td>${testMessage.tname}考试通过人数</td>
                <td>${testMessage.tname}通过率</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach items="${listTestMes}" var="testMessage">
                <td>${testMessage.totalNum}</td>
                <td>${testMessage.passNum}</td>
                <td>${testMessage.passRate}%</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach items="${listTestMes}" var="testMessage">
                <td colspan="3"><a href="/teacher/testMesInfo?tid=${testMessage.tid}">${testMessage.tname}详情</a></td>

            </c:forEach>
        </tr>
    </table>
</div>

</body>

</html>
