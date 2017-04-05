<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
		<div class="page-box">
			<div class="p9">
			<form id="company_change_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>企业类型：</dt>
							<dd class="detail">
								<input class="hidden"  id="id"   name="id" value="${evaluationId}">
								<input class="zui-combobox zui-validatebox" id="companyType" name="companyType" type="hidden" value="${infoVo.companyType }"
	                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=pgqylb"
	                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
	                          </dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b> 评估公司名称：</dt>
							<dd class="detail">
								<label>
									<input class="zui-input zui-validatebox" validate-type="Require,Length[0-32]" id="companyName" value="${infoVo.companyName }"  name="companyName">
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>简称：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[1-32]" id="shortName" name="shortName" value="${infoVo.shortName }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>上级名称：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" id="fatherName" validate-type="Require,Length[0-10]" name="fatherName" value="${infoVo.fatherName }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>邮政编码：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[6-6]" id="postalcode" name="postalcode" value="${infoVo.postalcode }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>网站：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[0-32]" id="website" name="website" value="${infoVo.website }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用：</dt>
							<dd class="detail">
								<label>
									<input class="zui-checkbox zui-validatebox" id="isStop" name="isStop" type="hidden" data-multiple="false" value="${infoVo.isStop }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
		                               data-valuefield="id" data-textfield="text" validate-type="Require">
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>类别：</dt>
							<dd class="detail">
								<label>
		                           <input class="zui-checkbox zui-validatebox" id="evaluateType" name="evaluateType" type="hidden" data-multiple="false" value="${infoVo.evaluateType }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
		                               data-valuefield="id" data-textfield="text" validate-type="Require">
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
		                    <dt class="title"><b class="c-red mr5">*</b>地区及详细地址:</dt>
		                    <dd class="detail">
		                        <div id="selectAddress" data-code="${infoVo.regionCode }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type="Require"/>
		                            <input id="regionCode" type="hidden" name="regionCode" value="50,5002,500233,500233100"/>
		                        </div>
		                    </dd>
		                    <dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" id="address" name="address" value="${infoVo.address }" />
		                        </label>
							</dd>
		                </dl>
					</div>
				<div class="page-box">
						<div class="page-title">明细信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>成立时间：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"  validate-type="Require"  id="foundDate" value="${infoVo.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate'})">
		                            <input type="hidden" id="changeFoundDate" name="foundDate" value="${infoVo.foundDate }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>法人：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[0-32]" id="legalPerson" name="legalPerson" value="${infoVo.legalPerson }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>税号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[0-32]" id="dutyParagraph" name="dutyParagraph" value="${infoVo.dutyParagraph }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>银行帐号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[0-20]" id="bankAccount" name="bankAccount" value="${infoVo.bankAccount }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>行业：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Require,Length[1-20]" id="industry" name="industry" value="${infoVo.industry }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title"><b class="c-red mr5">*</b>备注：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="remark" name="remark" alidate-type="Require,Length[0-200]" placeholder="最多可以输入200个字符">${infoVo.remark }</textarea>
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
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.checkbox','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
	function($, CALLBACK, Switch,Loading) {
        	$.ZUI.init();
        	 $("#selectAddress").Address({
        		 showStreet:false,//不显示街道
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
                     $('#regionCode').val(strCode);
                 }
             });
			$('#saveMeet').click(function(){
				var isValidate = $.ZUI.validateForm($('#company_change_form'));
				if(isValidate){
					var param = $('#company_change_form').serialize();
					$.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.cooperator.evaluation.company.save" context="admin"/>',
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
			$(document).ready(function(){
        		if("${operationType }" == "view"){
            		$("#saveMeet").hide();
            		$('#shortName').attr("disabled","disabled");
            		$('#companyName').attr("disabled","disabled");
            		$('#fatherName').attr("disabled","disabled");
            		$('#postalcode').attr("disabled","disabled");
            		$('#website').attr("disabled","disabled");
            		$('#address').attr("disabled","disabled");
            		$('#region').attr("disabled","disabled");
            		$('#foundDate').attr("disabled","disabled");
            		$('#legalPerson').attr("disabled","disabled");
            		$('#dutyParagraph').attr("disabled","disabled");
            		$('#bankAccount').attr("disabled","disabled");
            		$('#industry').attr("disabled","disabled");
            		$('#remark').attr("disabled","disabled");
            		$("#isStop").data("choose","disable");
            		$("#evaluateType").data("choose","disable");
            		$("#companyType").data("choose","disable");
            	
            	}
        	});
		});
	</script>
