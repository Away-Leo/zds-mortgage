<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="contactsEditDialogDiv" >
	    <div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" name="id"  id="id" value="${infoVo.id}" >
	            <input type="hidden" id="partnerId" name="partnerId" value="${terminalId}">
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>联系人名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input  zui-validatebox" validate-type="Require,Length[0-32]" name="linkman"  id="linkman" value="${infoVo.linkman}" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>办公固话/手机:</dt>
	               <dd class="detail">
	                	 <label>
	                    	<input class="zui-input  zui-validatebox" validate-type="Require,PhoneOrMobile" name="contactNumber"  id="contactNumber" value="${infoVo.contactNumber}" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title">职务:</dt>
	                <dd class="detail">
	                	 <label>
	                    	<input class="zui-input" name="duty"  id="duty" value="${infoVo.duty}" >
	                    </label>
	                </dd>
	            </dl>
	           
	        </form>
	    </div>
	</div>
	<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#contactsEditDialogDiv").Zdialog({
            width: 700, height: 300, closed: false, title: "${operationType eq 'add' ? '新增':operationType eq 'mod'? '编辑' :'查看'}联系人",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	var contactForm = $('#contactForm').serialize();
                    	var isValidate = $.ZUI.validateForm($('#contactForm'));
        				if(isValidate){
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.cooperator.contactsInfo.save" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#contacts_datagrid_view').ZTable("reload", {});
                                    	$("#contactsEditDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#contactsEditDialogDiv").Zdialog("close");
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
                    	$("#contactsEditDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	$(document).ready(function(){
    		var type = "${operationType}";
        	if(type == 'view'){
        		$('#linkman').attr("disabled","disabled");
        		$('#contactNumber').attr("disabled","disabled");
        		$('#duty').attr("disabled","disabled");
        		$("#zds_btn_message-btn").hide();
        	}
    	});
    	//初始化
        $.ZUI.init();
    });
</script>