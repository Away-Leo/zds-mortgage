<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-- 查册入押编辑 -->
<div id="div-detain" class="page-box">
	<div class="page-title" id="div-detain-form">查册入押</div>
	<div class="p5">
		<form id="detainForm" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="detainId" name="detainVo.id" value="${detainVo.id}">
			<input type="hidden" id="housePropertyId" name="detainVo.housePropertyId" value="${housePropertyVo.id}">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>抵押模式确认</td>
					<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
	                        <input class="zui-combobox zui-validatebox" validate-type="Require" id="detainVopledgeType" type="hidden" name="detainVo.pledgeType" value="${detainVo.pledgeType }"
		                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0052"
		                           data-valuefield="fullcode" data-textfield="name" data-toggle="combobox">
	                    </dd>
	                 </dl>   
					</td>
					<td class="td-title"></td>
					<td></td>
					<td class="td-title"></td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="td-title"><b class="c-red mr5">*</b>现抵押权人</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                       <input class="zui-combobox zui-validatebox" type="hidden" name="detainVo.presentId"
									data-url="<z:ukey key='com.cnfh.rms.casemanage.interview.findMortgageHolder' context='admin'/>&caseApplyId=${housePropertyVo.caseApplyId}"
									data-valuefield="fullcode" data-textfield="name" 
									data-defaultvalue="${detainVo.presentId }"   
									validate-type="Require">
		                    </dd>
                    	</dl> 
					</td>
					<td class="td-title"><b class="c-red mr5">*</b>抵押时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
			                    <label> 
			                    	<input class="zui-date zui-validatebox strToDate" type="text" id="detainVoPresentPledgeDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changePresentPledgeDate',dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'detainVoPredictCertifiedDate\')}'})"
			                    	validate-type="Require" readonly value="${detainVo.presentPledgeDate }")/>
									<input type="hidden" id="changePresentPledgeDate" name="detainVo.presentPledgeDate" value="${detainVo.presentPledgeDate }"/>
								</label>
		              		</dd>
	              		</dl>
					</td>
					<td class="td-title"><b class="c-red mr5">*</b>预计出他证日期</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
			                    <label> 
			                    	<input class="zui-date zui-validatebox strToDate" type="text" id="detainVoPredictCertifiedDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changePredictCertifiedDate',dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'detainVoPresentPledgeDate\')}'})"
			                    	validate-type="Require" readonly value="${detainVo.predictCertifiedDate }"/>
									<input type="hidden" id="changePredictCertifiedDate" name="detainVo.predictCertifiedDate" value="${detainVo.presentPledgeDate }"/>
								</label>
		              		</dd>
	              		</dl>
					</td>
				</tr>
				<tr>
					<td class="td-title">备注</td>
					<td colspan="5">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<textarea class="zui-area row-width  zui-validatebox" name="detainVo.remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${detainVo.remark }</textarea>
								</label>
								<div class="zd-area">
				                    <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
				                 </div>
							</dd>
						</dl>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
	var caseApplyId = '${param.caseApplyId}'; 
	seajs.use(['jquery','zd/jquery.zds.form'], function($) {
		$.ZUI.init("#div-detain");
	});
</script>