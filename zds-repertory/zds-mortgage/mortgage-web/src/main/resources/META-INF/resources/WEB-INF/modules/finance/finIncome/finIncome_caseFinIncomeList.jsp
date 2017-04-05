<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>案件收款明细查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">查询信息</div>
        <div id="searchForm" class="p5">
            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
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
							<input class="zui-input" id="customerName" name="cus|customerName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">业务部门：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id="developmentDepartmentName"
							name="c|developmentDepartmentName|LK|S">
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
		<div class="page-title">案件信息列表</div>
		<div class="p10">
			<div id="zd-table"></div>
		</div>
	</div>

<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.finance.getCaseApplyFinIncomeList" context="admin"/>';
	    	var url_view_case_finIncome_page = '<z:ukey key="com.zdsoft.finance.finance.finIncome.caseFinIncomeview" context="admin"/>';//案件收款明细查看页面
	    	
	    	 CALLBACK.scanFinIncome = function (index,value) {
	             ZDS_MESSAGE_CLIENT.openMenuLink('scan_case_finIncome_tab', "查看案件收款明细",url_view_case_finIncome_page+"&caseApplyId="+value.ID);
	         };
	    	
	    	$.ZUI.init();
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : true,
                  isRowNum : true,
                  pagination : true,
                  currentPage : 1,
                  tableCls : 'table-index',//table的样式
                  toolbar : [],
                  columns:[[
					{field : 'CASEAPPLYCODE',title : '案件号',width : 100,align : 'center'},
					{field : 'CUSTOMERNAME',title : '主借人',width : 100,align : 'center'},
					{field : 'PRODUCTTYPENAME',title : '产品分类',width : 100,align : 'center'},
					{field : 'PRODUCTSUBTYPENAME',title : '子产品',width : 120,align : 'center'},
					{field : 'APPLYAMOUNT',title : '贷款金额(元)',width : 100,align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
					{field : 'PAYERAMOUNT',title : '收款金额(元)',width : 100,align : 'center',
                           formatter: function (row, value) {
                          	 return ZTOOL.formatCurrency(value+""); 
                          }},
					{field : 'DEVELOPMENTDEPARTMENTNAME',title : '业务部门',width : 100,align : 'center'},
					{field : 'id',title : '操作',width : 100,align : 'center',
						formatter : function(rowData,value) {
							var  html = '<a title="查看案件收款明细"  onclick="scanFinIncome"><button class="btn-blue">详情</button></a>';
				            return html;
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
	    		$('input[class="zui-input"]').val("");
	    	});
	    });
	</script>
</body>
</html>