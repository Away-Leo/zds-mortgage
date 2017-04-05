<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp'%>   
<title>案件详情</title>
</head>
<body>  
	<div class="save">
	    <button id="save" class="btn-blue mr10">保存</button>
	    <button id="submit" class="btn-blue mr10">提交</button>
	</div>  
	
<div class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
			<!-- 案件信息end -->
			<!-- 案件信息begin -->    
			<%@ include file="after_caseApply_view.jsp"%>  
			<!-- 案件信息end -->  
			<!-- 案件信息end -->
			<!-- 联系人信息begin -->    
			<%@ include file="after_customer_list.jsp"%>  
			<!-- 联系人信息end -->  
			<!-- 最近跟催列表列表 -->    
		<!-- begin -->
			<%@ include file="../loanMiddleMonitor/lately_followInfo_list.jsp"%>  
		<!-- end -->  
		<!-- begin -->
			<%@ include file="after_supervise_common_edit.jsp"%>  
		<!-- end -->  
			
		</div>
</div>
</div>   
		<script type="text/javascript">
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
				
				function saveOrUpdate(submitStatus,flag){
					var isValidateSuperviseForm = $.ZUI.validateForm($('#supervise_form'));
					if($("#superviseReceiverName").val()==''){
						$.ZMessage.info("提示", "请选择督办接收人！", function () {
	                    });	     
						return false;
					}
			 		//校验
					if(!isValidateSuperviseForm){ 
						$.ZMessage.info("提示", "请完善必填项信息！", function () {
	                    });	   
						return false;
					}  
					var params = $('#supervise_form').serialize();
						params+="&submitStatus="+submitStatus;   
						$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.afterloan.afterSupervise.launchAfterSupervise" context="admin"/>',
		                    data: params,
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	$("#id").val(data.id);
		                        	if(!flag || submitStatus){    
			                        	$.ZMessage.success("成功", data.msg, function () {
			                        		if(submitStatus){
			                        			setTimeout(function(){
		        	     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
		        	     		                	ZDS_MESSAGE_CLIENT.closeSelf();
		        	     		                },200);  
			                        		}
			                          });
		                        	}else{
		                        		//事物问题，先保存再提交，不然前置事项获取不了启动流程的时候的数据
		                        		saveOrUpdate(true);
		                        	}
		                        }else{
			                           	$.ZMessage.error("错误", "保存失败！", function () {
				                        });
		                        }
		                    },
				            error: function () {
				            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员！", function () {
				             });
				            }
		                }); 
				}
				//保存
				$("#save").click(function(){   
					saveOrUpdate(false);
				});
				//提交
				$("#submit").click(function(){   
					saveOrUpdate(false,true);
				});
				
				$.ZUI.init();
				});
		</script>
</body>
</html>