<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>收款单复核</title>
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
            <form id="form_search" class="zui-form form-search" method="post" >
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
							<input class="zui-input" id="customerName" name="cc|customerName|LK|S" >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">产品分类：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" data-width="94" data-callback="renderCombobox" name="mca|productTypeName|E|S" id="productTypeName"
													data-url="<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?"
													data-valuefield="id" data-textfield="name">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden" id="productSubtypeId" name="mca|productSubtypeName|E|S" data-width="94">
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="customerName" name="receipt|orgName|LK|S" >
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
					<dt class="title">拓展经理：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="mca|developmentManagerName|LK|S" >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">单据号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="" name="receipt|billCode|LK|S" >
						</label>
					</dd>
				</dl>
				
				 <dl class="form-item">
					<dt class="title">单据状态：</dt>
					<dd class="detail">
						<label> 
						 <input class="zui-combobox" type="hidden" 
							  data-data="[{'id':'0','text':'草稿'},{'id':'1','text':'已提交'},{'id':'2','text':'集团已确认'},{'id':'3','text':'退回'},{'id':'1','text':'已废弃'}]"
					          data-valuefield="id" data-textfield="text" name="receipt|status|E|S" >
						</label>
					</dd>
				</dl>
				
				
				<dl class="form-item">
	                <dt class="title">实际收款日期：</dt>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="startpaidRepayDateLimit"  placeholder="选择开始日期"  onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'paidRepayDate_Re',maxDate:'#F{$dp.$D(\'endpaidRepayDateLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="paidRepayDate_Re" name="receipt|paidRepayDate|RE|S">
	                    </label>
	                </dd>
	                <dd class="detail">
	                    <label>
	                        <input class="zui-date zui-validatebox" type="text" id="endpaidRepayDateLimit"   placeholder="选择结束日期"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',dateFmt:'yyyy-MM-dd',vel:'paidRepayDate_Le',minDate:'#F{$dp.$D(\'startpaidRepayDateLimit\')}'})"  style="width: 95px;"/>
	                   		<input type="hidden" id="paidRepayDate_Le" name="receipt|paidRepayDate|LE|S">
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
					<dt class="title">录款方式：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-combobox" type="hidden"  name="receipt|recordMethod|E|S" id="productTypeId"
													 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0094"
													data-valuefield="fullcode" data-textfield="name">
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
	
	<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">案件信息列表</div>
		<div class="p10">
			<div id="zd-table"></div>
		</div>
	</div>

