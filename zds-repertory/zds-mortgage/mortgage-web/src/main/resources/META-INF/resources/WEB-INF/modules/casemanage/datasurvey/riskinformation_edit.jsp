<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 风险信息 -->
<c:if test="${not empty errorMsg }">页面初始化出现异常：${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
	<div class="frm-content frm-bottom" id="opinionDiv">
		<form id="riskInfo_form" class="zui-form" action="javascript:void(0);">
			<input type="hidden" name="id" value="${vo.id}">
			<input type="hidden" name="riskInfoVo.id" value="${vo.riskInfoVo.id}">
			<div class="page-box">
				<div class="page-title">基本信息</div>
				<div class="p5">
					<table class="table-detail">
					<input type="hidden" name="productTypeId" value="${vo.productTypeId}" />
					<input type="hidden" name="productSubtypeId" value="${vo.productSubtypeId}" />
						<tr>
							<td class="td-title pct15"><b class="c-red mr5">*</b>产品</td>
							<td class="pct20">
								${vo.productTypeName}--${vo.productSubtypeName}
							</td>
							<td class="td-title pct10">接单日期</td>
							<td id="fmtDate" class="pct25">${vo.applyDate} </td>
							<td class="td-title pct10"><b class="c-red mr5">*</b>贷款用途</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" type="hidden" name="capitalUseFor"
											 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0055"
											 data-defaultvalue="${vo.capitalUseFor }"
											data-valuefield="fullcode" data-textfield="name" validate-type="Require">
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title"><b class="c-red mr5">*</b>终端</td>
							<td >
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" type="hidden" name="terminalId"
											data-url="<z:ukey key='com.zdsoft.finance.cooperator.cooperatorSimpleCode' context='admin'/>&jsoncallback=?&createOrgCd=${vo.developmentDepartmentCode}"
											data-valuefield="id" data-textfield="terminalFullName" 
											data-defaultvalue="${vo.terminalId}"   
											validate-type="Require">
									</dd>
								</dl>
							</td>	
							<td class="td-title">拓展经理</td>
							<td >${vo.developmentManagerName}</td>
							<td class="td-title">拓展部门</td>
							<td>${vo.developmentDepartmentName}</td>
						</tr>
						<tr>
							<td class="td-title">资信员</td>
							<td>${vo.creditMember}</td>
							<td class="td-title">评估价抵押成数</td>
							<td>${vo.assessedPriceMortgage}
							<c:if test="${not empty vo.assessedPriceMortgage }">%</c:if>
							</td>
							<td class="td-title">贷款成数</td>
							<td>${vo.loanNumber}<c:if test="${not empty vo.loanNumber }">%</c:if></td>
						</tr>
					</table>
				</div>
				
				
			</div>
			<div class="page-box">
				<div class="page-title">风控措施</div>
				<div class="p5">
					<table class="table-detail">
						<tr>
							<td class="td-title pct15">预计查册时间</td>
							<td class="pct20">
								<dl class="form-item form-auto">
									<dd class="detail">
									<input class="zui-date zui-validatebox strToDate" type="text" id="riskInfoVo.expectSearchDate"  value="${vo.riskInfoVo.expectSearchDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectSearchDate',minDate: '%y-%M-%d'})" readonly/>
									 <input type="hidden" id="changeExpectSearchDate" value="${vo.riskInfoVo.expectSearchDate}" name="riskInfoVo.expectSearchDate"/>
									</dd>
								</dl>
							</td>

							<td class="td-title pct10"><b class="c-red mr5">*</b>提放类型</td>
							<td class="pct25">
								<dl class="form-item form-auto">
									<dd class="detail">
											 <input class="zui-combobox zui-validatebox" validate-type="Require"  type="hidden" name="riskInfoVo.putType" value="${vo.riskInfoVo.putType}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0050"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title pct10"><b class="c-red mr5">*</b>是否办理仲裁</td>
							<td class="pct20">
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
							<td class="td-title "><b class="c-red mr5">*</b>是否办理委托</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealEntrust" type="hidden" name="riskInfoVo.isDealEntrust" value="${vo.riskInfoVo.isDealEntrust}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title ">预计办理时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
								 		<input class="zui-date strToDate" type="text" id="riskInfoVo.expectEntrustDate" value="${vo.riskInfoVo.expectEntrustDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectEntrustDate',minDate: '%y-%M-%d'})" readonly /> 
										<input type="hidden" id="changeExpectEntrustDate" value="${vo.riskInfoVo.expectEntrustDate}" name="riskInfoVo.expectEntrustDate"/>
										
									</dd>
								</dl>
							</td>
							<td class="td-title "></td>
							<td></td>
						</tr>
						<tr>
						
							<td class="td-title "><b class="c-red mr5">*</b>是否办理债权公证</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealNotarial" type="hidden" name="riskInfoVo.isDealNotarial" value="${vo.riskInfoVo.isDealNotarial}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title ">预计办理时间</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-date strToDate" type="text" id="riskInfoVo.expectNotarialDate" validate-type="Require" value="${vo.riskInfoVo.expectNotarialDate}"
											onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeExpectNotarialDate',minDate: '%y-%M-%d'})" readonly/>
										 <input type="hidden" id="changeExpectNotarialDate" value="${vo.riskInfoVo.expectNotarialDate}" name="riskInfoVo.expectNotarialDate"/>
									</dd>
									
								</dl>
							</td>
							<td class="td-title "></td>
							<td></td>
						</tr>
						<tr>
							<td class="td-title "><b class="c-red mr5">*</b>是否办理可交易全委</td>
							<td>
								<dl class="form-item form-auto">
									<dd class="detail">
										<input class="zui-combobox zui-validatebox" validate-type="Require" id="riskInfoVo.isDealTradable" type="hidden" name="riskInfoVo.isDealTradable" value="${vo.riskInfoVo.isDealTradable}"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
				                           data-valuefield="fullcode" data-textfield="name" >
									</dd>
								</dl>
							</td>
							<td class="td-title "></td>
							<td></td>
							<td class="td-title "></td>
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
							<td class="td-title pct15">抵押物综述</td>
							<td colspan="5">
								<label> 
									<textarea class="zui-area row-width zui-disabled" readonly name="riskInfoVo.pledgeReview" >${vo.riskInfoVo.pledgeReview}</textarea>
								</label>
							</td>
						</tr>
						<tr>
							<td class="td-title pct15">业务综析</td>
							<td colspan="5"><label> <textarea class="zui-area row-width zui-disabled" readonly  name="riskInfoVo.businessAnalysis" >${vo.riskInfoVo.businessAnalysis}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct15">特殊情况</td>
							<td colspan="5"><label> <textarea class="zui-area  row-width zui-disabled" readonly name="riskInfoVo.specialSituation" >${vo.riskInfoVo.specialSituation}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct15">经营情况/工作情况</td>
							<td colspan="5"><label> <textarea class="zui-area row-width zui-disabled" readonly name="riskInfoVo.workSituation">${vo.riskInfoVo.workSituation}</textarea>
							</label></td>
						</tr>
						<tr>
							<td class="td-title pct15">其他</td>
							<td colspan="5"><label> <textarea class="zui-area  row-width zui-validatebox"  validate-place="true"  name="riskInfoVo.remark" validate-type="Length[0-250]" placeholder="最多可以输入250个字符">${vo.riskInfoVo.remark}</textarea>
							</label></td>
						</tr>
					</table>

				</div>
			</div>
		</form>

	</div>
</c:if>
<script>
	seajs.use([ 'jquery', 'zd/iframe', 'zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.button', 'zd/jquery.zds.seleter','zd/jquery.zds.address','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/make-first-py' ], 
			function($,IFRAME, ZTOOlS,CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			
			var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
			
			//格式化时间
		 	$("#fmtDate").html(ZTOOlS.strToDate($("#fmtDate").html()));
			$.ZUI.init("#opinionDiv");
		});
</script>
</body>
</html>