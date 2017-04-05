<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="page-box">
    <div class="page-title">风险审核</div>
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on" formatter="agencyRisk"><a href="javascript:void(0);">机构风险</a></li>
	            <li formatter="basicFunction"><a href="javascript:void(0);">案件基本信息</a></li>
	            <li formatter="collateral"><a href="javascript:void(0);">押品信息</a></li>
	            <li formatter="credit"><a href="javascript:void(0);">征信信息</a></li>
	            <li formatter="risk"><a href="javascript:void(0);">其它风险信息</a></li>		            
	            <li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
	            <li formatter="approvalOpinion"><a href="javascript:void(0);">审批意见</a></li>
	        </ul>
	        <div class="tabcontents" id="info-tabcontents">
	            <div id="showAgencyRisk"></div>
	            <div  id="showBasicFunction"></div>
                <div class="hide" id="showCollateral"></div>
                <div class="hide" id="showCredit"></div>
                <div class="hide" id="showRisk"></div>
                <div class="hide" id="showAttachment"></div>
                <div class="hide" id="showApprovalOpinion"></div>
	        </div>
	    </div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery',  'zd/switch','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.form', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	function ($, Switch,CALLBACK,Loading) {
	 //开关
    var agencyRisk=true;
    var basicFunction=true;
    var collateral=true;
    var credit=true;
    var risk=true;
    var attachment=true;
    var approvalOpinion=true;
    
    //回调  机构风险
	CALLBACK.agencyRisk=function(){
    	 //机构风险
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.agencyRiskView" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    	if(agencyRisk){
        	$('#showAgencyRisk').load(url,function(){
        		agencyRisk=false;
        	});
    	}
    }
    //案件基本信息
    CALLBACK.basicFunction=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.caseApplyBaseInfoView" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    	if(basicFunction){
        	$('#showBasicFunction').load(url,function(){
        		basicFunction=false;
        	}); 
    	}
    }
    //押品信息
    CALLBACK.collateral=function(){
    	 var url='<z:ukey key="com.zdsoft.finance.casemanage.riskPledgeView" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    	if(collateral){
        	$('#showCollateral').load(url,function(){
        		collateral=false;
        	});
    	} 
    }
    //征信信息
    CALLBACK.credit=function(){
     var url='<z:ukey key="com.zdsoft.finance.casemanage.customerCreditView" context="admin"/>&caseApplyId=${projectId}&caseApplyStage=YWDM009206&customerIds=${customerIds}&mainCustomerId=${mainCustomerId}';
    	if(credit){
        	$('#showCredit').load(url,function(){
        		credit=false;
        	});
    	} 
    }
    //风险信息
    CALLBACK.risk=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.riskInformationView" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    	if(risk){
        	$('#showRisk').load(url,function(){
        		risk=false;
        	});
    	}
    }
    //附件
    CALLBACK.attachment=function(){
    	// 不传递业务Id，查询案件所有附件
    	var url = '<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${productId}&linkCode=07&caseApplyId=${projectId}'; // &businessId=${businessKey}
//     	var url='<z:ukey key="com.zdsoft.finance.caseMaterial.initCaseMaterialProcessListPage" context="admin"/>&caseApplyId=${projectId}';
    	if(attachment){
        	$('#showAttachment').load(url,function(){
        		attachment=false;
        	});
    	}
    }
    
  	//审批意见
    CALLBACK.approvalOpinion=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.approvalInformationEdit" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}&taskInstanceId=${taskInstanceId}';
    	if(approvalOpinion){
        	$('#showApprovalOpinion').load(url,function(){
        		approvalOpinion=false;
        	});
    	}
    }
    $.ZUI.init();
       
	//初始化默认页面
    //var url='<z:ukey key="" context="admin"/>';
    //$('#showFunction').load(url);
