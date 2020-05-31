<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.cdtu.entity.User" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title></title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <style type="text/css">

        #main{

            height: auto;

            width: 100%;

            margin: 20px auto;

        }

        #main>div{

            height: auto;

            width: 100%;

            background-color: lightpink;

            color: white;

            font-size: 30px;

            text-align: center;

            /* line-height: 300px;*/

            display: none;

        }

        #main>div:nth-child(2){

            background-color:yellow;

        }

        #main>div:nth-child(3){

            background-color:greenyellow;

        }

        /*#main>div:nth-child(4){

         background-color: #1E90FF;

        }*/

        input{

            margin: 13px;

            border-radius: 5px;

            background-color: coral;

            border: 1px solid dodgerblue;

        }

        .color{

            background-color: red;

        }
        #right{
            position: relative;
            float: right;
        }
        #left{
            position: relative;
            float: left;
        }


    </style>

</head>

<body>
<form action="/student/test" method="post">
    <input type="hidden" name="uid" value="${user.id}" />
    <input type="hidden" name="tid" value="${test.tid}"/>
    <input type="hidden" name="majorid" value="${user.major.mid}"/>
    <input type="hidden" name="chioceAnswers" id="chiocesList"/>
    <input type="hidden" name="judgeAnswers" id="judgesList"/>
    <input type="hidden" name="vacantAnswers" id="vacantsList"/>
    <input type="hidden" name="createTime" id="createTime"/>
    <center><h2>${test.tname}</h2></center><br/>
    <label id="right">
        <h3>剩余时间:<label id="time">30:00</label></h3>

    </label><br />
    <div id="main">
        <!--<button id="but1" value="选择题" class="color">选择题</button>-->
        <input type="button"  id="but1" value="选择题" class="color"/>
        <!--<button id="but12" value="判断题" class="color">判断题</button>-->
        <input type="button"  id="but2" value="判断题" />
        <!--<button id="but3" value="填空题" class="color">填空题</button>-->
        <input type="button"  id="but3" value="填空题" />

        <!--   <input type="button"  id="but4" value="胡歌" />-->

        <div style="display: block;">
            一、选择题<br />
            <label id="chiocesTitle">
                <c:forEach items="${chioceList}" varStatus="status" var="chioce" >
                    <label>${chioce.account}</label><br />
                    A.<input type="radio" name="chioces${status.count}" value="A"/>${chioce.aitem}<br />
                    B.<input type="radio" name="chioces${status.count}" value="B"/>${chioce.bitem}<br />
                    C.<input type="radio" name="chioces${status.count}" value="C"/>${chioce.citem}<br />
                    D.<input type="radio" name="chioces${status.count}" value="D"/>${chioce.ditem}<br />
                </c:forEach>
            </label>
        </div>

        <div>
            二、判断题<br />
            <label id="judgesTitle">
                <c:forEach items="${judgeList}" varStatus="status" var="judge">
                    <label>${judge.account}</label><br/>
                    <input type="radio" name="judges${status.count}" value="0"/>×&nbsp;&nbsp;&nbsp;<input type="radio" name="judges${status.count}" value="1"/>√<br />
                </c:forEach>
            </label>
        </div>

        <div>
            三、填空题<br />
            <label id="vacantsTitle">
                <c:forEach items="${vacantList}" var="vacant">
                    <label>${vacant.account}</label><br/>
                    <input type="text" name="answer${vacant.vid}" placeholder="请输入你到答案" /><br />
                </c:forEach>
            </label>
        </div>

    </div>
    <input type="submit" value="提交" id="up" />
</form>

</body>

<script type="text/javascript">

    //选项卡的原理，先让所有的都隐藏，然后让当前的显示

    var main=document.getElementById("main");

    //var but=main.getElementsByTagName("button");
    var but=$("input[type='button']");

    var div=main.getElementsByTagName("div");



    for (var i=0;i<but.length;i++) {

        but[i].index=i;//给每个按钮增加一个index，把index改成其他的也可以



        but[i].onclick=function(){//绑定点击事件



            for (var i=0;i<but.length;i++) {

                div[i].style.display="none";//让所有div隐藏

                but[i].className=" ";//用循环清除所有but的className

            }

            this.className="color";//给当前的but加上className，使其在点击时变色

            div[this.index].style.display="block";//让当前对应的div显示

        }
    }

    var resChioce="";
    var resJudge="";
    var resVacant="";
    var date=new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour="00"+date.getHours();
    hour=hour.substr(hour.length-2);
    var minute="00"+date.getMinutes();
    minute=minute.substr(minute.length-2);
    var second="00"+date.getSeconds();
    second=second.substr(second.length-2);
    $(document).ready(function(){
        $("#up").click(function(){
            $("#chiocesTitle input[type='radio']").each(function(){

                    if($(this).is(":checked")){
                        resChioce+=$(this).val()+",";
                    }
                });

            $("#judgesTitle input[type='radio']").each(function(){
                    if($(this).is(":checked")){
                        resJudge+=$(this).val()+",";
                    }
                });
            $("#chiocesList").val(resChioce);
            $("#judgesList").val(resJudge);
            $("#vacantsTitle input[type='text']").each(function(){
                resVacant+=$(this).val()+",";
            });
            $("#vacantsList").val(resVacant);
            $("#createTime").val(year+"-"+month+"-"+day+hour+":"+minute+":"+second);
        });
        var m=30;
        var s=0;
        var timer=setInterval(function(){
            if(s<10){
                //如果秒数少于10在前面加上0
                $('#time').html(m+':0'+s);
            }else{
                $('#time').html(m+':'+s);
            }
            s--;
            if(s<0){
                //如果秒数少于0就变成59秒
                s=59;
                m--;
            }

            if($("#time").html()=="0:00"){
                alert("考试时间到！！！");
                window.clearInterval(timer);
                $("#submit").click();
            }
        },1000)

    });


</script>

</html>