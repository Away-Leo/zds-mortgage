<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>案件收款明细页面</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">

<!-- 	案件基本信息 -->
	<jsp:include page="../../casemanage/case_base_info.jsp"></jsp:include>
    
<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">案件收费明细列表</div>
		<div class="p10">
			<div id="zd-table1"></div>
		</div>
	</div>
	
	<script type="text/javascript">
    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
	    		 
	    	$.ZUI.init();
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.casemanage.findFeeInfomationByCaseApplyId" context="admin"/>&caseApplyId=${caseApply.id}';//获取数据
	    	var print_caseApply_fee="<z:ukey key='com.zdsoft.finance.finIncome.printFinIncome' context='admin'/>";
	    	
	    	$('#zd-table1').ZTable({
	    		 url : url_data_list,
                toolbar : [
                	 {
                         id: 'btncut3', text: '打印', iconCls: 'icon-btn04', buttonCls: 'btn-blue',
                         handler: function () {
                        	 var rows=$('#zd-table1').ZTable("getSelections");
                        	 if(rows.length==0){
                        		 $.ZMessage.warning("警告", "请选择要打印的数据！");
                        		 return;
                        	 }
                        	 var feeIds="";
                         	 for(var e in rows){
                         		feeIds+=rows[e].id+",";
                         	 }
                    		 ZDS_MESSAGE_CLIENT.openMenuLink('print_caseApply_fee_tab', "打印案件收费明细",print_caseApply_fee+"&caseApplyId=${caseApply.id}&feeIds="+feeIds);
                         }
                     }
                ],
                columns:[[
	                      {field: 'feeTypeName', title: '收款类型'},
	                      {field: 'feeItemName', title: '收款项目'},
	                      {field: 'feeeObjectName', title: '收款主体'},
	                      {field: 'expectedAmount', title: '应收金额'},
	                      {field: 'receivedAmount', title: '实收金额'}
                     ]],
                     idField: 'id',//每行数据的，唯一标识符
                     singleSelect: false,//表格行是单选还是多选
   	              	 isRowNum: true,//是否显示行号
   	             	 rows: 8,//分页情况下，显示的条数
   	             	 currentPage: 1,//分页情况下的，当前页
   	             	 pagination: false,//表格是否分页
   	             	 tableCls: 'table-index'//table的样式
      		});
	    	
	    });
	    
	</script>
</body>
</html>