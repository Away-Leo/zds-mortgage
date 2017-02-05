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
	<div class="frm-content">
		<!-- 查询区域 -->
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">案件号：</dt>
						<dd class="detail">
							<input class="zui-input" id="applyName" name="companyName|LK|S">

						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">机构：</dt>
						<dd class="detail">
							<input class="zui-input" id="payFrom" name="payFrom">

						</dd>
					</dl>

					<dl class="form-item">
						<dt class="title">产品分类：</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="bust" name="bust"
								<%--                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=bust" --%>
                              data-data="[{'id':'0','text':'渤海信托'},{'id':'1','text':'外贸信托'}]"
								data-valuefield="fullcode" data-textfield="name"
								validate-type="Require">
						</dd>
					</dl>
					<dl class="form-btn">
						<button type="button" class="btn-search-

blue" id="btn-search">查询</button>
						<button type="button" class="btn-search-

gray" id="btn-reset">重置</button>
					</dl>
				</form>
			</div>
		</div>

		<!-- 列表 -->
		<div class="page-box">
			<div class="p10">
				<div id="tb-seal" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.getCaseContractList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:caseApplyCode">案件号</th>
							<th data-options="field:createOrgCd">机构</th>
							<th data-options="field:productTypeId">产品分类</th>
							<th data-options="field:productSubtype">子产品</th>
							<th data-options="field:zjr">主借人</th>
							<th data-options="field:caseAmount">贷款金额(元)</th>
							<th data-options="field:caseDeadline">贷款期限</th>

							<th data-options="field:id,width:200" formatter="operate">操作</th>
						</tr>
					</table>
				</div>
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
						function($, CALLBACK, ZUI_MESSAGE_CLIENT) {

							//操作
							CALLBACK.operate = function(row, value) {
								var html = "<a title='补充'  onclick='sealDetails'><button class='btn-blue'><font><font>补充</font></font></button></a>";
								html += "<a title='打印' class='ml5'  onclick='contractPrint'><button class='btn-blue'><font><font>打印</font></font></button></a>";

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

							CALLBACK.contractPrint = function(index, data) {

								ZDS_MESSAGE_CLIENT
										.openMenuLink('caseContractPrint',
												'打印',
												'<z:ukey key="com.zdsoft.finance.contract.caseContractPrint" context="admin"/>');

							}

						});
	</script>
</body>
</html>