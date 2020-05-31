<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="${ctx}/public/js/jquery.min.js"></script>
    <script src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
</head>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="./index.html">安全管理培训系统</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${user.name}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('个人信息','/user/login/userInfo')">个人信息</a></dd>
                <dd><a onclick="x_admin_show('个人信息修改','/user/updatePersonInfo')">个人信息修改</a></dd>
                <!-- <dd><a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a></dd>-->
                <dd><a href="/user/logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a>欢迎登录</a></li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/user/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户列表</cite>

                        </a>
                    </li >
                    <li>
                        <a _href="/user/addUser">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加用户</cite>

                        </a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>题库管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/test/chioce/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>选择题列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="/test/chioce/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加选择题</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="/test/judge/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>判断题列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="/test/judge/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加判断题</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="/test/vacant/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>填空题列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="/test/vacant/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加填空题</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>文件管理中心</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/document/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>文档查询</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="/document/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>上传文档</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>考试时间管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="/test/timeControl">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>设置考试时间</cite>
                        </a>
                    </li >

                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/user/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2019 x-admin v1.0 All Rights Reserved</div>
</div>
<!-- 底部结束 -->

</body>

</html>
