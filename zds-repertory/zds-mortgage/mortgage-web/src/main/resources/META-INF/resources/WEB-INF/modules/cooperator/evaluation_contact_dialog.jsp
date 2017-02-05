<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="evaluationContactDialogDiv">
	<div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" name="id"   value="${infoVo.id }" >
	        	<input type="hidden" name="evaluationId"  id="evaluationId" value="${evaluationId}" >
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>姓名:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input" name="contactName"  id="contactName" value="${infoVo.contactName }" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>联系类型:</dt>
	               <dd class="detail">
                              <input class="zui-combobox zui-validatebox" id="contactType" name="contactType" type="hidden" value="${infoVo.contactType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=pglxlx"
                              data-valuefield="fullcode" 
                              data-textfield="name" >
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>联系电话:</dt>
	                <dd class="detail">
	                	 <label>
	                    	<input class="zui-input" name="contactTelNumber"  id="contactTelNumber" value="${infoVo.contactTelNumber }" >
	                    </label>
	                </dd>
	            </dl>
	           
	        </form>
	    </div>
	  </div>
	  
	  
<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#evaluationContactDialogDiv").Zdialog({
            width: 700, height: 340, closed: false, title: "审批意见配置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var addApprovalOptionForm=$.ZUI.validateForm($('#contactForm'));
                    		var addApprovalOptionForm = $('#contactForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.contact.save" context="admin"/>',
                                data: addApprovalOptionForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#evaluation_contact_datagrid').ZTable("reload", {});
                                    	$("#evaluationContactDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#evaluationContactDialogDiv").Zdialog("close");
                                }
                            });
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#evaluationContactDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
    	$.ZUI.initForms("#InfoDialog");
    });
</script>
