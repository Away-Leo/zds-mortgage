<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>补充</title>
</head>
<body>
	<div class="save">
	    <button id="saveData" class="btn-blue mr10">保存</button>
	    <button id="cancelBtn" class="btn-blue mr10">取消</button>
	</div>

	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="p10">
				<!-- 贷款合同—房屋抵押  /  抵押合同—房屋抵押  / 抵押物列表 -->		
				<%@ include file="contractSupplement/contractSupplement1_edit.jsp"%>
				<!-- 还款计划表  /  贷款补充合同  / 代办手续服务协议 / 保证金协议 / 董事会决议 / 股东会决议 / 股东决定书  / 委托扣款授权书 / 还款受托人   -->		
				<%@ include file="contractSupplement/contractSupplement2_edit.jsp"%>
				
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK) {
			$.ZUI.init();
			
			// 取消按钮
	        $('#cancelBtn').click(function(){
	        	 ZDS_MESSAGE_CLIENT.closeSelf();
	        });
			
			
			
			$('#saveData').click(function(){
				//不输入默认值为/
				for(var bcVal = 1; bcVal <= 76; bcVal++)
				{
					if( !( bcVal == 1 || bcVal == 21 || bcVal == 24 || bcVal == 32 || bcVal == 22 || bcVal == 33 || bcVal == 56
							|| bcVal == 34 || bcVal == 35 || bcVal == 36|| bcVal == 37 || bcVal == 38|| bcVal == 39|| bcVal == 40 
							|| bcVal == 41|| bcVal == 42|| bcVal == 49|| bcVal == 50|| bcVal == 51|| bcVal == 52 || bcVal == 58 
							|| bcVal == 62 || bcVal == 68 || bcVal == 73 || bcVal == 74 || bcVal == 43 || bcVal == 53)
							&& $("#bc" + bcVal ).val() == "" )
					{
						$("#bc" + bcVal ).val("/");
					}
				}
				var date = new Date();
				var endDate = new Date(date.getFullYear(), date.getMonth()+1, 0).getDate();
				//判断还款计划表中当月日期是否超过有效日期
				if($("#bc30" ).val() > endDate)
				{
					$.ZMessage.warning("警告", "还款计划表中当月日期超过有效日期!", function () {});
    				return;
				}
				//
				var contractSupplement1_edit_form = $.ZUI.validateForm($('#contractSupplement1_edit_form'));
				//
				var contractSupplement2_edit_form = $.ZUI.validateForm($('#contractSupplement2_edit_form'));
				var params = $('#contractSupplement1_edit_form').serialize();
				params += "&"+$('#contractSupplement2_edit_form').serialize();
				 //保存
				$.ajax({
                       type: 'post',
                       url: '<z:ukey key="com.zdsoft.finance.contract.saveCaseContractSupplement" context="admin"/>',
                       data: params,
                       dataType: 'json',
                       success: function (data) {
                    	   $.ZMessage.success("提示", "保存成功", function () {
	                        });
                    	   $('#conCaseContractSupplementId').val(data.optional.conCaseContractSupplementId)
                    	   $('#conCaseContractSupplement2Id').val(data.optional.conCaseContractSupplement2Id)
                   	 		$('#contractSupplement1_edit_form').ZTable("reload", {});
                   			$('#contractSupplement2_edit_form').ZTable("reload", {});
                       },
		            error: function () {
		            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		             });
		            }
                   });  
			});
			
			
		});
	</script>
</body>
</html>