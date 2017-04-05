<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>我的草稿</title>
</head>
<body>
<div id="myDraftForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">查询</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">申请类型：</dt>
					<dd class="detail">
						<input class="zui-combobox" type="hidden" id="modelType"
	             	        data-url="<z:ukey key='com.zdsoft.finance.workbench.getBusiModelTypes' context='admin'/>&jsoncallback=?"
	                        data-valuefield="fullCode" data-textfield="name" name="modelType|E|S">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">标题：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-60]" id="applyTitle" name="applyTitle|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" id="formStatus"
							data-url="<z:ukey key='com.zdsoft.finance.workbench.getBusiFormStatus' context='admin'/>&jsoncallback=?"
	                        data-valuefield="fullCode" data-textfield="name" name="formStatus|E|I">
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">申请时间：</dt>
                    <dd class="detail">
                        <label>
                        	<input type="text" class="zui-input width2-1 zui-validatebox" id="startTimeStr" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMddHHmmss',vel:'startTime',maxDate:'#F{$dp.$D(\'endTimeStr\')}'})">
                            <input type="hidden" id="startTime" name="applyDate|RE|L" />
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input type="text" class="zui-input width2-1 zui-validatebox" id="endTimeStr" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMddHHmmss',vel:'endTime',minDate:'#F{$dp.$D(\'startTimeStr\')}'})">
                            <input type="hidden" id="endTime" name="applyDate|LE|L" />
                        </label>
                    </dd>
                </dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="search" value="查询" />
					<input type="button" class="btn-blue" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">我的申请</div>
		<div class="p10">
			<div id="myApply_table" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.workbench.myApplyListInfos' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:businessCode">业务编号</th>
            			<th data-options="field:applyTitle">标题</th>
            			<th data-options="field:modelTypeName">申请类型</th>
            			<th data-options="field:applyDate" formatter="formatDate">申请时间</th>
            			<th data-options="field:currentTaskName" formatter="formatNull">当前任务</th>
            			<th data-options="field:currentDealEmpNm" formatter="formatNull">当前处理人</th>
            			<th data-options="field:formStatusName">状态</th>
            			<th data-options="field:id,width:23%" formatter="formatOperate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
// 流程图查看Url
var processViewUrl = '${processViewUrl}';
seajs.use([
           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog', 'zd/jquery.zds.combotree',
           'zd/jquery.zds.combobox','zd/jquery.zds.message','ztree', 'zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.validate', 'zd/jquery.zds.seleter'
           ], 
	 	function ($,ZTOOlS,CALLBACK,Zdialog) {
	
	$('#search').on('click',function(){
       	var formArray=$("#searchForm").serialize();
       	formArray=decodeURIComponent(formArray, true);
        $('#myApply_table').ZTable("reload", formArray);
	});
	
	$('#reset').on('click',function(){
		$('#startTime').val('');
    	$('#endTime').val('');
    	$('#applyTitle').val('');
    	$('#modelType').ZCombobox('setValue','');
    	$('#formStatus').ZCombobox('setValue','');
		$('#myApply_table').ZTable("reload", {});
    });
	
	CALLBACK.formatOperate=function(rowData,value){
		var result = "";
		var status = rowData.formStatus;
		if (status == 0) {
			// 草稿
			result += '<a href="javaScript:void(0)" onclick="editApply"><button class="btn-blue">修改</button></a>';
			result += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="wasteApply"><button class="btn-blue">废弃</button></a>';
		} else if (status == 3 || status == 6 || status == 5) {
			// 废弃或规则拒绝,审批不通过也可以再次发起
			result += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="editApply"><button class="btn-blue">再次发起</button></a>';
		}
		if (rowData.processInstanceKey != null && rowData.processInstanceKey.length > 0) {
			// 有流程信息
			result += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="viewProcess"><button class="btn-blue">查看流程</button></a>';
		}
		// 查看
		result += '&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="viewApply"><button class="btn-blue">详情</button></a>';
		return result;
	}
	// 格式化日期
	CALLBACK.formatDate=function(rowData,value){
		return ZTOOlS.strToDate(value);
	}
	// 格式化空值
	CALLBACK.formatNull=function(rowData,value){
		if (value == null) {
			return "暂无";
		} else {
			return value;
		}
	}
	
	// 编辑
	CALLBACK.editApply=function(index,rowData){
		var url = rowData.editUrl
		if (url == null || url.length == 0) {
			$.ZMessage.error("错误","未定位到请求路径！");
			return;
		}
		url +="?businessKey=" + rowData.businessEntityId + "&projectId=" + rowData.componentsEntityId + "&processInstanceId=" + rowData.processInstanceKey + "&busiFormId=" + rowData.id;
		ZDS_MESSAGE_CLIENT.openMenuLink('edit_apply_info', '编辑申请', url);
	}
	// 废弃
	CALLBACK.wasteApply=function(index,rowData){
		var url = rowData.scrappedUrl
		if (url == null || url.length == 0) {
			$.ZMessage.error("错误","未定位到请求路径！");
			return;
		}
		$.ZMessage.warning("警告", "请确认是否废弃该申请？", function () {
			$.ajax({
                type: 'post',
                url: url,
               data: {
                	businessKey:rowData.businessEntityId,
                	projectId:rowData.componentsEntityId,
                	processInstanceId:rowData.processInstanceKey,
                	busiFormId:rowData.id
                }, 
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$.ZMessage.success("提示", "废除成功", function () {
                    		$('#myApply_table').ZTable("reload", {});
    	                });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
    	                    $(".zd-message").ZWindow("close");
    	                });
                    }
                },
                error: function () {
                	$.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            });
        });
	}
	
	// 查看流程
	CALLBACK.viewProcess=function(index,rowData){
		// 工作流提供对应的请求路径
		ZDS_MESSAGE_CLIENT.openMenuLink('process_view', '流程查看', processViewUrl+ '?processInstId=' + rowData.processInstanceKey);
	}
	// 查看详情
	CALLBACK.viewApply=function(index,rowData){
		var url = rowData.viewUrl
		if (url == null || url.length == 0) {
			$.ZMessage.error("错误","未定位到请求路径！");
			return;
		}
		url +="?businessKey=" + rowData.businessEntityId + "&projectId=" + rowData.componentsEntityId + "&processInstanceId=" + rowData.processInstanceKey + "&busiFormId=" + rowData.id;
		ZDS_MESSAGE_CLIENT.openMenuLink('view_apply_info', '申请详情', url);
	}
	ZDS_MESSAGE_CLIENT.refreshThis = function () {
		var formArray=$("#searchForm").serialize();
       	formArray=decodeURIComponent(formArray, true);
        $('#myApply_table').ZTable("reload", formArray);
    };
	//初始化页面
	$.ZUI.init();
});
</script>
</html>