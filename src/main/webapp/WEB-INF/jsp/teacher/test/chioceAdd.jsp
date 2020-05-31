<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.ChioceTest" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>选择题修改</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/chioce/add" method="post" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="cid" value="${chioce.cid}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">题干</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="account" rows="5" cols="40"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">A</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="aitem" rows="5" cols="40"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">B</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="bitem" rows="5" cols="40"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">C</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="citem" rows="5" cols="40"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">D</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="ditem" rows="5" cols="40"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="answer"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题目分值</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="score"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">出题人</label>
        <div class="col-sm-10">
            <select class="form-control" name="uid">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">题类</label>
        <div class="col-sm-10">
            <select class="form-control" name="majorid">
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