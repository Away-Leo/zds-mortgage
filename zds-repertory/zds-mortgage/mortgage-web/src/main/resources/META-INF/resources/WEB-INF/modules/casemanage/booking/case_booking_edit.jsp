<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约界面</title>
<%@ include file="../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class ="frm-content frm-bottom">
		<form id="appoint_form" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="caseApplyId" name="caseApplyId" value="${caseApplyId}">
			<input type="hidden" id="id" name="id" value="${bookingVo.id}">
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
							<td class="pct10">
									<input type="text" class="zui-date strToDate  zui-validatebox" id="IsInterviewDate" value="${bookingVo.interviewDate }" onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
					                     <input type="hidden" id="interviewDate" name="interviewDate" value="${bookingVo.interviewDate }" /></td>
							<td colspan="2">
								<dl class="form-item">
									<dd class="detail">
					                       <input class="zui-combobox zui-validatebox" id="interviewAmOrPm" type="hidden" name="interviewAmOrPm" value="${bookingVo.interviewAmOrPm }"
					                        data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
					                         data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059" >
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title" >预计办理抵押</td>
							<td>
									<input type="text" class="zui-date strToDate zui-validatebox" id="IsMortgageDate" value="${bookingVo.mortgageDate }" onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',vel:'mortgageDate', dateFmt:'yyyy-MM-dd',minDate:new Date() })">
				                       	<input type="hidden" id="mortgageDate" name="mortgageDate" value="${bookingVo.mortgageDate }"/></td>
							<td >
								<dl class="form-item">
									<dd class="detail">
				                        <input class="zui-combobox zui-validatebox" id="mortgageDateAmOrPm" type="hidden" name="mortgageDateAmOrPm" value="${bookingVo.mortgageDateAmOrPm }"
				                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
				                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title" >预计公证时间</td>
							<td >
									<input type="text" class="zui-date strToDate zui-validatebox" id="IsNotarizationDate" value="${bookingVo.notarizationDate }" onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',vel:'notarizationDate', dateFmt:'yyyy-MM-dd',minDate:new Date()})">
				                        <input type="hidden" id="notarizationDate" name="notarizationDate" value="${bookingVo.notarizationDate }"/></td>
							<td >
								<dl class="form-item">
									<dd class="detail">
				                        <input class="zui-combobox zui-validatebox" id="notarizationAmOrPm" type="hidden" name="notarizationAmOrPm" value="${bookingVo.notarizationAmOrPm }"
				                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
				                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
									</dd>
								</dl>
							</td>
						</tr>
						<tr>
							<td class="td-title" >预计委托时间</td>
							<td >
									<input type="text" class="zui-date strToDate zui-validatebox" id="IsEntrustDate" value="${bookingVo.entrustDate }" onclick="WdatePicker({readOnly:true,realDateFmt:'yyyyMMdd',vel:'entrustDate', dateFmt:'yyyy-MM-dd',minDate:new Date()})">
				                        <input type="hidden" id="entrustDate" name="entrustDate" value="${bookingVo.entrustDate }"/></td>
							<td>
								<dl class="form-item">
									<dd class="detail">
				                         <input class="zui-combobox zui-validatebox" id="entrustAmOrPm" type="hidden" name="entrustAmOrPm" value="${bookingVo.entrustAmOrPm }"
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
				                          data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051059">
									</dd>
								</dl>
							</td>
						</tr>
				 		<tr>
				            <td class="td-title">备注</td>
				            <td colspan="2">
				                <label>
				                    <textarea class="zui-area row-width zui-validatebox" placeholder="最多可以输入250个字符" id="remark" name="remark" value="${bookingVo.remark }" validate-type="Length[0-250]">${bookingVo.remark}</textarea>
				                </label>
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
			                <td class="pct90" colspan="2">
			                	<dl>
			                		<dd>
			                			<label>
			                				<input class="zui-input zui_disabled"  value="${customerName}"
												id="recipients" name="recipients" readonly="readonly" >
			                			</label>
			                			<label hidden="true">
			                				<input class="zui-input" value="${phoneNumber}"
												id="phoneNumber" name="phoneNumber"  type="hidden">
			                			</label>
			                			<label hidden="true">
			                				<input class="zui-input" value="${email}"
												id="emailAddress" name="emailAddress"  type="hidden">
			                			</label>
			                		</dd>
			                	</dl>
			                </td>
			     		</tr>
						<tr>
			                <td class="td-title pct10">发送内容</td>
			                <td class="pct90" colspan="2">
			                	<label>
			                		<textarea class="zui-area row-width zui-validatebox" placeholder="最多可以输入250个字符" validate-type="Length[0-250]"  id="sendtContent" name="sendtContent" value="${bookingVo.sendtContent }">${bookingVo.sendtContent }</textarea>
			                	</label>
			                </td>
			     		</tr>
						<tr>
			                <td class="td-title pct10"><b class="c-red mr5"></b>提醒方式:</dt>
			                <td class="pct90" colspan="2">
			                	<dl>
			                		<dd>
			                			<lable>
						                    <input class="zui-checkbox zui-validatebox" id="remindWay" name="remindWay" type="hidden" data-multiple="false"
						                           data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0062" name="remindWay"
						                           data-valuefield="fullcode" data-textfield="name" value="${bookingVo.remindWay }" >
			                			</lable>
			                		</dd>
			                	</dl>
			                </td>
						</tr>
					</table>
				</div>
			</div>
		</form>
 </div>
<div class="save">
		<button id="btn-save" type="button" class="btn-blue mr10">保存</button>
		<button id="btn-cancel" type="button" class="btn-blue mr10">取消</button>
</div>
</body>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.loading','zd/jquery.zds.dialog','zd/jquery.zds.message','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, Loading, ZUI_MESSAGE_CLIENT) {
			//时间格式化
			CALLBACK.dateFormatterShow = function(index,value){
				if(value){
					return window.formatDate(index,value);
				}else{
					return '';
				}
			}; 
						
			$.ZUI.init();
			$("#btn-save").click(function(){
				var appointmentVo = $("#appoint_form").serialize();
				var interviewDate = $("#interviewDate").val();
				var mortgageDate = $("#mortgageDate").val();
				var notarizationDate = $("#notarizationDate").val();
				var entrustDate = $("#entrustDate").val();
				if(!( interviewDate || mortgageDate || notarizationDate || entrustDate)){
					$.ZMessage.info("提示", "请您至少选择一项预约！", function () {
                    });	 
					return false;
				}
				var params = appointmentVo;
				Loading.show();
				//保存案件预约
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.cnfh.rms.casemanage.booking.saveOrUpdateBooking" context="admin"/>',
                    data: params,
                    dataType: 'json',
                    success: function (data) {
                    	Loading.hide();
                        if (data.resultStatus == 0) {
                        	$.ZMessage.success("提示", "案件预约成功", function () {
                         		setTimeout(function(){
                                 	ZDS_MESSAGE_CLIENT.refreshOpenner();
                                 	ZDS_MESSAGE_CLIENT.closeSelf();
                                 },200);
                          	 });
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                        });
                        }
                    },
			         error: function () {
			            	Loading.hide();
			            	$.ZMessage.error("错误", "案件预约信息系统异常，请联系管理员", function () {
			             });
			            }
                }); 
			});
			
			$("#btn-cancel").click(function(){
				setTimeout(function(){
                 	ZDS_MESSAGE_CLIENT.closeSelf();
                 },200);
			});
			
						
		});	
</script>
</html>