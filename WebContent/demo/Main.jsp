<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>欢迎页</title>
    <link rel="stylesheet" href="./frame/layui/css/layui.css">
    <link rel="stylesheet" href="./frame/static/css/style.css">
    <link rel="icon" href="./frame/static/image/code.png">
    <style type="text/css">
        *{margin:0px;padding:0px;}
 
/**
 * 设置日历的大小
 */
.calendar{
  width: 240px;
  height: 400px;
  display: block;
}
 
/**
 * 设置日历顶部盒子
 */
.calendar .calendar-title-box{
  position: relative;
  width: 100%;
  height: 36px;
  line-height: 36px;
  text-align:center;
  border-bottom: 1px solid #ddd;
}
 
/**
 * 设置上个月的按钮图标
 */
.calendar .prev-month {
  position: absolute;
  top: 12px;
  left: 0px;
  display: inline-block;
  width: 0px;
  height: 0px;
  border-left: 0px;
  border-top: 6px solid transparent;
  border-right: 8px solid #999;
  border-bottom: 6px solid transparent;
  cursor: pointer;
}
 
/**
 * 设置下个月的按钮图标
 */
.calendar .next-month {
  position: absolute;
  top: 12px;
  right: 0px;
  display: inline-block;
  width: 0px;
  height: 0px;
  border-right: 0px;
  border-top: 6px solid transparent;
  border-left: 8px solid #999;
  border-bottom: 6px solid transparent;
  cursor: pointer;
}
 
 
/* 设置日历表格样式 */
.calendar-table{
  width: 100%;
  border-collapse: collapse;
  text-align:center;
}
 
/* 表格行高 */
.calendar-table tr{
  height: 30px;
  line-height: 30px;
}
 
/* 当前天 颜色特殊显示 */
.currentDay {
  color: red;
}
 
/* 本月 文字颜色 */
.currentMonth {
  color: #999;
}
 
/* 其他月颜色 */
.otherMonth{
  color: #ede;
}
#div2{
    /*height: 70px;
    border: 1px solid black;
    background: #E6EFF7;*/
    position: relative;
    border-bottom: 1px dashed #d0d0d0;
    padding: 0 20px;
    background: #E6EFF7;
    border-top-right-radius: 3px;
    border-top-left-radius: 3px;
    height: 49px;
    line-height: 49px;
    top:-15px;
}
.div3{
    /*position: relative;*/
    float: left;
    /*padding: 0 10px;*/
    height: 50px;
}
.div4{
    margin: -10px 15px;
    float: left;
    height: 55px;
}
.div5{
    height: 80px;
    border-bottom:1px solid gray;
}
    </style>
</head>
<body class="body">
<!-- <p style="font-size: 20px;">个人主页</p> -->
<div class="layui-row layui-col-space10 my-index-main">
    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
        <div class="layui-collapse">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">个人信息</h2>
                <div class="layui-colla-content layui-show">
                    <div class="layui-form-item">

	<c:forEach items="${list }" var="q">
        <div class="layui-inline">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${q.emp_name }"  lay-verify="phone" autocomplete="off" class="layui-input" style="border: 1px;">
            </div>
        </div>
        </c:forEach>
        <c:forEach items="${list }" var="q">
        <div class="layui-inline">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${q.emp_sex }"  lay-verify="email" autocomplete="off" class="layui-input" style="border: 1px;">
            </div>
        </div>
        </c:forEach>
        <c:forEach items="${list }" var="q">
        <div class="layui-inline">
            <label class="layui-form-label">部门：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${q.department_name }" name="phone" lay-verify="phone" autocomplete="off" class="layui-input" style="border: 1px;">
            </div>
        </div>
        </c:forEach>
        <c:forEach items="${list }" var="q">
        <div class="layui-inline">
            <label class="layui-form-label">职位：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${q.position_name }" name="email" lay-verify="email" autocomplete="off" class="layui-input" style="border: 1px;">
            </div>
        </div>
        </c:forEach>
        <c:forEach items="${list }" var="q">
        <div class="layui-inline">
            <label class="layui-form-label">电话：</label>
            <div class="layui-input-inline">
                <input type="tel"value="${q.emp_tel }" name="phone" lay-verify="phone" autocomplete="off" class="layui-input" style="border: 1px;">
            </div>
        </div>
        </c:forEach>
    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6">
        <div class="layui-collapse">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">时间观念</h2>
                <div class="layui-colla-content layui-show">
                    <div class='calendar' id='calendar' style="width: 400px;height: 230px;">     
                    </div>
                            <div style="height: 300px;width: 100px;margin-left: 420px;margin-top: -300px;">
                        <div style="float: right;">
                            <p id="time" style="font-size: 20px; margin-top: 90px;"></p></div>
                            
                            
                   </div>

                </div>
            </div>
        </div>
    </div>
    

    <div class="layui-col-xs12 layui-col-sm6 layui-col-md4" style="width:1152px;"style="height:200px;">
        <div class="layui-collapse">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">消息</h2>
						
                <div class="layui-colla-content layui-show" style="height:100px" style="border:1px">
                暂无消息
                </div>
            </div>
        </div> 
    </div>



