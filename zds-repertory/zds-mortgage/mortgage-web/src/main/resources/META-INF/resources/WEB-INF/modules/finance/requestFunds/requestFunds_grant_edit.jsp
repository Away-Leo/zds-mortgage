<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="gartDialogDiv">
	<div id="InfoDialog" >
	        <form id="saveOrUpdateFrom" class="zui-form mt15" >
	         <input type="hidden"  name="reqFundsIds"  value="${reqFundsIds}"/>
	        	<div class="page-box">
				<div class="p10">
						<table class="table-detail">
								<tr>   
				                    <td class="td-title pct20">请款笔数：</td>
				                    <td>
		                                <dd class="detail">
		                                   <label>
		                                   			<input type="text"  id="requestFunds_num" class="zui-input zui-disabled"   value="0"  readonly="readonly"/>
									  	   </label>
									   </dd>
									</td>
									
									 <td class="td-title pct20">请款合计(元)：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  id="requestFunds_amount"  class="zui-input zui-disabled"  value="0.00"  readonly="readonly" />
									  	   </label>
									   </dd>
		                            </dl>
									</td>
									</tr>
									
									<tr>
										 <td class="td-title pct20"><b class="c-red ">*</b>实际拨款日期：</td>
					                    <td colspan="3">	
						                     <dl class="form-item form-auto">
				                                <dd class="detail">
				                                   <label>
					                                    <input class="zui-date zui-validatebox" type="text" id="grantDate_date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'grantDate'})"  validate-type="Require">
				                         				<input type="hidden" id="grantDate" name="grantDate"  value="">
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
		
		var url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsList" context="admin"/>&jsoncallback=?&id|IN|S=${reqFundsIds}';//获取数据
		var save_url='<z:ukey key="com.zdsoft.finance.requestFunds.grantRequestFunds" context="admin"/>';
		
    	$("#gartDialogDiv").Zdialog({
    		 width: 800, height: 600, closed: false, title: "批量拨款",isDestroy:true,
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
                    	$("#gartDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    
        $.ZUI.init("#InfoDialog");	//初始化
        
        $('#zd-table11').ZTable({
   		 url : url_data_list,
   		 singleSelect: true,//表格行是单选还是多选
         	 isRowNum: true,//是否显示行号
        	 rows: 10,//分页情况下，显示的条数
        	 currentPage: 1,//分页情况下的，当前页
        	 pagination: true,//表格是否分页
        	 tableCls: 'table-index',//table的样式
         toolbar : [ ],
         columns:[[
       	  		     {field: 'orgName', title: '机构'},
                     {field: 'billCode', title: '请款单号'},
                     {field: 'reqFundsAmount', title: '请款金额(元)'},
                     {field: 'contactCompanyName', title: '往来单位'}
              ]],
  			onLoadSuccess:function(data){
				var allRows=$('#zd-table11').ZTable("getRows");
				var amountTotal=0.00;//请款合计
				for(var e in allRows){
					amountTotal+=allRows[e].reqFundsAmount;
				}
				$("#requestFunds_num").val(allRows.length);
				$("#requestFunds_amount").val(amountTotal);
			}
		});
        
        function saveOrSubmitForm(){
     	   		var validFlg = $.ZUI.validateForm($('#saveOrUpdateFrom'));
				 if (validFlg) {	
					$.ajax({
			                type: 'post',
			                url: save_url,
			                data:$('#saveOrUpdateFrom').serialize(),
							dataType: "json",
			                success: function (data) {
			                	if (data.resultStatus == 0) {
                                	$.ZMessage.success("提示", "保存成功", function () {
                                		$("#gartDialogDiv").Zdialog("close");
                        				$('#zd-table').ZTable("reload", {});//刷新列表数据
                	                });
                                }
			                }
			            });
				 } 
        }
    });
</script>
