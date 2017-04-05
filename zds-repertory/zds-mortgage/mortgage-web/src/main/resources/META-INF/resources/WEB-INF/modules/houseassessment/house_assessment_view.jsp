<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp"%>
<title>查看评估详情</title>
</head>
<body>
	<div id="tempDiv"></div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	
	var tempUrl  ='<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.houseAssessmentView" context="admin"/>&jsoncallback=?&bizid='+${bizid};

	$("#tempDiv").load(tempUrl);

});
</script>
</html>