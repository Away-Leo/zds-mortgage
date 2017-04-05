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
			<form id="appoint_form" class="zui-form " method="post" enctype="multipart/form-data">
				<div class="page-box">
					<div class="page-title">已预约人数</div>
					<div class="p5">
			               <div id="tb_appointmentPersonCount" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.booking.bookingtPersonCount" context="admin"/>","singleSelect":true,"pagination":false, "idField":"id","tableCls":"table-index"}'>
				                <table>
				                	<thead>
				                		<tr>
					                        <th data-options="field:MIDATE" formatter="dateFormatterShow" >日期</th>
					            			<th data-options="field:MORNING" >上午预约案件</th>
				            				<th data-options="field:AFTERNOON" >下午预约案件</th>
				            			</tr>
				            		</thead>
				               	</table>
			           		</div>
					</div>
				</div>
				<div class="page-box">
					<div class="page-title">预约客户</div>
		   			<div class="p5">
						<div id="tb_appointmentClient" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.booking.bookingClient" context="admin"/>&id=${caseApplyId }","singleSelect":true,"pagination":false, "idField":"id","tableCls":"table-index"}'>
							<table>
								<thead>
									<tr>
										<th data-options="field:JOINTYPE">参与类型</th>
				            			<th data-options="field:CUSTOMERNAME">姓名</th>
				            			<th data-options="field:CONTACTTYPE">联系类型</th>
				            			<th data-options="field:PHONENUMBER">电话号码</th>
									</tr>
								</thead>
							</table>
						</div>
						<table class="table-detail">
							<tr>
								<td class="td-title pct10" >预约面签时间</td>
								<td class="pct90">
									 <span id="interviewDate"></span> ${bookingVo.interviewAmOrPmName }
								</td>
							</tr>
							<tr>
								<td class="td-title pct10" >预计办理抵押</td>
								<td class="pct90">
									<span id="mortgageDate"></span> ${bookingVo.mortgageDateAmOrPmName }
								</td>
							</tr>
							<tr>
								<td class="td-title pct10" >预计公证时间</td>
								<td class="pct90">
									<span id="notarizationDate"></span> ${bookingVo.notarizationAmOrPmName }
								</td>
							</tr>
							<tr>
								<td class="td-title pct10" >预计委托时间</td>
								<td class="pct90">
									<span id="entrustDate"></span> ${bookingVo.entrustAmOrPmName }
								</td>
							</tr>
					 		<tr>
					            <td class="td-title">备注</td>
					            <td class="pct90">
					                  ${bookingVo.remark }
					            </td>
					        </tr>
					</table>
				</div>
			</div>
			<div class="page-box">
				<div class="page-title">预约提醒</div>
	    		<div class="p5">
					<table class="table-detail">
						<tr>
			                <td class="td-title pct10">收件人</td>
			                <td >
			                	${bookingVo.recipients }
			                </td>
			                <td class="td-title pct10">提醒方式:</td>
			                <td>${bookingVo.remindWayName }</td>
			     		</tr>
						<tr>
			                <td class="td-title pct10">发送内容</td>
			                <td colspan="3" class="pct90">
			                	${bookingVo.sendtContent }
			                </td>
			     		</tr>
					</table>
				</div>
			</div>
			</form>
</div>
</body>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK,ZTOOL,Loading, ZUI_MESSAGE_CLIENT) {
			//时间格式化
			CALLBACK.dateFormatterShow = function(index,value){
				if(value){
					return window.formatDate(index,value);
				}else{
					return '';
				}
			}; 
			
			$("#interviewDate").text(ZTOOL.strToDate(${bookingVo.interviewDate }+""));
			$("#mortgageDate").text(ZTOOL.strToDate(${bookingVo.mortgageDate }+""));
			$("#notarizationDate").text(ZTOOL.strToDate(${bookingVo.notarizationDate }+""));
			$("#entrustDate").text(ZTOOL.strToDate(${bookingVo.entrustDate }+""));
			
			//初始化页面
			$.ZUI.init();
						
		});	
</script>
</html>