<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class ="frm-content">
	<div class="page-box">
		<form class="zui-form" id="formDiv">
		<input type="hidden" name="caseApplyId" value="${caseApplyId}">
		<div class="page-title">档案入库</div>
 		<div class="p5">
 			<div id="tb_caseAppointment" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.finance.financialReview.archivesListData" context="admin"/>&caseApplyId=${caseApplyId}","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">名称</th>
            			<th data-options="field:CUSTOMERNAME">档案等级</th>
            			<th data-options="field:ACTUALAPPLYAMOUNT">数量</th>
            			<th data-options="field:INTERVIEWDATE">原件/复印件/照片件</th>
            			<th data-options="field:MORTGAGEDATE" >档案柜号</th>
            			<th data-options="field:NOTARIZATIONDATE" >入库人</th>
            			<th data-options="field:ENTRUSTDATE" >入库时间</th>
            			<th data-options="field:PHONENUMBER">状态</th>
			        </tr>
				</table>
			</div>
 		</div>
 		<div class="p5">
 			<table class="table-detail">
 				<tr>
 					<td class="td-title pct10" >是否收齐资料</td>
 					<td class="pct90">
 						<dl class="form-item">
							<dd class="detail">
		                         <input class="zui-combobox zui-validatebox" id="isAllCollectInformation" type="hidden" name="isAllCollectInformation" value="${reviewOfArchivesVo.isAllCollectInformation}"
		                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		                          data-valuefield="fullcode" data-textfield="name">
							</dd>
						</dl>
 					</td>
 				</tr>
 				<tr>
 					<td class="td-title pct10" >备注</td>
 					<td class="pct90">
 						 <label>
				             <textarea class="zui-area row-width" placeholder="最多可以输入500个字符" id="remark" name="remark" value="${reviewOfArchivesVo.remark }" validate-type="Length[0-500]">${reviewOfArchivesVo.remark}</textarea>
				         </label>	
 					</td>
 				</tr>
 			</table>
 		</div>
 		</form>
	</div>
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK) {


	//初始化页面
	$.ZUI.init();
});
</script>