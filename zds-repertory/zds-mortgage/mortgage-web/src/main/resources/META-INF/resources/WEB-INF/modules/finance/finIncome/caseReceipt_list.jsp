<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
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
				 <dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="caseApplyCode" name="c|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="cus|customerName|LK|S"  >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">案件状态：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" name="c|stage|E|S" id='caseApplyStatus'
													 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" data-width="94" data-callback="renderCombobox" name="c|productTypeId|E|S" id="productTypeId"
													data-url="<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?"
													data-valuefield="id" data-textfield="name">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" id="productSubtypeId" name="c|productSubtypeId|E|S" data-width="94">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
	                <dt class="title">申请日期：</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Re" name="c|applyDate|RE|S">
	                    </label>
	                </dd>
<!-- 	                <span class="word">_</span> -->
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Le" name="c|applyDate|LE|S">
	                    </label>
	                </dd>
	            </dl>
			</form>
			
			<div class="form-btn">
		            <button class="btn-blue" type="button" id="search_btn">查询</button>
		            <button class="btn-gray" id="reset_btn">重置</button>
       		    </div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="caseApplyTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.marketing.getCaseApplyList" context="admin"/>&stage|IN|S=YWDM009224,YWDM009238,YWDM009241","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">案件号</th>
            			<th data-options="field:CUSTOMERNAME">主借人</th>
            			<th data-options="field:PRODUCTTYPENAME">产品分类</th>
            			<th data-options="field:PRODUCTSUBTYPENAME">子产品</th>
            			<th data-options="field:APPLYDATE"  formatter="formatDate">申请日期</th>
            			<th data-options="field:APPLYAMOUNT"  formatter="formatAmount" >贷款金额(元)</th>
            			<th data-options="field:STAGE"  >案件状态</th>
            			<th data-options="field:ID" formatter="operation">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="blanckListDialog" ></div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.dialog'], function($, CALLBACK,ZTools) {
	
	//产品分类下拉框
	CALLBACK.renderCombobox=function(id,name){
		$("#productSubtypeId").ZCombobox({
            valueField: "id",
            textField: "productName",
            url:'<z:ukey key="com.zdsoft.finance.product.findProductListByCatId" context="admin"/>&jsoncallback=?&categoryId='+id
        });
	};
	
	CALLBACK.formatAmount=function(rowData,index){
		return ZTools.formatCurrency(rowData.APPLYAMOUNT+""); 
	};
	
	//查询
	$('#search_btn').on('click',function(){
       	var formArray=decodeURIComponent($("#search_from").serialize(), true);
       	$('#caseApplyTable').ZTable("reload", formArray);
	});
	
	//重置
	$('#reset_btn').on('click',function(){
		$('input[class="zui-input"]').val("");
		$('.zui-date').val("");
		$('input[type="hidden"]').val("");
		$('.zui-combobox').val("");
		$('.zui-combobox').ZCombobox('setValue','');
	});
	
	//格式化列表数据
	CALLBACK.formatDate=function(rowData,index){
		return ZTools.strToDate(rowData.APPLYDATE);
	};
	
	CALLBACK.operation=function(rowData,index){
		return '<a href="javaScript:void(0)" onclick="receipt"><button class="btn-blue">费用结算</button></a>';
	};
	
	//收款
	CALLBACK.receipt=function(index,rowData){
		var	url = '<z:ukey key="com.zdsoft.finance.finance.case.costSettlemenet" context="admin"/>&caseApplyId='+rowData.ID;
		$('#blanckListDialog').load(url,function(){});
	};
	
	//初始化页面
	$.ZUI.init();
});
</script>
</body>
</html>