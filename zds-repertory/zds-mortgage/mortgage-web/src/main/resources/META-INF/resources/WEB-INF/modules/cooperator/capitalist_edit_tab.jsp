<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>资方管理</title>
</head>
<body id="body">
<div class="frm-content">
<div class="page-box">
    <div class="p10">
	    <div class="info-tab">
	        <ul class="tabs" id="info-tabs">
	            <li class="tabs-on" formatter="companyInfo"><a href="javascript:void(0);">资方信息</a></li>
       	 		<li formatter="contactsInfo"><a href="javascript:void(0);">联系方式</a></li>
       	 		<li formatter="cooperatorIdea"><a href="javascript:void(0);">合作协议</a></li>
	        </ul>
	        <div class="tabcontents" id="info-tabcontents">
	            <div  id="showCompanyInfo"></div>
       			<div class="hide" id="showContactsInfo"></div>
       			<div class="hide" id="showCooperatorIdea"></div>
	        </div>
	    </div>
	</div>
</div>
</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK,Loading, Switch) {
            
             var company=true;
             var contacts=true;
             var cooperatorIdea=true;
             
			var url='<z:ukey key="com.zdsoft.finance.cooperator.capitalist.edit" context="admin"/>&operationType=${operationType}&capitalistId=${capitalist.id}';
            if(company){
            	$('#showCompanyInfo').load(url,function(){
            		company=false;
            	});
        	}
			    
			 //资方信息
            CALLBACK.companyInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.capitalist.edit" context="admin"/>&operationType=${operationType}&capitalistId=${capitalist.id}';
            	if(company){
                	$('#showCompanyInfo').load(url,function(){
                		company=false;
                	});
            	}
            }
          //联系人资料
			CALLBACK.contactsInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.capitalist.initCapitalistContact" context="admin"/>&operationType=${operationType}&capitalistId=${capitalist.id}';
            	if(contacts){
                	$('#showContactsInfo').load(url,function(){
                		contacts=false;
                	});
            	}
            } 
			 //合作协议
            CALLBACK.cooperatorIdea=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.idea.initCooperateIdea" context="admin"/>&operationType=${operationType}&capitalistId=${capitalist.id}';
            	if(cooperatorIdea){
                	$('#showCooperatorIdea').load(url,function(){
                		cooperatorIdea=false;
                	});
            	}
            }
        	$.ZUI.init();
			
		});
	</script>
</body>
</html>