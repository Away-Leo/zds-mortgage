<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公证</title>

<%@ include file="../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://192.168.18.253:8080/dev/static/assets/css/style.css"> -->
</head>
<body>
<!-- 搜索框 -->
<div class="page-box">
	<div class="page-title">查询信息</div>
	<div id="search" class="p5">
		<form id="Form" class="zui-form mt15">
			<dl class="form-item">
				<dt class="title">案件号:</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="caseApplyCode"
						name="ca|caseApplyCode|LK|S" value="">
					</label>
				</dd>
			</dl>

			<dl class="form-item">
				<dt class="title">主借人:</dt>
				<dd class="detail">
					<input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="customerName"
						name="cu|customerName|LK|S" value="">
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">主借人证件号:</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="credentialNo"
						name="cu|credentialNo|EQ|S" value="">
					</label>
				</dd>
			</dl>


			<dl class="form-item">
				<dt class="title">当前处理人:</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="operatorName"
						name="rec|operatorName|LK|S" value="">
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">资信员:</dt>
				<dd class="detail">
					<label> <input class="zui-input zui-validatebox"
						validate-type="Length[0-64]" type="text" id="creditMember"
						name="ca|riskManager|LK|S" value="">
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">产品分类：</dt>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productParentId" data-width="94" name="prc|id|EQ|S"
						data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productParentIdChange" data-height="300"
						data-defaultvalue="" data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
						id="productChildId" name="prp|id|EQ|S"" data-width="94"
						data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
						data-callback="" data-height="300" data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>

			</dl>

<!-- 			<dl class="form-item"> -->
<!-- 				<dt class="title">案件状态:</dt> -->
<!-- 				<dd class="detail"> -->
<!-- 					<input class="zui-combobox zui-validatebox" type="hidden" -->
<!-- 						id="institutionCode" name="institutionCode" -->
<%-- 						data-url="<z:ukey key="com.zdsoft.finance.findAllOrgSimpleCode" context="admin"/>&jsoncallback=?" --%>
<!-- 						data-callback="" data-height="300" data-defaultvalue="" -->
<!-- 						data-valuefield="id" data-textfield="text"> -->
<!-- 				</dd> -->
<!-- 			</dl> -->

		</form>
		<div class="form-btn">
			<button class="btn-blue" id="searchCaseTracking">查询</button>
			<button class="btn-gray" id="resetCaseTracking">重置</button>
		</div>
	</div>
</div>
<div class="page-box">
	<div class="page-title">案件跟踪表</div>
	<div class="p10">
		<div id="tb-product" class="zui-datagrid"
			zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.casetracking.getCaseTracking" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
			<table>
				<thead>
					<tr>
						<th data-options="field:caseApplyCode,width:5%">案件号</th>
						<th data-options="field:customerName,width:5%">主借人</th>
						<th data-options="field:credentialNo,width:10%">证件号码</th>
						<th data-options="field:categoryName,width:10%">产品分类</th>
						<th data-options="field:childName,width:10%">子产品</th>
						<th data-options="field:applyAmount,width:15%">申请金额(元)</th>
						<th data-options="field:creditMember,width:5%">资信员</th>
						<th data-options="field:nodeName,width:5%">当前处理人</th>
						<th data-options="field:operatorName,width:5%">当前节点</th>
						<th data-options="field:caseApplyStatus,width:5%">案件状态</th>
						<th data-options="field:developmentManagerName,width:5%">拓展经理</th>
						<th data-options="field:applyDate,width:10%"
							formatter="formatterDate">申请时间</th>
						<th data-options="field:id,width:5%" formatter="operate">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 日后做导出列表 -->
		<!-- 		<div id="btn-function">
	    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addCustomer"></a>
	    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
    </div> -->
	</div>
</div>
<script type="text/javascript">
seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.combobox','zd/jquery.zds.button', 'zd/jquery.zds.table',
			'zd/jquery.zds.form', 'zd/jquery.zds.message'],function($, CALLBACK,ZTOOlS) {

	//格式化时间
	CALLBACK.formatterDate = function(row, value) {
		return ZTOOlS.strToTime(value);
	};
	
    //下拉框初始化
//     $("#productParentId").ZCombobox();
//     $("#productChildId").ZCombobox();

	//搜索回调
	$('#searchCaseTracking').on('click',function() {
		var flag = $.ZUI.validateForm($('#Form'));
		if (flag) {
			var formArray = $("#Form").serializeArray();
			$('#tb-product').ZTable("reload",formArray);
		}
	});

	//重置回调
	$('#resetCaseTracking').on('click', function() {
		$('#gender').ZCombobox('setValue', '');
		$('#degree').ZCombobox('setValue', '');
		$('#clientNm').val('');
		var flag = $.ZUI.validateForm($('#Form'));
		if (flag) {
			var formArray = $("#Form").serializeArray();
			$('#tb-product').ZTable("reload",formArray);
		}
	});

	//操作格式化
	CALLBACK.operate = function(rowData, index) {
		var data = '<a href="javaScript:void(0)" onclick="detail"><button class="btn-blue">详情</button></a>'
		return data;
	};

	//详情页面
	CALLBACK.detail = function(index, row) {
		var editClientUrl = '<z:ukey key="com.zdsoft.finance.customer.editCustomer" context="admin"/>&jsoncallback=?&id='
				+ row.id;
		ZDS_MESSAGE_CLIENT.openMenuLink('编辑客户', '编辑客户',
				editClientUrl + "&openMethod=tabs");

	};
	
	/**
     * 下拉框联动
     * */
    CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
        var parentId = index;
       // debugger;
        loadProductChildId(parentId);
    };
   
    /**
     * 下拉数据
     * @param cataId
     */
    function loadProductChildId(pId) {
    	//debugger;
        var productChildId = $("#productChildId");
        productChildId.ZCombobox({queryParams: {"parentId": pId}});
    }
  //初始化
	$.ZUI.init();
    
});
</script>
</body>

</html>