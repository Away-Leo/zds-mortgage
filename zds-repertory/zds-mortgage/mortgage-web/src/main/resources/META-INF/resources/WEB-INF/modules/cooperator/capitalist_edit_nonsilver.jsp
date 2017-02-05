<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
</div>
	<div>
		<div class="page-box">
			<div class="p9">
			<form id="capitalist_change_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>资方类型：</dt>
							<dd class="detail">
								<label> 
								<input class="zui-combobox zui-validatebox" id="capitalistType" disabled name="capitalistType" type="hidden" value="${capitalist.capitalistType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zflx"
		                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
		                              <input type="hidden" name="id" id="id" value="${capitalist.id}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用：</dt>
							<dd class="detail">
									<input class="zui-checkbox zui-validatebox" id="isStop" name="isStop" type="hidden" data-multiple="false" value="${capitalist.isStop }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
		                               data-valuefield="id" data-textfield="text" validate-type="Require">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>资方名称：</dt>
							<dd class="detail">
								<label> <input class="zui-input" validate-type="Require,Length[0-15]" name="cooperatorName"  id="cooperatorName" value="${capitalist.cooperatorName}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">简称：</dt>
							<dd class="detail">
								<label> <input class="zui-input zui-validatebox" validate-type="Length[0-15]" name="cooperatorShortName"  id="cooperatorShortName" value="${capitalist.cooperatorShortName}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
								<label><input class="zui-input zui-validatebox" validate-type="Number,Length[0-11]" name="contactTelNumber"  id="contactTelNumber" value="${capitalist.contactTelNumber}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item block">
		                    <dt class="title">地区及详细地址:</dt>
		                    <dd class="detail">
		                        <div id="selectAddress" data-code="${capitalist.regionCode }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" />
		                            <input id="regionCode" type="hidden" name="regionCode" value="50,5002,500233,500233100"/>
		                        </div>
		                    </dd>
		                    <dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[1-64]" id="cooperatorAddress" name="cooperatorAddress" value="${capitalist.cooperatorAddress }" />
		                        </label>
							</dd>
		                </dl>
					</div>
					<div class="page-box">
						<div class="page-title">明细信息</div>
						<dl class="form-item">
							<dt class="title">成立时间：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"   id="foundDate" value="${capitalist.foundDateNm }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate'})">
		                            <input type="hidden" id="changeFoundDate" name="foundDate" value="${capitalist.foundDate }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">法人：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-32]" id="legalPerson" name="legalPerson" value="${capitalist.legalPerson }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">税号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-20]" id="dutyParagraph" name="dutyParagraph" value="${capitalist.dutyParagraph }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">银行帐号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Number,Length[0-20]" id="bankAccountShow" name="bankAccountShow" value="${capitalist.cooperatorBankVo.bankAccount }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">行业：</dt>
							<dd class="detail">
								<label>
									 <input class="zui-combobox zui-validatebox" id="industry" name="industry" type="hidden" data-multiple="false" value="${capitalist.industry }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=hangye"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">备注：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${capitalist.remark }</textarea>
		                        </label>
							</dd>
						</dl>
					</div>
				</form>
		            <div class="form-btn">
	                	<button id="saveMeet" type="button" class="btn-blue">保存</button>
	                </div>
			</div>
		</div>
	</div>
	<div id="zds_btn_btn-add"></div>
	<div id="zds_btn_btn-addMember"></div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
	function($, CALLBACK, Switch,Loading) {
			$.ZUI.init();
			 var operateType = "${operationType }";
			 if(operateType == 'add'){
	     			$("#isStop").val('yorn002');
	     		}
        	 $("#selectAddress").Address({
        		 showStreet:false,//不显示街道
        		 cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
				addressDisabled:operateType == 'view',//默认可修改地址信息，true为不可修改
                 callback:function(infos,selected_ids) {
                     var str = '';
                     var strCode = '';
                     for(var i=0;i<infos.length;i++) {
                         if(str==""){
                             str = str+infos[i];
                             strCode = strCode+selected_ids[i];
                         }else{
                             str = str+" / "+infos[i];
                             strCode = selected_ids[i];
                         }
                     }
                     $('#region').val(str);
                     $('#regionCode').val(strCode);
                 }
             });
        	
     			$('#capitalistType').data("choose","disable");
     			$('#cooperatorName').attr("disabled","disabled");
     		if(operateType == 'view'){
     			$("#saveMeet").hide();
    			$('#isStop').data("choose","disable");
    			$('#regionCode').data("choose","disable");
    			
    			$('#cooperatorAddress').attr("disabled","disabled");
    			$('#cooperatorShortName').attr("disabled","disabled");
    			
     			$('#contactTelNumber').attr("disabled","disabled");
     			$('#foundDate').attr("disabled","disabled");
     			$('#legalPerson').attr("disabled","disabled");
     			$('#dutyParagraph').attr("disabled","disabled");
     			$('#bankAccountShow').attr("disabled","disabled");
     			$('#industry').data("choose","disable");
     			$('#remark').attr("disabled","disabled");
     		}
			$('#saveMeet').click(function(){
				var isValidate = $.ZUI.validateForm($('#capitalist_change_form'));
				if(isValidate){
					var param = $('#capitalist_change_form').serialize();
					$.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.save" context="admin"/>',
                        data: param,
                        dataType: 'json',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                            	$.ZMessage.success("提示", "保存成功", function () {
                            		ZDS_MESSAGE_CLIENT.refreshOpenner();
	                                    setTimeout(function(){
	                                   	 ZDS_MESSAGE_CLIENT.closeSelf();
	                                    },200);
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
                        }
                    });
				}
			});
		});
	</script>
</body>
</html>