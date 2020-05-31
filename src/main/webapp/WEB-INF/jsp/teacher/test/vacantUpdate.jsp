<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>填空题修改</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/vacant/update" method="post" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="vid" value="${vacant.vid}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="account" rows="5" cols="40">${vacant.account}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目描述</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="comment" rows="5" cols="40">${vacant.comment}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="answer" rows="5" cols="40">${vacant.answer}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目分值</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="score" value="${vacant.score}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">出题人</label>
        <div class="col-sm-10">
            <select class="form-control" name="uid" value="${vacant.user.id}">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题类</label>
        <div class="col-sm-10">
            <select class="form-control" name="majorId" value="${vacant.major.mid}">
                <c:forEach items="${listMajor}" var="major">
                    <option value="${major.mid}"> ${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-info" type="submit" value="提交"/>
        </div>
    </div>
</form>
</body>
</html>