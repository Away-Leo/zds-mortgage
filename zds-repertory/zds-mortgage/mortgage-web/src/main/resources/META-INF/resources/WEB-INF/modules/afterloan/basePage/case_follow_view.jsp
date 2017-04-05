<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>案件详情</title>
</head>
<body>
<div class="page-box">
	<div class="page-title">案件基本信息</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">案件号：</td>
				<td>${caseApplyCode }</td>
				<td class="td-title pct10">机构：</td>
				<td>${mechanismName }</td>
				<td class="td-title pct10">子产品：</td>
				<td>${productSubtypeName }</td>
			</tr>
			<tr>
				<td class="td-title pct10">放款金额(元)：</td>
				<td>${loanApplyAmount }</td>
				<td class="td-title pct10">合同开始日期：</td>
				<td>${contractStartDate }</td>
				<td class="td-title pct10">合同结束日期：</td>
				<td>${contractEndDate }</td>
			</tr>
			<tr>
				<td class="td-title pct10">逾期金额(元)：</td>
				<td>${overdueAmount }</td>
				<td class="td-title pct10">逾期日期：</td>
				<td>${overdueDate }</td>
				<td class="td-title pct10">逾期天数：</td>
				<td>${overdueDay }</td>
			</tr>
		</table>		
	</div>
	<div>
		<input type="hidden" id="caseApplyId" value="${caseApplyId }"/>
	</div>
	
	<div class="page-title">跟进催收单</div>
	<div class="p10">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">跟催方式：</td>
				<td>${followInfoVo.followType }</td>
				<td class="td-title pct10">跟催状态：</td>
				<td>${followInfoVo.followStatus }</td>
				<td class="td-title pct10">外呼对象：</td>
				<td>${followInfoVo.callOutObject }</td>
			</tr>
			<tr>
				<td class="td-title pct10">处置预案：</td>
				<td>${followInfoVo.handlePlan }</td>
				<td class="td-title pct10">子目录：</td>
				<td>${followInfoVo.childHandlePlan }</td>
				<td class="td-title pct10">跟踪部门：</td>
				<td>${followInfoVo.departmentName }</td>
			</tr>
			<tr>
				<td class="td-title pct10">预计还款日期：</td>
				<td>${followInfoVo.predictRepayDate }</td>
				<td class="td-title pct10">预计下次跟进日期：</td>
				<td>${followInfoVo.pretNextFlUpDate }</td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
			<tr>
				<td class="td-title">跟催情况：</td>
                <td colspan="5">${followInfoVo.followCondiction }</td>
			</tr>
			<tr>
				<td class="td-title pct10">跟催人：</td>
				<td>${followInfoVo.employeeName }</td>
				<td class="td-title pct10">跟催日期：</td>
				<td>${followInfoVo.followDate }</td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
		</table>		
	</div>
	
	<div class="page-title">督办事项</div>
	<div class="p10">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">督办类型：</td>
				<td>${afterSuperviseVo.superviseType }</td>
				<td class="td-title pct10">督办子类型：</td>
				<td>${afterSuperviseVo.superviseChildType }</td>
				<td class="td-title pct10"></td>
				<td></td>
			</tr>
			<tr>
				<td class="td-title pct10">督办接收人：</td>
				<td>${afterSuperviseVo.superviseReceiverName }</td>
				<td class="td-title pct10">督办抄送人：</td>
				<td>${afterSuperviseVo.superviseCopyReceiverName }</td>
				<td class="td-title pct10">处理时限：</td>
				<td>${afterSuperviseVo.processingDate }</td>
			</tr>
			<tr>
				<td class="td-title">督办说明：</td>
                <td colspan="5">${afterSuperviseVo.remark }</td>
			</tr>
		</table>		
	</div>
	
	<div class="page-title">反馈结果</div>
	<div class="page-box">
        <div class="p10">
            <div id="afterSuperviseTable" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.base.getAfterDispatchBySuperviseId" context="admin"/>&jsoncallback=?&id=${afterSuperviseVo.id }","singleSelect":false,"pagination":false,"idField":"id","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:superviserName">贷后人员</th>
                        <th data-options="field:feedbackDate">反馈时间</th>
                        <th data-options="field:feedbackRresults">反馈结果</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'], 
		function($, ZTOOL,CALLBACK) {
			$.ZUI.init();
		});
	</script>
</body>
</html>