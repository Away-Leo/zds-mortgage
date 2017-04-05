<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp"%>
<title></title>
</head>

<body>
<div>
<iframe name="ifr1" id="ifr1" frameborder="0" scrolling="auto" src=${houseAssessmentUrl }  width="100%" height="600px"></iframe> 
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback'], 
		function($, CALLBACK,ZUI_MESSAGE_CLIENT) {
			 //关闭之前的操作
		    ZDS_MESSAGE_CLIENT.closeBeforeTab=function(){
		    	$.ajax({
		              type: 'post',
		              url: '<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.queryHouseEvaluateSumPrice" context="admin"/>',
		              data: {
		            	  businessId:"${param.bizId}",
		            	  houseKey:"${param.houseKey}",
		            	  houseArea:"${param.houseArea}",
		            	  province:"${param.province}",
		            	  city:"${param.city}",
		            	  district:"${param.district}",
		            	  houseAddress:"${param.houseAddress}"
		              },
		               dataType: 'json',
		               success: function (data) {
		            	   ZDS_MESSAGE_CLIENT.closeSelf();
		                  if (data.resultStatus == 0) {
		                	  var sumPrice = data.optional.sumPrice;
		                	 if (sumPrice != null && sumPrice > 0) {
				      		        //先删除，再刷新openner
				      		        var pdata={"sumPrice":sumPrice};
				      		        //关闭页面的操作
				      		        ZDS_MESSAGE_CLIENT.refreshOpenner(pdata);
		                	  } 
	                       }
	                    },
			            error: function () {
			            	$.ZMessage.error("错误", "查询房产评估价失败", function () {
			             	});
			            }
		         });
		    } 

	
	});
</script>
</html>