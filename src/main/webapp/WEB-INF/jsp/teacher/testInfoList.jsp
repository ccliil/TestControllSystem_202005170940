<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>考生试卷数据展示</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="margin: 1%;">
<form action="/teacher/testInfo/list" method="post" class="form-inline">
    <div class="form-group">
        <select class="form-control" name="find" id="find">
            <option value="testName">试卷名称</option>
            <option value="userName">考生姓名</option>
        </select>
    </div>

    <div class="form-group">
        <input class="form-control" type="text" name="findValue"/>
    </div>
    <div class="form-group">
        <input class="btn btn-info" type="submit" value="查找"/>
    </div>
</form>

<table class="table table-striped table-bordered table-hover">
    <tr>
        <td>试卷名称</td>
        <td>考试人</td>
        <td>考试时间</td>
        <td>选择题答案</td>
        <td>判断题答案</td>
        <td>填空题答案</td>
        <td>考生试卷分数</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="testInfo">
        <tr>
            <td>${testInfo.test.tname}</td>
            <td>${testInfo.user.name}</td>
            <td>${testInfo.createTime}</td>
            <td>${testInfo.cAnswerList}</td>
            <td>${testInfo.jAnswerList}</td>
            <td>${testInfo.vAnswerList}</td>
            <td>${testInfo.score}</td>
            <td>
                <a href="/teacher/testInfo/delete?fid=${testInfo.fid}">删除</a>&nbsp;&nbsp;
                <a href="/teacher/testInfo/lookTest?fid=${testInfo.fid}">查看</a>&nbsp;&nbsp;
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>