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
<title>信托计划调配</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title"></div>
    <div class="p5">
        <form id="searchInstitutionForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">机构:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="orgName" name="orgName|LK|S" value="">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">资方:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="cooperatorName" name="capitalists.cooperatorName|LK|S" value="">
                    </label>
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" type="button" id="search">查询</button>
            <button class="btn-gray" type="button" id="reset">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="p10">
        <div id="tb-plan" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.capital.getInstitutionFunds" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:orgName">机构</th>
		            <th data-options="field:capitalNames">资方</th>
		            <th data-options="field:remark">备注</th>
		            <th data-options="field:updateTimeStr">最近修改日期</th>
		            <th data-options="field:id" formatter="changeValue">操作</th>
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
    	
    	// 回调函数
		CALLBACK.changeValue = function(rowData,index){
			return "<a title='编辑' class='icon-btn22 handler-icon c-green'  onclick='evaluationEdit'></a>";
		};
		
		// 回调函数
		CALLBACK.evaluationEdit = function(index,rowData){
			 var changeInstitutionUrl = '<z:ukey key="com.zdsoft.finance.capital.initCasePlanDistribution" context="admin"/>&jsoncallback=?&id='+rowData.id;
            ZDS_MESSAGE_CLIENT.openMenuLink('changeInstitutionId','修改信托计划调配',changeInstitutionUrl + "&openMethod=tabs");
		};
		
		// 初始化
		$.ZUI.init();
		
		// 查询按钮点击事件
		$('#search').click(function(){
    		doSearch();
    	});
		
		// 重置按钮点击事件
    	$('#reset').click(function(){
    		$('#cooperatorName').val('');
    		$('#orgName').val('');
    	});
    	
		// 查询操作
    	function doSearch() {
			var formArray=$("#searchInstitutionForm").serialize();
			formArray = decodeURIComponent(formArray, true);
			
			$('#tb-plan').ZTable("reload",formArray);
		};
		
		// 刷新本页面方法
		ZDS_MESSAGE_CLIENT.refreshThis=function(){
			doSearch();
        };
    });
</script>
</body>
</html>