<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>费用请款单查询</title>
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
							<input class="zui-input" id="" name="billCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">是否拨款：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" id="" type="hidden"
	                               data-data="[{'id':'T','text':'是'},{'id':'F','text':'否'}]"
	                               data-valuefield="id" data-textfield="text" name="isAllocate|E|S" >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">是否付款：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" id="" type="hidden"
	                               data-data="[{'id':'T','text':'是'},{'id':'F','text':'否'}]"
	                               data-valuefield="id" data-textfield="text" name="isPayment|E|S" >
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

	<div class="hide" id="showGrantEditPage"></div>

<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
	    		
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsList" context="admin"/>&jsoncallback=?';
	    	var url_view_RequestFunds_page='<z:ukey key="com.zdsoft.finance.requestFunds.scanRequestFundsView" context="admin"/>';
	    	 CALLBACK.scanRequestFunds = function (index,value) {
	             ZDS_MESSAGE_CLIENT.openMenuLink('scan_RequestFunds_tab', "查看案件请款信息",url_view_RequestFunds_page+"&businessKey="+value.id);
	         };
	         
	    	$.ZUI.init();
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : true,
                  isRowNum : true,
                  pagination : true,
                  tableCls : 'table-index',//table的样式
                  columns:[[
                	 {field : 'applyState',title : '状态',align : 'center',
                         formatter: function (row, value) {
                        	var xs={"0":"草稿","1":"审批中","2":"通过","":"废弃"};
                           	 return xs[value+""]; 
                           }},
					{field : 'orgName',title : '机构',align : 'center'},
					{field : 'billCode',title : '请款单号',align : 'center'},
					{field : 'reqFundsAmount',title : '请款金额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
					{field : 'summary',title : '摘要',align : 'center'},
					{field : 'isAllocate',title : '是否拨款',align : 'center',
						formatter : function(rowData,value) {
							return value?"是":"否";	
					}},
					{field : 'grantDate',title : '实际拨款日期',align : 'center',
						formatter : function(rowData,value) {
							return value==0?"":ZTOOL.strToDate(""+value);	
					}},
					{field : 'isAllocate',title : '是否付款',align : 'center',
						formatter : function(rowData,value) {
							return value?"是":"否";	
					}},
					{field : 'paymentDate',title : '实际付款日期',align : 'center',
						formatter : function(rowData,value) {
							return value==0?"":ZTOOL.strToDate(""+value);	
					}},{field : 'id',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							return '<a title="查看案件请款信息"  onclick="scanRequestFunds"><button class="btn-blue">详情</button></a>';
				}}
				] ]
        	});
	         
		    	
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