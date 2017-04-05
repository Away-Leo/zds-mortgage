<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zdsoft.framework.core.common.configure.AppParameter"%>
<%
	String localContextServer = request.getContextPath();
%>  
<!-- 资调 编辑-->
<div class="info-tab">
	<ul class="tabs">
		<li class="tabs-on" formatter="applicantInfo"><a href="javascript:void(0);">申请人信息</a></li>
		<li formatter="houseInfo"><a href="javascript:void(0);">押品信息</a></li>
		<li formatter="questionnaireInfo"><a href="javascript:void(0);">现场问卷</a></li>
		<li formatter="riskInfo"><a href="javascript:void(0);">风险信息</a></li>
		<li formatter="feeInfo"><a href="javascript:void(0);">费用信息</a></li>
		<li formatter="creditInfo"><a href="javascript:void(0);">征信信息</a></li>
		<li formatter="receivablePlan"><a href="javascript:void(0);">还款计划</a></li>
		<li formatter="otherProperty"><a href="javascript:void(0);">其他资产</a></li>
		<li formatter="familyInComeInfo"><a href="javascript:void(0);">家庭收入</a></li>
		<li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
	</ul>
	<div class="tabcontents" id="showTabcontents">
		<div id="applicantInfo">申请人信息</div>
		<div class="hide" id="houseInfo">押品信息</div>
		<div class="hide" id="questionnaireInfo">现场问卷</div>
		<div class="hide" id="riskInfo">风险信息</div>
		<div class="hide" id="feeInfo">费用信息</div>
		<div class="hide" id="creditInfo">征信信息</div>
		<div class="hide" id="receivablePlan">还款计划</div>
		<div class="hide" id="otherProperty">其他资产</div>
		<div class="hide" id="familyInComeInfo">家庭收入</div>
		<div class="hide" id="attachment">附件</div>
	</div>
</div>
<!-- 主借人关系 -->
<div class="hide" id="showRelationship"></div>

