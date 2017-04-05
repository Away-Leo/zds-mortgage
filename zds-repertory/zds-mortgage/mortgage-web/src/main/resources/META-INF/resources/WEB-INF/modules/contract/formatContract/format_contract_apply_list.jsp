<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>
<title>格式化合同申领</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">申请人：</dt>
						<dd class="detail">
							<input class="zui-input" id="launchempname" name="bf|launchempname|LK|S">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态：</dt>
						<dd class="detail">
						<input class="zui-combobox" type="hidden" name="ap|applyStatus|E|S"
							data-toggle="combobox" data-multiple="false"
							data-valuefield="id" data-textfield="text"
							data-data="[{'id':'1','text':'未申请'},{'id':'2','text':'已申请'},
							{'id':'3','text':'可领用'},{'id':'4','text':'已领用'}]">
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
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.formatContractApplyList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
					<table>
						<tr>
							<th data-options="field:applyNo">申请单</th>
							<th data-options="field:totalCount">总份数</th>
							<th data-options="field:applyEmpNm">申请人</th>
							<th data-options="field:applyDate" formatter="dateFormat">申请时间</th>
							<th data-options="field:applyStatusNm">申请状态</th>
							<th data-options="field:id,width:200" formatter="operateFormat">操作</th>
						</tr>
					</table>
				</div>
				<div id="btn-function">
					<a class="zui-toolbar" id="btn-add" text="申请" buttonCls="btn-blue" handler="doAdd"></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form',
		           'zd/jquery.zds.message','zd/jquery.zds.combobox',
		           'zd/jquery.zds.table'], function($, CALLBACK,ZTools) {

			//操作
			CALLBACK.operateFormat = function(rowDate, index) {
				var html = '<a title="详情" onclick="doView" class="btn-blue">详情</a>';
				var status = rowDate.applyStatus;
				if(status == 1){
					html += '&nbsp;&nbsp;<a title="编辑" onclick="doEdit" class="btn-blue">编辑</a>';
				}
	            return html;
			};
			
			CALLBACK.dateFormat=function(rowData,index){
				return ZTools.strToDate(rowData.applyDate);
			}
			
			//查询
			$('#btn-search').click(function() {
				var formArray = $("#search_from").serializeArray();
				$('#tb-list').ZTable("reload",formArray);
			});
			
			//重置
			$("#btn-reset").click(function() {
                $.ZUI.resetForms('#search_from');
			});
			
			//新增
			CALLBACK.doAdd=function(){
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_add', '格式化合同申请', '<z:ukey key="com.zdsoft.finance.contract.editFormatContractApply" context="admin"/>');
			}
			//详情
			CALLBACK.doView=function(index, rowDate){
				var id = rowDate.id;
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_view', '格式化合同申请详情', '<z:ukey key="com.zdsoft.finance.contract.viewFormatContractApply" context="admin"/>&id='+id);
			}
			//编辑
			CALLBACK.doEdit=function(index, rowDate){
				var id = rowDate.id;
				ZDS_MESSAGE_CLIENT.openMenuLink('contract_edit', '格式化合同申请编辑', '<z:ukey key="com.zdsoft.finance.contract.editFormatContractApply" context="admin"/>&id='+id);
			}
	    	
	    	ZDS_MESSAGE_CLIENT.refreshThis = function () {
	    		var formArray = $("#search_from").serializeArray();
				$('#tb-list').ZTable("reload",formArray);
	    	};
			$.ZUI.init();
		});
		</script>
</body>
</html>