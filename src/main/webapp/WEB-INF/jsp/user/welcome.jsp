<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎界面</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>

<body style="margin: 1%;text-align: center;">
<div id="admin">
    <h2>欢迎${user.username}进入系统</h2>
    <table class="table table-hover table-bordered  table-striped">
        <tr>
            <td>登录账号</td>
            <td>登录人</td>
            <td>登录时间</td>
        </tr>
        <tr>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td id="time"></td>
        </tr>
    </table>
</div>
</body>
<script>
    var date=new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour="00"+date.getHours();
    hour=hour.substr(hour.length-2);
    var minute="00"+date.getMinutes();
    minute=minute.substr(minute.length-2);
    var second="00"+date.getSeconds();
    second=second.substr(second.length-2);
    $(document).ready(function(){
        $("#time").html(year+"-"+month+"-"+day+hour+":"+minute+":"+second);
    });
</script>
</html>
