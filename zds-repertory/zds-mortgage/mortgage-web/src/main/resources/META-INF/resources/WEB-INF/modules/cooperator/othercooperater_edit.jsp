<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>其他合作单位编辑</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
				<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					<input type="hidden" id="id" name="id" value="${otherCooperaterVo.id }">
					<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>企业类型：</dt>
							<dd class="detail">
							<label>
			                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="companyType" type="hidden" name="companyType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=ct00100"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.companyType }">
       						</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>合作单位名称：</dt>
							<dd class="detail">
							<label>
									<input class="zui-input zui-validatebox" validate-type="Require,Length[0-64]" 
									id="contactCompanyName" name="contactCompanyName" value="${otherCooperaterVo.contactCompanyName }">
       						</label>
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">简称:</dt>
			                <dd class="detail">
			                    <input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="shortName" name="shortName" value="${otherCooperaterVo.shortName }">
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">类别：</dt>
							<dd class="detail">
			                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="type" type="hidden" name="type" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=t00100"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.type }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">上级名称：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="fatherName" name="fatherName" value="${otherCooperaterVo.fatherName }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>合作单位归属：</dt>
							<dd class="detail">
			                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="contactCompanyBelong" type="hidden" name="contactCompanyBelong" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=cb00100"
			                           data-valuefield="fullcode" data-textfield="name" data-width="94px" data-defaultvalue="${otherCooperaterVo.contactCompanyBelong }" data-callback="setValue">
			                <dd class="detail">
							<label>       
			                        <input class="zui-input nwidth2 zui-validatebox" validate-type="Require,Length[0-64]" 
									id="contactCompanyBelongName" name="contactCompanyBelongName" value="${otherCooperaterVo.contactCompanyBelongName }">
       						</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
							<label>
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-64],PhoneOrMobile" 
									id="callNumber" name="callNumber" value="${otherCooperaterVo.callNumber }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">传真：</dt>
							<dd class="detail">
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="foxNumber" name="foxNumber" value="${otherCooperaterVo.foxNumber }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮政编码：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-18],ZipCode" 
									id="postalCode" name="postalCode" value="${otherCooperaterVo.postalCode }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">网站：</dt>
							<dd class="detail">
							<label>
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-18],IsUrl" 
									id="website" name="website" value="${otherCooperaterVo.website }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用：</dt>
							<dd class="detail">
									<input class="zui-checkbox zui-validatebox" name="isStop" id="isStop" data-multiple="false" 
									data-data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]" data-valuefield="id" value="${otherCooperaterVo.isStop }"
									data-textfield="text" validate-type="Require" style="display: none;" type="hidden">
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">地址：</dt>
                             <dd class="detail">
                             	<input type="hidden" id="detailedProvince" name="detailedProvince" value="${otherCooperaterVo.detailedProvince }"/>
	                            <input type="hidden" id="detailedCity" name="detailedCity" value="${otherCooperaterVo.detailedCity }"/>
	                            <input type="hidden" id="detailedDistrict" name="detailedDistrict" value="${otherCooperaterVo.detailedDistrict }"/>
                             	<div id="selectAddress" data-code="${otherCooperaterVo.detailedDistrict }">
                             		<label>
			                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 190px;" validate-type=""/>
			                            <%-- <input id="detailedCode" type="hidden" name="detailedCode" value="${otherCooperaterVo.detailedCode }"/> --%>
		                            </label>
		                        </div>
                             </dd>
                            <dd class="detail">
                            	<label>
                               		<input class="zui-input zui-validatebox" validate-type="Length[1-200]" 
									id="detailedAddress" name="detailedAddress" value="${otherCooperaterVo.detailedAddress }" style="width: 466%;">
                            	</label>
							</dd>
						</dl>
					</div>
					<div class="page-box">
						<div class="page-title">明细信息</div>
						<dl class="form-item">
							<dt class="title">成立时间：</dt>
							<dd class="detail">
								<input type="text" class="zui-date zui-validatebox strToDate" readonly id="foundDate" value="${otherCooperaterVo.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate'})">
		                        <input type="hidden" id="changeFoundDate" name="foundDate" value="${otherCooperaterVo.foundDate }"/>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">法人：</dt>
							<dd class="detail">
							<label>
									<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="legalPerson" name="legalPerson" value="${otherCooperaterVo.legalPerson }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">税号:</dt>
			                <dd class="detail">
			                <label>
			                    <input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="dutyParagraph" name="dutyParagraph" value="${otherCooperaterVo.dutyParagraph }">
							</label>
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">银行账号：</dt>
							<dd class="detail">
							<label>
								<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="bankAccount" name="bankAccount" value="${otherCooperaterVo.bankAccount }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">行业：</dt>
							<dd class="detail">
			                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="industry" type="hidden" name="industry" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1818"
				                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.industry }">
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">备注：</dt>
							<dd class="detail">
							<label>
								<textarea class="zui-area zui-validatebox" validate-type="Length[0-3000]" id="remark" name="remark" placeholder="最多可以输入3000个字符">${otherCooperaterVo.remark }</textarea>
							</label>
							</dd>
						</dl>
					</div>
		        </form>
		            <div class="form-btn">
	                	<button id="submitClient" type="button" class="btn-blue" >保存</button>
	                </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			CALLBACK.setValue = function(index,value){
				var contactCompanyBelong = value;
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.otherCooperater.findContactCompanyBelongName" context="admin"/>',
                    data: {contactCompanyBelong:contactCompanyBelong},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        		$('#contactCompanyBelongName').val(data.optional.otherCooperaterVo.contactCompanyBelongName);
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "查询失败", function () {
                        });
                    }
                });
			}
			
			//保存
			$('#submitClient').click(function(){
				//校验
				var isValidate = $.ZUI.validateForm($('#client_form'));
				if(isValidate){
					var param = $('#client_form').serializeArray();
					//保存
					$.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.otherCooperater.saveOtherCooperater" context="admin"/>',
                        data: param,
                        dataType: 'json',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                            	$.ZMessage.success("提示", "保存成功", function () {
                             	 });
                            }else{
                            	$.ZMessage.error("错误", data.msg, function () {
		                        });
                            }
                        },
                        error: function () {
                        	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
	                        });
                        }
                    });
				}
			});
			
			$("#selectAddress").Address({
				showStreet:false,//不显示街道
				cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
                callback:function(infos,selected_ids) {
                    var str = '';
                    var strCode = '';
                    for(var i=0;i<infos.length;i++) {
                        if(str==""){
                            str = str+infos[i];
                            strCode = strCode+selected_ids[i];
                        }else{
                            str = str+" / "+infos[i];
                            strCode = strCode+","+selected_ids[i];
                        }
                    }
                    $('#region').val(str);
                    $('#detailedProvince').val(selected_ids[0]);
                    $('#detailedCity').val(selected_ids[1]);
                    $('#detailedDistrict').val(selected_ids[2]);
                }
            });
			$.ZUI.init();
			
   			
		});
	</script>
</body>
</html>