<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增请款</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">
	
	 <h1 class="page-title">
			请款信息
	</h1>
		
	<div class="frm-content frm-bottom">
	 	<form id="saveOrupdateFrom" class="zui-form" action="javascript:void(0);" zdata-options="{}">		
	 		<input name="id"  type="hidden"  value="${funds.id}"/>
	 		<input name="reqType"  type="hidden"  value="1"/>
			<div class="page-box">
				
				<div class="p10">
						<table class="table-detail">
								<tr>   
				                    <td class="td-title pct10"><b class="c-red ">*</b>单据号：</td>
				                    <td>
		                                <dd class="detail">
		                                   <label>
		                                   
		                                   <input type="text"  class="zui-input zui-disabled"  id="billCode" name="billCode"   value="${funds.billCode}"  readonly="readonly"/>
									  	   </label>
									   </dd>
									</td>
									
									 <td class="td-title pct10"><b class="c-red ">*</b>请款日期：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input class="zui-date zui-validatebox" type="text" id="reqFundsDate_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'reqFundsDate'})" >
			                         				<input type="hidden" id="reqFundsDate" name="reqFundsDate"  value="${funds.reqFundsDate}">
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									
									 <td class="td-title pct10"><b class="c-red ">*</b>摘要：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-validatebox"  id="summary" name="summary"  validate-type="Require,Length[1-100]"   value="${funds.summary}"/>
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									
								</tr>
								
								<tr>   
				                    <td class="td-title pct10"><b class="c-red ">*</b>收款银行：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-validatebox"  id="accountName" name="accountName"  validate-type="Require,Length[1-50]"  value="${funds.accountName}"/>
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									 <td class="td-title pct10"><b class="c-red ">*</b>收款开户行：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-validatebox"  id="bankName" name="bankName"  validate-type="Require,Length[1-50]"  value="${funds.bankName}"/>
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									 <td class="td-title pct10"><b class="c-red ">*</b>收款账号：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-validatebox"  id="bankAccount" name="bankAccount"  validate-type="Require,Length[1-30]"  value="${funds.bankAccount}"/>
									  	   </label>
									   </dd>
		                            </dl>
									</td>
								</tr>
								
								
								<tr>   
								 <td class="td-title pct10"><b class="c-red ">*</b>往来单位：</td>
				                    <td>	
				                     <dl class="form-item ">
		                                <dd class="detail">
				                    				 		<input class="zui-combobox zui-validatebox" type="hidden"   data-multiple="true"   data-callback="searchPayObject"
							                      data-data=' ${payObject}'          data-height="150"
							                      data-valuefield="payObjectId"  data-textfield="payObjectName"   name="contactCompany"  id="contactCompany"  validate-type="Require"  data-defaultvalue="${funds.contactCompany}"  >
	
									   </dd>
		                            </dl>
									</td>
									
									 <td class="td-title pct10"><b class="c-red ">*</b>请款项目：</td>
				                    <td>	
				                    
				                    
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
	
										<input class="zui-combobox zui-validatebox" type="hidden"   data-multiple="true"   data-callback="searchFee"
							                      data-url='<z:ukey key="com.zdsoft.finance.parameter.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?'
							                      data-height="150"
							                      data-valuefield="code"  data-textfield="text"   name="requestFundsProject"  id="requestFundsProject"  validate-type="Require"  data-defaultvalue="${funds.requestFundsProject}"  >
	
									   </dd>
		                            </dl>
									</td>
							</tr>
							
								<tr>
				                    <td class="td-title pct10">备注：</td>
				                    <td colspan="5">
				                   		<label>
				                         	<textarea class="zui-area row-width zui-validatebox"  placeholder="最多可以输入200个字符" id="remark" name="remark" validate-type="Length[0-200]">${funds.remark}</textarea>
				                        </label>
				                        <div class="zd-area">
				                            <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
				                        </div>
				                    </td>
		               			 </tr>
		            </table>
				</div>
			</div>
		</form>
		
		<!-- datagrid列表区域 -->
	    <div class="page-box">
	        <div class="page-title">案件费用列表</div>
				<div class="p10">
					<div id="zd-table"></div>
				</div>
		</div>
		
		   <!-- 按钮区域 -->
			<div class="save">
				<button id="btn-save" class="btn-blue mr10">保存</button>
			    <button id="btn-submit" class="btn-blue mr10">提交</button>
			</div>
	</div>
	
	
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 
		 	var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.caseFeeList" context="admin"/>&caseApply.id|IN|S=${caseApplyId}&receivedAmount|R|D=0';//获取数据
		 		
		 		if("${funds.id}"!=""){
		 		 	url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsDetailList" context="admin"/>&reqFundsId|E|S=${funds.id}';
		 		}
		 	
			var save_url = '<z:ukey key="com.zdsoft.finance.requestFunds.saveOrSubmitRequestFunds"  context="admin"/>';
           
			$("#reqFundsDate_date").val(ZTOOL.strToDate($("input[name=reqFundsDate]").val()));
			
			CALLBACK.searchPayObject=function(data){
				 $('#zd-table').ZTable("reload",{"payObjectId|IN|S":data,"feeItem|IN|S":$("#requestFundsProject").ZCombobox("getValue")});
			};
			
			CALLBACK.searchFee=function(data){
				 $('#zd-table').ZTable("reload",{"feeItem|IN|S":data,"payObjectId|IN|S":$("#contactCompany").ZCombobox("getValue")});
			};
			
           $('#zd-table').ZTable({
	    		 url : url_data_list,
	    		 singleSelect: false,//表格行是单选还是多选
              	 isRowNum: false,//是否显示行号
             	 pagination: false,//表格是否分页
             	 tableCls: 'table-index',//table的样式
             	 onEdit:true,
              toolbar : [ { id : 'btn-add',
                  	text : '删除',
                  	iconCls : 'icon-btn12',
                      buttonCls : 'btn-orange',
                      handler : function(){ //打开页签
                      	var row=$('#zd-table').ZTable("getSelections");
  						if(row.length == 0){
  							$.ZMessage.error("警告", "请选择一条数据！", function () {});
  							return;
  						}
  						$.ZMessage.confirm("确认", "是否删除这["+row.length+"]项费用？", function (r) {
  				            if (r == 'recovery') {
  				            	 $('#zd-table').ZTable("deleteRow");
  				            }
				        	}, {
				            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
				            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
				        	});
                      } 
    			  } ],
              columns:[[
            	  		  {field: 'feeId',hidden:true},
            	  		  {field: 'feeType',hidden:true},
            	  		  {field: 'feeItem',hidden:true},
            	  		  {field: 'caseApplyId',hidden:true},
            	  		  {field: 'payObjectId',hidden:true},
            	  		  {field: 'caseApplyCode', title: '案件号',width:100},
	                      {field: 'feeTypeName', title: '请款类型',width:100},
	                      {field: 'feeItemName', title: '请款项目',width:100},
	                      {field: 'payObjectName', title: '往来单位',width:100},
	                      {field: 'receivedAmount', title: '实收金额(元)',width:100},
	                      {field: 'expectedPayableAmount', title: '预收应付(元)',width:100},
	                      {field: 'paidAmount', title: '累计已付(元)',width:100},
            	  		  {field: 'reqBigAmount', title: '请款最大额',hidden:true},
	                      {field: 'reqFundsAmount', title: '请款金额(元)',width:100}
                   ]],columnsType: [
                       {
                           "reqFundsAmount": {
                               "inputType": "input",
                               "defaultValue":"0",
                               "validateType": "Require,Amount,IsOverNumber,CompareAmount[<=-reqBigAmount],MinSize[0.001]",
                               "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|不能大于最大请款额|请款金额不能小于0"
                           }
                       },
                       {
                           "inputWidth": 120,
                           "inputHeight": 24
                       }
                   ]
    		});
           
           $("#btn-save").click(function (){
        	   saveOrSubmitForm(false);
	 		});
           
           $("#btn-submit").click(function (){
        	   saveOrSubmitForm(true);
	 		});
           
           $.ZUI.init();
           
           function saveOrSubmitForm(oper){
        	     var validFlg = $.ZUI.validateForm($('#saveOrupdateFrom'));
        	   	 var row= $('#zd-table').ZTable("getRows");
 				 if (validFlg&&(typeof(row)!="undefined"&&row.length>0)) {	
 					$.ajax({
 			                type: 'post',
 			                url: save_url,
 			                data:$('#saveOrupdateFrom').serialize()+'&contactCompanyName='+$('#contactCompany').ZCombobox("getText")+'&requestFundsProjectName='+$('#requestFundsProject').ZCombobox("getText")+"&isSubmit="+oper+"&feeList="+JSON.stringify(row),
 							dataType: "json",
 			                success: function (data) {
 			                	$("input[name=id]").val(data.id);
 			                	if(data.resultStatus== 0){
	 			                	if(data.msg!=null){
	 			                		$.ZMessage.success("成功", "操作成功[下一处理人:"+data.msg+"]",function(){
	 			                			ZDS_MESSAGE_CLIENT.refreshOpenner();
				                    		setTimeout(function(){
			                                	 ZDS_MESSAGE_CLIENT.closeSelf();
			                                },200);
	 			                		});
	 			                	}else{
	 			                		$.ZMessage.success("成功", "操作成功",function(){
	 			                			window.location.href='<z:ukey key="com.zdsoft.finance.requestFunds.requestFundsEditPage" context="admin"/>&businessKey='+data.id;
	 			                		});
	 			                	}
 			                	}else{
 			                		$.ZMessage.error("错误", "操作失败");
 			                	}
 			                }
 			            });
 				 }
           }
    });
</script>
</body>
</html>