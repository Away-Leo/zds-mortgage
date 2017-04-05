<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>查看还款计划</title>
</head>
<body>
<div class="save">
    <button id="back" class="btn-blue mr10">返回</button>
</div>

<div class="frm-content frm-bottom">
<div class="page-box">
	<div class="p10">
		<div class="page-box">
			<div class="page-title">基本信息
				<input type="button" class="btn-search-blue" style="float:right" id="searchReceivablePlan" value="导出还款计划" />
				<input type="button" class="btn-search-blue" style="float:right" id="searchReceivablePlan" value="导出本月应还款计划" />
				<input type="button" class="btn-search-blue" style="float:right" id="searchReceivablePlan" value="导出还款情况" />
			</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">案件号</td>
						<td class="pct20">
							<label>${caseApplyVo.caseApplyCode }</label>
						</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">
							<label>${caseApplyVo.customerName }</label>
						</td>
						<td class="td-title pct10">审批金额</td>
						<td class="pct30">
							<!-- 暂时取申请金额 -->
							<label>${caseApplyVo.applyAmount }元</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">期限</td>
						<td>
							<label>${caseApplyVo.applyTerm }/${caseApplyVo.applyTermUnitName }</label>
						</td>
						<td class="td-title pct10">利率</td>  
						<td>
							<label>${caseApplyVo.applyRate }%</label>
						</td>
						<td class="td-title pct10">还款计划利率</td>
						<td>
							<label>${caseApplyVo.receivableInfoVo.loanMonthRate }%</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">放款金额</td>
						<td>
							<label>${caseApplyVo.loanApplyAnount }元</label>
						</td>
						<td class="td-title pct10">已收本金</td>
						<td>
							<label>${caseApplyVo.loanApplyAnount-caseApplyVo.caseApplyBalance }</label>
						</td>
						<td class="td-title pct10">未收本金</td>
						<td>
							<label>${caseApplyVo.caseApplyBalance }元</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">还款方式</td>
						<td>
							<label>${caseApplyVo.receivableInfoVo.repaymentTypeTwoName }</label>
						</td>
						<td class="td-title pct10">已收利息</td>
						<td>
							<label>0</label>
						</td>
						<td class="td-title pct10">已收服务费</td>
						<td>
							<label>0</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">罚息记息方式</td>
						<td>
							<label></label>
						</td>
						<td class="td-title pct10">逾期利率</td>
						<td>
							<label>${caseApplyVo.overdueRate }%</label>
						</td>
						<td class="td-title pct10">罚息总应收</td>
						<td>
							<label>0</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">罚息已收</td>
						<td>
							<label>0</label>
						</td>
						<td class="td-title pct10">罚息待收</td>
						<td>
							<label>0</label>
						</td>
						<td class="td-title pct10">当期罚息总应收</td>
						<td>
							<label>0</label>
						</td>
					</tr>
					
					<tr>
						<td class="td-title pct10">当期罚息已收</td>
						<td>
							<label>0</label>
						</td>
						<td class="td-title pct10">当期罚息代收</td>
						<td>
							<label>0</label>
						</td>
						<td class="td-title pct10"></td>
						<td>
						</td>
					</tr>
			</table>
		</div>
	</div>

	<div class="page-box">
		<div class="page-title">还款计划跟进</div>
		<div class="p5">
				<!-- 还款计划	table -->
	           <table class="table-flow">
	               <tr>
	                   <th colspan="6">还款计划</td>
	                   <th colspan="5">已还与待还</td>
	                   <th colspan="4">罚息</td>
	                   <th colspan="3">违约金</td>
	               </tr>
	               <tr>
	                   <th >期次</td>
	                   <th >应还日期</th>
	                   <th >本金</th>
	                   <th >服务费</th>
	                   <th >利息</th>
	                   <th >剩余本金</th>
	                   
	                   <th >本金</td>
	                   <th >利息</th>
	                   <th >服务费</th>
	                   <th >待还</th>
	                   <th >结清本息日期</th>
	                   
	                   <th >逾期罚息</td>
	                   <th >减免</th>
	                   <th >实收填充</th>
	                   <th >罚息未收</th>
	                   
	                   <th >应收</th>
	                   <th >减免</th>
	                   <th >已收</th>
	               </tr>
	               <c:forEach var="packageVo" items="${packageVoList }">
	               	<tr>
	                	<td>${packageVo.repaymentAmountAllotVo.periods }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.planRepayDate }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.planPrincipalAmount }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.planServiceFee }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.planInterestAmount }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.remainPrincipal }</td>
	                	
	                	<td>${packageVo.repaymentAmountAllotVo.paidTotalPrincipalAmount }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.paidTotalInterestAmount }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.paidTotalServiceFee }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.dplanPrincipalAmount+packageVo.repaymentAmountAllotVo.dplanInterestAmount+packageVo.repaymentAmountAllotVo.dplanServiceFee }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.piSettlementDate }</td>
	                	
	                	<td>${packageVo.repaymentAmountAllotVo.planPenalty }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.reductionPenalty }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.paidTotalPenalty }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.dplanPenalty }</td>
	                	
	                	<td>${packageVo.repaymentAmountAllotVo.planDamages }</td>
	                	<td>${packageVo.repaymentAmountAllotVo.reductionDamages}</td>
	                	<td>${packageVo.repaymentAmountAllotVo.paidTotalDamages}</td>
	                </tr>
		                <c:if test="${not empty packageVo.repaymentAmountAllotVoList}">
		                <tr >
		                   <th rowspan="${fn:length(packageVo.repaymentAmountAllotVoList )+1}">实收明细</td>
		                   <th style="background-color: #EEE8AA;">实收日期</td>
		                   <th style="background-color: #EEE8AA;">本金</th>
		                   <th style="background-color: #EEE8AA;">服务费</th>
		                   <th style="background-color: #EEE8AA;">利息</th>
		                   <th style="background-color: #EEE8AA;">总余额逾期</th>
		                   
		                   <th style="background-color: #EEE8AA;">逾期天数</td>
		                   <th style="background-color: #EEE8AA;">逾期计息</th>
		                   <th style="background-color: #EEE8AA;">罚息填充</th>
		                   <th style="background-color: #EEE8AA;">(本期逾期余额</th>
		                   <th style="background-color: #EEE8AA;">逾期天数</th>
		                   <th style="background-color: #EEE8AA;">逾期计息</th>
		                   <th style="background-color: #EEE8AA;">罚息填充)</th>
		                   
		                   <th style="background-color: #EEE8AA;">实收违约金</th>
		                   <th style="background-color: #EEE8AA;">提交日期</th>
		                   <th style="background-color: #EEE8AA;">机构操作员</td>
		                   <th style="background-color: #EEE8AA;">集团确认</th>
		                   <th style="background-color: #EEE8AA;">确认日期</th>
		               </tr>
		               <c:forEach var="allotVo" items="${packageVo.repaymentAmountAllotVoList }">
		               	<tr style="background-color: #FFEBCD;">
		                	<td >${allotVo.planRepayDate }</td>
		                	<td >${allotVo.planPrincipalAmount }</td>
		                	<td >${allotVo.planServiceFee }</td>
		                	<td>${allotVo.planInterestAmount }</td>
		                	<td>${allotVo.paidRepayDate }</td>
		                	
		                	<td>${allotVo.overdueDays }</td>
		                	<td>${allotVo.planPenalty }</td>
		                	<td>${allotVo.paidTotalPenalty}</td>
		                	<td>${allotVo.dplanPenalty }</td>
		                	<td>${allotVo.overdueDays }</td>
		                	<td>${allotVo.paidDamages }</td>
		                	<td>${allotVo.currentPlanPenalty }</td>
		                	
		                	<td>${allotVo.paidDamages }</td>
		                	<td>${allotVo.paidRepayDate }</td>
		                	<td>${allotVo.paidRepayDate }</td>
		                	<td>${allotVo.paidRepayDate }</td>
		                	<td>${allotVo.paidRepayDate }</td>
		                </tr>
		               </c:forEach>
		               </c:if>
	               </c:forEach>
	           </table>
	       </div>
	  </div>
	</div>
</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery'],function($) {
		//返回页面
		$('#back').click(function(){
         	ZDS_MESSAGE_CLIENT.closeSelf();
    	});
	});
</script>
</body>
</html>