<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 跟进催收单 -->
	<div class="page-box">  
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
	                    <td class="pct20">
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
			</div>     
    
<script type="text/javascript">  
	
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
		function($, CALLBACK) {   
		//跟催状态控制显示隐藏
		if($("#supervisd").html()=="是"){    
			$("#superviseDetail").css("display","");
			$("#wh1").css("display","");
			$("#wh2").css("display","");
			$("#yy1").css("display","none");
			$("#yy2").css("display","none");
		}
		else{  
			$("#superviseDetail").css("display","none");
			$("#wh1").css("display","none");
			$("#wh2").css("display","none");
			$("#yy1").css("display","");
			$("#yy2").css("display","");
		}
		$.ZUI.init();
	});
</script>
