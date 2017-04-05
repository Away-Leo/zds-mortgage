<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>费用收费管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" >
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="caseApplyCode" name="ma|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="bcust|customerName|LK|S"  >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">案件状态：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" name="ma|stage|E|S" id='caseApplyStatus'
													 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" data-width="94" data-callback="renderCombobox" name="ma|productTypeId|E|S" id="productTypeId"
													data-url="<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?"
													data-valuefield="id" data-textfield="name">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" id="productSubtypeId" name="ma|productSubtypeId|E|S" data-width="94">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
	                <dt class="title">申请日期：</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Re" name="ma|applyDate|RE|S">
	                    </label>
	                </dd>
<!-- 	                <span class="word">_</span> -->
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Le" name="ma|applyDate|LE|S">
	                    </label>
	                </dd>
	            </dl>
			</form>
			 <div class="form-btn">
		            <button class="btn-blue" type="button" id="btn_search">查询</button>
		            <button class="btn-gray" id="btn_reset">重置</button>
       		    </div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="caseApplyTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.casetracking.getCaseTracking" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:caseApplyCode">案件号</th>
            			<th data-options="field:customerName">主借人</th>
            			<th data-options="field:productTypeName">产品分类</th>
            			<th data-options="field:productSubtypeName">子产品</th>
            			<th data-options="field:applyDate"  formatter="formatDate">申请日期</th>
            			<th data-options="field:applyAmount"  formatter="formatAmount" >贷款金额(元)</th>
            			<th data-options="field:stageName"  >案件状态</th>
            			<th data-options="field:id" formatter="formatId">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
	
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK,ZTOOLS) {
	
	 
	 
	//产品分类下拉框
	CALLBACK.renderCombobox=function(id,name){
		$("#productSubtypeId").ZCombobox({
            valueField: "id",
            textField: "productName",
            url:'<z:ukey key="com.zdsoft.finance.product.findProductListByCatId" context="admin"/>&jsoncallback=?&categoryId='+id,
            onSelect:function(value,text,index){
                $('#productSubtypeId').val(value);
            }
        });
	};
	
	CALLBACK.formatAmount=function(rowData,index){
		return ZTOOLS.formatCurrency(rowData.applyAmount+""); 
	};

	
	//查询
	$('#btn_search').on('click',function(){
       	var formArray=decodeURIComponent($("#search_from").serialize(), true);
       	$('#caseApplyTable').ZTable("reload", formArray);
	});
	
	//重置
	$('#btn_reset').on('click',function(){
		$('input[class="zui-input"]').val("");
		$('.zui-date').val("");
		$('input[type="hidden"]').val("");
		$('.zui-combobox').val("");
		$('.zui-combobox').ZCombobox('setValue','');
	});
	
	//格式化列表数据
	CALLBACK.formatDate=function(rowData,index){
		return ZTOOLS.strToDate(""+rowData.applyDate);
	}
	
	CALLBACK.formatId=function(rowData,index){
		return '<a href="javaScript:void(0)" onclick="receipt"><button class="btn-blue">收款</button></a>';
	}
	
	//收款
	CALLBACK.receipt=function(index,rowData){
		ZDS_MESSAGE_CLIENT.openMenuLink('finIncome_receipt', '收款', '<z:ukey key="com.zdsoft.finance.finance.finIncome.receipt" context="admin"/>&caseApplyId='+rowData.id);
	}
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>