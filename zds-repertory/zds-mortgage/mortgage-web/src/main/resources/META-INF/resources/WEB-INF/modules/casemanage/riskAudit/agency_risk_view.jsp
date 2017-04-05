<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page-box">
	<div class="page-title">机构风险信息</div>
	<div class="p10">
		<table class="table-index scroll">
			<thead>
				<tr>
					<th>类型</th>
					<th>在贷总额</th>
					<th>在贷余额</th>
					<th>在贷案件数</th>
					<th>逾期案件数</th>
					<th>逾期案件占比</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orgRisk}" var="orgRisk" varStatus="v">
					<tr>
						<td>${orgRisk.riskType }</td>
						<td>${orgRisk.loanAmount }</td>
						<td>${orgRisk.loanBalance }</td>
						<td>${orgRisk.loansNumber }</td>
						<td>${orgRisk.overdueLoansNumber }</td>
						<td>${orgRisk.overdueLoansProportion }</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="page-box">
	<div class="page-title">规则风险</div>
	<div class="p10">
		<table class="table-index scroll">
			<thead>
				<tr>
					<th >编号</th>
					<th>大类</th>
					<th>提示</th>
					<th>结果</th>
					<th>特批状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${riskList}" var="risk" varStatus="v">
					<tr>
						<td>${risk.rulesNo }</td>
						<td>${risk.rulesTypeName }</td>
						<td>${risk.rulesPrompt }</td>
<%-- 						<td>${risk.rulesResultName }</td> --%>
						<td>提示</td>
						<td>${risk.specialStatusName }</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="page-box">
	<div class="page-title">风险特批</div>
	<div class="p10">
		<table class="table-index scroll">
			<thead>
				<tr>
					<th>序号</th>
					<th>主借人</th>
					<th>申请事项</th>
					<th>风险措施</th>
					<th>申请时间</th>
					<th>特批状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tvoList}" var="things" varStatus="v">
					<tr>
						<td>${v.index+1}</td>
						<td>${mainCustomerName}</td>
						<td>${things.itemTypeName }</td>
						<td>${things.riskItemTypeName }</td>
						<td>${things.applyDate }</td>
						<td>${things.specialApproveStatus }</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="page-box">
	<div class="page-title">费用特批</div>
	<div class="p10">
		<div class="mb5">
	        <span class="mr10">费用类型:<span class="c-orange" name="totalShouldReceiveSpan">${feeType}</span></span>
	        <span class="mr10">|</span>
	        <span class="mr10">应收:<span class="c-orange" name="totalTrueReceiveSpan">${expectedAmount }</span>元</span>
	        <span class="mr10">|</span>
	        <span class="mr10">实收:<span class="c-orange" name="totalShouldPaySpan">${receivedAmount }</span>元</span>
	        <span class="mr10"></span>
	        <a class="fr f14 c-gray icon-open-bot" onclick="showOrHideDetail(this,'#feeSpecialApproval')"></a>
	    </div>
	    <table class="table-index scroll" id="feeSpecialApproval">
			<thead>
				<tr>
					<th>序号</th>
					<th>收费对象</th>
					<th>费用类型</th>
					<th>应收金额(元)</th>
					<th>标准应收金额(元)</th>
					<th>特批状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${feeList}" var="fee" varStatus="v">
					<tr>
						<td>${v.index+1}</td>
						<td>${fee.feePerson }</td>
						<td>${fee.feeItemName }</td>
						<td>${fee.expectedAmount }</td>
						<td>${fee.standardAmount }</td>
						<td>${fee.specialStatusName }</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="page-box">
	<div class="page-title">评分卡风险建议</div>
	<div class="p10">
		<table class="table-index scroll">
			<thead>
				<tr>
					<th>产品类型</th>
					<th>评分卡得分</th>
					<th>风险分段</th>
					<th>建议最高抵押层数</th>
					<th>风控提示</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scoreList}" var="score" varStatus="v">
					<tr>
						<td>${score.productType }</td>
						<td>${score.scoreCard }</td>
						<td>${score.riskSegment }</td>
						<td>${score.adviseHighPlies }</td>
						<td>${score.riskTips }</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
seajs.use(['jquery'], function ($) {

	window.showOrHideDetail=function(that,target){
	    if($(that).hasClass('icon-open-bot')){
	        $(that).removeClass('icon-open-bot').addClass('icon-open-top');
	    }else{
	        $(that).removeClass('icon-open-top').addClass('icon-open-bot');
	    }
	    $(target).toggle();
	}
});
</script>