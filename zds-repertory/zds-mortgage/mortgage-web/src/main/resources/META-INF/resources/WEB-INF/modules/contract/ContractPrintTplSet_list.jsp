<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>套打模板配置</title>
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
	<div class="frm-content">
		<div class="page-box">
			<div class="p10">
				<form id="search_from" class="zui-form form-search" method="post"
					enctype="multipart/form-data" >
					<dl class="form-item">
						<dt class="title">模版名称：</dt>
						<dd class="detail">
							<input class="zui-input" id="tid" name="ccc|TEMPLATENAME|LK|S">

						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">所属机构：</dt>

						<dd class="detail">
							<input class="zui-combotree zui-validatebox" type="hidden"
								data-parent-value="false" name="ccc|BELOWORGCODE|IN|S"
								id="textBELOWORGCODE" data-multiple="false" data-defaultvalue=""
								data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
								data-valuefield="id" data-textfield="text">
						</dd>
					</dl>

					</dd>
					</dl>

					<dl class="form-item">
						<dt class="title">资金来源：</dt>

						<dd class="detail">
						
								<input class="zui-combobox" type="hidden"
									name="ccc|FUNDSOURCE|E|S" id="fundsource"
									data-url="<z:ukey key='com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode' context='admin'/>&jsoncallback=?"
									data-defaultvalue="" data-valuefield="id"
									data-textfield="capitalName">
						</dd>

					</dl>

					<dl class="form-item">
						<dt class="title">可用机构：</dt>
						<dd class="detail">
							<input class="zui-combotree zui-validatebox" type="hidden"
								data-parent-value="false" name="ccc|orgCode|IN|S" id="orgky"
								data-multiple="true" data-defaultvalue=""
								data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
								data-valuefield="id" data-textfield="text">

						</dd>
					</dl>


					<div class="form-btn">
						<button type="button" class="btn-blue" id="btn-search">查询</button>
						<button type="button" class="btn-gray" id="btn-reset">重置</button>
					</div>
				</form>
			</div>
		</div>
		
		<div class="page-box">
			<div class="p10">
			    <div id="tb-template" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.contractPrintTplSetList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
					<table>
						<tr>
							<th data-options="field:TEMPLATENAME">模版名称</th>
							<th data-options="field:ORGNAME">所属机构</th>
							<th data-options="field:productNames">适用产品分类</th>
							<th data-options="field:CAPITALNAME">资金来源</th>
							<th data-options="field:CANUSEORGNAME">可用机构</th>
							<th data-options="field:id,width:200" formatter="operate">操作</th>
						</tr>
					</table>
				</div>
				<div id="btn-function">
				    <a class="zui-toolbar" id="addzfPrintTemplate" text="新增模板" buttonCls="btn-blue" handler="addzfPrintTemplate"></a>
			    </div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		seajs.use(
						[ 'jquery', 'zd/jquery.zds.page.callback',
								'zd/jquery.zds.form', 'zd/jquery.zds.message',
								'zd/jquery.zds.table'],
						function($, CALLBACK) {

							CALLBACK.operate = function(row, value) {
								var html = "<a title='编辑'  onclick='edit'><button class='btn-blue'><font>编辑</font></button></a>";
								html += "<a title='删除'  onclick='del' class='ml10'><button class='btn-blue'><font>删除</font></button></a>";

								return html;
							};

							
							//添加
							CALLBACK.addzfPrintTemplate=function(){
								ZDS_MESSAGE_CLIENT
								.openMenuLink(
										'addzfPrintTemplateAdd',
										'新增模版',
										'<z:ukey key="com.zdsoft.finance.contract.ContractPrintTplSet_edit" context="admin"/>');
			                }
							//编辑
							CALLBACK.edit=function(index,row)
							{
								var url = '<z:ukey key="com.zdsoft.finance.contract.ContractPrintTplSet_edit" context="admin"/>&jsoncallback=?&ContractPrintTplSet_id='+row.ID;
								ZDS_MESSAGE_CLIENT.openMenuLink(
										'editzfPrintTemplate',
										'编辑',
										url);	
							}
							//删除
							CALLBACK.del=function(index,row)
							{
								var id=row.ID;
								var deleteUrl = '<z:ukey key="com.zdsoft.finance.contract.ContractPrintTplSet_del" context="admin"/>';
								
								 $.ZMessage.question("确认", "确认删除？", function () {
										$.get(deleteUrl,{id : id},function(result) {
											
											if (result.resultStatus == 0) {$.ZMessage.info("成功","数据删除成功",function() {
												setTimeout(function(){
													doSearch();});
												
															})
																				} else {
																					$.ZMessage.error("错误","删除数据错误"+ result.msg,
																									function() {
																										return false;
																									});
																				}
																			});
										 });
								//doSearch();
							}
							
							
							function doSearch()
							{
								var flag = $.ZUI.validateForm($('#search_from'));
								if (flag) {
											var formArray = $("#search_from").serialize();
											formArray = decodeURIComponent(formArray, true);
											$('#tb-template').ZTable("reload",formArray);
								}
								
							}
							//查询
							$('#btn-search').on('click',doSearch);

							 ZDS_MESSAGE_CLIENT.refreshThis=function(){
									doSearch();
							   }; 
							   
							 $('#btn-reset').click(function(){

							        $.ZUI.resetForms('#search_from');//重置
							 });
							   
							$.ZUI.init();
						});
	</script>

</body>
</html>