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
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="caseApplyCode" name="caseApplyCode" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="customerName" name="customerName" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">所属机构:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="createOrgCd" name="createOrgCd" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">所属信托计划:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="creditEntrustName" name="creditEntrustName" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">申请状态:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="caseApplyStatus" name="caseApplyStatus" value="">
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
		            <th data-options="field:createOrgCd">机构</th>
		            <th data-options="field:caseApplyCode">案件号</th>
		            <th data-options="field:customerName">客户名</th>
		            <th data-options="field:caseApplyStatus">案件状态</th>
		            <th data-options="field:applyDate">分配日期</th>
		            <th data-options="field:creditEntrustName">分配信托计划</th>
		            <th data-options="field:currentApplyLimit">申请额度</th>
		            <th data-options="field:matching">备付资金匹配</th>
		            <th data-options="field:cancelDate">取消日期</th>
		            <th data-options="field:applyDeadline">期限(月)</th>
		            <th data-options="field:applyAmount">审批(元)</th>
		            <th data-options="field:loanDate">放款日期</th>
		            <th data-options="field:id" formatter="quotaManagement">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
	
	</div>
</div>


<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
    	CALLBACK.quotaManagement = function(index,rowData){
    		var html =  "<a title='额度管理' class='btn-blue ' buttonCls='btn-blue' onclick='initQuotaManagement'>额度管理</a>";
    		return html;
    	};
    	
    	CALLBACK.initQuotaManagement = function(index,rowData){
    		 var changeInstitutionUrl = '<z:ukey key="com.zdsoft.finance.capital.initQuotaAllocation" context="admin"/>&jsoncallback=?&id='+rowData.id + '&creditEntrustId='+rowData.creditEntrustId;
             ZDS_MESSAGE_CLIENT.openMenuLink('changeInstitutionId','修改信托计划调配',changeInstitutionUrl + "&openMethod=tabs");
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
    		$('#caseApplyStatus').val('');
    	});
    	
    	
    	function doSearch(){
    		var formArray=$("#searchForm").serialize();
			formArray = decodeURIComponent(formArray, true);
			$('#tb-plan').ZTable("reload",formArray);
    	}
    });
</script>
</body>
</html>