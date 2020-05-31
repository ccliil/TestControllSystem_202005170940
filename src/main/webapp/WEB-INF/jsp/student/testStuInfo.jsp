<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>已考试卷</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <style>
        #right{
            position: relative;
            float: right;
        }
    </style>
</head>
<body>
<center><h3>${testInfo.test.tname}</h3></center>
<div id="right">
    ${testInfo.user.name}同学，你本次的考试成绩为${testInfo.score}
</div>
一、选择题<br/>
<c:forEach items="${chioceList}" var="chioce">
    <label>${chioce.account}</label>&nbsp;&nbsp;&nbsp;&nbsp;<label>${chioce.answer}</label><br/>
    A.<label>${chioce.aitem}</label><br/>B.<label>${chioce.bitem}</label><br/>C.<label>${chioce.citem}</label><br/>D.<label>${chioce.ditem}</label><br/>
</c:forEach>
选择题参考答案：${testInfo.cAnswerList}<br/>
二、判断题<br/>
<c:forEach items="${judgeList}" var="judge">
    <label>${judge.account}</label>&nbsp;&nbsp;&nbsp;&nbsp;
    <c:if test="${judge.answer==0}">
        <label>×</label><br/>
    </c:if>
    <c:if test="${judge.answer==1}">
        <label>√</label><br/>
    </c:if>
</c:forEach>

判断题参考答案：${testInfo.jAnswerList}<br/>
三、填空题<br/>
<c:forEach items="${vacantList}" var="vacant">
    <label>${vacant.account}</label>&nbsp;&nbsp;&nbsp;&nbsp;<label>${vacant.answer}</label><br />
</c:forEach>
填空题参考答案：${testInfo.vAnswerList}
</body>
</html>

