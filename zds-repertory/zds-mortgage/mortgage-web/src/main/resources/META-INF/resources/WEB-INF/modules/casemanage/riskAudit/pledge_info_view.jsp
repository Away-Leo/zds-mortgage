<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>押品信息</title>
</head>
<body>
	<div id="frm-content">
		<form class="zui-form form-search" action="javascript:void(0);"
			zdata-options={"url":""}>

			<div class="page-box"> 
				<div class="page-title">押品信息</div>
				<div class="p10">
					<div id="tb-collateral-base" class="table-index">
						<table>
							<thead>
								<tr>
									<th>抵押物</th>
									<th>地址</th>
									<th>类型</th>
									<th>面积(㎡)</th>
									<th>单价(元/㎡)</th>
									<th>综合评估价/元</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${housePropertyList}" var="item" varStatus="s">
									<tr>
										<td>抵押物${s.index+1 }</td>
										<td>${item.provinceName }${item.cityName }${item.districtName }${item.mailingAddress }</td>
										<td>${item.estatePropertiesName }</td>
										<td>${item.area }</td>
										<td><fmt:formatNumber value="${(!empty item.synthesizePrice && item.synthesizePrice!=0 && !empty item.area && (item.area*1)>0)? item.synthesizePrice/(item.area*1):0  }" pattern="#,##0.00#"/></td>
										<td><fmt:formatNumber value="${item.synthesizePrice }" pattern="#,##0.00#"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- 列表区域 -->
			<div class="page-box">
				<div class="p10">
					<div id="tb-mortgage-situation">
						<table class="table-index">
							<thead>
								<tr>
									<th rowspan="2">抵押物</th>
									<th colspan="2">一抵情况</th>
									<th colspan="2">二抵情况</th>
									<th rowspan="2">现抵押(元)</th>
									<th rowspan="2">楼龄</th>
									<th rowspan="2">居住状态</th>
									<th rowspan="2">风控核定价</th>
									<th rowspan="2">贷款成数(%)</th>
								</tr>
								<tr>
									<th>银行</th>
									<th>余额(元)</th>
									<th>银行</th>
									<th>余额(元)</th>
								</tr>
							
							<tbody>
								<c:forEach items="${casePledgeInfoList }" var="item" varStatus="s">
									<tr>
 										<td>抵押物${s.index+1}</td>
 										<td>${item.firstLoanBank }</td>
										<td><fmt:formatNumber value="${item.firstLoanBalance }" pattern="#,##0.00#"/></td> 
										<td>${item.secondLoanBank }</td> 
										<td><fmt:formatNumber value="${item.secondLoanBalance }" pattern="#,##0.00#"/></td> 
										<td><fmt:formatNumber value="${item.applyAmount }" pattern="#,##0.00#"/></td> 
 										<td>${item.floorAge }</td> 
										<td>${item.livingStateName }</td> 
 										<td><fmt:formatNumber value="${item.controlPrice }" pattern="#,##0.00#"/></td> 
 										<c:if test="${s.index==0 }">
 										<td rowspan="${fn:length(casePledgeInfoList)}">${item.percentage }</td> 
 										</c:if>
									</tr>
								</c:forEach>
							</tbody>
							</thead>
 						</table>
 					</div>
 				</div>
 			</div>
 			<div class="page-box">
				<div class="p10">
						<table class="table-detail">
							<tbody>
							<tr>
									<th class="pct5">抵押物综述</th>
									<td class="pct20" colspan="12">${caseApply.riskInfoVo.pledgeReview}</td>
								</tr>
								<tr>
									<th class="pct5">业务综析</th>
									<td class="pct20" colspan="12">${caseApply.riskInfoVo.businessAnalysis}</td>
								</tr>
								<tr>
									<th class="pct5">特殊情况</th>
									<td class="pct20" colspan="12">${caseApply.riskInfoVo.specialSituation}</td>
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