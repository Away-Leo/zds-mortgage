<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 征信信息 -->
<div id="creditListContentDiv">
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">征信信息</div>
		<div class="p5" id="creditInfoList"></div>
	</div>
</div>
<div id="uploadCreditList" style="display: none"></div>	
<script>
	seajs.use([ 'jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.dialog','zd/jquery.zds.button',
		'zd/jquery.zds.message','zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.message' ], function($, CALLBACK) {
      	//加载征信信息 列表
        $('#creditInfoList').ZTable({
 	       url : '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.loadCreditList" context="admin"/>&jsoncallback=?&caseApplyId='+caseApplyId,
 	       singleSelect : true,
 	       isRowNum : false,
 	       pagination : false,
 	       idField: "id",
 	       rows : 20,
 	       tableCls : 'table-index',//table的样式
 	       columns:[[
 	    	   	{field : 'customerId',			title : '客户id',			align : 'center',hidden:true},
 				{field : 'customerName',		title : '客户姓名',		align : 'center'},
 				{field : 'credentialType',		title : '证件类型code', 	align : 'center',hidden:true},
 				{field : 'credentialTypeName',	title : '证件类型', 		align : 'center'},
 				{field : 'credentialNo',		title : '证件号码', 		align : 'center'},
 				{field : 'joinType',			title : '参与类型code', 	align : 'center',hidden:true},
				{field : 'joinTypeName',		title : '参与类型', 		align : 'center'},
 				{field : 'actualUsePerson',		title : '是否实际用款人code',align : 'center',hidden:true},
 				{field : 'actualUsePersonName',	title : '是否实际用款人',	align : 'center'},
 				{field : 'linkStatusCode',		title : '状态code', 		align : 'center',hidden:true},
 				{field : 'linkStatusCodeName',	title : '状态', 			align : 'center'},
 				{field : 'id',					title : '操作', 			align : 'center',formatter:function(r,v){
 					var creditOperation;
 					//未获取征信
 					if(r.linkStatusCode == null || r.linkStatusCode == "" || r.linkStatusCode == "YWDM008501"){
 						creditOperation = '<a href="javaScript:void(0)" onclick="uploadCredit" class="btn-blue">上传</a>';
 					}
 					//已获取征信
 					if(r.linkStatusCode == "YWDM008505"){
 						creditOperation = '<a href="javaScript:void(0)" onclick="checkCredit" class="btn-blue">查看征信</a>';
 					}
 					//已上传征信报告
 					if(r.linkStatusCode == "YWDM008504"){
 						creditOperation = '<a href="javaScript:void(0)" onclick="uploadNextCredit" class="btn-blue">重新上传</a>';
 					}
 					//已在线授权书, 已上传征信授权书
 					if(r.linkStatusCode == "YWDM008502" || r.linkStatusCode == "YWDM008503"){
 					}
				 	return creditOperation;
 				}}
	 		] ],
	 		onDelete:function(index, rowData) {
	 			return true;
	 		},
	 		onLoadSuccess:function() {
	 		}
 		});
      	//上传按钮
        CALLBACK.uploadCredit = function(index,data){
      		var uploadCutomer = JSON.stringify(data);
   		    var upUrl = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.uploadCreditList" context="admin"/>';
       		$("#uploadCreditList").load(upUrl,{"uploadCutomer":uploadCutomer});
      	}
      	//查看征信
        CALLBACK.checkCredit = function(index,data){
      		var params = "&customerIds="+data.customerId+"&mainCustomerId="+data.customerId;
      		params += "&caseApplyId=" + data.caseApplyId + "&caseApplyStage=YWDM009206&isTab=true"
        	ZDS_MESSAGE_CLIENT.openMenuLink('checkCredit', "查看征信",'<z:ukey key="com.zdsoft.finance.creditManage.initCustomerCreditViews" context="admin"/>'+params);
      	}
      	//重新上传按钮
      	CALLBACK.uploadNextCredit = function(index,data){
      		var uploadCutomer = JSON.stringify(data);
   		    var upUrl = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.uploadCreditList" context="admin"/>';
       		$("#uploadCreditList").load(upUrl,{"uploadCutomer":uploadCutomer});
      	}
      	
        $.ZUI.init("#creditListContentDiv");
	});
	
</script>