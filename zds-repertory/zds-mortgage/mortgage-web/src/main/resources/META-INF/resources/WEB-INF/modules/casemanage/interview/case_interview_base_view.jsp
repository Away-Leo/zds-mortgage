<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_js.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>案件面签总页面</title>
</head>
<body>
<div class ="frm-content">
	<div class="page-box">
	    <div class="page-title">案件面签</div>
	    <div class="p10">
		    <div class="info-tab">
		    	<!-- tab表单 -->
		        <ul class="tabs" id="info-tabs">
		         	<li class="tabs-on" formatter="caseView"><a href="javascript:void(0);">案件信息</a></li>
		            <li formatter="interview"><a href="javascript:void(0);">面签信息</a></li>
		            <li formatter="questionnaire"><a href="javascript:void(0);">现场问卷</a></li>
		            <li formatter="approvalOpinion"><a href="javascript:void(0);">审批意见</a></li>
		            <li formatter="attachment"><a href="javascript:void(0);">附件</a></li>
		        </ul>
		       	 <!-- 表单显示内容 -->
		        <div class="tabcontents" id="info-tabcontents">
		        	<div id="caseView"></div>
		            <div class="hide"  id="showInterview"></div>
		            <div class="hide"  id="showQuestionnaire"></div>
		            <div class="hide" id="showApprovalOpinion"></div>
		            <div class="hide"  id="attachment"></div>
		        </div>
		    </div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery',  'zd/switch','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 
	'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.form', 
	'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	function ($, Switch,CALLBACK,Loading) {
	 //开关
	var caseView=true;
    var interview=true;
    var questionnaire=true;
    var attachment=true;
    var approvalOpinion = true ;
    
    
    //案件基础信息
	CALLBACK.caseView=function(){
    	//查看
    	var url='<z:ukey key="com.zdsoft.finance.casemanage.caseApplyBaseInfoView" context="admin"/>&projectId=${caseApplyId}';
    	if(caseView){
        	$('#caseView').load(url,function(){
        		caseView=false;
        	});
    	}
    }
    //案件面签信息
	CALLBACK.interview=function(){
    	//面签信息
    	//查看页面
    	var url='<z:ukey key="com.cnfh.rms.casemanage.interview.viewInterviewDetails" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(interview){
        	$('#showInterview').load(url,function(){
        		interview=false;
        	});
    	}
    }
    
    //现场问卷信息
    CALLBACK.questionnaire=function(){
    	var url='<z:ukey key="com.zdsoft.finance.question.questionnaireView" context="admin"/>&caseApplyId=${caseApplyId}&sceneTypeCode=YWDM0010203';
    	if(questionnaire){
        	$('#showQuestionnaire').load(url,function(){
        		questionnaire=false;
        	}); 
    	} 
    }
    
    //审批意见
    CALLBACK.approvalOpinion=function(){
    	var url='<z:ukey key="com.cnfh.rms.casemanage.interview.approvalOpinion" context="admin"/>&caseApplyId=${caseApplyId}';
    	if(approvalOpinion){
        	$('#showApprovalOpinion').load(url,function(){
        		approvalOpinion=false;
        	}); 
    	}
    }
    
  //附件
    CALLBACK.attachment=function(){
    	var url='<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=${productId}&businessId=${caseApplyId}&caseApplyId=${caseApplyId}&LinkCode=14';
    	if(attachment){
        	$('#attachment').load(url,function(){
        		attachment=false;
        	}); 
    	}
    }
    
    $.ZUI.init();
    //编辑页面
	var url='<z:ukey key="com.zdsoft.finance.casemanage.caseApplyBaseInfoView" context="admin"/>&projectId=${caseApplyId}';
   
    $('#caseView').load(url); 
});
</script>
</html>