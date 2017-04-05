<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件计划分配</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">资金计划分配</div>
    <div class="p5">
        <form id="searchForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">案件号：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="caseApplyCode" name="caseApplyCode" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">主借人：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="customerName" name="customerName" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">所属机构：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="createOrgCd" name="createOrgCd" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">所属信托计划：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="creditEntrustName" name="creditEntrustName" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">申请状态：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-combobox " id="caseApplyStatus" name="caseApplyStatus" type="hidden"
                             data-data="[{'id':'01','text':'正常'},{'id':'02','text':'退单'}]"
                              data-valuefield="id" data-textfield="text">
                    </label>
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="search">查询</button>
            <button class="btn-gray" id="reset">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="p10">
        <div id="tb-plan" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.capital.getDistributions" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:mechanismName">机构</th>
		            <th data-options="field:caseApplyCode">案件号</th>
		            <th data-options="field:customerName" formatter="changeCustomerName">客户名</th>
		            <th data-options="field:caseApplyStatus">案件状态</th>
		            <th data-options="field:allotDate" formatter="changeAllotNull">分配日期</th>
		            <th data-options="field:creditEntrustName">分配信托计划</th>
		            <th data-options="field:currentApplyLimit" formatter="currency">申请额度</th>
		            <th data-options="field:matching">备付资金匹配</th>
		            <th data-options="field:cancelDate" formatter="changeNullValue">取消日期</th>
		            <th data-options="field:applyTerm">期限(月)</th>
		            <th data-options="field:applyAmount" formatter="currency2">审批(元)</th>
		            <th data-options="field:createTime" formatter="changeValue">放款日期</th>
		            <th data-options="field:effectiveStatus">资金状态</th>
		            <th data-options="field:id" formatter="quotaManagement">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
	</div>
</div>


<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK,TOOL, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
    	
    	// 格式化操作按钮
    	CALLBACK.quotaManagement = function(index,rowData){
    		if(index.effectiveStatus == '额度未申请'){
    			return "<a title='申请额度' class='btn-blue ' buttonCls='btn-blue' onclick='quotaApplication'>申请额度</a>";
    		}else if(index.effectiveStatus == '已申请未配资金'){
    			return "<a title='取消额度' class='btn-blue mt5 mr5' buttonCls='btn-blue' onclick='cancellationQuota' >取消额度</a><a title='分配备付金' class='btn-blue mt5 mr5' buttonCls='btn-blue'  onclick='allocationAllowance'>分配备付金</a><a title='转资金计划' class='btn-blue mt5 mr5' buttonCls='btn-blue' onclick='transferPlan'>转资金计划</a>";
    		}else if(index.effectiveStatus == '已申请已配资金'){
    			return "<a title='取消备付金分配' class='btn-blue mt5 mr5' buttonCls='btn-blue'  onclick='cancellationAllowance'>取消备付金分配</a><a title='转资金计划' class='btn-blue mt5 mr5' buttonCls='btn-blue'  onclick='transferPlan'>转资金计划</a>";
    		}
    	};
    	
   		CALLBACK.currency = function(index,rowData){
   			return TOOL.formatCurrency(index.currentApplyLimit);
   		};
   		
   		CALLBACK.currency2 = function(index,rowData){
   			return TOOL.formatCurrency(index.applyAmount);
   		};
   		
   		CALLBACK.changeNullValue = function(index,rowData){
   			if(0 == index.cancelDate || !index.cancelDate){
   				return '';
   			}else{
   				return index.cancelDate.toString().substring(0,4)+'-'+index.cancelDate.toString().substring(4,6)+'-'+index.cancelDate.toString().substring(6,8);
   			}
   		};
   		CALLBACK.changeAllotNull = function(index,rowData){
   			if(!index.allotDate){
   				return '';
   			}else{
   				return index.allotDate.toString().substring(0,4)+'-'+index.allotDate.toString().substring(4,6)+'-'+index.allotDate.toString().substring(6,8);
   			}
   		};
    	
    	// 申请额度事件
    	CALLBACK.quotaApplication = function(index,rowData){
    		 var quotaApplicationUrl = '<z:ukey key="com.zdsoft.finance.capital.quotaApplication" context="admin"/>&jsoncallback=?&id='+rowData.id;
             ZDS_MESSAGE_CLIENT.openMenuLink('quotaApplicationId','申请额度',quotaApplicationUrl + "&openMethod=tabs");
    	};
    	// 取消额度事件
    	CALLBACK.cancellationQuota = function(index,rowData){
    		 var cancellationQuotaUrl = '<z:ukey key="com.zdsoft.finance.capital.cancellationQuota" context="admin"/>&jsoncallback=?&id='+rowData.id;
             ZDS_MESSAGE_CLIENT.openMenuLink('cancellationQuotaId','取消额度',cancellationQuotaUrl + "&openMethod=tabs");
    	};
    	// 申请备付金事件
    	CALLBACK.allocationAllowance = function(index,rowData){
    		 var allocationAllowanceUrl = '<z:ukey key="com.zdsoft.finance.capital.allocationAllowance" context="admin"/>&jsoncallback=?&id='+rowData.id;
             ZDS_MESSAGE_CLIENT.openMenuLink('allocationAllowanceId','申请备付金',allocationAllowanceUrl + "&openMethod=tabs");
    	};
    	// 取消备付金事件
    	CALLBACK.cancellationAllowance = function(index,rowData){
    		 var cancellationAllowanceUrl = '<z:ukey key="com.zdsoft.finance.capital.cancellationAllowance" context="admin"/>&jsoncallback=?&id='+rowData.id;
             ZDS_MESSAGE_CLIENT.openMenuLink('cancellationAllowanceId','取消备付金',cancellationAllowanceUrl + "&openMethod=tabs");
    	};
    	// 转资金计划时间
    	CALLBACK.transferPlan = function(index,rowData){
    		 var transferPlanUrl = '<z:ukey key="com.zdsoft.finance.capital.transferPlan" context="admin"/>&jsoncallback=?&id='+rowData.id;
             ZDS_MESSAGE_CLIENT.openMenuLink('transferPlanId','计划转让',transferPlanUrl + "&openMethod=tabs");
    	};
    	
    	
    	// 格式化时间
    	CALLBACK.changeValue = function(index,rowData){
    		if(index.createTime){
    			return index.createTime.toString().substring(0,4)+'-'+index.createTime.toString().substring(4,6)+'-'+index.createTime.toString().substring(6,8);;
    		}else{
    			return '';
    		}
    	};
    	
    	CALLBACK.changeCustomerName = function(index,rowData){
    		if(index.customerName){
    			return index.customerName;
    		}else{
    			return '';
    		}
    	};
    	
    	
    	// 初始化
    	$.ZUI.init();
    	
    	$('#search').click(function(){
    		doSearch();
    	});
    	
    	$('#reset').click(function(){
    		$('#caseApplyCode').val('');
    		$('#customerName').val('');
    		$('#createOrgCd').val('');
    		$('#creditEntrustName').val('');
    		$('#caseApplyStatus').ZCombobox('setValue','');
    	});
    	
    	// 父页面刷新自己
		ZDS_MESSAGE_CLIENT.refreshThis=function(){
			doSearch();
        };
    	
    	
    	function doSearch(){
    		var formArray=$("#searchForm").serialize();
			formArray = decodeURIComponent(formArray, true);
			$('#tb-plan').ZTable("reload",formArray);
    	}
    });
</script>
</body>
</html>