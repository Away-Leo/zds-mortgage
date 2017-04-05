<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="paymentDialogDiv">
	<div id="InfoDialog" >
	        <form id="saveOrUpdateFrom" class="zui-form mt15" >
	         <input type="hidden"  name="reqFundsId"  value="${reqFundsId}"/>
	        	<div class="page-box">
				<div class="p10">
						<table class="table-detail">
								<tr>   
				                    <td class="td-title pct20">请款单号：</td>
				                    <td>
		                                <dd class="detail">
		                                   <label>
		                                   			<input type="text"  id="" class="zui-input zui-disabled"   readonly="readonly"  value="${billCode}"/>
									  	   </label>
									   </dd>
									</td>
									
									 <td class="td-title pct20">往来单位：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-disabled"   readonly="readonly"  value="${payObjectName}"/>
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									</tr>
									
									<tr>
										 <td class="td-title pct20"><b class="c-red ">*</b>实际付款日期：</td>
					                    <td colspan="3">	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
					                                    <input class="zui-date zui-validatebox" type="text" id="paymentDate_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'paymentDate'})"  validate-type="Require">
				                         				<input type="hidden" id="paymentDate" name="paymentDate"  >
											  	   </label>
											   </dd>
				                            </dl>
										</td>
								</tr>
		            </table>
				</div>
			</div>
	            
	        </form>
	        
	         <div class="page-title">请款费用列表</div>
				<div class="p10">
					<div id="zd-table11"></div>
				</div>
			</div>
	</div>
</div>
	  
	  
<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
, function ($,CALLBACK, COMBOBOX,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		
		var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsDetailList" context="admin"/>&reqFundsId|E|S=${reqFundsId}&payObjectId|E|S=${payObjectId}';//获取数据
		var save_url='<z:ukey key="com.zdsoft.finance.requestFunds.paymentRequestFunds" context="admin"/>';
		
    	$("#paymentDialogDiv").Zdialog({
            width: 800, height: 600, closed: false, title: "付款单",isDestroy:true,
            buttons: [{
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	saveOrSubmitForm();
                    }
                },{
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#paymentDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    
        $.ZUI.init("#InfoDialog");	//初始化
        
        $('#zd-table11').ZTable({
   		 url : url_data_list,
   		 singleSelect: true,//表格行是单选还是多选
         	 isRowNum: true,//是否显示行号
        	 pagination: false,//表格是否分页
        	 tableCls: 'table-index',//table的样式
//         	 onEdit:true,
         toolbar : [ ],
         columns:[[
        	 		 {field: 'id', title: 'id',hidden:true},
       	  		     {field: 'caseApplyCode', title: '案件号'},
                     {field: 'feeTypeName', title: '付款类型'},
                     {field: 'feeItemName', title: '付款项目'},
                     {field: 'reqFundsAmount', title: '请款金额(元)'},
                     {field: 'paymentAmount', title: '付款金额(元)',
						formatter : function(rowData,value) {
							return rowData.reqFundsAmount;	
					}}
              ]],columnsType: [
                  {
                      "paymentAmount": {
                          "inputType": "input",
                          "validateType": "Require,Amount,CompareAmount[<=-reqFundsAmount]",
                          "validateFalse":"||不能大于最大请款额,请重新输入!"
                      }
                  },
                  {
                      "inputWidth": 120,
                      "inputHeight": 24
                  }
              ]
		});
        
        function saveOrSubmitForm(){
     	   		var validFlg = $.ZUI.validateForm($('#saveOrUpdateFrom'));
     	   		var row=$('#zd-table11').ZTable("getRows");
				 if (validFlg) {	
					$.ajax({
			                type: 'post',
			                url: save_url,
			                data:$('#saveOrUpdateFrom').serialize()+"&payFeeList="+JSON.stringify(row),
							dataType: "json",
			                success: function (data) {
			                	if (data.resultStatus == 0) {
                                	$.ZMessage.success("提示", "保存成功", function () {
                                		$("#paymentDialogDiv").Zdialog("close");
                        				$('#zd-table').ZTable("reload", {});//刷新列表数据
                	                });
                                }
			                }
			            });
				 }
        }
    });
</script>
