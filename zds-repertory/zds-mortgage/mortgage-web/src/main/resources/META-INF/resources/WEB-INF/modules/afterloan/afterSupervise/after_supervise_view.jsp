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
<div class="frm-content frm-bottom">
	<div class="save">
	    <button id="goBack" class="btn-blue mr10">返回</button>  
	</div>    
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
			<%@ include file="after_supervise_common_view.jsp"%>  
		<!-- end -->  
			
		</div>
</div>
</div>   
		<script type="text/javascript">
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
				$("#goBack").click(function(){
					setTimeout(function(){
		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
		                	ZDS_MESSAGE_CLIENT.closeSelf();
		                },200);  
				});
				
				$.ZUI.init();
				});
		</script>
</body>
</html>