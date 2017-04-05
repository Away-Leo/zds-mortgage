<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p9">
			<form id="terminal_change_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
						<div class="page-title">基本信息</div>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端编码：</dt>
							<dd class="detail">
								<label> <input class="zui-input zui-disabled" validate-type="Require,Length[1-64]" name="terminalCode" id="terminalCode" value="${terminal.terminalCode }" >
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
									<input class="zui-combotree zui-validatebox" id="terminalType" name="terminalType" type="hidden" value="${terminal.terminalType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00103"
		                              data-valuefield="fullcode" data-parent-value="false"  data-textfield="name" validate-type="Require">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">协议编号：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" alidate-type="Length[0-20]" id="dealNumber" name="dealNumber" value="${terminal.dealNumber }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">公司电话：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="PhoneOrMobile" id="companyTel" name="companyTel" value="${terminal.companyTel }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">月均产能量预测：</dt>
							<dd class="detail">
								<label>
		                           <span class="word"> <input class="zui-input zui-validatebox width2" style="width:170px;"  validate-type="Digital[18-12]" validate-false="请输入正确的数字" id="monthOutAvgPredict" name="monthOutAvgPredict" value="${terminal.monthOutAvgPredict }" />单
		                        	</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">终端等级：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" validate-type="Number,Length[0-3]" id="grade" name="grade" value="${terminal.grade }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端归属：</dt>
							<dd class="detail">
								 <input class="zui-combobox zui-validatebox" id="belongType" data-width="94" name="belongType" type="hidden"  value="${terminal.belongType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00104"
		                              data-valuefield="fullcode"  data-textfield="name" validate-type="Require" data-callback="changeCode">
                             </dd>
		                     <dd class="detail" id="ddbelongCode1">
		                     	<input class="zui-input width2 "  id="belongCode1" value="${terminal.belongRelevanceName }">
		                     </dd>       
		                    <dd class="detail" id="ddbelongCode2">
		                     	<input class="zui-input width2 "  id="belongCode2" value="${terminal.belongRelevanceName }">
	                     	    <input type="hidden" id="belongRelevanceCode" name="belongRelevanceCode" value="${terminal.belongRelevanceCode }"/>	
		                        <input type="hidden" id="belongRelevanceName" name="belongRelevanceName" value="${terminal.belongRelevanceName }"/>
		                     </dd>     
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>终端状态：</dt>
							<dd class="detail">
									<input class="zui-combobox zui-validatebox" id="terminalStatus" validate-type="Require" name="terminalStatus" type="hidden" value="${terminal.terminalStatus == null ? 'YWDM0016301' :terminal.terminalStatus}"
			                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00163"
			                              data-valuefield="fullcode"  data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">返佣类型：</dt>
							<dd class="detail">
		                        	<input class="zui-combobox zui-validatebox" id="rebateType" data-width="94" name="rebateType" type="hidden"  value="${terminal.rebateType }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00106" data-callback="rebate"
		                              data-valuefield="fullcode"  data-textfield="name" >
		                         <label >
		                            <input class="zui-input width2 zui-validatebox "  type="text" id="returnRate" validate-type="Digital[18-12]" validate-false="请输入正确的数字" name="returnRate" value="${terminal.returnRate }">
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
						<dt class="title sptitle">是否允许发送手机短信：</dt>
							<dd class="detail">
								<label>
									<input class="zui-combobox zui-validatebox" id="isAllowPhoneMsg" name="isAllowPhoneMsg" type="hidden" data-multiple="false" value="${terminal.isAllowPhoneMsg }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00107"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">是否机构共用：</dt>
							<dd class="detail">
								<label>
									<input class="zui-combobox zui-validatebox" id="isBelongOrg" name="isBelongOrg" type="hidden"  value="${terminal.isBelongOrg }"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		                              data-valuefield="fullcode"  data-textfield="name" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">可接受返佣方式：</dt>
							<dd class="detail">
		                           <input class="zui-checkbox zui-validatebox" id="acceptRebateType" name="acceptRebateType" type="hidden"  value="${terminal.acceptRebateType }"
		                               data-multiple="true"
		                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00108"
		                               data-valuefield="id" data-textfield="text" >
							</dd>
						</dl>
						<dl class="form-item block">
		                    <dt class="title"><b class="c-red mr5">*</b>终端地址：</dt>
		      				<dd class="detail">
		                        <div id="selectAddress" data-code="${terminal.addCounty }">
		                            <input id="region" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 200px;" validate-type="Require"/>
		                        </div>
		                        <input type="hidden" name="addProvince" id="addProvince" value="${terminal.addProvince  }"/>
		                        <input type="hidden" name="addCity" id="addCity" value="${terminal.addCity  }"/>
		                        <input type="hidden" name="addCounty" id="addCounty" value="${terminal.addCounty  }"/>
		                    </dd>
		                    <dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox" id="address"  validate-type="Require,Length[1-32]"  name="address" value="${terminal.address }" />
		                        </label>
							</dd>
		                </dl>
						<dl class="form-item block">
							<dt class="title">涉及业务：</dt>
							<dd class="detail">
		                            <input class="zui-checkbox zui-validatebox" id="businessScope" name="businessScope" type="hidden" 
		                               data-multiple="true" value="${terminal.businessScope }"
		                                data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00109"
		                               data-valuefield="id" data-textfield="text">
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">合作要求和建议：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="cooperateSuggest" name="cooperateSuggest" validate-type="Length[0-300]" placeholder="最多可以输入300个字符">${terminal.cooperateSuggest }</textarea>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
						<dt class="title">没有继续合作原因：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="onContinueReason" name="onContinueReason" validate-type="Length[0-300]" placeholder="最多可以输入300个字符">${terminal.onContinueReason }</textarea>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item block">
							<dt class="title">该终端的特殊说明：</dt>
							<dd class="detail">
								<label>
		                            <textarea class="zui-area zui-validatebox" id="specialInstruction" name="specialInstruction" validate-type="Length[0-300]" placeholder="最多可以输入300个字符">${terminal.specialInstruction }</textarea>
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
		                            <input class="zui-input zui-date zui-validatebox strToDate" id="foundDate" value="${terminal.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate',maxDate:'${maxDate}'})">
		                            <input type="hidden" id="changeFoundDate" name="foundDate" value="${terminal.foundDate }" />
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title sptitle">主要出资人及股东：</dt>
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
		                            <input class="zui-input nwidth2 zui-validatebox" style="width:170px;"  validate-type="Number,MinSize[1]" id="staffNumber" name="staffNumber" value="${terminal.staffNumber }" /><span class="word">人</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">员工流动性：</dt>
							<dd class="detail">
								<label>
									<input class="zui-checkbox zui-validatebox" id="staffTurnover" name="staffTurnover" type="hidden" data-multiple="false"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00110"
		                               value="${terminal.staffTurnover }" data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title sptitle">返佣是否给员工集体提成：</dt>
							<dd class="detail">
								<label>
									 <input class="zui-checkbox zui-validatebox" id="isGiveDeductMoney" name="isGiveDeductMoney" type="hidden" data-multiple="false" value="${terminal.isGiveDeductMoney }"
		                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		                               data-valuefield="id" data-textfield="text" >
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">发放工资日期：</dt>
							<dd class="detail">
								<label>
								<span class="word">每月</span><input  class="nwidth2 zui-input zui-validatebox" style="width:150px;"  validate-type="Number,Size[1-31]" id="wageday" name="wageday" value="${terminal.wageday }" /><span class="word">日</span> 
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">月均经营成本：</dt>
							<dd class="detail">
								<label>
		                           	<input class="zui-input zui-validatebox width2" validate-type="Digital[18-2],MinSize[1]" style="width:170px;" id="monthManageCost" name="monthManageCost" value="${terminal.monthManageCost }" /><span class="word">元</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">注册资本：</dt>
							<dd class="detail">
								<label>
		                            <input class="zui-input zui-validatebox width2" validate-type="Digital[18-2],MinSize[1]" style="width:170px;" id="registeredCapital" name="registeredCapital" value="${terminal.registeredCapital }" /><span class="word">元</span>
		                        </label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">办公场所面积：</dt>
							<dd class="detail">
								<label>
									<input class="zui-input zui-validatebox width2" validate-type="Digital[18-12]" validate-false="请输入正确的面积" style="width:170px;" id="workArea" name="workArea" value="${terminal.workArea }" /><span class="word">㎡</span>
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
	var uri_get_all_emp = '<z:res resource="essential.comm.employees.select" isDefault="true"/>' + "&jsoncallback=?";
	var uri_get_all_post_select = '<z:res resource="ess.post.find-all-select" isDefault="true"/>' + "&jsonCallBack=?";  
	var uri_all_org_tree = '<z:res resource="enssential.org.findOrgToTree" isDefault="true"/>' + "&jsoncallback=?";
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter', 'zd/completer','common/zds-common-selecter'], 
	function($, CALLBACK,Loading, Switch) {
			
			// 暂时去掉模糊查询
// 			var data1 = '${data}'; 
// 			var data2 = '${data2}';
// 			// 部门选择
// 			$("#belongCode1").completer({
// 	            suggest: true,//默认false
// 	            idField: 'code',//默认id,唯一标识字段
// 	            nameField: 'name',//默认name,下拉列表展示数据的字段
// 	            valueField: 'py',//默认value,根据值查询数据的字段
// 	            source: data1,
// 	            placeObj:$("#ddbelongCode1"),//悬浮框需要定位到的对象
// 	            writable: false,//默认false，是否可自定义输入
// 	            complete: function (data) {
// 	                $('#belongRelevanceName').val(data.name);
// 	                $('#belongRelevanceCode').val(data.code);
// 	            },
// 	            filter: function (val) {
// 	                return val;//过滤输入的value值
// 	            }

// 	        });
			
// 			// 人员选择
// 			$("#belongCode2").completer({
// 	            suggest: true,//默认false
// 	            idField: 'code',//默认id,唯一标识字段
// 	            nameField: 'name',//默认name,下拉列表展示数据的字段
// 	            valueField: 'py',//默认value,根据值查询数据的字段
// 	            source: data2,
// 	            placeObj:$("#ddbelongCode2"),//悬浮框需要定位到的对象
// 	            writable: false,//默认false，是否可自定义输入
// 	            complete: function (data) {
// 	                $('#belongRelevanceName').val(data.name);
// 	                $('#belongRelevanceCode').val(data.code);
// 	            },
// 	            filter: function (val) {
// 	                return val;//过滤输入的value值
// 	            }

// 	        });
			
 			CALLBACK.changeCode = function(index,rowData){
 				if("YWDM0010403" == index){ // 人员
 					$('#belongCode2').show();
 					$('#belongCode1').hide();
 				}else{ // 非人员
 					$('#belongCode2').hide();
 					$('#belongCode1').show();
 				}
 			};

			$.ZUI.init();
			
 			$(function(){
 				var belongType = $('#belongType').ZCombobox('getValue');
 				if("YWDM0010403" == belongType){ // 人员
 					$('#belongCode2').show();
 					$('#belongCode1').hide();
 				}else{ // 非人员
 					$('#belongCode2').hide();
 					$('#belongCode1').show();
 				}
 			});


			$("#belongCode1").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "org", singleSelect: true,onlyLeafCheck:false,
		        onOk: function (data) {
		        	$('#belongCode1').val(data[0].text);
		        	$('#belongRelevanceCode').val(data[0].id);
		        	$('#belongRelevanceName').val(data[0].text);
		     },onBeforeOpen:function(){  
		         //重新获取列表中的值
		         return true;
		    }});
			
			$("#belongCode2").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: true,
		        onOk: function (data) {   
		        	$('#belongCode2').val(data[0]._data.empNm);
		        	$('#belongRelevanceCode').val(data[0]._data.empCd);
		        	$('#belongRelevanceName').val(data[0]._data.empNm);
		     },onBeforeOpen:function(){ 
		         //重新获取列表中的值
		         return true;
		     }});
			
			
			$.ZUI.strToDate();
			
			var contacts = true;
			var bank = true;
			var account = true;
			var history = true;
			// 初始化联系人资料(默认显示)
         	var url='<z:ukey key="com.zdsoft.finance.cooperator.initContactsInfo" context="admin"/>&terminalId=${terminal.id}';
         	if(contacts){
               	$('#showContactsInfo').load(url,function(){
               		contacts = false;
               	});     		
         	}
    
        	
			// 联系人资料
			CALLBACK.contactsInfo=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initContactsInfo" context="admin"/>&terminalId=${terminal.id}';
            	if(contacts){
                   	$('#showContactsInfo').load(url,function(){
                   		contacts = false;
                   	});     		
             	}
            };  
			 // 合作银行
            CALLBACK.cooperatorBank=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initCooperatorBank" context="admin"/>&terminalId=${terminal.id}';
               	if(bank){
               		$('#showCooperatorBank').load(url,function(){
               			bank = false;
                   	});  		
             	}
            };
			 // 返佣账户
            CALLBACK.receiveAccount=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initBrokerageAccount" context="admin"/>&terminalId=${terminal.id}';
            	if(account){
            		$('#showReceiveAccount').load(url,function(){
            			account = false;
                   	});	
            	}
               	
            };
			 // 终端维护记录
            CALLBACK.terminalHistory=function(){
            	var url='<z:ukey key="com.zdsoft.finance.cooperator.initTerminalHistory" context="admin"/>&terminalId=${terminal.id}';
            	if(history){
            		$('#showTerminalHistory').load(url,function(){
            			history = false;
                   	});	
            	}
               	
            };
			 
         	//居住地址
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
                    $('#addProvince').val(selected_ids[0]);
                    $('#addCity').val(selected_ids[1]);
                    $('#addCounty').val(selected_ids[2]);
                }
            });
        	
			 
			$('#saveMeet').click(function(){
				var isValidate = $.ZUI.validateForm($('#terminal_change_form'));
				if(isValidate){
					var isAllowPhoneMsg = $('#isAllowPhoneMsg').ZCombobox('getValue');
					var rows = $('#contacts_datagrid_view').ZTable('getRows');
					if(('YWDM0010702' == isAllowPhoneMsg || 'YWDM0010703' == isAllowPhoneMsg) && (rows.length ==0)){
						$.ZMessage.warning("提示", "请添加联系人资料", function () {
       	                });
					}else{
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
				}
			});
		});
	</script>
</body>
</html>