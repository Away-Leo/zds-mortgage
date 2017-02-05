<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>额度申请</title>
</head>
<body>
	<div>
		<div class="page-box" id = "caseBaseInfo_formDiv">
		<div class="page-title" style="background-color: orange;">资金来源(出资人信息)</div>
			<div class="p10">
			<input type="hidden" id="id" name="id" value="${caseApplyVo.id }"> 
		    <form id="caseBaseInfo_form" class="zui-form form-search" action="javascript:void(0);">
			<div class="p5">
				<table class="table-detail">
	                <tbody>
		                <tr>
		                    <td class="td-title pct10">案件号</td>
		                    <td class="pct20">${caseApplyVo.caseApplyCode }</td>
		                    <td class="td-title pct10">主借人</td>
		                    <td class="pct20">${caseApplyVo.customerName }</td>
		                    <td class="td-title pct10">子产品</td>
		                    <td class="pct30">${caseApplyVo.productSubtypeName }</td>
		                </tr>
		                <tr>
		                    <td class="td-title pct10" >抵押类型</td>
		                    <td class="pct20">${caseApplyVo.pledgeType }</td>
		                    <td class="td-title pct10">资金来源</td>
		                    <td class="pct20">${caseApplyVo.capitalSource }</td>
		                    <td class="td-title pct10">资金计划名称</td>
		                    <td class="pct30">${fundPlanInfoVo.fundPlanName }<input class="zui-input" value="${fundPlanInfoVo.fundPlanName }"
								id="fundPlanName" name="fundPlanName"  type="hidden"></td>
		                </tr>
		                <tr>
		                    <td class="td-title pct10">额度状态</td>
		                    <td class="pct20">${caseApplyVo.actualLimitStatus }</td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct20"></td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct30"></td>
		                </tr>
	           		</tbody>
	            </table>
			</div>
			</form>
			
			<form id="caseLimitApply_form" class="zui-form form-search" action="javascript:void(0);">
			<input type="hidden" id="isPrepareAccount" name="isPrepareAccount" value="${fundPlanInfoVo.isPrepareAccount }"> 
			<input type="hidden" id="loanOutAccountAmount" name="loanOutAccountAmount" value="${fundPlanInfoVo.loanOutAccountAmount }">  
			<input type="hidden" id="currentApplierId" name="currentApplierId" value="${caseApplyVo.currentApplierId }"> 
			<div class="p5">
				<h1 class="page-title">货款发放账户信息</h1>
				<table class="table-detail">
	                <tbody>
		                <tr>
		                    <td class="td-title pct10">机构名称</td>
		                    <td class="pct20">${fundPlanInfoVo.loanOutAccountBranchName }<input class="zui-input" value="${fundPlanInfoVo.loanOutAccountBranchName }"
								id="loanOutAccountBranchName" name="loanOutAccountBranchName"  type="hidden"></td>
		                    <td class="td-title pct10">账户名</td>
		                    <td class="pct20">${fundPlanInfoVo.loanOutAccountName }<input class="zui-input" value="${fundPlanInfoVo.loanOutAccountName }"
								id="loanOutAccountName" name="loanOutAccountName" type="hidden"></td>
		                    <td class="td-title pct10">账号</td>
		                    <td class="pct30">${fundPlanInfoVo.loanOutAccount }<input class="zui-input" value="${fundPlanInfoVo.loanOutAccount }"
								id="loanOutAccount" name="loanOutAccount" type="hidden"></td>
		                </tr>
	           		</tbody>
	            </table>
	            <h1 class="page-title">货款回款账户信息</h1>
				<table class="table-detail">
	                <tbody>
		                <tr>
		                    <td class="td-title pct10">机构名称</td>
		                    <td class="pct20">${fundPlanInfoVo.loanBackAccountBranchName }<input class="zui-input" value="${fundPlanInfoVo.loanBackAccountBranchName }"
								id="loanBackAccountBranchName" name="loanBackAccountBranchName" type="hidden"></td>
		                    <td class="td-title pct10">账户名</td>
		                    <td class="pct20">${fundPlanInfoVo.loanBackAccountName }<input class="zui-input" value="${fundPlanInfoVo.loanBackAccountName }"
								id="loanBackAccountName" name="loanBackAccountName" type="hidden"></td>
		                    <td class="td-title pct10">账号</td>
		                    <td class="pct30">${fundPlanInfoVo.loanBackAccount }<input class="zui-input" value="${fundPlanInfoVo.loanBackAccount }"
								id="loanBackAccount" name="loanBackAccount" type="hidden"></td>
							
		                </tr>
	           		</tbody>
	            </table>
	            <h1 class="page-title">额度信息</h1>
				<table class="table-detail">
	                <tbody>
		                <tr>
		                    <td class="td-title pct10">当前额度申请</td>
		                    <td class="pct20">${caseLimitApplyVo.currentApplyLimit }<input class="zui-input" value="${caseLimitApplyVo.currentApplyLimit }"
								id="currentApplyLimit" name="currentApplyLimit" ></td>
		                    <td class="td-title pct10">是否已获得备付资金分配 </td>
		                    <td class="pct20">${fundPlanInfoVo.isGetPrepareLimit }</td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct30"></td>
		                </tr>
	           		</tbody>
	            </table>
	            <h1 class="page-title">当前申请信息</h1>
				<table class="table-detail">
	                <tbody>
		                <tr>
		                    <td class="td-title pct10">申请人</td>
		                    <td class="pct20">${caseApplyVo.currentApplierName }<input class="zui-input" type="hidden" value="${caseLimitApplyVo.limitApplierEmpName }"
								id="currentApplierName" name="currentApplierName" ></td>
		                    <td class="td-title pct10">申请日期</td>
		                    <td class="pct20">${caseLimitApplyVo.applyDate }</td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct30"></td>
		                </tr>
	           		</tbody>
	            </table>
	           
			</div>
			</form>
			
			
			<h1 class="page-title" style="background-color: orange;">本案件申请记录</h1>
			<div class="p5">
				<div id="costitemTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.limitApply.getTheCaseLimitApplyHistory" context="admin"/>&jsoncallback=?id="'+${caseApplyVo.id }+',"singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
					<table>
	        			<tr>
	            			<th data-options="field:currentApplyLimit">申请额度（元）</th>
	            			<th data-options="field:fundPlanName">分配资金计划</th>
	            			<th data-options="field:limitApplierEmpName">申请人</th>
	            			<th data-options="field:allotDate">分配日期</th>
	            			<th data-options="field:cancelDate">取消日期</th>
	            			<th data-options="field:limitCancelEmpName">取消人</th>
				        </tr>
					</table>
				</div>
			</div>
		       	
		       
			</div>
		</div>
	</div>
	
	
	<div class="form-btn" style="background-color: gray;" >
       	<button id="caseLimitApplySave" type="button" class="btn-blue" >申请额度</button>
        <button id="cancelLimitApplyReturn" type="button" class="btn-blue" >取消额度</button>
	</div>
	 
