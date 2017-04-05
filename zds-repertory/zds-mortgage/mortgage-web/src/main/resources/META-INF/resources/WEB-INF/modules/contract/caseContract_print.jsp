<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file='../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/common_upload.jsp"%>
<title>合同套打</title>

</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="contract_addoredit_form" class="zui-form" method="post"
				enctype="multipart/form-data">
				<div class="page-title">基本信息</div>

				<div class="p5">
					<table class="table-detail">
						<tbody>
							<tr>
								<td class="td-title pct10">案件号</td>
								<td>${caseContractInfo.CASEAPPLYCODE}</td>
								<td class="td-title pct10">主借人</td>
								<td>${caseContractInfo.CUSTOMERNAME}</td>
								<td class="td-title">机构</td>
								<td>${caseContractInfo.MECHANISMNAME}</td>
							</tr>
							<tr>
							<td class="td-title pct10">合同编号</td>
								<td>${caseContractInfo.CONTRACTNO}</td>
								<td class="td-title">合同开始时间</td>
								<td>${caseContractInfo.CONTRACTSTARTDATE2}</td>
								<td class="td-title">合同结束时间</td>
								<td>${caseContractInfo.CONTRACTENDDATE2}</td>
							</tr>
							<tr>
							    <td class="td-title">合同金额(元)</td>
								<td>${caseContractInfo.CONTRACTAMOUNT}</td>
								<td class="td-title">合同期限</td>
								<td>${caseContractInfo.Date}天</td>
								<td></td>
								<td></td>
							</tr>
                            <tr>	
                                <td class="td-title">抵押物（房产）</td>
                                <td>${caseContractInfo.get("MAILINGADDRESS")}</td>
                                <td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="page-title">合同生成</div>
				<div class="p5">
					<div id="tb-plan" class="table-detail">
						<table>
						<tr>
								<td>合同名称</td>
								<td>合同名称</td>
								<td>合同名称</td>
						</tr>
						<c:forEach items="${caseInfo}" var="c" varStatus="status">
									<c:if test="${status.count%3==1}">
									   <tr>
									</c:if> 
									<td>${c.CONTRACTNAME}
									    <button class="btn-blue" onclick="PrintView('${c.CASECONTRACTID}','${c.ID}','1','${c.ATTACHMENTID}')">预览</button>
								    </td>
									<c:if test="${status.count%3==0}">
									   </tr>
									</c:if> 
						</c:forEach>
						
						
						</table>
					</div>

					<div class="page-title">合同套打</div>
					<div class="p5">
						<div id="tb-plan" class="table-detail">
							<table>
								<tr>
									<td>合同名称</td>
									<td>合同名称</td>
									<td>合同名称</td>
								</tr>
										<c:forEach items="${printTplList}" var="c" varStatus="status">
									<c:if test="${status.count%3==1}">
									   <tr>
									</c:if> 
									<td>${c.TEMPLATENAME}
									    <button class="btn-blue" onclick="PrintView('${c.ID}','${c.BID}','2')">预览</button>
								    </td>
									<c:if test="${status.count%3==0}">
									   </tr>
									</c:if> 
						        </c:forEach>
						
							</table>
						</div>
					</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.form', 'zd/jquery.zds.message',
				'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox',
				'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ], function($,
				CALLBACK, ZUI_MESSAGE_CLIENT) {
			$.ZUI.init();
		});
		function PrintView(caseApplyId,id,type,ATTACHMENTID){
			var msg;
			if(type==1){
				msg="合同生成 :合同ID:"+id+",案件合同ID:"+caseApplyId;	
			
				var urlPrint='<z:ukey key="com.zdsoft.finance.contract.conDocGen" context="admin"/>';
				
				ZDS_MESSAGE_CLIENT.openMenuLink('printDetails', '打印详情', urlPrint
								+ "?openMethod=tabs&ContractTemplateId="+id+"&caseApplyId="+caseApplyId+"&attachmentId="+ATTACHMENTID);	
			}else{
				msg="合同套打 :模板ID:"+id+",案件ID:"+caseApplyId;
				
				var urlPrint='http://'+'<%= request.getLocalAddr()%>'+':'+'<%= request.getServerPort()%>'+'/web/designer4dev/ViewPrint.jsp';
				//console.log(urlPrint);
				ZDS_MESSAGE_CLIENT.openMenuLink(
						'printDetails', '打印详情', urlPrint
								+ "?openMethod=tabs&ContractTemplateId="+id+"&caseApplyId="+caseApplyId);
			}

		}
	</script>
</body>
</html>