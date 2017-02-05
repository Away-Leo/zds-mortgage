<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>营销登记</title>
</head>
<body>
	<div class="save">
	    <button id="saveBeforehandApply" class="btn-blue mr10">保存</button>
	    <button id="submitBeforehandApply" class="btn-blue mr10">提交</button>
	</div>

	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="p10">
				<!-- 基本信息 -->		
				<%@ include file="beforehandapply/basic_info_edit.jsp"%>
				<div class="page-box">
					<!-- 客户信息 -->
					<%@ include file="beforehandapply/client_info_edit.jsp"%>
					<!-- 客户信息》联系方式 -->
					<%@ include file="beforehandapply/client_contact_way_edit.jsp"%>
					<!-- 客户信息》工作单位信息 -->
					<%@ include file="beforehandapply/client_work_unit_edit.jsp"%>
				</div>
				
				<!-- 押品信息 -->		
				<%@ include file="beforehandapply/collateral_info_edit.jsp"%>
				<!--  编辑抵押情况 -->   
				<%@ include file="beforehandapply/pledge_info_edit.jsp"%>
				<!--  产权人信息 -->
				<%@ include file="beforehandapply/property_owner_edit.jsp"%>	
				<!-- 上传征信 -->		
				<%@ include file="beforehandapply/upload_credit_file_edit.jsp"%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK) {
			$.ZUI.init();
			
			//保存营销申请信息
			$('#saveBeforehandApply').click(function(){
				saveData(false);
			});
			
			//提交营销申请信息
			$('#submitBeforehandApply').click(function(){
				saveData(true);
			});
			
			function saveData(status){
				//基本信息
				var isValidateBeforehandApply = $.ZUI.validateForm($('#beforehandApply_form'));
				//客户信息
				var isValidateClientForm = $.ZUI.validateForm($('#client_form'));
				//配偶
				var isValidateClientSpouseForm = true;
				if($("#client_spouse_form").is(":visible")){
				 isValidateClientSpouseForm = $.ZUI.validateForm($('#client_spouse_form'));
				}
				//押品信息
				var isValidateHouseProperty = $.ZUI.validateForm($('#houseProperty_form'));
				//校验
				if(!isValidateBeforehandApply || !isValidateClientForm || !isValidateClientSpouseForm ||!isValidateHouseProperty){
					$.ZMessage.info("提示", "请完善必填项信息！", function () {
                    });	 
					return false;
				}
					
				var params = $('#beforehandApply_form').serialize();
					params += "&"+$('#houseProperty_form').serialize();
					params += "&"+$('#client_form').serialize();
					
					params += '&submitStatus=' + status;
					if($("#client_spouse_form").is(":visible")){
						params += "&"+$('#client_spouse_form').serialize();
					}
				//获取联系方式对象
				var contactWayInfoList=$('#contactWayInfoList').ZTable("getRows");
				params += '&contactWayInfos='+JSON.stringify(contactWayInfoList);
				
				//获取工作单位对象
				var workUnitList=$('#workUnitList').ZTable("getRows");
				params += '&workUnitInfos='+JSON.stringify(workUnitList);
				
				//获取抵押情况对象
				var pledgeList=$('#pledgeList').ZTable("getRows");
				params += '&pledgeInfos='+JSON.stringify(pledgeList);
				
				//获取产权人情况对象
				var propertyList=$('#propertyList').ZTable("getRows");
				params += '&propertyInfos='+JSON.stringify(propertyList);
				
				//获取上传id对象
				var creditAttachments=$('#creditAttachment').val();
				params += '&creditAttachmentIds='+creditAttachments;
				 //保存
				$.ajax({
                       type: 'post',
                       url: '<z:ukey key="com.zdsoft.finance.marketing.saveBeforehandApply" context="admin"/>',
                       data: params,
                       dataType: 'json',
                       success: function (data) {
                           if (data.resultStatus == 0) {
                        	   var msg = "保存营销登记成功！";
                        	   if(status){
                        		   msg = "下一节点处理人："+data.optional.currentDealEmpNm;
                        	   }
                           	$.ZMessage.success("成功", msg, function () {
                           		$('#beforehandApplyId').val(data.optional.beforehandApplyId);
                           		//客户id
                           		$("#mainCustomerId").val(data.optional.customerId);
                           		//押品id
                           		$("#housePropertyId").val(data.optional.housePropertyId);
                           		//配偶id
                           		if(data.optional.spouseId){
                           			$("#spouseId").val(data.optional.spouseId);
                           		}
                           		if(status){
                           			setTimeout(function(){
    	     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
    	     		                	ZDS_MESSAGE_CLIENT.closeSelf();
    	     		                },200);
                           		}
                             });
                           }else{
	                           	$.ZMessage.error("错误", data.msg, function () {
		                        });
                           }
                       },
		            error: function () {
		            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		             });
		            }
                   }); 
			}
		});
	</script>
</body>
</html>