<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>案件基本信息</title>
</head>
<body>
	<div id="frm-content">
		<form class="zui-form form-search" action="javascript:void(0);"
			zdata-options={"url":""}>

			<div class="page-box">
				<h1 class="page-title">案件基本信息</h1>
				<div class="p10">
					<table class="table-detail">
						<tbody>
							<tr>
								<th class="pct10">案件号</th>
								<td>${caseApply.caseApplyCode }</td>
								<th class="pct10">机构</th>
								<td>${caseApply.mechanismName }</td>
								<th class="pct10">主借人</th>
								<td>${caseApply.customerName}</td>
							</tr>
							<tr>
								<th class="pct10">贷款金额</th>
								<td><fmt:formatNumber value="${caseApply.applyAmount }" pattern="#,##0.00#"/>元</td>
								<th class="pct10">贷款期限</th>
								<td>${caseApply.applyTerm }&nbsp;${caseApply.applyTermUnitName }</td>
								<th class="pct10">子产品</th>
								<td>${caseApply.productSubtypeName }</td>
							</tr>
							<tr>
								<th class="pct10">贷款利率</th>
								<td>${caseApply.applyRate }${caseApply.applyRateUnitName }</td>
								<th class="pct10">还款方式</th>
								<td>${caseApply.repayMethodName }</td>
								<th class="pct10">贷款月供</th>
								<td><fmt:formatNumber value="${principalAndInterestAmount}" pattern="#,##0.00#"/>元</td>
							<tr>
								<th class="pct10">资金来源</th>
								<td>${caseApply.capitalSourceName }</td>
								<th class="pct10">贷款用途</th>
								<td>${caseApply.capitalUseForName}</td>
								<th class="pct10">评估价抵押成数</th>
								<td>${caseApply.assessedPriceMortgage }<c:if test="${not empty caseApply.assessedPriceMortgage }">%</c:if></td>
							</tr>
							<tr>
								<th class="pct10">贷款成数</th>
								<td>${caseApply.loanNumber }
								<c:if test="${not empty caseApply.loanNumber }">%</c:if>
								</td>
								<th class="pct10"></th>
								<td></td>
								<th class="pct10"></th>
								<td></td>
							</tr>
							<tr>
									<th class="pct10">抵押物综述</th>
									<td class="pct20" colspan="5">${caseApply.riskInfoVo.pledgeReview}</td>
								</tr>
								<tr>
									<th class="pct10">业务综析</th>
									<td class="pct20" colspan="5">${caseApply.riskInfoVo.businessAnalysis}</td>
								</tr>
								<tr>
									<th class="pct10">特殊情况</th>
									<td class="pct20" colspan="5">${caseApply.riskInfoVo.specialSituation}</td>
								</tr>
								<tr>
									<th class="pct10">其他</th>
									<td class="pct20" colspan="5">${caseApply.riskInfoVo.remark}</td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog',
				'zd/jquery.zds.button', 'zd/jquery.zds.message',
				'zd/jquery.zds.table', 'zd/jquery.zds.form',
				'zd/jquery.zds.message' ], function($, CALLBACK) {
			//格式化时间
			/* CALLBACK.formatterDate = function(row, value) {
				return window.formatDate(row, value);
			}; */

			$.ZUI.init();
			//$.ZUI.initGrid("#tb-mortgage-situation");
			//$.ZUI.initGrid("#tb-solvency");
		});
	</script>
</body>
</html>