<script type="text/javascript" src="./frame/layui/layui.js"></script>
<script type="text/javascript" src="./js/index.js"></script>
<script type="text/javascript" src="./frame/echarts/echarts.min.js"></script>
<script type="text/javascript">
    layui.use(['element', 'form', 'table', 'layer', 'vip_tab'], function () {
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTab = layui.vip_tab
                , element = layui.element
                , $ = layui.jquery;

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main-line'));

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            title: {
                text: '示例'
            },
            tooltip: {},
            legend: {
                data: ['销量']
            },
            xAxis: {
                data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        });

        // 打开选项卡
        $('.my-nav-btn').on('click', function(){
            if($(this).attr('data-href')){
                //vipTab.add('','标题','路径');
                vipTab.add($(this),'<i class="layui-icon">'+$(this).find("button").html()+'</i>'+$(this).find('p:last-child').html(),$(this).attr('data-href'));
            }
        });

        // you code ...


    });

    var myChart = echarts.init(document.getElementById('main-line1'));
    
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            
            tooltip: {},
            legend: {
                data:['报销数']
            },
            xAxis: {
                data: ["差旅","交通","住宿","其他"]
            },
            yAxis: {},
            series: [{
                name: '报销数',
                type: 'bar',
                data: [5, 20, 36, 10]
            }]
        });
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form()
                    ,layer = layui.layer
                    ,layedit = layui.layedit
                    ,laydate = layui.laydate;
        });

        layui.use(['carousel', 'form'], function(){
            var carousel = layui.carousel
            ,form = layui.form;
            
            //常规轮播
            carousel.render({
              elem: '#test1'
              ,arrow: 'always'
            });
            
            //改变下时间间隔、动画类型、高度
            carousel.render({
              elem: '#test2'
              ,interval: 1800
              ,anim: 'fade'
              ,height: '120px'
            });
            
            //设定各种参数
            var ins3 = carousel.render({
              elem: '#test3'
            });
            //图片轮播
            carousel.render({
              elem: '#test10'
              ,width: '778px'
              ,height: '440px'
              ,interval: 5000
            });
            
            //事件
            carousel.on('change(test4)', function(res){
              console.log(res)
            });
            
            var $ = layui.$, active = {
              set: function(othis){
                var THIS = 'layui-bg-normal'
                ,key = othis.data('key')
                ,options = {};
                
                othis.css('background-color', '#5FB878').siblings().removeAttr('style'); 
                options[key] = othis.data('value');
                ins3.reload(options);
              }
            };
            
            //监听开关
            form.on('switch(autoplay)', function(){
              ins3.reload({
                autoplay: this.checked
              });
            });
            
            $('.demoSet').on('keyup', function(){
              var value = this.value
              ,options = {};
              if(!/^\d+$/.test(value)) return;
              
              options[this.name] = value;
              ins3.reload(options);
            });
            
            //其它示例
            $('.demoTest .layui-btn').on('click', function(){
              var othis = $(this), type = othis.data('type');
              active[type] ? active[type].call(this, othis) : '';
            });
          });

