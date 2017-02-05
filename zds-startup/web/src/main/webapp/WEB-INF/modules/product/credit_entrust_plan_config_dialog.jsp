<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="creditEntrustPlanConfigDialog">
    <div id="entrustPlanConfigDialog">
        <form id="addCreditEntrustPlanConfigForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" id="productId" name="productVo.id" value="${productId }">
            <input type="hidden" id="creditEntrustPlanConfigId" name="id" value="${creditEntrustPlanConfig.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>资方:</dt>
	             <dd class="detail">
				     <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="capitalistId"  name="capitalistId" value="${creditEntrustPlanConfig.capitalistId }">
				     <input class="zui-input zui-validatebox" type="hidden" validate-type="" id="capitalistName"  name="capitalistName" value="${creditEntrustPlanConfig.capitalistName }">
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>资金计划:</dt>
	             <dd class="detail">
					 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="creditEntrustId"  name="creditEntrustId" value="${creditEntrustPlanConfig.creditEntrustId }">
				     <input class="zui-input zui-validatebox" type="hidden" validate-type="" id="creditEntrustName"  name="creditEntrustName" value="${creditEntrustPlanConfig.creditEntrustName }">
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title sptitle"><b class="c-red mr5">*</b>最低评估成数(包含):</dt>
	             <dd class="detail">
					 <label> 
						<input class="zui-input zui-validatebox" validate-type="Require,Digital[0-2]" validate-false="|请输入正确数字" id="minEvaluateNum" name="minEvaluateNum" value="${creditEntrustPlanConfig.minEvaluateNum }">
					 </label>
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title sptitle"><b class="c-red mr5">*</b>最高评估成数(不包含):</dt>
	             <dd class="detail">
					 <label> 
						<input class="zui-input zui-validatebox" validate-type="Require,Digital[0-2]" validate-false="|请输入正确数字" id="maxEvaluateNum" name="maxEvaluateNum" value="${creditEntrustPlanConfig.maxEvaluateNum }">
					 </label>
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>是否启用:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                         data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="${creditEntrustPlanConfig.isEnable == null ? true : creditEntrustPlanConfig.isEnable }"
                         data-valuefield="id" data-textfield="text" name="isEnable">
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#creditEntrustPlanConfigDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "资金计划配置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addCreditEntrustPlanConfigForm'));
                    	if(flag){
                    		var addCreditEntrustPlanConfigForm = $('#addCreditEntrustPlanConfigForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.creditEntrustPlanConfig.saveOrUpdate" context="admin"/>',
                                data: addCreditEntrustPlanConfigForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#tb-creditEntrustPlanConfig').ZTable("reload", {});
                                    	$("#creditEntrustPlanConfigDialog").Zdialog("close");
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
                                	$("#creditEntrustPlanConfigDialog").Zdialog("close");
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
                    	$("#creditEntrustPlanConfigDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	$("#capitalistId").ZCombobox({
            valueField: "id",
            textField: "cooperatorName",
            url:'<z:ukey key="com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode" context="admin"/>&jsoncallback=?',
            onSelect:function(value,text,index){
                $('#capitalistId').val(value);
                $('#capitalistName').val(text);
            }
        });
    	
    	$("#creditEntrustId").ZCombobox({
            valueField: "id",
            textField: "creditEntrustName",
            url:'<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustList" context="admin"/>&jsoncallback=?',
            onSelect:function(value,text,index){
                $('#creditEntrustId').val(value);
                $('#creditEntrustName').val(text);
            }
        });
    	
    	//初始化
        $.ZUI.initForms("#entrustPlanConfigDialog");
    });
</script>