//     var showApprovalOpinion='<z:ukey key="com.zdsoft.finance.casemanage.approvalInformationEdit" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    var url='<z:ukey key="com.zdsoft.finance.casemanage.agencyRiskView" context="admin"/>&projectId=${projectId}&businessKey=${businessKey}&processInstanceId=${processInstanceId}';
    $('#showAgencyRisk').load(url,function(){
    	agencyRisk=false;
	}); 
    // 保存风险审核意见
    var uri_save_approve_opinions = '<z:ukey key="com.zdsoft.finance.casemanage.saveOrUpRiskAuditApprove" context="admin"/>';
  	//保存
 	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
  		
 		var flag=$.ZUI.validateForm($('#taskProcessOptionApproval'));
  		if(flag){
  			var approvalOpinion =  $("#approvalOpinion").ZCheckbox('getText');
  	    	if(approvalOpinion=="" || approvalOpinion== null){
  	    		$.ZMessage.error("错误", "请选择风险措施信息");
  	    		return false ;
  	    	}
  	    	var args = JSON.parse(datas.args);
        	var params =  '&processInstanceId=' + args.processInstanceId;
	        	params += '&taskInstanceId=' + args.taskInstanceId;
	        	params += '&taskId=' + args.taskId;
	        	params += '&taskName=' + args.taskName;
	        	params += '&jobAppCd=' + args.jobAppCd;
	        	params += '&businessKey=${businessKey}';
	        	params += '&projectId=${projectId}';
	        	params += '&opinionIds=' + $("#approvalOpinion").ZCheckbox('getValue');
              	$.ajax({
                   url: uri_save_approve_opinions,
                   type: "post",
                   dataType: "json",
                   data: params,
                   success: function (msg) {
                       if (msg != null) {
                           if (msg.resultStatus == "0") {
                               //将相关数据回写用于保存方法
                        	   var opinionApproval = $("#opinionApproval").ZCombobox("getValue");
                     	    	$("#opinion").val(opinionApproval);
                     	    	// 保存时不拼接审批意见
                     	    	var opinioncontent = $("#opinioncontentApproval").val();
                     	    	$("#opinion_content").val(opinioncontent);
                     	    	ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,"保存审批意见成功！");
                           } else {
                        	   ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,msg.msg);
                           }
                       } else {
                    	   ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,"保存审批意见失败！");
                       }
                   }
               });
  		}else{
  			$.ZMessage.error("错误", "请完成审批意见！");
  		}
    };
    //提交方法
    ZDS_WORKFLOW_CLIENT.submitFunction = function (datas) {
  		
 		var flag=$.ZUI.validateForm($('#taskProcessOptionApproval'));
  		if(flag){
  			var approvalOpinion =  $("#approvalOpinion").ZCheckbox('getText');
  	    	if(approvalOpinion=="" || approvalOpinion== null){
  	    		$.ZMessage.error("错误", "请选择风险措施信息");
  	    		return false ;
  	    	}
  	    	var args = JSON.parse(datas.args);
        	var params =  '&processInstanceId=' + args.processInstanceId;
	        	params += '&taskInstanceId=' + args.taskInstanceId;
	        	params += '&taskId=' + args.taskId;
	        	params += '&taskName=' + args.taskName;
	        	params += '&jobAppCd=' + args.jobAppCd;
	        	params += '&businessKey=${businessKey}';
	        	params += '&projectId=${projectId}';
	        	params += '&opinionIds=' + $("#approvalOpinion").ZCheckbox('getValue');
              	$.ajax({
                   url: uri_save_approve_opinions,
                   type: "post",
                   dataType: "json",
                   data: params,
                   success: function (msg) {
                       if (msg != null) {
                           if (msg.resultStatus == "0") {
                               //将相关数据回写用于保存方法
                        	   var opinionApproval = $("#opinionApproval").ZCombobox("getValue");
                     	    	$("#opinion").val(opinionApproval);
                     	    	// 提交时拼接审批意见
                     	    	var opinioncontent = $("#opinioncontentApproval").val();
                     	    	$("#opinion_content").val(opinioncontent +"; "+ approvalOpinion);
                     	    	ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,"提交审批意见成功！");
                           } else {
                        	   ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,msg.msg);
                           }
                       } else {
                    	   ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,"保存审批意见失败！");
                       }
                   }
               });
  		}
    };
   });
</script>