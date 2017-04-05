<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>营销跟踪</title>
</head>
<body>
	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="p10">
				<!-- 营销申请基本信息 -->		
				<%@ include file="beforehandapply/basic_info_view.jsp"%>
				<div class="page-box">
					<!-- 客户信息 -->
					<%@ include file="beforehandapply/client_info_view.jsp"%>
					<!-- 客户信息》联系方式 -->
					<%@ include file="beforehandapply/client_contact_way_view.jsp"%>
					<!-- 客户信息》工作单位信息 -->
					<%@ include file="beforehandapply/client_work_unit_view.jsp"%>
				</div>
				
				<!-- 押品信息 -->		
				<%@ include file="beforehandapply/collateral_info_view.jsp"%>
				<!--  编辑抵押情况 -->   
				<%@ include file="beforehandapply/pledge_info_view.jsp"%>
				<!--  产权人信息 -->
				<%@ include file="beforehandapply/property_owner_view.jsp"%>	
				<!-- 上传征信 -->		
				<%@ include file="beforehandapply/upload_credit_file_view.jsp"%>
				<!-- 营销跟踪信息回显 -->
				<div id='tails'></div>
				<!-- 营销跟踪信息录入 -->
				<%@ include file="beforehand_tail_record_edit.jsp"%>
			</div>
		</div>
	</div>
	<div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-return" class="btn-blue mr10">返回</button>
	</div>	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'
		           ], function($, CALLBACK,ZUI_MESSAGE_CLIENT) {
			//定义全局变量
			var caseApplyId = '${beforehandApplyVo.id}';
			
			$('#tails').load('<z:ukey key="com.zdsoft.finance.marketing.listCaseTails" context="admin"/>&caseApplyId='+caseApplyId);
			
			//保存按钮绑定
			$("#btn-save").bind("click", function(){
				var params = $('#tailForm').serialize();
				save(params);
				
			});
			//返回绑定
			$("#btn-return").bind("click", function(){
				ZDS_MESSAGE_CLIENT.closeSelf();
			});
			//保存方法
			function save(params){
				var validation = $.ZUI.validateForm($('#tailForm'));
				if(!validation){
					return false;
				}
				$.ajax({
		            type: 'post',
		            url: '<z:ukey key="com.zdsoft.finance.marketing.saveOrUpdateCaseTail" context="admin"/>',
		            data: params,
		            dataType: 'json',
		            success: function (data) {
		            	if (data.resultStatus == 0) {
		            		//$('#caseTailVoId').val(data.id);
		            		$('#tails').load('<z:ukey key="com.zdsoft.finance.marketing.listCaseTails" context="admin"/>&caseApplyId='+caseApplyId);
		            		$('#tailRemark').val('');
		            		$.ZMessage.success("提示", "保存成功", function () {
		                   	 });
		            	}
		            	else{
		                   	$.ZMessage.error("错误", data.msg, function () {})
		            	};
			                   
		           	},
		            error: function () {
		              	$.ZMessage.error("错误", "保存案件跟踪信息异常，请联系管理员", function () {
		                });
		            }
		            
		            
		         });
		 	}
			 
		});
	</script>
</body>
</html>