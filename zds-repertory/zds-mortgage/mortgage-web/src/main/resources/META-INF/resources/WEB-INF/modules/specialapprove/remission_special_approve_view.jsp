<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>费用减免特批申请</title>
</head>
<body>
	<form id="remissionForm">
		<!-- 费用减免特批申请 -->
		<div class="page-box">
			<h1 class="page-title">费用减免特批申请</h1>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">案件号</td>
						<td class="pct20">
							${remissionBasicVo.caseApplyCode }
						</td>
						<td class="td-title pct10">编号</td>
						<td class="pct20">
							${remissionBasicVo.remissionCode }
						</td>
						<td class="td-title pct10">放款金额</td>
						<td class="pct30">
							${remissionBasicVo.loanAmount }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">申请人</td>
						<td class="pct20">
							${remissionBasicVo.applyUserName }
						</td>
						<td class="td-title pct10">申请人部门</td>
						<td class="pct20">
							${remissionBasicVo.applyDeptName }
						</td>
						<td class="td-title pct10">申请时间</td>
						<td class="pct30">
							${remissionBasicVo.applyDate }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">机构</td>
						<td class="pct20">
							${remissionBasicVo.orgName }
						</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">
							${remissionBasicVo.mainBorrowName }
						</td>
						<td class="td-title pct10">子产品</td>
						<td class="pct30">
							${remissionBasicVo.productSubtypeName }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">本金余额</td>
						<td class="pct20">
							${remissionBasicVo.principalBalance }
						</td>
						<td class="td-title pct10">垫资天数</td>
						<td class="pct20">
							${remissionBasicVo.loaningDays }
						</td>
						<td class="td-title pct10">逾期天数</td>
						<td class="pct30">
							${remissionBasicVo.overdueDays }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>罚息挂钩标准</td>
						<td class="pct20">
							<dl class="form-item">
					         	<dd class="detail">
					                <div id="us"> <!-- 校验罚息挂钩标准 -->
					                   	<input class="zui-combobox zui-validatebox" type="hidden" id="penaltyUseStandard"
				                             data-width="190" data-toggle="combobox" data-height="300" value="${remissionBasicVo.penaltyUseStandard }"
				                             data-data="[{'id':'0','text':'当前本金'},{id:'1','text':'剩余本金'}]"
				                             validate-type="Require" data-defaultvalue="0"
				                             data-valuefield="id" data-textfield="text" />
									</div>
					            </dd>
					       	 </dl>
						</td>
						<td class="td-title pct10">放款日期</td>
						<td class="pct20">
							${remissionBasicVo.loanDate }
						</td>
						<td class="td-title pct10">本次还款日期</td>
						<td class="pct30">
							${remissionBasicVo.currentReceiveDate }
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 本次减免费用明细 -->
		<div class="page-box">
			<h1 class="page-title">本次减免费用明细</h1>
			<div class="p5">
				<div id="remissionDetail"></div>
			</div>
		</div>
		
		<!-- 备注 -->
		<div class="page-box">
			<h1 class="page-title">备注</h1>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">申请事由</td>
						<td colspan="5">
	                         <label>
	                       		 <textarea id="remark" class="zui-area row-width zui-validatebox"
	                       		 validate-type="Length[0-512]" placeholder="最多可以输入512个字符" disabled="disabled">${remark }</textarea>
	                         </label>
						</td>
					</tr>		
				</table>
			</div>
		</div>
		
		<!-- 引入附件 -->
		<div id="freeAttachment"></div>
		
		<!-- 新增减免项弹窗 -->
		<div id="remissionAdd" style="display:none">
			 <div id="propertyEditDiv" class="p10">
				 <dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>罚息挂钩标准：</dt>
		         	<dd class="detail">
		                <label>
		                	<input id="penaltyUseStandardText" class="zui-input" disabled="disabled" type="text">
		                </label>
		            </dd>
		       	  	<dt class="title"><b class="c-red mr5">*</b>减免项目：</dt>
		            <dd class="detail">
					 	<label>
							<input class="zui-combobox zui-validatebox" type="hidden" id="remissionItem"
	                             data-width="190" data-toggle="combobox" 
	                             data-data="[{'code':'YWDM00120004','name':'罚息'},{'code':'YWDM00120009','name':'违约金'}]"
	                             data-callback="getItemAmount"
	                             data-height="300" validate-type="Require"
	                             data-valuefield="code" data-textfield="name">
						</label>
				    </dd>
		       	 </dl>
		       	
		       	 <dl class="form-item block">
		       		<dt class="title">应收金额（元）：</dt>
					<dd class="detail">
						<label>
							<input type="text"  class="zui-input zui-validatebox" id="receivedAmount" disabled="disabled" value="0"/>
						</label>
					</dd>
		       		 <dt class="title">实收金额（元）：</dt>
		             <dd class="detail">
		              	<label> 
		              		<input type="text" class="zui-input zui-validatebox" id="expectedAmount" name="expectedAmount" value="0"/>
						</label>
		             </dd>
		       	</dl>
		       	<dl class="form-item">
		       	   	<dt class="title">减免金额（元）：</dt>
	                <dd class="detail">
	                    <input class="zui-input zui-validatebox" validate-type="Require,Digital[7-4]" id="annulAmount" disabled="disabled" name="annulAmount" value="0">
	                </dd>
		        </dl>
		    </div> 
		</div>
	</form>
