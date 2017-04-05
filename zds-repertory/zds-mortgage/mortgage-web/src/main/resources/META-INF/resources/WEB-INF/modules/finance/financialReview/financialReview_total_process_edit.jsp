<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content">
	<div class="page-box">
	    <div class="page-title">案件面签</div>
	    <div class="p10">
		    <div class="info-tab">
		    	<!-- tab表单 -->
		        <ul class="tabs" id="info-tabs">
		            <li class="tabs-on" formatter="caseView"><a href="javascript:void(0);">案件信息</a></li>
		            <li formatter="feeView"><a href="javascript:void(0);">费用信息</a></li>
		            <li formatter="archivalView"><a href="javascript:void(0);">档案信息</a></li>
		            <li formatter="repaymentPlan"><a href="javascript:void(0);">还款计划</a></li>
		            <li formatter="approvalOpinion"><a href="javascript:void(0);">审批意见</a></li>
		            <li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
		        </ul>
		       	 <!-- 表单显示内容 -->
		        <div class="tabcontents" id="showTabcontents">
		            <div id="caseView"></div>
		            <div class="hide"  id="feeView"></div>
		            <div class="hide" id="archivalView"></div>
		            <div class="hide"  id="repaymentPlan"></div>
		            <div class="hide" id="showApprovalOpinion"></div>
		            <div class="hide"  id="attachment"></div>
		        </div>
		    </div>
		</div>
	</div>