</body>



<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, Loading, ZUI_MESSAGE_CLIENT) {
			
			//$("#currentApplierName").val("${caseApplyVo.currentApplierName }");
			//当caseApplyVo.actualApplyAmount为空时,则默认选择caseApplyVo.applyAmount值
			var currentApplyLimit = $("#currentApplyLimit").val();
			if(currentApplyLimit==null||actualApplyAmount.length==0){
				$("#currentApplyLimit").val("${caseApplyVo.applyAmount}");
			}
			
			$.ZUI.initForms("#caseBaseInfo_formDiv");
			
			//点击申请额度按钮之后的处理
			$("#caseLimitApplySave").click(function(){
				var params = {};
/* 				var actualApplyAmount = $("#actualApplyAmount").val();
				if(actualApplyAmount==null||actualApplyAmount.length==0){
					$("#actualApplyAmount").val("${caseApplyVo.applyAmount}");
				}
				params.applyLimit = actualApplyAmount; */
				params.caseLimitPlanInfoVo = $('#caseLimitApply_form').serialize();
				params.fundPlanName = ${fundPlanInfoVo.fundPlanName }.val();
				params.caseApplyId = $('#id').val(); 
				
				//保存额度申请信息
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.saveCaseLimitApply" context="admin"/>',
                    data: params,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	$.ZMessage.warning("提示", "额度申请成功", function () {
                        		/* $('#id').val(data.optional.caseLimitApplyVo.caseApplyId); */
                          	 });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                    },
			            error: function () {
			            	$.ZMessage.error("错误", "额度申请信息系统异常，请联系管理员", function () {
			             });
			            }
                });
			});
			
			$("#cancelLimitApplyReturn").click(function(){
				var params = {};
				params.id = $("#id").val();
				//保存额度申请信息
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.cancelCaseLimitApply" context="admin"/>',
                    data: params,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	$.ZMessage.warning("提示", "额度取消成功", function () {
                          	 });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                    },
			            error: function () {
			            	$.ZMessage.error("错误", "额度取消信息系统异常，请联系管理员", function () {
			             });
			            }
                });
			});
			
		});
		
	</script>

</html>