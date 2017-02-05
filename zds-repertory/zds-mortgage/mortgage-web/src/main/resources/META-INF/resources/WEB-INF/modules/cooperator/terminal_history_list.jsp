<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include file='../common/common_js.jsp'%>
	<div class="frm-content" id="historyDiv">
		<div class="page-box">
			<div class="p10">
				<div id="terminal_datagrid_view" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.getTerminalHistory" context="admin"/>&cooperatorTerminal.id|E|S=${terminalId}&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index"}'>
					<table>
						<thead>
		        			<tr>
		            			<th data-options="field:planTimeFrame">计划时段</th>
		            			<th data-options="field:maintainer">维护人</th>
		            			<th data-options="field:approver">审批人</th>
		            			<th data-options="field:planApproveType">计划维护方式</th>
		            			<th data-options="field:planterminalState">计划终端状态</th>
		            			<th data-options="field:planMaintainThing">计划具体维护事项</th>
		            			<th data-options="field:isDoneName">是否完成</th>
		            			<th data-options="field:howThingCondition">具体落实执行情况</th>
					        </tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.loading','zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message','zd/jquery.zds.combobox', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter']
    , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			//操作
    	$.ZUI.initGrid('#historyDiv');
		$.ZUI.initForms('#historyDiv');
		});
	</script>
	
