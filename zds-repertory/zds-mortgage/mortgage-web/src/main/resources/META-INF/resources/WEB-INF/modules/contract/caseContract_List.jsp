<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<title>案件合同管理</title>
</head>
<body>
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
		<form id="search_from"  method="post"
			enctype="multipart/form-data" class="zui-form mt15">
			<dl class="form-item">
				<dt class="title">案件号：</dt>
				<dd class="detail">
					<input class="zui-input" id="caseApplyCode" name="mcp|caseApplyCode|LK|S">
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">机构：</dt>
				<dd class="detail">
					<input class="zui-input" id="payFrom" name="mcp|mechanismName|LK|S">

				</dd>
			</dl>

			<dl class="form-item">
			<dt class="title">产品分类：</dt>
			<dd class="detail">
				<label> 
					<input class="zui-combobox" type="hidden" data-width="94" data-callback="renderCombobox" id="productTypeId" name="mcp|productTypeId|E|S"
											data-url="<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?"
											data-valuefield="id" data-textfield="name">
				</label>
			</dd>
			<dd class="detail">
				<label> 
					<input class="zui-combobox" type="hidden" id="productSubtypeId" name="mcp|productSubtypeId|E|S" data-width="94">
				</label>
			</dd>
			</dl>
   
				<dl class="form-btn">
				<button type="button" class="btn-search-blue" id="btn-search">查询</button>
				<button type="button" class="btn-search-gray" id="btn-reset">重置</button>
			</dl>
		</form>
	</div>
</div>
		<!-- 列表 -->
<div class="page-box">
    <div class="page-title">案件合同列表</div>
    <div class="p10">
		<div id="tb-seal" class="zui-datagrid"
			zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.getCaseContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
			<table>
				<tr>
					<th data-options="field:CASEAPPLYCODE">案件号</th>
					<th data-options="field:MECHANISMNAME">机构</th>
					<th data-options="field:PRODUCTTYPENAME">产品分类</th>
					<th data-options="field:PRODUCTSUBTYPENAME">子产品</th>
					<th data-options="field:CUSTOMERNAME">主借人</th>
					<th data-options="field:APPLYAMOUNT" formatter="formatCurrency">贷款金额(元)</th>
					<th data-options="field:applyTermName">贷款期限（月）</th>

					<th data-options="field:id,width:200" formatter="operate">操作</th>
				</tr>
			</table>
		</div>
	</div>
</div>

	<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
        ], function ($, CALLBACK,ZTOOL,Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

							//操作
							CALLBACK.operate = function(row, value) {
								var html = "<a title='补充'  onclick='contractSupplement'><button class='btn-blue'><font><font>补充</font></font></button></a>";
								html += "<a title='打印' class='ml5'  onclick='contractPrint'><button class='btn-blue'><font><font>打印</font></font></button></a>";

								return html;
							};
							
							//产品分类下拉框
							CALLBACK.renderCombobox=function(id,name){
								
								if(!id){
								}else{
									$("#productSubtypeId").ZCombobox({
							            valueField: "id",
							            textField: "productName",
							            url:'<z:ukey key="com.zdsoft.finance.product.findProductListByCatId" context="admin"/>&jsoncallback=?&categoryId='+id,
							            onSelect:function(value,text,index){
							                $('#productSubtypeId').val(value);
							            }
							        });
								}
								
							};
							
							//金额分隔符
			                CALLBACK.formatCurrency=function(row, value) {
			                    return ZTOOL.formatCurrency(value+""); 
			                };

							$.ZUI.init();

							//查询
							$('#btn-search').click(
									function() {
										var formArray = $("#search_from")
												.serializeArray();
										$('#tb-seal').ZTable("reload",
												formArray);
									});
							//重置
							$("#btn-reset").click(function() {
								/* $("#search_from")[0].reset();
								$("#productTypeId").ZCombobox("setValue","");
								$("#productSubtypeId").ZCombobox("setValue","");
								$('#tb-seal').ZTable("reload", {}); */
								$.ZUI.resetForms('#search_from');
								$('#tb-seal').ZTable("reload", {});
							});

							CALLBACK.contractPrint = function(index, data) {
								console.log(data);
								ZDS_MESSAGE_CLIENT
										.openMenuLink('caseContractPrint',
												'打印',
												'<z:ukey key="com.zdsoft.finance.contract.caseContractPrint" context="admin"/>&operationType=print&caseApplyId='+data.CASEAPPLYID);

							};
							
							CALLBACK.contractSupplement = function(index, data) {
								ZDS_MESSAGE_CLIENT
										.openMenuLink('caseContractSupplement',
												'补充',
												'<z:ukey key="com.zdsoft.finance.contract.caseContractSupplement" context="admin"/>&caseApplyId='+data.CASEAPPLYID+"&id="+data.ID + "&capitalUseFor=" + data.CAPITALUSEFOR);

							}

						});
	</script>
</body>
</html>