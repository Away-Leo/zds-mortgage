<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>付款管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">查询信息</div>
        <div id="searchForm" class="p5">
            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
           
                 <dl class="form-item">
					<dt class="title">请款单号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="rq|billCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">是否付款：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" id="" type="hidden"
	                               data-data="[{'id':'T','text':'是'},{'id':'F','text':'否'}]"
	                               data-valuefield="id" data-textfield="text" name="rqd|isPayment|E|S" >
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
		<div class="page-title">请款信息列表</div>
		<div class="p10">
			<div id="zd-table"></div>
		</div>
	</div>

	<div class="hide" id="showPaymentEditPage"></div>

<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
	    		
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsGroupPayObjectNameList" context="admin"/>&rq|isAllocate|E|S=T&rqd|isPayment|E|S=F&jsoncallback=?';
	    	
	    	CALLBACK.reqFundsRepayment = function (index,value) {
	    		$('#showPaymentEditPage').load('<z:ukey key="com.zdsoft.finance.requestFunds.paymentEditPage" context="admin"/>&reqFundsId='+value.ID+"&billCode="+value.BILLCODE+"&payObjectName="+value.PAYOBJECTNAME+"&payObjectId="+value.PAYOBJECTID,function() {});
	         };
	         
	         $.ZUI.init();
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : true,
                  isRowNum : true,
                  pagination : true,
                  tableCls : 'table-index',//table的样式
                  toolbar : [ ],
                  columns:[[
					{field : 'BILLCODE',title : '请款单号',align : 'center'},
					{field : 'PAYOBJECTNAME',title : '往来单位',align : 'center'},
					{field : 'REQFUNDSAMOUNT',title : '请款金额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
					{field : 'SUMMARY',title : '摘要',align : 'center'},
					{field : 'ISPAYMENT',title : '是否付款',align : 'center',
						formatter : function(rowData,value) {
							return value=="T"?"是":"否";	
					}},
					{field : 'PAYMENTDATE',title : '实际付款日期',align : 'center',hidden:true,
						formatter : function(rowData,value) {
							return value==null?"":ZTOOL.strToDate(""+value);	
					}},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							return '<a title="付款"  onclick="reqFundsRepayment"><button class="btn-blue">付款</button></a>';
					}}
				] ]
        	});
	         
	         ZDS_MESSAGE_CLIENT.refreshThis=function(){
		    		$('#zd-table').ZTable("reload");
		    	};//回调刷新当前页面
		    	
	    	$('#search').on('click',function(){
	    		var formArray=$("#form_search").serialize();
	    			formArray=decodeURIComponent(formArray, true);
	    		$('#zd-table').ZTable("reload",formArray);
	    	});
	    	
	    	$('#reset').on('click',function(){
	    		 $('input').val("");
	    		 $('.zui-combobox').ZCombobox("select","");
	    	});
	    });
	</script>
</body>
</html>