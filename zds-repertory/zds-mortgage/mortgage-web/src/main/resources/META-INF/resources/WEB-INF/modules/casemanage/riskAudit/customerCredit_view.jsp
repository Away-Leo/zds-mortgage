<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>客户信息</title>
</head>
<body>
	<div id="frm-content">
		<form class="zui-form form-search" action="javascript:void(0);"
			zdata-options={"url":""}>

			<div class="page-box">
				<h1 class="page-title">客户信息</h1>
				<div class="p10">
					<table class="table-detail">
						<tbody>
							<tr>
								<th class="pct10">主借人</th>
								<td>${caseApply.customerName}</td>
								<th class="pct10">性别</th>
								<td>${beforePersonalCustomerVo.genderName}</td>
								<th class="pct10">年龄</th>
								<td>${beforePersonalCustomerVo.age}</td>
								
							</tr>
							<tr>
								<th class="pct10">婚姻状况</th>
								<td>${beforePersonalCustomerVo.maritalStatusName}</td>
								<th class="pct10">信用评分</th>
								<td>${score.scoreCard }</td>
							</tr>
							<tr>
									<th class="pct10">经营信息/工作信息</th>
									<td class="pct20" colspan="5">${caseApply.riskInfoVo.workSituation}</td>
								</tr>
						</tbody>
					</table>
				</div>
				<div id="initCustomerCreditViews">
					 
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
			$("#initCustomerCreditViews").load('<z:ukey key="com.zdsoft.finance.creditManage.initCustomerCreditViews" context="admin"/>&caseApplyId=${projectId}&caseApplyStage=YWDM009206&customerIds=${customerIds}&mainCustomerId=${mainCustomerId}');
// 			
			$.ZUI.init();
			//$.ZUI.initGrid("#tb-mortgage-situation");
			//$.ZUI.initGrid("#tb-solvency");
		});
	</script>
</body>
</html>