<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>机构合同报备</title>
</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="contract_addoredit_form" class="zui-form" method="post"
				enctype="multipart/form-data">
				<div class="page-title">基本信息</div>

				<div class="p5">
					<table class="table-detail">
						<tbody>
							<tr>
								<td class="td-title pct10">案件号</td>
								<td>${vo.caseNo}</td>
								<td class="td-title pct10">接单日期</td>
								<td>${vo.jiedanDate}</td>
								<td class="td-title pct10">子产品</td>
								<td>${vo.subProduct}</td>
							</tr>
							<tr>
								<td class="td-title">拓展经理</td>
								<td>${vo.entendMNG}</td>
								<td class="td-title">拓展部门</td>
								<td></td>
								<td class="td-title">机构</td>
								<td>${vo.jigou}</td>
							</tr>
							<tr>
								<td class="td-title">申请金额（元）</td>
								<td></td>
								<td class="td-title">贷款期限</td>
								<td></td>
								<td class="td-title">还款方式</td>
								<td></td>
							</tr>
							<tr>
								<td class="td-title">贷款利率</td>
								<td></td>
								<td class="td-title">逾期利率</td>
								<td></td>
								<td class="td-title">终端</td>
								<td></td>
							</tr>
							<tr>
								<td class="td-title">资金来源</td>
								<td></td>
								<td class="td-title"></td>
								<td></td>
								<td class="td-title"></td>
								<td></td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="page-title">押品信息</div>
				<div class="p10">
					<div id="securityTable" class="zui-datagrid"
						zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.seal.securityList" context="admin"/>&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","pageSize":4}'>
						<table>
							<thead>
								<tr>
									<th data-options="field:communityName">小区名称</th>
									<th data-options="field:securityAddress">押品地址</th>
									<th data-options="field:securityYears">楼龄</th>
									<th data-options="field:area">面积</th>
									<th data-options="field:FCType">房产性质</th>
									<th data-options="field:FCProperty">房产权属</th>
									<th data-options="field:isSpruce">是否有装修</th>
									<th data-options="field:Evaluating">综合评估价</th>
								</tr>
							</thead>

						</table>
					</div>
				</div>
				<div class="page-title">外贸信托打印资料明细清单</div>
				<div class="mr10" style="text-align: right; margin-top: 2px;">
					<button type="button" class="btn-search-blue" id="btn-inport">导入</button>
					<button type="button" class="btn-search-blue" id="btn-print">打印</button>
				</div>

				<div class="p10">
					<jsp:include page='seal_control.jsp' flush="true">
						<jsp:param name="id" value="0" />
					</jsp:include>
				</div>


				<div class="page-title">现场驻点人员打印资料明细</div>

				<div class="mr10" style="text-align: right; margin-top: 2px;">
					<button type="button" class="btn-search-blue" id="btn-inport1">导入</button>
					<button type="button" class="btn-search-blue" id="btn-print1">打印</button>
				</div>
				<div class="p10">
					<jsp:include page='seal_control.jsp' flush="true">
						<jsp:param name="id" value="1" />
					</jsp:include>
				</div>

				<div class="page-title">附件</div>

				<div class="p10">
					<div id="filesTable" class="zui-datagrid"
						zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.seal.filesList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","rows":5}'>
						<table>
							<thead>
								<tr>
									<th data-options="field:belongClass">所属分类</th>
									<th data-options="field:className">类别名称</th>
									<th data-options="field:pingyingCode">拼音码</th>
									<th data-options="field:numCode">数字记忆码</th>
									<th data-options="field:fileName">文件名</th>
									<th data-options="field:documentName">文档名称</th>
									<th data-options="field:createBy">上传人</th>
									<th data-options="field:createDate">上传时间</th>
									<th data-options="field:from">来源</th>
									<th data-options="field:id,width:200" formatter="operate">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>

				<div class="page-title">申请信息</div>
				<div class="p10">
					<jsp:include page='file_list.jsp' flush="true"></jsp:include>

				</div>
			</form>

			<div class="form-btn">
				<button id="saveseal" type="button" class="btn-blue">保存</button>
				<button id="submitseal" type="button" class="btn-blue">提交</button>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		seajs
				.use(
						[ 'jquery', 'zd/jquery.zds.page.callback',
								'zd/jquery.zds.form', 'zd/jquery.zds.message',
								'zd/jquery.zds.dialog',
								'zd/jquery.zds.combobox',
								'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ],
						function($, CALLBACK) {

							CALLBACK.appendText = function(row, value) {
								var html = '<input class="zui-input" id="hetongbianma" value="'+value+'" />';

								return html;
							};
							CALLBACK.appendCheckBox = function(row, value) {

								var html = '<ul class="form-checkbox"><li data-name="0"><i class="icon-checkbox"></i><font><font>公章</font></font></li><li data-name="1"><i class="icon-checkbox"></i><font><font>合同章</font></font></li><li data-name="2"><i class="icon-checkbox"></i><font><font>法人章</font></font></li><li data-name="2"><i class="icon-checkbox"></i><font><font>授权章</font></font></li></ul>';

								return html;
							};
							CALLBACK.appendTextAera = function(row, value) {

								var html = '<textarea class="zui-area zui-validatebox" validate-type="Require" placeholder="最多可以输入200个字符" style="width: 200px !important;height:35px !important;">'
										+ value + '</textarea>';

								return html;
							};
							CALLBACK.operate = function(row, value) {

								var html = '<button class="btn-blue mr10">查看</button>';
								html += '<button class="btn-blue mr10">编辑</button>';
								html += '<button class="btn-blue mr10">删除</button>';
								return html;
							}
							$.ZUI.init();
							$("#btn-inport").click(function() {
								alert("btn-inport");

							});

						});
	</script>
	</ body>
</html>
