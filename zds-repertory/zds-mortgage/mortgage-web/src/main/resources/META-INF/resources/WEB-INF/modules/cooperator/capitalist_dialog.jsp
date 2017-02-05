<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="capitalistDialogDiv">
	<div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" name="id"   value="${infoVo.id }" >
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>资方名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input  zui-validatebox"  validate-type="Require,Length[1-20]" name="cooperatorName"  id="cooperatorName" value="${infoVo.cooperatorName }" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>资方类型:</dt>
	               <dd class="detail">
                              <input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType" type="hidden" value="${infoVo.capitalistType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zflx"
                              data-valuefield="fullcode" 
                              data-textfield="name" validate-type="Require">
	                </dd>
	            </dl>
	        </form>
	    </div>
	  </div>
	  
	  
<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	
    	$("#capitalistDialogDiv").Zdialog({
            width: 700, height: 300, closed: false, title: "添加资方",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	var contactForm = $('#contactForm').serialize();
                    	var isValidate = $.ZUI.validateForm($('#InfoDialog'));
        				if(isValidate){
	                    	$("#capitalistDialogDiv").Zdialog("close");
	                    	var url = '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.tab" context="admin"/>&operationType=add&'+contactForm
	                    	ZDS_MESSAGE_CLIENT.openMenuLink('capitalist123_edit', '资方编辑', url);
        				}
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#capitalistDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.init();
    });
</script>
