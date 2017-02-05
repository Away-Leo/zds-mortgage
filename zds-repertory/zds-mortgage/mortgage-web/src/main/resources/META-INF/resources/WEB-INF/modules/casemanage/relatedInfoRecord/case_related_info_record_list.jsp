<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>征信录入</title>
</head>
<body>

	<div class="frm-content">
		<div class="page-box">
			<div id="search" class="p5">
				<form id="searchInfoRecordForm" class="zui-form form-search">
					<dl class="form-item">
						<dt class="title">案件号：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="caseApplyCode"
								name="caseApply.caseApplyCode|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">主借人：</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="borrowName"
								name="borrowName|LK|S">
							</label>
						</dd>
					</dl>
					
					<dl class="form-item">
                        <dt class="title">产品分类：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productParentId"
                                   data-width="94"
                                   name="caseApply.productTypeId|E|S"
                                   data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productChildId"
                                   name="caseApply.productSubtypeId|E|S" data-width="94"
                                   data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>

                    </dl>
					<dl class="form-item">
						<dt class="title">操作人:</dt>
						<dd class="detail">
							<label> <input class="zui-input" id="operator"
								name="operator|LK|S">
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">录入事项:</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="recordType"
								name="recordType|E|S" type="hidden"
								data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0063"
								data-valuefield="fullcode" data-textfield="name">
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">录入时间：</dt>
						<dd class="detail">
							<label> <input class="zui-input width2-1 zui-validatebox"
								type="text" id="recordStartDate" name="recordStartDate"
								validate-type=""
								onclick="WdatePicker({realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'_recordStartDate',maxDate:'#F{$dp.$D(\'recordEndDate\')}'})"
								readonly /> <input type="hidden" id="_recordStartDate"
								name="recordDate|RE|L" />
							</label>
						</dd>
						<span class="word">至</span>
						<dd class="detail">
							<label> <input class="zui-input width2-1 zui-validatebox"
								type="text" id="recordEndDate" name="recordEndDate"
								validate-type=""
								onclick="WdatePicker({realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'_recordEndDate',minDate:'#F{$dp.$D(\'recordStartDate\')}'})"
								readonly /> <input type="hidden" id="_recordEndDate"
								name="recordDate|LE|L" />
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态:</dt>
						<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="recordStatus"
								name="recordStatus|E|S" type="hidden"
								data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0064"
								data-valuefield="fullcode" data-textfield="name">
						</dd>
					</dl>
				</form>
				<div class="form-btn">
					<button class="btn-blue" id="btn-search">查询</button>
					<button class="btn-gray" id="btn-search">重置</button>
				</div>
			</div>
		</div>

		<!-- 列表区域 -->
		<div class="page-box">
			<div class="page-title">录入</div>
			<div class="p10">
				<div id="tb-case-apply-record" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.relatedinforecord.getCaseRecordList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:caseApplyCode"
								formatter="formatCaseApplyCode">案件号</th>
							<th data-options="field:mainBorrower">主借人</th>
							<th data-options="field:productTypeName"
								formatter="formatProductTypeName">产品分类</th>
							<th data-options="field:productSubtypeName"
								formatter="formatProductSubtypeName">子产品</th>
							<th data-options="field:applyAmount"
								formatter="formatApplyAmount">申请金额（元）</th>
							<th data-options="field:applyPeriod"
								formatter="formatApplyPeriod">贷款期限</th>
							<th data-options="field:recordDate">录入时间</th>
							<th data-options="field:recordTypeName">录入事项</th>
							<th data-options="field:recordStatusName">状态</th>
							<th data-options="field:id" formatter="operate">操作</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script>
		seajs
				.use(
						[ 'jquery', 'zd/jquery.zds.page.callback',
								'zd/jquery.zds.combobox',
								'zd/jquery.zds.dialog', 'zd/jquery.zds.button',
								'zd/jquery.zds.message', 'zd/jquery.zds.table',
								'zd/jquery.zds.form', 'zd/jquery.zds.message' ],
						function($, CALLBACK) {
							 /**
			                 * 产品分类下拉框联动
			                 * */
			                CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
			                    var parentId = index;
			                    loadProductChildId(parentId);
			                };
			                /**
			                 * 产品下拉框数据
			                 * @param cataId
			                 */
			                function loadProductChildId(pId) {
			                    var productChildId = $("#productChildId");
			                    productChildId.ZCombobox({queryParams: {"parentId": pId}});
			                }
							
							
							//操作格式化
							CALLBACK.operate = function(rowData, index) {
								var data = "";
								var recordStatusName = rowData.recordStatusName;
								if (recordStatusName == "未录入") {
									data += '<a href="javaScript:void(0)" onClick="recordCredit"><button class="btn-blue">录入</button></a>&nbsp;&nbsp;';
								}
								if (recordStatusName == "已录入") {
									data += '<a href="javaScript:void(0)" onclick="editCredit"><button class="btn-blue">修改</button></a>&nbsp;&nbsp;'
											+ '<a href="javaScript:void(0)" onclick="viewCredit"><button class="btn-blue">详情</button></a>&nbsp;&nbsp;';
									+'<a href="javaScript:void(0)" onclick="media"><button class="btn-blue">影像资料</button></a>';
								}
								return data;
							}

							//重写案件号
							CALLBACK.formatCaseApplyCode = function(rowData,
									index) {
								console.log(rowData.caseApply);
								return rowData.caseApply.caseApplyCode;
							}
							//重写父产品名称
							CALLBACK.formatProductTypeName = function(rowData,
									index) {
								var productTypeName = "";
								var productTypeId = rowData.caseApply.productTypeId;
								$
										.ajax({
											url : '<z:ukey key="com.zdsoft.finance.product.findProductOrCategoryNameById" context="admin"/>&jsoncallback=?&id='
													+ productTypeId + '&type=2',
											type : 'GET',
											async : false,
											success : function(result) {//返回数据根据结果进行相应的处理  
												console.log(result);
												if (result.resultStatus == 0) {
													productTypeName = result.optional.productOrCategoryName;
												} else {
													console
															.log("findProductOrCategoryNameById faild");
												}
											}
										});
								return productTypeName;
							}
							//重写子产品名称
							CALLBACK.formatProductSubtypeName = function(
									rowData, index) {
								var productSubtypeName = "";
								var productSubtypeId = rowData.caseApply.productSubtypeId;
								$
										.ajax({
											url : '<z:ukey key="com.zdsoft.finance.product.findProductOrCategoryNameById" context="admin"/>&jsoncallback=?&id='
													+ productSubtypeId
													+ '&type=1',
											type : 'GET',
											async : false,
											success : function(result) {//返回数据根据结果进行相应的处理  
												console.log(result);
												if (result.resultStatus == 0) {
													productSubtypeName = result.optional.productOrCategoryName;
												} else {
													console
															.log("findProductOrCategoryNameById faild");
												}
											}
										});
								return productSubtypeName;
							}
							//重写申请金额
							CALLBACK.formatApplyAmount = function(rowData,
									index) {
								return rowData.caseApply.applyAmount;
							}
							//重写贷款期限
							CALLBACK.formatApplyPeriod = function(rowData,
									index) {
								return rowData.caseApply.applyDeadline
										+ rowData.caseApply.applyDeadlineUnit;
							}

							//录入
							CALLBACK.recordCredit = function(index, rowData) {
								var id = rowData.caseApply.id;
								ZDS_MESSAGE_CLIENT
										.openMenuLink(
												'record',
												'征信录入',
												'<z:ukey key="com.zdsoft.finance.casemanage.credit.list" context="admin"/>&jsoncallback=?&caseApplyId='
														+ id);
							}

							$.ZUI.init();
							//查询
							$('#btn-search').click(
									function() {
										var formArray = $(
												"#searchInfoRecordForm")
												.serializeArray();
										$('#tb-case-apply-record').ZTable(
												"reload", formArray);
									});
							//重置
							$("#btn-reset").click(function() {
								//$("#searchInfoRecordForm")[0].reset();
								$("#caseApplyCode").val("");
								$("#borrowName").val("");
							});
						});
	</script>

</body>
</html>