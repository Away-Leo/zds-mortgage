<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="frm-content">
	<div class="save">
	    <button id="btn-save" class="btn-blue mr10">保存</button>
	    <button id="btn-submit" class="btn-blue mr10">提交</button>
	</div>
	<!-- 表单 -->
	<form id="receiptForm" class="zui-form" action="javascript:void(0);" zdata-options="{}">
		<input type="hidden" name="id" value="${finIncomeVo.id }">
		<input type="hidden" name="caseApplyId" value="${caseApplyVo.id }">
		<input type="hidden" name="caseApplyCode" value="${caseApplyVo.caseApplyCode}">
        <div class="page-box">
            <div class="page-title">收款单</div>
            <div class="p5">
                <table class="table-detail">
                    <tbody><tr>
                        <td class="td-title pct10">单据号：</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail">
									<label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled" readonly="readonly" validate-type="Require" name="billCode" value="${finIncomeVo.billCode }">
                                    </label>                                
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10">案件号：</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" validate-type="" value="${caseApplyVo.caseApplyCode }">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10">主借人：</td>
                        <td class="pct30">
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" id="customerName" validate-type="" value="${caseApplyVo.customerName }">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="td-title">业务部门：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" validate-type="" value="${caseApplyVo.developmentDepartmentName }">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title">付款方：</td>
                        <td>
                            <dl class="form-item form-auto">
                            
                                <dd class="detail">
                                    <input class="zui-combobox zui-validatebox" type="hidden" name="payerId" id="payerId"   value="${finIncomeVo.payerId }">
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title">付款人：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly"   id="payerCustomerName" name="payerCustomerName" value="${finIncomeVo.payerCustomerName }">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    
                    <tr>
                       
                        <td class="td-title">收款总金额(元)：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" id="totalExpectedAmount" validate-type="" value="0">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title">收入总金额(元)：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" id="totalReceivedAmount" validate-type="" value="0" >
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        
                          <td class="td-title">代收总金额(元)：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly"  id="totalCollectionAmount"  value="0">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        
                    </tr>
                    
                    <tr>
                        <td class="td-title">预收总金额(元)：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                    <label>
                                        <input type="text" class="zui-input zui-validatebox zui-disabled"  readonly="readonly" id="totalpredictAmount"  value="0">
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        
                         <td class="td-title"><b class="c-red ">*</b>收款日期：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                	<label>
                                        <input type="text" class="zui-date zui-validatebox" id="receiptsDate" validate-type="Require"   onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',vel:'receiptsDateShow'})">
                                        <input type="hidden" id="receiptsDateShow" name="receiptsDate" value="${finIncomeVo.receiptsDate}" />
                                     </label>
                                </dd>
                            </dl>
                        </td>
                        
                        <td class="td-title"><b class="c-red ">*</b>结算方式：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
                                	<input class="zui-checkbox zui-validatebox " name="settlement" type="hidden" data-multiple="true"   
                            		data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0086"
                                    data-valuefield="id" data-textfield="text" validate-type="Require" value="${finIncomeVo.settlement}">
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    
                    <tr>
                      
                        <td class="td-title"><b class="c-red ">*</b>缴款人：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
									<label>
                                        <input type="text" class="zui-input zui-validatebox" validate-type="Require,Length[1-30]" name="payerPersonName" value="${finIncomeVo.payerPersonName}">
                                    </label>                                
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title"><b class="c-red ">*</b>摘要：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail">
									<label>
                                        <input type="text" class="zui-input zui-validatebox" validate-type="Require,Length[1-100]" name="summary" value="${finIncomeVo.summary}">
                                    </label>                                
                                </dd>
                            </dl>
                        </td>
                        
                          <td class="td-title">收据号：</td>
                        <td>
                            <dl class="form-item form-auto">
                                <dd class="detail mr15">
                                	<label>
                                        <input type="text" class="zui-input  zui-validatebox "   validate-type="Require,Length[1-20]"   name="receiptNo" value="${finIncomeVo.receiptNo }">
                                    </label>
                                </dd>
                                <dd class="detail">
                                	<input class="zui-checkbox zui-validatebox" name="invoiceFlag" type="hidden" data-multiple="true" data-data="[{'id':'0','text':'已开票'}]" 
                                	data-valuefield="id" data-textfield="text"  value="${finIncomeVo.invoiceFlag }" >
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td class="td-title">备注：</td>
                        <td colspan="6">
                            <label>
			                 	<textarea class="zui-area zui-validatebox" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${finIncomeVo.remark }</textarea>
				             </label>
				             <div class="zd-area">
			                    <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
			                 </div>
                        </td>
                    </tr>
                </tbody>
               </table>
            </div>
        </div>
    </form>
	<!-- 列表区域 -->
	<div class="page-box">
	  <div class="page-title">单据明细
	  </div>
	  <div class="p10">
	      <div id="zd-table11"></div>
	</div>
