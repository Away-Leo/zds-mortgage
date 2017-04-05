<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<div id="approvalOptionDialog">
    <div id="optionDialog">
        <form id="addApprovalOptionForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" id="productId" name="productVo.id" value="${productId }">
            <input type="hidden" id="approvalOpinionId" name="id" value="${approvalOpinion.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>审批类型:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${approvalOpinion.approvalType }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00124"
	                        data-valuefield="fullcode" data-textfield="name" data-callback="approvalType" name="approvalType">
	             </dd>
	        </dl>
            <dl class="form-item" id="mortgageOrderDl">
	             <dt class="title"><b class="c-red mr5">*</b>抵押顺位:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" value="${approvalOpinion.mortgageOrder }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0052"
	                        data-valuefield="fullcode" data-textfield="name" name="mortgageOrder">
	             </dd>
	         </dl>
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>是否启用:</dt>
	             <dd class="detail">
	                 <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                         data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="${approvalOpinion.isEnable == null ? true : approvalOpinion.isEnable }"
                         data-valuefield="id" data-textfield="text" name="isEnable">
	             </dd>
	         </dl>
	         <br>
	         <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>审批用语:</dt>
	             <dd class="detail">
	                 <label>
	                 	<textarea class="zui-area zui-validatebox" name="remark" validate-type="Require,Length[0-500]" placeholder="最多可以输入500个字符">${approvalOpinion.remark }</textarea>
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
    seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.table'], function ($,CALLBACK) {
    	
    	CALLBACK.approvalType = function(value,text){
    		if(value == "YWDM0012402"){
    			$("#mortgageOrderDl").hide();
    			$("#addApprovalOptionForm input[name='mortgageOrder']").attr("validate-type","");
    		}else{
    			$("#mortgageOrderDl").show();
    			$("#addApprovalOptionForm input[name='mortgageOrder']").attr("validate-type","Require");
    		}
    	};
    	
    	$("#approvalOptionDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "审批意见配置",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addApprovalOptionForm'));
                    	if(flag){
                    		var addApprovalOptionForm = $('#addApprovalOptionForm').serializeArray();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.approvalOpinion.saveOrUpdate" context="admin"/>',
                                data: addApprovalOptionForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#tb-approvalOpinion').ZTable("reload",{});
                                    	$("#approvalOptionDialog").Zdialog("close");
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
                                	$("#approvalOptionDialog").Zdialog("close");
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
                    	$("#approvalOptionDialog").Zdialog("close");
                    }
                }
            ]
        });
    	//初始化
        $.ZUI.initForms("#optionDialog");
    });
</script>
