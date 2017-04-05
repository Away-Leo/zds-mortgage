<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 案件基本信息 -->
	<div class="page-box">
		<h1 class="page-title">基本信息</h1>
		<div class="p5">
	    <table class="table-detail">
	        <tr>
	            <td class="td-title pct15">案件号</td>
	            <td class="pct15" id="caseApplyCode"></td>
	            <td class="td-title pct15">接单日期</td>
	            <td class="pct15" id="applyDateStr"></td>
	            <td class="td-title pct15">子产品</td>
	            <td class="pct15" id="productSubtypeName"></td>
	        </tr>
	        <tr>
	            <td class="td-title pct15">拓展经理</td>
	            <td class="pct15" id="developmentManagerName"></td>
	            <td class="td-title pct15">拓展部门</td>
	            <td class="pct15" id="developmentDepartmentName"></td>
	            <td class="td-title pct15">机构</td>
	            <td class="pct15" id="mechanismName"></td>
	        </tr>
	        <tr>
	            <td class="td-title pct15">申请金额（元）</td>
	            <td class="pct15" id="applyAmount"></td>
	            <td class="td-title pct15">贷款期限（月）</td>
	            <td class="pct15" id="applyTerm"></td>
	            <td class="td-title pct15">还款方式</td>
	            <td class="pct15" id="repayMethod"></td>
	        </tr>
	        <tr>
	            <td class="td-title pct15">贷款利率</td>
	            <td class="pct15" id="applyRate"></td>
	            <td class="td-title pct15">逾期利率</td>
	            <td class="pct15" id="overdueRate"></td>
	            <td class="td-title pct15">终端</td>
	            <td class="pct15" id="cooperatorName"></td>
	        </tr>
	        <tr>
	            <td class="td-title pct15">资金来源</td>
	            <td class="pct15" id="capitalSource"></td>
	            <td class="td-title pct15"></td>
	            <td class="pct15"></td>
	            <td class="td-title pct15"></td>
	            <td class="pct15"></td>
	        </tr>
	    </table>
	</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/tools'],
		   function ($,ZTOOL) {
		$.ajax({
            url: '<z:ukey key="com.zdsoft.finance.specialApprove.publicCaseApplyBasicInfo" context="admin"/>&jsoncallback=?',
            type: "post",
            dataType: "json",
            async:false,
            data: {"caseApplyId":"${caseApplyId }","type":"0"},
            success: function (data) {
                if (data != null) {
                    if (data.resultStatus == "0") {
                    	//案件基本信息
                    	var basicInfoVo = data.optional.basicInfoVo;
                    	if(basicInfoVo){
                    		assignmentBasicInfo(basicInfoVo);
                    	}
                    	//终端
                    	var cooperatorTerminalVo = data.optional.cooperatorTerminalVo;
                    	if(cooperatorTerminalVo){
                    		assignmentTerminal(cooperatorTerminalVo);
                    	}
                    } else {
                        $.ZMessage.warning("错误", "操作失败！" + msg.msg);
                    }
                } else {
                    $.ZMessage.warning("错误", "操作失败！");
                }
            }
        });
		
		function assignmentBasicInfo(basicInfoVo){
			$("#caseApplyCode").text(basicInfoVo.caseApplyCode);
			$("#applyDateStr").text(basicInfoVo.applyDateStr);
			$("#productSubtypeName").text(basicInfoVo.productSubtypeName);
			$("#developmentManagerName").text(basicInfoVo.developmentManagerName);
			$("#developmentDepartmentName").text(basicInfoVo.developmentDepartmentName);
			$("#mechanismName").text(basicInfoVo.mechanismName);
			$("#applyAmount").text(ZTOOL.formatCurrency(basicInfoVo.applyAmount+""));
			$("#applyTerm").text(basicInfoVo.applyTerm);
			$("#repayMethod").text(basicInfoVo.repayMethodName);
			$("#applyRate").text(basicInfoVo.applyRate + basicInfoVo.applyRateUnitName);
			$("#overdueRate").text(basicInfoVo.overdueRate==0?"":basicInfoVo.overdueRate + basicInfoVo.overdueRateUnitName);
			$("#capitalSource").text(basicInfoVo.capitalSourceName);
		}
		
		function assignmentTerminal(cooperatorTerminalVo){
			$("#cooperatorName").text(cooperatorTerminalVo.terminalFullName);
		}
		
	});
</script>
