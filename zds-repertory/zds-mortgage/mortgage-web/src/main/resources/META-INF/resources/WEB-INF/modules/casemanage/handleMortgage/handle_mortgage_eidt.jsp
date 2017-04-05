<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-- 办理抵押 -->
<div class="info-tab">
	<ul class="tabs">
		<li class="tabs-on" formatter="showNotarize"><a href="javascript:void(0);">公证</a></li>
		<li formatter="showDetain"><a href="javascript:void(0);">查册入押</a></li>
		<li formatter="showMaterialPromise"><a href="javascript:void(0);">后补资料承诺</a></li>
		<li formatter="showAttachmentFile"><a href="javascript:void(0);">附件信息</a></li>
	</ul>
	<div class="tabcontents"  id="showTabcontents">  
		<div id="showNotarize">公证</div>
		<div class="hide" id="showDetain">查册入押</div>
		<div class="hide" id="showMaterialPromise">后补资料承诺</div>
		<div class="hide" id="showAttachmentFile">附件信息</div>
	</div>
</div>
<script type="text/javascript">
  	var isValidate=true;
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/switch'],function ($, callback,Loading) {
		var notarize = true;
		var detain=true
		var materialPromise = true;
		var attachmentFile = true;
		
		//设置保存过的页签信息
		var onsave=JSON.parse('${validateVoJson}');
		var onsaveObj={};
		for(var i=0;i<onsave.length;i++){
			onsaveObj[onsave[i].tabName]=onsave[i].executeTag;
		}
		var saveList={};
		$("#showTabcontents>div").each(function(){
			saveList[$(this).attr('id')]=0;
		})
		$.extend(saveList,onsaveObj);
		function onSave(){
			for(var key in saveList){
				if(saveList[key]==1){
					$('.info-tab>ul.tabs>li[formatter="'+key+'"]>a .tab-gou').remove();
					$('.info-tab>ul.tabs>li[formatter="'+key+'"]>a').append('<span class="tab-gou"></span>');
				}
			}
		}
		onSave();
		
		//公证
		callback.showNotarize = function() {
			var notarizeUrl = '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.editNotarize" context="admin"/>&caseApplyId=${caseApplyVo.id }';
			if (notarize) {
				$('#showNotarize').load(notarizeUrl, function() {
					notarize = false;
				});
			}
		}
		//初始化公证
		callback.showNotarize();
		//查册入押
		callback.showDetain = function() {
		var detainUrl = '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.initDetain" context="admin"/>&caseApplyId=${caseApplyVo.id }';
			if (detain) {
				$('#showDetain').load(detainUrl, function() {
					detain = false;
				});
			}
		}
		
		//后补资料承诺
		callback.showMaterialPromise = function() {
		var materialPromiseUrl = '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.editMaterialPromise" context="admin"/>&caseApplyId=${caseApplyVo.id }';
			if (materialPromise) {
				$('#showMaterialPromise').load(materialPromiseUrl, function() {
					materialPromise = false;
				});
			}
		}
		
		//附件信息
		callback.showAttachmentFile = function() {
		var attachmentFileUrl = '<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${caseApplyVo.productSubtypeId}&linkCode=14&caseApplyId=${caseApplyVo.id}&businessId=${caseApplyVo.id }';
			if (attachmentFile) {
				$('#showAttachmentFile').load(attachmentFileUrl, function() {
					attachmentFile = false;
				});
			}
		}
		
		//保存办理抵押
	  	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
			$("#showTabcontents>div").each(function(index){
				if($(this).attr("class")==undefined||$(this).attr("class")==""){
					//获取当前操作页签Id
	    			var matterId = $(this).attr("id");
	    			if(matterId=="showNotarize"){ //公证
	    				//获取(公证)——公证列表信息
	    				var notarizeList = $('#notarizeList').ZTable("getRows");
	    				var param = 'notarizeLists='+JSON.stringify(notarizeList);
	    				//(公证)——公证信息列表是否填写完成
	    				var notarizeFlag=true;
	    				$.each(notarizeList,function(i){
	    					var tr =notarizeList[i];
	    					var notarizeOffice =tr.notarizeOffice;
	    					if(notarizeOffice==""){
	    						notarizeFlag=false;
	    						return false;
	    					}
	    				});
	    				if(!notarizeFlag){
	    					$.ZMessage.info("提示","请完善公证列表信息！");
	    					return false;
	    				}
	    				Loading.show();
	    				isValidate=true;
	    				$.ajax({
				              type: 'post',
				              url: '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.saveNotarize" context="admin"/>',
				              data: param,
				              async: false,
				               dataType: 'json',
				               success: function (data) {
				            	   Loading.hide();
				                  if (data.resultStatus == 0) {
				                	  $('#notarizeList').ZTable("reload");
				                	  saveValidate();
				                	  $.ZMessage.success("成功", data.msg, function () {
				                	  });
		                          }else{
		                        	  $.ZMessage.error("失败", data.msg, function () {
	                        	  }); 
		                          }
			                    },
					            error: function () {
					            	Loading.hide();
					            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
					             	});
					            }
				         });
	    			}else if(matterId=="showDetain"){ //查册入押
	    				//获取(查册入押)——抵押情况列表信息
	    				var pledgeList = $("#pledgeList").ZTable("getRows");
	    				var param = '&pledgeInfoVoLists=' + JSON.stringify(pledgeList);
	    				
	    				//获取(查册入押)——产权人信息列表信息
	    				var propertyList = $('#propertyList').ZTable("getRows");
	    				param += '&propertyOwnerVoLists='+ JSON.stringify(propertyList);
	    				
	    				//获取(查册入押)——产权状态对象
	    				param += "&"+ $('#searchStatusForm').serialize();
	    				
	    				//获取(查册入押)——查册入押对象
	    				param += "&"+ $('#detainForm').serialize();
	    				
	    				//(查册入押)——产权人信息列表校验为是否为空列表
	    				if(propertyList.length == 0){
	    					$.ZMessage.info("提示", "请至少添加一条产权人信息");
	    					return false;
	    				}
	    				//(查册入押)——产权状态校验
	    				var validateSearchSatusInfoForm = $.ZUI.validateForm($('#searchStatusForm'));
	    				if(!validateSearchSatusInfoForm){
	    					$.ZMessage.info("提示", "请完善产权状态信息！");
	    					return false;
	    				} 
	    				//(查册入押)——查册入押校验
	    				var validateDetainInfoForm = $.ZUI.validateForm($('#detainForm'));
	    				if(!validateDetainInfoForm){
	    					$.ZMessage.info("提示", "请完善查册入押信息！");
	    					return false;
	    				}
	    				Loading.show();
	    				//设置模块保存为false
	    				isValidate= false;
	    				$.ajax({
				              type: 'post',
				              url: '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.saveDetain" context="admin"/>',
				              data: param,
				              async: false,
				               dataType: 'json',
				               success: function (data) {
				            	   Loading.hide();
				                  if (data.resultStatus == 0) {
				                	  $("#detainId").val(data.optional.detainId);
			                		  $("#searchId").val(data.optional.searchId);
			                		  window.saveValidateShow();
				                	  $.ZMessage.success("成功", data.msg, function () {
				                		  saveValidate();
				                	  });
		                          }else{
		                        	  $.ZMessage.error("失败", data.msg, function () {
	                          	  }); 
		                          }
			                    },
					            error: function () {
					            	Loading.hide();
					            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
					             	});
					            }
				         });
	    			}else if(matterId=="showMaterialPromise"){ //后补资料承诺
	    				//(后补资料承诺)——后补资料承诺列表
	    				if(!$("#materialPromiseList").ZTable("getRows")){
	    					$.ZMessage.info("提示", "请完善后补资料承诺信息！");
	    					return false;
	    				}
	    				//(后补资料承诺)——后补资料承诺列表
	    				var param = '&materialPromises=' +JSON.stringify($("#materialPromiseList").ZTable("getRows"));
	    				Loading.show();
	    				isValidate=true;
	    				$.ajax({
				              type: 'post',
				              url: '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.saveMaterialPromiseList" context="admin"/>',
				              data: param,
				              async: false,
				               dataType: 'json',
				               success: function (data) {
				            	   Loading.hide();
				            	   saveValidate();
				                  if (data.resultStatus == 0) {
				                	  $('#notarizeList').ZTable("reload");
				                	  $.ZMessage.success("成功", data.msg, function () {
				                	  });
		                          }else{
		                        	  $.ZMessage.error("失败", data.msg, function () {
	                      	  }); 
		                          }
			                    },
					            error: function () {
					            	Loading.hide();
					            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
					             	});
					            }
				         });
	    			}else if(matterId=="showAttachmentFile"){ //附件信息
	    				isValidate=true;
	    				 saveValidate();
	    			}
	    			
			    	//保存事项模块验证
	    			function saveValidate(){
		    			if(isValidate){
							var validate ="";
							validate += "businessKey=${caseApplyVo.id }";
			    			validate += "&matterName=handleMortgage";
			    			validate += "&tabName=" + matterId;
			    			validate += "&executeTag=" + 1;
							$.ajax({
					              type: 'post',
					              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
					              data: validate,
					              async: false,
					              dataType: 'json',
					              success: function (data) {
					            	  saveList[matterId]=1;
					            	  onSave();
				                  },
						          error: function () {
						            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
						            });
						         }
					        });
		    			}
	    			}
	    				
					
				}
			});
	   	
		   	//返回流程状态
		   	return WORKFLOW_FLAG;
	  	 };
	   
	  	//提交方法
	    ZDS_WORKFLOW_CLIENT.submitFunction = function(datas){
	    	//判断页签是否全部保存完
	    	var allSaveStatus=true;
	    	for(var key in saveList){
	    		if(saveList[key]==0){
	    			allSaveStatus=false;
	    			break;
	    		}
	    	}
	    	if(!allSaveStatus){
	    		$.ZMessage.info("提示", "请执行未保存的页签！");
	    		return false;
	    	}
	    	
	    	//流程参数
			var args = JSON.parse(datas.args);
			var params = '&processInstanceId=' + args.processInstanceId;
	        params += '&taskInstanceId=' + args.taskInstanceId;
	        params += '&taskId=' + args.taskId;
	        params += '&taskName=' + args.taskName;
	        params += '&jobAppCd=' + args.jobAppCd;
	        params += '&projectId=${caseApplyVo.id }';
	        params += '&businessKey=${businessKey}';
			$.ajax({
				url:'<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.editJobSaveHandleMortgage" context="admin"/>',
				data:params,
				type:"post",
				async: false,
				dataType:"json",
				traditional:true,
				success:function(rdata){
					if(rdata.resultStatus == 0){
						//执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,rdata.msg);
					}else{
						//执行回调函数
						ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,rdata.msg);
					}
				}
			});
	    }
	});
</script>