<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
			<!-- 客户信息 -->
			<%@ include
				file="../../marketing/beforehandapply/client_info_edit.jsp"%>
			<!-- 客户信息》联系方式 -->
			<%@ include
				file="../../marketing/beforehandapply/client_contact_way_edit.jsp"%>
			<!-- 客户信息》工作单位信息 -->
			<%@ include
				file="../../marketing/beforehandapply/client_work_unit_edit.jsp"%>
		</div>
	</div>
</div>
<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form'], 
		function($, CALLBACK) {
			$.ZUI.init();
			window.saveData = function(){
				var status=false;
				//客户信息
				var isValidateClientForm = $.ZUI.validateForm($('#client_form'));
				//配偶
				var isValidateClientSpouseForm = true;
				if($("#client_spouse_form").is(":visible")){
				 isValidateClientSpouseForm = $.ZUI.validateForm($('#client_spouse_form'));
				}
				//校验
				if(!isValidateClientForm||!isValidateClientSpouseForm){
					$.ZMessage.info("提示", "请完善必填项信息！", function () {
                    });	 
					return false;
				}
				
				var params = $('#client_form').serialize();
				//获取联系方式对象
				var contactWayInfoList=$('#contactWayInfoList').ZTable("getRows");
				params += '&contactWayInfos='+JSON.stringify(contactWayInfoList);
				if($("#client_spouse_form").is(":visible")){
					params += "&"+$('#client_spouse_form').serialize();
				}
				 //获取工作单位对象
				var workUnitList=$('#workUnitList').ZTable("getRows");
				params += '&workUnitInfos='+JSON.stringify(workUnitList);
				params +='&caseApplyId=${caseApplyId}';
				//保存
				$.ajax({
		              type: 'post',
		              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.save" context="admin"/>',
		              data: params,
		              async: false,
		               dataType: 'json',
		               success: function (data) {
		                  if (data.resultStatus == 0) {
		                	  $.ZMessage.success("成功", data.msg, function () {
                          		status=true;
                            	 });
		                        }else{
		                        	status=false;
		                        }
		                    },
					            error: function () {
					            	 status=false;
					            }
		                });
				return status;
				
			}
			
			
		});
	</script>