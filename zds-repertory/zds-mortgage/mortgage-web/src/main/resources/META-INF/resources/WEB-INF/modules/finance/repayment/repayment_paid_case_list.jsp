<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>案件还款查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style>
    	table.scroll th,table.scroll td{
    		white-space: nowrap;
    	}
    </style>
</head>
<body>
<div class="frm-content" style="height:90%;">
<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">查询信息</div>
        <div id="searchForm" class="p5">
            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
                 <dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="caseApplyCode" name="mca|caseApplyCode|E|S">
						</label>
					</dd>
				</dl>
				 <dl class="form-item">
					<dt class="title">单据号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="receipt|billCode|E|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" data-width="94" data-callback="renderCombobox" name="mca|productTypeId|E|S" id="productTypeId"
													data-url="<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?"
													data-valuefield="id" data-textfield="name">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" id="productSubtypeId" name="mca|productSubtypeId|E|S" data-width="94">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="cc|customerName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="mechanismName" name="mca|mechanismName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item" style="display: none;">
					<dt class="title">信托计划：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="plan"  >
						</label>
					</dd>
				</dl>
				
            </form>
                <div class="form-btn">
		            <button class="btn-blue" type="button" id="search">查询</button>
		            <button class="btn-gray" id="reset">重置</button>
       		    </div>
        </div>
    </div>
    
	<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">收款单明细列表</div>
		<div class="p10">
			<div id="zd-table"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form', 'zd/jquery.zds.table','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter'],
	    	 function ($, CALLBACK,ZTOOL){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.repayment.repaymentApplyList" context="admin"/>';
	    	var url_view_repaymentReceipt_page='<z:ukey key="com.zdsoft.finance.repayment.repaymentReceiptReviewPage" context="admin"/>&scan=false';
	    	
	    	 CALLBACK.scanRepayment = function (index,value) {
		    		 	ZDS_MESSAGE_CLIENT.openMenuLink('repaymentReceipt_view_tab', "详情",url_view_repaymentReceipt_page+"&receiptId="+value.ID);
	         };
	         
	       //产品分类下拉框
		    CALLBACK.renderCombobox=function(id,name){
		    			$("#productSubtypeId").ZCombobox({
		    	            valueField: "id",
		    	            textField: "productName",
		    	            url:'<z:ukey key="com.zdsoft.finance.product.findProductListByCatId" context="admin"/>&jsoncallback=?&categoryId='+id
		    	        });
		    	};
	       
	    	$.ZUI.init();
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : true,
                  isRowNum : true,
                  pagination : true,
                  tableCls : 'table-index',//table的样式
                  columns:[[
                		{field : 'STATUS',title : '状态',align : 'center',
    						formatter : function(rowData,value) {
    							var x={"0":"草稿","1":"审批中","2":"通过","3":"退回","4":"废弃"};
    							return x[value];	
    						}},
					{field : 'ORGNAME',title : '机构',align : 'center'},
					{field : 'BILLCODE',title : '单据号',align : 'center'},
					{field : 'COLLOECTIONCODE',title : '收据号',align : 'center',
                        formatter: function (row, value) {
                         	 return value==null?"":value; 
                         }},
					{field : 'CASEAPPLYCODE',title : '案件号',align : 'center'},
					{field : 'CUSTOMERNAME',title : '主借人',align : 'center',
                        formatter: function (row, value) {
                          	 return value==null?"":value; 
                          }},
					{field : 'COLLECTIONAMOUNT',title : '收款总额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
					{field : 'PAIDPRINCIPALAMOUNT',title : '本金(元)',align : 'center',
                           formatter: function (row, value) {
                          	 return ZTOOL.formatCurrency(value+""); 
                          }},
					{field : 'PAIDSERVICEFEE',title : '服务费(元)',align : 'center',
                              formatter: function (row, value) {
                             	 return ZTOOL.formatCurrency(value+""); 
                             }},
					{field : 'PAIDINTERESTAMOUNT',title : '利息(元)',align : 'center',
                                 formatter: function (row, value) {
                                	 return ZTOOL.formatCurrency(value+""); 
                                }},
					{field : 'PAIDPENALTY',title : '罚息(元)',align : 'center',
                                    formatter: function (row, value) {
                                   	 return ZTOOL.formatCurrency(value+""); 
                                   }},
					{field : 'PAIDDAMAGES',title : '违约金(元)',align : 'center',
                                       formatter: function (row, value) {
                                      	 return ZTOOL.formatCurrency(value+""); 
                                      }},
				    {field : 'PAIDREPAYDATE',title : '收款日期',align : 'center',
                                          formatter: function (row, value) {
                                              return ZTOOL.strToDate(value+""); //时间转换
                                          }},
				    {field : 'RECEIPTS',title : '经手人',align : 'center'},
				    {field : 'PRODUCTSUBTYPENAME',title : '产品',align : 'center'},
				    {field : 'RECEIPTTYPE',title : '还款类型',align : 'center',
                        formatter: function (row, value) {
                        	var xt={"1":"正常还款","2":"预收款"};
                         	 return xt[value+""]; 
                         }},
				    {field : '',title : '逾期天数',align : 'center',hidden:true},
				    {field : '',title : '委贷银行',align : 'center',hidden:true},
					{field : 'CAPITALSOURCE',title : '资金来源',align : 'center',hidden:true},
					{field : '',title : '所属信托计划',align : 'center',hidden:true},
					{field : '',title : '信贷债权状态',align : 'center',hidden:true},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
				            return  '<a title="详情"  onclick="scanRepayment"><button class="btn-blue">详情</button></a>';
					}}
				] ]
        	});
	    	
		    	
	    	$('#search').on('click',function(){
	    		var formArray=$("#form_search").serialize();
	    			formArray=decodeURIComponent(formArray, true);
	    		$('#zd-table').ZTable("reload",formArray);
	    	});
	    	
	    	$('#reset').on('click',function(){
	    		$('input[class="zui-input"]').val("");
	    		$('.zui-combobox').val("");
	    		$('.zui-combobox').ZCombobox('setValue','');
	    	});
	    });
	</script>
</body>
</html>