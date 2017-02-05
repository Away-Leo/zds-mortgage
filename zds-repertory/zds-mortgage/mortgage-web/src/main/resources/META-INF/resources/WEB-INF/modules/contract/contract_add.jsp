<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp'%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构合同报备申请</title>
</head>
<body>
	<div class="page-box">
		<div class="p10">
			<form id="contract_addoredit_form" class="zui-form " method="post"
				enctype="multipart/form-data">
				<div class="page-title">基本信息</div>
				<dl class="form-item">
					<dt class="title">标题：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="expenditureType"
							name="expenditureType" type="hidden"
							value=""
							data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=edtp"
							data-valuefield="fullcode" data-callback="getFeeItems"
							data-textfield="name" validate-type="Require">
					</dd>

					<dt class="title">编号：</dt>
					<dd class="detail">
						<label> <input
							class="zui-input zui-disabled zui-validatebox" id="totalAmount"
							value="" name="totalAmount" />
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">申请人：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="expenditureType"
							name="expenditureType" type="hidden"
							value=""
							data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=edtp"
							data-valuefield="fullcode" data-callback="getFeeItems"
							data-textfield="name" validate-type="Require">
					</dd>

					<dt class="title">申请人部门：</dt>
					<dd class="detail">
						<label> <input
							class="zui-input zui-disabled zui-validatebox" id="totalAmount"
							value="" name="totalAmount" />
						</label>
					</dd>

					<dt class="title">申请时间：</dt>
					<dd class="detail">
						<label> <input
							class="zui-input zui-disabled zui-validatebox" id="totalAmount"
							value="" name="totalAmount" />
						</label>
					</dd>
				</dl>

				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="expenditureType"
							name="expenditureType" type="hidden"
							value=""
							data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=edtp"
							data-valuefield="fullcode" data-callback="getFeeItems"
							data-textfield="name" validate-type="Require">
					</dd>

					<dt class="title">申请类别：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" id="totalAmount"
							value="" name="totalAmount" />
						</label>
					</dd>
				</dl>
			</form>
		</div>
	</div>
</body>
</html>