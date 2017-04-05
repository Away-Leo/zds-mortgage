<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>费用请款管理</title>
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
	    	
	    	var url_data_list = '<z:ukey key="com.cnfh.rms.casemanage.casetracking.getCaseTracking" context="admin"/>';
	    	
	    	var url_edit_requestFunds_page='<z:ukey key="com.zdsoft.finance.requestFunds.requestFundsEditPage" context="admin"/>';
	    	
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
                  singleSelect : false,
                  isRowNum : false,
                  pagination : true,
                  currentPage : 1,
                  tableCls : 'table-index',//table的样式
                  toolbar : [
                	  {
                          id: 'btncut3', text: '请款', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
                          handler: function () {
                         	 var rows=$('#zd-table').ZTable("getSelections");
                         	 if(rows.length==0){
                         		 $.ZMessage.warning("警告", "请选择要请款的案件！");
                         		 return;
                         	 }
                         	 var caseApplyIds=[];
                         	 for(var e in rows){
                         		caseApplyIds.push(rows[e].id);
                         	 }
                              ZDS_MESSAGE_CLIENT.openMenuLink('case_requestFunds_apply_tab', "案件请款",url_edit_requestFunds_page+"&caseApplyId="+caseApplyIds+"&reqType=1");
                          }
                      }
                  ],
                  columns:[[
					{field : 'caseApplyCode',title : '案件号',align : 'center'},
					{field : 'customerName',title : '主借人',align : 'center'},
					{field : 'productTypeName',title : '产品分类',align : 'center'},
					{field : 'productSubtypeName',title : '子产品',align : 'center'},
					{field : 'applyDate',title : '贷款日期',align : 'center',
						formatter : function(rowData,value) {
							return ZTOOL.strToDate(""+value);	
					}},
					{field : 'applyAmount',title : '贷款金额(元)',align : 'center',
                        formatter: function (row, value) {
                       	 return ZTOOL.formatCurrency(value+""); 
                       }},
					{field : 'stageName',title : '案件状态',align : 'center'}
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