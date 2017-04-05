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
									<input class="zui-input" value="${otherCooperaterVo.enterpriseTypeName }" disabled="disabled"
									id="enterpriseType" name="enterpriseType" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">合作单位名称：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.companyName }" disabled="disabled"
									id="companyName" name="companyName" >
							</dd>
						</dl>
						<dl class="form-item">
			                <dt class="title">简称：</dt>
			                <dd class="detail">
			                    <input class="zui-input" value="${otherCooperaterVo.shortName }" disabled="disabled"
									id="shortName" name="shortName" >
			                </dd>
			            </dl>
						<dl class="form-item">
							<dt class="title">类别：</dt>
							<dd class="detail">
								<input class="zui-input" value="${otherCooperaterVo.companyTypeName }" disabled="disabled"
									id="companyType" name="companyType" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">上级名称：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.parentOrg }" disabled="disabled"
									id="parentOrg" name="parentOrg" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">合作单位归属：</dt>
							<dd class="detail">
								<input class="zui-input" value="${otherCooperaterVo.companyBelongName }" disabled="disabled"
									id="contactCompanyBelong" name="contactCompanyBelong" style="width:94px">
			                    <input class="zui-input nwidth2" disabled="disabled"
									id="companyBelongRelevanceName" name="companyBelongRelevanceName" value="${otherCooperaterVo.companyBelongRelevanceName }" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">电话：</dt>
							<dd class="detail">
                               		<input class="zui-input" value="${otherCooperaterVo.telephone }" disabled="disabled"
									id="telephone" name="telephone" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">传真：</dt>
							<dd class="detail">
                               		<input class="zui-input" value="${otherCooperaterVo.fax }" disabled="disabled"
									id="fax" name="fax" >
							</dd>
						</dl>
						<dl class="form-item">
							<dt class="title">邮政编码：</dt>
							<dd class="detail">
									<input class="zui-input" value="${otherCooperaterVo.zipCode }" disabled="disabled"
									id="zipCode" name="zipCode" >
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
									<input class="zui-input" value="${otherCooperaterVo.isStopName }" disabled="disabled"
									id="isStop" name="isStop" >
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
			                            <input id="region" class="zui-input zui-validatebox"  type="text" disabled="disabled" style="width: 190px;" validate-type="Require"/>
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
			                <dt class="title">税号：</dt>
			                <dd class="detail">
			                    <input class="zui-input" value="${otherCooperaterVo.taxNo }" disabled="disabled"
									id="taxNo" name="taxNo" >
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
			                    <input class="zui-input" value="${otherCooperaterVo.industryName }" disabled="disabled"
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