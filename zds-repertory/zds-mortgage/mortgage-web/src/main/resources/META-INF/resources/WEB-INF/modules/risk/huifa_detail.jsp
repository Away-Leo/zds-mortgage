<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>汇法网详情</title>
</head>
<body>
	<div class="frm-content">
		<div class="page-box">
			<div class="p10">
				<div class="page-box">
					<h1 class="page-title">执行公开信息</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">被执行人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">执行法院</th>
										<th data-options="field:field4">执行案件号</th>
										<th data-options="field:field5">执行内容</th>
										<th data-options="field:field7" formatter="formatDate">立案时间</th>
										<th data-options="field:field8">执行状态</th>
										<th data-options="field:field9">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${publishList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">失信老赖名单</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">被执行人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">执行法院</th>
										<th data-options="field:field4">执行案件号</th>
										<th data-options="field:field5">执行内容</th>
										<th data-options="field:field6">日期类别</th>
										<th data-options="field:field7" formatter="formatDate">具体日期</th>
										<th data-options="field:field8">执行状态</th>
										<th data-options="field:field9">异议备注</th>
									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${dishonestList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">限制高消费名单</h1>
					<div class="p10">
					
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">被执行人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">执行法院</th>
										<th data-options="field:field4">执行案件号</th>
										<th data-options="field:field5">执行内容</th>
										<th data-options="field:field6">日期类别</th>
										<th data-options="field:field7" formatter="formatDate">具体时间</th>
										<th data-options="field:field8">执行状态</th>
										<th data-options="field:field9">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${restrictedHighConsumeList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">限制出入境名单</h1>
					<div class="p10">
						
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">被限制姓名</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">执行法院</th>
										<th data-options="field:field4">执行案件号</th>
										<th data-options="field:field5">执行内容</th>
										<th data-options="field:field6">日期类别</th>
										<th data-options="field:field7" formatter="formatDate">具体时间</th>
										<th data-options="field:field8">执行状态</th>
										<th data-options="field:field9">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${restrictedEntryAndExitList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">民商事裁判文书</h1>
					<div class="p10">
						
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">当事人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">审理机关</th>
										<th data-options="field:field4">案号</th>
										<th data-options="field:field5">涉案事由</th>
										<th data-options="field:field6">涉案金额</th>
										<th data-options="field:field7">诉讼地位</th>
										<th data-options="field:field8" formatter="formatDate">结案时间</th>
										<th data-options="field:field9">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${civilJudgmentList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgAmount }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">民商事审批流程</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">当事人姓名或名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">审理机关</th>
										<th data-options="field:field4">案号</th>
										<th data-options="field:field5">涉案事由</th>
										<th data-options="field:field6">诉讼地位</th>
										<th data-options="field:field7">日期类别</th>
										<th data-options="field:field8" formatter="formatDate">具体日期</th>
										<th data-options="field:field9">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${civilApprovalProcessList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>

				<div class="page-box">
					<h1 class="page-title">罪犯及嫌疑人名单</h1>
					<div class="p10">
						
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">当事人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">侦查/批捕/审批机关</th>
										<th data-options="field:field4">案号</th>
										<th data-options="field:field5">违法事由</th>
										<th data-options="field:field6">处理结果</th>
										<th data-options="field:field7" formatter="formatDate">处理时间</th>
										<th data-options="field:field8">异议备注</th>
									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${criminalList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgResult }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">行政违法记录</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">当事人姓名或名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">执法/复议/审判机关/信息来源机构</th>
										<th data-options="field:field4">案号</th>
										<th data-options="field:field5">违法事由</th>
										<th data-options="field:field6">行政执法结果</th>
										<th data-options="field:field7">法院审理结果</th>
										<th data-options="field:field8">日期类别</th>
										<th data-options="field:field9" formatter="formatDate">具体日期</th>
										<th data-options="field:field10">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${illegalityList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgResult }</td>
											<td>${basicVo.msgStatus }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">欠税名单</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">纳税人名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">主管税务机关/信息来来源机构</th>
										<th data-options="field:field4">所欠税种</th>
										<th data-options="field:field5">欠税属性</th>
										<th data-options="field:field6">欠税余额</th>
										<th data-options="field:field7" formatter="formatDate">欠税发生时间</th>
										<th data-options="field:field8">异议备注</th>

									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${oweTaxesList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgAmount }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">纳税非正常户</h1>
					<div class="p10">
								<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">纳税人名称</th>
										<th data-options="field:field2">纳税人识别号</th>
										<th data-options="field:field3">主管税务机关/信息来源机构</th>
										<th data-options="field:field4" formatter="formatDate">认定日期</th>
										<th data-options="field:field5">异议备注</th>
									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${abnormalTaxList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgCourt }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
				<div class="page-box">
					<h1 class="page-title">欠款欠费名单</h1>
					<div class="p10">
							<table class="scroll table-index">
								<thead class="datagrid-header">
									<tr>
										<th data-options="field:field1">欠款人姓名/名称</th>
										<th data-options="field:field2">证件号码</th>
										<th data-options="field:field3">拖欠金额</th>
										<th data-options="field:field4">拖欠币种</th>
										<th data-options="field:field5">拖欠事由</th>
										<th data-options="field:field6">合同/借据编号</th>
										<th data-options="field:field7" formatter="formatDate">拖欠起始日期</th>
										<th data-options="field:field8">异议备注</th>
									</tr>
								</thead>
								<tbody class="datagrid-body">
									<c:forEach items="${arrearageList}" var="basicVo">
										<tr>
											<td>${basicVo.msgName }</td>
											<td>${basicVo.msgIdNum }</td>
											<td>${basicVo.msgAmount }</td>
											<td>${basicVo.msgType }</td>
											<td>${basicVo.msgContent }</td>
											<td>${basicVo.msgNumber }</td>
											<td>${basicVo.msgTime }</td>
											<td>${basicVo.msgRemark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>