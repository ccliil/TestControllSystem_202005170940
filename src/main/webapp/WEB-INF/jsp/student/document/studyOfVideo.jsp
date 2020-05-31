<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>视频学习</title>
    <script type="text/javascript " src="${ctx}/public/js/jquery.min.js"></script>
    <script type="text/javascript " src="${ctx}/public/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/bootstrap.css">

    <style>
        #play {
            width: 20%;
            text-align: center;
            position: relative;
            float: left;
            /*background-color: rosybrown;*/
            height: 560px;
        }

        #welcome {
            width: 80%;
            text-align: center;
            height: 60px;
            /*background-color: orchid;*/
            position: relative;
            float: left;
        }

        #video {
            width: 80%;
            height: 500px;
            text-align: center;
            position: relative;
            float: left;
            /*background-color: darkviolet;*/
        }
    </style>
</head>
<body>


<div id="play" >
    <ul id="content" >
        <c:forEach items="${list}" var="document">
            <li >${document.dataName}</li>
        </c:forEach>
    </ul>
</div>
<div id="welcome">
    <h3>欢迎${user.name}进入系统学习</h3>
</div>
<div id="video">
    <video width="100%" height="500px" controls="controls">
        <source src=""></source>
    </video>
</div>


<form action="/student/study/save" method="post" class="form-inline" style="padding: 10px;clear: both;">
    <input class="form-control" type="hidden" name="uid" value="${user.id}"/>
    <input class="form-control" type="hidden" name="videoNames" id="videoNames"/>
    <div id="codeCheck" class="form-group">
        <strong>请输入验证码</strong>
        <input class="form-control" type="text" id="code"/>&nbsp;&nbsp;&nbsp;&nbsp;
        <label id="getCode"></label>
    </div>
    <div class="form-group">
        <input class="btn btn-info" type="submit" value="保存学习进度"/>
    </div>
</form>

</body>
<script>
    function createCode() {
        var randomNum = "";
        for (var i = 0; i < 4; i++) {
            randomNum += Math.floor(Math.random() * 10);
        }
        return randomNum;
    }

    var total = 0;
    var resoult = "";
    var names = "";
    var time1 = 0, time2 = 0, time3 = 0;
    $(document).ready(function () {
        $("#codeCheck").hide();
        var ulList = $("#content li");
        $("#content li").click(function () {
            var count = $(this).index();
            resoult = ulList.eq(count).text();
            var ilocation = "video/" + resoult;
            $("#video video").remove();
            var video_id = "v_" + new Date().valueOf();
            $("#video").html('<video id="' + video_id + '" width="100%" height="500px" controls="controls"><source src="' + ilocation + '"> </video>');
            $("#" + video_id).bind("loadedmetadata", function () {
                total = this.duration; //获取总时长
                time1 = total * 1000 * 0.2;
                time2 = total * 1000 * 0.5;
                time3 = total * 1000 - (total * 1000 * 0.7);
                var timeout = setTimeout(function () {
                    $("#codeCheck").show();
                    $("#getCode").html(createCode());
                    var time = setTimeout(function () {
                        if ($("#code").val().trim() == $("#getCode").text().trim()) {
                            $("#codeCheck").hide();
                            var timeout2 = setTimeout(function () {
                                var list = document.getElementsByTagName("li");
                                for (var i = 0; i < list.length; i++) {
                                    if (list[i].innerHTML == resoult) {
                                        names += list[i].innerHTML + "//";
                                        $("#videoNames").val(names);
                                        list[i].innerHTML += "<lable>已完成</lable>";
                                    }
                                }
                            }, time3);
                        } else {
                            $("video").trigger("pause");
                            $("video")[0].currentTime = 0;
                            top.location.reload();
                            $("#codeCheck").hide();
                        }
                    }, time1);

                }, time2);
            });

        });
    });
</script>
</html>