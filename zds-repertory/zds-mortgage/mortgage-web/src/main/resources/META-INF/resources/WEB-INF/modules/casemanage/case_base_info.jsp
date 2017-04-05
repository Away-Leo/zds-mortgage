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
					<th class="td-title pct10">子产品：</th>
					<td class="pct25">${caseApply.productSubtypeName}</td>
				</tr>
				<tr>
					<th class="td-title pct10">业务部门：</th>
					<td class="pct25">${caseApply.caseApplyCode}</td>
					<th class="td-title pct10">拓展人员：</th>
					<td class="pct25">${caseApply.developmentManagerName}</td>
					<th class="td-title pct10">资金计划名称：</th>
					<td class="pct25">${caseApply.fundPlanName}</td>
				</tr>
				<tr>
					<th class="td-title pct10">贷款金额(元)：</th>
					<td class="pct25">${caseApply.applyAmount}</td>
					<th class="td-title pct10">贷款期限：</th>
					<td class="pct25">${caseApply.applyTerm}${caseApply.applyTermUnitName}</td>
					<th class="td-title pct10">贷款利率：</th>
					<td class="pct25">${caseApply.applyRate}${caseApply.applyRateUnitName}</td>
				</tr>
				
				<tr>
					<th class="td-title pct10">放款金额(元)：</th>
					<td class="pct25">0.00</td>
					<th class="td-title pct10">还款方式：</th>
					<td class="pct25">到期还本</td>
					<th class="td-title pct10">逾期利率：</th>
					<td class="pct25">0.00</td>
				</tr>
				
				<tr>
					<th class="td-title pct10">合同开始日期：</th>
					<td class="pct25"></td>
					<th class="td-title pct10">合同结束日期：</th>
					<td class="pct25"></td>
					<th class="td-title pct10"></th>
					<td class="pct25"></td>
				</tr>
				
				<tr>
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