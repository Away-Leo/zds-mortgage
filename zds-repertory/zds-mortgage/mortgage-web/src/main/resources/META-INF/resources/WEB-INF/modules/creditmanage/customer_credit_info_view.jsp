<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="crditDivDetail" class="frm-content">
	<!-- 张三征信信息概览 -->
	<c:if test="${empty mianCreditInfo and empty otherCreditInfos }">
	<div class="page-box">
		<div class="page-title">征信信息</div>
		<div class="p5" style="text-align: center;">
			暂无相关征信信息！
		</div>
	</div>
	</c:if>
	<c:if test="${not empty mianCreditInfo }">
	<div class="page-box">
		<div class="page-title">征信信息</div>
		<div class="p5">
			<table class="table-index">
				<tr>
					<th colspan="6"><span style="margin-left: 100px;">${mianCreditInfo.customerName }征信信息概览</span><a id="${mianCreditInfo.id }" href="javaScript:void(0)" class="btn-blue viewReport" style="float: right;"  >查看征信报告</a></th>
				</tr>
				<tr>
					<th colspan="6">贷款</th>
				</tr>
				<tr>
					<th>贷款总额</th>
					<th>贷款总笔数</th>
					<th>已结清贷款笔数</th>
					<th>已结清贷款总额</th>
					<th>未结清笔数</th>
					<th>未结清贷款总额</th>
				</tr>
				<tr>
					<td>${mianCreditInfo.loanTotalAmount }</td>
					<td>${mianCreditInfo.loanTotalNum }</td>
					<td>${mianCreditInfo.endLoanTotalNum }</td>
					<td>${mianCreditInfo.endLoanTotalAmount }</td>
					<td>${mianCreditInfo.loaningTotalNum }</td>
					<td>${mianCreditInfo.loaningTotalAmount }</td>
				</tr>
				<tr>
					<th colspan="6">信用卡</th>
				</tr>
				<tr>
					<th>信用卡发卡总额</th>
					<th>发放张数</th>
					<th>已使用额度</th>
					<th>信用卡使用率</th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>${mianCreditInfo.creditApplyTotalAmount }</td>
					<td>${mianCreditInfo.creditApplyNum }</td>
					<td>${mianCreditInfo.creditUsedAmount }</td>
					<td>${mianCreditInfo.creditUsedRate }</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th colspan="6">逾期征信信息概览（最近12个月）</th>
				</tr>
				<tr>
					<th colspan="6">贷款逾期</th>
				</tr>
				<tr>
					<th>逾期总笔数</th>
					<th>未逾期笔数</th>
					<th>超标笔数</th>
					<th>单笔最高逾期期数</th>
					<th>单笔最高逾期金额</th>
					<th></th>
				</tr>
				<tr>
					<td>${mianCreditInfo.loanOverdueNum }</td>
					<td>${mianCreditInfo.loanNotOverNum }</td>
					<td>${mianCreditInfo.loanOverMarkNum }</td>
					<td>${mianCreditInfo.loanMaxOverduePeriods }</td>
					<td>${mianCreditInfo.loanMaxOverAmount }</td>
					<td></td>
				</tr>
				<tr>
					<th colspan="6">贷记卡逾期</th>
				</tr>
				<tr>
					<th>逾期总笔数</th>
					<th>未逾期笔数</th>
					<th>超标笔数</th>
					<th>单笔最高逾期期数</th>
					<th>单笔最高逾期金额</th>
					<th></th>
				</tr>
				<tr>
					<td>${mianCreditInfo.cardOverdueNum }</td>
					<td>${mianCreditInfo.cardNotOverNum }</td>
					<td>${mianCreditInfo.cardOverMarkNum }</td>
					<td>${mianCreditInfo.cardMaxOverPeriods }</td>
					<td>${mianCreditInfo.cardMaxOverAmount }</td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
	</c:if>
	<!-- 其他人征信概要 -->
	<c:if test="${not empty otherCreditInfos }">
	<div class="page-box">
		<div class="page-title">其他人征信概要</div>
		<c:forEach items="${otherCreditInfos }" var="creditInfo">
		<div class="p5">
			<table class="table-index">
				<tr>
					<th colspan="6"><span style="margin-left: 100px;">${creditInfo.customerName }征信信息概览</span><a id="${creditInfo.id }" href="javaScript:void(0)" class="btn-blue viewReport" style="float: right;">查看征信报告</a></th>
				</tr>
				<tr>
					<th colspan="6">贷款</th>
				</tr>
				<tr>
					<th>贷款总额</th>
					<th>贷款总笔数</th>
					<th>已结清贷款笔数</th>
					<th>已结清贷款总额</th>
					<th>未结清笔数</th>
					<th>未结清贷款总额</th>
				</tr>
				<tr>
					<td>${creditInfo.loanTotalAmount }</td>
					<td>${creditInfo.loanTotalNum }</td>
					<td>${creditInfo.endLoanTotalNum }</td>
					<td>${creditInfo.endLoanTotalAmount }</td>
					<td>${creditInfo.loaningTotalNum }</td>
					<td>${creditInfo.loaningTotalAmount }</td>
				</tr>
				<tr>
					<th colspan="6">信用卡</th>
				</tr>
				<tr>
					<th>信用卡发卡总额</th>
					<th>发放张数</th>
					<th>已使用额度</th>
					<th>信用卡使用率</th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>${creditInfo.creditApplyTotalAmount }</td>
					<td>${creditInfo.creditApplyNum }</td>
					<td>${creditInfo.creditUsedAmount }</td>
					<td>${creditInfo.creditUsedRate }</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th colspan="6">逾期征信信息概览（最近12个月）</th>
				</tr>
				<tr>
					<th colspan="6">贷款逾期</th>
				</tr>
				<tr>
					<th>逾期总笔数</th>
					<th>未逾期笔数</th>
					<th>超标笔数</th>
					<th>单笔最高逾期期数</th>
					<th>单笔最高逾期金额</th>
					<th></th>
				</tr>
				<tr>
					<td>${creditInfo.loanOverdueNum }</td>
					<td>${creditInfo.loanNotOverNum }</td>
					<td>${creditInfo.loanOverMarkNum }</td>
					<td>${creditInfo.loanMaxOverduePeriods }</td>
					<td>${creditInfo.loanMaxOverAmount }</td>
					<td></td>
				</tr>
				<tr>
					<th colspan="6">贷记卡逾期</th>
				</tr>
				<tr>
					<th>逾期总笔数</th>
					<th>未逾期笔数</th>
					<th>超标笔数</th>
					<th>单笔最高逾期期数</th>
					<th>单笔最高逾期金额</th>
					<th></th>
				</tr>
				<tr>
					<td>${creditInfo.cardOverdueNum }</td>
					<td>${creditInfo.cardNotOverNum }</td>
					<td>${creditInfo.cardOverMarkNum }</td>
					<td>${creditInfo.cardMaxOverPeriods }</td>
					<td>${creditInfo.cardMaxOverAmount }</td>
					<td></td>
				</tr>
			</table>
		</div>
		</c:forEach>
	</div>
	</c:if>
</div>
<script>
seajs.use(['jquery'],
	function ($) {
	// 详情报告查看路径
	var viewReprotUrikey = '<z:ukey key="com.zdsoft.finance.creditManage.initCustomerCreditDetailReportView" context="admin"/>';
	// 查看调查报告按钮事件
	$(".viewReport").each(function(index, ele){
		$(ele).click(function(){
			var url = viewReprotUrikey + "&statisticsId=" + $(ele).attr("id");
			ZDS_MESSAGE_CLIENT.openMenuLink('customer_credit_report_view', '查看征信报告', url);
		});
	});
	$.ZUI.initDiv("#crditDivDetail");
});
</script>
