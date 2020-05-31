<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>个人信息</title>

    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="text-align: center">
<div class="panel panel-default">
    <div class="panel-heading">
        <h3>个人信息</h3>
    </div>
    <div class="panel-body form-horizontal">

        <table class="table table-hover table-striped">
            <tr>
                <td >用户名：</td>
                <td >${user.username}</td>
            </tr>
            <tr>
                <td >姓名</td>
                <td >${user.name}</td>
            </tr>
            <tr>
                <td >身份</td>
                <td >${user.identify}</td>
            </tr>
            <tr>
                <td >电话号码</td>
                <td >${user.tel}</td>
            </tr>
            <c:if test="${major}!=null">
                <tr>
                    <td >专业</td>
                    <td >${user.name}</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>

</body>
</html>