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
						<dt class="title">申请人：</dt>
						<dd class="detail">
							<input class="zui-input" id="applyName" name="companyName|LK|S">

						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">资方：</dt>
						<dd class="detail">
							<input class="zui-input" id="payFrom" name="payFrom">

						</dd>
					</dl>

					<dl class="form-item">
						<dt class="title">资方类别：</dt>
						<dd class="detail">
						<label>
                              <input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType" type="hidden" value="${infoVo.capitalistType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00112"
                              data-valuefield="fullcode" 
                              data-textfield="name" >
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

		<div class="page-box">

			<div class="p10">
				<div id="tb-plan" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.reportContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
					<table>
						<tr>
							<th data-options="field:applyName">申请人</th>
							<th data-options="field:payFrom">资方</th>
							<th data-options="field:payFromClass">资方类别</th>
							<th data-options="field:contractStatus">状态</th>
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
		 seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.address', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
	            ],
							function($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

								//操作
								CALLBACK.operate = function(row, value) {
									html = "<a title='详请' class='icon-btn31 handler-icon c-orange' onclick='contactView'></a>";
									html += "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='contactEdit'></a>";
									html += "<a title='删除' class='icon-btn12 handler-icon c-gray' onclick='contactDel'></a>";
									return html;
								};
								
								

								//查询
								$('#btn-search').click(
										function() {
											var formArray = $("#search_from")
													.serializeArray();
											$('#projectTable').ZTable("reload",
													formArray);
										});
								//重置
								$("#btn-reset").click(function() {
									$("#search_from")[0].reset();
								});
								
								CALLBACK.doAdd=function(){
				                	ZDS_MESSAGE_CLIENT.openMenuLink('contract_add', '机构合同报备申请', '<z:ukey key="com.zdsoft.finance.contract.addReportContractApply" context="admin"/>');
								}
								
		 $.ZUI.init();
		 }
		 
		 
		 );
								
								/* CALLBACK.addContract=function(){
									alert("aa")
									ZDS_MESSAGE_CLIENT.openMenuLink('contract_add', '机构合同报备申请', '<z:ukey key="com.zdsoft.finance.contract.addContractApply" context="admin"/>');
									
								}  */
								
							
		</script>
</body>
</html>