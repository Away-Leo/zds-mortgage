<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value=""> 
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="caseApplyCode" name="caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="customerName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" name="caseLimitApplyVo.limitStatus" data-width="128"
													data-data="[{'id':'0','text':'未申请额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" name="caseLimitApplyVo.limitStatus" data-width="128"
													data-data="[{'id':'0','text':'未申请额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">拓展经理：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="developmentManager" name="productTypeId|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">风控状态：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" name="caseLimitApplyVo.limitStatus"
													data-data="[{'id':'0','text':'未申请额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn_search" value="查询" />
					<input type="button" class="btn-search-blue" id="btn_reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="caseApplyTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.receivablePlanManager.receivablePlanCaseList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" ,"toolbar":"#b"}'>
				<table>
        			<tr>
            			<th data-options="field:caseApplyCode">案件号</th>
            			<th data-options="field:mechanismName">机构</th>
            			<th data-options="field:customerName">主借人</th>
            			<th data-options="field:productTypeName">产品分类</th>
            			<th data-options="field:productSubtypeName">子产品</th>
            			<th data-options="field:pledgeType">应收利息</th>
            			<th data-options="field:trustPlanName">机构审核</th>
            			<th data-options="field:groupDonfirm">集团确认</th>
            			<th data-options="field:applyLimit" >风控状态</th>
            			<th data-options="field:applyAmount">贷款金额（元）</th>
            			<th data-options="field:limitStatus">应收服务费（元）</th>
            			<th data-options="field:developmentManagerName">拓展经理</th>
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
	CALLBACK.operate = function(rowData,value){
		var data='<a href="javaScript:void(0)" onclick="beforeLoan"><button class="btn-blue">放款前</button></a>&nbsp;&nbsp;'+
    	'<a href="javaScript:void(0)" onclick="afterLoan"><button class="btn-blue">放款后</button></a>'
    	return data;
	};
	//操作
	CALLBACK.afterLoan = function(rowData,value){
		var initBeforeLoan = '<z:ukey key="com.zdsoft.finance.caseManager.initBeforeLoanPage" context="admin"/>&jsoncallback=?';
        ZDS_MESSAGE_CLIENT.openMenuLink('receivableInfoId','放款后',initBeforeLoan + "&openMethod=tabs&caseApplyId="+value.id);
		console.log(value);
	};
	
	//初始化页面
	$.ZUI.init();
	
	//查询
	$("#btn_search").click(function(){
		var formArray=$("#search_from").serializeArray();
		console.log(formArray);
		$("#caseApplyTable").ZTable("reload",formArray);
	});
	//重置
	$("#btn_reset").click(function(){
		$("#search_from")[0].reset();
	});
});
</script>
</html>