<script type="text/javascript">
	var submitData;//提交参数
	var caseApplyId = "${caseApplyId}";
	var sceneTypeCode = "YWDM0010202";
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/switch'],function ($,callback,Loading,tabSwitch) {
				var applicantInfo = true;
				var houseInfo=true
				var riskInfo = true;
				var questionnaireInfo = true;
				var feeInfo = true;
				var creditInfo = true;
				var receivablePlan = true;
				var otherProperty = true;
				var familyInComeInfo = true;
				var attachment = true;
				var otherProperty = true;
				
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
				
				//申请人信息
				callback.applicantInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadApplicantInfomation" context="admin"/>&caseApplyId=${caseApplyId}';
					if (applicantInfo) {
						$('#applicantInfo').load(url, function() {
							applicantInfo = false;
						});
					}
				}
				//押品信息
				callback.houseInfo = function() {
					var url = '<z:ukey key="com.cnfh.rms.marketing.houseProperty.editHousePropertyTab" context="admin"/>&caseApplyId=${caseApplyId}';
					if (houseInfo) {
						$('#houseInfo').load(url,function() {
							houseInfo = false;
						});
					}
				}
				//风险信息
				callback.riskInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.riskinformation.editRiskInformation" context="admin"/>&caseApplyId=${caseApplyId}';
					if (riskInfo) {
						$('#riskInfo').load(url,function() {
							riskInfo = true;
						});
					}
				}
				//现场问卷
				callback.questionnaireInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.question.editQuestionnaire" context="admin"/>&caseApplyId=${caseApplyId}&sceneTypeCode='+sceneTypeCode;
					if (questionnaireInfo) {
						$('#questionnaireInfo').load(url,function() {
							questionnaireInfo = false;
						});
					}
				}
				//费用信息
				callback.feeInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.edit" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (feeInfo) {
						$('#feeInfo').load(url,function() {
							feeInfo = false;
						});
					}
				}
				//征信信息
				callback.creditInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.edit" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (creditInfo) {
						$('#creditInfo').load(url,function() {
							creditInfo = false;
						});
					}
				}
				//还款计划
				callback.receivablePlan = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.intoReceivablePlanDetail" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (receivablePlan) {
						$('#receivablePlan').load(url,function() {
							receivablePlan = false;
						});
					}
				}
				//其他资产
				callback.otherProperty = function(){
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.otherProperty" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
					if (otherProperty) {
						$('#otherProperty').load(url,function() {
							otherProperty = false;
						});
					}
				}
				
				//家庭收入信息
				callback.familyInComeInfo = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo" context="admin"/>&caseApplyId=${caseApplyId}';
					if (familyInComeInfo) {
						$('#familyInComeInfo').load(url,function() {
							familyInComeInfo = false;
						});
					}
				}
				//附件
				callback.attachment = function() {
					var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.attachment.load" context="admin"/>&caseApplyId=${caseApplyId}&businessKey=${businessKey}';
					if (attachment) {
						$('#attachment').load(url,function() {
							attachment = false;
						});
					}
				}
				//初始化申请人信息
				var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadApplicantInfomation" context="admin"/>&caseApplyId=${caseApplyId}';
				if (applicantInfo) {
					$('#applicantInfo').load(url, function() {
						applicantInfo = false;
					});
				}
				
				//保存
			    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
			    	$("#showTabcontents>div").each(function(index){
			    		if($(this).attr("class")==undefined||$(this).attr("class")==""){
			    			//获取当前操作页签Id
			    			var matterId = $(this).attr("id");
			    			//设置参数
			    			var params = "";
			    			//设置保存路径
			    			var saveUrl = "";
			    			
			    			if(matterId=="applicantInfo"){ //申请人信息
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
			    				
			     				//获取客户信息
			    				 var param = $('#client_form').serialize();
			     				
			    				//获取联系方式对象
			    				var contactWayInfoList=$('#contactWayInfoList').ZTable("getRows");
			    				param += '&contactWayInfos='+JSON.stringify(contactWayInfoList);
			    				
			    				//获取配偶
			    				if($("#client_spouse_form").is(":visible")){
			    					param += "&"+$('#client_spouse_form').serialize();
			    				}
			    				 //获取工作单位对象
			    				var workUnitList=$('#workUnitList').ZTable("getRows");
			    				param += '&workUnitInfos='+JSON.stringify(workUnitList);
			    				
			    				param +='&caseApplyId='+caseApplyId;
			    				Loading.show();
			    				$.ajax({
						              type: 'post',
						              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.saveApplicantInfomation" context="admin"/>',
						              data: param,
						              async: false,
						               dataType: 'json',
						               success: function (data) {
						            	   Loading.hide();
						                  if (data.resultStatus == 0) {
						                	  $.ZMessage.success("成功", data.msg, function () {
						                		  $("#page-tab li").removeClass("tabs-disabled");
						                		  if($("#page-tab li[class='tabs-on']").attr('id')=="cust_newId"){
						                			  $("#page-tab li[class='tabs-on']").attr('id','cust_'+data.id);
						                			  $("#mainCustomerId").val(data.id);
						                		  }
						                		  //返回页面标题
						                		 var pageText=$("#page-tab li[class='tabs-on']>a").text().split(' ')[0];//去掉取出的空格
						                		 var pageHtml=$("#page-tab li[class='tabs-on']>a").html();
						                		 
						                		 var reg=new RegExp(pageText);
						                		 pageHtml=pageHtml.replace(reg,data.optional.joinTypeName+":"+data.optional.customerName);
						                		$("#page-tab li[class='tabs-on']>a").html(pageHtml);
						                		tabSwitch.init(); //初始化页签
				                              }); 
					                        }else if (data.resultStatus ==-2){
					                        	$.ZMessage.info("提示", data.msg, function () {
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
			    				
			    			}else if(matterId=="houseInfo"){ //押品信息
			    				//押品信息
			    				var validationRe = $.ZUI.validateForm($('#houseProperty_form'));
			    				//产权状态
			    				var validationSearchStatus = $.ZUI.validateForm($('#searchStatusForm'));
			    				//验证
			    				if(!validationRe||!validationSearchStatus){
			    					$.ZMessage.info("提示", "请完善必填项信息！", function () {
			                         });
			    					return false;
			    				}
			    				//form取值 押品信息
			    				var param = $('#houseProperty_form').serialize();
			    				//设置案件Id
			    				param += '&caseApplyId=' + caseApplyId;
			    				//form取值 产权状态
			    				param += "&" + $('#searchStatusForm').serialize();
			    				//获取产权人信息
			    				var propertyList = $('#propertyList').ZTable("getRows");
			    				param += '&propertyList=' + JSON.stringify(propertyList);
			    				//抵押情况
			    				var pledgeList = $('#pledgeList').ZTable("getRows");
			    				param += '&pledgeList=' + JSON.stringify(pledgeList);
			    				Loading.show();
			    				$.ajax({
						              type: 'post',
						              url: '<z:ukey key="com.cnfh.rms.marketing.houseProperty.saveHouseProperty" context="admin"/>',
						              data: param,
						              async: false,
						               dataType: 'json',
						               success: function (data) {
						            	   Loading.hide();
						                  if (data.resultStatus == 0) {
						                	  $.ZMessage.success("成功", data.msg, function () {
						                		  $(".houseBox li").removeClass("tabs-disabled");
						                		  if($(".houseBox li[class='tabs-on']").attr('id')=="house_newId"){
						                			  $(".houseBox li[class='tabs-on']").attr('id','house_'+data.optional.housePropertyId);
						                			  $("#houseId").val(data.optional.housePropertyId);
						                		  }
						                		  $("#searchId").val(data.optional.searchId);
						                		//返回页面标题
						                		 var pageText=$(".houseBox li[class='tabs-on']>a").text().split(' ')[0];//去掉取出的空格
						                		 var pageHtml=$(".houseBox li[class='tabs-on']>a").html();
						                		 
						                		 var reg=new RegExp(pageText);
						                		 pageHtml=pageHtml.replace(reg,data.optional.communityName);
						                		$(".houseBox li[class='tabs-on']>a").html(pageHtml);
						                		tabSwitch.init(); //初始化页签
				                              }); 
					                        }else if (data.resultStatus ==-2){
					                        	$.ZMessage.info("提示", data.msg, function () {
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
			    			}else if(matterId=="riskInfo"){ //风险信息
			    				//校验
			    				var isValidateriskInfoVo = $.ZUI.validateForm($('#riskInfo_form'));
			    				if(!isValidateriskInfoVo){
			    					$.ZMessage.info("提示", "请完善必填项信息！", function () {
			                        });	 
			    					return false;
			    				}
			    				//获取风险信息
			    				params += $('#riskInfo_form').serialize();
			    				saveUrl +='<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.riskinformation.save" context="admin"/>';
			    				Loading.show();
			    			}else if(matterId=="questionnaireInfo"){ //现场问卷
			    				var questionObj=window.saveQuestionnaire();
				    			if(questionObj.validFlg){
			    					params +="&caseApplyId="+questionObj.caseApplyId;
			    					params +="&sceneTypeCode="+questionObj.sceneTypeCode;
			    					params +="&questionList="+JSON.stringify(questionObj.questionList);
			    					saveUrl +='<z:ukey key="com.zdsoft.finance.question.saveQuestionnaire"  context="admin"/>';
			    					Loading.show();
				    			}else{
				    				if(!questionObj.isView){
					    				return;
				    				}
				    			}
			    			}else if(matterId=="feeInfo"){ //费用信息
			    				// 校验页面数据是否正确
			    				var validate = $.ZUI.validateForm($('#feeInfoForm'));
			    				if (!validate) {
			    					$.ZMessage.info("提示", "请完善必填项信息！", function () {
			                        });	 
			    		            return false;
			    		        }
			    				params += $('#feeInfoForm').serialize();
			    				saveUrl += '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.save" context="admin"/>&caseApplyId=${caseApplyId}';
			    				Loading.show();
			    			}else if(matterId=="creditInfo"){ //征信信息
			    				
			    			}else if(matterId=="receivablePlan"){ //还款计划
			    				
			    				var validationRe = $.ZUI.validateForm($('#receivablePlanForm'));
			    				var validationBank = $.ZUI.validateForm($('#bankAccountForm'));
			    				//校验
			    				if(!validationRe || !validationBank){
			    					$.ZMessage.info("提示", "请完善必填项信息！", function () {
			                        });
			    					return false;
			    				}
			    				params += $('#receivablePlanForm').serialize();
			    				params += "&" + $('#bankAccountForm').serialize();
			    				//获取所有还款计划
			    				var rowsData = $('#receivablePlanEdit').ZTable("getRows");
			    				params += '&receivablePlanJson=' + JSON.stringify(rowsData);
			    				Loading.show();
			    				$.ajax({
						              type: 'post',
						              url: '<z:ukey key="com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivableInfo" context="admin"/>&jsoncallback=?',
						              data: params,
						              async: false,
						              dataType: 'json',
						              success: function (data) {
						            	  Loading.hide();
										  if (data.resultStatus == 0) {
						                	  $.ZMessage.success("成功", data.msg, function () {
						                	  });
						                	  //给还款计划赋值ID  防止重复提交
						                	  $("#receivablePlanForm").find("input[name='id']").val(data.id);
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
			    				
			    			}else if(matterId=="otherProperty"){  //其他资产
			    				$.ZMessage.success("成功", "保存其他资产成功！", function () {
			                	  }); 
			    			}else if(matterId=="familyInComeInfo"){  //家庭收入
			    				//校验
			    	            var validate = $.ZUI.validateForm($('#familyIncome_form'));
			    	            if (!validate) {
			    	            	$.ZMessage.info("提示", "请完善必填项信息！", function () {
			                        });
			    	                return false;
			    	            }
			    	            var param = $('#familyIncome_form').serialize();
			    	            param += "&caseApplyId="+caseApplyId;
			    	            Loading.show();
			    	            $.ajax({
						              type: 'post',
						              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.save" context="admin"/>&jsoncallback=?',
						              data: param,
						              async: false,
						               dataType: 'json',
						               success: function (data) {
						            	   Loading.hide();
						                  if (data.resultStatus == 0) {
						                	  $.ZMessage.success("成功", data.msg, function () {
						                		//删除样式数据
						          				$("#page-family li").removeClass("tabs-disabled");
						                		  if($("#page-family li[class='tabs-on']").attr('id')=="family_newId"){
						                			  $("#page-family li[class='tabs-on']").attr('id','family_'+data.id);
						                		  }
						                		//返回页面标题
						                		 var pageText=$("#page-family li[class='tabs-on']>a").text().split(' ')[0];//去掉取出的空格
						                		 var pageHtml=$("#page-family li[class='tabs-on']>a").html();
						                		 
						                		 var reg=new RegExp(pageText);
						                		 pageHtml=pageHtml.replace(reg,data.sourceKey);
						                		$("#page-family li[class='tabs-on']>a").html(pageHtml);
						                		tabSwitch.init(); //初始化页签
				                              }); 
					                        }else if (data.resultStatus ==-2){
					                        	$.ZMessage.info("提示", data.msg, function () {
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
			    			}else if(matterId=="attachment"){  //附件
			    				
			    			}
						    //保存
			    			if(saveUrl==""){
			    				$.ZMessage.success("成功", "当前页签信息保存成功！");
			    			}else{
								$.ajax({
						              type: 'post',
						              url: saveUrl,
						              data: params,
						              async: false,
						               dataType: 'json',
						               success: function (data) {
						            	   Loading.hide();
						                  if (data.resultStatus == 0) {
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
								
			    			}
							
							//保存事项模块验证
							var validate ="";
							validate += "businessKey=" + caseApplyId;
			    			validate += "&matterName=dataSurvey";
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
			    	});
		     };
			    
		    //提交方法
		    ZDS_WORKFLOW_CLIENT.submitFunction = function(datas){
		    	//判断页签是否全部保存完
		    	var allSaveStatus=true;
		    	for(var key in saveList){
		    		if(saveList[key]==0&&key!="familyInComeInfo"){
		    			allSaveStatus=false;
		    			break;
		    		}
		    	}
		    	if(!allSaveStatus){
		    		$.ZMessage.info("提示", "请执行未保存的页签！");
		    		return false;
		    	}
		    	submitData=datas;//赋值参数
				var isShowReStatus = checkShowRelationship();
		    	if(isShowReStatus==-2){
		    		$.ZMessage.info("提示", "请添加一位主借人信息！");
		    		return false;
		    	}else{
					if(isShowReStatus){
						var relationshipUrl = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadRelationship" context="admin"/>';
				    	$('#showRelationship').load(relationshipUrl+"&caseApplyId=${caseApplyId}");
					}else{
						submitProcess(submitData);
					}
		    	}
		    }
		    
			window.submitProcess=function(datas) {
				//---------start------流程中有修改页面，需要提交业务数据操作------------------
				//流程参数
				var args = JSON.parse(datas.args);
				var params = '&processInstanceId=' + args.processInstanceId;
		        params += '&taskInstanceId=' + args.taskInstanceId;
		        params += '&taskId=' + args.taskId;
		        params += '&taskName=' + args.taskName;
		        params += '&jobAppCd=' + args.jobAppCd;
		        params += '&projectId=${caseApplyId}';
		        params += '&businessKey=${businessKey}';
				$.ajax({
					url:'<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.editJobSave" context="admin"/>',
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
		    	//---------end------流程中有修改页面，需要提交业务数据操作------------------
			}
			
			//检查是否要弹出选择关系弹窗
			function checkShowRelationship(){
				var params ='&caseApplyId=${caseApplyId}';
				var isShow = null;
				$.ajax({
					url:'<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.isShowRelationship" context="admin"/>',
					data:params,
					type:"post",
					async: false,
					dataType:"json",
					traditional:true,
					success:function(rdata){
						if(rdata.resultStatus == -2){
							isShow=rdata.resultStatus;
						}else{
							if(rdata.resultStatus == 0){
								if(rdata.optional.isShow == '1'){
									isShow = true;
								}else{
									isShow = false;
								}
								//执行回调函数
							}else{
								isShow = false;
							}
						}
					}
				});
				return isShow;
			}
	});
</script>