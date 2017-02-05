<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../common/common_js.jsp'%>
<title>案件合同盖章列表</title>
</head>
<body>
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">合同编号：</dt>
						<dd class="detail">
							<input class="zui-input" id="hetongbianma"
								name="hetongbianma|LK|S">

						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">机构：</dt>
						<dd class="detail">
							<input class="zui-input" id="jigou" name="jigou|LK|S">
						</dd>
					</dl>

					<dl class="form-item">
						<dt class="title">合同状态：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="bust" name="bust"
								data-data="[{'id':'0','text':'渤海信托'},{'id':'1','text':'外贸信托'}]"
								data-valuefield="fullcode" data-textfield="name"
								validate-type="Require">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">案件状态：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="bust" name="bust"
								<%--                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=bust" --%>
                              data-data="[{'id':'0','text':'渤海信托'},{'id':'1','text':'外贸信托'}]"
								data-valuefield="fullcode" data-textfield="name"
								validate-type="Require">
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
				<div id="tb-seal" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.seal.sealList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:createOrgCd">机构</th>
							<th data-options="field:caseApplyCode">案件号</th>
							<th data-options="field:caseContractNo">合同编号</th>
							<th data-options="field:applyStatus">合同状态</th>
							<th data-options="field:trackingNoSend">驻点寄出快递单号</th>
							<th data-options="field:trackingNoReceive">机构退回快递单号</th>
							<th data-options="field:loanTotalAmount">放款金额(元)</th>
							<th data-options="field:loanStartDate">放款日期</th>
							<th data-options="field:caseApplyStatus">案件状态</th>
							<th data-options="field:id,width:200" formatter="operate">操作</th>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			seajs
					.use(
							[ 'jquery', 'zd/jquery.zds.page.callback',
									'zd/jquery.zds.form',
									'zd/jquery.zds.message',
									'zd/jquery.zds.dialog',
									'zd/jquery.zds.combobox',
									'zd/jquery.zds.table',
									'zd/jquery.zds.seleter' ],
							function($, CALLBACK, ZUI_MESSAGE_CLIENT) {

								//操作
								CALLBACK.operate = function(row, value) {
									var html = "<a title='详请' class='icon-btn22 c-green'  onclick='sealDetails'>详请</a>";
									html += "<a title='申请盖章' class='icon-btn22 c-green'  onclick='sealApply'>申请盖章</a>";
									html += "<a title='申请盖章' class='icon-btn22 c-green'  onclick='initZDSH'>驻点人员审核</a>";
									html += "<a title='外贸信托审核' class='icon-btn22 c-green'  onclick='initWMXTSH'>外贸信托审核</a>";
									html += "<a title='驻点盖章' class='icon-btn22 c-green'  onclick='initZDGZ'>驻点盖章</a>";
									html += "<a title='驻点寄出' class='icon-btn22 c-green'  onclick='initZDJC'>驻点寄出</a>";
									html += "<a title='驻点合同退回' class='icon-btn22 c-green'  onclick='initZDHERSendBack'>驻点合同退回</a>";
									html += "<a title='信托合同收回' class='icon-btn22 c-green'  onclick='initXTHERSendBack'>信托合同收回</a>";
									
									
									return html;
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
									$("#search_from")[0].reset();
								});

								CALLBACK.sealApply = function(index, data) {

									ZDS_MESSAGE_CLIENT
											.openMenuLink('seal_apply',
													'机构申请盖章',
													'<z:ukey key="com.zdsoft.finance.seal.apply" context="admin"/>');

								}
								CALLBACK.initZDSH = function(index, data) {

									ZDS_MESSAGE_CLIENT
											.openMenuLink('initZDSH', '驻点人员审核',
													'<z:ukey key="com.zdsoft.finance.seal.initZDSH" context="admin"/>');

								}
								CALLBACK.initWMXTSH = function(index, data) {
									ZDS_MESSAGE_CLIENT
											.openMenuLink('initWMXTSH',
													'外贸信托审核',
													'<z:ukey key="com.zdsoft.finance.seal.initWMXTSH" context="admin"/>');

								}
								CALLBACK.initZDGZ = function(index, data) {
									ZDS_MESSAGE_CLIENT
											.openMenuLink('initZDGZ', '驻点盖章',
													'<z:ukey key="com.zdsoft.finance.seal.initZDGZ" context="admin"/>');
								}

								CALLBACK.initZDJC = function(index, data) {
									ZDS_MESSAGE_CLIENT
											.openMenuLink('initZDJC', '驻点寄出',
													'<z:ukey key="com.zdsoft.finance.seal.initZDJC" context="admin"/>');
								}

								CALLBACK.initZDHERSendBack = function(index,
										data) {
									ZDS_MESSAGE_CLIENT
											.openMenuLink('initZDJC', '驻点合同退回',
													'<z:ukey key="com.zdsoft.finance.seal.initZDHERSendBack" context="admin"/>');
								}
								
								CALLBACK.initXTHERSendBack=function(index,data)
								{
									ZDS_MESSAGE_CLIENT
									.openMenuLink('initXTHERSendBack', '信托合同收回',
											'<z:ukey key="com.zdsoft.finance.seal.initXTHERSendBack" context="admin"/>');
									
								}

							});
		</script>
</body>
</html>