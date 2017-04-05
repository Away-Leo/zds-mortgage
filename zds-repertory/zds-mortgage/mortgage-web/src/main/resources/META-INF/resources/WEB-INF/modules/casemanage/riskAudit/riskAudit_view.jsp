<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_js.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>风险审核</title>
</head>
<body>
<div class="page-box">
    <div class="page-title">风险审核</div>
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on" formatter="agencyRisk"><a href="javascript:void(0);">机构风险</a></li>
	            <li formatter="basicFunction"><a href="javascript:void(0);">案件基本信息</a></li>
	            <li formatter="collateral"><a href="javascript:void(0);">押品信息</a></li>
	            <li formatter="credit"><a href="javascript:void(0);">征信信息</a></li>
	            <li formatter="risk"><a href="javascript:void(0);">风险信息</a></li>		            
	            <li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
	            <li formatter="approvalOpinion"><a href="javascript:void(0);">审批意见</a></li>
	        </ul>
	        <div class="tabcontents" id="info-tabcontents">
	            <div id="showAgencyRisk"></div>
	            <div class="hide"  id="showBasicFunction"></div>
                <div class="hide" id="showCollateral"></div>
                <div class="hide" id="showCredit"></div>
                <div class="hide" id="showRisk"></div>
                <div class="hide" id="showAttachment"></div>
                <div class="hide" id="showApprovalOpinion"></div>
	        </div>
	    </div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/switch','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.form', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], 
			function ($, Switch, CALLBACK,Loading) {
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
   	var url='<z:ukey key="com.zdsoft.finance.casemanage.agencyRiskView" context="admin"/>&projectId=${projectId}';
   	if(agencyRisk){
       	$('#showAgencyRisk').load(url,function(){
       		agencyRisk=false;
       	});
   	}
   }
	//案件基本信息
    CALLBACK.basicFunction=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.caseApplyBaseInfoView" context="admin"/>&projectId=${projectId}';
    	if(basicFunction){
        	$('#showBasicFunction').load(url,function(){
        		basicFunction=false;
        	}); 
    	}
    }
    //押品信息
    CALLBACK.collateral=function(){
    	 var url='<z:ukey key="com.zdsoft.finance.casemanage.riskPledgeView" context="admin"/>&projectId=${projectId}';
    	if(collateral){
        	$('#showCollateral').load(url,function(){
        		collateral=false;
        	});
    	} 
    }
    //征信信息
    CALLBACK.credit=function(){
     var url='<z:ukey key="com.zdsoft.finance.creditManage.initCustomerCreditViews" context="admin"/>&caseApplyId=${projectId}&caseApplyStage=YWDM009206&customerIds=${customerIds}&mainCustomerId=${mainCustomerId}';
    	if(credit){
        	$('#showCredit').load(url,function(){
        		credit=false;
        	});
    	} 
    }
    //风险信息
    CALLBACK.risk=function(){
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.riskInformationView" context="admin"/>&projectId=${projectId}';
    	if(risk){
        	$('#showRisk').load(url,function(){
        		risk=false;
        	});
    	}
    }
    //附件
    CALLBACK.attachment=function(){
    	var url = '<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${productId}&linkCode=06&caseApplyId=${projectId}&businessId=${businessKey}';
    	if(attachment){
        	$('#showAttachment').load(url,function(){
        		attachment=false;
        	});
    	}
    }
      
  	//审批意见
    CALLBACK.approvalOpinion=function(){
    	var url = '<z:ukey key="com.zdsoft.finance.casemanage.approvalInformationOutProcessEdit" context="admin"/>&caseApplyId=${projectId}';
    	if(approvalOpinion){
        	$('#showApprovalOpinion').load(url,function(){
        		approvalOpinion=false;
        	});
    	}
    }
       
	//初始化默认页面
    var url='<z:ukey key="com.zdsoft.finance.casemanage.agencyRiskView" context="admin"/>&projectId=${projectId}';
    $('#showAgencyRisk').load(url,function(){
    	agencyRisk=false;
	}); 
    
    $.ZUI.init();
   });
</script>
</html>