<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="frm-content frm-bottom" id="opinionDiv">
	<form id="receivablePlanForm" class="zui-form" action="javascript:void(0);">
		<div class="page-box">
			<div class="page-title">基本信息</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">案件号</td>
						<td class="pct20">
							${caseApply.caseApplyCode }
						</td>
						<td class="td-title pct10">主借人</td>
						<td class="pct20">
							${caseApply.customerName }
						</td>
						<td class="td-title pct10">贷款金额(元)</td>
						<td class="pct30">
							<fmt:formatNumber value="${caseApply.applyAmount }" pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">贷款期限</td>
						<td class="pct20">
							${caseApply.applyTerm }${caseApply.applyTermUnitName }
						</td>
						<td class="td-title pct10">贷款利率</td>
						<td class="pct20">
							${receivableInfo.loanMonthRate }${receivableInfo.loanMonthRateUnitName }
						</td>
						<td class="td-title pct10">还款方式</td>
						<td class="pct20">
							${receivableInfo.repaymentTypeName }
							${receivableInfo.repaymentTypeTwoName }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">利率性质</td>
						<td class="pct20">
							${receivableInfo.rateNatureName }
							${receivableInfo.rateNatureTwoName }
						</td>
						<td class="td-title pct10">逾期利率</td>
						<td class="pct20">
							${receivableInfo.overdueDailyRate }${receivableInfo.overdueDailyRateUnitName }
						</td>
						<td class="td-title pct10">预计放款日期</td>
						<td class="pct30">
							<span class="dateFmt">${receivableInfo.expectLoanDate }</span>
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">还款日</td>
						<td class="pct20">
							<c:choose>
								<c:when test="${receivableInfo.repaymentDate == null }">
									
								</c:when>
								<c:otherwise>
									${receivableInfo.repaymentDate}日
								</c:otherwise>
							</c:choose>
						</td>
						<td class="td-title pct10">综合利率</td>
						<td class="pct20">
							${receivableInfo.syntheticalRate }${receivableInfo.syntheticalRateUnitName }
						</td>
						<td class="td-title pct10">动态转换利率</td>
						<td class="pct30">
							${receivableInfo.internalRateReturn }${receivableInfo.internalRateReturnUnitName }
						</td>
					</tr>
					<tr>
						<td class="td-title pct10">预计开始日期</td>
						<td class="pct20">
							<span class="dateFmt">${receivableInfo.expectStartDate }</span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<form id="bankAccountForm">
		<div class="page-box">
			<div class="page-title">收款账户</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">开户行</td>
						<td class="pct20">
							${loanAccountVo.bankAccount }
						</td>
						<td class="td-title pct10">银行代码</td>
						<td class="pct20">
							${loanAccountVo.bankCode }
						</td>
						<td class="td-title pct10">账户名</td>
						<td class="pct30">
							${loanAccountVo.cardholderName }
						</td>
					</tr>
					<tr>
						<td class="td-title">账号</td>
						<td>
							${loanAccountVo.bankNo }
						</td>
					</tr>
				</table>

			</div>
		</div>
		
		<div class="page-box">
			<div class="page-title">还款（代扣）账户</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">开户行</td>
						<td class="pct20">
							${receivableAccountVo.bankAccount }
						</td>
						<td class="td-title pct10">银行代码</td>
						<td class="pct20">
							${receivableAccountVo.bankCode }
						</td>
						<td class="td-title pct10">账户名</td>
						<td class="pct30">
							${receivableAccountVo.cardholderName }
						</td>
					</tr>
					<tr>
						<td class="td-title">账号</td>
						<td>
							${receivableAccountVo.bankNo }
						</td>
					</tr>
				</table>

			</div>
		</div>
		<div class="page-box">
			<div class="page-title">备注说明</div>
			<div class="p5">
				<table class="table-detail">
					<tr>
						<td class="td-title pct10">备注说明</td>
						<td colspan="5"><label>${receivableInfo.remark}
						</label></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="page-box">
			<div id="receivableView"></div>
		</div>
	</form>

</div>
<script>
	seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form' ], function($,CALLBACK,ZTools) {
		$.ZUI.initForms('#opinionDiv');
		// 日期格式化
		$(".dateFmt").each(function(index,ele){
			$(ele).text(ZTools.strToDate($(ele).text()));
		});
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGenerateView" context="admin"/>&jsoncallback=?&caseApplyId=${caseApply.id }';
		$('#receivableView').load(url);
	});
</script>