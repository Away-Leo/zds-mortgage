<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跟催</title>
<%@ include file="../../common/common_js.jsp" %>
</head> 
<body>
	<div class="frm-content frm-bottom" id="opinionDiv">
	<form id="search" class="zui-form" action="javascript:void(0);" >
	
	 <!-- 最近跟催信息 -->       
   <%@ include file="../loanMiddleMonitor/lately_followInfo_list.jsp"%>  
	
		<!-- 跟进催收单 -->
	    <div class="page-box">
	        <div class="page-title">跟进催收单</div>
	        <div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct10">跟催方式</td>
	                    <td class="pct20">
	                    	${follow.followType}
	                    </td>
	                    <td class="td-title pct10">跟催状态</td>
	                    <td class="pct20" id="followStatus">
	                   	 	${follow.followStatus}
	                    </td>
	                    <td class="td-title pct10" id="wh1">外呼对象</td>
	                    <td class="pct20" id="wh2">
	                    	${follow.callOutObject}
	                    </td>
	                    <td class="td-title pct10" id="yy1">无效原因</td>
	                    <td class="pct20" id="yy2">
	                    	${follow.invalidReason}
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">处置预案</td>
	                    <td class="pct20">
		                    ${follow.handlePlan}
	                    </td>
	                    <td class="td-title pct10">子目录</td>
	                    <td class="pct20">
	                    	${follow.childHandlePlan}
	                    </td>
	                    <td class="td-title pct10">跟踪部门</td>
	                    <td class="pct20">
	                    	${follow.departmentName}
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">预计还款日期</td>
	                    <td class="pct20">
	                    	${follow.predictRepayDate}
	                    </td>
	                    <td class="td-title pct10">预计下次跟进日期</td>
	                    <td class="pct20">
	                    	${follow.pretNextFlUpDate}
	                    </td>
	                    <td class="td-title pct10">是否发起督办</td>
	                    <td class="pct20" id="supervisd">
	                   		${follow.supervisd}
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">跟催情况</td>
	                    <td class="pct20" colspan="5">
	                    	${follow.followCondiction}
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">跟催人</td>
	                    <td class="pct20">
	                    	${follow.employeeName}
	                    </td>
	                    <td class="td-title pct10">跟催日期</td>
	                    <td class="pct20">
	                    	${follow.followDate}
	                    </td>
	                    <td class="td-title pct10"></td>
	                    <td class="pct20"></td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    <div id="superviseDetail">
	    <!-- 督办事项 -->
		   <%@ include file="../afterSupervise/after_supervise_common_view.jsp"%>  
		 </div>
	</form>
	</div> 
	 
<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
, function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
	//跟催状态控制显示隐藏
	debugger;
	if($("#supervisd").html().trim()=="是"){
		$("#superviseDetail").css("display","");
	}
	else{
		$("#superviseDetail").css("display","none");
	}
	//根据跟催状态
	if($("#followStatus").html().trim()=="有效"){
		$("#wh1").css("display","");
		$("#wh2").css("display","");
		$("#yy1").css("display","none");
		$("#yy2").css("display","none");
	}
	else{
		$("#wh1").css("display","none");
		$("#wh2").css("display","none");
		$("#yy1").css("display","");
		$("#yy2").css("display","");
	}
    	$.ZUI.init();

    });


</script>
</body>
</html>