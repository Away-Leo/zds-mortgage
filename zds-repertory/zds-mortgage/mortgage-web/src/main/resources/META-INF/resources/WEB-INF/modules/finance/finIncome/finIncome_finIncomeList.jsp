<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>案件收款单查询</title>
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
							<input class="zui-input" id="" name="mca|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">单据号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="fin|billCode|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">业务部门：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="mca|developmentDepartmentName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="mca|mechanismName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> <input class="zui-input" id=""	name="cus|customerName|LK|S">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">收款日期：</dt>
					 <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Re" name="fin|receiptsDate|RE|S">
	                    </label>
	                </dd>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Le" name="fin|receiptsDate|LE|S">
	                    </label>
	                </dd>
				</dl>
				
				 <dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
						<label> 
						 <input class="zui-combobox" type="hidden" 
							  data-data="[{'id':'0','text':'草稿'},{'id':'1','text':'待确认'},{'id':'2','text':'已确认'},{'id':'3','text':'已退回'},{'id':'1','text':'已废弃'}]"
					          data-valuefield="id" data-textfield="text" name="fin|status|E|S" >
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
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.finance.finIncomeDataList" context="admin"/>';
	    	var url_view_case_finIncome_page = '<z:ukey key="com.zdsoft.finance.finance.finIncome.view" context="admin"/>';//案件收款明细查看页面
	    	var url_edit_finIncome_page = '<z:ukey key="com.zdsoft.finance.finance.finIncome.receipt" context="admin"/>';
	    	var url_delete_repaymentReceipt = '<z:ukey key="com.zdsoft.finance.finance.finIncome.destoryFinIncome" context="admin"/>';
	         
	         CALLBACK.editFinIncome = function (index,value) {
	             ZDS_MESSAGE_CLIENT.openMenuLink('edit_finIncome_tab', "处理",url_edit_finIncome_page+"&businessKey="+value.ID);
	         };
	         
	         CALLBACK.destoryFinIncome = function (index,value) {
	        	 $.ZMessage.question("提示", "是否废弃这条数据？", function () {
		            	$.ajax({
			                type: 'post',
			                url: url_delete_repaymentReceipt+"&finIncomeId="+value.ID,
							dataType: "json",
			                success: function (data) {
			                	if(data.resultStatus== 0){
	 			                		$.ZMessage.success("成功", "操作成功",function(){
	 			                			$('#zd-table').ZTable("reload");
	 			                		});
			                	}else{
			                		$.ZMessage.error("错误", "操作失败");
			                	}
			                }
			            });
	        	});
	         };
	         
	         CALLBACK.scanFinIncome = function (index,value) {
	             ZDS_MESSAGE_CLIENT.openMenuLink('scan_finIncome_tab', "查看收款单明细",url_view_case_finIncome_page+"&businessKey="+value.ID);
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
                	{field : 'MECHANISMNAME',title : '机构',align : 'center'},
					{field : 'CASEAPPLYCODE',title : '案件号',align : 'center'},
					{field : 'CUSTOMERNAME',title : '主借人',align : 'center'},
					{field : 'BILLCODE',title : '单据号',align : 'center'},
					{field : 'PAYERAMOUNT',title : '收款金额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
                       {field : 'RECEIPTSDATE',title : '收款日期',align : 'center',
                           formatter: function (row, value) {
                             	 return ZTOOL.strToDate(value+""); 
                             }},
					{field : 'DEVELOPMENTDEPARTMENTNAME',title : '业务部门',align : 'center'},
					{field : 'STATUS',title : '状态',align : 'center',
                        formatter: function (row, value) {
                        	var xx={"0":"草稿","1":"待确认","2":"已确认","3":"已废弃","4":"已退回"};
                         	 return xx[value]; 
                          }},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							var aHtml='';
							 if("${empCd}"==rowData.CREATEBY&&(rowData.STATUS==0||rowData.STATUS==4)){
								 aHtml+='<a title="处理"  onclick="editFinIncome"><button class="btn-blue">处理</button></a>&nbsp;&nbsp;';	 
								 aHtml+='<a title="废弃"  onclick="destoryFinIncome"><button class="btn-blue">废弃</button></a>&nbsp;&nbsp;';	
							 }
							 aHtml+='<a title="查看案件收款明细"  onclick="scanFinIncome"><button class="btn-blue">详情</button></a>';
						return aHtml;
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
	    		$('.zui-date').val("");
	    		$('input[type="hidden"]').val("");
	    		$('.zui-combobox').val("");
	    		$('.zui-combobox').ZCombobox('setValue','');
	    	});
	    });
	</script>
</body>
</html>