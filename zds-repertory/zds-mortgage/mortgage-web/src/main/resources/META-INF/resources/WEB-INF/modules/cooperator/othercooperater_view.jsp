<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<title>其他合作单位查看</title>
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
							<dt class="title">企业类型：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.companyType }" disabled="disabled"
									id="companyType" name="companyType" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">合作单位名称：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.contactCompanyName }" disabled="disabled"
									id="contactCompanyName" name="contactCompanyName" >
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">简称:</dt>
			                <dd class="detail">
			                    <input class="zui-input" value="${otherCooperaterVo.shortName }" disabled="disabled"
									id="shortName" name="shortName" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">类别：</dt>
							<dd class="detail">
								<input class="zui-input" value="${otherCooperaterVo.type }" disabled="disabled"
									id="type" name="type" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">上级名称：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.fatherName }" disabled="disabled"
									id="fatherName" name="fatherName" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">合作单位归属：</dt>
							<dd class="detail">
								<input class="zui-input" value="${otherCooperaterVo.contactCompanyBelong }" disabled="disabled"
									id="contactCompanyBelong" name="contactCompanyBelong" style="width:94px">
			                    <input class="zui-input nwidth2" value="${otherCooperaterVo.contactCompanyBelongName }" disabled="disabled"
									id="contactCompanyBelongName" name="contactCompanyBelongName" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
                               		<input class="zui-input" value="${otherCooperaterVo.callNumber }" disabled="disabled"
									id="callNumber" name="callNumber" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">传真：</dt>
							<dd class="detail">
                               		<input class="zui-input" value="${otherCooperaterVo.foxNumber }" disabled="disabled"
									id="foxNumber" name="foxNumber" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮政编码：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.postalCode }" disabled="disabled"
									id="postalCode" name="postalCode" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">网站：</dt>
							<dd class="detail">
                               		<input class="zui-input" value="${otherCooperaterVo.website }" disabled="disabled"
									id="website" name="website" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">是否停用：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.isStop }" disabled="disabled"
									id="isStop" name="isStop" >
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
			                            <input id="region" class="zui-input zui-validatebox"  type="text" disabled="disabled" style="width: 190px;" validate-type="Require"/>
			                            <%-- <input id="detailedCode" type="hidden" name="detailedCode" value="${otherCooperaterVo.detailedCode }"/> --%>
		                            </label>
		                        </div>
                             </dd>
                            <dd class="detail">
                               <input class="zui-input" value="${otherCooperaterVo.detailedAddress }" disabled="disabled"
									id="detailedAddress" name="detailedAddress" style="width: 466%;">
							</dd>
						</dl>
					</div>
					<div class="page-box">
						<div class="page-title">明细信息</div>
						<dl class="form-item">
							<dt class="title">成立时间：</dt>
							<dd class="detail">
									<input class="zui-input strToDate" value="${otherCooperaterVo.foundDate }" disabled="disabled"
									value="" id="foundDate" name="foundDate" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">法人：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.legalPerson }" disabled="disabled"
									id="legalPerson" name="legalPerson" >
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">税号:</dt>
			                <dd class="detail">
			                    <input class="zui-input" value="${otherCooperaterVo.dutyParagraph }" disabled="disabled"
									id="dutyParagraph" name="dutyParagraph" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">银行账号：</dt>
							<dd class="detail">
								<input class="zui-input" value="${otherCooperaterVo.bankAccount }" disabled="disabled"
									id="bankAccount" name="bankAccount" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">行业：</dt>
							<dd class="detail">
			                    <input class="zui-input" value="${otherCooperaterVo.industry }" disabled="disabled"
									id="industry" name="industry" >
							</dd>
						</dl>
						<br>
						<dl class="form-item">
							<dt class="title">备注：</dt>
							<dd class="detail">
								<textarea class="zui-area zui-disabled" id="remark" name="remark" disabled="disabled">${otherCooperaterVo.remark }</textarea>
							</dd>
						</dl>
					</div>
		        </form>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.address','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/make-first-py'], 
			function($, CALLBACK) {
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