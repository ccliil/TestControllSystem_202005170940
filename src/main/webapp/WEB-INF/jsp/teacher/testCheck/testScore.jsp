<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>考卷成绩情况</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/js/echarts.min.js"></script>
    <style>
        #right{
            width: 100%;
            height: 500px;
        }
    </style>
</head>
<body>
<center><div><h3>${test.tname}成绩情况</h3></div></center>
<input type="hidden" id="data" value="${listNum}" />
<input type="hidden" id="nums" value="${test.listInfo.size()}"/>
<div id="right"></div><br />
<a href="/teacher/chioceInfo?tid=${test.tid}">选择题详情</a><br />
<a href="/teacher/judgeInfo?tid=${test.tid}">判断题详情</a><br />
<a href="/teacher/vacantInfo?tid=${test.tid}">填空题详情</a>
</body>
<script>
    var listNums;
    $(document).ready(function(){
        var	list=$("#data").val();
        list=list.replace(/\[|]/g,'');
        listNums =list.split(",");
        var myChart=echarts.init(document.getElementById('right'));
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (params) {
                    var tar = params[1];
                    return '考生总人数：'+ $("#nums").val()+ '<br/>' + tar.name + '人数 : ' + tar.value;
                }
            },
            xAxis: {
                type: 'category',
                data: ['90~100', '80~90', '70~80','60~70','未及格']
            },
            yAxis: {
                type: 'value'
            },
            series: [{ name: '辅助',
                type: 'bar',
                stack:  '总量',
                label: {
                    normal: {
                        show:true,
                        position: 'inside'
                    }
                },
                data: listNums
            },
                {
                    name: '生活费',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show:true,
                            position: 'inside'
                        }
                    },
                    itemStyle: {
                        normal: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        },
                        emphasis: {
                            barBorderColor: 'rgba(0,0,0,0)',
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    data:listNums
                }
            ]

        };
        myChart.setOption(option);
    });
</script>
</html>