<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.loading','zd/jquery.zds.form', 'zd/jquery.zds.table','zd/jquery.zds.combobox'],
	    	 function ($, CALLBACK,ZTOOL){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.repayment.repaymentApplyList" context="admin"/>';
	    	
	    	var url_review_view = '<z:ukey key="com.zdsoft.finance.repayment.repaymentReceiptReviewPage" context="admin"/>&scan=true';
	    	
	    	var url_update_status = '<z:ukey key="com.zdsoft.finance.repayment.updateStatus" context="admin"/>';
	    	
	    	var url_view_repaymentReceipt_page='<z:ukey key="com.zdsoft.finance.repayment.repaymentReceiptReviewPage" context="admin"/>&scan=false';
	    	
	    	//产品分类下拉框
		    CALLBACK.renderCombobox=function(id,name){
		    			$("#productSubtypeId").ZCombobox({
		    	            valueField: "id",
		    	            textField: "productName",
		    	            url:'<z:ukey key="com.zdsoft.finance.product.findProductListByCatId" context="admin"/>&jsoncallback=?&categoryId='+id
		    	        });
		    	};
		    	
		    	CALLBACK.scanFinIncome = function (index,value) {
		    		ZDS_MESSAGE_CLIENT.openMenuLink('revirw_view_tab', "复核",url_review_view+"&receiptId="+value.ID+"&caseApplyId="+value.CASEAPPLYID);
		    	};
		    	
		    	 CALLBACK.scanRepayment = function (index,value) {
		    		 	ZDS_MESSAGE_CLIENT.openMenuLink('repaymentReceipt_view_tab1', "详情",url_view_repaymentReceipt_page+"&receiptId="+value.ID);
	      		};
	    	
	    	$('#zd-table').ZTable({
	    	      url : url_data_list,
                  singleSelect : false,
                  isRowNum : false,
                  pagination : true,
                  isScroll:true,
                  tableCls: 'table-index scroll',
                  toolbar : [
                	  {
                          id: 'btncut3', text: '批量复核', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
                          handler: function () {
                         	 var rows=$('#zd-table').ZTable("getSelections");
                         	 if(rows.length==0){
                         		 $.ZMessage.warning("警告", "请选择要复核的案件！");
                         		 return;
                         	 }
                         	 
                         	$.ZMessage.confirm("确认", "是否要批量复核！", function (r) {
                                if (r == 'recovery') {
                                	 var ids=[];
                                 	 for(var e in rows){
                                 		ids.push(rows[e].ID);
                                 	 }
                                 	$.ajax({
             			                type: 'post',
             			                url: url_update_status,
             			                data:"&status=2&id="+ids,
             							dataType: "json",
             			                success: function (data) {
             			                	if(data.resultStatus == 0){
           	 			                		$.ZMessage.success("成功", "复核成功",function(){
           	 			                			$('#zd-table').ZTable("reload");
           	 			                		});
             			                	}else{
             			                		$.ZMessage.error("错误", "操作失败");
             			                	}
             			                }
             			            });
                                }
                            }, {
                                'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
                                'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
                            });
                          }
                      },
                      {
                          id: 'btn3', text: '导出明细', iconCls: 'icon-btn08', buttonCls: 'btn-white',
                          handler: function () {
	                        	  $.ZMessage.confirm("提示", "是否导出待复核的收款?<br/>如果导出缓慢,请耐心等待....", function (r) {
	      				            if (r == 'recovery') {
	      				            	  window.location.href ='<z:ukey key="com.zdsoft.finance.repayment.exportRepaymentReceiptExcel" context="admin"/>';
	      				            }
	    				        	}, {
	    				            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
	    				            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
	    				        	});
                          }
                      }
                  ],
                  columns:[[
                		{field : 'MECHANISMNAME',title : '机构',align : 'center'},
    					{field : 'BILLCODE',title : '单据号',align : 'center'},
    					{field : 'COLLOECTIONCODE',title : '收据号',align : 'center',
                            formatter: function (row, value) {
                            	 return value==null?"无":value; 
                            }},
    					{field : 'CASEAPPLYCODE',title : '案件号',align : 'center'},
    					{field : 'CUSTOMERNAME',title : '主借人',align : 'center',
                            formatter: function (row, value) {
                            	 return value==null?"无":value; 
                            }},
                            {field : 'DEVELOPMENTMANAGERNAME',title : '拓展经理',align : 'center'},
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
    				    {field : 'PAIDREPAYDATE',title : '实际收款日期',align : 'center',
                                              formatter: function (row, value) {
                                                  return ZTOOL.strToDate(value+""); //时间转换
                                              }},
    				    {field : 'RECEIPTS',title : '经手人',align : 'center'},
    				    {field : 'RECORDMETHOD',title : '录款方式',align : 'center',
    						formatter : function(rowData,value) {
    							var x={"YWDM009401":"按系统规则录入金额","YWDM009402":"不按规则自由录入","YWDM009403":"保证金转供款","YWDM009404":"预收款转供款"};
    							return x[value];	
    						}},
    				    {field : 'PRODUCTSUBTYPENAME',title : '产品',align : 'center'},
    				    {field : 'OVREDUEDAY',title : '逾期天数',align : 'center',
                            formatter: function (row, value) {
                              	 return value==null?"0":value; 
                              }},
    				    {field : '',title : '委贷银行',align : 'center',hidden:true},
    					{field : 'CAPITALISTNAME',title : '资金来源',align : 'center',
                            formatter: function (row, value) {
                           	 return value==null?"无":value; 
                           }},
    					{field : 'FUNDPLANNAME',title : '信托计划',align : 'center',
                               formatter: function (row, value) {
                              	 return value==null?"无":value; 
                              }},
    					{field : '',title : '信贷债权状态',align : 'center',
                                  formatter: function (row, value) {
                                    	 return "正常"; 
                                    }},
    					{field : 'STATUS',title : '状态',align : 'center',
    						formatter : function(rowData,value) {
    							var x={"0":"草稿","1":"已提交","2":"集团已确认","3":"退回","4":"已废弃"};
    							return "<font color='#8B658B'>"+x[value]+"</font>";	
    						}},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							var  html = '<a title="复核"  onclick="scanFinIncome"><button class="btn-blue">复核</button></a>';
							if(rowData.STATUS!=1){
								  html = '<a title="详情"  onclick="scanRepayment"><button class="btn-blue">详情</button></a>';
							}
				            return html;
					}}
				] ]
        	});
	
      		$.ZUI.init();
	         ZDS_MESSAGE_CLIENT.refreshThis=function(){
		    		$('#zd-table').ZTable("reload");
		    	};//回调刷新当前页面
		    	
	    	$('#btn_search').on('click',function(){
	    		var formArray=$("#form_search").serialize();
	    			formArray=decodeURIComponent(formArray, true);
	    		$('#zd-table').ZTable("reload",formArray);
	    	});
	    	
	    	$('#btn_reset').on('click',function(){
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
