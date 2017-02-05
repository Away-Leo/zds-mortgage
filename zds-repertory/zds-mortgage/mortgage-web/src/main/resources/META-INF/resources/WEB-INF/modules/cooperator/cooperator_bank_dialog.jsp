<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="bankDialogDiv">
	    <div id="InfoDialog">
	        <form id="bankFromTable" class="zui-form mt15"  >
	        	<input type="hidden" name="id"  id="id" value="${infoVo.id}" >
	            <input type="hidden" id="terminalId" name="terminalId" value="${terminalId}">
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>开户银行名称:</dt>
	               <dd class="detail">
	                	 <label>
	                    	<input class="zui-input  zui-validatebox" name="bankName"  validate-type="Require,Length[1-20]" id="bankName" value="${infoVo.bankName}" >
	                    </label>
	                </dd>
	            </dl>
	            
	        </form>
	    </div>
	</div>
	<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	
    	$("#bankDialogDiv").Zdialog({
            width: 700, height: 300, closed: false, title: "${operationType eq 'add' ? '新增':operationType eq 'mod'? '编辑' :'查看'}合作银行",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	var contactForm = $('#bankFromTable').serialize();
                    	var isValidate = $.ZUI.validateForm($('#InfoDialog'));
        				if(isValidate){
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.cooperator.cooperatorBank.save" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#bank_datagrid_view').ZTable("reload", {});
                                    	$("#bankDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#bankDialogDiv").Zdialog("close");
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
                    	$("#bankDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	$(document).ready(function(){
    		var type = "${operationType}";
        	if(type == 'view'){
        		$('#bankName').attr("disabled","disabled");
        		$("#zds_btn_message-btn").hide();
        	}
    	});
    	//初始化
        $.ZUI.init();
    });
</script>