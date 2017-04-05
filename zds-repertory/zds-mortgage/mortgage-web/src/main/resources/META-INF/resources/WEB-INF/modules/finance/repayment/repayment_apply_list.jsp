<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>收款单明细</title>
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
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form', 'zd/jquery.zds.table','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.message'],
	    	 function ($, CALLBACK,ZTOOL){
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.repayment.repaymentApplyList" context="admin"/>&receipt|status|IN|I=0,3';
	    	var url_edit_repaymentReceipt_page='<z:ukey key="com.zdsoft.finance.repayment.repaymentReceiptEditPage" context="admin"/>';
	    	var url_edit_repaymentAdvanceReceipt_page='<z:ukey key="com.zdsoft.finance.repayment.repaymentAdvanceReceiptEditPage" context="admin"/>';
	    	var url_delete_repaymentReceipt='<z:ukey key="com.zdsoft.finance.repayment.deleteRepaymentReceipt" context="admin"/>';
	    	
	    	 CALLBACK.updateRepayment = function (index,value) {
	    		 	if(value.RECEIPTTYPE=="1"){
		    		 	ZDS_MESSAGE_CLIENT.openMenuLink('repaymentReceipt_tab', "编辑收款单",url_edit_repaymentReceipt_page+"&receiptId="+value.ID+"&caseApplyId="+value.CASEAPPLYID);
	    		 	}else{
		    		 	ZDS_MESSAGE_CLIENT.openMenuLink('repaymentAdvanceReceipt_tab', "编辑预收款",url_edit_repaymentAdvanceReceipt_page+"&receiptId="+value.ID+"&caseApplyId="+value.CASEAPPLYID);
	    		 	}
	         };
	         
	         CALLBACK.destoryRepayment = function (index,value) {
	        	 $.ZMessage.question("提示", "是否废弃这条数据？", function () {
				            	$.ajax({
		 			                type: 'post',
		 			                url: url_delete_repaymentReceipt+"&receiptId="+value.ID,
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
                  toolbar : [
                	  {
                          id: 'btn-add1', text: '新增收款单', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
                          handler: function () {}
                      },
                      {
                          id: 'btn-add2', text: '新增预收款', iconCls: 'icon-btn08', buttonCls: 'btn-orange',
                          handler: function () {}
                      },
                      {
                          id: 'btn3', text: '导出明细', iconCls: 'icon-sbtn05', buttonCls: 'btn-white',
                          handler: function () {
                        	  	var url='<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=收款单列表导出文档';
              		        var param=$("table").html();
              				$("form").remove("#exportFrom");
              		        $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
              		        $("#exportFrom").submit();
                          }
                      }
                  ],
                  columns:[[
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
				    {field : '',title : '逾期天数',align : 'center',hidden:true},
				    {field : '',title : '委贷银行',align : 'center',hidden:true},
					{field : 'CAPITALSOURCE',title : '资金来源',align : 'center',hidden:true},
					{field : '',title : '所属信托计划',align : 'center',hidden:true},
					{field : '',title : '信贷债权状态',align : 'center',hidden:true},
					{field : 'STATUS',title : '状态',align : 'center',
						formatter : function(rowData,value) {
							var x={"0":"草稿","1":"审批中","2":"通过","3":"退回","4":"不通过"};
							return x[value];	
						}},
					{field : 'ID',title : '操作',align : 'center',
						formatter : function(rowData,value) {
							var  html = '<a title="编辑"  onclick="updateRepayment"><button class="btn-blue">编辑</button></a>';
							html += '&nbsp;&nbsp;<a title="废弃"  onclick="destoryRepayment"><button class="btn-blue">废弃</button></a>';
				            return html;
					}}
				] ]
        	});
	    	
	    	bindClickToBtn('zds_btn_btn-add1','新增收款单',url_edit_repaymentReceipt_page);
		    bindClickToBtn('zds_btn_btn-add2','新增预收款',url_edit_repaymentAdvanceReceipt_page);

	    	function bindClickToBtn(btnId,btnTitle,btnUrl){
	    		// 项目选择器
		        $("#"+btnId).Zseleter({
		            title: '案件选择器',
		            btnId: "CASEAPPLYCODE",
		            width: 800,
		            height: 500,
		            key: "id",
		            value: "caseApplyCode",
		            singleSelect: true,
		            columns: {
		                test: [[
		                    {field: 'caseApplyCode', title: '案件号', width: 80},
		                    {field : 'customerName',title : '主借人', width: 80,
								formatter : function(rowData,value) {
									return value==null?"":value;	
							}},
		                    {field : 'productSubtypeName',title : '产品名称', width: 80},
		                    {field : 'applyAmount',title :'项目金额(元)', width: 80},
		                    {field : 'developmentManagerName',title : '项目经理', width: 80}
		                ]]
		            },
		            url: '<z:ukey key="com.cnfh.rms.casemanage.casetracking.getCaseTracking" context="admin"/>&ma|stage|E|S=YWDM009240',
		            type: 'test',
		            defSearchForm: {
		                test: [
		                    {
		                        label: "案件号",
		                        type: "input",
		                        name: "ma|caseApplyCode|LK|S"
		                    }
		                ]
		            },
		            onOk: function (data) {
		            	if(data.length>0){
			            	ZDS_MESSAGE_CLIENT.openMenuLink(btnId+'_tab', btnTitle,btnUrl+"&caseApplyId="+data[0]._data.id);
		            	}
		            },
		            onClose:function(){
							$("#"+btnId).Zseleter("clearLabel");
		            }
		        });
	    	}
	    	
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
	    		$('.zui-combobox').val("");
	    		$('.zui-combobox').ZCombobox('setValue','');
	    	});
	    });
	</script>
</body>
</html>