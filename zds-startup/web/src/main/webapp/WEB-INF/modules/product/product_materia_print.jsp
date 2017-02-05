<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>打印</title>
</head>
<body>
<div id="printContent">
</div>
<script type="text/javascript">
	seajs.use([
	           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog', 'zd/jquery.zds.combotree',
	           'zd/jquery.zds.combobox','zd/jquery.zds.message','ztree', 'zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.validate', 'zd/jquery.zds.seleter'
	           ], 
	 		function ($,ZTOOlS,CALLBACK,Zdialog) {
				$(document).ready(function(){
					$("#printContent").append('${content}');
					window.print();
				});

	});

</script>
</body>
</html>