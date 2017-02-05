<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>产品编辑</title>
</head>
<body id="body">
<div class="page-box">
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on"  formatter="companyInfo"><a href="javascript:void(0);">公司信息</a></li>
       	 		<li formatter="contactsInfo"><a href="javascript:void(0);">联系人资料</a></li>
	        </ul>
	        <div class="tabcontents" id="info-tabcontents">
	            <div  id="showCompanyInfo"></div>
       			<div class="hide" id="showContactsInfo"></div>
	        </div>
	    </div>
	</div>
</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
		function($, CALLBACK, Switch,Loading) {
			 var company=true;
             var contacts=true;
		
             var id = "${evaluationId}";
             
             var url='<z:ukey key="com.zdsoft.finance.cooperator.evaluation.add" context="admin"/>&operationType=${operationType}&evaluationId=${evaluationId}';
        	if(company){
            	$('#showCompanyInfo').load(url,function(){
            		company=false;
            	});
        	}
			//联系人资料
			CALLBACK.contactsInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.evaluation.initContact" context="admin"/>&operationType=${operationType}&evaluationId=${evaluationId}';
            	if(contacts){
                	$('#showContactsInfo').load(url,function(){
                		contacts=false;
                	});
            	}
            }     
			 //公司信息
            CALLBACK.companyInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.evaluation.add" context="admin"/>&operationType=${operationType}&evaluationId=${evaluationId}';
            	if(company){
                	$('#showCompanyInfo').load(url,function(){
                		company=false;
                	});
            	}
            }
			 
        	$.ZUI.init();
			
		});
	</script>
</body>
</html>