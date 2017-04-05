<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp'%>
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>格式化合同申领详情</title>
</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="edit_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-title">
					<h1 class="page-title">基本信息</h1>
				</div>
				<div class="page-box">
					<div class="p5">
			            <table class="table-detail">
							<tr>
			                    <td class="td-title pct10">申请单</td>
			                    <td class="pct20">
			                    	${vo.applyNo }
			                    </td>
			                    <td class="td-title pct10">机构名称</td>
			                    <td class="pct20">
			                    	${emp.orgNm}
			                    </td>
			                    <td class="td-title pct10">申请人状态</td>
			                    <td class="pct20">
			                    	${vo.applyStatusNm }
			                    </td>
							</tr>
							<tr>
			                    <td class="td-title pct10">驻点寄出快递单号</td>
			                    <td class="pct20">
			                    	${vo.trackingNoSend }
			                    </td>
			                    <td class="td-title pct10">合同退回信托快递单号</td>
			                    <td class="pct20">
			                    	${vo.trackingNoReceive}
			                    </td>
			                    <td class="td-title pct10"></td>
			                    <td class="pct20"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="page-title">
					<h1 class="page-title">打印资料明细清单</h1>
				</div>
				<div class="page-box">
					<div class="p10">
						<div id="tb-plan" class="zui-datagrid"
							zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.formatContractDetailList" context="admin"/>&jsoncallback=?&formatContractApplyId|E|S=${vo.id}","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
							<table>
								<tr>
									<th data-options="field:capitalistName">资方</th>
						            <th data-options="field:contractTypeName">合同类型</th>
						            <th data-options="field:contractName">合同名称</th>
						            <th data-options="field:contractCopies">份数</th>
								</tr>
							</table>
						</div>
					</div>
				</div>
				
				<div class="page-title">
					<h1 class="page-title">其他信息</h1>
				</div>
				<div class="page-box">
					<div class="p5">
			            <table class="table-detail">
							<tr>
			                    <td class="td-title pct10">申请人</td>
			                    <td class="pct20">
			                    	<input class="zui-input zui-validatebox" disabled="disabled" value="${emp.empNm}">
			                    	<input type="hidden" name="applyEmpCode" value="${emp.empCd}"/>
			                    </td>
			                    <td class="td-title pct10">申请时间</td>
			                    <td class="pct20">
			                    	<label>
			                            <input type="zui-input zui-validatebox" disabled="disabled" id="showApplyData" value="${vo.applyDate}"/>
			                            <input type="hidden" name="applyDate" value="${vo.applyDate}"/>
				                    </label>
								</td>
							</tr>
							<tr>
				         		<td class="td-title pct10">备注</td>
				                <td colspan="3">
				                    <label>
				                    	<textarea class="zui-area row-width" id="remark" name="remark" disabled="disabled" validate-type="Require,Length[0-200]" placeholder="最多可以输入200个字符">${vo.remark }</textarea>
				                    </label>
			                   	</td>
							</tr>
						</table>
					</div>
				</div>
				<input type="hidden" name="id" id="formatContractApplyId" value="${vo.id }">
			</form>
		</div>
	</div>
	<div id="contactsDialog"  style="display: none">
	<script type="text/javascript">
		seajs.use(['jquery','zd/tools', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox',
		           'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 
		           'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
		           ],function($, ZTOOlS,CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
            $("#showApplyData").val(ZTOOlS.strToDate("${vo.applyDate}"));
		 	$.ZUI.init();
		 });
	</script>
</body>
</html>