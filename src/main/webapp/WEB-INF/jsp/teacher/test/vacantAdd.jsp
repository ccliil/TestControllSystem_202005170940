<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>判断题添加页面</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="margin: 1%;">
<form action="/teacher/vacant/add" method="post" class="form-horizontal" style="width: 60%;">
    <div class="form-group">
        <label class="col-sm-2 control-label">题目</label>
        <div class="col-sm-10">
            <textarea class="form-control"  name="account" rows="5" cols="40" placeholder="请输入添加的题目"></textarea><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目知识点</label>
        <div class="col-sm-10">
            <textarea class="form-control"  name="comment" rows="5" cols="40" placeholder="请输入添加题目的知识点描述"></textarea><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <textarea class="form-control"  name="answer" rows="5" cols="40" placeholder="请输入添加题目的答案"></textarea><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目分值</label>
        <div class="col-sm-10">
            <input class="form-control"  type="text" name="score" placeholder="请输入添加的该题分值"/><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">出题人</label>
        <div class="col-sm-10">
            <select class="form-control"  name="uid">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题类</label>
        <div class="col-sm-10">
            <select class="form-control"  name="majorId">
                <c:forEach items="${listMajor}" var="major">
                    <option value="${major.mid}">${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-info"  type="submit" value="提交"/>
        </div>
    </div>
</form>
</body>
</html>
