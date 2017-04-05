<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构合同报备申请</title>
</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="contract_addoredit_form" class="zui-form " method="post"
				enctype="multipart/form-data">
				<div class="page-title">
					<h1 class="page-title">基本信息</h1>
				</div>
				<div class="page-box">
				<div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct10">标题</td>
	                    <td class="pct20" colspan="3">
	                    <label>
								<input  style="width:100%" class="zui-input zui-validatebox" validate-type="Require" name="contractName">
							</label>
	                    </td>
	                    <td class="td-title pct10">编号</td>
	                    <td class="pct20"><input type="hidden" name="contractNo" value="${vo.contractNo}"/></td>
					</tr>
					<tr>
	                    <td class="td-title pct10">申请人</td>
	                    <td class="pct20">xxxx</td>
	                    <td class="td-title pct10">申请人部门</td>
	                    <td class="pct20">xxxx</td>
	                    <td class="td-title pct10">申请时间</td>
	                    <td class="pct20">
	                    	<label>
		                            <input class="zui-input zui-validatebox"  validate-type="Require"  id="foundDate" value="${infoVo.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate'})">
		                            <input type="hidden" id="changeFoundDate" name="foundDate" value="${infoVo.foundDate }" />
		                    </label>
						</td>
					</tr>
					<tr>
	                    <td class="td-title pct10">机构</td>
	                    <td class="pct20">
	                    <input class="zui-combobox zui-validatebox" id="expenditureType"
							name="expenditureType" type="hidden"
							value=""
							data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00133"
							data-valuefield="fullcode" data-callback="getFeeItems"
							data-textfield="name" validate-type="Require">
						</td>
	                    <td class="td-title pct10">申请类别</td>
	                    <td class="pct20">xxxx</td>
	                    <td class="td-title pct10"></td>
	                    <td class="pct20"></td>
					</tr>
				</table>
				</div>
				</div>
				
				<div class="page-title">
					<h1 class="page-title">合同信息</h1>
				</div>
				<div class="page-box">
				<div class="p10">
				
				<div id="tb-plan" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.standardContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:capitalName">资方</th>
				            <th data-options="field:capitalType">资方类别</th>
				            <th data-options="field:contractType">合同类型</th>
				            <th data-options="field:contractName">合同名称</th>
				            <th data-options="field:contractType">文件类型</th>
				            <th data-options="field:attachmentName">附件</th>
				            <th data-options="field:id" formatter="contactFormat">操作</th>
						</tr>
					</table>
				</div>
				<div id="btn-function">
					<a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="doAdd"></a>
				</div>
				</div>
				<div class="p5">
	            			<table class="table-detail">
	            			<tr>
	            			<td class="td-title">备注</td>
	                    	<td colspan="7">
		                    
		                    <label>
		                            <textarea class="zui-area row-width" id="remark" name="remark" alidate-type="Require,Length[0-200]" placeholder="最多可以输入200个字符">${infoVo.remark }</textarea>
		                        </label>
	                    </td>
					</tr>
				</table>
			</div>
				
			</div>
			</form>
		            <div class="form-btn">
	                	<button id="saveMeet" type="button" class="btn-blue">保存</button>
	                </div>
		</div>
	</div>
	
	<script type="text/javascript">
		 seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.address', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
	            ],
							function($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

								//操作
								CALLBACK.contactFormat = function(row, value) {
									html = "<a title='编辑' class='icon-btn22 handler-icon c-green' onclick='contactEdit'></a>";
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
				                	ZDS_MESSAGE_CLIENT.openMenuLink('contract_add', '机构合同报备申请', '<z:ukey key="com.zdsoft.finance.contract.addContractApply" context="admin"/>');
								}
								
		 $.ZUI.init();
		 }
		 
		 
		 );
		</script>
</body>
</html>