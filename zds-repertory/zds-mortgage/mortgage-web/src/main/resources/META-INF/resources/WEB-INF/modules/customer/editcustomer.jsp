<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>客户信息编辑</title>
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
			                        <td class="td-title pct10"><b class="c-red mr5">*</b>姓名</td>
			                        <td class="pct20">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                <label>
			                                    <input class="zui-input zui-validatebox" validate-type="Require,Length[1-128]" 
												id="customerName" name="customerName" value="${postLoanPersonalVo.customerName }">
	                                		</label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title pct10">曾用名</td>
			                        <td class="pct20">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input zui-validatebox" validate-type="Length[0-128]" 
													id="formerName" name="formerName" value="${postLoanPersonalVo.formerName }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title pct10">客户头像</td>
			                        <td class="pct30" rowspan="4">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                </dd>
			                            </dl>
			                        </td>
			                    </tr>
			                    <tr>
			                        <td class="td-title"><b class="c-red mr5">*</b>证件类型</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="credentiaType" type="hidden" name="credentiaType" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=060600"
							                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${postLoanPersonalVo.credentiaType }" data-callback="setattr">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"><b class="c-red mr5">*</b>证件号码</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" 
													id="credentialNo" name="credentialNo" onblur="checkClientCredentialNo()" value="${postLoanPersonalVo.credentialNo }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                    </tr>
			                    <tr>
			                        <td class="td-title"><b class="c-red mr5">*</b>性别</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="gender" type="hidden" name="gender" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g0156"
							                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${postLoanPersonalVo.gender }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"><b class="c-red mr5">*</b>出生日期</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                <label>
			                                    <input type="text" class="zui-date zui-validatebox strToDate" readonly validate-type="Require" value="${postLoanPersonalVo.birthdayDate }" id="birthday" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeBirthday'})" onblur="setAge()">
		                            			<input type="hidden" id="changeBirthday" name="birthdayDate" value="${postLoanPersonalVo.birthdayDate }"/>
	                               			</label>
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
			                                        <input class="zui-input" readonly="readonly"
													id="age" name="age" >
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"><b class="c-red mr5">*</b>婚况</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="maritalStatus" type="hidden" name="maritalStatus" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=x030300"
							                           data-valuefield="fullcode" data-textfield="name" data-callback="onselect" data-defaultvalue="${postLoanPersonalVo.maritalStatus }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"></td>
			                    </tr>
			                    <tr>
			                        <td class="td-title"><b class="c-red mr5">*</b>职业类型</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="careerType" type="hidden" name="careerType" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g050500"
							                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${postLoanPersonalVo.careerType }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"><b class="c-red mr5">*</b>教育程度</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="degree" type="hidden" name="degree" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
							                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${postLoanPersonalVo.degree }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
			                        <td class="td-title"><b class="c-red mr5">*</b>居住年限</td>
			                        <td colspan="3">
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="liveAge" type="hidden" name="liveAge" value=""
							                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=y00100"
							                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="${postLoanPersonalVo.liveAge }">
			                                    </label>
			                                </dd>
			                            </dl>
			                        </td>
		                        </tr>
		                        <tr>
			                        <td class="td-title"><b class="c-red mr5">*</b>邮箱地址</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                    <label>
			                                        <input class="zui-input zui-validatebox" validate-type="Require,Length[0-50],Email" 
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
			                        <td class="td-title pct10"><b class="c-red mr5">*</b>家庭地址</td>
			                        <td>
	                            		<dl class="form-item form-auto">
			                                <dd class="detail">
			                                	<input type="hidden" id="homeProvince" name="province" value=""/>
					                            <input type="hidden" id="homeCity" name="city" value=""/>
					                            <input type="hidden" id="homeDistrict" name="district" value=""/>
				                             	<div id="selectAddress" data-code="">
						                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 190px;" validate-type="Require"/>
						                            <!-- <input id="liveCode" type="hidden" name="liveCode" value=""/> -->
						                        </div>
						                        <input type="hidden" name="addressType" value="t0930"/>
						                        <input type="hidden" id="liveId" name="id" value=""/>
				                             </dd>
				                            <dd class="detail">
				                            		<label>
					                               		<input class="zui-input zui-validatebox" validate-type="Require,Length[1-500]" 
														id="liveAddress" name="liveAddress" >
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
			                        <td class="td-title pct10"><b class="c-red mr5">*</b>户籍地址</td>
			                        <td>
			                            <dl class="form-item form-auto">
			                                <dd class="detail">
			                                	<input type="hidden" id="liveProvince" name="province" value=""/>
					                            <input type="hidden" id="liveCity" name="city" value=""/>
					                            <input type="hidden" id="liveCounty" name="district"  value=""/>
				                             	<div id="selectAddresss" data-code="">
						                            <input id="regions" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 190px;" validate-type="Require"/>
	                            					<!-- <input id="domicileCode" type="hidden" name="domicileCode" value=""/> -->
						                        </div>
						                        <input type="hidden" name="addressType" value="t0931"/>
						                        <input type="hidden" id="domicileId" name="id" value=""/>
				                             </dd>
				                            <dd class="detail">
				                            		<label>
					                               		<input class="zui-input zui-validatebox" validate-type="Require,Length[0-500]" 
														id="domicileAddress" name="domicileAddress" >
													</label>
											</dd>
											<dd class="detail">
											<label>
			                               		<input class="zui-combobox" type="hidden"
			                               		data-data="[{'id':'homeDistrict','text':'复制家庭地址','isDefault':'true'}]"
			                               		data-valuefield="id" data-textfield="text" data-callback="copy">
			                               	</label>
											</dd>
										</dl>
			                        </td>
			                    </tr>
							</table>
						</form>
			            </div>
        			</div>
				<form id="marital_form" class="zui-form " method="post" enctype="multipart/form-data" <c:if test="${postLoanPersonalVo.maritalStatus == 'x0303002' || postLoanPersonalVo.maritalStatus == 'x0303003' || postLoanPersonalVo.maritalStatus == 'x0303004' || postLoanPersonalVo.maritalStatus == 'x0303005'}">style="display:inline"</c:if><c:if test="${postLoanPersonalVo.maritalStatus != 'x0303002' && postLoanPersonalVo.maritalStatus != 'x0303003' && postLoanPersonalVo.maritalStatus != 'x0303004' && postLoanPersonalVo.maritalStatus != 'x0303005'}">style="display:none"</c:if>>
					<input type="hidden" id="maritalId" name="id" value="">
					<div class="page-box">
						<div class="page-title">配偶信息</div>
						<div class="p5">
						<table class="table-detail">
							<tr>
		                        <td class="td-title pct10"><b class="c-red mr5">*</b>配偶姓名</td>
		                        <td class="pct20">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                <label>
		                                    <input class="zui-input zui-validatebox" validate-type="Require,Length[1-128]" 
											id="maritalclientNm" name="customerName" >
                               			</label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title pct10">曾用名</td>
		                        <td class="pct20">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input zui-validatebox" validate-type="Length[0-128]" 
												id="maritalformerNm" name="formerName" >
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title pct10">客户头像</td>
		                        <td class="pct30" rowspan="4">
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                </dd>
		                            </dl>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="td-title"><b class="c-red mr5">*</b>证件类型</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="maritalcredentiaType" type="hidden" name="credentiaType" value=""
						                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=060600"
						                           data-valuefield="fullcode" data-textfield="name" data-callback="setattrs">
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"><b class="c-red mr5">*</b>证件号码</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" 
												id="maritalcredentialNo" name="credentialNo" onblur="checkMaritalCredentialNo()">
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"></td>
		                    </tr>
		                    <tr>
		                        <td class="td-title"><b class="c-red mr5">*</b>邮箱地址</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-input zui-validatebox" validate-type="Require,Length[0-50],Email" 
												id="maritalemail" name="email" >
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"><b class="c-red mr5">*</b>职业类型</td>
		                        <td>
		                            <dl class="form-item form-auto">
		                                <dd class="detail">
		                                    <label>
		                                        <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="maritalcareerType" type="hidden" name="careerType" value=""
						                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g050500"
						                           data-valuefield="fullcode" data-textfield="name" >
		                                    </label>
		                                </dd>
		                            </dl>
		                        </td>
		                        <td class="td-title"></td>
		                    </tr>
		                    <tr>
	                    	<td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
		                    </tr>
	                    </table>
	                    </div>
					</div>					
		        </form>
		            <div class="page-box">
						<div class="page-title">联系方式</div>
						<div class="p10">
					        <div id="tb-contact" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.postLoanContact.findByClientId" context="admin"/>&jsoncallback=?&customerId|E|S=${postLoanPersonalVo.id}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#contact-function"}'>
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
							<div id="contact-function">
							    <a class="zui-toolbar" id="contact-add" text="新增" buttonCls="btn-blue" handler="contactAdd"></a>
						    </div>
						</div>
					</div>
					<div class="page-box">
						<div class="page-title">工作单位信息</div>
						<div class="p10">
					        <div id="tb-unit" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.postLoanWorkUnit.findByClientId" context="admin"/>&jsoncallback=?&customerId|E|S=${postLoanPersonalVo.id}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#unit-function"}'>
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
							<div id="unit-function">
							    <a class="zui-toolbar" id="unit-add" text="新增" buttonCls="btn-blue" handler="unitAdd"></a>
						    </div>
						</div>
					</div>
		            <div class="form-btn">
	                	<button id="submitClient" type="button" class="btn-blue" >保存</button>
	                </div>
			</div>
		</div>
	</div>
	
					<!-- 新增联系方式弹出框 -->
	                <div  id="contactDialog" style = "display: none">
						<div class="p10">
					        <form id="contact_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="contactId" name="id" value="">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											id="customerName" name="customerName">
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>联系类型：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="contactType" type="hidden" name="contactType" value=""
						                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=c0115"
						                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>电话号码：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15],PhoneOrMobile" 
											value="" id="contactPhoneNumber" name="phoneNumber" >
                           			</label>
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 编辑联系方式弹出框 -->
	                <div  id="contactEditDialog" style = "display: none">
						<div class="p10">
					        <form id="contactedit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="contactIdEdit" name="id" value="">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											id="customerNameEdit" name="customerName">
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>联系类型：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="contactTypeEdit" type="hidden" name="contactType" value=""
						                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=c0115"
						                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>电话号码：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15],PhoneOrMobile" 
											value="" id="contactPhoneNumberEdit" name="phoneNumber" >
                           			</label>
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 新增工作单位弹出框 -->
					<div  id="unitDialog" style = "display: none">
						<div class="p10">
					        <form id="unit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="unitId" name="id" value="">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											id="workUnitName" name="workUnitName">
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作单位名称：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											value="" id="companyName" name="companyName" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业类型：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="industryType" type="hidden" name="industryType" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=i00100"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="industry" type="hidden" name="industry" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1818"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>单位性质：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="workUnitNature" type="hidden" name="workUnitNature" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=w0121"
				                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作年限(年)：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Number" 
											value="" id="workYears" name="workYears" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>职务：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="position" type="hidden" name="position" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=p0126"
				                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位电话：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Length[1-15],PhoneOrMobile" 
											value="" id="unitPhoneNumber" name="phoneNumber" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位地址：</dt>
									<dd class="detail">
											<input type="hidden" id="workUnitProvince" name="province" value=""/>
				                            <input type="hidden" id="workUnitCity" name="city" value=""/>
				                            <input type="hidden" id="workUnitDistrict" name="district" value=""/>
											<div id="selectWorkUnitAddress" data-code="">
					                            <input id="workUnitRegions" class="zui-input zui-validatebox" style="width:190px;" type="text" readonly="true" style="width: 200px;" validate-type=""/>
                            					<!-- <input id="workUnitCode" type="hidden" name="workUnitCode" value=""/> -->
					                        </div>
									</dd>
									<dd class="detail">
										<input class="zui-input zui-validatebox" validate-type="Length[1-500]" 
										value="" id="workUnitAddress" name="workUnitAddress" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 编辑工作单位弹出框 -->
					<div  id="unitEditDialog" style = "display: none">
						<div class="p10">
					        <form id="unitedit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<input type="hidden" id="unitIdEdit" name="id" value="">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											id="workUnitNameEdit" name="workUnitName">
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作单位名称：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" 
											value="" id="companyNameEdit" name="companyName" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业类型：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="industryTypeEdit" type="hidden" name="industryType" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=i00100"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="industryEdit" type="hidden" name="industry" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1818"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>单位性质：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="workUnitNatureEdit" type="hidden" name="workUnitNature" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=w0121"
				                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作年限(年)：</dt>
									<dd class="detail">
									<label>
											<input class="zui-input zui-validatebox" validate-type="Require,Number" 
											value="" id="workYearsEdit" name="workYears" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>职务：</dt>
									<dd class="detail">
									<label>
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="positionEdit" type="hidden" name="position" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=p0126"
				                           data-valuefield="fullcode" data-textfield="name" >
                           			</label>
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位电话：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Length[1-15],PhoneOrMobile" 
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
						                            <input id="workUnitRegionsEdit" class="zui-input zui-validatebox" style="width:190px;" type="text" readonly="true" style="width: 200px;" validate-type=""/>
	                            					<!-- <input id="workUnitCodeEdit" type="hidden" name="workUnitCode" value=""/> -->
					                        </div>
									</dd>
									<dd class="detail">
										<input class="zui-input zui-validatebox" validate-type="Length[1-500]" 
										value="" id="workUnitAddressEdit" name="workUnitAddress" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			window.checkClientCredentialNo = function(){
				
				var credentialNo = $("#credentialNo").val();
				var credentiaType = $("#credentiaType").ZCombobox("getValue");
				var credentiaTypeText = $("#credentiaType").ZCombobox("getText");
				// 证件类型为身份证
				if(credentiaTypeText == '身份证'){
					// 根据身份证自动填充出生日期 和性别 和年龄
					if(credentialNo!=null && credentialNo!=""  && credentialNo.length==18){
						// 根据身份证自动填充出生日期
						$('#birthday').val(credentialNo.substr(6, 4)+'-'+credentialNo.substr(10, 2)+'-'+credentialNo.substr(12, 2));
						$('#changeBirthday').val(credentialNo.substr(6, 4)+credentialNo.substr(10, 2)+credentialNo.substr(12, 2));
						// 根据身份证获取性别
						var sex = "";
						if (parseInt(credentialNo.charAt(16) / 2) * 2 != credentialNo.charAt(16)){
							sex = '男';
							$("#gender").ZCombobox('setValue','g01561');
						}else{
							 sex = '女';
							 $("#gender").ZCombobox('setValue','g01562');
						}
						var year = credentialNo.substr(6, 4);
						var date = new Date();
						var dateyear = date.getFullYear();
						var age = dateyear - year;
						$("#age").val(age);
					}					
				}		
			};
			
			window.setAge = function(){
				var birth = $("#changeBirthday").val();
				var year = birth.substr(0, 4);
				var date = new Date();
				var dateyear = date.getFullYear();
				var age = dateyear - year;
				$("#age").val(age);
			}
			
			CALLBACK.setattr = function(index,value){
				if(value == '身份证'){
					$("#credentialNo").attr('validate-type',"Require,Length[1-64],IDCard");
				}else{
					$("#credentialNo").attr('validate-type',"Require,Length[1-64]");
				}
			}
			
			CALLBACK.setattrs = function(index,value){
				if(value == '身份证'){
					$("#maritalcredentialNo").attr('validate-type',"Require,Length[1-64],IDCard");
				}else{
					$("#maritalcredentialNo").attr('validate-type',"Require,Length[1-64]");
				}
			}
			
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
            							data:data.district,
            							cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
            							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
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
            			                data:data.district,
            			                cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
            			                getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
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
				if(maritalStatus == 'x0303002' || maritalStatus == 'x0303003' || maritalStatus == 'x0303004' || maritalStatus == 'x0303005'){
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.cnfh.customer.findMaritalByCustomerId" context="admin"/>',
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
	            						$("#maritalcredentiaType").ZCombobox('setValue',data.credentiaType);
	            						$("#maritalcredentialNo").val(data.credentialNo);
	            						$("#maritalcareerType").ZCombobox('setValue',data.careerType);
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
				var credentiaType = '${postLoanPersonalVo.credentiaType }';
				if(credentiaType != '06060001' ){
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
			
			//校验证件类型+证件号（配偶）
			window.checkMaritalCredentialNo = function(){
				
				var credentialNo = $("#maritalcredentialNo").val();
				var credentiaType = $("#maritalcredentiaType").ZCombobox("getValue");
				var credentiaTypeText = $("#maritalcredentiaType").ZCombobox("getText");
				
				if(credentialNo!=null && credentialNo!="" && credentiaType!=null) {
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.cnfh.customer.findByCredentiaTypeAndCredentialNo" context="admin"/>',
	                    data: {credentiaType:credentiaType,credentialNo:credentialNo},
	                    dataType: 'json',
	                    success: function (result) {
	                        if (result.resultStatus != 0) {
	                        	var data = result.optional.postLoanPersonalVo;
                        		if(data){
                        			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
                					if(data.id ){
                						//如果附属人已存在，给附属人赋值
                						$('#maritalId').val(data.id);
	            						$("#maritalclientNm").val(data.customerName);
	            						$("#maritalformerNm").val(data.formerName);
	            						$("#maritalcredentiaType").ZCombobox('setValue',data.credentiaType);
	            						$("#maritalcredentialNo").val(data.credentialNo);
	            						$("#maritalcareerType").ZCombobox('setValue',data.careerType);
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
			
			//判断主借人配偶信息是否显示
			CALLBACK.onselect = function(value,text,index){
				if(value == 'x0303002' || value == 'x0303003' || value == 'x0303004' || value == 'x0303005'){
					$('#marital_form').css("display","inline");
				}else{
					$('#marital_form').css("display","none");
				}
			};
			
		  //居住地址复制给户籍地址
			CALLBACK.copy = function(v,t){
				  //获取对象的值
		          var objValue=$('#'+v).val();
		          if(null!=objValue && objValue!=""){
			        	//赋值街道详细地址
		            	if(v="homeDistrict"){
		            		$("#domicileAddress").val($('#liveAddress').val());
		            	}else if(v="liveCounty"){
		            		$("#domicileAddress").val($('#domicileAddress').val());
		            	}
		            	//用这种方式复制选择项
		                $("#selectAddresss").setAddress({
		                    showStreet:false,//不显示街道
		                    cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
		                    data:objValue,
		                    callback:function(infos,selected_ids) {
		                        var str = '';
		                        for(var i=0;i<infos.length;i++) {
		                            if(str==""){
		                                str = str+infos[i];
		                            }else{
		                                str = str+" / "+infos[i];
		                            }
		                        }
		                        //显示中文
		                        $('#regions').val(str);
		                        $('#liveProvince').val(selected_ids[0]);
		                        $('#liveCity').val(selected_ids[1]);
		                        $('#liveCounty').val(selected_ids[2]);
		                        
		                    }
		                });
		          }
				  /* $("#selectAddresss").setAddress({
					  	showStreet:false,//不显示街道
		                data:data,
		                cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
		                getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
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
		                    $('#domicileCode').val(data);
		                }
		            });
				  alert($('#liveCode').val());
				  alert($('#domicileCode').val()); */
				  $("#domicileAddress").val($("#liveAddress").val());
			  };
			
			//联系方式新增弹出框打开
			CALLBACK.contactAdd = function(index, row){
		  		var id = $("#customerId").val();
		  		if(id==null || id==""){
		  			$.ZMessage.error("错误", "请先保存客户信息", function(){});
		  		}else{
					$("#contactDialog").Zdialog("open");
		  		}
			};
			
			$("#contactDialog").Zdialog({
				width : 700,
				height : 250,
				closed : true,
				title:"新增联系方式",
				buttons : [{
					id : 'message-btn',
					text : '确定',
					buttonCls: 'btn-blue',
					handler : function() {
						//校验联系方式信息
                        var Validate = $.ZUI.validateForm($('#contact_form'));
             			if(Validate){
             				var param = $('#contact_form').serialize();
             				param += "&customerId="+$('#customerId').val();
             				//联系方式信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.cnfh.postLoanContact.savePostLoanContact" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.success("提示", "保存成功", function () {
                                     		$("#contactDialog").Zdialog("close");
                                     		$("#tb-contact").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                    });
                                     }
                                 },
                                 error: function () {
                                   	$.ZMessage.error("错误", "保存联系方式系统异常，请联系管理员", function () {
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
						$("#contactDialog").Zdialog("close");
					}
				} ]
			});
			
			//联系方式操作格式化
	        CALLBACK.contactView=function(rowData,index){
	        	var data='<a class="icon-btn22 handler-icon c-green" title="编辑" onclick="contactEditDialog"></a>&nbsp;&nbsp;';
	        		data+='<a class="icon-btn12 handler-icon c-gray" title="删除" onclick="contactDel"></a>';
	        	return data;
	        }
			
	        CALLBACK.contactDel = function(index, row){
	        	$.ZMessage.question("确认", "确认删除", function (r) {
	                	$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.cnfh.postLoanContact.delPostLoanContact" context="admin"/>',
		                    data: {id : row.id},
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	 $.ZMessage.success("提示", "删除成功", function () {
		                        		$("#tb-contact").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
		                          	 });
		                        }else{
		                          	$.ZMessage.error("错误", data.msg, function () {
				                    });
		                        }
		                    },
		                    error: function () {
		                      	$.ZMessage.error("错误", "删除联系方式系统异常，请联系管理员", function () {
			                    });
		                    }
		                });
	            });
			};
			
	      //联系方式编辑弹出框打开
			CALLBACK.contactEditDialog = function(index, row){
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.postLoanContact.findPostLoanContactById" context="admin"/>',
                    data: {id : row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
            				$("#contactIdEdit").val(data.optional.postLoanContactVo.id);
                        	$("#customerNameEdit").val(data.optional.postLoanContactVo.customerName);
            				$("#contactTypeEdit").ZCombobox('setValue',data.optional.postLoanContactVo.contactType);
            				$("#contactPhoneNumberEdit").val(data.optional.postLoanContactVo.phoneNumber);
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
				$("#contactEditDialog").Zdialog("open");
			};
			
			$("#contactEditDialog").Zdialog({
				width : 700,
				height : 250,
				closed : true,
				title:"编辑联系方式",
				buttons : [{
					id : 'message-btn',
					text : '确定',
					buttonCls: 'btn-blue',
					handler : function() {
						//校验联系方式信息
                        var Validate = $.ZUI.validateForm($('#contactedit_form'));
             			if(Validate){
             				var param = $('#contactedit_form').serialize();
             				param += "&customerId="+$('#customerId').val();
             				//联系方式信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.cnfh.postLoanContact.savePostLoanContact" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.success("提示", "保存成功", function () {
                                     		$("#contactEditDialog").Zdialog("close");
                                     		$("#tb-contact").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                    });
                                     }
                                 },
                                 error: function () {
                                   	$.ZMessage.error("错误", "保存联系方式系统异常，请联系管理员", function () {
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
						$("#contactEditDialog").Zdialog("close");
					}
				} ]
			});
			
			//工作单位新增弹出框打开
			CALLBACK.unitAdd = function(index, row){
		  		var id = $("#customerId").val();
		  		if(id==null || id==""){
		  			$.ZMessage.error("错误", "请先保存客户信息", function(){});
		  		}else{
					$("#unitDialog").Zdialog("open");
		  		}
			};
			$("#unitDialog").Zdialog({
				width : 700,
				height : 350,
				closed : true,
				title:"新增工作单位",
				buttons : [{
					id : 'message-btn',
					text : '确定',
					buttonCls: 'btn-blue',
					handler : function() {
						//校验工作单位
                        var Validate = $.ZUI.validateForm($('#unit_form'));
             			if(Validate){
             				var param = $('#unit_form').serialize();
             				param += "&customerId="+$('#customerId').val();
             				//工作单位信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.cnfh.postLoanWorkUnit.savePostLoanWorkUnit" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.success("提示", "保存成功", function () {
                                     		$("#unitDialog").Zdialog("close");
                                     		$("#tb-unit").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                    });
                                     }
                                 },
                                 error: function () {
                                   	$.ZMessage.error("错误", "保存工作单位系统异常，请联系管理员", function () {
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
						$("#unitDialog").Zdialog("close");
					}
				} ]
			});
			
	      	//工作单位操作格式化
	        CALLBACK.unitView=function(rowData,index){
	        	var data='<a class="icon-btn22 handler-icon c-green" title="编辑" onclick="unitEditDialog"></a>';
	        	data+='&nbsp;&nbsp;<a class="icon-btn12 handler-icon c-gray" title="删除" onclick="unitDel"></a>';
	        	return data;
	        }
	      	
	        CALLBACK.unitDel = function(index, row){
	        	$.ZMessage.question("确认", "确认删除", function (r) {
	                	$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.cnfh.postLoanWorkUnit.delPostLoanWorkUnit" context="admin"/>',
		                    data: {id : row.id},
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	 $.ZMessage.success("提示", "删除成功", function () {
		                        		 $("#tb-unit").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
		                          	 });
		                        }else{
		                          	$.ZMessage.error("错误", data.msg, function () {
				                    });
		                        }
		                    },
		                    error: function () {
		                      	$.ZMessage.error("错误", "删除工作单位系统异常，请联系管理员", function () {
			                    });
		                    }
		                });
	            });
			};
	      	
	      //工作单位编辑弹出框打开
			CALLBACK.unitEditDialog = function(index, row){
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.postLoanWorkUnit.findPostLoanWorkUnitById" context="admin"/>',
                    data: {id : row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
            				$("#unitIdEdit").val(data.optional.postLoanWorkUnitVo.id);
                        	$("#workUnitNameEdit").val(data.optional.postLoanWorkUnitVo.workUnitName);
            				$("#companyNameEdit").val(data.optional.postLoanWorkUnitVo.companyName);
            				$("#industryTypeEdit").ZCombobox('setValue',data.optional.postLoanWorkUnitVo.industryType);
            				$("#industryEdit").ZCombobox('setValue',data.optional.postLoanWorkUnitVo.industry);
            				$("#workUnitNatureEdit").ZCombobox('setValue',data.optional.postLoanWorkUnitVo.workUnitNature);
            				$("#workYearsEdit").val(data.optional.postLoanWorkUnitVo.workYears);
            				$("#positionEdit").ZCombobox('setValue',data.optional.postLoanWorkUnitVo.position);
            				$("#unitPhoneNumberEdit").val(data.optional.postLoanWorkUnitVo.phoneNumber);
            				$("#workUnitAddressEdit").val(data.optional.postLoanWorkUnitVo.workUnitAddress);
            				$("#selectWorkUnitAddressEdit").setAddress({
    							showStreet:false,//不显示街道
    							data:data.optional.postLoanWorkUnitVo.district,
    							cityUrl:'<z:res resource="ess.simplecode.ByParentId" isDefault="true"/>&jsonCallBack=?',//真实数据源
    							getDataCityUrl:'<z:res resource="ess.simplecode.region" isDefault="true"/>&jsoncallback=?',//根据子节点取同级及上级的数据
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
				title:"编辑工作单位",
				buttons : [{
					id : 'message-btn',
					text : '确定',
					buttonCls: 'btn-blue',
					handler : function() {
						//校验工作单位
                        var Validate = $.ZUI.validateForm($('#unitedit_form'));
             			if(Validate){
             				var param = $('#unitedit_form').serialize();
             				param += "&customerId="+$('#customerId').val();
             				//工作单位信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.cnfh.postLoanWorkUnit.savePostLoanWorkUnit" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.success("提示", "保存成功", function () {
                                     		$("#unitEditDialog").Zdialog("close");
                                     		$("#tb-unit").ZTable("reload",{'customerId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                    });
                                     }
                                 },
                                 error: function () {
                                   	$.ZMessage.error("错误", "保存工作单位系统异常，请联系管理员", function () {
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
						$("#unitEditDialog").Zdialog("close");
					}
				} ]
			});
			
			$('#submitClient').click(function(){
				var maritalStatus = $('#maritalStatus').ZCombobox("getValue");
				if(maritalStatus == 'x0303002' || maritalStatus == 'x0303003' || maritalStatus == 'x0303004' || maritalStatus == 'x0303005'){
					//校验主借人
					var isValidate = $.ZUI.validateForm($('#client_form'));
					//校验家庭地址
					var liveValidate = $.ZUI.validateForm($('#live_form'));
					//校验户籍地址
					var domicileValidate = $.ZUI.validateForm($('#domicile_form'));
					//校验配偶信息
                    var Validate = $.ZUI.validateForm($('#marital_form'));
                    if(isValidate && liveValidate && domicileValidate && Validate){
                    	var param = $('#client_form').serialize();
    					//保存主借人
    					$.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.cnfh.customer.saveCustomer" context="admin"/>',
                            data: param,
                            dataType: 'json',
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                	//将主借人的id赋值到页面
    	                            $('#customerId').val(data.optional.newPostLoanPersonalVo.id);
                                	
    	                            var liveparam = $('#live_form').serialize();
    	                            liveparam += "&customerId="+$('#customerId').val();
    	                            liveparam += "&customerName="+$('#customerName').val();
   	                 				//家庭地址信息保存
   	                 				$.ajax({
   	                                     type: 'post',
   	                                     url: '<z:ukey key="com.cnfh.postLoanAddress.saveAddress" context="admin"/>',
   	                                     data: liveparam,
   	                                     dataType: 'json',
   	                                     success: function (data) {
   	                                         if (data.resultStatus == 0) {
   	                                        	 	//将家庭地址id赋值到页面
	                                           		$('#liveId').val(data.optional.newPostLoanAddressVo.id);
	   	                                           	var domicileparam = $('#domicile_form').serialize();
		   	     	   	                 			domicileparam += "&customerId="+$('#customerId').val();
		   	     	   	                 			domicileparam += "&customerName="+$('#customerName').val();
		   	     	                 				//户籍地址信息保存
		   	     	                 				$.ajax({
		   	     	                                     type: 'post',
		   	     	                                     url: '<z:ukey key="com.cnfh.postLoanAddress.saveAddress" context="admin"/>',
		   	     	                                     data: domicileparam,
		   	     	                                     dataType: 'json',
		   	     	                                     success: function (data) {
		   	     	                                         if (data.resultStatus == 0) {
		   	     	                                       		//将户籍地址id赋值到页面
	   	     	                                           		$('#domicileId').val(data.optional.newPostLoanAddressVo.id);
		   	     	                                           	var params = $('#marital_form').serialize();
			   	     	       	                 				params += "&customerId="+$('#customerId').val();
			   	     	       	                 				//配偶信息保存
			   	     	       	                 				$.ajax({
			   	     	       	                                     type: 'post',
			   	     	       	                                     url: '<z:ukey key="com.cnfh.customer.saveMarital" context="admin"/>',
			   	     	       	                                     data: params,
			   	     	       	                                     dataType: 'json',
			   	     	       	                                     success: function (data) {
			   	     	       	                                         if (data.resultStatus == 0) {
			   	     	       	                                         	 $.ZMessage.success("提示", "保存成功", function () {
			   	     	       	                                         		 //将配偶id赋值到页面
			   	     	       	                                           		 $('#maritalId').val(data.optional.newLatestCustomerVo.id);
			   	     	       	                                           	 });
			   	     	       	                                         }else{
			   	     	       	                                           	$.ZMessage.error("错误", data.msg, function () {
			   	     	       	                 		                    });
			   	     	       	                                         }
			   	     	       	                                     },
			   	     	       	                                     error: function () {
			   	     	       	                                       	$.ZMessage.error("错误", "保存配偶信息系统异常，请联系管理员", function () {
			   	     	       	                 	                    });
			   	     	       	                                     }
			   	     	       	                                 });
		   	     	                                         }else{
		   	     	                                           	$.ZMessage.error("错误", data.msg, function () {
		   	     	                 		                    });
		   	     	                                         }
		   	     	                                     },
		   	     	                                     error: function () {
		   	     	                                       	$.ZMessage.error("错误", "保存户籍地址信息系统异常，请联系管理员", function () {
		   	     	                 	                    });
		   	     	                                     }
		   	     	                                 });
   	                                         }else{
   	                                           	$.ZMessage.error("错误", data.msg, function () {
   	                 		                    });
   	                                         }
   	                                     },
   	                                     error: function () {
   	                                       	$.ZMessage.error("错误", "保存家庭地址信息系统异常，请联系管理员", function () {
   	                 	                    });
   	                                     }
   	                                 });
                                }else{
                                	$.ZMessage.error("错误", data.msg, function () {
    		                        });
                                }
                            },
                            error: function () {
                            	$.ZMessage.error("错误", "保存主借人信息系统异常，请联系管理员", function () {
    	                        });
                            }
                        });
                    }
				}else{
					//校验主借人
					var isValidate = $.ZUI.validateForm($('#client_form'));
					//校验家庭地址
					var liveValidate = $.ZUI.validateForm($('#live_form'));
					//校验户籍地址
					var domicileValidate = $.ZUI.validateForm($('#domicile_form'));
                    if(isValidate && liveValidate && domicileValidate){
                    	var param = $('#client_form').serialize();
    					//保存主借人
    					$.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.cnfh.customer.saveCustomer" context="admin"/>',
                            data: param,
                            dataType: 'json',
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                	//将主借人的id赋值到页面
    	                            $('#customerId').val(data.optional.newPostLoanPersonalVo.id);
    	                            var liveparam = $('#live_form').serialize();
    	                            liveparam += "&customerId="+$('#customerId').val();
    	                            liveparam += "&customerName="+$('#customerName').val();
   	                 				//家庭地址信息保存
   	                 				$.ajax({
   	                                     type: 'post',
   	                                     url: '<z:ukey key="com.cnfh.postLoanAddress.saveAddress" context="admin"/>',
   	                                     data: liveparam,
   	                                     dataType: 'json',
   	                                     success: function (data) {
   	                                         if (data.resultStatus == 0) {
   	                                        	//将家庭id赋值到页面
	                                           	$('#liveId').val(data.optional.newPostLoanAddressVo.id);
	                                         		 
   	                                           	var domicileparam = $('#domicile_form').serialize();
	   	     	   	                 			domicileparam += "&customerId="+$('#customerId').val();
	   	     	   	                 			domicileparam += "&customerName="+$('#customerName').val();
	   	     	                 				//户籍地址信息保存
	   	     	                 				$.ajax({
	   	     	                                     type: 'post',
	   	     	                                     url: '<z:ukey key="com.cnfh.postLoanAddress.saveAddress" context="admin"/>',
	   	     	                                     data: domicileparam,
	   	     	                                     dataType: 'json',
	   	     	                                     success: function (data) {
	   	     	                                         if (data.resultStatus == 0) {
	   	     	                                         	 $.ZMessage.success("提示", "保存成功", function () {
	   	     	                                         		 //将户籍id赋值到页面
	   	     	                                           		 $('#domicileId').val(data.optional.newPostLoanAddressVo.id);
	   	     	                                           	 });
	   	     	                                         }else{
	   	     	                                           	$.ZMessage.error("错误", data.msg, function () {
	   	     	                 		                    });
	   	     	                                         }
	   	     	                                     },
	   	     	                                     error: function () {
	   	     	                                       	$.ZMessage.error("错误", "保存户籍地址信息系统异常，请联系管理员", function () {
	   	     	                 	                    });
	   	     	                                     }
	   	     	                                 });
   	                                         }else{
   	                                           	$.ZMessage.error("错误", data.msg, function () {
   	                 		                    });
   	                                         }
   	                                     },
   	                                     error: function () {
   	                                       	$.ZMessage.error("错误", "保存家庭地址信息系统异常，请联系管理员", function () {
   	                 	                    });
   	                                     }
   	                                 });
                                }else{
                                	$.ZMessage.error("错误", data.msg, function () {
    		                        });
                                }
                            },
                            error: function () {
                            	$.ZMessage.error("错误", "保存主借人信息系统异常，请联系管理员", function () {
    	                        });
                            }
                        });
                    }
				}
			});
			
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
			
			//工作单位新增地址
			$("#selectWorkUnitAddress").Address({
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
                    $('#workUnitRegions').val(str);
                    $('#workUnitProvince').val(selected_ids[0]);
                    $('#workUnitCity').val(selected_ids[1]);
                    $('#workUnitDistrict').val(selected_ids[2]);
                }
            });
			
			//工作单位编辑地址
			$("#selectWorkUnitAddressEdit").Address({
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