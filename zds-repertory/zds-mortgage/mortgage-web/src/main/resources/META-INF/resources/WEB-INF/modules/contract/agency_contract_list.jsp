<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../common/common_js.jsp'%>
<title>机构合同报备</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">标题：</dt>
						<dd class="detail">
							<input class="zui-input" id="applytitle" name="bf|applytitle|LK|S">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">申请人：</dt>
						<dd class="detail">
							<input class="zui-input" id="launchempname" name="bf|launchempname|LK|S">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态：</dt>
						<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" id="formStatus"
							data-url="<z:ukey key='com.zdsoft.finance.workbench.getBusiFormStatus' context='admin'/>&jsoncallback=?"
	                        data-valuefield="fullCode" data-textfield="name" name="bf|formStatus|E|S">
					</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-blue" id="btn-search">查询</button>
						<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
		</div>

		<div class="page-box">
			<div class="p10">
				<div id="tb-list" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.agencyContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
					<table>
						<tr>
							<th data-options="field:applyTitle">标题</th>
							<th data-options="field:applyType">申请类别</th>
							<th data-options="field:applyEmpNm">申请人</th>
							<th data-options="field:applyStatusNm">状态</th>
							<th data-options="field:id,width:200" formatter="operate">操作</th>
						</tr>
					</table>
				</div>
				<div id="btn-function">
					<a class="zui-toolbar" id="btn-add" text="申请" buttonCls="btn-blue" handler="doAdd"></a>
		    		<a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		seajs.use(['jquery', 'zd/jquery.zds.page.callback', 
		           'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 
		           'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 
		           'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
		           ],function($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			var exports_url = '<z:ukey key="com.zdsoft.finance.capital.exportSpareCapital" context="admin"/>&jsoncallback=?';
			//导出
			CALLBACK.exports = function(rowDate, index) {    
				var _rows = $('#tb-list').ZTable("getRows");
				if(_rows.length==0){
					$.ZMessage.info("提示", "机构合同报备数为0，不能导出！", function () {
                    });	 
				}else{
					window.location.href=exports_url;  
				}
			}
			//操作
			CALLBACK.operate = function(rowDate, index) {
				var html = '<a title="详情" onclick="doView" class="btn-blue">详情</a>';
				var status = rowDate.applyStatus;
				if(status == 0){
					html += '&nbsp;&nbsp;<a title="编辑" onclick="doEdit" class="btn-blue">编辑</a>';
					html += '&nbsp;&nbsp;<a title="删除" onclick="doDelete" class="btn-blue">删除</a>';
				}
	            return html;
			};
			
			//查询
			$('#btn-search').click(function() {
				var formArray = $("#search_from").serializeArray();
				$('#tb-list').ZTable("reload",formArray);
			});
			
			//重置
			$("#btn-reset").click(function() {
				$("#search_from")[0].reset();
				$("#formStatus").ZCombobox("setValue","");
			});
			
			//新增
			CALLBACK.doAdd=function(){
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_add', '机构合同报备申请', '<z:ukey key="com.zdsoft.finance.contract.editAgencyContract" context="admin"/>');
			}
			//编辑
			CALLBACK.doEdit=function(index, rowDate){
				var id = rowDate.id;
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_edit', '机构合同报备编辑', '<z:ukey key="com.zdsoft.finance.contract.editAgencyContract" context="admin"/>&id='+id);
			}
			//查看
			CALLBACK.doView=function(index, rowDate){
				var id = rowDate.id;
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_edit', '机构合同报备详情', '<z:ukey key="com.zdsoft.finance.contract.viewAgencyContract" context="admin"/>&id='+id);
			}
			//删除
	        CALLBACK.doDelete=function(index,row){
	        	$.ZMessage.question("提示", "是否删除", function (index) {
		            $.ajax({
		                type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.contract.delAgencyContract" context="admin"/>',
		                data: {id : row.id},
		                dataType: 'json',
		                success: function (data) {
		                	if (data.resultStatus == 0) {
		                		$.ZMessage.success("提示", data.msg, function () {});
		                		var formArray = $("#search_from").serializeArray();
		       				$('#tb-list').ZTable("reload",formArray);
		                	} else {
		          				$.ZMessage.error("错误", data.msg, function () {});
		          			}
		               }
		             });
	        	});
	    	 };
	    	
	    	ZDS_MESSAGE_CLIENT.refreshThis = function () {
	    		var formArray = $("#search_from").serializeArray();
				$('#tb-list').ZTable("reload",formArray);
	    	};
			$.ZUI.init();
		});
		</script>
</body>
</html>