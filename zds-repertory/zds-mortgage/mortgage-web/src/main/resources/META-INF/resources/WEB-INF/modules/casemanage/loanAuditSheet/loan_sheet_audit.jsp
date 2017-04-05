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
<title>贷审审核单</title>
</head>
<body>
	<div id="frm-content">
		<form class="zui-form form-search" action="javascript:void(0);"
			zdata-options={"url":""}>

			<div class="page-box">
				<h1 class="page-title">基本信息</h1>
				<div class="p10">
					<table class="table-index scroll">
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
								<th class="pct10">综合利率</th>
								<td>${caseApply.applyRate }${caseApply.applyRateUnitName }</td>
									<th class="pct10">贷款月供</th>
									<td><fmt:formatNumber value="${principalAndInterestAmount}" pattern="#,##0.00#"/>元</td>
								<th class="pct10"></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 列表区域 -->
			<div class="page-box">
				<div class="page-title">主借人、共借人、担保人基本情况</div>
				<div class="p10">
					<div id="tb-customer" class="table-index">
						<table>
							<thead>
								<tr>
									<th>参与类型</th>
									<th>姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>婚况</th>
									<th>户籍地址</th>
									<th>与主借人关系</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${beforeCustomerList}" var="d" varStatus="v">
									<tr>
										<td>${d.joinTypeName}</td>
										<td>${d.customerName}</td>
										<td>${d.genderName}</td>
										<td>${d.age}</td>
										<td>${d.maritalStatusName}</td>
										<td>${d.permanentAddress}</td>
										<td>${d.relationshipName}</td>
									<tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="page-box">
				<div class="page-title">偿债能力</div>
				<div class="p10">
					<div id="tb-solvency">
						<table class="table-index">
							<thead>
								<tr>
									<th width="20%">类型</th>
									<th>详情</th>
								</tr>
								<tr>
									<td>经营情况/工作情况</td>
									<td>${caseApply.riskInfoVo.workSituation}</td>
								</tr>
								<tr>
									<td>资产情况</td>
									<td>${assetSituation}</td>
								</tr>
								<tr>
									<td>征信情况</td>
									<td>${stringCredit}</td>
								</tr>
								<tr>
									<td>其他</td>
									<td>${caseApply.riskInfoVo.remark}</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

			<!-- 列表区域 -->
			<div class="page-box">
				<div class="page-title">抵押物情况</div>
				<div class="p10">
					<div id="tb-collateral-base" class="table-index">
						<table>
							<thead>
								<tr>
									<th>抵押物</th>
									<th>地址</th>
									<th>类型</th>
									<th>面积(㎡)</th>
									<th>单价(元/㎡）</th>
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
										<td><fmt:formatNumber value="${(!empty item.synthesizePrice && item.synthesizePrice!=0 && !empty item.area && (item.area*1)>0)? item.synthesizePrice/(item.area*1):0 }" pattern="#,##0.00#"/></td>
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
									<th rowspan="2">现抵押</th>
									<th rowspan="2">楼龄</th>
									<th rowspan="2">居住状态</th>
									<th rowspan="2">风控核定价(元)</th>
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

		</form>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog',
				'zd/jquery.zds.button', 'zd/jquery.zds.message',
				'zd/jquery.zds.table', 'zd/jquery.zds.form',
				'zd/jquery.zds.message' ], function($, CALLBACK) {
			window.showOrHideDetail=function(that,target){
			    if($(that).hasClass('icon-open-bot')){
			        $(that).removeClass('icon-open-bot').addClass('icon-open-top');
			    }else{
			        $(that).removeClass('icon-open-top').addClass('icon-open-bot');
			    }
			    $(target).toggle();
			}
			/**
			 * 保存方法
			 * @param datas 传入的参数
			 */
			ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
				ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS);
			};
			ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
			
			
			$.ZUI.init();
			//$.ZUI.initGrid("#tb-mortgage-situation");
			//$.ZUI.initGrid("#tb-solvency");
		});
	</script>
</body>
</html>