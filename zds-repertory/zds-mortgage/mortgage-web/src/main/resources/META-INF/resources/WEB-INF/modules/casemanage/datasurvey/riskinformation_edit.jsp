<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file="../../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风险信息</title>
</head>
<body id="TreeBody">
<c:if test="${not empty errorMsg }">页面初始化出现异常，请联系管理员！异常信息为：${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
	<div class="frm-content frm-bottom" id="opinionDiv">
		<form id="riskInfo_form" class="zui-form" action="javascript:void(0);">
			<input type="hidden" name="id" value="${vo.id}">
			<input type="hidden" name="riskInfoVo.id" value="${vo.riskInfoVo.id}">
			<div class="page-box">
				<div class="page-title">基本信息</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10"><b class="c-red mr5">*</b>产品</td>
							<td>${vo.productTypeName}-${vo.productSubtypeName}</td>
							<td class="td-title pct10">接单日期</td>
							<td><span  id="fmtDate">${vo.applyDate}</span> 
							</td>
							<td class="td-title pct10">贷款用途</td>
							<td>${vo.capitalUseForName }</td>
						</tr>
						<tr>
							<td class="td-title pct10">终端</td>
							<td>${vo.terminalIdName }	
							</td>
							<td class="td-title pct10">拓展经理</td>
							<td>${vo.developmentManagerName}</td>
							<td class="td-title pct10">拓展部门</td>
							<td>${vo.developmentDepartmentName}</td>
						</tr>
						<tr>
							<td class="td-title pct10">资信员</td>
							<td>${vo.creditMember}</td>
							<td class="td-title pct10">评估价抵押成数</td>
							<td>${vo.assessedPriceMortgage}</td>
							<td class="td-title pct10">贷款成数</td>
							<td>${vo.loanNumber}</td>
						</tr>
					</table>
				</div>
				
				
			</div>
			<div class="page-box">
				<div class="page-title">风控措施</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">预计查册时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
									<input class="zui-date zui-validatebox strToDate" type="text" id="riskInfoVo.expectSearchDate"  value="${vo.riskInfoVo.expectSearchDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectSearchDate'})" readonly/>
									 <input type="hidden" id="changeExpectSearchDate" value="${vo.riskInfoVo.expectSearchDate}" name="riskInfoVo.expectSearchDate"/>
									</dd>
								</dl>
							</td>

							<td class="td-title pct10"><b class="c-red mr5">*</b>提放类型</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
											 <input class="zui-combobox zui-validatebox" validate-type="Require"  type="hidden" name="riskInfoVo.putType" value="${vo.riskInfoVo.putType}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0050"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"><b class="c-red mr5">*</b>是否办理仲裁</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
											 <input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealArbitration" type="hidden" name="riskInfoVo.isDealArbitration" value="${vo.riskInfoVo.isDealArbitration}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title pct10"><b class="c-red mr5">*</b>是否办理委托</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealEntrust" type="hidden" name="riskInfoVo.isDealEntrust" value="${vo.riskInfoVo.isDealEntrust}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">预计办理时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
								 		<input class="zui-date strToDate" type="text" id="riskInfoVo.expectEntrustDate" value="${vo.riskInfoVo.expectEntrustDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectEntrustDate'})" readonly /> 
										<input type="hidden" id="changeExpectEntrustDate" value="${vo.riskInfoVo.expectEntrustDate}" name="riskInfoVo.expectEntrustDate"/>
										
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td></td>
						</tr>
						<tr>
						
							<td class="td-title pct10"><b class="c-red mr5">*</b>是否办理债权公证</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealNotarial" type="hidden" name="riskInfoVo.isDealNotarial" value="${vo.riskInfoVo.isDealNotarial}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10">预计办理时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-date strToDate" type="text" id="riskInfoVo.expectNotarialDate" validate-type="Require" value="${vo.riskInfoVo.expectNotarialDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectNotarialDate'})"/>
										 <input type="hidden" id="changeExpectNotarialDate" value="${vo.riskInfoVo.expectNotarialDate}" name="riskInfoVo.expectNotarialDate"/>
									</dd>
									
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td></td>
						</tr>
						<tr>
							<td class="td-title pct10"><b class="c-red mr5">*</b>是否办理可交易全委</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealTradable" type="hidden" name="riskInfoVo.isDealTradable" value="${vo.riskInfoVo.isDealTradable}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"></td>
							<td></td>
							<td class="td-title pct10"></td>
							<td></td>
						</tr>
					</table>

				</div>
			</div>

			<div class="page-box">
				<div class="page-title">风控评价</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct10">抵押物综述</td>
							<td colspan="5"><label> <textarea class="zui-area zui-validatebox row-width" validate-type="Require,Length[0-500]" name="riskInfoVo.pledgeReview" placeholder="最多可以输入500个字符">${vo.riskInfoVo.pledgeReview}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct10">业务综析</td>
							<td colspan="5"><label> <textarea class="zui-area zui-validatebox row-width" validate-type="Require,Length[0-500]"  name="riskInfoVo.businessAnalysis" placeholder="最多可以输入500个字符">${vo.riskInfoVo.businessAnalysis}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct10">特殊情况</td>
							<td colspan="5"><label> <textarea class="zui-area zui-validatebox row-width"  validate-type="Require,Length[0-500]"  name="riskInfoVo.specialSituation" placeholder="最多可以输入500个字符">${vo.riskInfoVo.specialSituation}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct10">经营情况/工作情况</td>
							<td colspan="5"><label> <textarea class="zui-area zui-validatebox row-width" validate-type="Require,Length[0-500]"  name="riskInfoVo.workSituation" placeholder="最多可以输入500个字符">${vo.riskInfoVo.workSituation}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct10">其他</td>
							<td colspan="5"><label> <textarea class="zui-area zui-validatebox row-width" validate-type="Require,Length[0-500]"  name="riskInfoVo.mo" 
										placeholder="最多可以输入500个字符">${vo.riskInfoVo.mo}</textarea>
							</label></td>
						</tr>
					</table>

				</div>
			</div>
		</form>

	</div>
	</c:if>
	<script>
	var productUrl =  '<z:ukey key="com.zdsoft.finance.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
		seajs.use([ 'jquery', 'zd/iframe', 'zd/tools', 'zd/jquery.zds.form','zd/jquery.zds.button', 'zd/jquery.zds.seleter','zd/jquery.zds.address','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/make-first-py' ], function($,
				IFRAME, ZTOOlS,CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			//格式化时间
			 $("#fmtDate").html(ZTOOlS.strToTime($("#fmtDate").html()));

				// 获取贷款基本申请数据
				function getriskInfoVo() {
					return $('#riskInfo_form').serialize();
				}
				CALLBACK.productTypeChange = function(v,t){
					$("#productSubtypeId").ZCombobox({
		       		 	valueField: "id",
		                textField: "value",
		                url: productUrl+"&categoryId="+v
		       		});
				}
				 //保存
				window.saveData = function(){
				var status=false;
					//校验
					var isValidateriskInfoVo = $.ZUI.validateForm($('#riskInfo_form'));
					if(!isValidateriskInfoVo){
						$.ZMessage.info("提示", "请完善必填项信息！", function () {
	                    });	 
						return false;
					}
						var riskInfoVo = getriskInfoVo();
						//保存
						$.ajax({
	                        type: 'post',
	                        url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.riskinformation.save" context="admin"/>',
	                        data: riskInfoVo,
	                        dataType: 'json',
	                        success: function (data) {
	                            if (data.resultStatus == 0) {
	                            	$.ZMessage.success("成功", data.msg, function () {
	                            		status=true;
	                              	 });
	                            	
	                            }else{
	                            	status=false;
	                            }
	                        },
					            error: function () {
					            	status=false;
					            }
	                    });
					return status;
				 }
		
		
			
			$.ZUI.initForms('#opinionDiv');
			$.ZUI.init();

		});
	</script>

</body>
</html>