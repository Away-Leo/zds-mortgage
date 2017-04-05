<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="page-box">
       <h1 class="page-title">
			案件基本信息
		</h1>
		<div class="p10">
			<table class="table-detail">
				<tr>
					<th class="td-title pct10">案件号：</th>
					<td class="pct25">${caseApply.caseApplyCode}</td>
					<th class="td-title pct10">主借款人：</th>
					<td class="pct25">${caseApply.customerName}</td>
					<th class="td-title pct10">拓展人员：</th>
					<td class="pct25">${caseApply.developmentManagerName}</td>
					
				</tr>
				<tr>
					<th class="td-title pct10">业务部门：</th>
					<td class="pct25">${caseApply.developmentDepartmentName}</td>
					
					<th class="td-title pct10">产品父类名称：</th>
					<td class="pct25">${caseApply.productTypeName}</td>
					<th class="td-title pct10">产品子类名称：</th>
					<td class="pct25">${caseApply.productSubtypeName}</td>
				</tr>
				<tr>
					<th class="td-title pct10">贷款金额(元)：</th>
					<td class="pct25">${caseApply.applyAmount}</td>
					<th class="td-title pct10">贷款期限：</th>
					<td class="pct25">${caseApply.applyTerm}/${caseApply.applyTermUnitName}</td>
					<th class="td-title pct10">贷款利率(%)：</th>
					<td class="pct25">${caseApply.applyRate}/${caseApply.applyRateUnitName}</td>
				</tr>
				
				<tr>
					<th class="td-title pct10">放款金额(元)：</th>
					<td class="pct25">${caseApply.loanApplyAnount}</td>
					<th class="td-title pct10">还款方式：</th>
					<td class="pct25">${caseApply.repayMethodName}</td>
					<th class="td-title pct10">逾期利率(%)：</th>
					<td class="pct25">${caseApply.overdueRate}/${caseApply.overdueRateUnitName}</td>
				</tr>
				
				<tr  id="tr_contract" style="display: none;">
					<th class="td-title pct10">合同开始日期：</th>
					<td class="pct25">${caseApply.contractStartDate}</td>
					<th class="td-title pct10">合同结束日期：</th>
					<td class="pct25">${caseApply.contractEndDate}</td>
					<th class="td-title pct10"></th>
					<td class="pct25"></td>
				</tr>
				
				<tr id="tr_amount" style="display: none;">
					<th class="td-title pct10">已收本金(元)：</th>
					<td class="pct25">0.00</td>
					<th class="td-title pct10">已收利息(元)：</th>
					<td class="pct25">0.00</td>
					<th class="td-title pct10">已收罚息(元)：</th>
					<td class="pct25">0.00</td>
				</tr>
				
			</table>
		</div>
    </div>