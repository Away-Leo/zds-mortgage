<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>档案管理</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">档案管理筛选条件</div>
		<div class="p10">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="projectCd" name="projectCd|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">客户名称：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerNm" name="customerNm|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品名称：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="productNm" name="productNm|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">案件状态：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="projectStatus" name="projectStatus|E|I">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
					<input type="button" class="btn-search-gray" id="btn-reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">案件</div>
		<div class="p10">
			<div id="projectTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.contractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:projectCd">案件号</th>
            			<th data-options="field:customerNm">客户名称</th>
            			<th data-options="field:productNm">产品名称</th>
            			<th data-options="field:productChild">子产品</th>
            			<th data-options="field:loanAmount">贷款金额(万元)</th>
            			<th data-options="field:bank">银行</th>
            			<th data-options="field:applyBy">申请人</th>
            			<th data-options="field:applyTime" formatter="formatterDate">申请时间</th>
            			<th data-options="field:projectStatus">案件状态</th>
            			<th data-options="field:id" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//格式化时间
	CALLBACK.formatterDate = function(row,value){
		return window.formatDate(row,value);
	};
	//操作
	CALLBACK.operate = function(row,value){
		var html = "<a title='入库' class='handler-icon icon-btn54' onclick='putStorage'></a>";
		html += "<a title='借出' class='handler-icon icon-btn55' onclick='loanOut'></a>";
		return html;
	};
	//入库
	CALLBACK.putStorage = function(index,data){
		console.log(data);
	};
	//借出
	CALLBACK.loanOut = function(index,data){
		console.log(data);
	};
	
	//初始化页面
	$.ZUI.init();
	//查询
	$('#btn-search').click(function(){
		var formArray=$("#search_from").serializeArray();
		$('#projectTable').ZTable("reload",formArray);
	});
	//重置
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
	});
});
</script>
</html>