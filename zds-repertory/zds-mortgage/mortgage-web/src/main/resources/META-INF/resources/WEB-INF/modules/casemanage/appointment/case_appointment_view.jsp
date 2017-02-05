<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约详情</title>

<%@ include file="../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class ="frm-content">
	<input type="hidden" id="id" name="id" value="${caseApplyId }">
		<div class="page-box">
	        <h1 class="page-title">已预约人数</h1>
			<div class="p10">
	               <div id="tb_appointmentPersonCount" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.appointment.getAppointmentPersonCount" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":false, "idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
		                <table>
		                	<thead>
		                		<tr>
			                        <th data-options="field:date" >日期</th>
			            			<th data-options="field:morning" >上午预约案件</th>
		            				<th data-options="field:afternoon" >下午预约案件</th>
		            			</tr>
		            		</thead>
		               	</table>
	           		</div>
			</div>
		</div>
		<br>
		
		<form id="appoint_form" class="zui-form " method="post" enctype="multipart/form-data">
			<div class="page-box">
				<div class="page-title">预约客户</div>
	   			 <div class="p5">
						<div id="tb_appointmentClient" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.appointment.appointmentClient" context="admin"/>&jsoncallback=?&id=${caseApplyId }","singleSelect":true,"pagination":false, "idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
							<table>
								<thead>
									<tr>
										<th data-options="field:joinType">参与类型</th>
				            			<th data-options="field:customerName">姓名</th>
				            			<th data-options="field:contactType">联系类型</th>
				            			<th data-options="field:phoneNumber">电话号码</th>
									</tr>
								</thead>
							</table>
						</div>
				<table class="table-detail">
					<tr>
						<td class="td-title pct10" >预约面签时间</td>
						<td class="pct10">
							<input type="text" disabled="true" class="zui-date strToDate  zui-validatebox" id="IsInterviewDate" value="${appointmentVo.interviewDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})"></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
				                       <input data-choose="disable" class="zui-combobox zui-validatebox" id="interviewAmOrPm" type="hidden" name="interviewAmOrPm" value="${appointmentVo.interviewAmOrPm }"
				                        data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
				                         data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059" >
								</dd>
							</dl>
						</td>
				        <td class="pct70"></td>
					</tr>
					<tr>
						<td class="td-title pct10" >预计办理抵押</td>
						<td class="pct10">
							<input type="text" disabled="true" class="zui-date strToDate  zui-validatebox" id="IsInterviewDate" value="${appointmentVo.mortgageDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})"></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                        <input data-choose="disable" class="zui-combobox zui-validatebox" id="mortgageDateAmOrPm" type="hidden" name="mortgageDateAmOrPm" value="${appointmentVo.mortgageDateAmOrPm }"
			                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
			                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
								</dd>
							</dl>
						</td>
			            <td class="pct70"></td>
					</tr>
					<tr>
						<td class="td-title pct10" >预计公证时间</td>
						<td class="pct10">
							<input type="text" disabled="true" class="zui-date strToDate  zui-validatebox" id="notarizationDate" value="${appointmentVo.notarizationDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})"></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                        <input data-choose="disable" class="zui-combobox zui-validatebox" id="notarizationAmOrPm" type="hidden" name="notarizationAmOrPm" value="${appointmentVo.notarizationAmOrPm }"
			                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
			                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
								</dd>
							</dl>
						</td>
			            <td class="pct70"></td>
					</tr>
					<tr>
						<td class="td-title pct10" >预计委托时间</td>
						<td class="pct10">
							<input type="text" disabled="true" class="zui-date strToDate  zui-validatebox" id="entrustDate" value="${appointmentVo.entrustDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})"></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                         <input data-choose="disable" class="zui-combobox zui-validatebox" id="entrustAmOrPm" type="hidden" name="entrustAmOrPm" value="${appointmentVo.entrustAmOrPm }"
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
			                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
								</dd>
							</dl>
						</td>
			            <td class="pct70"></td>
					</tr>
			 		<tr>
			            <td class="td-title">备注</td>
			            <td colspan="5">
			                <label>
			                    <textarea disabled="true" class="zui-area row-width" placeholder="最多可以输入500个字符" id="mo" name="mo" value="${appointmentVo.mo }" validate-type="Require,Length[0-500]">${appointmentVo.mo }</textarea>
			                </label>
			            </td>
			        </tr>
				</table>
				<br>
				<div class="page-box">
					<div class="page-title">预约提醒</div>
		    		<div class="p5">
						<table class="table-detail">
							<tr>
				                <td class="td-title">收件人</td>
				                <td colspan="5">
				                	<dl>
				                		<dd>
				                			<label>
				                				${appointmentVo.recipients }
				                			</label>
				                		</dd>
				                	</dl>
				                </td>
				     		</tr>
							<tr>
				                <td class="td-title">发送内容</td>
				                <td colspan="10">
				                	<label>
				                		<textarea disabled="true" class="zui-area row-width" placeholder="最多可以输入500个字符" name="sendtContent" value="${appointmentVo.sendtContent }">${appointmentVo.sendtContent }</textarea>
				                	</label>
				                </td>
				     		</tr>
							<tr>
				                <td class="td-title"><b class="c-red mr5"></b>提醒方式:</dt>
				                <td class="detail">
				                	<dl>
				                		<dd>
				                			<lable>
							                    <input data-choose="disable" class="zui-checkbox zui-validatebox" id="remindWay" name="remindWay" type="hidden" data-multiple="false"
							                           data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0062" name="remindWay"
							                           data-valuefield="fullcode" data-textfield="name" value="${appointmentVo.remindWay }" >
				                			</lable>
				                		</dd>
				                	</dl>
				                </td>
							</tr>
							
						</table>
					</div>
				</div>
		
			</div>
		</div>
	</form>

</div>

<div class="save">
		<button id="btn-back" class="btn-blue mr10">返回</button>
</div>


</body>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, Loading, ZUI_MESSAGE_CLIENT) {
			$.ZUI.init();
	
			$("#btn-back").click(function(){
				setTimeout(function(){
                 	ZDS_MESSAGE_CLIENT.closeSelf();
                 },200);
			});
			
						
		});	
</script>

</html>