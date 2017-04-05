<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>专户贷方资金（非本金）跟踪</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="tempUuid" name="tempUuid" value="${tempUuid }"/>
			<input type="hidden" id="id" name="id" value="${loanCapitalVo.id }"/>
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustId}"/>
			
			<div class="page-box">
		        <div class="page-title">基本信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">贷方类型</td>
		                    <td class="pct20">${loanCapitalVo.lenderTypeName }</td>
		                    <td class="td-title pct10">贷方名称</td>
		                    <td class="pct20">${loanCapitalVo.lenderName }</td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct20"></td>
		                </tr>
		            </table>
		            <div class="m10">
			            <table class="table-detail">
							<c:if test="${fn:length(feeItemVos)>0}">
								<tr><td>序号</td><td>费用项目</td><td>金额</td></tr>
							</c:if>
							<c:forEach var="feeItemVo" items="${feeItemVos }" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${feeItemVo.feeItemNm }</td>
									<td><input type="hidden" id="feeItemCd" name="feeItemCd" value="${feeItemVo.feeItemCd }"/>
										<input type="hidden" id="feeItemNm" name="feeItemNm" value="${feeItemVo.feeItemNm }"/>
										${feeItemVo.feeAmount }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">总计</td>
		                    <td class="pct20">${loanCapitalVo.totalAmount }</td>
		                    <td class="td-title pct10">实际发生日期</td>
		                    <td class="pct20">${loanCapitalVo.happenDate }</td>
		                    <td class="td-title pct10">资金状态</td>
		                    <td class="pct20">${loanCapitalVo.capitalStateName}</td>
		                </tr>
		                <tr>
		                    <td class="td-title">备注</td>
		                    <td colspan="5">${loanCapitalVo.remark }</td>
		                </tr>
		            </table>
		        </div>
		        <div class="page-box">
				<div class="page-title">操作日志</div>
				<div class="p10">
					<div id="log_datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.capital.getCreditLogs' context='admin'/>&jsoncallback=?&businessId|E|S=${loanCapitalVo.id }","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
						<table>
							<thead>
			        			<tr>
			            			<th data-options="field:operationTypeName">操作类型</th>
			            			<th data-options="field:operationContent">操作内容</th>
			            			<th data-options="field:operationEmpName">处理人</th>
			            			<!-- <th data-options="field:remark">备注</th> -->
			            			<th data-options="field:operationDateName">操作时间</th>
						        </tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		    </div>
		</form>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			// 初始化
			$.ZUI.init();
		});
	</script>
</body>
</html>