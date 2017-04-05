<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>营销登记详情</title>
</head>
<body>
<div class="save">
    <button id="back" class="btn-blue mr10">返回</button>
</div>
<div  class="frm-content frm-bottom">
	<div class="page-box">
		<div class="p10">
			<!-- 营销申请基本信息 -->		
			<%@ include file="beforehandapply/basic_info_view.jsp"%>
			<div class="page-box">
				<!-- 客户信息 -->
				<%@ include file="beforehandapply/client_info_view.jsp"%>
				<!-- 客户信息》联系方式 -->
				<%@ include file="beforehandapply/client_contact_way_view.jsp"%>
				<!-- 客户信息》工作单位信息 -->
				<%@ include file="beforehandapply/client_work_unit_view.jsp"%>
			</div>
			
			<!-- 押品信息 -->		
			<%@ include file="beforehandapply/collateral_info_view.jsp"%>
			<!--  编辑抵押情况 -->   
			<%@ include file="beforehandapply/pledge_info_view.jsp"%>
			<!--  产权人信息 -->
			<%@ include file="beforehandapply/property_owner_view.jsp"%>	
			<!-- 上传征信 -->		
			<%@ include file="beforehandapply/upload_credit_file_view.jsp"%>
		</div>
	</div>
</div>
<script type="text/javascript">
	//返回页面
	$('#back').click(function(){
     	ZDS_MESSAGE_CLIENT.closeSelf();
	});
</script>
</body>
</html>