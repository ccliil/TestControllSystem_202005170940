<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>考生试卷数据展示</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<center>
    <h2>${testInfo.test.tname}</h2>
</center>
<p><h4>考生姓名：${testInfo.user.name}&nbsp;&nbsp;&nbsp;&nbsp;考生得分：${testInfo.score}&nbsp;&nbsp;&nbsp;&nbsp;考试日期：${testInfo.createTime}</h4></p>
一、选择题<br/>
<c:forEach items="${chioceList}" var="chioce">
    <label>${chioce.account}</label>&nbsp;&nbsp;参考答案：<label>${chioce.answer}</label><br/>
    A、<label>${chioce.aitem}</label>&nbsp;&nbsp;&nbsp;&nbsp;B、<label>${chioce.bitem}</label>&nbsp;&nbsp;&nbsp;&nbsp;
    C、<label>${chioce.citem}</label>&nbsp;&nbsp;&nbsp;&nbsp;D、<label>${chioce.ditem}</label><br/>

</c:forEach>
<h3>考生选择题答案：${testInfo.cAnswerList}</h3><br/>
二、判断题<br/>
<c:forEach items="${judgeList}" var="judge">
    <label>${judge.account}</label>&nbsp;&nbsp;参考答案：
    <c:if test="${judge.answer==0}"> <label>×</label><br /></c:if>
    <c:if test="${judge.answer==1}"> <label>√</label><br /></c:if>
</c:forEach>
<h3>考生判断题答案：${testInfo.jAnswerList}</h3><br/>
三、填空题<br/>
<c:forEach items="${vacantList}" var="vacant">
    <label>${vacant.account}</label>&nbsp;&nbsp;参考答案：<label>${vacant.answer}</label><br />
</c:forEach>
<h3>考生填空题答案：${testInfo.vAnswerList}</h3>
</body>
</html>