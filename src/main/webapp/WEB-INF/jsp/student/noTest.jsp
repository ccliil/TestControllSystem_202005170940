<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>考试公示</title>
    <style>
        div{
            text-align: center;
        }
    </style>
</head>
    <body>
    <div>
        <h3>${msg}</h3>
        <c:if test="${flag==true}">
            <a href="/student/test?uid=${user.id}">考试链接</a>
        </c:if>
    </div>

    </body>
</html>