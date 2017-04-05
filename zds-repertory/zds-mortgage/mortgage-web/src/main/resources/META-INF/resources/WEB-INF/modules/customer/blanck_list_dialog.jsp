<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div id="blanckListDialogs" class="page-box">
    <div class="p10" id="creatBlanckListDialog">
        <form id="blanckList_form" class="zui-form " method="post" enctype="multipart/form-data">
        	<dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
				<dd class="detail">
				<label>
						<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" id="blackName" name="blackName">
       			</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>证件类型：</dt>
				<dd class="detail">
						<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-20]" id="credentiaType" type="hidden" name="credentiaType" value=""
                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0025"
                           data-valuefield="fullcode" data-textfield="name"  data-callback="setattr">
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>证件号码：</dt>
				<dd class="detail">
				<label>
						<input class="zui-input zui-validatebox" validate-type="Require" 
						value="" id="credentialNo" name="credentialNo" >
       			</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>原因：</dt>
				<dd class="detail">
						<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-20]" id="reasonType" type="hidden" name="reasonType" value=""
                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0068"
                           data-valuefield="fullcode" data-textfield="name" >
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title"><b class="c-red mr5">*</b>来源：</dt>
				<dd class="detail">
						<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-20]" id="source" type="hidden" name="source" value=""
                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0069"
                           data-valuefield="fullcode" data-textfield="name" >
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">开始日期：</dt>
				<dd class="detail">
						<input type="text" class="zui-date zui-validatebox" id="startDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeStartDate',maxDate:'#F{$dp.$D(\'endDate\')}'})">
		                <input type="hidden" id="changeStartDate" name="startDate" />
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">结束日期：</dt>
				<dd class="detail">
						<input type="text" class="zui-date zui-validatebox" id="endDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeEndDate',minDate:'#F{$dp.$D(\'startDate\')}'})">
		                <input type="hidden" id="changeEndDate" name="endDate" />
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">工作单位：</dt>
				<dd class="detail">
				<label>
						<input class="zui-input zui-validatebox" validate-type="Length[0-128]" 
						value="" id="workUnit" name="workUnit" >
       			</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">工作电话：</dt>
				<dd class="detail">
				<label>
						<input class="zui-input zui-validatebox" validate-type="Length[1-11],PhoneOrMobile" 
						value="" id="workContact" name="workContact" >
       			</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">家庭电话：</dt>
				<dd class="detail">
				<label>
						<input class="zui-input zui-validatebox" validate-type="Length[1-11],PhoneOrMobile" 
						value="" id="familyContact" name="familyContact" >
       			</label>
				</dd>
			</dl>
			<br>
			<dl class="form-item">
	             <dt class="title">备注:</dt>
	             <dd class="detail">
	                 <label>
	                 	<textarea class="zui-area zui-validatebox" name="remark" validate-type="Length[0-500]" placeholder="最多可以输入500个字符"></textarea>
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
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.dialog'], function ($,CALLBACK) {
    	
    	CALLBACK.setattr = function(index,value){
			if(value == '身份证'){
				$("#credentialNo").attr('validate-type',"Require,Length[1-64],IDCard");
			}else{
				$("#credentialNo").attr('validate-type',"Require,Length[1-64]");
			}
		}
    	
    	//新增黑名单弹出框打开
        $("#blanckListDialogs").Zdialog({
			width : 700,
			height : 340,
			closed : false,
			title:"新增黑名单",
			isDestroy:true,
			buttons : [{
				id : 'message-btn',
				text : '确定',
				buttonCls: 'btn-blue',
				handler : function() {
					//校验黑名单输入数据
                    var Validate = $.ZUI.validateForm($('#blanckList_form'));
         			if(Validate){
         				var param = $('#blanckList_form').serialize();
         				//黑名单保存
         				$.ajax({
                             type: 'post',
                             url: '<z:ukey key="com.zdsoft.finance.blanckList.saveBlanckList" context="admin"/>',
                             data: param,
                             dataType: 'json',
                             success: function (data) {
                                 if (data.resultStatus == 0) {
                                 	 $.ZMessage.success("提示", "保存成功", function () {
                                 		$("#blanckListDialogs").Zdialog("close");
                                 		$("#tb-blanckList").ZTable("reload",{});
                                   	 });
                                 }else{
                                   	$.ZMessage.error("错误", data.msg, function () {
         		                    });
                                 }
                             },
                             error: function () {
                               	$.ZMessage.error("错误", "保存黑名单系统异常，请联系管理员", function () {
         	                    });
                             }
                         });
         			}
				}
			},{
				id : 'message-btn',
				text : '取消',
				buttonCls : 'btn-gray',
				handler : function() {
					$("#blanckListDialogs").Zdialog("close");
				}
			} ]
		});
    	
    	//初始化
        $.ZUI.initForms("#creatBlanckListDialog");
    	
        $("#blanckListDialogs").Zdialog("open");
    });
</script>
