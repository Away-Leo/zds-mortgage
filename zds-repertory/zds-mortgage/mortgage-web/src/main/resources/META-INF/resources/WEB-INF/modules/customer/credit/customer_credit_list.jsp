<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content" id="customer_credit_page_div">
	<div class="page-box">
	    <div class="page-title">征信信息</div>
	    <div class="p10">
	        <div id="clientCredit-datagrid" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.customer.credit.queryBeforeCustomerCreditList" context="admin"/>&caseApplyId=${caseApplyId}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
			    <table>
			        <thead>
			        <tr>
			            <th data-options="field:customerName,width:20%">客户姓名</th>
			            <th data-options="field:credentiaType,width:15%">证件类型</th>
			            <th data-options="field:credentialNo,width:15%">证件号码</th>
			            <th data-options="field:joinType,width:10%">参与类型</th>
			            <th data-options="field:actualUsePerson,width:10%">是否实际用款人</th>
			            <th data-options="field:creditStatus,width:10%">状态</th>
			            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
			        </tr>
			        </thead>
			    </table>
			</div>
		</div>
	</div>
</div>	
<%@ include file='attachment_add.jsp'%>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], 
    		function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
    	$.ZUI.initDiv("#customer_credit_page_div");
    	
    	/* 资调-征信信息规则：
    	
    	页面数据取自于客户，去重
    	默认状态都为未获取征信，操作存在上传
    	
    	已在线授权书=1,已上传征信授权书=2,已上传征信=3
    	已上传征信授权书=4,已上传征信=5
    	已获取征信=6
    	
    	1-app征信选择了在线授权，状态更新为已在线授权书。操作（没有按钮）
    	2-app征信选择了上传授权书，状态更新为已上传征信授权书。操作（上传按钮存在）
    	3-app征信选择了上传征信，状态更新为已上传征信。操作（上传按钮存在）

    	4-web上传附件时候，选择了文件类型为（征信授权书）。状态更新为已上传征信授权书。操作（没有按钮）
    	5-web上传附件时候，选择了文件类型为（征信报告）。状态更新为已上传征信。操作（没有按钮）

    	6-当在线授权和上传征信授权书通过接口获取到信息后，状态更改为已获取征信。操作（存在查看征信按钮）。返回异常后（可再次上传）
    	7-当上传征信后，内务通过案件录入，完成征信录入后。状态更改为已获取征信。操作（存在查看征信按钮） */
		        
		//操作格式化
		CALLBACK.formatId=function(index,r){
    		
    		if(r.status=="1"||r.status=="4"||r.status=="5"){
    			return;
    		}
    		
    		var html = "";
    		//if(r.status=="2"||r.status=="3"){
    			//html += "<a class='icon-mbtn49 handler-icon c-blue' onclick='doUpload' title='上传'>上传</a>";
    			html += "<a onclick='doUpload'><button class='btn-blue' type='button'>上传</button></a>";
    		//}
    		
    		//if(r.status=="6"||r.status=="7"){
    			//html += "<a class='icon-mbtn49 handler-icon c-blue' onclick='doView' title='查看征信'>查看征信</a>";
    			html += "&nbsp;&nbsp;<a onclick='doView'><button class='btn-blue' type='button'>查看征信</button></a>";
    		//}
    		
    		return html;
		}
		
		//上传
		CALLBACK.doUpload=function(index,row){
			alert(row.id);
			//打开dialog
			$('#uploadAtta_customer_credit').Zdialog('open');
			$("#creditId").val(row.id);
		}
		
		//查看征信
		CALLBACK.doView=function(index,row){
			ZDS_MESSAGE_CLIENT.openMenuLink('customer_credit_view', '查看征信', '<z:ukey key="com.zdsoft.finance.customer.credit.initBeforeCustomerCreditViewPage" context="admin"/>&customerCreditId='+row.id);
		}

		//初始化
		$.ZUI.init();
	});
</script>