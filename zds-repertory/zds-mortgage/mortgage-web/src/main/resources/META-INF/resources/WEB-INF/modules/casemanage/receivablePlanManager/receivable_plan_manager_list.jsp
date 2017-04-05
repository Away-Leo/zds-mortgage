<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_js.jsp" %>
<title>还款计划管理列表</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="searchReceivablePlanForm"  class="zui-form mt15">
				<dl class="form-item">
	                <dt class="title">案件号:</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
	                    </label>
	                </dd>
	            </dl>
				<dl class="form-item  form-auto">
                	<dt class="title">产品分类:</dt>
	                <dd class="detail">  
						<input class="zui-combobox zui-validatebox" type="hidden" name="c|productTypeId|E|S" id ="productTypeId"
							data-width="150"
							data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
							data-callback="productTypeChange" data-id="isAgriculture" 
							data-defaultvalue=""
							data-valuefield="id" data-textfield="text">
					</dd>
					<dd class="detail">
						<input class=" zui-combobox zui-validatebox" type="hidden" id="productSubtypeId" name="c|productSubtypeId|E|S"
							data-width="150">
					</dd>
	            </dl>
	             <dl class="form-item">
	                <dt class="title">主借人:</dt>
	                <dd class="detail">
	                    <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
	                </dd>
	            </dl>
				<dl class="form-item">
					<dt class="title">拓展经理:</dt>
					<dd class="detail">
						<input class="zui-input" id="developmentManagerName" type="text" name="c|developmentManagerName|LK|S">
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">风控状态:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" id="riskStatusName" name="riskStatusName"
								data-data="[{'id':'0','text':'未垫资'},{'id':'1','text':'已垫资'},{'id':'2','text':'已回款'}]"
								data-valuefield="id" data-textfield="text" data-width="150">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="searchReceivablePlanList" value="查询" />
					<input type="button" class="btn-gray" id="resetReceivablePlanList" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb-ReceivablePlanList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.receivevableplanManagerList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" ,"toolbar":"#b"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE,width:10%">案件号</th>
            			<th data-options="field:MECHANISMNAME,width:10%">机构</th>
            			<th data-options="field:CUSTOMERNAME,width:5%">主借人</th>
            			<th data-options="field:PRODUCTTYPENAME,width:10%">产品分类</th>
            			<th data-options="field:PRODUCTSUBTYPENAME,width:10%">子产品</th>
            			<th data-options="field:PLANINTERESTAMOUNT,width:5%" formatter="formatterAmount">应收利息（元）</th>
            			<th data-options="field:INSTITUTIONALAUDIT,width:5%" formatter="institutionalAudit">机构审核</th>
            			<th data-options="field:GROUPCONFIRMATION,width:5%" formatter="groupConfirmation">集团确认</th>
            			<th data-options="field:riskStatusName,width:5%" >风控状态</th>
            			<th data-options="field:LOANAPPLYANOUNT,width:5%" formatter="formatterAmount">贷款金额（元）</th>
            			<th data-options="field:EXPECTEDAMOUNT,width:5%" formatter="formatterAmount">应收服务费（元）</th>
            			<th data-options="field:DEVELOPMENTMANAGERNAME,width:10%">拓展经理</th>
            			<th data-options="field:ID,width:15%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
		//产品子类查询
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
		CALLBACK.productTypeChange = function(v,t){
			$("#productSubtypeId").ZCombobox({
       		 	valueField: "id",
                textField: "value",
                url: productUrl+"&categoryId="+v
       		});
		}
		//机构审核
		CALLBACK.institutionalAudit = function(rowData,value){
			if(rowData.INSTITUTIONALAUDIT=="1"){
				return "是";
			}
	    	return "";
		};
		//集团确认
		CALLBACK.groupConfirmation = function(rowData,value){
			if(rowData.GROUPCONFIRMATION=="1"){
				return "是";
			}
	    	return "";
		};
		 // 金额转换
        CALLBACK.formatterAmount = function(row,value){
			 if (value != null && value != "") {
	        	return ZTOOL.formatCurrency(value + "");
			 } 
			 return "";
		};
		//操作格式化
		CALLBACK.formatId = function(rowData,value){
			var data = '<a href="javaScript:void(0)" onclick="beforeLoan"><button class="btn-blue">放款前</button></a>';
			if(rowData.isCanSend){
			    data += '&nbsp;&nbsp<a href="javaScript:void(0)" onclick="afterLoan"><button class="btn-blue">放款后</button></a>';
			}
	    	return data;
		};
		//操作放款前
		CALLBACK.beforeLoan = function(index,row){
			var initBeforeLoan = '<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.initBeforeLoan" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
	        ZDS_MESSAGE_CLIENT.openMenuLink('放款前','放款前',initBeforeLoan + "&openMethod=tabs");
		};
		//操作放款后
		CALLBACK.afterLoan = function(index,row){
			var initAfterLoan = '<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.editRepaymentPlan" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
	        ZDS_MESSAGE_CLIENT.openMenuLink('放款后','放款后',initAfterLoan + "&openMethod=tabs");
		};
		
		//查询回调
		$("#searchReceivablePlanList").click(function(){
			var flag=$.ZUI.validateForm($('#searchReceivablePlanForm'));
			if(flag){
            	var formArray=$("#searchReceivablePlanForm").serializeArray();
            	$('#tb-ReceivablePlanList').ZTable("reload", formArray);
        	}
		});
		//重置回调
        $('#resetReceivablePlanList').on('click',function(){
        	$("#searchReceivablePlanForm")[0].reset();
			$("#productSubtypeId").val("");  
			$("#productTypeId").val("");     
        	$("#productTypeId").ZCombobox("setValue","");
			$("#productSubtypeId").ZCombobox("setValue","");
			$("#riskStatusName").ZCombobox("setValue","");
        	var flag=$.ZUI.validateForm($('#searchReceivablePlanForm'));
        	if(flag){
            	var formArray=$("#searchReceivablePlanForm").serializeArray();
            	$('#tb-ReceivablePlanList').ZTable("reload", formArray);
        	}
        });
		
        //刷新
        function doSearch() {
			$('#tb-ReceivablePlanList').ZTable("reload",{});
		};
        //页面回调
        ZDS_MESSAGE_CLIENT.refreshThis=function(){
    		doSearch();
        };
      	//初始化页面
		$.ZUI.init();
	});
</script>
</html>