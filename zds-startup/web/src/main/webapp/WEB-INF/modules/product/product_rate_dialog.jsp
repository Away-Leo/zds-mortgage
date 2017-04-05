<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../common/common_js.jsp" %>

<div id="productRateDialog">
    <div id="ratedialog">
        <form id="addProductRateForm" class="zui-form mt15" action="javascript:void(0);"
              zdata-options="{}">
            <input type="hidden" id="productId" name="productId" value="${productId }">
            <input type="hidden" id="productRateId" name="id" value="${productRate.id }">
            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>贷款利率:</dt>
	             <dd class="detail">
	                 <label>
	                     <input type="text" class="zui-input nwidth2 zui-validatebox" validate-type="Require,Digital[2-2]" name="rate" value="${productRate.rate }"/>
	                 </label>
	             </dd>
	             <dd class="detail">
	             	<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" data-width="78" value="${productRate.rateUnit }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00119"
	                        data-valuefield="fullcode" data-textfield="name" name="rateUnit">
	          </dd>
	         </dl>
	         <br>
	            <dl class="form-item">
	             <dt class="title"><b class="c-red mr5">*</b>贷款期限范围:</dt>
	             <dd class="detail">
	                 <label>
	                     <input type="text" class="zui-input nwidth2 zui-validatebox" validate-type="Require,Integer" name="startDate" value="${productRate.startDate }"/>
	                 </label>
	             </dd>
	             <dd class="detail">
	             	<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" data-width="78" value="${productRate.startDateUnit }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931"
	                        data-valuefield="fullcode" data-textfield="name" name="startDateUnit">
	          </dd>
	          <span class="word" style="margin-left: 25px;margin-right: 25px;">至</span>
	          <dd class="detail">
	                 <label>
	                     <input type="text" class="zui-input nwidth2 zui-validatebox" validate-type="Require,Integer" name="endDate" value="${productRate.endDate }"/>
	                 </label>
	             </dd>
	             <dd class="detail">
	             	<input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" data-width="78" value="${productRate.endDateUnit }"
	             	          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931"
	                        data-valuefield="fullcode" data-textfield="name" name="endDateUnit">
	          </dd>
	         </dl>
        </form>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#productRateDialog").Zdialog({
            width: 700, height: 340, closed: false, title: "产品利率",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                   	var flag=$.ZUI.validateForm($('#addProductRateForm'));
                    	if(flag){
                    		var addProductRateForm = $('#addProductRateForm').serializeArray();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.product.saveOrUpdateRate" context="admin"/>',
                                data: addProductRateForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#tb-productRate').ZTable("reload",{"loadProductId":$("#addProductRateForm input[name='productId']").val()});
                                    	$("#productRateDialog").Zdialog("close");
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
                                	$("#productRateDialog").Zdialog("close");
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
                    	$("#productRateDialog").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.initForms("#ratedialog");
    });
</script>
