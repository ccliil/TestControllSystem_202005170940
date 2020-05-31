<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>试卷信息修改</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/teacher/testList/update" method="post" class="form-horizontal" style="width: 60%;">
    <input type="hidden" name="tid" value="${test.tid}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label">试卷名称</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="tname" value="${test.tname}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">使用专业</label>
        <div class="col-sm-10">
            <select class="form-control" name="majorid" value="${test.majorid}">
                <c:forEach items="${listMajor}" var="major">
                    <option value="${major.mid}">${major.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">创建人</label>
        <div class="col-sm-10">
            <select class="form-control" name="uid" value="${test.uid}">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">试卷总分</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="totalScore" value="${test.totalScore}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">是否被启用</label>
        <div class="col-sm-10">
            <c:if test="${test.isUsed==0}">
                <input type="radio" name="isUsed" checked="checked" value="0"/>未被启用&nbsp;&nbsp;
                <input type="radio" name="isUsed" value="1"/>已被启用
            </c:if>
            <c:if test="${test.isUsed==1}">
                <input type="radio" name="isUsed" value="0"/>未被启用&nbsp;&nbsp;
                <input type="radio" name="isUsed" value="1" checked="checked"/>已被启用
            </c:if>
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
