<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>案件收款查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style>
    	table.scroll th,table.scroll td{
    		white-space: nowrap;
    	}
    </style>
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
							<input class="zui-input" id="caseApplyCode" name="mca|caseApplyCode|LK|S">
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
					<dt class="title">拓展经理：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="mca|developmentManagerName|LK|S"  >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
	                <dt class="title">接单日期：</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startTimeLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Re',maxDate:'#F{$dp.$D(\'endTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Re" name="mca|applyDate|RE|S">
	                    </label>
	                </dd>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endTimeLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'applyDate_Le',minDate:'#F{$dp.$D(\'startTimeLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="applyDate_Le" name="mca|applyDate|LE|S">
	                    </label>
	                </dd>
	            </dl>
	            
	            <dl class="form-item">
					<dt class="title">资金来源：</dt>
					<dd class="detail">
						<label> 
													
						 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="" data-url="<z:ukey key='com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode' context='admin'/>&jsoncallback=?"
				                          data-valuefield="id" data-textfield="capitalName" name="mca|capitalSource|E|S" >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">信托计划：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden"  name="cla|fundPlanId|E|S" id="productTypeId"
													data-url="<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustList" context="admin"/>&jsoncallback=?"
													data-valuefield="id" data-textfield="creditEntrustName">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
	                <dt class="title">放款日期：</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startActualDateLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'actualDate_Re',maxDate:'#F{$dp.$D(\'endActualDateLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="actualDate_Re" name="loan|actualDate|RE|S">
	                    </label>
	                </dd>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endActualDateLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'actualDate_Le',minDate:'#F{$dp.$D(\'startActualDateLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="actualDate_Le" name="loan|actualDate|LE|S">
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
	    	 function ($, CALLBACK,ZTOOL,Loading){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.marketing.getCaseApplyCustomerReceivableList" context="admin"/>&mca|stage|E|S=YWDM009240';
	    	
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
                  currentPage : 1,
                  tableCls : 'table-index',//table的样式
                  isScroll:true,
                  tableCls: 'table-index scroll',
                  toolbar : [{
                          id: 'btn1', text: '导出本月应还', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
                          handler: function () {
                        	  $.ZMessage.confirm("提示", "是否导出本月应还?<br/>如果导出缓慢,请耐心等待....", function (r) {
        				            if (r == 'recovery') {
        				            	  window.location.href ='<z:ukey key="com.zdsoft.finance.repayment.exportReceivableMonthToExcel" context="admin"/>';
        				            }
      				        	}, {
      				            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
      				            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
      				        	});
                          }
                      }
                  ],
                  columns:[[
					{field : 'CASEAPPLYCODE',title : '案件号',align : 'center'},
					{field : 'CUSTOMERNAME',title : '主借人',align : 'center',
						formatter : function(rowData,value) {
							return value==null?"":value;	
					}},
					{field : 'PRODUCTTYPENAME',title : '产品分类',align : 'center'},
					{field : 'PRODUCTSUBTYPENAME',title : '子产品',align : 'center'},
					{field : 'DEVELOPMENTMANAGERNAME',title : '拓展经理',align : 'center'},
					{field : 'APPLYDATE',title : '接单日期',align : 'center',
						formatter : function(rowData,value) {
							return ZTOOL.strToDate(""+value);	
					}},
					{field : 'APPLYTERM',title : '贷款期限(月)',align : 'center'},
					{field : 'APPLYTERM',title : '贷款期限(天)',align : 'center',
						formatter : function(rowData,value) {
							return rowData.APPLYTERM*30;	
					}},
					{field : 'ACTUALDATE',title : '放款日期',align : 'center',
						formatter : function(rowData,value) {
							return ZTOOL.strToDate(value==null?"0":value+"");	
					}},
					{field : 'APPLYAMOUNT',title : '贷款金额(元)',align : 'center',
                        formatter: function (row, value) {
                            return ZTOOL.formatCurrency(value+""); 
                        }},
					{field : 'LOANAPPLYANOUNT',title : '已放款(元)',align : 'center',
                            formatter: function (row, value) {
                                return ZTOOL.formatCurrency(value+""); 
                            }},
					{field : '',title : '已收回(元)',align : 'center',
						formatter : function(rowData,value) {
							return ZTOOL.formatCurrency(""+(rowData.LOANAPPLYANOUNT-rowData.CASEAPPLYBALANCE));	
					}},
					{field : 'CASEAPPLYBALANCE',title : '未收回(元)',align : 'center',
                        formatter: function (row, value) {
                            return ZTOOL.formatCurrency(value+""); 
                        }},
					{field : 'OVREDUETIME',title : '当前逾期次数',align : 'center',
						formatter : function(rowData,value) {
							return value==null?"0":value;	
					}},
					{field : 'DAYS',title : '逾期天数',align : 'center',
						formatter : function(rowData,value) {
							return value==null?"0":value;	
					}},
					{field : 'CAPITALISTNAME',title : '资金来源',align : 'center',
						formatter : function(rowData,value) {
							return value==null?"":value;	
					}},
					{field : 'FUNDPLANNAME',title : '所属信托计划',align : 'center',
						formatter : function(rowData,value) {
							return value==null?"":value;	
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