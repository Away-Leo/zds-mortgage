<<<<<<< HEAD
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
								<input type="text" class="zui-date strToDate  zui-validatebox" id="IsInterviewDate" value="${appointmentVo.interviewDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd',minDate:new Date() })">
				                     <input type="hidden" id="interviewDate" name="interviewDate" value="${appointmentVo.interviewDate }" /></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
				                       <input class="zui-combobox zui-validatebox" id="interviewAmOrPm" type="hidden" name="interviewAmOrPm" value="${appointmentVo.interviewAmOrPm }"
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
								<input type="text" class="zui-date strToDate zui-validatebox" id="IsMortgageDate" value="${appointmentVo.mortgageDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'mortgageDate', dateFmt:'yyyy-MM-dd',minDate:new Date() })">
			                       	<input type="hidden" id="mortgageDate" name="mortgageDate" value="${appointmentVo.mortgageDate }"/></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                        <input class="zui-combobox zui-validatebox" id="mortgageDateAmOrPm" type="hidden" name="mortgageDateAmOrPm" value="${appointmentVo.mortgageDateAmOrPm }"
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
								<input type="text" class="zui-date strToDate zui-validatebox" id="IsNotarizationDate" value="${appointmentVo.notarizationDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'notarizationDate', dateFmt:'yyyy-MM-dd',minDate:new Date()})">
			                        <input type="hidden" id="notarizationDate" name="notarizationDate" value="${appointmentVo.notarizationDate }"/></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                        <input class="zui-combobox zui-validatebox" id="notarizationAmOrPm" type="hidden" name="notarizationAmOrPm" value="${appointmentVo.notarizationAmOrPm }"
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
								<input type="text" class="zui-date strToDate zui-validatebox" id="IsEntrustDate" value="${appointmentVo.entrustDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'entrustDate', dateFmt:'yyyy-MM-dd',minDate:new Date()})">
			                        <input type="hidden" id="entrustDate" name="entrustDate" value="${appointmentVo.entrustDate }"/></td>
						<td class="pct10">
							<dl class="form-item">
								<dd class="detail">
			                         <input class="zui-combobox zui-validatebox" id="entrustAmOrPm" type="hidden" name="entrustAmOrPm" value="${appointmentVo.entrustAmOrPm }"
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
			                    <textarea class="zui-area row-width" placeholder="最多可以输入500个字符" id="mo" name="mo" value="${appointmentVo.mo }" validate-type="Require,Length[0-500]">${appointmentVo.mo }</textarea>
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
				                				<input class="zui-input" value="${customerName}"
													id="recipients" name="recipients"  type="hidden">${customerName}
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
				                <td class="td-title">发送内容</td>
				                <td colspan="10">
				                	<label>
				                		<textarea class="zui-area row-width" placeholder="最多可以输入500个字符" name="sendtContent" value="${appointmentVo.sendtContent }">${appointmentVo.sendtContent }</textarea>
				                	</label>
				                </td>
				     		</tr>
							<tr>
				                <td class="td-title"><b class="c-red mr5"></b>提醒方式:</dt>
				                <td class="detail">
				                	<dl>
				                		<dd>
				                			<lable>
							                    <input class="zui-checkbox zui-validatebox" id="remindWay" name="remindWay" type="hidden" data-multiple="false"
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
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-cancel" class="btn-blue mr10">取消</button>
</div>


