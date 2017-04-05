<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>案件列表</title>
</head>
<body>
<div id="myOverdueCaseForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">逾期案件</div>
		<div class="p10">
			<form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
				<dl class="form-item">
					<dt class="title">案件号:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-32]" id="applayCaseCode" name="applayCaseCode">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">机构:</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" type="text" validate-type="Length[0-100]" id="orgCode" name="orgCode">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">逾期天数:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="overdueDays"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00158"
	                        data-valuefield="fullcode" data-textfield="name" name="overdueDays">
					</dd>
				</dl>
				<dl class="form-item">
                    <dt class="title">未回款(元):</dt>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="paymentAmount" name="paymentAmount" validate-type="" />
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="paymentAmountEnd" name="paymentAmountEnd" validate-type="" />
                        </label>
                    </dd>
                </dl>
       
                <dl class="form-item">
                    <dt class="title">放款日期:</dt>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="startTime" name="startloanDate" validate-type="" onclick="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')}'})" readonly/>
                        </label>
                    </dd>
					<span class="word">至</span>
                    <dd class="detail">
                        <label>
                        	<input class="zui-input width2-1 zui-validatebox" type="text" id="endTime" name="endLoanDate" validate-type="" onclick="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
					<dt class="title">贷后状态:</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="afterLoanStatus"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00126"
	                        data-valuefield="fullcode" data-textfield="name" name="afterLoanStatus">
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-blue" id="search" value="查询" />
					<input type="button" class="btn-blue" id="reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
	<div class="page-title">逾期案件列表</div>
		<div class="p10">
			<div id="tb-overdueCaseList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.workbench.overdueCase.getOverdueCaseList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
				<table>
        			<tr>
            			<th data-options="field:MECHANISMNAME,width:10%">机构</th>
            			<th data-options="field:CUSTOMERNAME,width:10%">主借人</th>
            			<th data-options="field:CASEAPPLYCODE,width:5%">案件号</th>
            			<th data-options="field:PRODUCTTYPENAME,width:10%">产品分类</th>
            			<th data-options="field:PRODUCTSUBTYPENAME,width:10%">子产品产品</th>
            			<th data-options="field:SQTS,width:5%">申请天数</th>
            			<th data-options="field:LOANANOUNT,width:10%">已放款(元)</th>
            			<th data-options="field:PAIDPRINCIPALAMOUNT,width:10%">未回款(元)</th>
            			<th data-options="field:DAYS,width:5%">逾期天数</th>
            			<th data-options="field:ACTUALDATE,width:10%">放款日期</th>
            			<th data-options="field:CASEAPPLYSTATUS,width:10%">贷后状态</th>
            			<th data-options="field:ID,width:5%" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
			<div id="btn-function">
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
		</div>
	</div>
</div>

<div id="findRemindPoolDialog" style="display: none">
</div>
</body>
<script type="text/javascript">
seajs.use([
           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'
           ], 
	 	function ($,ZTOOlS,CALLBACK) {
	
	$('#search').on('click',function(){
		var flag=$.ZUI.validateForm($('#searchForm'));
    	if(flag){
        	var formArray=$("#searchForm").serialize();
        	formArray=decodeURIComponent(formArray, true);
        	$('#tb-overdueCaseList').ZTable("reload", formArray);
    	}
	});
	
	$('#reset').on('click',function(){
		$('#applayCaseCode').val('');
    	$('#orgCode').val('');
    	$('#overdueDays').ZCombobox('setValue','');
    	$('#paymentAmount').val('');
    	$('#paymentAmountEnd').val('');
    	$('#startTime').val('');
    	$('#endTime').val('');
    	$('#afterLoanStatus').ZCombobox('setValue','');
		$('#tb-overdueCaseList').ZTable("reload", {});
    });
	
	CALLBACK.formatId=function(rowData,index){
		var id=rowData.ID;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		
		return '<a href="javaScript:void(0)" onclick="handle"><button class="btn-blue">处理</button></a>';
	}
	
	 //处理格式化
    CALLBACK.handle=function(index,rowData){
    	var id=rowData.ID;
		if(!id){
			$.ZMessage.error("错误", "未获取到主键", function () {
                $(".zd-message").ZWindow("close");
            });
			return ;
		}
		var eidtHandleUrl = '<z:ukey key="com.zdsoft.finance.afterloan.loanAfterHandle" context="admin"/>&jsoncallback=?&caseApplyId='+rowData.ID;
		ZDS_MESSAGE_CLIENT.openMenuLink('loanAfterHandle','处理',eidtHandleUrl + "&openMethod=tabs"); 
    }
	

	
	//导出逾期案件列表
    CALLBACK.exports=function(){
    	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=逾期案件列表导出文档";
        var param=$("table").html();
		$("form").remove("#exportFrom");
        $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
        $("#exportFrom").submit();
    }
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>