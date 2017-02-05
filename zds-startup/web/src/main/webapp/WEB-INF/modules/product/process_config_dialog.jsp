<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="processConfigDialog">
    <div id="configDialog">
        <form id="addProcessConfigForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" id="productId" name="productVo.id" value="${productId }">
            <input type="hidden" id="processConfigId" name="id" value="${processConfig.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>流程名称:</dt>
	             <dd class="detail">
	                 <label>
                        <input type="text" id="processName" class="zui-input zui-validatebox" validate-type="Require,Length[0-60]" name="processName" value="${processConfig.processName }"/>
                    </label>
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>代码:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${processConfig.processFormCd }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=processFormCd"
	                        data-valuefield="fullcode" data-textfield="name" name="processFormCd">
	             </dd>
	         </dl>
	         <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>流程Key:</dt>
	             <dd class="detail">
	                 <label>
                        <input type="text" id="processKey" class="zui-input zui-validatebox" validate-type="Require,Length[0-60]" name="processKey" value="${processConfig.processKey }"/>
                    </label>
	             </dd>
	        </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>是否启用:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                         data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="${processConfig.isEnable == null ? true : processConfig.isEnable }"
                         data-valuefield="id" data-textfield="text" name="isEnable">
	             </dd>
	         </dl>
	         <br>
	         <dl class="form-item">
	             <dt class="title">备注:</dt>
	             <dd class="detail">
	                 <label>
	                 	<textarea class="zui-area zui-validatebox" name="remark" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${processConfig.remark }</textarea>
		             </label>
		             <div class="zd-area">
	                    <span class="zd-curval">0</span>/<span class="zd-maxval">500</span>
	                 </div>
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#processConfigDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "流程配置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addProcessConfigForm'));
                    	if(flag){
                    		var addProcessConfigForm = $('#addProcessConfigForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.processConfig.saveOrUpdate" context="admin"/>',
                                data: addProcessConfigForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	var formArray=$("#queryProcessConfigForm").serialize();
                                    	formArray=decodeURIComponent(formArray, true);
                                    	$('#tb-processConfig').ZTable("reload", formArray);
                                    	$("#processConfigDialog").Zdialog("close");
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
                                	$("#processConfigDialog").Zdialog("close");
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
                    	$("#processConfigDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.initForms("#configDialog");
    });
</script>
