<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>集团处理</title>

<%@ include file="../../common/common_js.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://192.168.18.253:8080/dev/static/assets/css/style.css"> -->
</head>
<body>
<!-- 复议价变更 -->
<div id="div-evluatedAppeal" class="page-box">
	<div class="page-title" id="div-group-handle-form">复议价变更</div>
	<div class="p5">
		<form id="firstForm" class="zui-form " method="post" enctype="multipart/form-data">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>风控复议价</td>
					<td class="pct20">
						<dl class="form-item form-auto">
							<dd class="detail">
		                       <input class="zui-input zui-validatebox" type="text" validate-type="Require,Digital[10-2]" id = "appealAprolAmount" name="appealAprolAmount" value="${evaluatedAppealVo.appealAprolAmount }" >
		                       <input type="hidden" id = "id" name="id" value="${evaluatedAppealVo.id }">
		                    </dd>
	                   	</dl> 
					</td>
					<td class="td-title pct10"></td>
	                <td class="pct20"></td>
	                <td class="td-title pct10"></td>
	                <td class="pct30"></td>
	               </tr>
	        </table>
    </div>
</div>
<!-- 地址与价格block -->	
<div id="div-evluatedAppeal" class="page-box">
		<div class="page-title" id="div-detain-form">地址与价格</div>
	<div class="p5">
		<form id="evaluatedAppealForm" class="zui-form " method="post" enctype="multipart/form-data">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10">押品地址</td>
					<td class="pct20">${fullAdress }</td>
					<td class="td-title pct10">申诉时间</td>
					<td class="pct20" id="appealDate">${evaluatedAppealVo.appealDate }</td>
					<td class="td-title pct10">有无特殊因素</td>
					<td class="pct30">${evaluatedAppealVo.isSpecificFactorName }</td>
				</tr>
				<tr>
					<td class="td-title">备注</td>
					<td colspan="5">${evaluatedAppealVo.remark }</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div id="tempDiv"></div>
<!-- 变更记录 -->
<div class="page-box">
       <h1 class="page-title">变更记录</h1>
       <div class="p5">
       	<div id="changedRecord">
		</div>
	</div>
</div>

<div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-back" class="btn-blue mr10">返回</button>
</div>	
</body>
<script type="text/javascript">
seajs.use([ 'jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.button', 'zd/jquery.zds.table',
			'zd/jquery.zds.form', 'zd/jquery.zds.message'],function($, CALLBACK,ZTOOlS,ZTable) {
	//定义全局变量
	var housePropertyId = '${evaluatedAppealVo.housePropertyId}';
	var appealDatea=$("#appealDate").html();
	$("#appealDate").html(ZTOOlS.strToTime(appealDatea));
	//加载房产评估数据
	var tempUrl  ='<z:ukey key="com.zdsoft.finance.houseassessment.houseEvaluate.getPriceForCompany" context="admin"/>&bizid='+housePropertyId+'&groupHandlePage="yes"';

	$("#tempDiv").load(tempUrl);

	//加载表格
	$('#changedRecord').ZTable({
	       url : '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.group.listHandleAppealRecord" context="admin"/>&housePropertyId=' +housePropertyId ,
	       singleSelect : true,
	       isRowNum : false,
	       pagination : true,
	       currentPage : 1,
	       idField: "id",
	       tableCls : 'table-index',//table的样式
	       columns:[[
				{field : 'appealAprolAmount',title : '复议价(元)', align : 'center'},
				{field : 'handleEmployeeName',title : '变更人', align : 'center'},
				{field : 'handleDate',title : '变更时间', align : 'center', formatter:function(r,v){
						return ZTOOlS.strToTime(v);
					}
				}
			] ],
			onLoadSuccess:function() {
			}
		});

	//保存按钮绑定
	$("#btn-save").bind("click", function(){
		var submitted = '1';
		var id = $('#id').val();
		var appealAprolAmount = $('#appealAprolAmount').val()
		var params = "&submitted="+submitted + "&id="+id+"&appealAprolAmount="+appealAprolAmount;
		save(params,true);
		
	});
	//返回
	$("#btn-back").bind("click", function(){
		ZDS_MESSAGE_CLIENT.refreshOpenner();
		ZDS_MESSAGE_CLIENT.closeSelf();
	});
	//保存方法
	function save(params,submitted){
		var validation = $.ZUI.validateForm($('#firstForm'));
		if(!validation){
			return false;
		}
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.group.saveOrUpdateHandleAppeal" context="admin"/>',
            data: params,
            dataType: 'json',
            success: function (data) {
            	if (data.resultStatus == 0) {
            		$.ZMessage.success("提示", "保存成功", function () {
	            		if(submitted){
	        				setTimeout(function(){
	        					ZDS_MESSAGE_CLIENT.refreshOpenner();
	        					ZDS_MESSAGE_CLIENT.closeSelf();
	        		        },200);
	                   	 }
            		});
            	}else{
                   	$.ZMessage.error("错误", data.msg, function () {})
            	};
           	},
            error: function () {
              	$.ZMessage.error("错误", "评估价申诉系统异常，请联系管理员", function () {
                });
            }
            
            
         });
	}
	
 	$.ZUI.init(); 
});
</script>

</html>