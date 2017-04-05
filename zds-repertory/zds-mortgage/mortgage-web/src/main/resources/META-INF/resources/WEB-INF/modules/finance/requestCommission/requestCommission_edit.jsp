<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>新增支佣请款</title>
    <%@ include file="../../common/common_js.jsp" %>
</head>
<body id="body">
	
	 <h1 class="page-title">
			请款信息
	</h1>
		
	<div class="frm-content frm-bottom">
	 	<form id="saveOrupdateFrom" class="zui-form" action="javascript:void(0);" zdata-options="{}">	
	 		<input name="id"  type="hidden"  value="${funds.id}"/>
	 		<input name="reqType"  type="hidden"  value="2"/>	
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
									
									<td class="td-title pct10"><b class="c-red ">*</b>请款金额：</td>
				                    <td>	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text" readonly="readonly"  class="zui-input zui-disabled"  id="reqFundsAmount" name="reqFundsAmount" value="${funds.reqFundsAmount}" />
									  	   </label>
									   </dd>
		                            </dl>
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
								</tr>
								
								<tr>   
				                    <td class="td-title pct10"><b class="c-red ">*</b>摘要：</td>
				                    <td colspan="5">	
				                     <dl class="form-item form-auto">
		                                <dd class="detail">
		                                   <label>
				                    				<input type="text"  class="zui-input zui-validatebox"  id="summary" name="summary"  validate-type="Require,Length[1-100]"  value="${funds.summary }"/>
									  	   </label>
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
				<div id="commissionTable"></div>
			</div>
		</div>
		
	   <!-- 按钮区域 -->
		<div class="save">
			<button id="btn-save" type="button" class="btn-blue mr10">保存</button>
		    <button id="btn-submit" type="button" class="btn-blue mr10">提交</button>
		</div>
	
	</div>
	
	<script type="text/javascript">
	 seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.combobox', 'zd/jquery.zds.seleter', 'zd/jquery.zds.form', 'zd/jquery.zds.table'],
	    	 function ($, CALLBACK,ZTOOL){
		 	
		 	var url_data_list = '<z:ukey key="com.zdsoft.finance.requestCommission.caseFeeList" context="admin"/>&caseApply.id|IN|S=${caseApplyId}&feeItem|IN|S=FYDM000005,FYDM000015,FYDM000016';//获取数据
		 	if('${funds.id}' != ''){
		 		 url_data_list = '<z:ukey key="com.zdsoft.finance.requestFunds.getRequestFundsDetailList" context="admin"/>&reqFundsId|E|S=${funds.id}';//获取数据
		 	}
			var save_url = '<z:ukey key="com.zdsoft.finance.requestCommission.saveOrSubmitRequestCommission"  context="admin"/>';
           
			$("#reqFundsDate_date").val(ZTOOL.strToDate($("input[name=reqFundsDate]").val()));
			
           
           $('#commissionTable').ZTable({
	    		 url : url_data_list,
	    		 singleSelect: false,//表格行是单选还是多选
              	 isRowNum: false,//是否显示行号
             	 pagination: false,//表格是否分页
             	 tableCls: 'table-index',//table的样式
//              	 onEdit:true,
              toolbar : [ { id : 'btn-add',
                  	text : '删除',
                  	iconCls : 'icon-btn12',
                      buttonCls : 'btn-orange',
                      handler : function(){ //打开页签
                      	var row=$('#commissionTable').ZTable("getSelections");
  						if(row.length == 0){
  							$.ZMessage.error("警告", "请选择一条数据！", function () {});
  							return;
  						}
  						$.ZMessage.confirm("确认", "是否删除这["+row.length+"]项费用？", function (r) {
  				            if (r == 'recovery') {
  				            	 $('#commissionTable').ZTable("deleteRow");
  				            }
				        	}, {
				            'recovery': {id: 'recovery', text: '确认', buttonCls: 'btn-blue'},
				            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
				        	});
                      } 
    			  } ],
              columns:[[
            	  		  {field: 'caseApplyCode', title: '案件号',width:100},
	                      {field: 'feeTypeName', title: '请款类型',width:100},
	                      {field: 'feeItemName', title: '请款项目',width:100},
	                      {field: 'receivedAmount', title: '可请款金额(元)',width:100},
	                      {field: 'expectedPayableAmount', title: '预收应付(元)',width:100,hidden:true},
	                      {field: 'paidAmount', title: '累计已付(元)',width:100,hidden:true},
            	  		  {field: 'reqBigAmount', title: '请款最大额',hidden:true},
	                      {field: 'reqFundsAmount', title: '请款金额(元)',width:100}
                   ]],
           		onLoadSuccess:function(data) {
           			var rows=$('#commissionTable').ZTable("getRows",true);
           			var reqFundsAmount=0;
       				for(var e in rows){
       					reqFundsAmount+=rows[e].reqFundsAmount;
       				}
       				$("#reqFundsAmount").val(reqFundsAmount);
        		}
    		});
           
           
           $("#btn-save").click(function (){
        	   saveOrSubmitForm(false);
	 		});
           
           $("#btn-submit").click(function (){
        	   saveOrSubmitForm(true);
	 		});
           
           
           function saveOrSubmitForm(oper){
        	   var validFlg = $.ZUI.validateForm($('#saveOrupdateFrom'));
        	   var rows=$('#commissionTable').ZTable('getRows');
        	   
      		 if (!validFlg) {	
      				return;
      			}
      	
      		if((typeof(row)=="undefined"||row.length==0)){
      			$.ZMessage.warning("警告","请收取支佣费用");
      			return;
      		}
      		
			$.ajax({
	                type: 'post',
	                url: save_url,
	                data:$('#saveOrupdateFrom').serialize()+"&isSubmit="+oper+"&feeList="+JSON.stringify($('#commissionTable').ZTable("getRows")),
					dataType: "json",
	                success: function (data) {
	                	$("input[name=id]").val(data.id);
	                	if(data.resultStatus== 0){
		                	if(data.msg!=null){
		                		$.ZMessage.info("成功", "操作成功[下一处理人:"+data.msg+"]",function(){
		                			ZDS_MESSAGE_CLIENT.refreshOpenner();
	                    		setTimeout(function(){
                                	 ZDS_MESSAGE_CLIENT.closeSelf();
                                },200);
		                		});
		                	}else{
		                		$.ZMessage.info("成功", "操作成功");
		                	}
	                	}else{
	                		$.ZMessage.error("错误", "操作失败");
	                	}
	                }
	            });
           }
            $.ZUI.init();
    });
</script>
</body>
</html>