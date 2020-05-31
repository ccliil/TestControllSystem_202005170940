<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.cdtu.entity.User" %> -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <style>
        html, body  {
            height: 100%;
            width: 100%;
            margin: 0 !important;
            padding: 0 !important;
        }

        h2, h3 {
            margin: 0 !important;
            padding: 0 !important;
        }

        body {
            background-color: #2b3137 !important;
            color: #fff !important;
        }

        #bg {
            position: relative;
            width: 500px;
            height: 500px;
            top: 50%;
            margin: 0 auto;
            transform: translateY(-50%);
        }
        .login{
            width: 450px;
            height: 270px;
            background-color: #fff;
            border: 1px solid #efefef;
            text-align: center;
            border-radius: 10px;
            color: #333 !important;
        }

        .login h3{
            margin: 0 !important;
            height: 80px;
            line-height: 80px;
        }
        a{
            text-decoration: none;
        }

        #top, #top h2{
            width: 100%;
            height: 120px;
            text-align: center;
            line-height: 120px ;
        }

        .form-horizontal .form-group {
            margin-right: 0 !important;
            margin-left: 0 !important;
        }

        .form-group {
            margin-bottom: 25px !important;
        }

        .loginbox {
            text-align:center;
        }

        .resetpassword {
            font-size: 12px;
            margin-left: 20px;
        }
    </style>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">
</head>
<body>
<div id="bg">
    <div id="top">
        <h2>欢迎进入系统</h2>
    </div>

    <div class="login">
        <h3>用户登录</h3>
        <!--             <form action="/user/login" method="post">
                        用户名：<input type="text" id="username" name="username" placeholder="请输入用户名" /><br/>
                        <br />
                        密&nbsp;&nbsp;码：<input type="password" id="password" name="password" placeholder="请输入密码" /><br/>
                        <br />
                        <input type="submit" value="登录" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="/user/resetPassword">忘记密码</a>
                    </form> -->
        <form class="form-horizontal" action="/user/login" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group loginbox">
                <button type="submit" class="btn btn-default">登录</button>
                <a class="resetpassword" href="/user/resetPassword">忘记密码</a>
            </div>
        </form>

    </div>
    <div id="message">${message}</div>

</div>
<script>

    msg=document.getElementById("message");
    if(msg.outerHTML==null || msg.outerHTML==""){
        msg.hidden;
    }

</script>
</body>
</html>