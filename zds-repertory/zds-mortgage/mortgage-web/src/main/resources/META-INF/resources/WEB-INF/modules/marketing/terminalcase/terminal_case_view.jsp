<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>终端进件详情</title>
</head>
<body>
	<div  class="frm-content frm-bottom">
		<div class="page-box">
			<div class="p10">
				<!-- 营销申请基本信息 -->		
				<%@ include file="../beforehandapply/basic_info_view.jsp"%>
				<div class="page-box">
					<!-- 客户信息 -->
					<%@ include file="../beforehandapply/client_info_view.jsp"%>
					<!-- 客户信息》联系方式 -->
					<%@ include file="../beforehandapply/client_contact_way_view.jsp"%>
					<!-- 客户信息》工作单位信息 -->
					<%@ include file="../beforehandapply/client_work_unit_view.jsp"%>
				</div>
				
				<!-- 押品信息 -->		
				<%@ include file="../beforehandapply/collateral_info_view.jsp"%>
				<!--  编辑抵押情况 -->   
				<%@ include file="../beforehandapply/pledge_info_view.jsp"%>
				<!--  产权人信息 -->
				<%@ include file="../beforehandapply/property_owner_view.jsp"%>	
				<!-- 审批意见 -->
				<%@ include file="terminal_case_approval_opinion_view.jsp" %>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
			function($, CALLBACK) {
			$.ZUI.init();
			 
		});
	</script>
</body>
</html>