</body>

<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.table','zd/jquery.zds.dialog','zd/jquery.zds.combobox'],
		   function ($, callback) {
		
		//应收金额
		var receivedAmount = 0;
		//实收表ID
		var repaymentAmountId = "",isAdd = true,missionIndex = "";
				 
		 $('#remissionDetail').ZTable({
	         columns: [[
	             {field: 'beRemissionItemCode', title: '收费项目code', align: 'center',hidden:true, width: '0%' },
	             {field: 'beRemissionItemName', title: '收费项目', align: 'center', width: '22%' },
	             {field: 'receivedAmount', title: '应收金额（元）', align: 'center', width: '22%' },
	             {field: 'expectedAmount', title: '实收金额（元）', align: 'center', width: '22%' },
	             {field: 'annulAmount', title: '减免金额（元）', align: 'center', width: '22%'},
	             {field: 'penaltyUseStandard', title: '罚息挂钩标准', align: 'center',hidden:true, width: '0%'},
	             {field: 'repaymentAmountId', title: '实收ID', align: 'center',hidden:true, width: '0%'}
	         ]],
	         columnsType: [
	             {
	            	 "beRemissionItemName": {
	                     "inputType": "text",
	                     "validateType": "Require"
	                 },
	                 "receiveAmount": {
	                     "inputType": "text"
	                 },
	                 "expectedAmount": {
	                     "inputType": "input"
	                 },
	                 "annulAmount": {
	                     "inputType": "text"
	                 }
	             },
	             {
	                 "inputWidth": 100,
	                 "inputHeight": 24,
	                 "areaWidth": 100,
	                 "areaHeight": 24
	             }
	         ],
	         idField: 'id',//每行数据的，唯一标识符
	         //queryParams: {param: 'xxooxxooxxoo000000000000000000'},//分页业务参数
	         url: '<z:ukey key="com.zdsoft.finance.specialApprove.queryRemissionItemDetail" context="admin"/>&jsoncallback=?&specialApproveManageId=${specialApproveManageId }',
	         singleSelect: false,//表格行是单选还是多选
	         isRowNum: true,//是否显示行号
	         rows: 8,//分页情况下，显示的条数
	         currentPage: 1,//分页情况下的，当前页
	         pagination: false,//表格是否分页
	         tableCls: 'table-index',//table的样式
	         onEdit:false,
	         batchSave: false //默认为true，是否批量保存
	    });
		
		$("#freeAttachment").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+'&productId=${fileStoreVo.productId }' +
		'&linkCode=${fileStoreVo.linkCode }&caseApplyId=${caseApplyId }&businessId=${fileStoreVo.businessId }');
		
		$("#penaltyUseStandard").ZCombobox("disable");
	});
</script>
</html>