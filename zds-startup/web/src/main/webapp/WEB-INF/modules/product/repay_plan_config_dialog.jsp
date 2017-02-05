<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="repayPlanConfigDialog">
    <div id="planConfigDialog">
        <form id="addRepayPlanConfigForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" id="productId" name="productVo.id" value="${productId }">
            <input type="hidden" id="repayPlanConfigId" name="id" value="${repayPlanConfig.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>费用项:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${repayPlanConfig.feeCd }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=feeCd"
	                        data-valuefield="fullcode" data-textfield="name" name="feeCd">
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>收款方:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${repayPlanConfig.receiverCd }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=receiverCd"
	                        data-valuefield="fullcode" data-textfield="name" name="receiverCd">
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>是否启用:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                         data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="${repayPlanConfig.isEnable == null ? true : repayPlanConfig.isEnable }"
                         data-valuefield="id" data-textfield="text" name="isEnable">
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#repayPlanConfigDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "还款计划配置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addRepayPlanConfigForm'));
                    	if(flag){
                    		var addRepayPlanConfigForm = $('#addRepayPlanConfigForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.repayPlanConfig.saveOrUpdate" context="admin"/>',
                                data: addRepayPlanConfigForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	var formArray=$("#queryRepayPlanConfig").serialize();
                                    	formArray=decodeURIComponent(formArray, true);
                                    	$('#tb-repayPlanConfig').ZTable("reload", formArray);
                                    	$("#repayPlanConfigDialog").Zdialog("close");
                                    }else{
                                    	$.ZMessage.error("错误", data.msg, function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#repayPlanConfigDialog").Zdialog("close");
                                }
                            });
                    	}
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#repayPlanConfigDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.initForms("#planConfigDialog");
    });
</script>
