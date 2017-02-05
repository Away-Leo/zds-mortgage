<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>驻点人员审核</title>
</head>
<body>
	<div class="page-box">
		<form id="contract_addoredit_form" class="zui-form" method="post"
			enctype="multipart/form-data">
			<div class="page-title">基础信息</div>
			<div class="p10">
				<table class="table-detail">
					<tbody>
						<tr>
							<td class="td-title pct10">案件号</td>
							<td>1501-2015090008</td>
							<td class="td-title pct10">放款金额</td>
							<td>380000.00元</td>
							<td class="td-title pct10">放款期限</td>
							<td>1827天</td>
						</tr>
						<tr>
							<td class="td-title">抵押物（房产）</td>
							<td colspan="3">津南区双港镇梨双公路西北侧普泰花园7-2-1001</td>

							<td class="td-title"></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="page-title">外贸信托打印资料明细清单</div>
			<div class="mr10" style="text-align: right; margin-top: 2px;">
				<button type="button" class="btn-search-blue" id="btn-print1">打印</button>
			</div>
			<div class="p10">
				<jsp:include page='seal_control.jsp' flush="true">
					<jsp:param name="id" value="0" />
				</jsp:include>
			</div>
			<div class="page-title">现场驻点人员打印资料明细</div>

			<jsp:include page='seal_control.jsp' flush="true">
				<jsp:param name="id" value="1" />
			</jsp:include>
			<div class="p10"></div>

			<div class="page-title">附件</div>

			<div class="p10">
				<jsp:include page='file_list.jsp' flush="true"></jsp:include>
			</div>

			<div class="page-title">申请信息</div>
			<div class="p10">


				<%@ include file="seal_applyinfo.jsp"%>
			</div>
			<div class="page-title">快递单号</div>
			<div class="p10">
				<table class="table-detail">
					<tbody>
						<tr>
							<td class="td-title pct10">驻点寄出快递单号</td>
							<td><input class="zui-input" id="payFrom" name="payFrom">
							</td>
							<td class="td-title pct10"></td>
							<td></td>
							<td class="td-title pct10"></td>
							<td></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="page-title">审批意见</div>
			<div class="p10">
				<table class="table-detail">
					<tbody>

						<tr>
							<td class="td-title pct10" rowspan="2">驻点人员审核</td>
							<td class="td-title">受理人</td>

							<td>李四</td>
							<td class="td-title">审批意见</td>
							<td>同意</td>
							<td class="td-title">意见结论</td>

							<td>同意</td>
						</tr>
						<tr>

							<td class="td-title">审批意见:</td>
							<td colspan="5">
								审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见 <a>显示更多</a>
							</td>

						</tr>

						<tr>
							<td class="td-title pct10" rowspan="2">外贸信托审核</td>
							<td class="td-title">受理人</td>

							<td>李四</td>
							<td class="td-title">审批意见</td>
							<td>同意</td>
							<td class="td-title">意见结论</td>

							<td>同意</td>
						</tr>
						<tr>

							<td class="td-title">审批意见:</td>
							<td colspan="5">
								审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见 <a>显示更多</a>
							</td>

						</tr>
						<tr>
							<td class="td-title pct10" rowspan="2">外驻点盖章</td>
							<td class="td-title">受理人</td>

							<td>李四</td>
							<td class="td-title">审批意见</td>
							<td>同意</td>
							<td class="td-title">意见结论</td>

							<td>同意</td>
						</tr>
						<tr>

							<td class="td-title">审批意见:</td>
							<td colspan="5">
								审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见审批意见 <a>显示更多</a>
							</td>

						</tr>
						<tr>
							<td class="td-title pct10" rowspan="2">驻点寄出</td>
							<td class="td-title">操作:</td>
							<td>
								<dl class="form-item">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" id="bust2"
											name="bust2"
											data-data="[{'id':'0','text':'同意'},{'id':'1','text':'不同意'},{'id':'2','text':'退回'}]"
											data-valuefield="id" data-textfield="text" />
									</dd>
								</dl>
							</td>
							<td class="td-title"></td>
							<td></td>
							<td class="td-title"></td>

							<td></td>
						</tr>
						<tr>

							<td class="td-title">审批意见:</td>
							<td colspan="5"><textarea class="zui-area zui-validatebox"
									validate-type="Require" placeholder="最多可以输入200个字符"></textarea>
							</td>

						</tr>
					</tbody>
				</table>
			</div>
			<div class="form-btn">
				<button id="saveseal" type="button" class="btn-blue">保存</button>
				<button id="submitseal" type="button" class="btn-blue">提交</button>
			</div>
		</form>
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

						});
	</script>
</body>
</html>