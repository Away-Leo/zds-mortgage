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
	        	<dt class="title">资方:</dt>
	            <dd class="detail">
	           		<input type="hidden" name="capitalistId" value="${product.capitalistId}"/>
	           		<input class="zui-input" name="capitalistName" value="${product.capitalistName}" readonly="readonly"/>
	            </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>资金计划:</dt>
	             <dd class="detail">
					 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="capitalPlanId"  name="capitalPlanId" value="${creditEntrustPlanConfig.capitalPlanId}">
				     <input class="zui-input zui-validatebox" type="hidden" validate-type="" id="capitalPlanName"  name="capitalPlanName" value="${creditEntrustPlanConfig.capitalPlanName }">
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title sptitle"><b class="c-red mr5">*</b>最低评估成数(包含):</dt>
	             <dd class="detail">
					 <label> 
						<input class="zui-input zui-validatebox" validate-type="Require,Digital[1-2],CompareAmount[<-maxPercentage]" validate-false="|请输入正确数字|最低评估成数不能大于等于最高评估成数" id="minPercentage" name="minPercentage" value="${creditEntrustPlanConfig.minPercentage }">
					 </label>
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title sptitle"><b class="c-red mr5">*</b>最高评估成数(不包含):</dt>
	             <dd class="detail">
					 <label> 
						<input class="zui-input zui-validatebox" validate-type="Require,Digital[1-2],CompareAmount[>-minPercentage]" validate-false="|请输入正确数字|最高评估成数不能小于等于最低评估成数" id="maxPercentage" name="maxPercentage" value="${creditEntrustPlanConfig.maxPercentage }">
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
                    		var addCreditEntrustPlanConfigForm = $('#addCreditEntrustPlanConfigForm').serializeArray();
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
                                    	$('#tb-creditEntrustPlanConfig').ZTable("reload",{});
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
    	
    	/* $("#capitalistId").ZCombobox({
            valueField: "id",
            textField: "capitalName",
            url:'<z:ukey key="com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode" context="admin"/>&jsoncallback=?',
            value:'${creditEntrustPlanConfig.capitalistId}',
            onSelect:function(value,text,index){
                $('#capitalistId').val(value);
                $('#capitalistName').val(text);
                if(value == ""){
                	value = "gufeng";
                }
            }
        }); */
        $("#capitalPlanId").ZCombobox({
            valueField: "id",
            textField: "creditEntrustName",
            url:'<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustListByCapitalistId" context="admin"/>&capitalistId=${product.capitalistId}&jsoncallback=?',
            onSelect:function(value,text,index){
                $('#capitalPlanId').val(value);
                $('#capitalPlanName').val(text);
            }
        });
    	if("${creditEntrustPlanConfig.capitalPlanId}" != "" && "${creditEntrustPlanConfig.capitalPlanId}" != "null"){
	    	$("#capitalPlanId").ZCombobox({
	            valueField: "id",
	            textField: "creditEntrustName",
	            url:'<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustListByCapitalistId" context="admin"/>&capitalistId=${creditEntrustPlanConfig.capitalistId}&jsoncallback=?',
	            value:'${creditEntrustPlanConfig.capitalPlanId}',
           		onSelect:function(value,text,index){
	                $('#capitalPlanId').val(value);
	                $('#capitalPlanName').val(text);
	            }
	        });
    	}
    	
    	//初始化
        $.ZUI.initForms("#entrustPlanConfigDialog");
    });
</script>
