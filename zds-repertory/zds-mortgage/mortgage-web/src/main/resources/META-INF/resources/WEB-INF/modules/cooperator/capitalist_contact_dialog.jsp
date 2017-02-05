<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="contactsDialogDiv"  >
	    <div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" name="id"  id="infoid" value="${infoVo.id}" >
	            <input type="hidden" id="capitalistId" name="capitalistId" value="${capitalistId}">
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>联系人名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input zui-validatebox" name="contactName" validate-type="Require,Length[0-15]"  id=contactName value="${infoVo.contactName}" >
	                    </label>
	                </dd>
	            </dl>
	             <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>电话:</dt>
	                <dd class="detail">
	                	 <label>
	                    	<input class="zui-input zui-validatebox"  validate-type="Require,Number,Length[0-11]"  name="contactTelNumber" id="contactTelNumberShow"  value="${infoVo.contactTelNumber}">
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title">联系类型:</dt>
	               <dd class="detail">
                              <input class="zui-combobox zui-validatebox" id="contactType" name="contactType" type="hidden" value="${infoVo.contactType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=pglxlx"
                              data-valuefield="fullcode"  
                              data-textfield="name" >
	                </dd>
	            </dl>
	           
	        </form>
	    </div>
	</div>
	<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#contactsDialogDiv").Zdialog({
    		width: 700, height: 300, closed: false, title: "${operationType eq 'add' ? '新增':operationType eq 'mod'? '编辑' :'查看'}资方联系方式",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	var isValidate = $.ZUI.validateForm($('#InfoDialog'));
        				if(isValidate){
                    	var contactForm = $('#contactForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.contact.save" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#contacts_datagrid_view').ZTable("reload", {});
                                    	$("#contactsDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#contactsDialogDiv").Zdialog("close");
                                }
                            });
        				}
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#contactsDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	//初始化
    	$.ZUI.initForms("#InfoDialog");
    	$(document).ready(function(){
    		var type = "${operationType}";
        	if(type == 'view'){
        		$('#contactName').attr("disabled","disabled");
        		$("#contactTelNumberShow").attr("disabled","disabled");
        		$("#contactType").data("choose","disable");
        		$("#zds_btn_message-btn").hide();
        	}
    	});
    	
    });
</script>