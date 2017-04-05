<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>我的待办</title>
</head>
<body>
<div id="myToDoForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">我的待办</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">流程名称:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="processNm" name="processNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">任务名称:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="taskName" name="taskName">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">标题:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="subject" name="subject">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">流程发起人:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="applyNm" name="applyNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">任务开始时间:</dt>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="startTime" name="startTime" validate-type="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})" readonly/>
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="endTime" name="endTime" validate-type="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
                        </label>
                    </dd>
                </dl>
				<dl class="form-btn">
					<button type="button" class="btn-blue" id="search">查询</button>
					<button type="button" class="btn-blue" id="reset">重置</button>
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<!-- <div class="page-title">我的待办</div> -->
		<div class="p10">
			<div id="tb-myToDo" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.workbench.toDo.getToDoList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:processNm,width:10%">流程名称</th>
            			<th data-options="field:taskName,width:8%">任务名称</th>
            			<th data-options="field:subject,width:6%">标题</th>
            			<th data-options="field:applyNm,width:5%">流程发起人</th>
            			<th data-options="field:applyTime,width:12%" formatter="changeDate">申请时间</th>
            			<th data-options="field:preTaskOperator,width:5%">上一节点处理人</th>
            			<th data-options="field:startTime,width:12%" formatter="changeDate1">任务开始时间</th>
            			<th data-options="field:dueTime,width:12%" formatter="changeDate2">任务计划结束时间</th>
            			<th data-options="field:duration,width:10%" formatter="getDuration">耗时</th>
            			<th data-options="field:assigneeNm,width:8%">处理人</th>
            			<th data-options="field:projectId,width:12%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
//流程图查看Url
var processViewUrl = '${processViewUrl}';
seajs.use([
           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog', 'zd/jquery.zds.combotree',
           'zd/jquery.zds.combobox','zd/jquery.zds.message','ztree', 'zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.validate', 'zd/jquery.zds.seleter'
           ], 
	 	function ($,ZTOOlS,CALLBACK,Zdialog) {
	var thisIframeId=window.name;
	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-myToDo').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
		$("#searchForm input").val("");
// 		$('#tb-myToDo').ZTable("reload", {});
    });
	
	CALLBACK.formatId=function(rowData,index){
		var id=rowData.projectId;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		return '<a href="javaScript:void(0)" onclick="handle"><button class="btn-blue">处理</button></a>'
    	+
    	'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="progress"><button class="btn-blue">进度</button></a>';
	}
	
	CALLBACK.changeDate = function(rowData,index) {
		var date = new Date(rowData.applyTime);
		return date.format('yyyy-MM-dd hh:mm');
	};
	
	CALLBACK.changeDate1 = function(rowData,index) {
		var date = new Date(rowData.startTime);
		return date.format('yyyy-MM-dd hh:mm');
	};
	
	CALLBACK.changeDate2 = function(rowData,index) {
		var date = new Date(rowData.dueTime);
		return date.format('yyyy-MM-dd hh:mm');
	};
	
	CALLBACK.getDuration = function(rowData,index) {
		var duration = "";
		var nowTime = new Date().getTime();
		var tim = nowTime-rowData.startTime;
		var day = parseInt(tim/86400000);
		if(day>0){
			duration = duration + day +'天';
		}
		var hour = parseInt((tim%86400000)/3600000);
		if(day>0 || hour>0){
			duration = duration + hour +'小时';
		}
		var minutes = parseInt((tim%86400000%3600000)/60000);
		
			duration = duration + minutes +'分钟';
		return duration;
	};

	
	//处理任务
	CALLBACK.handle = function(index,rowData) {
		ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask',
				rowData.taskName,
				rowData.framePageUrl + "&openMethod=tabs&parentIframeId="
						+ thisIframeId);
		//ZDS_MESSAGE_CLIENT.openMenuLink('myPendingTask'+taskCandidateId, taskName, url+"?taskCandidateId=" + taskCandidateId + "&taskCandidateId=" + taskCandidateId + "&openMethod=tabs&parentIframeId="+thisIframeId);
	}
	
	  
	
	CALLBACK.progress=function(index,rowData){
		ZDS_MESSAGE_CLIENT.openMenuLink('process_view', '进度', processViewUrl+ '?processInstId=' + rowData.procInstanceId);
	}
	
	//初始化页面
	$.ZUI.init();
});

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}  
</script>
</html>