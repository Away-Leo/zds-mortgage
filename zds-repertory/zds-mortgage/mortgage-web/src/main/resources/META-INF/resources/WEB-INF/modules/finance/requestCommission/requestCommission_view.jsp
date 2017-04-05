<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>支佣请款查看</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">
	<div class="page-box">
		<h1 class="page-title">支佣请款信息</h1>
		<div class="p10">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10"><b class="c-red ">*</b>单据号：</td>
					<td class="pct20">${funds.billCode}</td>
					<td class="td-title pct10"><b class="c-red ">*</b>请款金额：</td>
					<td >${funds.reqFundsAmount}</td>
					<td class="td-title pct10"><b class="c-red ">*</b>请款日期：</td>
					<td class="pct20" id="reqFundsDate_label">${funds.reqFundsDate}</td>
				</tr>
				<tr>
					<td class="td-title pct10"><b class="c-red ">*</b>摘要：</td>
					<td class="pct20 " colspan="5">${funds.summary}</td>
				</tr>
				<tr>
					<td class="td-title pct10">备注：</td>
					<td colspan="5">${funds.remark}</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- datagrid列表区域 -->
	<div class="page-box">
		<div class="page-title">案件费用列表</div>
		<div class="p10">
			<div id="zd-table1"></div>
		</div>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/tools', 'zd/jquery.zds.table' ],
						function($, ZTOOL) {
							var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsDetailList" context="admin"/>&reqFundsId|E|S=${funds.id}';//获取数据
							$("#reqFundsDate_label").html(ZTOOL.strToDate("${funds.reqFundsDate}"));
							$('#zd-table1').ZTable({
								url : url_data_list,
								singleSelect : true,//表格行是单选还是多选
								isRowNum : true,//是否显示行号
								pagination : true,//表格是否分页
								rows : 10,
								tableCls : 'table-index',//table的样式
								toolbar : [],
								columns : [ [
								{
									field : 'caseApplyCode',
									title : '案件号',
									width : 100
								}, {
									field : 'feeTypeName',
									title : '请款类型',
									width : 100
								}, {
									field : 'feeItemName',
									title : '请款项目',
									width : 100
								}, {
									field : 'receivedAmount',
									title : '可请款金额(元)',
									width : 100
								}, {
									field : 'expectedPayableAmount',
									title : '预收应付(元)',
									width : 100,hidden:true
								}, {
									field : 'paidAmount',
									title : '累计已付(元)',
									width : 100,hidden:true
								}, {
									field : 'reqFundsAmount',
									title : '请款金额(元)',
									width : 100
								} ] ]
							});
							$.ZUI.init();

						});
	</script>
</body>
</html>