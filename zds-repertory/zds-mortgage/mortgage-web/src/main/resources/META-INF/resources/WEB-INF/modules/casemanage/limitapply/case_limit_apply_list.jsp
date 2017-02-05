<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件额度申请</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="caseApplyCode"  name="c|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="customerName" name="cus|customerName|LK|S" >
						</label>
					</dd>
				</dl>
<%-- 				<dl class="form-item">
		               <dt class="title">产品分类:</dt>
		               <dd class="detail">
		                   <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="productTypeId" type="text" name="productTypeId|E|S" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
				                           data-valuefield="fullcode" data-textfield="name" >
		               </dd>
		               <dt class="title"></dt>
		               <dd class="detail">
		                   <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="productSubtype" type="text" name="productSubtype|E|S" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
				                           data-valuefield="fullcode" data-textfield="name" >
		               </dd>
		           </dl> --%>
		           <dl class="form-item">
		            	<dt class="title">产品分类：</dt>
			                <dd class="detail">
			                	<input class="zui-combobox zui-validatebox" type="hidden" id="productTypeId"
			                              data-width="94"
			                              name="productTypeName"
			                              data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
			                              data-callback="productParentIdChange"
			                              data-height="300"
			                              data-defaultvalue=""
			                              data-valuefield="id" data-textfield="text">
			                </dd>
			                <dd class="detail">
			                    <input class="zui-combobox zui-validatebox" type="hidden" id="productSubtype"
			                              name="productSubtypeName" data-width="94"
			                              data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
			                              data-callback=""
			                              data-height="300"
			                              data-defaultvalue=""
			                              data-valuefield="id" data-textfield="text">
			                </dd>
           	    </dl>
				<dl class="form-item">
					<dt class="title">额度状态：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="actualLimitStatus" type="hidden" name="c|actualLimitStatus|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0059"
			                           data-valuefield="fullcode" data-textfield="name" >
                	</dd>
						<!-- <label> 
							<input id="actualLimitStatus" class="zui-combobox" type="hidden" name="actualLimitStatus"
													data-data="[{'id':'0','text':'未分配额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
													data-valuefield="id" data-textfield="text">
						</label> -->
				</dl>
			</form>
			<div class="form-btn">
				<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
				<input type="button" class="btn-search-blue" id="btn-reset" value="重置" />
			</div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb_caseLimitApply" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.limitApply.caseLimitApplyList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" ,"toolbar":"#btn-function"}'>
				<table>
        			<tr>
            			<th data-options="field:caseApplyCode">案件号</th>
            			<th data-options="field:customerName">主借人</th>
            			<th data-options="field:productTypeName">产品分类</th>
            			<th data-options="field:productSubtypeName">子产品</th>
            			<th data-options="field:pledgeType">抵押类型</th>
            			<th data-options="field:fundPlanName">资金计划名称</th>
            			<th data-options="field:developmentManagerName">拓展经理</th>
            			<th data-options="field:applyAmount,hidden:true">申请额度</th>
            			<th data-options="field:actualApplyAmount" formatter="isHasView">申请额度</th>
            			<th data-options="field:actualLimitStatus">额度状态</th>
            			<th data-options="field:id" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {

	//若是实际额度申请部位
	CALLBACK.isHasView = function(rowData,value){
		if(rowData.actualApplyAmount==null||rowData.actualApplyAmount==0||rowData.actualApplyAmount.length==0){
			return rowData.applyAmount;
		}
		return rowData.actualApplyAmount;
	};

	//操作
	CALLBACK.operate = function(rowData,value){
		if(rowData.actualLimitStatus=='已分配额度未分配资金'||rowData.actualLimitStatus=='已分配额度已分配资金'){
			var data = '<a href="javaScript:void(0)" onclick="cancleApplyLimit"><button class="btn-blue">取消额度</button></a>&nbsp;&nbsp;' +
	    	'<a href="javaScript:void(0)" onclick="viewApplyLimit"><button class="btn-blue">详情</button></a>';
		}else{
			var data = '<a href="javaScript:void(0)" onclick="saveApplyLimit"><button class="btn-blue">额度申请</button></a>';
		}
		return data;
	};
	//额度申请格式化
	CALLBACK.saveApplyLimit = function(index,row){
		var saveApplyLimitUrl = '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.addCaseLimitApply" context="admin"/>&jsoncallback=?&id='+row.id+'&customerName='+row.customerName+'&pledgeType='+row.pledgeType;
        ZDS_MESSAGE_CLIENT.openMenuLink('额度申请','额度申请',saveApplyLimitUrl + "&openMethod=tabs"); 
	};
	//取消额度格式化
	CALLBACK.cancleApplyLimit = function(index,row){
         var params = {};
		 params.id = row.id;
         $.ajax({
             type: 'post',
             url: '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.cancelCaseLimitApply" context="admin"/>',
             data: params,
             dataType: 'json',
             success: function (data) {
                 if (data.resultStatus == 0) {
                 	$.ZMessage.warning("提示", "额度取消成功", function () {
                 		var arr=[];
                 		arr[0] = index;
                 		arr[1] = row;
                 		$('#caseApplyTable').ZTable("editOneRow",arr);
                   	 });
                 }else{
                 	$.ZMessage.error("错误", data.msg, function () {
                  });
                 }
             },
        	error: function () {
        		$.ZMessage.error("错误", "额度取消信息系统异常，请联系管理员", function () {
         	});
       	 }
      });
		/* var cancleApplyLimitUrl = '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.cancelCaseLimitApply" context="admin"/>&jsoncallback=?&id='+row.id; */
       	
	};
	//查看额度申请详情
	CALLBACK.viewApplyLimit = function(index,row){
		var saveApplyLimitUrl = '<z:ukey key="com.zdsoft.finance.casemanage.limitApply.detailsCaseLimitApply" context="admin"/>&jsoncallback=?&id='+row.id;
        ZDS_MESSAGE_CLIENT.openMenuLink('详情','详情',saveApplyLimitUrl + "&openMethod=tabs");
	};
	
	//查询回调
	$("#btn-search").click(function(){
		var flag=$.ZUI.validateForm($('#search_from'));
		if(flag){
			var formArray=$("#search_from").serializeArray();
			$("#tb_caseLimitApply").ZTable("reload",formArray);
		}
	});
	//重置回调
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
	});
	
	//获取子页面刷新的请求进行刷新
/* 	ZDS_MESSAGE_CLIENT.refreshThis = function (data) {
        var datas = JSON.parse(data.args);
        alert("角色名称：" + datas.roseId + ",角色组名称：" + datas.groupId);
        var param = {};
        $('#role-datagrid').ZTable("reload", param);
    }; */
	
/* 	ZDS_MESSAGE_CLIENT.refreshThis = function(data){
        //var datas = JSON.parse(data.args);
        //alert("角色名称：" + datas.roseId + ",角色组名称：" + datas.groupId);
		doSearch();
    };
 */
 
	//初始化页面
	$.ZUI.init();
});
</script>
</html>