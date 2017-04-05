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
			                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[1-20]" id="enterpriseType" type="hidden" name="enterpriseType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0023"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.enterpriseType }">
       						</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>合作单位名称：</dt>
							<dd class="detail">
							<label>
									<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
									id="companyName" name="companyName" value="${otherCooperaterVo.companyName }">
       						</label>
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">简称：</dt>
			                <dd class="detail">
			                    <input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="shortName" name="shortName" value="${otherCooperaterVo.shortName }">
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>类别：</dt>
							<dd class="detail">
			                    <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-20]" id="companyType" type="hidden" name="companyType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00120"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.companyType }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">上级名称：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="parentOrg" name="parentOrg" value="${otherCooperaterVo.parentOrg }">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>合作单位归属：</dt>
							<dd class="detail">
			                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-20]" id="companyBelong" type="hidden" name="companyBelong" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00104"
			                           data-valuefield="fullcode" data-textfield="name" data-width="94px" data-defaultvalue="${otherCooperaterVo.companyBelong }" data-callback="changeCode" />
							 <dd class="detail" id="ddbelongCode1">
		                     	<input class="zui-input width2 "  id="belongCode1" value="${otherCooperaterVo.companyBelongRelevanceName }">
		                     </dd>       
		                     <dd class="detail" id="ddbelongCode2">
		                     	<input class="zui-input width2 "  id="belongCode2" value="${otherCooperaterVo.companyBelongRelevanceName }">
	                     	    <input type="hidden" id="companyBelongRelevanceCode" name="companyBelongRelevanceCode" value="${otherCooperaterVo.companyBelongRelevanceCode }"/>	
		                        <input type="hidden" id="companyBelongRelevanceName" name="companyBelongRelevanceName" value="${otherCooperaterVo.companyBelongRelevanceName }"/>
		                     </dd> 
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
							<label>
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-32],PhoneOrMobile" 
									id="telephone" name="telephone" value="${otherCooperaterVo.telephone }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">传真：</dt>
							<dd class="detail">
							<label>
                               		<input class="zui-input zui-validatebox" validate-type="Phone" 
									id="fax" name="fax" value="${otherCooperaterVo.fax }">
									</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮政编码：</dt>
							<dd class="detail">
							<label>
									<input class="zui-input zui-validatebox" validate-type="Length[0-32],ZipCode" 
									id="zipCode" name="zipCode" value="${otherCooperaterVo.zipCode }">
									</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">网站：</dt>
							<dd class="detail">
							<label>
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-32],IsUrl" 
									id="website" name="website" value="${otherCooperaterVo.website }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用：</dt>
							<dd class="detail">
									<input class="zui-checkbox zui-validatebox" name="isStop" id="isStop" data-multiple="false" 
									data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
									data-valuefield="id" value="${otherCooperaterVo.isStop }"
									data-textfield="text" validate-type="Require" style="display: none;" type="hidden">
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">地址：</dt>
                             <dd class="detail">
                             	<input type="hidden" id="addProvince" name="addProvince" value="${otherCooperaterVo.addProvince }"/>
	                            <input type="hidden" id="addCity" name="addCity" value="${otherCooperaterVo.addCity }"/>
	                            <input type="hidden" id="addCountry" name="addCountry" value="${otherCooperaterVo.addCountry }"/>
                             	<div id="selectAddress" data-code="${otherCooperaterVo.addCountry }">
                             		<label>
			                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 190px;" validate-type=""/>
		                            </label>
		                        </div>
                             </dd>
                            <dd class="detail">
                            	<label>
                               		<input class="zui-input zui-validatebox" style="width: 640px;" validate-type="Length[1-128]" 
									id="detailedAddress" name="detailedAddress" value="${otherCooperaterVo.detailedAddress }" >
                            	</label>
							</dd>
						</dl>
					</div>
					<div class="page-box">
						<div class="page-title">明细信息</div>
						<dl class="form-item">
							<dt class="title">成立时间：</dt>
							<dd class="detail">
								<input type="text" class="zui-date zui-validatebox strToDate" readonly id="foundDate" value="${otherCooperaterVo.foundDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeFoundDate',maxDate:'${maxDate}'})">
		                        <input type="hidden" id="changeFoundDate" name="foundDate" value="${otherCooperaterVo.foundDate }"/>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">法人：</dt>
							<dd class="detail">
							<label>
									<input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="legalPerson" name="legalPerson" value="${otherCooperaterVo.legalPerson }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">税号：</dt>
			                <dd class="detail">
			                <label>
			                    <input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="taxNo" name="taxNo" value="${otherCooperaterVo.taxNo }">
							</label>
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">银行账号：</dt>
							<dd class="detail">
							<label>
								<input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="bankAccount" name="bankAccount" value="${otherCooperaterVo.bankAccount }">
							</label>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">行业：</dt>
							<dd class="detail">
			                    <input class="zui-combotree zui-validatebox" validate-type="Length[0-20]" id="industry" type="hidden" name="industry" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0021"
				                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${otherCooperaterVo.industry }">
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">备注：</dt>
							<dd class="detail">
							<label>
								<textarea class="zui-area zui-validatebox" validate-type="Length[0-512]" id="remark" name="remark" placeholder="最多可以输入512个字符">${otherCooperaterVo.remark }</textarea>
							</label>
							</dd>
						</dl>
					</div>
		        </form>
		            <div class="form-btn">
	                	<button id="submitClient" type="button" class="btn-blue" >保存</button>
	                	<button id="cancleBtn" type="button" class="btn-blue" >返回</button>
	                </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	var uri_get_all_emp = '<z:res resource="essential.comm.employees.select" isDefault="true"/>' + "&jsoncallback=?";
	var uri_get_all_post_select = '<z:res resource="ess.post.find-all-select" isDefault="true"/>' + "&jsonCallBack=?";  
	var uri_all_org_tree = '<z:res resource="enssential.org.findOrgToTree" isDefault="true"/>' + "&jsoncallback=?";
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py','zd/completer','common/zds-common-selecter'], 
			function($, CALLBACK) {
			
			// 暂时去掉模糊查询
// 			var data1 = '${data}';
// 			var data2 = '${data2}';
// 			// 部门选择
// 			$("#belongCode1").completer({
// 	            suggest: true,//默认false
// 	            idField: 'code',//默认id,唯一标识字段
// 	            nameField: 'name',//默认name,下拉列表展示数据的字段
// 	            valueField: 'py',//默认value,根据值查询数据的字段
// 	            source:data1,
// 	            writable: false,//默认false，是否可自定义输入
// 	            placeObj:$("ddbelongCode1"),//悬浮框需要定位到的对象
// 	            complete: function (data) {
// 	                $('#companyBelongRelevanceName').val(data.name);
// 	                $('#companyBelongRelevanceCode').val(data.code);
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
// 	            source:data2,
// 	            writable: false,//默认false，是否可自定义输入
// 	            placeObj:$("ddbelongCode2"),//悬浮框需要定位到的对象
// 	            complete: function (data) {
// 	                $('#companyBelongRelevanceName').val(data.name);
// 	                $('#companyBelongRelevanceCode').val(data.code);
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
			
			$("#belongCode1").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "org", singleSelect: true,onlyLeafCheck:false,
		        onOk: function (data) {
		        	$('#belongCode1').val(data[0].text);
		        	$('#companyBelongRelevanceCode').val(data[0].id);
		        	$('#companyBelongRelevanceName').val(data[0].text);
		     },onBeforeOpen:function(){  
		         //重新获取列表中的值
		         return true;
		    }});
			
			$("#belongCode2").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: true,
		        onOk: function (data) {   
		        	$('#belongCode2').val(data[0]._data.empNm);
		        	$('#companyBelongRelevanceCode').val(data[0]._data.empCd);
		        	$('#companyBelongRelevanceName').val(data[0]._data.empNm);
		     },onBeforeOpen:function(){ 
		         //重新获取列表中的值
		         return true;
		     }});
			
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
                            		ZDS_MESSAGE_CLIENT.refreshOpenner();
                                    setTimeout(function(){
                                   		ZDS_MESSAGE_CLIENT.closeSelf();
                                    },200);
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
			
			// 取消按钮
			$('#cancleBtn').click(function(){
				ZDS_MESSAGE_CLIENT.closeSelf();
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
                    $('#addProvince').val(selected_ids[0]);
                    $('#addCity').val(selected_ids[1]);
                    $('#addCountry').val(selected_ids[2]);
                }
            });
			$.ZUI.init();
			
			$(function(){
				var belongType = $('#companyBelong').ZCombobox('getValue');
				if("YWDM0010403" == belongType){ // 人员
					$('#belongCode2').show();
					$('#belongCode1').hide();
				}else{ // 非人员
					$('#belongCode2').hide();
					$('#belongCode1').show();
				}
			});
		});
	</script>
</body>
</html>