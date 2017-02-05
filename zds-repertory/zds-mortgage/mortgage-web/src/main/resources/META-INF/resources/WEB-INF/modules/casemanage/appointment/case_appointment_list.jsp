<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">预约</div>
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="caseApplyCode"  name="c|caseApplyCode|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="customerName" name="cus|customerName|LK|S" >
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">预约状态：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="appointmentType" type="hidden" name="c|appointmentType|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0061"
			                           data-valuefield="fullcode" data-textfield="name" >
                	</dd>
				</dl>
				<dl class="form-item">
                <dt class="title">预约面签时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates" name="ca|interviewDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee" name="ca|interviewDate|LE|S"/>
                     </label>
                </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约抵押时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates" name="ca|mortgageDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee" name="ca|mortgageDate|LE|S"/>
                     </label>
                </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约公证时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates" name="ca|notarizationDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee" name="ca|notarizationDate|LE|S"/>
                     </label>
                 </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约委托时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates" name="ca|entrustDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee" name="ca|entrustDate|LE|S"/>
                     </label>
                 </dd>
            	</dl>
			</form>
			<div class="form-btn">
				<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
				<input type="button" class="btn-search-blue" id="btn-reset" value="重置" />
			</div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb_caseAppointment" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.appointment.getAppointmentList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" ,"toolbar":"#btn-function"}'>
				<table>
        			<tr>
            			<th data-options="field:caseApplyCode">案件号</th>
            			<th data-options="field:customerName">主借人</th>
            			<th data-options="field:actualApplyAmount">申请金额（元）</th>
            			<th data-options="field:interviewDate" formatter="dateFormatterShow">预约面签时间</th>
            			<th data-options="field:mortgageDate" formatter="dateFormatterShow">预约抵押时间</th>
            			<th data-options="field:notarizationDate" formatter="dateFormatterShow">预约公证时间</th>
            			<th data-options="field:entrustDate" formatter="dateFormatterShow">预约委托时间</th>
            			<th data-options="field:phoneNumber">联系电话</th>
            			<th data-options="field:appointmentType">预约状态</th>
            			<th data-options="field:id" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {

	//时间格式化
	CALLBACK.dateFormatterShow = function(index,value){
		if(value){
			return window.formatDate(index,value);
		}else{
			return '';
		}
	}; 
	
	//操作
	CALLBACK.operate = function(rowData,value){
		if(rowData.appointmentType=='已预约'){
			var data = '<a href="javaScript:void(0)" onclick="changeAppointmentDate"><button class="btn-blue">更改预约时间</button></a>&nbsp;&nbsp;' +
	    	'<a href="javaScript:void(0)" onclick="viewDetails"><button class="btn-blue">详情</button></a>';
		}else{
			var data = '<a href="javaScript:void(0)" onclick="addAppointment"><button class="btn-blue">预约</button></a>';
		}
		return data;
	};
	//预约格式化
	CALLBACK.addAppointment = function(index,row){
		var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.casemanage.appointment.addAppointment" context="admin"/>&jsoncallback=?&id='+row.id+'&customerName='+row.customerName+'&phoneNumber='+row.phoneNumber+'&email='+row.email;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointTab','预约界面',addAppointmentUrl + "&openMethod=tabs"); 
	};
	//更改预约时间格式化
	CALLBACK.changeAppointmentDate = function(index,row){
		var changeAppointmentDate = '<z:ukey key="com.zdsoft.finance.casemanage.appointment.addAppointment" context="admin"/>&jsoncallback=?&id='+row.id+'&customerName='+row.customerName+'&phoneNumber='+row.phoneNumber+'&email='+row.email;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointTab','预约界面',changeAppointmentDate + "&openMethod=tabs"); 
	};
	//详情格式化
	CALLBACK.viewDetails = function(index,row){
		var viewDetailsUrl = '<z:ukey key="com.zdsoft.finance.casemanage.appointment.detailsAppointment" context="admin"/>&jsoncallback=?&id='+row.id;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointViewTab','预约详情',viewDetailsUrl + "&openMethod=tabs"); 
	};
	

	//查询回调
	$("#btn-search").click(function(){
		var flag=$.ZUI.validateForm($('#search_from'));
		if(flag){
			var formArray=$("#search_from").serializeArray();
			$("#tb_caseAppointment").ZTable("reload",formArray);
		}
	});
	//重置回调
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
	});
	
	
	//刷新
     function doSearch() {
		$('#tb_caseAppointment').ZTable("reload",{});
	}; 
    //页面回调
    ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
    }; 
	
	//初始化页面
	$.ZUI.init();
});
</script>
</html>