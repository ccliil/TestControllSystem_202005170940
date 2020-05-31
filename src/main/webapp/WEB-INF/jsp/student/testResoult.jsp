<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>考试结果</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <style>
        div{
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <h4>${msg}</h4>
    试卷详情请查看<a href="/student/test/information?id=${user.id}">试卷详情</a>
</div>
</body>

</html>