</div>

<!-- 新增弹窗 -->
<div id="receiptDetailDialog" style="display:none">
	<div id="receiptDetailDialogDiv" class="p10">
     <form id="receiptDetailForm" class="zui-form" method="post" >
		 <dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>收款类型：</dt>
            <dd class="detail">
              <input class="zui-combobox zui-validatebox" type="hidden"
                        data-url='<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00146'
                        data-height="200"
                        data-valuefield="fullcode" data-textfield="name" validate-type="Require"  name="feeTypeName" id="feeTypeName"  >

            </dd>
       	</dl>
       	
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>收款项目：</dt>
            <dd class="detail">
              		<input class="zui-combobox zui-validatebox" type="hidden"
                      data-url='<z:ukey key="com.zdsoft.finance.parameter.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?'
                      data-height="200"
                      data-valuefield="code" data-textfield="text" validate-type="Require"  name="feeItemName" id="feeItemName" >
            </dd>
       	</dl>  
       	
       		<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>收款主体：</dt>
            <dd class="detail">
              		<input class="zui-combobox zui-validatebox" type="hidden"
                        data-url='<z:ukey key="com.zdsoft.finance.parameter.getInComeBodyOrgList"  context="admin"/>&orgCode=${caseApplyVo.mechanismCode}'
                        data-height="200"
                        data-valuefield="id" data-textfield="name" validate-type="Require"   id="incomeSubject_d"  >
            </dd>
       	</dl>  
       	
       	<dl class="form-item">
       		<dt class="title"><b class="c-red mr5">*</b>实收金额：</dt>
            <dd class="detail">
              <label> 
              		<input type="text" class="zui-input zui-validatebox" name="paidAmount"   validate-type="Require,Amount"  />
              </label> 
            </dd>
       	</dl>
     </form>
    </div> 
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.dialog','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table'], 
		function($, CALLBACK,ZTools) {
	
		var url_data_list = '<z:ukey key="com.zdsoft.finance.finance.feeInfomation.list" context="admin"/>&caseApplyId=${caseApplyVo.id}';//获取数据
		
		if("${finIncomeVo.id}"!=""){
		 	url_data_list = "<z:ukey key='com.zdsoft.finance.finance.finIncomeDetail.list' context='admin'/>&finIncomeId|E|S=${finIncomeVo.id}";
		}
		
		$("#receiptsDate").val(ZTools.strToDate("${finIncomeVo.receiptsDate}"));
		
		var incomeSubject=$.parseJSON('${incomeSubject}');
	    var  dataJson="[";
	    var bool=false;
   		for(var i in incomeSubject){
   			if(bool){
   				dataJson+=",";
   			}
   			dataJson+="{'id':'"+incomeSubject[i].id+"',"+"'name':'"+incomeSubject[i].inBody+"'}";
   			bool=true;
   		}
   		dataJson+="]";

		//付款方下拉框
		$("#payerId").ZCombobox({
	          valueField: "id",
	          textField: "name",
	          url:'<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0056' ,
	          onSelect:function(value,text,index){
	        	  $("#payerCustomerName").val("");
		      	  $('#zd-table11').ZTable("reload",{"payerType":value});
		      	 
		      	  $.ajax({
		                  type: 'post',
		                  url: '<z:ukey key="com.zdsoft.finance.finance.getCustomerName" context="admin"/>&caseApplyId=${caseApplyVo.id}&joinType='+value ,
		                  dataType: 'json',
		                  success: function (data) {
		                	  if(data != null){
		                  		$("#payerCustomerName").val(data.customerName);
		                	  }
		                  }
		              });
	          }
	      });
	    
    	$('#zd-table11').ZTable({
    	       url : url_data_list,
    	       singleSelect : true,
    	       isRowNum : true,
    	       pagination : false,
    	       currentPage : 1,
    	       idField: "id",
    	       tableCls : 'table-index',//table的样式
    	       toolbar : [
    	    	   {
	                   id: 'btncut1', text: '新增预收款', iconCls: 'icon-btn08', buttonCls: 'btn-orange',
	                   handler: function () {
	                	   $("#feeTypeName").ZCombobox("setValue","YWDM0014608");
	                	   $("#feeTypeName").ZCombobox("disable");
	                	   $("p[data-name=YWDM0014608]").show();
	                	   
	                	   var rows=$('#zd-table11').ZTable("getRows");
	            			if (typeof(rows)!="undefined"&&rows.length>0) {	
		        				for(var e in rows){
		        					//判断是否有预收款
		         					if("YWDM0014608"==rows[e].feeType){
		         						 $.ZMessage.warning("警告", "本次已收取预收款！");
		         						return;
		         					}
		        				}
    						}
	                	   $("#receiptDetailDialog").Zdialog("open");
	                   }
               		},
               	 {
 	                   id: 'btncut2', text: '新增款项', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
 	                   handler: function () {
 	                	  $("p[data-name=YWDM0014608]").hide();
 	                	  $("#feeTypeName").ZCombobox("setValue","1");
 	                	  $("#feeTypeName").ZCombobox("enabled");
 	                	  $("#receiptDetailDialog").Zdialog("open");
 	                  }
                  },
                 {
   	                   id: 'btncut3', text: '删除', iconCls: 'icon-btn12', buttonCls: 'btn-gray',
   	                   handler: function () {
   	                	 var rows=$('#zd-table11').ZTable("getSelections");
                     	 if(rows.length==0){
                     		 $.ZMessage.warning("警告", "请选择要删除的费用项！");
                     		 return;
                     	 }
                     	 
                     	 $.ZMessage.question("提示", "是否要删除该费用项？", function () {
                     		$('#zd-table11').ZTable('deleteRow');
     	        		 });
   	                   }
                  } ,
                  {
  	                   id: 'btncut4', text: '打印收费明细', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
  	                   handler: function () {
  	                	 	var rows=$('#zd-table11').ZTable("getRows");
  	                	    var print_caseApply_fee="<z:ukey key='com.zdsoft.finance.finIncome.printFinIncomeDetail' context='admin'/>";
  	              			 ZDS_MESSAGE_CLIENT.openMenuLink('print_caseApply_fee_tabs2', "打印收费明细",print_caseApply_fee+"&caseApplyId=${caseApplyVo.id}&finIncomeId=${finIncomeVo.id}");
  	                   }
                 } ,
                 {
 	                   id: 'btncut5', text: '打印收据', iconCls: 'icon-btn08', buttonCls: 'btn-blue',
 	                   handler: function () {
 	                	  $.ZMessage.info("提示", "未提供套打模板！");
 	                	  return;
 	                   }
                } 
    	       ],
    	       onEdit:true,
    	       columns:[[
    	    	    {field : 'feeId',hidden:true},
    	    	  	{field : 'feeType',hidden:true},
    	    	  	{field : 'feeItem',hidden:true},
    	    	  	{field : 'feeObjectId',hidden:true},
    	    	  	{field : 'payObjectId',hidden:true},
    	    	  	{field : 'expectedPayableAmount',hidden:true},
    	    	  	{field : 'feeTypeName',title : '收款类型',align : 'center'},
    	    	  	{field : 'feeItemName',title : '收款项目',align : 'center'},
    	    	  	{field : 'feeObjectTypeName',title : '付款方',align : 'center',hidden:true},
    				{field : 'incomeSubject',title : '收款主体',align : 'center'},
    				{field : 'expectedAmount',title : '应收金额(元)',align : 'center'},
    				{field : 'receivedAmount',title : '已收金额(元)',align : 'center'},
    				{field : 'paidAmount',title : '实收金额(元)',align : 'center'},
    				{field : 'receivedAmount',title : '集团确认金额(元)',align : 'center'},
    				{field : 'id',title : '操作', align : 'center',hidden:true}
    		] ],
    		 columnsType: [
    		                {
    		                    "incomeSubject": {
    		                        "inputType": "combobox",
    		                         "data": {
    		                            "valueField": "id",
    		                            "textField": "name",
    		                            "data": dataJson
    		                        },
    		                        "validateType": "Require"
    		                    }
    		               ,"paidAmount": {
    		                        "inputType": "input",
    		                        "validateType": "Require,Amount,IsOverNumber,MinSize[0.001]",
    		                        "validateFalse":"该项为必填项|输入金额不正确|小数位数不能超过2位!|实收金额不能小于0"
    		                    }
    		                    
    		                },
    		                {
    		                    "inputWidth": 155
    		                }
    		],
    		onLoadSuccess:function(data) {
    			window.calcAmount();
    			 $("#zd-table11 input").change(function() {window.calcAmount(); });
    		}
    		
    	});
	
		
    	window.calcAmount=function(){
    		
			var totalExpectedAmount = 0;//收款总金额
			var totalReceivedAmount = 0;//收入总金额
			var totalCollectionAmount = 0;//代收总金额
			var totalpredictAmount = 0;//预收总金额
			
			var rows=$('#zd-table11').ZTable("getRows",true);
 			if (typeof(rows)!="undefined"&&rows.length>0) {	
 				for(var e in rows){
 					totalExpectedAmount+=parseFloat(rows[e].paidAmount); 
 					if("YWDM0014608"==rows[e].feeType){
 						totalpredictAmount+=parseFloat(rows[e].paidAmount); 
 					}else if("YWDM0014606"==rows[e].feeType){
 						totalCollectionAmount+=parseFloat(rows[e].paidAmount); 
 					}else if("YWDM0014607"==rows[e].feeType){
 						totalReceivedAmount+=parseFloat(rows[e].paidAmount); 
 					}
 				}
 				
 				$("#totalExpectedAmount").val(totalExpectedAmount);
 				$("#totalReceivedAmount").val(totalReceivedAmount);
 				$("#totalCollectionAmount").val(totalCollectionAmount);
 				$("#totalpredictAmount").val(totalpredictAmount);
			}
		};
    	
	$("#receiptDetailDialog").Zdialog({
		   width: 700, 
		   height: 400,
		   closed: true, 
		   title: "新增款项",
		   buttons: [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#receiptDetailForm'));
	            	   if (isValid) {
	            		   var row =  {//行数据
                                "feeType": $("#feeTypeName").ZCombobox("getValue"),
                                "feeTypeName": $("#feeTypeName").ZCombobox("getText"),
                                "feeItem": $("#feeItemName").ZCombobox("getValue"),
                                "feeItemName": $("#feeItemName").ZCombobox("getText"),
                                "feeObjectId": '',
                                "payObjectId": '',
                                "incomeSubject":$("#incomeSubject_d").ZCombobox("getValue"),
                                "expectedAmount":$("input[name=paidAmount]").val(),
                                "receivedAmount":0,
                                "expectedPayableAmount":$("input[name=paidAmount]").val(),
                                "paidAmount": $("input[name=paidAmount]").val(),
                                "feeId":"",
                                "id": ""
                                };
	                   		$('#zd-table11').ZTable("addOneRow",row);
	                   		$("#receiptDetailDialog").Zdialog("close");
	                   		window.calcAmount();
	            	   }
	               }
	           },
	           {
	               id: 'message-btn',
	               text: '取消',
	                  buttonCls: 'btn-gray',
	                  handler: function () {
	                    $("#receiptDetailDialog").Zdialog("close");
	                  }
	            }
	        ]
	   	});
	
	//保存
	$('#btn-save').on('click',function(){
			saveMe(false);
	});
	
	//提交
	$('#btn-submit').on('click',function(){
			saveMe(true);
	});
	
	function saveMe(isSubmit){
		
		 var valid = $.ZUI.validateForm($('#receiptForm'));
		 var row= $('#zd-table11').ZTable("getRows");
		 if (!valid) {	
				return;
			}
	
		if((typeof(row)=="undefined"||row.length==0)){
			$.ZMessage.warning("警告","请填写本次收费");
			return;
		}

		var receiptForm = $('#receiptForm').serialize();
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.finance.finIncome.save" context="admin"/>&isSubmit='+isSubmit,
            data: receiptForm+'&receiptItems='+JSON.stringify(row),
            dataType: 'json',
            success: function (data) {
            	$("input[name=id]").val(data.id);
            	if(data.resultStatus== 0){
            		$.ZMessage.success("提示", "操作成功", function () {
            			if(isSubmit){
	                		ZDS_MESSAGE_CLIENT.closeSelf();
            			}else{
            				window.location.href='<z:ukey key="com.zdsoft.finance.finance.finIncome.receipt" context="admin"/>&businessKey='+data.id;
            			}
                    });
             	}else{
             		$.ZMessage.error("错误", "操作失败");
             	}
            }
        });
	}
	if("${finIncomeVo.id}"!=""){
		$("#zds_btn_btncut4").show();
		$("#zds_btn_btncut5").show();
	}else{
		$("#zds_btn_btncut4").hide();
		$("#zds_btn_btncut5").hide();
	}
	$.ZUI.init();
});

</script>
</body>
</html>