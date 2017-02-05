<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    

<div id="div-detain" class="page-box">
	<div class="page-title" id="div-detain-form">查册入押</div>
	<div class="p5">
		<form id="detain" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="detainVohouseId" name="housePropertyId" value="${housePropertyVo.id}">
			<table class="table-detail">
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>抵押模式确认</td>
					<td class="pct20">
					<dl class="form-item form-auto">
						<dd class="detail">
	                        <input class="zui-combobox zui-validatebox" validate-type="Require" id="detainVopledgeType" type="hidden" name="pledgeType" value="${detainVo.pledgeType }"
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
					<td class="td-title">现抵押权人</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                       <input class="zui-input" type="text" id = "detainVopresentName" name="presentName" value="${detainVo.presentName }">
		                    </dd>
                    	</dl> 
					</td>
					<td class="td-title">抵押时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
			                    <label> 
			                    	<input class="zui-date zui-validatebox strToDate" type="text" id="detainVoPresentPledgeDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changePresentPledgeDate',dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'detainVoPredictCertifiedDate\')}'})" validate-type="Require" readonly value="${detainVo.presentPledgeDate }")/>
									<input type="hidden" id="changePresentPledgeDate" name="presentPledgeDate" value="${detainVo.presentPledgeDate }"/>
								</label>
		              		</dd>
	              		</dl>
					</td>
					<td class="td-title">预计出他证日期</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
			                    <label> 
			                    	<input class="zui-date zui-validatebox strToDate" type="text" id="detainVoPredictCertifiedDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changePredictCertifiedDate',dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'detainVoPresentPledgeDate\')}'})" validate-type="Require" readonly value="${detainVo.predictCertifiedDate }"/>
										<input type="hidden" id="changePredictCertifiedDate" name="predictCertifiedDate" value="${detainVo.presentPledgeDate }/>
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
										<textarea class="zui-area row-width" name="mo" 
														placeholder="最多可以输入500个字符">${detainVo.mo }</textarea>
									</label>
								</dd>
							</dl>
						</td>
				</tr>
			</table>
		</form>
	</div>
</div>

