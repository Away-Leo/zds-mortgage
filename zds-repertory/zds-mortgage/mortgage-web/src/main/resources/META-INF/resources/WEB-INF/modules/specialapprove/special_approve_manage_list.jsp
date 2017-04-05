<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value=""> 
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" name="ca|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" name="ca|mechanismName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="t|customerName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
	                <dd class="detail">
	                	<input class="zui-combobox zui-validatebox" type="hidden" id="productTypeId"
	                              data-width="94"
	                              name="ca|productTypeId|LK|S"
	                              data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
	                              data-callback="productParentIdChange"
	                              data-height="300"
	                              data-valuefield="id" data-textfield="text">
	                </dd>
	                <dd class="detail">
	                    <input class="zui-combobox zui-validatebox" type="hidden" id="productSubtypeId"
	                              name="ca|productSubtypeId|LK|S" data-width="94"
	                              data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
	                              data-height="300"
	                              data-valuefield="id" data-textfield="text">
	                </dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
						<input class="zui-checkbox" data-multiple="false" value="0" id="specialStatus"
						data-data="[{'value':'0','text':'全部'},{'value':'1','text':'费用特批'},{'value':'2','text':'风险特批'}]"
						type="hidden" validate-type="Require" data-valuefield="value" data-textfield="text"
						data-callback="changeStage"/>
						
						<input type="hidden" id='countRiskRules' value="0" />
						<input type="hidden" id='countFeeRules' value="0" />
					</dd>
				</dl>
				
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn_search" value="查询" />
					<input type="button" class="btn-search-gray" id="btn_reset" value="重置" />
				</dl> 
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="caseApplyTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.specialApprove.querySpecialApproveList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" }'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">案件号</th>
            			<th data-options="field:DEVELOPMENTDEPARTMENTNAME">机构</th>
            			<th data-options="field:CUSTOMERNAME" formatter="handleEmpty">主借人</th>
            			<th data-options="field:PRODUCTTYPENAME">产品分类</th>
            			<th data-options="field:PRODUCTSUBTYPENAME">子产品</th>
            			<th data-options="field:APPLYTERM">贷款期限（月）</th>
            			<th data-options="field:APPLYAMOUNT" formatter="formatCurrency">申请金额（元）</th>
            			<th data-options="field:CAPITALNAME" formatter="handleEmpty">资金来源</th>
            			<th data-options="field:SYSTEMRULES" formatter="systemOperate">系统触发待特批</th>
			        	<th data-options="field:b" formatter="artificialOperate">人工发起特批</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'],
		function($, CALLBACK,ZTOOL) {
	
	CALLBACK.handleEmpty=function(rowData,value){
		if(value == null){
			return "";
		}else
			return value;
	}
	
	CALLBACK.changeStage=function(value){
		if(0 == value){
			$("#countFeeRules").attr("name","");
			$("#countRiskRules").attr("name","");
			return;
		}
		if(1 == value){
			$("#countFeeRules").attr("name","efr|countFeeRules|R|I");
			$("#countRiskRules").attr("name","");
			return;
		}
		if(2 == value){
			$("#countRiskRules").attr("name","err|countRiskRules|R|I");
			$("#countFeeRules").attr("name","");
			return;
		}
	}
	
	//系统触发特批
	CALLBACK.systemOperate = function(rowData,value){
		//value值结构（风险特批总数&费用特批总数）
		var sysRules = [];
		if(value.length > 1){
			sysRules = value.split("&");
		}
		var data = '';
		if(sysRules.length > 0 && sysRules[0] > 0){
			data += '<a href="javaScript:void(0)" onclick="speciallySysRisk"><button class="btn-blue">风险特批</button></a>&nbsp;&nbsp;';
		}
		if(sysRules.length > 1 && sysRules[1] > 0){
			data += '<a href="javaScript:void(0)" onclick="speciallySysFee"><button class="btn-blue">费用特批</button></a>';
		}
    	return data;
	};
	
	//人工发起特批
	CALLBACK.artificialOperate = function(rowData,value){
		var data='<a href="javaScript:void(0)" onclick="speciallyReduction"><button class="btn-blue">减免特批</button></a>&nbsp;&nbsp;'+
    	'<a href="javaScript:void(0)" onclick="speciallyRisk"><button class="btn-blue">风险特批</button></a>' + 
    	'&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="speciallyFree"><button class="btn-blue">自由还款特批</button></a>';
    	return data;
	};
	
	//系统触发风险特批
	CALLBACK.speciallySysRisk = function(rowData,value){
		var status = validateSysSpecProcessStatus(value.ID,1);
		if(!status){
			return;
		}
		var SAUrl = '<z:ukey key="com.zdsoft.finance.specialApprove.pageSpecialApproveRiskSysApply" context="admin"/>&jsoncallback=?';
		ZDS_MESSAGE_CLIENT.openMenuLink('speciallyRiskSys','风险特批',SAUrl + "&openMethod=tabs&caseApplyId="+value.ID);
	};
	//系统触发费用特批
	CALLBACK.speciallySysFee = function(rowData,value){
		var status = validateSysSpecProcessStatus(value.ID,3);
		if(!status){
			return;
		}
		var SAUrl = '<z:ukey key="com.zdsoft.finance.specialApprove.initSpecialApproveFeeApply" context="admin"/>&jsoncallback=?';
		ZDS_MESSAGE_CLIENT.openMenuLink('speciallyfeeSys','费用特批',SAUrl + "&openMethod=tabs&caseApplyId="+value.ID);
	};
	//风险特批
	CALLBACK.speciallyRisk = function(rowData,value){
		var SAUrl = '<z:ukey key="com.zdsoft.finance.specialApprove.pageSpecialApproveRiskApply" context="admin"/>&jsoncallback=?';
        ZDS_MESSAGE_CLIENT.openMenuLink('speciallyRisk','风险特批',SAUrl + "&openMethod=tabs&caseApplyId="+value.ID);
	};
	//自由还款特批
	CALLBACK.speciallyFree = function(rowData,value){
		var SAUrl = '<z:ukey key="com.zdsoft.finance.specialApprove.pageSpecialApproveFreeApply" context="admin"/>&jsoncallback=?';
        ZDS_MESSAGE_CLIENT.openMenuLink('speciallyFree','自由还款特批',SAUrl + "&openMethod=tabs&caseApplyId="+value.ID);
	};
	//减免特批
	CALLBACK.speciallyReduction = function(rowData,value){
		var SAUrl = '<z:ukey key="com.zdsoft.finance.specialApprove.pageSpecialApproveRemissionApply" context="admin"/>&jsoncallback=?';
        ZDS_MESSAGE_CLIENT.openMenuLink('speciallyReduction','减免特批',SAUrl + "&openMethod=tabs&caseApplyId="+value.ID);
	};
	
	CALLBACK.productParentIdChange = function(v,t){
		var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
		$("#productSubtypeId").ZCombobox({
   		 	valueField: "id",
            textField: "value",
            url: productUrl+"&categoryId="+v
   		});
	}
	//金额分隔符
    CALLBACK.formatCurrency=function(row, value) {
        return ZTOOL.formatCurrency(value+""); 
    }
	//初始化页面
	$.ZUI.init();
	
	//查询
	$("#btn_search").click(function(){
		var formArray=$("#search_from").serializeArray();
		$("#caseApplyTable").ZTable("reload",formArray);
	});
	
	//重置
	$("#btn_reset").click(function(){
		$("#search_from")[0].reset();
		$("#productTypeId").ZCombobox("setValue","");
		$("#productSubTypeId").ZCombobox("setValue","");
		//alert($("#productSubTypeId").ZCombobox("getValue"))
		$("#specialStatus").ZCheckbox("setValue","0");
		$("#countFeeRules").attr("name","");
		$("#countRiskRules").attr("name","");
		
	});
	
	//刷新
    function doSearch() {
		$('#caseApplyTable').ZTable("reload",{});
	};
    //页面回调
    ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
    };
	
	function validateSysSpecProcessStatus(caseApplyId,specialApproveType){
		var res = false;
		if(caseApplyId && specialApproveType){
			$.ajax({
				url: '<z:ukey key="com.zdsoft.finance.specialApprove.validateSysSpecProcessStatus" context="admin"/>',
				method: "post",
				dataType: "json",
				async: false,
				data: {"caseApplyId":caseApplyId,"specialApproveType":specialApproveType},
				success:function(data){
					var status = data.optional.status;
					if(!status){
						$.ZMessage.error("错误","该记录已发起流程，不能重复发起",function(){});
						res = false;
					}else res = true;
				},
				error:function(e){
					$.ZMessage.error("错误","系统错误",function(){});
				}
			});
		}else {
			$.ZMessage.error("错误","案件ID或特批类型为空",function(){});
		}
		return res;
	}
});
</script>
</html>