<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
<div class="save">
    <button id="btn-loan" class="btn-blue mr10">放款</button>
</div>

	<div class="frm-content frm-bottom" id="opinionDiv">
	    <div class="page-box">
	        <div class="page-title">基本信息</div>
	        <div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct10">案件号</td>
	                    <td class="pct20">${apply.CASEAPPLYCODE}</td>
	                    <td class="td-title pct10">接单日期</td>
	                    <td class="pct20">${apply.CREATETIME}</td>
	                    <td class="td-title pct10">子产品</td>
	                    <td class="pct20">${apply.PRODUCTSUBTYPENAME}</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">审批金额(元)</td>
	                    <td class="pct20" group="decimal">${apply.CASEAPPLYAMOUNT}</td>
	                    <td class="td-title pct10">贷款期限</td>
	                    <td class="pct20">${apply.loanTerm}</td>
	                    <td class="td-title pct10">风控审批通过日期</td>
	                    <td class="pct20">${apply.passDate}</td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    <div class="page-box">
	        <div class="page-title">资金情况</div>
	        <div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct10">已放款资金(元)</td>
	                    <td class="pct20" group="decimal">${apply.loanAmount}</td>
	                    <td class="td-title pct10">待垫出(元)</td>
	                    <td class="pct20" group="decimal">${apply.APPLYAMOUNT}</td>
	                    <td class="td-title pct10">已回收资金</td>
	                    <td class="pct20">0元</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">待回收资金(元)</td>
	                    <td group="decimal">${apply.CASEAPPLYBALANCE}</td>
	                    <td class="td-title pct10">资金来源</td>
	                    <td>${apply.CAPITALNAME}</td>
	                    <td class="td-title pct10">资金计划</td>
	                    <td>${apply.FUNDPLANNAME}</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">分配额度(元)</td>
	                    <td group="decimal">${apply.APPLYAMOUNT}</td>
	                    <td class="td-title pct10">放款银行</td>
	                    <td>${apply.receiveBankName}</td>
	                    <td class="td-title pct10">放款银行账户账号</td>
	                    <td>${apply.receiveBankAccount}</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">备付资金匹配</td>
	                    <td>${apply.actualLimitStatus}</td>
	                    <td class="td-title"></td>
	                    <td></td>
	                    <td class="td-title"></td>
	                    <td></td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    
	    <form id="searchForm" class="zui-form" action="javascript:void(0);"
          zdata-options={"url":'<z:ukey key="com.zdsoft.finance.loan.sureAllowLoan"  context="admin"/>',"callBack":"saveCallBack"}>

        <div class="page-box">
            <div class="page-title">操作</div>
            <div class="p5">
                <table class="table-detail">
                	<tr>
                        <td class="td-title pct10"><b class="c-red mr5">*</b>操作类型<input id="loanApplyId" name="loanApplyId" type="hidden" value="${apply.ID}"></td>
                        <td class="pct20">
                        	 <dl class="form-item">
                                <dd class="detail">
	                                    <input class="zui-combobox zui-validatebox" type="hidden" id="operateType" name="operateType"
                               				data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0099"
	                                           data-callback="change"
	                                           data-valuefield="id" data-textfield="text" validate-type="Require" validate-false="请选择操作类型" />
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10"><b class="c-red mr5">*</b>实际发生日期</td>
                        <td class="pct20">
                        	<dl class="form-item">
                                <dd class="detail">
                                	<label>
                                		 <input class="zui-date zui-validatebox" id="loanDate" name="loanDate" type="text" onclick="WdatePicker()" validate-type="Require" validate-false="请选择实际放款日期" readonly/>
                                	</label>
                                </dd>
                            </dl>
						</td>
						<td class="td-title pct10"><b class="c-red mr5">*</b>放款金额(元)</td>
                        <td class="pct20">
                        	<dl class="form-item">
	                        	<dd class="detail">
	                                <label>
	                                	<input type="text" class="zui-input zui-validatebox" validate-type="Require" id="loanAmount" name="loanAmount" value="${apply.APPLYAMOUNT}" readonly>
	                                </label>
	                             </dd>
                            </dl>
						</td>
                    </tr>
                	<tr>
                        <td class="td-title pct10">备注</td>
                        <td class="pct20" colspan="5">
                        	 <label>
								<textarea id="remark" name="remark" class="zui-area zui-validatebox"  validate-false="备注不能超过250个字" validate-type="Length[0-250]"></textarea>
                             </label>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
	    
	</div>
	
<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
, function ($, CALLBACK, ZTOOL, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	 $.ZUI.init();
    	 //改变金额格式
    	 var tdArray=$("[group='decimal']");
    	 for(var i=0;i<tdArray.length;i++){
    		 $(tdArray[i]).html(formatCurrency($(tdArray[i]).html()));
    	 }
    	 //金额分隔符
        function formatCurrency(value) {
             return ZTOOL.formatCurrency(value+""); 
         }
    	//放款
        $("#btn-loan").click(function(){
        	var isValidate = $.ZUI.validateForm($('#opinionDiv'));
            if (isValidate) {
            	var params=$("#searchForm").serialize();
            	params = decodeURIComponent(params, true);
            	Loading.show();
            	$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.loan.sureLoan" context="admin"/>',
                    data: params,
                    dataType:"json",
                    success: function (data) {
                    	Loading.hide();
                        if (data.resultStatus == 0) {
                        	$.ZMessage.success("提示", "放款确认成功", function () {
                         		setTimeout(function(){
                                 	ZDS_MESSAGE_CLIENT.refreshOpenner();
                                 	ZDS_MESSAGE_CLIENT.closeSelf();
                                 },200);
                          	 });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                    },
			            error: function () {
			            	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			            		Loading.hide();
			             });
			            }
                }); 
            }
		});
       

    });


</script>
	
</body>
</html>