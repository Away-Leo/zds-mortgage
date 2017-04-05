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
								<input class="zui-combobox zui-validatebox" id="capitalistType"  name="capitalistType" type="hidden" value="${capitalist.capitalistType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00112"
		                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" validate-type="Require">
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用：</dt>
							<dd class="detail">
									<input class="zui-checkbox zui-validatebox" id="isStop" name="isStop" type="hidden" data-multiple="false" value="${capitalist.isStop == null ? 'YWDM0049001': capitalist.isStop}"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		                               data-valuefield="id" data-textfield="text" validate-type="Require">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">类别：</dt>
							<dd class="detail">
								<label> 
								<input class="zui-combobox zui-validatebox" id="capitalistCategory"  name="capitalistCategory" type="hidden" value="${capitalist.capitalistCategory }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00112"
		                              data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>资方名称：</dt>
							<dd class="detail">
								<label> 
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]"  id="capitalName" name ="capitalName" value="${capitalist.capitalName}" >
									<input type="hidden" name="id" id="id" value="${capitalist.id}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">简称：</dt>
							<dd class="detail">
								<label> <input class="zui-input zui-validatebox" validate-type="Length[0-32]" name="capitalShortName"  id="capitalShortName" value="${capitalist.capitalShortName}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
								<label> <input class="zui-input zui-validatebox" validate-type="PhoneOrMobile" name="telephone"  id="telephone" value="${capitalist.telephone}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item block">
		                    <dt class="title">地区及详细地址:</dt>
		                    <dd class="detail">
		                        <div id="selectAddress" data-code="${capitalist.addCountry }">
		                            <input id="region" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 200px;" />
		                        </div>
		                        <input type="hidden" name="addProvince" id="addProvince" value="${capitalist.addProvince  }"/>
		                        <input type="hidden" name="addCity" id="addCity" value="${capitalist.addCity  }"/>
		                        <input type="hidden" name="addCountry" id="addCountry" value="${capitalist.addCountry  }"/>
		                    </dd>
		                    <dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-64]" style="width:610px;" id="address" name="address" value="${capitalist.address }" />
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
		                            <input class="zui-input zui-validatebox"    id="foundDate" value="${capitalist.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate',maxDate:'${maxDate}'})">
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
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-32]" id="dutyParagraph" name="dutyParagraph" value="${capitalist.dutyParagraph }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">银行帐号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Number,Length[0-20]" id="bankAccount" name="bankAccount" value="${capitalist.bankAccount }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">行业：</dt>
							<dd class="detail">
								<label>
									 <input class="zui-combotree zui-validatebox" id="industry" name="industry" type="hidden" data-multiple="false" value="${capitalist.industry }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0021"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">是否有银行协议：</dt>
							<dd class="detail">
								<label>
								 <input class="zui-checkbox zui-validatebox" id="isHasBankAgreement" name="isHasBankAgreement" type="hidden" data-multiple="false" value="${capitalist.isHasBankAgreement }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
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
		                        <div class="zd-area">
			                    	<span class="zd-curval">0</span>/<span class="zd-maxval">512</span>
			                 	</div>
							</dd>
						</dl>
					</div>
				</form>
	            <div class="form-btn">
                	<button id="saveCapital" type="button" class="btn-blue">保存</button>
                </div>
			</div>
		</div>
	</div>
	<div id="zds_btn_btn-add"></div>
	<div id="zds_btn_btn-addMember"></div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], 
	function($, CALLBACK, Switch,Loading) {
			$.ZUI.init();
        	$('#capitalistType').data("choose","disable");
        	
        	// 如果为查看，则修改为查看框
        	var operateType = "${operationType }";
       		if(operateType == 'view'){
       			$("#saveMeet").hide();
       			
       			$("#saveMeet").hide();
       			$('#isStop').data("choose","disable");
       			$('#capitalistCategory').data("choose","disable");
       			$('#capitalName').attr("disabled","disabled");
       			$('#capitalShortName').attr("disabled","disabled");
       			$('#telephone').attr("disabled","disabled");
       			$('#region').data("choose","disable");
       			$('#address').attr("disabled","disabled");
       			$('#foundDate').attr("disabled","disabled");
       			$('#legalPerson').attr("disabled","disabled");
       			$('#dutyParagraph').attr("disabled","disabled");
       			$('#bankAccount').attr("disabled","disabled");
       			$('#industry').data("choose","disable");
       			$('#remark').attr("disabled","disabled");
       			$('#isHasBankAgreement').attr("disabled","disabled");
       			$('#saveCapital').hide();
       		}
       		
       		// 地址选择器
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
                    $('#addProvince').val(selected_ids[0]);
                    $('#addCity').val(selected_ids[1]);
                    $('#addCountry').val(selected_ids[2]);
                }
            });
			$('#saveCapital').click(function(){
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