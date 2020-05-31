<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>试卷查看</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<center>
    <h2>${test.tname}</h2><h5>总分：${test.totalScore}</h5><br />
</center>
<div>
    一、选择题<br />
    <c:forEach items="${chioceList}" var="chioce">
        <label>${chioce.account}</label><label>答案：${chioce.answer}</label><br />
        A、<label>${chioce.aitem}</label>&nbsp;&nbsp;&nbsp;&nbsp;B、<label>${chioce.bitem}</label>&nbsp;&nbsp;&nbsp;&nbsp;
       C、<label>${chioce.citem}</label>&nbsp;&nbsp;&nbsp;&nbsp;D、<label>${chioce.ditem}</label><br />
    </c:forEach>
    二、判断题<br />
    <c:forEach items="${judgeList}" var="judge">
        <label>${judge.account}</label>
        <c:if test="${judge.answer==0}">
            <label>答案：×</label>
        </c:if>
        <c:if test="${judge.answer==1}">
            <label>答案：√</label>
        </c:if><br />
        <input type="radio" value="√"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="×"/><br />
    </c:forEach>
    三、填空题<br />
    <c:forEach items="${vacantList}" var="vacant">
        <label>${vacant.account}</label><label>答案：${vacant.answer}</label><br/>
    </c:forEach>
</div>
</body>
</html>