</div>
<script type="text/javascript">
var submitData;//提交参数
var caseApplyId = '${caseApplyId}';
seajs.use(['jquery',  'zd/switch','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 
	'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.form', 
	'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	function ($, Switch,CALLBACK,Loading) {
	
	 //开关
    var caseView=true;
    var feeView=true;
    var archivalView=true;
    var repaymentPlan=true;
    var attachment=true;
    var approvalOpinion = true ;
    
    //案件基础信息
	CALLBACK.caseView=function(){
    	//查看
    	var url='<z:ukey key="com.cnfh.rms.finance.financialReview.caseApplyView" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(caseView){
        	$('#caseView').load(url,function(){
        		caseView=false;
        	});
    	}
    }
    
    //费用信息
    CALLBACK.feeView=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.feeinfomation.view" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(feeView){
        	$('#feeView').load(url,function(){
        		feeView=false;
        		//保存事项模块验证
        		var validate ="";
        		validate += "businessKey=" + caseApplyId;
        		validate += "&matterName=financialReview";
        		validate += "&tabName=feeView";
        		validate += "&executeTag=" + 1;
        		$.ajax({
                      type: 'post',
                      url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
                      data: validate,
                      async: false,
                      dataType: 'json',
                      success: function (data) {
                    	  saveList['feeView']=1;
                    	  onSave();
                      },
        	          error: function () {
        	            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
        	            });
        	         }
                });
        	}); 
    	}
    }
    
  	//档案信息
    CALLBACK.archivalView=function(){
    	var url='<z:ukey key="com.cnfh.rms.finance.financialReview.archivesView" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(archivalView){
        	$('#archivalView').load(url,function(){
        		archivalView=false;
        		//保存事项模块验证
        		var validate ="";
        		validate += "businessKey=" + caseApplyId;
        		validate += "&matterName=financialReview";
        		validate += "&tabName=archivalView";
        		validate += "&executeTag=" + 1;
        		$.ajax({
                      type: 'post',
                      url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
                      data: validate,
                      async: false,
                      dataType: 'json',
                      success: function (data) {
                    	  saveList['archivalView']=1;
                    	  onSave();
                      },
        	          error: function () {
        	            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
        	            });
        	         }
                });
        	}); 
    	}
    }
  	
    //审批意见
    CALLBACK.approvalOpinion=function(){
    	var url='<z:ukey key="com.cnfh.rms.casemanage.interview.approvalOpinion" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(approvalOpinion){
        	$('#showApprovalOpinion').load(url,function(){
        		approvalOpinion=false;
        		//保存事项模块验证
        		var validate ="";
        		validate += "businessKey=" + caseApplyId;
        		validate += "&matterName=financialReview";
        		validate += "&tabName=approvalOpinion";
        		validate += "&executeTag=" + 1;
        		$.ajax({
                      type: 'post',
                      url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
                      data: validate,
                      async: false,
                      dataType: 'json',
                      success: function (data) {
                    	  saveList['approvalOpinion']=1;
                    	  onSave();
                      },
        	          error: function () {
        	            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
        	            });
        	         }
                });
        	}); 
        	}
    	}
  	
  	//还款计划
    CALLBACK.repaymentPlan=function(){
    	var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.intoReceivablePlanDetail" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId}';
    	if(repaymentPlan){
        	$('#repaymentPlan').load(url,function(){
        		repaymentPlan=false;
        	}); 
    	}
    }
  	
  	//附件
    CALLBACK.attachment=function(){
    	var url='<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${productId}&businessId=${businessKey}&caseApplyId=${caseApplyId}&LinkCode=14';
    	if(attachment){
        	$('#attachment').load(url,function(){
        		attachment=false;
        	}); 
    	}
    }
    
    $.ZUI.init();
    //初始化页面
	var url='<z:ukey key="com.cnfh.rms.finance.financialReview.caseApplyView" context="admin"/>&caseApplyId=${caseApplyId}';
    $('#caseView').load(url,function(){
    	caseView=false;
    	//保存事项模块验证
		var validate ="";
		validate += "businessKey=" + caseApplyId;
		validate += "&matterName=financialReview";
		validate += "&tabName=caseView";
		validate += "&executeTag=" + 1;
		$.ajax({
              type: 'post',
              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
              data: validate,
              async: false,
              dataType: 'json',
              success: function (data) {
            	  saveList['caseView']=1;
            	  onSave();
              },
	          error: function () {
	            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
	            });
	         }
        });
	}); 

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
	
	
	//保存
    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
	
    	$("#showTabcontents>div").each(function(index){
    		if($(this).attr("class")==undefined||$(this).attr("class")==""){
    			//获取当前操作页签Id
    			var matterId = $(this).attr("id");
    			//设置参数
    			var params = "";
    			//设置保存路径
    			var saveUrl = ""; var caseView=true;
    		    var feeView=true;
    		    var archivalView=true;
    		    var repaymentPlan=true;
    		    var attachment=true;
    			
    			if(matterId=="caseView"){ //案件信息
    				 $.ZMessage.success("成功", "保存成功！", function () {});
    			}else if(matterId=="feeView"){ //费用信息
    				 $.ZMessage.success("成功", "保存成功！", function () {});
    				
    			}else if(matterId=="archivalView"){ //档案复核
    				
    				var validate = $.ZUI.validateForm($('#formDiv'));
    		        if (validate) {
    		        	 var params = $('#formDiv').serialize();
    		             var args = JSON.parse(datas.args);
    		             params += '&processInstanceId=' + args.processInstanceId;
    		             params += '&taskInstanceId=' + args.taskInstanceId;
    		             params += '&taskId=' + args.taskId;
    		             params += '&taskName=' + args.taskName;
    		             params += '&jobAppCd=' + args.jobAppCd;
    		             params += '&businessKey=${businessKey}'
    		             //alert(params);
    		             $.ajax({
    		                 url:'<z:ukey key="com.cnfh.rms.finance.financialReview.saveOrUpdateFinancialReview" context="admin"/>',
    		                 data:params,
    		                 type:"post",
    		                 dataType:"json",
    		                 success:function(rdata){
    		                     if(rdata.resultStatus == 0){
    		                    	 $.ZMessage.success("成功", "保存成功！", function () {});
    		                     }else{
    		                    	 $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {});
    		                     }
    		                 }
    		             });
    		        }
    			}else if(matterId=="repaymentPlan"){ //还款计划
    				
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
    				saveUrl +='<z:ukey key="com.zdsoft.finance.casemanage.receivablePlanManager.saveReceivableInfo" context="admin"/>&jsoncallback=?';
    				
    			}else if(matterId=="approvalOpinion"){ //费用信息
    				 $.ZMessage.success("成功", "保存成功！", function () {});
    				
    			}else if(matterId=="attachment"){  //附件
    				
    			}
			    //保存
    			if(saveUrl==""){
    			}else{
					$.ajax({
			              type: 'post',
			              url: saveUrl,
			              data: params,
			              async: false,
			               dataType: 'json',
			               success: function (data) {
			                  if (data.resultStatus == 0) {
			                	  $.ZMessage.success("成功", data.msg, function () {
			                	  });
	                          }else{
	                        	  $.ZMessage.error("失败", data.msg, function () {
                            	  }); 
	                          }
		                    },
				            error: function () {
				            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
				             	});
				            }
			         });
    			}
				
				//保存事项模块验证
				var validate ="";
				validate += "businessKey=" + caseApplyId;
    			validate += "&matterName=financialReview";
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
   ZDS_WORKFLOW_CLIENT.submitFunction = function (datas) {
        //---------start------流程中有修改页面，需要提交业务数据操作------------------
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
        //校验
        var validate = $.ZUI.validateForm($('#formDiv'));
        if (validate) {
             var args = JSON.parse(datas.args);
             var params = 'processInstanceId=' + args.processInstanceId;
             params += '&taskInstanceId=' + args.taskInstanceId;
             params += '&taskId=' + args.taskId;
             params += '&taskName=' + args.taskName;
             params += '&jobAppCd=' + args.jobAppCd;
             params += '&businessKey=${businessKey}';
             params += '&caseApplyId=${caseApplyId}';
             //alert(params);
             $.ajax({
                 url:'<z:ukey key="com.cnfh.rms.finance.financialReview.saveOrUpdateFinancial" context="admin"/>',
                 data:params,
                 type:"post",
                 dataType:"json",
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
        //---------end------流程中有修改页面，需要提交业务数据操作------------------
    };

});
</script>