</script>
</body>
</html>
<script type="text/javascript">
    (function(){
  /*
   * 用于记录日期，显示的时候，根据dateObj中的日期的年月显示
   */
  var dateObj = (function(){
    var _date = new Date();    // 默认为当前系统时间
    return {
      getDate : function(){
        return _date;
      },
      setDate : function(date) {
        _date = date;
      }
    };
  })();
 
  // 设置calendar div中的html部分
  renderHtml();
  // 表格中显示日期
  showCalendarData();
  // 绑定事件
  bindEvent();
 
  /**
   * 渲染html结构
   */
  function renderHtml() {
    var calendar = document.getElementById("calendar");
    var titleBox = document.createElement("div");  // 标题盒子 设置上一月 下一月 标题
    var bodyBox = document.createElement("div");  // 表格区 显示数据
 
    // 设置标题盒子中的html
    titleBox.className = 'calendar-title-box';
    titleBox.innerHTML = "<span class='prev-month' id='prevMonth'></span>" +
      "<span class='calendar-title' id='calendarTitle'></span>" +
      "<span id='nextMonth' class='next-month'></span>";
    calendar.appendChild(titleBox);    // 添加到calendar div中
 
    // 设置表格区的html结构
    bodyBox.className = 'calendar-body-box';
    var _headHtml = "<tr>" + 
              "<th>日</th>" +
              "<th>一</th>" +
              "<th>二</th>" +
              "<th>三</th>" +
              "<th>四</th>" +
              "<th>五</th>" +
              "<th>六</th>" +
            "</tr>";
    var _bodyHtml = "";
 
    // 一个月最多31天，所以一个月最多占6行表格
    for(var i = 0; i < 6; i++) {  
      _bodyHtml += "<tr>" +
              "<td></td>" +
              "<td></td>" +
              "<td></td>" +
              "<td></td>" +
              "<td></td>" +
              "<td></td>" +
              "<td></td>" +
              "</tr>";
    }
    bodyBox.innerHTML = "<table id='calendarTable' class='calendar-table'>" +
              _headHtml + _bodyHtml +
              "</table>";
    // 添加到calendar div中
    calendar.appendChild(bodyBox);
  }
 
  /**
   * 表格中显示数据，并设置类名
   */
  function showCalendarData() {
    var _year = dateObj.getDate().getFullYear();
    var _month = dateObj.getDate().getMonth() + 1;
    var _dateStr = getDateStr(dateObj.getDate());
 
    // 设置顶部标题栏中的 年、月信息
    var calendarTitle = document.getElementById("calendarTitle");
    var titleStr = _dateStr.substr(0, 4) + "年" + _dateStr.substr(4,2) + "月";
    calendarTitle.innerText = titleStr;
 
    // 设置表格中的日期数据
    var _table = document.getElementById("calendarTable");
    var _tds = _table.getElementsByTagName("td");
    var _firstDay = new Date(_year, _month - 1, 1);  // 当前月第一天
    for(var i = 0; i < _tds.length; i++) {
      var _thisDay = new Date(_year, _month - 1, i + 1 - _firstDay.getDay());
      var _thisDayStr = getDateStr(_thisDay);
      _tds[i].innerText = _thisDay.getDate();
      //_tds[i].data = _thisDayStr;
      _tds[i].setAttribute('data', _thisDayStr);
      if(_thisDayStr == getDateStr(new Date())) {    // 当前天
        _tds[i].className = 'currentDay';
      }else if(_thisDayStr.substr(0, 6) == getDateStr(_firstDay).substr(0, 6)) {
        _tds[i].className = 'currentMonth';  // 当前月
      }else {    // 其他月
        _tds[i].className = 'otherMonth';
      }
    }
  }
 
  /**
   * 绑定上个月下个月事件
   */
  function bindEvent() {
    var prevMonth = document.getElementById("prevMonth");
    var nextMonth = document.getElementById("nextMonth");
    addEvent(prevMonth, 'click', toPrevMonth);
    addEvent(nextMonth, 'click', toNextMonth);
  }
 
  /**
   * 绑定事件
   */
  function addEvent(dom, eType, func) {
    if(dom.addEventListener) {  // DOM 2.0
      dom.addEventListener(eType, function(e){
        func(e);
      });
    } else if(dom.attachEvent){  // IE5+
      dom.attachEvent('on' + eType, function(e){
        func(e);
      });
    } else {  // DOM 0
      dom['on' + eType] = function(e) {
        func(e);
      }
    }
  }
 
  /**
   * 点击上个月图标触发
   */
  function toPrevMonth() {
    var date = dateObj.getDate();
    dateObj.setDate(new Date(date.getFullYear(), date.getMonth() - 1, 1));
    showCalendarData();
  }
 
  /**
   * 点击下个月图标触发
   */
  function toNextMonth() {
    var date = dateObj.getDate();
    dateObj.setDate(new Date(date.getFullYear(), date.getMonth() + 1, 1));
    showCalendarData();
  }
 
  /**
   * 日期转化为字符串， 4位年+2位月+2位日
   */
  function getDateStr(date) {
    var _year = date.getFullYear();
    var _month = date.getMonth() + 1;    // 月从0开始计数
    var _d = date.getDate();
     
    _month = (_month > 9) ? ("" + _month) : ("0" + _month);
    _d = (_d > 9) ? ("" + _d) : ("0" + _d);
    return _year + _month + _d;
  }
})();
</script>
<script type="text/javascript">
    function time(){
        var vDay;
        var date =  new Date();
        day = date.getDate();
        hours = date.getHours();
        minutes = date.getMinutes();
        seconds = date.getSeconds();

        document.getElementById("time").innerHTML="当前时间："+hours + ":" + minutes +":" + seconds;
    };
    setInterval("time()",1000);
</script>