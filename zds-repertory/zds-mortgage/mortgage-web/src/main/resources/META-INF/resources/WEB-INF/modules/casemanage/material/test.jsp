<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../../common/common_js.jsp'%>
<%@ include file="../../common/common_upload.jsp" %><!-- 上传专用 -->
<title>案件资料清单测试</title>
</head>
<body id="body">
	<div class="page-box">
	    <div class="page-title">功能列表</div>
	    <div class="p10">
		    <div class="info-tab">
		        <ul class="tabs" id="info-tabs">
		            <li class="tabs-on"><a href="javascript:void(0);">基本信息</a></li>
		            <li formatter="materialAttaFunction"><a href="javascript:void(0);">附件</a></li>
		            <li formatter="materialAttaFunction2"><a href="javascript:void(0);">征信信息</a></li>
		        </ul>
		        <div class="tabcontents" id="info-tabcontents">
		            <div id="showFunction"></div>
		        </div>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
	    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/switch'],function ($, CALLBACK,Switch) {	   
	    	//测试征信
			CALLBACK.materialAttaFunction2=function(){
				var url = '<z:ukey key="com.zdsoft.finance.customer.credit.initBeforeCustomerCreditListPage" context="admin"/>&caseApplyId=4028925459baef2d0159bb0116850000';
            	$(".zd-window").remove();
            	$('#showFunction').empty();
            	$('#showFunction').load(url);
            }     
			
	    	//测试附件
            CALLBACK.materialAttaFunction=function(){
            	var url='<z:ukey key="com.zdsoft.finance.caseMaterial.initCaseMaterialProcessListPage" context="admin"/>&caseApplyId=4028925459baef2d0159bb0116850000';
            	$(".zd-window").remove();
            	$('#showFunction').empty();
            	$('#showFunction').load(url);
            }       
	    });
    </script>
</body>
</html>