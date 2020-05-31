<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>文件数据修改</title>
    <script type="text/javascript " src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript " src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/public/css/bootstrap.css">
</head>
<body style="padding: 1%;">
<form action="/document/update" method="post" class="form-horizontal" style="width: 60%;">
    <input class="form-control" type="hidden" name="id" value="${document.id}"/>

    <div class="form-group">
        <label class="col-sm-2 control-label">标题</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="title" value="${document.title}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">描述</label>
        <div class="col-sm-10">
            <textarea class="form-control" name="remark" cols="20" rows="5">${document.remark}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">文件名</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="dataName" value="${document.dataName}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">是否下载</label>
        <div class="col-sm-10">

            <c:if test="${document.isLoad==0}">
                <input type="radio" name="isLoad" value="0" checked="checked"/>不可下载 &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="isLoad" value="1"/>可下载
            </c:if>
            <c:if test="${document.isLoad==1}">
                <input type="radio" name="isLoad" value="0"/>不可下载 &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="isLoad" value="1" checked="checked"/>可下载
            </c:if>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">发布人</label>
        <div class="col-sm-10">
            <select class="form-control" name="uId" value="${document.uId}">
                <c:forEach items="${listUser}" var="user">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">适用专业</label>
        <div class="col-sm-10">
            <select class="form-control" name="majorId" value="${document.majorId}">
                <c:forEach items="${listMajor}" var="major">
                    <option value="${major.mid}">${major.name}</option>
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