</body>
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, Loading, ZUI_MESSAGE_CLIENT) {
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
				//var params = {};
				//params.id = $("#id").val();
				var params = appointmentVo;
				params += "&id="+$("#id").val();
				//保存案件预约
				$.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.casemanage.appointment.saveAppointment" context="admin"/>',
                    data: params,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	$.ZMessage.warning("提示", "案件预约成功", function () {
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

=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约界面</title>

<%@ include file="../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://192.168.18.253:8080/dev/static/assets/css/style.css"> -->
</head>
<body>
<input type="hidden" id="id" name="id" value="${caseApplyId }">
<div class ="frm-content">
	<div class="page-box">
        <h1 class="page-title">已预约人数</h1>
		<div class="p10">
               <div id="zds-table-house" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.appointment.appointmentClient" context="admin"/>&jsoncallback=?&id=${caseApplyId }","singleSelect":true,"idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
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
	<!-- 客户预约 -->
	<div class="page-box">
        <h1 class="page-title">预约客户</h1>
		<div class="p10">
			<div id="tb_AppointmentPersonCount" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.appointment.appointmentClient" context="admin"/>&jsoncallback=?&id=${caseApplyId }","singleSelect":true,"idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
				<table>
					<thead>
						<tr>
							<th data-options="field:aaa">参与类型</th>
	            			<th data-options="field:bbb">姓名</th>
	            			<th data-options="field:ccc">联系类型</th>
	            			<th data-options="field:dddd">电话号码</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
	<form id="appoint_form" class="zui-form " method="post" enctype="multipart/form-data">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10" >预约面签时间</td>
				<td class="pct10">
						<input type="text" class="zui-date zui-validatebox" id="interviewDate" value="" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd' })">
		                     <input type="hidden" id="chooseInterviewDate" name="interviewDate" value=""/></td>
				<td class="pct10">
					<dl class="form-item">
						<dd class="detail">
		                       <input class="zui-combobox zui-validatebox" id="interviewAmOrPm" type="hidden" name="interviewAmOrPm" value=""
		                        data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
		                         data-valuefield="fullcode" data-textfield="name" >
						</dd>
					</dl>
				</td>
		        <td class="pct70"></td>
			</tr>
			<tr>
				<td class="td-title pct10" >预计办理抵押</td>
				<td class="pct10">
						<input type="text" class="zui-date zui-validatebox" id="mortgageDate" value="" onclick="WdatePicker({realDateFmt:'yyyy-MM-dd',vel:'mortgageDate', dateFmt:'yyyy-MM-dd'})">
	                       	<input type="hidden" id="chooseMortgageDate" name="mortgageDate" value=""/></td>
				<td class="pct10">
					<dl class="form-item">
						<dd class="detail">
	                        <input class="zui-combobox zui-validatebox" id="mortgageDateAmOrPm" type="hidden" name="mortgageDateAmOrPm" value=""
	                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
	                          data-valuefield="fullcode" data-textfield="name">
						</dd>
					</dl>
				</td>
	            <td class="pct70"></td>
			</tr>
			<tr>
				<td class="td-title pct10" >预计公证时间</td>
				<td class="pct10">
						<input type="text" class="zui-date zui-validatebox" id="notarizationDate" value="" onclick="WdatePicker({realDateFmt:'yyyy-MM-dd',vel:'notarizationDate', dateFmt:'yyyy-MM-dd'})">
	                        <input type="hidden" id="chooseNotarizationDate" name="notarizationDate" value=""/></td>
				<td class="pct10">
					<dl class="form-item">
						<dd class="detail">
	                        <input class="zui-combobox zui-validatebox" id="notarizationAmOrPm" type="hidden" name="notarizationAmOrPm" value=""
	                         data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
	                          data-valuefield="fullcode" data-textfield="name">
	                          
						</dd>
					</dl>
				</td>
	            <td class="pct70"></td>
			</tr>
			<tr>
				<td class="td-title pct10" >预计委托时间</td>
				<td class="pct10">
						<input type="text" class="zui-date zui-validatebox" id="entrustDate" value="" onclick="WdatePicker({realDateFmt:'yyyy-MM-dd',vel:'entrustDate', dateFmt:'yyyy-MM-dd'})">
	                        <input type="hidden" id="chooseEntrustDate" name="entrustDate" value=""/></td>
				<td class="pct10">
					<dl class="form-item">
						<dd class="detail">
	                         <input class="zui-combobox zui-validatebox" id="entrustAmOrPm" type="hidden" name="entrustAmOrPm" value=""
	                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
	                          data-valuefield="fullcode" data-textfield="name">
						</dd>
					</dl>
				</td>
	            <td class="pct70"></td>
			</tr>
	 		<tr>
	            <td class="td-title">备注</td>
	            <td colspan="5">
	                <label>
	                    <textarea class="zui-area row-width" placeholder="最多可以输入500个字符" id="mo" name="mo" validate-type="Require,Length[0-500]"></textarea>
	                </label>
	            </td>
	        </tr>
		</table>
		<table class="table-detail">
			<div>
				<h1 class="page-title">预约提醒</h1>
				<tr>
	                <td class="td-title">收件人</td>
	                <td colspan="5">张三</td>
	     		</tr>
				<tr>
	                <td class="td-title">发送内容</td>
	                <td colspan="10">程序猿</td>
	     		</tr>
				<tr>
	                <td class="td-title"><b class="c-red mr5"></b>提醒方式:</dt>
	                <td class="detail">
	                    <input class="zui-checkbox zui-validatebox" id="checkbox1" type="hidden" data-multiple="true"
	                           data-data="[{'id':'0','text':'短信'},{'id':'1','text':'邮箱'}]"
	                           data-valuefield="id" data-textfield="text" >
	                </td>
				</tr>
			</div>
		</table>
	</form>

</div>

 <div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-cancel" class="btn-blue mr10">取消</button>
</div>
</div>


</body>
<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.form'], 
			function($, CALLBACK, Loading, ZUI_MESSAGE_CLIENT) {
			
	
			
		});
		
	</script>

>>>>>>> branch 'master' of http://222.177.14.56:8081/liuwei/zds-finance-mortgage.git
</html>