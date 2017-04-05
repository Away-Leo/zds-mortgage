<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 案件跟踪信息录入 -->
<div id="div-tail" class="page-box">
	<div class="p5">
		<form id="tailForm" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="caseTailVoId" name="id" value="${caseTailVo.id}">
			<input type="hidden" id="caseTailCaseApplyId" name="caseApplyId" value="${beforehandApplyVo.id}">
			<input type="hidden" id="marketingPersonId" name="marketingPersonId" value="${caseTailVo.marketingPersonId}">
			<table class="table-detail">
				<tr>
					<td class="td-title">营销人员</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                       <input class="zui-input zui-validatebox" type="text" validate-type="Require" id = "marketingPersonName" name="marketingPersonName" value="${caseTailVo.marketingPersonName }" readonly>
		                    </dd>
                    	</dl> 
					</td>
					<td class="td-title"><b class="c-red mr5">*</b>跟踪时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
			                    <label> 
			                    	<input class="zui-date zui-validatebox strToDate" type="text" id="tailDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changetailDate',dateFmt:'yyyy-MM-dd'})"
			                    	validate-type="Require" readonly value="${caseTailVo.tailDate }")/>
									<input type="hidden" id="changetailDate" name="tailDate" value="${caseTailVo.tailDate  }"/>
								</label>
		              		</dd>
	              		</dl>
					</td>
					<td class="td-title"></td>
					<td></td>
				</tr>
				<tr>
					<td class="td-title"><b class="c-red mr5">*</b>跟踪内容</td>
					<td colspan="5">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<textarea class="zui-area row-width zui-validatebox" validate-type="Require,Length[1-500]" 
									name="tailContent" placeholder="最多可以输入500个字符" id="tailRemark">${caseTailVo.tailContent }</textarea>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>