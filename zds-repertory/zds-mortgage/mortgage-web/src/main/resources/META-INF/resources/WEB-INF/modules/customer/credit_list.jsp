<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>征信录入</title>
</head>
<body>
<input id="caseApplyId" name="caseApplyId" type="hidden" value="${caseApplyId }">
	<div id="creditListContentDiv">
		<!-- 列表区域 -->
		<div class="page-box">
			<div class="page-title">征信录入</div>
			<div class="p10">
				<div id="tb-processConfig" class="zui-datagrid"
					zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.credit.getCreditList" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId }","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
					<table>
						<tr>
							<th data-options="field:customerName">姓名</th>
							<th data-options="field:credentialNo">身份证</th>
							<th data-options="field:joinTypeName">参与类型</th>
							<th data-options="field:creditStatus">状态</th>
							<th data-options="field:recordDate">录入时间</th>
							<th data-options="field:id" formatter="formatId">操作人</th>
						</tr>
					</table>
				</div>
			</div>
		</div>

	</div>

	<script>
		seajs.use([ 'jquery', 'zd/jquery.zds.page.callback',
				'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog',
				'zd/jquery.zds.button', 'zd/jquery.zds.message',
				'zd/jquery.zds.table', 'zd/jquery.zds.form',
				'zd/jquery.zds.message' ], function($, CALLBACK) {

			//操作格式化
	        CALLBACK.formatId=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onClick="recordCredit"><button class="btn-blue">录入</button></a>&nbsp;&nbsp;'+
	        	'<a href="javaScript:void(0)" onclick="editCredit"><button class="btn-blue">修改</button></a>&nbsp;&nbsp;' +
	        	'<a href="javaScript:void(0)" onclick="viewCredit"><button class="btn-blue">详情</button></a>';
	        	return data;
	        }
			
	   	 	//新增合作单位格式化
	        CALLBACK.recordCredit=function(){
            	ZDS_MESSAGE_CLIENT.openMenuLink('credit_record', '录入', '<z:ukey key="com.zdsoft.finance.casemanage.credit.recordCredit" context="admin"/>');
            }
			
	      /* //编辑格式化
	        CALLBACK.editCrm=function(index,row){
            	var editUrl = '<z:ukey key="com.zdsoft.finance.customer.editCustomer" context="admin"/>&jsoncallback=?&id='+row.id;
	            ZDS_MESSAGE_CREDIT.openMenuLink('编辑客户','编辑客户',editClientUrl + "&openMethod=tabs");
            }
	        
	    	//查看格式化
	        CALLBACK.viewCrm=function(index,row){
        		var editUrl = '<z:ukey key="com.zdsoft.finance.customer.findCustomerById" context="admin"/>&jsoncallback=?&id='+row.id;
	            ZDS_MESSAGE_CREDIT.openMenuLink('查看客户','查看客户',editClientUrl + "&openMethod=tabs");
            } */

			$.ZUI.init();
		});
	</script>

</body>
</html>