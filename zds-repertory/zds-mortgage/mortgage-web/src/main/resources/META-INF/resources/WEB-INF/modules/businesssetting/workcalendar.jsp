<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../easyui/easyui.css">
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<style type="text/css">
	.calendar-sunday,.calendar-saturday {
		color : black;
		background-color:#4E99D9;
	}
	.calendar-header,.calendar-title{
		height:33px;
	}
	.calendar-header{
		background:#5caeff;
		font-size:15px;
	}
	.calendar-text span{
		height:29px;
	}
	.calendar-title span{
		height:29px;
		font-size:15px;
		line-height:29px;
	}
	.calendar{
		border-color:#5caeff;
	}
	.calendar table th{
    	font-size:18px;
	}
	.calendar table td {
    	font-size: 15px;
    }
</style>
<%@ include file='../common/common_js.jsp'%>
</head>

<body>
		
	<div style="margin:80px auto;width:100%;height: 100%">
		<div class="p5">
			<dl class="form-item" style="float: left;width: 100px">
				<dt class="title">休息日</dt>
				<dd class="detail" style="border:1px solid #000000;width:20px;height:20px;background-color:#4E99D9;"></dd>
			</dl>
			<dl class="form-item" style="float: left;width: 100px;">
				<dt class="title">工作日</dt>
				<dd class="detail" style="border:1px solid #000000;width:20px;height:20px;background-color:#ffffff;"></dd>
			</dl>
		</div>
		<div id="cc" style="margin:60px auto auto auto;">
		</div>
		<div style="margin: 60px auto auto auto;width: 50px;height: 30px">
			<input id="setDay" style="width:100px;height:30px;"  class="btn-blue" type="button" onclick="calendar.updateWorkDay()" value="设置为休息日">
		</div>
	</div>
</body>
<script>
	$('#cc').calendar({
	    current:new Date(),
	    weeks : ["日","一","二","三","四","五","六"],
	    months : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	    onSelect : function(){
			var currentDay=$(".calendar-selected");
			//根据当前选中日期为休息日或工作日控制按钮的value
			if(currentDay.css("background-color")=="rgb(78, 153, 217)"){
				$("#setDay").val("设置为工作日");
			}
			else{
				$("#setDay").val("设置为休息日");
			}
	    },
	    width : 1110,
	    height : 560
	});
	//日历对象
	var calendar={};
	//初始化日历
	calendar.init=function(){
		addListener();
		var allDays=$(".calendar-day");
		var count=0;
		var lastDay;
		var firstDay;
		var param={};
		//设置日历当前选择的日期，如果为当前月则选择当天。如为其他月份，则选择该月份第一天
		for(var i=0;i<allDays.length;i++){
			if($(allDays[i]).attr("class").indexOf("calendar-other-month")<0){
				var dayArray=$(allDays[i]).attr("abbr").split(",");
				if(!(dayArray[1]==new Date().getMonth()+1+"")){
					$("#cc").calendar("moveTo",new Date(dayArray[0], dayArray[1]-1, dayArray[2]));
				}
				else{
					$("#cc").calendar("moveTo",new Date());
				}
				break;
			}
		}
		allDays=$(".calendar-day");
		//查找当前月的第一天和最后一天
		for(var i=0;i<allDays.length;i++){
			if(count==0&&$(allDays[i]).attr("class").indexOf("calendar-other-month")<0){
				firstDay=calendar.convertFormat($(allDays[i]).attr("abbr"));
				var dayArray=$(allDays[i]).attr("abbr").split(",");
				count++;
			}
			if((count==1&&$(allDays[i]).attr("class").indexOf("calendar-other-month")>-1)||i==allDays.length-1){
				lastDay=calendar.convertFormat($(allDays[i-1]).attr("abbr"));
				break;
			}
		}
		param.firstDay=firstDay;
		param.lastDay=lastDay;
		
		//根据数据初始化日历的工作日和非工作日
		$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.parameter.getWorkCalendar"  context="admin"/>&jsoncallback=?',
			dataType : "json",
			data :  param,
			success : function(data){
				var days=data.rows;
				var j=0;
				for(var i=0;i<allDays.length;i++){
					if(days[j]){
						if(calendar.convertFormat($(allDays[i]).attr("abbr"))==days[j][0]){
							if(days[j][1]==0){
								$(allDays[i]).css("background-color","#4E99D9");
							}
							else{
								$(allDays[i]).css("background-color","white");
							}
							j++;
						}
					}
				}
				//根据当前选中日期为休息日或工作日控制按钮的value
				var currentDay=$(".calendar-selected.calendar-day");
				if(currentDay){
					if(currentDay.css("background-color")=="rgb(78, 153, 217)"){
						$("#setDay").val("设置为工作日");
					}
					else{
						$("#setDay").val("设置为休息日");
					}
				}
			}
		});
	}
	calendar.updateWorkDay=function(){
		//日历上 的当前日期
		var currentDay=$(".calendar-selected");
		var dayStr=currentDay.attr("abbr");
		var dayData=dayStr.split(",");
		var param={};
		//获取当前日期的状态，0为休息日，1为工作日
		if(currentDay.css("background-color")=="rgb(78, 153, 217)"){
			param.status=0;
		}
		else{
			param.status=1;
		}
		param.day=dayData[0]+(dayData[1].length>1?dayData[1]:"0"+dayData[1])+(dayData[2].length>1?dayData[2]:"0"+dayData[2]);
		$.ajax({
        	method : "post",
        	dataType : "json",
        	data : param,
        	url : '<z:ukey key="com.zdsoft.finance.parameter.updateWorkCalendar"  context="admin"/>&jsoncallback=?',
        	success : function(result){
        		if(result.resultStatus==0){
        			$.messager.alert('成功',result.msg);
        			if(currentDay.css("background-color")=="rgb(78, 153, 217)"){
            			currentDay.css("background-color","white")
            		}
            		else{
            			currentDay.css("background-color","#ff8040")
            		}
        		}
        		else{
        			$.messager.alert('失败',result.msg);
        		}
        	}
        			
        });
	}
	//转换日期格式
	calendar.convertFormat=function(day){
		var dayData=day.split(",");
		return dayData[0]+(dayData[1].length>1?dayData[1]:"0"+dayData[1])+(dayData[2].length>1?dayData[2]:"0"+dayData[2]);
	}
	
	
	//添加改变月份或者年份事件
	$(".calendar-text").click(function(){
		setTimeout("addListener()",200);
	});
	//添加日历上的各个事件
	function addListener(){
		$(".calendar-nav").unbind("click");
		$(".calendar-nav").click(function(){
			setTimeout("calendar.init()",200);
		});
		$(".calendar-other-month").unbind("click");
		$(".calendar-other-month").click(function(){
			setTimeout("calendar.init()",200);
		});
	}
	window.onload=calendar.init;
</script>
</html>