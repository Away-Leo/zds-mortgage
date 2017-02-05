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
			<form id="terminal_change_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>编码：</dt>
							<dd class="detail">
								<label> <input class="zui-input" validate-type="Require,Length[0-15]" name="autoCode" disabled  id="autoCode" value="${terminal.autoCode}" >
								<input class="hidden " style="display:none"  name="id"  id="id" value="${terminal.id}" >
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端全称：</dt>
							<dd class="detail">
								<label> <input class="zui-input zui-validatebox" validate-type="Require,Length[1-15]" id="terminalFullName" value="${terminal.terminalFullName }"  name="terminalFullName">
								</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端类别：</dt>
							<dd class="detail">
									<input class="zui-combobox zui-validatebox" id="terminalType" name="terminalType" type="hidden" value="${terminal.terminalType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zdlb"
		                              data-valuefield="fullcode"  data-textfield="name" validate-type="Require">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">协议编号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-20]" id="dealNumber" name="dealNumber" value="${terminal.dealNumber }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">公司电话：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input toInt zui-validatebox" validate-type="Length[0-11]" id="companyTel" name="companyTel" value="${terminal.companyTel }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">月均产能量预测：</dt>
							<dd class="detail">
								<label>
		                           <input class="zui-input w170 toInt zui-validatebox "   validate-type="Digital[18-12]" id="monthOutAvgPredict" name="monthOutAvgPredict" value="${terminal.monthOutAvgPredict }" />
		                           <span class="word"> 单
		                        	</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">终端上月业务量：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input w170 toInt zui-validatebox "  id="lastMonthBusiness" validate-type="Digital[18-12]" name="lastMonthBusiness" value="${terminal.lastMonthBusiness }" />
		                            <span class="word">单 </span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title sptitle">终端平均每单贷款额度：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input w170 zui-validatebox toInt"   validate-type="Digital[18-12]" id="oneBillAvgVaule" name="oneBillAvgVaule" value="${terminal.oneBillAvgVaule }" />
		                            <span class="word">元 </span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">终端状态：</dt>
							<dd class="detail">
									<input class="zui-combobox zui-validatebox" id="terminalStatus" name="terminalStatus" type="hidden" value="${terminal.terminalStatus }"
			                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zdzt"
			                              data-valuefield="fullcode"  data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">终端等级：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox"  validate-type="Number,Length[0-3]" id="grade" name="grade" value="${terminal.grade }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端归属：</dt>
							<dd class="detail">
									<input class="zui-combobox zui-validatebox" id="belongType" data-width="94" name="belongType" type="hidden"  value="${terminal.belongType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=zdgs"
		                              data-valuefield="fullcode"  data-textfield="name" validate-type="Require">
		                         <label>
		                            <input class="zui-input width2 zui-validatebox" validate-type="Require,Length[1-30]" type="text" id="belongTypeName" name="belongTypeName" value="${terminal.belongTypeName }">
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">是否机构共用：</dt>
							<dd class="detail">
								<label>
									<input class="zui-combobox zui-validatebox" id="isBelongOrg" name="isBelongOrg" type="hidden"  value="${terminal.isBelongOrg }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=gggy"
		                              data-valuefield="fullcode"  data-textfield="name" >
		                        </label>
							</dd>
						</dl>
						
						<dl class="form-item">
							<dt class="title">返佣类型：</dt>
							<dd class="detail">
		                        	<input class="zui-combobox zui-validatebox" id="rebateType" data-width="94" name="rebateType" type="hidden"  value="${terminal.rebateType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=fylx" data-callback="rebate"
		                              data-valuefield="fullcode"  data-textfield="name" >
		                         <label >
		                            <input class="zui-input width2 zui-validatebox "  type="text" id="returnRate" validate-type="Digital[18-12]" name="returnRate" value="${terminal.returnRate }">
		                            <input class="zui-input width2 zui-validatebox " type="text" id="returnAmount" validate-type="Digital[18-12]" name="returnAmount" value="${terminal.returnAmount }">
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
						<dt class="title sptitle">是否允许发送手机短信：</dt>
							<dd class="detail">
								<label>
									<input class="zui-combobox zui-validatebox" id="isAllowPhoneMsg" name="isAllowPhoneMsg" type="hidden" data-multiple="false" value="${terminal.isAllowPhoneMsg }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">可接受返佣方式：</dt>
							<dd class="detail">
		                           <input class="zui-checkbox zui-validatebox" id="acceptRebateType" name="acceptRebateType" type="hidden"  value="${terminal.acceptRebateType }"
		                               data-multiple="true"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=fyfs"
		                               data-valuefield="id" data-textfield="text" >
							</dd>
						</dl>
						<dl class="form-item block">
		                    <dt class="title"><b class="c-red mr5">*</b>终端地址:</dt>
		      				<dd class="detail">
		                        <div id="selectAddress" data-code="${terminal.regionCode }">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type="Require" />
		                            <input id="regionCode" type="hidden" name="regionCode" value=""/>
		                        </div>
		                    </dd>
		                    <dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" id="cooperatorAddress"  validate-type="Require,Length[0-32]"  name="cooperatorAddress" value="${terminal.cooperatorAddress }" />
		                        </label>
							</dd>
		                </dl>
						<dl class="form-item block">
							<dt class="title">涉及业务：</dt>
							<dd class="detail">
		                            <input class="zui-checkbox zui-validatebox" id="businessScope" name="businessScope" type="hidden" 
		                               data-multiple="true" value="${terminal.businessScope }"
		                                data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=sjyw"
		                               data-valuefield="id" data-textfield="text">
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">合作要求和建议：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="cooperateSuggest" name="cooperateSuggest" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${terminal.cooperateSuggest }</textarea>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
						<dt class="title">没有继续合作原因：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="onContinueReason" name="onContinueReason" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${terminal.onContinueReason }</textarea>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">该终端的特殊说明：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="specialInstruction" name="specialInstruction" validate-type="Length[0-500]" placeholder="最多可以输入500个字符">${terminal.specialInstruction }</textarea>
		                        </label>
							</dd>
						</dl>
					</div>
					<div class="page-box">
						<div class="page-title">经营信息</div>
						<dl class="form-item">
						<dt class="title">成立时间：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-date zui-validatebox"    id="foundDate" value="${terminal.foundDateNm }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate'})">
		                            <input type="hidden" id="changeFoundDate" name="foundDate" value="${terminal.foundDate }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">主要出资人及股东：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Length[0-32]" id="mainShareholder" name="mainShareholder" value="${terminal.mainShareholder }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">员工数：</dt>
							<dd class="detail">
								<label>
									
		                            <input class="zui-input w170 toInt zui-validatebox"  validate-type="Number,Length[0-5]" id="staffNumber" name="staffNumber" value="${terminal.staffNumber }" />
		                            <span class="word">人 </span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">员工流动性：</dt>
							<dd class="detail">
								<label>
									<input class="zui-checkbox zui-validatebox" id="staffTurnover" name="staffTurnover" type="hidden" data-multiple="false"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=sors"
		                               value="${terminal.staffTurnover }" data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title sptitle">返佣是否给员工集体提成：</dt>
							<dd class="detail">
								<label>
									 <input class="zui-checkbox zui-validatebox" id="isGiveDeductMoney" name="isGiveDeductMoney" type="hidden" data-multiple="false" value="${terminal.isGiveDeductMoney }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=yorn"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">发放工资日期：</dt>
							<dd class="detail">
								<label>
								<span class="word">每月</span>
								<input  class="nwidth2 zui-validatebox toInt   zui-input"  validate-type="Number,Size[1-31]" id="wageday" name="wageday" value="${terminal.wageday }" /> 
								<span class="word">日 </span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">月均经营成本：</dt>
							<dd class="detail">
								<label>
		                       
		                           	<input class="zui-input w170 toInt zui-validatebox "  validate-type="Digital[18-12]" id="monthManageCost" name="monthManageCost" value="${terminal.monthManageCost }" />
		                           	 <span class="word">元</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">注册资本：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input w170 toInt zui-validatebox "   validate-type="Digital[18-12]" id="registeredCapital" name="registeredCapital" value="${terminal.registeredCapital }" />
		                            <span class="word">元</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">办公场所面积：</dt>
							<dd class="detail">
								<label>
		                           <input class="zui-input w170 toInt zui-validatebox "   validate-type="Digital[18-12]" id="workArea" name="workArea" value="${terminal.workArea }" />
		                           <span class="word">㎡</span>
		                        </label>
							</dd>
						</dl>
					</div>
				</form>
					<div class="page-box">
						<div class="page-title">其他信息</div>
						<div class="p10">
						    <div class="info-tab">
						        <ul class="tabs" id="info-tabs">
						        	 <li class="tabs-on" formatter="contactsInfo"><a href="javascript:void(0);">联系人资料</a></li>
						            <li formatter="cooperatorBank"><a href="javascript:void(0);">合作银行</a></li>
						            <li formatter="receiveAccount" class=""><a href="javascript:void(0);">返佣账户</a></li>
						            <li formatter="terminalHistory"><a href="javascript:void(0);">终端维护记录</a></li>
						        </ul>
						        <div class="tabcontents" id="info-tabcontents">
						        	<div  id="showContactsInfo">
					                </div>
					                <div class="hide" id="showCooperatorBank">
					                </div>
					                <div class="hide" id="showReceiveAccount">
					                </div>
					                <div class="hide" id="showTerminalHistory">
					                </div>
						        </div>
						    </div>
						</div>
					</div>
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
			 var receive=true;
             var contacts=true;
             var bank=true;
             var terminal=true;
			$(document).ready(function(){
				
				var type = $("#rebateType").val();
				if(type == 'fylx001'){
					$("#returnRate").show();
					$("#returnAmount").hide();
				}else if(type == 'fylx002'){
					$("#returnRate").hide();
					$("#returnAmount").show();
				}else{
					$("#returnRate").hide();
					$("#returnAmount").hide();
				}
			});		
			
         	var url='<z:ukey key="com.zdsoft.finance.cooperator.initContactsInfo" context="admin"/>&terminalId=${terminal.id}';
        	if(contacts){
            	$('#showContactsInfo').load(url,function(){
            		contacts=false;
            	});
        	}
			//联系人资料
			CALLBACK.rebate=function(index,data){
				if(index == 'fylx001'){
					$("#returnRate").show();
					$("#returnAmount").hide();
				}else if(index == 'fylx002'){
					$("#returnRate").hide();
					$("#returnAmount").show();
				}else{
					$("#returnRate").hide();
					$("#returnAmount").hide();
				}
        	}
			//联系人资料
			CALLBACK.contactsInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initContactsInfo" context="admin"/>&terminalId=${terminal.id}';
            	if(contacts){
                	$('#showContactsInfo').load(url,function(){
                		contacts=false;
                	});
            	}
            }     
			 //合作银行
            CALLBACK.cooperatorBank=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initCooperatorBank" context="admin"/>&terminalId=${terminal.id}';
            	if(bank){
                	$('#showCooperatorBank').load(url,function(){
                		bank=false;
                	});
            	}
            }
			 //返佣账户
            CALLBACK.receiveAccount=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initReceiveAccount" context="admin"/>&terminalId=${terminal.id}';
            	if(receive){
                	$('#showReceiveAccount').load(url,function(){
                		receive=false;
                	});
            	}
            }
			 //终端维护记录
            CALLBACK.terminalHistory=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initTerminalHistory" context="admin"/>&terminalId=${terminal.id}';
            	if(terminal){
                	$('#showTerminalHistory').load(url,function(){
                		terminal=false;
                	});
            	}
            }
            $("#selectAddress").Address({
            	showStreet:false,//不显示街道
            	cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
                callback:function(infos,selected_ids){
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
        	$.ZUI.init();
			 
			$('#saveMeet').click(function(){
				var isValidate = $.ZUI.validateForm($('#terminal_change_form'));
				if(isValidate){
					var param = $('#terminal_change_form').serialize();
					$.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.cooperator.save" context="admin"/>',
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