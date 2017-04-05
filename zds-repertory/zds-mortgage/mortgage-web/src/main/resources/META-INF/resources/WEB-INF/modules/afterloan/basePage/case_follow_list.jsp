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
<div class="frm-content">
	<div class="page-box">
		<div class="page-title">跟催详情</div>
	    <div class="p10">
            <div id="followUpTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.afterloan.base.followInfoList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}","singleSelect":false,"pagination":false,"idField":"id","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:followType">跟催方式</th>
                        <th data-options="field:followStatus">跟催状态</th>
                        <th data-options="field:handlePlan">跟催预案</th>
                        <th data-options="field:childHandlePlan">子目录</th>
                        <th data-options="field:predictRepayDate">预计还款日期</th>
                        <th data-options="field:followCondiction">跟催情况</th>
                        <th data-options="field:employeeName">跟催人</th>
                        <th data-options="field:followDate">跟催日期</th>
                        <th data-options="field:supervisd">是否发起督办</th>
                        <th data-options="field:id" formatter="followUpView">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
       </div>
	</div>
	<div class="page-box">
		<div class="page-title">督办详情</div>
        <div class="p10">
            <div id="afterSuperviseTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.base.afterSuperviseList" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}","singleSelect":false,"pagination":false,"idField":"id","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:superviseType">督办类型</th>
                        <th data-options="field:superviseChildType">督办子类型</th>
                        <th data-options="field:superviseReceiverName">督办接收人</th>
                        <th data-options="field:remark">督办说明</th>
                        <th data-options="field:feedbackRresults">反馈结果</th>
                        <th data-options="field:superviserName">贷后人员</th>
                        <th data-options="field:feedbackDate">反馈时间</th>
                        <th data-options="field:id" formatter="afterSuperviseView">操作</th>
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
			
			// 跟催列表操作按钮
			CALLBACK.followUpView = function(){
				var html =  "<a title='跟催信息查看' class='btn-blue ' buttonCls='btn-blue' onclick='viewFollowUp'>查看</a>";
				return html;	
			};
			
			// 督办信息操作按钮
			CALLBACK.afterSuperviseView = function(){
				var html =  "<a title='督办信息查看' class='btn-blue ' buttonCls='btn-blue' onclick='viewAfterSupervise'>查看</a>";
				return html;
			};
			
			// 跟催信息查看事件
			CALLBACK.viewFollowUp = function(index,rowData){
				var followUpViewUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowUpDetail" context="admin"/>&jsoncallback=?&followUpId='+rowData.id;
 	            ZDS_MESSAGE_CLIENT.openMenuLink('followUpId','跟催信息查看',followUpViewUrl + "&openMethod=tabs");
			};
			
			// 督办信息查看事件
			CALLBACK.viewAfterSupervise = function(index,rowData){
				var afterSuperviseViewUrl = '<z:ukey key="com.zdsoft.finance.afterloan.base.initFollowUpDetail" context="admin"/>&jsoncallback=?&afterSuperviseId='+rowData.id;
 	            ZDS_MESSAGE_CLIENT.openMenuLink('afterSuperviseId','督办信息查看',afterSuperviseViewUrl + "&openMethod=tabs");
			};
			
			$.ZUI.init();
		});
	</script>
</body>
</html>