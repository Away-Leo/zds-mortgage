<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="questionDialog">
    <div id="dialog">
        <form id="addQuestionForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>类型:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="sceneCd"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=questionSceneCd"
	                        data-valuefield="fullcode" data-textfield="name" name="sceneCd">
	             </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.seleter'], function ($) {
    	$("#questionDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "场景设置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addQuestionForm'));
                    	if(flag){
                    		var sceneCd = $('#sceneCd').val();
                    		var jsonStr = '${jsonStr}';
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.questionScene.saveOrUpdate" context="admin"/>',
                                data: {'sceneCd':sceneCd,'jsonStr':jsonStr},
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$("#questionDialog").Zdialog("close");
                                    	$.ZMessage.success("提示", "保存成功", function () {
            	                            setTimeout(function(){
            	                             ZDS_MESSAGE_CLIENT.refreshOpenner();
            	                           	 ZDS_MESSAGE_CLIENT.closeSelf();
            	                            },200);
                    	                    $(".zd-message").ZWindow("close");
                    	                });
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
                                	$("#questionDialog").Zdialog("close");
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
                    	$("#questionDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.initForms("#dialog");
    });
</script>
