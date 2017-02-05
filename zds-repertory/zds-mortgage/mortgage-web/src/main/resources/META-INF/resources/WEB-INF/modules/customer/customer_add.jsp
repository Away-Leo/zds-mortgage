<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>客户信息新增</title>
</head>
<body>
	<div>
		<div class="page-box">
			<div class="p10">
				<form id="client_form" class="zui-form " method="post" enctype="multipart/form-data">
					<input type="hidden" id="customerId" name="id" value="">
					<div class="page-box">
						<div class="page-title">主借人信息</div>
						<dl class="form-item">
							<dt class="title">主借人头像：</dt>
							<dd class="detail">
								<input type="hidden" id="imgId" name="imgId" value="">
								<div id="showImg">
								</div>
								
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" 
									id="clientNm" name="clientNm" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">曾用名：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="formerNm" name="formerNm" >
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title"><b class="c-red mr5">*</b>证件类型:</dt>
			                <dd class="detail">
			                    <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="credentiaType" type="hidden" name="credentiaType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=060600"
			                           data-valuefield="fullcode" data-textfield="name" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>证件号码：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-18]" 
									id="credentialNo" name="credentialNo" onblur="checkClientCredentialNo()">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>出生日期：</dt>
							<dd class="detail">
									<input type="text" class="zui-date zui-validatebox" validate-type="Require" id="birthday" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeBirthday'})">
		                            <input type="hidden" id="changeBirthday" name="birthday" />
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>性别：</dt>
							<dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="gender" type="hidden" name="gender" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=3030"
			                           data-valuefield="fullcode" data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>婚况：</dt>
							<dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="marital" type="hidden" name="marital" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=x030300"
			                           data-valuefield="fullcode" data-textfield="name" data-callback="onselect">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>职业类型：</dt>
							<dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="careerType" type="hidden" name="careerType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=c10100"
			                           data-valuefield="fullcode" data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">个人年收入(元)：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-18]" 
									id="annualIncome" name="annualIncome" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">教育程度：</dt>
							<dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="degree" type="hidden" name="degree" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
			                           data-valuefield="fullcode" data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮箱地址：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="email" name="email" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">居住年限(年)：</dt>
							<dd class="detail">
									<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="liveAge" type="hidden" name="liveAge" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=y00100"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="">
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>居住地址：</dt>
							<%-- <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="liveProvince" type="hidden" name="liveProvince" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=2222"
			                           data-valuefield="fullcode" data-textfield="name" >
                            </dd>
                            <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="liveCity" type="hidden" name="liveCity" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1919"
			                           data-valuefield="fullcode" data-textfield="name" >
                             </dd>
                            <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="liveDistrict" type="hidden" name="liveDistrict" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1919"
			                           data-valuefield="fullcode" data-textfield="name" >
                             </dd> --%>
                             <dd class="detail">
                             	<div id="selectAddress" data-code="">
		                            <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type="Require"/>
		                            <input id="liveCode" type="hidden" name="liveCode" value=""/>
		                        </div>
                             </dd>
                            <dd class="detail">
                               		<input class="zui-input zui-validatebox" validate-type="Require,Length[1-200]" 
									id="liveAddress" name="liveAddress" >
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">户籍地址：</dt>
							<%-- <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="domicileProvince" type="hidden" name="domicileProvince" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=2222"
			                           data-valuefield="fullcode" data-textfield="name" >
                            </dd>
                            <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="domicileCity" type="hidden" name="domicileCity" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1919"
			                           data-valuefield="fullcode" data-textfield="name" >
                           </dd>
                           <dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="domicileDistrict" type="hidden" name="domicileDistrict" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1919"
			                           data-valuefield="fullcode" data-textfield="name" >
                          </dd> --%>
                          <dd class="detail">
                             	<div id="selectAddresss" data-code="">
		                            <input id="regions" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type=""/>
		                            <input id="domicileCode" type="hidden" name="domicileCode" value=""/>
		                        </div>
                             </dd>
                          <dd class="detail">
                               		<input class="zui-input zui-validatebox" validate-type="Length[0-200]" 
									id="domicileAddress" name="domicileAddress" >
							</dd>
							<dd class="detail">
                               		<input class="zui-combobox" id="copy" type="hidden" name="copy"
                               		data-data="[{'id':'copy','text':'复制主借人居住地址','isDefault':'true'}]"
                               		data-valuefield="id" data-textfield="text" data-callback="copy">
							</dd>
						</dl>
					</div>
				</form>
				<form id="marital_form" class="zui-form " method="post" enctype="multipart/form-data" style="display:none">
					<input type="hidden" id="maritalId" name="id" value="">
					<div class="page-box">
						<div class="page-title">主借人配偶信息</div>
						<dl class="form-item">
							<dt class="title">配偶头像：</dt>
							<dd class="detail">
								<input type="hidden" id="maritalImgId" name="imgId" value="">
								<div id="maritalShowImg">
								</div>
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" 
									value="" id="maritalclientNm" name="clientNm" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">曾用名：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-64]" 
									id="maritalformerNm" name="formerNm" >
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title"><b class="c-red mr5">*</b>证件类型:</dt>
			                <dd class="detail">
			                    <input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="maritalcredentiaType" type="hidden" name="credentiaType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=060600"
			                           data-valuefield="fullcode" data-textfield="name" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>证件号码：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Require,Length[1-18]" 
									id="maritalcredentialNo" name="credentialNo" onblur="checkMaritalCredentialNo()">
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>职业类型：</dt>
							<dd class="detail">
                               		<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="maritalcareerType" type="hidden" name="careerType" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g050500"
			                           data-valuefield="fullcode" data-textfield="name" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮箱地址：</dt>
							<dd class="detail">
									<input class="zui-input zui-validatebox" validate-type="Length[0-32]" 
									id="maritalemail" name="email" >
							</dd>
						</dl>
					</div>					
		        </form>
		            <div class="page-box">
						<div class="page-title">联系方式</div>
						<div class="p10">
					        <div id="tb-contact" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contact.findByClientId" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#contact-function"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:contactName,width:20%">姓名</th>
							            <th data-options="field:relationshipType,width:20%">关系</th>
							            <th data-options="field:contactType,width:25%">联系类型</th>
							            <th data-options="field:contact,width:25%">电话号码</th>
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
					        <div id="tb-unit" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.workUnits.findByClientId" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#unit-function"}'>
							    <table>
							        <thead>
							        <tr>
							            <th data-options="field:unitsName,width:6%">姓名</th>
							            <th data-options="field:relationshipType,width:5%">关系</th>
							            <th data-options="field:companyName,width:10%">工作单位名称</th>
							            <th data-options="field:industryType,width:10%">行业类型</th>
							            <th data-options="field:industry,width:10%">行业</th>
							            <th data-options="field:unitNature,width:6%">单位性质</th>
							            <th data-options="field:position,width:6%">职务</th>
							            <th data-options="field:workeYears,width:2%">工作年限</th>
							            <th data-options="field:unitPhone,width:8%">工作单位电话</th>
							            <th data-options="field:unitAddress,width:25%">单位住址</th>
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
	                <div class="page-box" id="contactDialog" style = "display: none">
						<!-- <div class="page-title">新增联系方式</div> -->
						<div class="p10">
					        <form id="contact_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="contactName" type="hidden" name="contactName">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>关系：</dt>
									<dd class="detail">
											<input class="zui-validatebox" validate-type="Require,Length[0-15]" id="relationshipType" name="relationshipType">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>联系类型：</dt>
									<dd class="detail">
											<input class="zui-validatebox" validate-type="Require,Length[0-15]" id="contactType" name="contactType">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>电话号码：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15]" 
											value="" id="contact" name="contact" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 查看联系方式弹出框 -->
	                <div class="page-box" id="contactViewDialog" style = "display: none">
						<!-- <div class="page-title">查看联系方式</div> -->
						<div class="p10">
					        <form id="contactView_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="contactnameview">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">关系：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly" 
											value="" id="relationshipTypeview">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">联系类型：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="contactTypeview">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">电话号码：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="contactsview">
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 新增工作单位弹出框 -->
					<div class="page-box" id="unitDialog" style = "display: none">
						<!-- <div class="page-title">新增工作单位信息</div> -->
						<div class="p10">
					        <form id="unit_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>姓名：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="unitsName" type="hidden" name="unitsName">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>关系：</dt>
									<dd class="detail">
											<input class="zui-validatebox" validate-type="Require,Length[0-15]" id="relationshipTypes" name="relationshipType">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作单位名称：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Require" 
											value="" id="companyName" name="companyName" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>行业类型：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="industryType" type="hidden" name="industryType" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=i00100"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>单位性质：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="unitNature" type="hidden" name="unitNature" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0202"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>职务：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="position" type="hidden" name="position" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1212"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作年限(年)：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15]" 
											value="" id="workeYears" name="workeYears" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>工作单位电话：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15]" 
											value="" id="unitPhone" name="unitPhone" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>行业：</dt>
									<dd class="detail">
											<input class="zui-combobox zui-validatebox" validate-type="Require,Length[0-15]" id="industry" type="hidden" name="industry" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=1818"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title"><b class="c-red mr5">*</b>单位地址：</dt>
									<dd class="detail">
											<input class="zui-input zui-validatebox" validate-type="Require,Length[1-15]" 
											value="" id="unitAddress" name="unitAddress" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
					
					<!-- 查看工作单位弹出框 -->
					<div class="page-box" id="unitViewDialog" style = "display: none">
						<!-- <div class="page-title">查看工作单位信息</div> -->
						<div class="p10">
					        <form id="unitView_form" class="zui-form " method="post" enctype="multipart/form-data">
					        	<dl class="form-item">
									<dt class="title">姓名：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="unitsNameView">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">关系：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="relationshipTypesView">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位名称：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="companyNameView">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业类型：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="industryTypeView">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位性质：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="unitNatureView">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">职务：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="positionView" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作年限(年)：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="workeYearsView" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">工作单位电话：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly"
											value="" id="unitPhoneView" >
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">行业：</dt>
									<dd class="detail">
											<input class="zui-input" id="industryView" name="industry" readonly="readonly">
									</dd>
								</dl>
								<dl class="form-item">
									<dt class="title">单位地址：</dt>
									<dd class="detail">
											<input class="zui-input" readonly="readonly" 
											value="" id="unitAddressView" >
									</dd>
								</dl>
					        </form>
						</div>
					</div>
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
			
			//校验证件类型+证件号（主借人）
			window.checkClientCredentialNo = function(){
				
				var credentialNo = $("#credentialNo").val();
				var credentiaType = $("#credentiaType").ZCombobox("getValue");
				var credentiaTypeText = $("#credentiaType").ZCombobox("getText");
				
				if(credentialNo!=null && credentialNo!="" && credentiaType!=null) {
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.customer.findByCredentiaTypeAndCredentialNo" context="admin"/>',
	                    data: {credentiaType:credentiaType,credentialNo:credentialNo},
	                    dataType: 'json',
	                    success: function (result) {
	                        if (result.resultStatus == 0) {
	                        	var data = result.optional.latestCustomerVo;
                        		if(data){
                        			//如果id不为空，表示对应证件类型+证件号的主借人人已存在，带出来允许修改
                					if(data.id ){
                						//如果主借人已存在，赋值
                						$('#customerId').val(data.id);
                						$("#imgId").val(data.imgId);
						    			$('#showImg').children().remove();
						    			var url = '<z:ukey key="public.upload.download" context="admin"/>&attachmentId=' + data.imgId;
						    			$('#showImg').append("<img  width='200px' height='87px' src ='"+url+"'/>")
                						$("#customerId").val(data.id);
                						$("#clientNm").val(data.clientNm);
                						$("#formerNm").val(data.formerNm);
                						$("#credentiaType").ZCombobox('setValue',data.credentiaType);
                						$("#credentialNo").val(data.credentialNo);
                						$("#changeBirthday").val(data.birthday);
                						$("#birthday").val(data.birthday);
                						$("#gender").ZCombobox('setValue',data.gender);
                						$("#marital").ZCombobox('setValue',data.marital);
                						$("#careerType").ZCombobox('setValue',data.careerType);
                						$("#annualIncome").val(data.annualIncome);
                						$("#degree").ZCombobox('setValue',data.degree);
                						$("#email").val(data.email);
                						$("#liveAge").ZCombobox('setValue',data.liveAge);
                						$("#selectAddress").setAddress({
                							showStreet:false,//不显示街道
                							data:data.liveCode,
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
                			                    $('#liveCode').val(data.liveCode);
                			                }
                			            });
                						$("#liveAddress").val(data.liveAddress);
                						$("#selectAddresss").setAddress({
                							showStreet:false,//不显示街道
                			                data:data.domicileCode,
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
                			                    $('#domicileCode').val(data.domicileCode);
                			                }
                			            });
                						$("#domicileAddress").val(data.domicileAddress);
                						
                						//有配偶给配偶赋值
                						if(data.marital == 'x0303002' || data.marital == 'x0303003' || data.marital == 'x0303004' || data.marital == 'x0303005'){
                							$('#marital_form').css("display","inline");
                							var clientId = data.id;
	                						$.ajax({
	                		                    type: 'post',
	                		                    url: '<z:ukey key="com.zdsoft.finance.customer.findMaritalByClientId" context="admin"/>',
	                		                    data: {clientId:clientId},
	                		                    dataType: 'json',
	                		                    success: function (result) {
	                		                        if (result.resultStatus == 0) {
	                		                        	var data = result.optional.maritalCustomerVo;
	                		                    		if(data){
	                		                    			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
	                		            					if(data.id ){
	                		            						//如果附属人已存在，给附属人赋值
	                		            						$('#maritalId').val(data.id);
	                		            						$("#maritalclientNm").val(data.clientNm);
	                		            						$("#maritalformerNm").val(data.formerNm);
	                		            						$("#maritalcredentiaType").ZCombobox('setValue',data.credentiaType);
	                		            						$("#maritalcredentialNo").val(data.credentialNo);
	                		            						$("#maritalcareerType").ZCombobox('setValue',data.careerType);
	                		            						$("#maritalemail").val(data.email);
	                		            						$("#maritalImgId").val(data.imgId);
	                		            						$('#maritalShowImg').children().remove();
	                							    			var url = '<z:ukey key="public.upload.download" context="admin"/>&attachmentId=' + data.imgId;
	                							    			$('#maritalShowImg').append("<img  width='200px' height='87px' src ='"+url+"'/>")
	                		            					}
	                		            				}
	                		                        }
	                		                    },
	                		                    error: function () {
	                		                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
	                		                    }
	                		                });
                						}else{
                							$('#marital_form').css("display","none");
                						}
                						//联系方式列表加载
                						$("#tb-contact").ZTable("reload",{'clientId|E|S':data.id});
                						//工作单位列表加载
                						$("#tb-unit").ZTable("reload",{'clientId|E|S':data.id});
                					}
                				}
	                        }
	                    },
	                    error: function () {
	                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
	                    }
	                });
				}
				
				// 证件类型为身份证
				if(credentiaTypeText == '身份证'){
					// 根据身份证自动填充出生日期 和性别
					if(credentialNo!=null && credentialNo!=""  && credentialNo.length==18){
						// 根据身份证自动填充出生日期
						$('#birthday').val(credentialNo.substr(6, 4)+'-'+credentialNo.substr(10, 2)+'-'+credentialNo.substr(12, 2));
						$('#changeBirthday').val(credentialNo.substr(6, 4)+credentialNo.substr(10, 2)+credentialNo.substr(12, 2));
						// 根据身份证获取性别
						var sex = "";
						if (parseInt(credentialNo.charAt(16) / 2) * 2 != credentialNo.charAt(16)){
							sex = '男';
							$("#gender").ZCombobox('setValue','303001');
						}else{
							 sex = '女';
							 $("#gender").ZCombobox('setValue','303002');
						}
					}					
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
	                    url: '<z:ukey key="com.zdsoft.finance.customer.findByCredentiaTypeAndCredentialNo" context="admin"/>',
	                    data: {credentiaType:credentiaType,credentialNo:credentialNo},
	                    dataType: 'json',
	                    success: function (result) {
	                        if (result.resultStatus != 0) {
	                        	var data = result.optional.latestCustomerVo;
                        		if(data){
                        			//如果id不为空，表示对应证件类型+证件号的附属人已存在，带出来允许修改
                					if(data.id ){
                						//如果附属人已存在，给附属人赋值
                						$('#maritalId').val(data.id);
                						$("#maritalformerNm").val(data.formerNm);
                						$("#maritalcareerType").ZCombobox('setValue',data.careerType);
                						$("#maritalemail").val(data.email);
                						$("#maritalclientNm").val(data.clientNm);
                						$("#maritalImgId").val(data.imgId);
                						$('#maritalShowImg').children().remove();
						    			var url = '<z:ukey key="public.upload.download" context="admin"/>&attachmentId=' + data.imgId;
						    			$('#maritalShowImg').append("<img  width='200px' height='87px' src ='"+url+"'/>")
                					}
                				}
	                        }
	                    },
	                    error: function () {
	                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {});
	                    }
	                });
				}
			}
			
			//判断主借人配偶信息是否显示
			CALLBACK.onselect = function(value,text,index){
				if(value == 'x0303002' || value == 'x0303003' || value == 'x0303004' || value == 'x0303005'){
					$('#marital_form').css("display","inline");
				}else{
					$('#marital_form').css("display","none");
				}
			};
			
		  //居住地址复制给户籍地址
		  CALLBACK.copy = function(){
			  $("#selectAddresss").setAddress({
				  	showStreet:false,//不显示街道
	                data:$("#liveCode").val(),
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
	                    $('#domicileCode').val($("#liveCode").val());
	                }
	            });
			  $("#domicileAddress").val($("#liveAddress").val());
		  };
			
			//联系方式新增弹出框打开
			CALLBACK.contactAdd = function(index, row){
		  		var id = $("#customerId").val();
		  		if(id==null || id==""){
		  			$.ZMessage.error("错误", "请先保存客户信息", function(){});
		  		}else{
		  			var id = $('#customerId').val();
		  			var url = "<z:ukey key='com.zdsoft.finance.customer.findClientNameByClientId' context='admin'/>&jsoncallback=?&clientId="+id;
		  			$("#contactName").ZCombobox({
		  		         valueField: "fullcode",
		  		         textField: "name",
		  		         emptyValue:true,
		  		         multiple:false,
		  		         url:url
		  		  	});
					$("#contactDialog").Zdialog("open");
		  		}
			};
			
			$("#contactDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"新增联系方式",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '确定',
					handler : function() {
						//校验联系方式信息
                        var Validate = $.ZUI.validateForm($('#contact_form'));
             			if(Validate){
             				var param = $('#contact_form').serializeArray();
             				param += "&clientId="+$('#customerId').val();
             				//联系方式信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.zdsoft.finance.contact.saveContact" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.warning("提示", "保存成功", function () {
                                     		$("#contactDialog").Zdialog("close");
                                     		$("#tb-contact").ZTable("reload",{'clientId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                        //$(".zd-message").ZWindow("close");
             		                    });
                                     }
                                 },
                                 error: function () {
                                   	$.ZMessage.error("错误", "保存联系方式系统异常，请联系管理员", function () {
             	                    });
                                 }
                             });
             			}
						$("#contactDialog").Zdialog("close");
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
			
			//工作单位新增弹出框打开
			CALLBACK.unitAdd = function(index, row){
		  		var id = $("#customerId").val();
		  		if(id==null || id==""){
		  			$.ZMessage.error("错误", "请先保存客户信息", function(){});
		  		}else{
		  			var id = $('#customerId').val();
		  			var url = "<z:ukey key='com.zdsoft.finance.customer.findClientNameByClientId' context='admin'/>&jsoncallback=?&clientId="+id;
		  			$("#unitsName").ZCombobox({
		  		         valueField: "fullcode",
		  		         textField: "name",
		  		         emptyValue:true,
		  		         multiple:false,
		  		       	 url:url
		  		  	});
					$("#unitDialog").Zdialog("open");
		  		}
			};
			$("#unitDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"新增工作单位",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '确定',
					handler : function() {
						//校验工作单位
                        var Validate = $.ZUI.validateForm($('#tb-unit'));
             			if(Validate){
             				var param = $('#unit_form').serializeArray();
             				param += "&clientId="+$('#customerId').val();
             				//工作单位信息保存
             				$.ajax({
                                 type: 'post',
                                 url: '<z:ukey key="com.zdsoft.finance.workUnits.saveWorkUnits" context="admin"/>',
                                 data: param,
                                 dataType: 'json',
                                 success: function (data) {
                                     if (data.resultStatus == 0) {
                                     	 $.ZMessage.warning("提示", "保存成功", function () {
                                     		$("#unitDialog").Zdialog("close");
                                     		$("#tb-unit").ZTable("reload",{'clientId|E|S':$('#customerId').val()});
                                       	 });
                                     }else{
                                       	$.ZMessage.error("错误", data.msg, function () {
             		                        //$(".zd-message").ZWindow("close");
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
			
			//联系方式操作格式化
	        CALLBACK.contactView=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onclick="contactViewDialog"><button class="btn-blue">详情</button></a>&nbsp;&nbsp;';
	        		data+='<a href="javaScript:void(0)" onclick="contactDel"><button class="btn-blue">删除</button></a>';
	        	return data;
	        }
			
	        CALLBACK.contactDel = function(index, row){
	        	$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.workUnits.delContact" context="admin"/>',
                    data: {id : row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	 $.ZMessage.warning("提示", "删除成功", function () {
                        		$("#tb-contact").ZTable("reload",{'clientId|E|S':$('#customerId').val()});
                          	 });
                        }else{
                          	$.ZMessage.error("错误", data.msg, function () {
		                        //$(".zd-message").ZWindow("close");
		                    });
                        }
                    },
                    error: function () {
                      	$.ZMessage.error("错误", "删除联系方式系统异常，请联系管理员", function () {
	                    });
                    }
                });
			};
			
	      //联系方式查看弹出框打开
			CALLBACK.contactViewDialog = function(index, row){
				$("#contactnameview").val(row.contactName);
				$("#relationshipTypeview").val(row.relationshipType);
				$("#contactTypeview").val(row.contactType);
				$("#contactsview").val(row.contact);
				$("#contactViewDialog").Zdialog("open");
			};
			
			$("#contactViewDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"查看联系方式",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					handler : function() {
						$("#contactViewDialog").Zdialog("close");
					}
				}]
			});
			
	      	//工作单位操作格式化
	        CALLBACK.unitView=function(rowData,index){
	        	var data='<a href="javaScript:void(0)" onclick="unitViewDialog"><button class="btn-blue">详情</button></a>';
	        	data+='&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="unitDel"><button class="btn-blue">删除</button></a>';
	        	return data;
	        }
	      	
	        CALLBACK.unitDel = function(index, row){
	        	$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.workUnits.delWorkUnits" context="admin"/>',
                    data: {id : row.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	 $.ZMessage.warning("提示", "删除成功", function () {
                        		 $("#tb-unit").ZTable("reload",{'clientId|E|S':$('#customerId').val()});
                          	 });
                        }else{
                          	$.ZMessage.error("错误", data.msg, function () {
		                        //$(".zd-message").ZWindow("close");
		                    });
                        }
                    },
                    error: function () {
                      	$.ZMessage.error("错误", "删除工作单位系统异常，请联系管理员", function () {
	                    });
                    }
                });
			};
	      	
	      //工作单位查看弹出框打开
			CALLBACK.unitViewDialog = function(index, row){
				$("#unitsNameView").val(row.unitsName);
				$("#industryView").val(row.industry);
				$("#relationshipTypesView").val(row.relationshipType);
				$("#companyNameView").val(row.companyName);
				$("#industryTypeView").val(row.industryType);
				$("#unitNatureView").val(row.unitNature);
				$("#positionView").val(row.position);
				$("#workeYearsView").val(row.workeYears);
				$("#unitPhoneView").val(row.unitPhone);
				$("#unitAddressView").val(row.unitAddress);
		  		$("#unitViewDialog").Zdialog("open");
			};
			$("#unitViewDialog").Zdialog({
				width : 430,
				height : 350,
				closed : true,
				title:"查询工作单位",
				onOpen:function(){
					 CALLBACK.openLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},onClose:function(){
					CALLBACK.closeLayerBefore(this,420);//更改弹出与iframe的间距   参数一表示 obj 弹出层的div 参数二表示 需要调整的高度
				},
				
				buttons : [{
					id : 'message-btn',
					text : '关闭',
					handler : function() {
						$("#unitViewDialog").Zdialog("close");
					}
				}]
			});
			
			$('#submitClient').click(function(){
				//校验主借人
				var isValidate = $.ZUI.validateForm($('#client_form'));
				if(isValidate){
					var param = $('#client_form').serializeArray();
					//保存主借人
					$.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.customer.saveCustomer" context="admin"/>',
                        data: param,
                        dataType: 'json',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                            	//将主借人的id赋值到页面
	                            $('#customerId').val(data.optional.newLatestCustomerVo.id);
                            	var marital = $('#marital').ZCombobox("getValue");
                            	if(marital == 'x0303002' || marital == 'x0303003' || marital == 'x0303004' || marital == 'x0303005'){
                            		//校验配偶信息
    	                            var Validate = $.ZUI.validateForm($('#marital_form'));
    	                 			if(isValidate){
    	                 				var param = $('#marital_form').serializeArray();
    	                 				param += "&customerId="+$('#customerId').val();
    	                 				//配偶信息保存
    	                 				$.ajax({
    	                                     type: 'post',
    	                                     url: '<z:ukey key="com.zdsoft.finance.customer.saveMarital" context="admin"/>',
    	                                     data: param,
    	                                     dataType: 'json',
    	                                     success: function (data) {
    	                                         if (data.resultStatus == 0) {
    	                                         	 $.ZMessage.warning("提示", "保存成功", function () {
    	                                         		 //将配偶id赋值到页面
    	                                           		 $('#maritalId').val(data.optional.newLatestCustomerVo.id);
    	                                           	 });
    	                                         }else{
    	                                           	$.ZMessage.error("错误", data.msg, function () {
    	                 		                        //$(".zd-message").ZWindow("close");
    	                 		                    });
    	                                         }
    	                                     },
    	                                     error: function () {
    	                                       	$.ZMessage.error("错误", "保存配偶信息系统异常，请联系管理员", function () {
    	                 	                    });
    	                                     }
    	                                 });
    	                 			}
                            	}else{
                            		$.ZMessage.warning("提示", "保存成功", function () { });
                            	}
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
			});
			
			$.ZUI.init();
			
			//居住地址
			$("#selectAddress").Address({
				showStreet:false,//不显示街道
	            cityUrl:"cityData.json",//获取城市数据源
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
                    $('#liveCode').val(strCode);
                }
            });
			
			//户籍地址
			$("#selectAddresss").Address({
				showStreet:false,//不显示街道
	            cityUrl:"cityData.json",//获取城市数据源
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
                    $('#domicileCode').val(strCode);
                }
            });
			
			//关系下拉框
			$("#relationshipType").ZCombobox({
		         valueField: "eventsCd",
		         textField: "eventsName",
		         emptyValue:true,
		         multiple:false,
		         url:"<z:ukey key='com.zdsoft.finance.customer.findRelationshipType' context='admin'/>&jsoncallback=?"
		  	});
			
			$("#relationshipTypes").ZCombobox({
		         valueField: "eventsCd",
		         textField: "eventsName",
		         emptyValue:true,
		         multiple:false,
		         url:"<z:ukey key='com.zdsoft.finance.customer.findRelationshipType' context='admin'/>&jsoncallback=?"
		  	});
			
			//联系类型下拉框
			$("#contactType").ZCombobox({
		         valueField: "eventsCd",
		         textField: "eventsName",
		         emptyValue:true,
		         multiple:false,
		         url:"<z:ukey key='com.zdsoft.finance.customer.findContactType' context='admin'/>&jsoncallback=?"
		  	});
			
		});
		
	</script>
</body>
</html>