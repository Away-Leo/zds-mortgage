<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>客户信息查看</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
					<div class="page-box">
			            <div class="page-title">客户信息</div>
			            <div class="p5">
						<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
							<input type="hidden" id="customerId" name="id" value="${postLoanPersonalVo.id }">
			                <table class="table-detail">
			                    <tr>
			                        <td class="td-title pct10">姓名</td>
			                        <td class="pct20">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <input class="zui-input" disabled="disabled"
												id="customerName" name="customerName" value="${postLoanPersonalVo.customerName }">
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title pct10">曾用名</td>
			                        <td class="pct20">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input" disabled="disabled"
													id="formerName" name="formerName" value="${postLoanPersonalVo.formerName }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title pct10"></td>
			                        <td class="pct30" rowspan="4">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                </dd>
			                            </dl>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="td-title">证件类型</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="credentialType" name="credentialType" value="${postLoanPersonalVo.credentialType }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title">证件号码</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input" disabled="disabled"
													id="credentialNo" name="credentialNo" value="${postLoanPersonalVo.credentialNo }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                    </tr>
			                    <tr>
			                        <td class="td-title">性别</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="gender" name="gender" value="${postLoanPersonalVo.gender }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title">出生日期</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
		                            			<input class="zui-input strToDate" disabled="disabled" id="birthdayDate" name="birthdayDate" value="${postLoanPersonalVo.birthdayDate }"/>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                    </tr>
			                    <tr>
			                    	<td class="td-title">年龄</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input" disabled="disabled"
													id="age" name="age" >
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title">婚况</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="maritalStatus" name="maritalStatus" value="${postLoanPersonalVo.maritalStatus }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                    </tr>
			                    <tr>
			                        <td class="td-title">职业类型</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="careerType" name="careerType" value="${postLoanPersonalVo.careerType }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title">教育程度</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="degree" name="degree" value="${postLoanPersonalVo.degree }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title">居住年限</td>
			                        <td colspan="3">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                    	<input class="zui-input" disabled="disabled"
													id="liveAge" name="liveAge" value="${postLoanPersonalVo.liveAge }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
		                        </tr>
		                        <tr>
			                        <td class="td-title">邮箱地址</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input" disabled="disabled"
													id="email" name="email" value="${postLoanPersonalVo.email }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                        <td>
			                        </td>
			                        <td class="td-title"></td>
			                        <td>
			                        </td>
		                        </tr>
			                </table>
						</form>
						<form id="live_form" class="zui-form " method="post" enctype="multipart/form-data">
							<table class="table-detail mt-1">
								<tr>
			                        <td class="td-title pct10">家庭地址</td>
			                        <td>
	                            		<dl class="form-item form-auto">
			                                <dd class="detail">
			                                	<input type="hidden" id="homeProvince" name="province" value=""/>
					                            <input type="hidden" id="homeCity" name="city" value=""/>
					                            <input type="hidden" id="homeDistrict" name="district" value=""/>
				                             	<div id="selectAddress" data-code="">
							                            <input id="region" class="zui-input zui-validatebox"  type="text" disabled="disabled" style="width: 190px;" validate-type="Require"/>
							                            <!-- <input id="liveCode" type="hidden" name="liveCode" value=""/> -->
						                        </div>
						                        <input type="hidden" name="addressType" value="t0930"/>
						                        <input type="hidden" id="liveId" name="id" value=""/>
				                             </dd>
				                            <dd class="detail">
				                            		<label>
					                               		<input class="zui-input" disabled="disabled" 
														id="liveAddress" name="liveAddress" style="width: 394%;">
													</label>
											</dd>
										</dl>
			                        </td>
			                    </tr>
							</table>
						</form>
						<form id="domicile_form" class="zui-form " method="post" enctype="multipart/form-data">
							<table class="table-detail mt-1">
								<tr>
			                        <td class="td-title pct10">户籍地址</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                	<input type="hidden" id="liveProvince" name="province" value=""/>
					                            <input type="hidden" id="liveCity" name="city" value=""/>
					                            <input type="hidden" id="liveCounty" name="district"  value=""/>
				                             	<div id="selectAddresss" data-code="">
							                            <input id="regions" class="zui-input zui-validatebox"  type="text" disabled="disabled" style="width: 190px;" validate-type="Require"/>
		                            					<!-- <input id="domicileCode" type="hidden" name="domicileCode" value=""/> -->
						                        </div>
						                        <input type="hidden" name="addressType" value="t0931"/>
						                        <input type="hidden" id="domicileId" name="id" value=""/>
				                             </dd>
				                            <dd class="detail">
				                            		<label>
					                               		<input class="zui-input" disabled="disabled" 
														id="domicileAddress" name="domicileAddress" style="width: 394%;">
													</label>
											</dd>
										</dl>
			                        </td>
			                    </tr>
							</table>
						</form>
			            </div>
        			</div>
				<form id="marital_form" class="zui-form " method="post" enctype="multipart/form-data" <c:if test="${postLoanPersonalVo.maritalStatus == '已婚' || postLoanPersonalVo.maritalStatus == '初婚' || postLoanPersonalVo.maritalStatus == '再婚' || postLoanPersonalVo.maritalStatus == '复婚'}">style="display:inline"</c:if><c:if test="${postLoanPersonalVo.maritalStatus != '已婚' && postLoanPersonalVo.maritalStatus != '初婚' && postLoanPersonalVo.maritalStatus != '再婚' && postLoanPersonalVo.maritalStatus != '复婚'}">style="display:none"</c:if>>
					<input type="hidden" id="maritalId" name="id" value="">
					<div class="page-box">
						<div class="page-title">配偶信息</div>
						<div class="p5">
						<table class="table-detail">
							<tr>
		                        <td class="td-title pct10">配偶姓名</td>
		                        <td class="pct20">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <input class="zui-input" disabled="disabled"
											id="maritalclientNm" name="customerName" >
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title pct10">曾用名</td>
		                        <td class="pct20">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input" disabled="disabled"
												id="maritalformerNm" name="formerName" >
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title pct10"></td>
		                        <td class="pct30" rowspan="3">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                </dd>
		                            </dl>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="td-title">证件类型</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                    	<input class="zui-input" disabled="disabled"
													id="maritalcredentialType" name="credentialType">
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title">证件号码</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input" 
												id="maritalcredentialNo" name="credentialNo" disabled="disabled">
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"></td>
		                    </tr>
		                    <tr>
		                        <td class="td-title">邮箱地址</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input" disabled="disabled"
												id="maritalemail" name="email" >
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title">职业类型</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                    	<input class="zui-input" disabled="disabled"
													id="maritalcareerType" name="careerType">
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"></td>
		                    </tr>
		                    
	                    </table>
	                    </div>
					</div>					
		        </form>
		            <div class="page-box">
						<div class="page-title">联系方式</div>
						<div class="p10">
					        <div id="tb-contact" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.postLoanContact.findByClientId" context="admin"/>&jsoncallback=?&customerId|E|S=${postLoanPersonalVo.id}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:customerName,width:20%">姓名</th>
							            <th data-options="field:contactType,width:25%">联系类型</th>
							            <th data-options="field:phoneNumber,width:25%">电话号码</th>
							            <th data-options="field:id,width:15%" formatter="contactView">操作</th>
							        </tr>
							        </thead>
							    </table>
							</div>
						</div>
					</div>
					<div class="page-box">
						<div class="page-title">工作单位信息</div>
						<div class="p10">
					        <div id="tb-unit" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.postLoanWorkUnit.findByClientId" context="admin"/>&jsoncallback=?&customerId|E|S=${postLoanPersonalVo.id}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:workUnitName,width:6%">姓名</th>
							            <th data-options="field:companyName,width:10%">工作单位名称</th>
							            <th data-options="field:industryType,width:10%">行业类型</th>
							            <th data-options="field:industry,width:10%">行业</th>
							            <th data-options="field:workUnitNature,width:6%">单位性质</th>
							            <th data-options="field:position,width:6%">职务</th>
							            <th data-options="field:workYears,width:2%">工作年限(年)</th>
							            <th data-options="field:phoneNumber,width:8%">工作单位电话</th>
							            <th data-options="field:workUnitAddress,width:25%">单位住址</th>
							            <th data-options="field:id,width:20%" formatter="unitView">操作</th>
							        </tr>
							        </thead>
							    </table>
							</div>
						</div>
					</div>
		            <div class="form-btn">
	                	<button id="submitClient" type="button" class="btn-blue" >保存</button>
	                </div>
			</div>
		</div>
	</div>
					
					<!-- 查看联系方式弹出框 -->
	                <div  id="contactEditDialog" style = "display: none">
						<div class="p10">
					        <form id="contactedit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="contactIdEdit" name="id" value="">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled" 
											id="customerNameEdit" name="customerName">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">联系类型：</dt>
									<dd class="detail">
										<input class="zui-input" disabled="disabled"
											id="contactTypeEdit" name="contactType">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">电话号码：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled"
											value="" id="contactPhoneNumberEdit" name="phoneNumber" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 查看工作单位弹出框 -->
					<div  id="unitEditDialog" style = "display: none">
						<div class="p10">
					        <form id="unitedit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="unitIdEdit" name="id" value="">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled" 
											id="workUnitNameEdit" name="workUnitName">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位名称：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled"
											value="" id="companyNameEdit" name="companyName" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业类型：</dt>
									<dd class="detail">
										<input class="zui-input" disabled="disabled"
											id="industryTypeEdit" name="industryType">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业：</dt>
									<dd class="detail">
										<input class="zui-input" disabled="disabled"
											id="industryEdit" name="industry">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位性质：</dt>
									<dd class="detail">
										<input class="zui-input" disabled="disabled"
											id="workUnitNatureEdit" name="workUnitNature">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作年限(年)：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled"
											value="" id="workYearsEdit" name="workYears" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">职务：</dt>
									<dd class="detail">
										<input class="zui-input" disabled="disabled"
											id="positionEdit" name="position">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位电话：</dt>
									<dd class="detail">
											<input class="zui-input" disabled="disabled"
											value="" id="unitPhoneNumberEdit" name="phoneNumber" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位地址：</dt>
									<dd class="detail">
											<input type="hidden" id="workUnitProvinceEdit" name="province" value=""/>
				                            <input type="hidden" id="workUnitCityEdit" name="city" value=""/>
				                            <input type="hidden" id="workUnitDistrictEdit" name="district" value=""/>
											<div id="selectWorkUnitAddressEdit" data-code="">
						                            <input id="workUnitRegionsEdit" class="zui-input zui-validatebox"  type="text" style="width:190px;" disabled="disabled" validate-type=""/>
	                            					<!-- <input id="workUnitCodeEdit" type="hidden" name="workUnitCode" value=""/> -->
					                        </div>
									</dd>
									<dd class="detail">
										<input class="zui-input" disabled="disabled" 
										value="" id="workUnitAddressEdit" name="workUnitAddress" style="width: 232%;">
									</dd>
								</dl>
					        </form>
						</div>
					</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			function gerAddress(){
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.postLoanAddress.findByCustomerNameAndCustomerId" context="admin"/>',
                    data: {customerName:'${postLoanPersonalVo.customerName }' , customerId:'${postLoanPersonalVo.id }',addressType:'t0930' },
                    dataType: 'json',
                    success: function (result) {
                        if (result.resultStatus == 0) {
                        	var data = result.optional.postLoanAddressVo;
                    		if(data){
                    			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
            					if(data.id ){
            						$("#selectAddress").setAddress({
            							showStreet:false,//不显示街道
            							addressDisabled:true,//默认可修改地址信息，true为不可修改
            							cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
            							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
            							data:data.district,
            			                callback:function(infos,selected_ids) {
            			                    var str = '';
            			                    for(var i=0;i<infos.length;i++) {
            			                        if(str==""){
            			                            str = str+infos[i];
            			                        }else{
            			                            str = str+" / "+infos[i];
            			                        }
            			                    }
            			                    $('#region').val(str);
            			                    $('#liveProvince').val(selected_ids[0]);
            		                        $('#liveCity').val(selected_ids[1]);
            		                        $('#liveCounty').val(selected_ids[2]);
            			                }
            			            });
            						$("#liveAddress").val(data.liveAddress);
            						$("#liveId").val(data.id);
            					}
            				}
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
                    }
                });
				
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.postLoanAddress.findByCustomerNameAndCustomerId" context="admin"/>',
                    data: {customerName:'${postLoanPersonalVo.customerName }' , customerId:'${postLoanPersonalVo.id }',addressType:'t0931' },
                    dataType: 'json',
                    success: function (result) {
                        if (result.resultStatus == 0) {
                        	var data = result.optional.postLoanAddressVo;
                    		if(data){
                    			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
            					if(data.id ){
            						$("#selectAddresss").setAddress({
            							showStreet:false,//不显示街道
            							addressDisabled:true,//默认可修改地址信息，true为不可修改
            							cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
            							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
            			                data:data.district,
            			                callback:function(infos,selected_ids) {
            			                    var str = '';
            			                    for(var i=0;i<infos.length;i++) {
            			                        if(str==""){
            			                            str = str+infos[i];
            			                        }else{
            			                            str = str+" / "+infos[i];
            			                        }
            			                    }
            			                    $('#regions').val(str);
            			                    $('#homeProvince').val(selected_ids[0]);
            			                    $('#homeCity').val(selected_ids[1]);
            			                    $('#homeDistrict').val(selected_ids[2]);
            			                }
            			            });
            						$("#domicileAddress").val(data.domicileAddress);
            						$("#domicileId").val(data.id);
            					}
            				}
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
                    }
                });
			};
			
			//根据客户id查询配偶信息
			function getMarital(){
				var clientId = '${postLoanPersonalVo.id }';
				var maritalStatus = '${postLoanPersonalVo.maritalStatus }';
				if(maritalStatus == '已婚' || maritalStatus == '初婚' || maritalStatus == '再婚' || maritalStatus == '复婚'){
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.cnfh.customer.findMaritalCustomerByCustomerId" context="admin"/>',
	                    data: {clientId:clientId},
	                    dataType: 'json',
	                    success: function (result) {
	                        if (result.resultStatus == 0) {
	                        	var data = result.optional.maritalCustomerVo;
	                    		if(data){
	            					if(data.id ){
	            						//如果附属人已存在，给附属人赋值
	            						$('#maritalId').val(data.id);
	            						$("#maritalclientNm").val(data.customerName);
	            						$("#maritalformerNm").val(data.formerName);
	            						$("#maritalcredentialType").val(data.credentialType);
	            						$("#maritalcredentialNo").val(data.credentialNo);
	            						$("#maritalcareerType").val(data.careerType);
	            						$("#maritalemail").val(data.email);
	            						$("#maritalImgId").val(data.attachmentId);
	            					}
	            				}
	                        }
	                    },
	                    error: function () {
	                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
	                    }
	                });
				}
			};
			
			function getAge(){
				var credentialType = '${postLoanPersonalVo.credentialType }';
				if(credentialType != '身份证' ){
					var birthday = '${postLoanPersonalVo.birthdayDate }';
					if(birthday != '' && birthday != null){
						var year = birthday.substr(0, 4);
						var date = new Date();
						var dateyear = date.getFullYear();
						var age = dateyear - year;
						$("#age").val(age);
					}else{
						$("#age").val('');
					}
				}else{
					var credentialNo = '${postLoanPersonalVo.credentialNo }';
					var year = credentialNo.substr(6, 4);
					var date = new Date();
					var dateyear = date.getFullYear();
					var age = dateyear - year;
					$("#age").val(age);
				}
			};
			
			//联系方式操作格式化
	        CALLBACK.contactView=function(rowData,index){
	        	var data='<a class="icon-btn31 handler-icon c-orange" title="查看" onclick="contactViewDialog"></a>';
	        	return data;
	        }
			
	      //联系方式编辑弹出框打开
			CALLBACK.contactViewDialog = function(index, row){
   				$("#contactIdEdit").val(row.id);
               	$("#customerNameEdit").val(row.customerName);
   				$("#contactTypeEdit").val(row.contactType);
   				$("#contactPhoneNumberEdit").val(row.phoneNumber);
				$("#contactEditDialog").Zdialog("open");
			};
			
			$("#contactEditDialog").Zdialog({
				width : 700,
				height : 250,
				closed : true,
				title:"查看联系方式",
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					buttonCls : 'btn-gray',
					handler : function() {
						$("#contactEditDialog").Zdialog("close");
					}
				}]
			});
			
	      	//工作单位操作格式化
	        CALLBACK.unitView=function(rowData,index){
	        	var data='<a class="icon-btn31 handler-icon c-orange" title="查看" onclick="unitViewDialog"></a>';
	        	return data;
	        }
	      	
	      //工作单位查看弹出框打开
			CALLBACK.unitViewDialog = function(index, row){
				$("#unitIdEdit").val(row.id);
            	$("#workUnitNameEdit").val(row.workUnitName);
				$("#companyNameEdit").val(row.companyName);
				$("#industryTypeEdit").val(row.industryType);
				$("#industryEdit").val(row.industry);
				$("#workUnitNatureEdit").val(row.workUnitNature);
				$("#workYearsEdit").val(row.workYears);
				$("#positionEdit").val(row.position);
				$("#unitPhoneNumberEdit").val(row.phoneNumber);
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.postLoanWorkUnit.findPostLoanWorkUnitById" context="admin"/>',
                    data: {id : row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
            				$("#selectWorkUnitAddressEdit").setAddress({
    							showStreet:false,//不显示街道
    							addressDisabled:true,//默认可修改地址信息，true为不可修改
    							cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
    							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
    							data:data.optional.postLoanWorkUnitVo.district,
    			                callback:function(infos,selected_ids) {
    			                    var str = '';
    			                    for(var i=0;i<infos.length;i++) {
    			                        if(str==""){
    			                            str = str+infos[i];
    			                        }else{
    			                            str = str+" / "+infos[i];
    			                        }
    			                    }
    			                    $('#workUnitRegionsEdit').val(str);
    			                    $('#workUnitProvinceEdit').val(selected_ids[0]);
    		                        $('#workUnitCityEdit').val(selected_ids[1]);
    		                        $('#workUnitDistrictEdit').val(selected_ids[2]);
    			                    $("#workUnitAddressEdit").val(data.optional.postLoanWorkUnitVo.workUnitAddress);
    			                }
    			            });
                        }else{
                          	$.ZMessage.error("错误", data.msg, function () {
		                    });
                        }
                    },
                    error: function () {
                      	$.ZMessage.error("错误", "查询联系方式系统异常，请联系管理员", function () {
	                    });
                    }
                });
		  		$("#unitEditDialog").Zdialog("open");
			};
			
			$("#unitEditDialog").Zdialog({
				width : 700,
				height : 350,
				closed : true,
				title:"查看工作单位",
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					buttonCls : 'btn-gray',
					handler : function() {
						$("#unitEditDialog").Zdialog("close");
					}
				}]
			});
			
			//居住地址
			$("#selectAddress").Address({
				showStreet:false,//不显示街道
				cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
	            addressDisabled:true,//默认可修改地址信息，true为不可修改
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
                    $('#homeProvince').val(selected_ids[0]);
                    $('#homeCity').val(selected_ids[1]);
                    $('#homeDistrict').val(selected_ids[2]);
                }
            });
			
			//户籍地址
			$("#selectAddresss").Address({
				showStreet:false,//不显示街道
				cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
	            addressDisabled:true,//默认可修改地址信息，true为不可修改
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
                    $('#regions').val(str);
                    $('#liveProvince').val(selected_ids[0]);
                    $('#liveCity').val(selected_ids[1]);
                    $('#liveCounty').val(selected_ids[2]);
                }
            });
			
			//工作单位编辑地址
			$("#selectWorkUnitAddressEdit").Address({
				showStreet:false,//不显示街道
				cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
				getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
	            addressDisabled:true,//默认可修改地址信息，true为不可修改
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
                    $('#workUnitRegionsEdit').val(str);
                    $('#workUnitProvinceEdit').val(selected_ids[0]);
                    $('#workUnitCityEdit').val(selected_ids[1]);
                    $('#workUnitDistrictEdit').val(selected_ids[2]);
                }
            });
			
			$.ZUI.init();
			
			getMarital();
			
			getAge();
			
			gerAddress();
			
		});
		
	</script>
</body>
</html>