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
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="bookingType" type="hidden" name="c|bookingType|E|S" 
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0061"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="YWDM0051061">
                	</dd>
				</dl>
				<dl class="form-item">
                <dt class="title">预约面签时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="interviewDate1"  class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'interviewDate2\')}' ,readOnly:true,realDateFmt:'yyyyMMdd',vel:'interviewDate',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="interviewDate" name="ca|interviewDate|RE|S"  />
                     </label>
                      <span class="word">至</span>
                     <label>
                         <input type="text"  class="zui-input width2-1" id="interviewDate2"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'interviewDate1\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee" name="ca|interviewDate|LE|S"/>
                     </label>
                </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约抵押时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="mortgageDate1" class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'mortgageDate2\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates" name="ca|mortgageDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text"  class="zui-input width2-1" id="mortgageDate2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'mortgageDate1\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee1',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee1" name="ca|mortgageDate|LE|S"/>
                     </label>
                </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约公证时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="notarizationDate1"  class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'notarizationDate2\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDates2',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates2" name="ca|notarizationDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" class="zui-input width2-1" id="notarizationDate2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'notarizationDate1\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee3',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee3" name="ca|notarizationDate|LE|S"/>
                     </label>
                 </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">预约委托时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="entrustDate1"  class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'entrustDate2\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDates4',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDates4" name="ca|entrustDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text"  class="zui-input width2-1" id="entrustDate2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'entrustDate1\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee5',dateFmt:'yyyy-MM-dd'})">
                         <input type="hidden" id="applyDatee5" name="ca|entrustDate|LE|S"/>
                     </label>
                 </dd>
            	</dl>
			</form>
			<div class="form-btn">
				<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
				<input type="button" class="btn-gray" id="btn-reset" value="重置" />
			</div>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="p10">
			<div id="tb_caseAppointment" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.booking.bookingList" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">案件号</th>
            			<th data-options="field:CUSTOMERNAME">主借人</th>
            			<th data-options="field:ACTUALAPPLYAMOUNT" formatter="formatterAmount">申请金额（元）</th>
            			<th data-options="field:INTERVIEWDATE" formatter="dateFormatterShow">预约面签时间</th>
            			<th data-options="field:MORTGAGEDATE" formatter="dateFormatterShow">预约抵押时间</th>
            			<th data-options="field:NOTARIZATIONDATE" formatter="dateFormatterShow">预约公证时间</th>
            			<th data-options="field:ENTRUSTDATE" formatter="dateFormatterShow">预约委托时间</th>
            			<th data-options="field:PHONENUMBER">联系电话</th>
            			<th data-options="field:APPOINTMENTTYPE">预约状态</th>
            			<th data-options="field:ID" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTools) {
	//时间格式化
	CALLBACK.dateFormatterShow = function(index,value){
		if(value){
			return window.formatDate(index,value);
		}else{
			return '';
		}
	}; 
	
	CALLBACK.formatterAmount = function(index,value){
		if(value){
			return ZTools.formatCurrency(value+"");
		}else{
			return '';
		}
	}
	
	//操作
	CALLBACK.operate = function(rowData,value){
		if(rowData.APPOINTMENTTYPE=='已预约'){
			var data = '<a href="javaScript:void(0)" class="btn-blue" onclick="changeAppointmentDate">更改预约时间</a>&nbsp;&nbsp;' +
	    	'<a href="javaScript:void(0)" onclick="viewDetails" class="btn-blue">详情</a>';
		}else{
			var data = '<a href="javaScript:void(0)" class="btn-blue" onclick="addAppointment">预约</a>';
		}
		return data;
	};
	
	//预约格式化
	CALLBACK.addAppointment = function(index,row){
		var addAppointmentUrl = '<z:ukey key="com.cnfh.rms.casemanage.booking.BookingEdit" context="admin"/>&jsoncallback=?&id='+row.ID+'&customerName='+row.CUSTOMERNAME+'&phoneNumber='+row.PHONENUMBER+'&email='+row.EMAIL;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointTab','预约编辑',addAppointmentUrl + "&openMethod=tabs"); 
	};
	
	//更改预约时间格式化
	CALLBACK.changeAppointmentDate = function(index,row){
		var changeAppointmentDate = '<z:ukey key="com.cnfh.rms.casemanage.booking.BookingEdit" context="admin"/>&jsoncallback=?&id='+row.ID+'&customerName='+row.CUSTOMERNAME+'&phoneNumber='+row.PHONENUMBER+'&email='+row.EMAIL;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointTab','预约编辑',changeAppointmentDate + "&openMethod=tabs"); 
	};
	
	//详情格式化
	CALLBACK.viewDetails = function(index,row){
		var viewDetailsUrl = '<z:ukey key="com.cnfh.rms.casemanage.booking.bookingView" context="admin"/>&jsoncallback=?&id='+row.ID;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointViewTab','预约详情',viewDetailsUrl + "&openMethod=tabs"); 
	};
	
	//查询回调
	$("#btn-search").click(function(){
		var flag=$.ZUI.validateForm($('#search_from'));
		if(flag){
			var formArray=$("#search_from").serializeArray();
			formArray.push({"name":"flag","value":"true"});
			$("#tb_caseAppointment").ZTable("reload",formArray);
		}
	});
	
	//重置回调
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
		$("#bookingType").ZCombobox('setValue',"YWDM0051061");
		$("#interviewDate").val("");
        $("#applyDatee").val("");
        $("#applyDates").val("");
        $("#applyDatee1").val("");
        $("#applyDates2").val("");
        $("#applyDatee3").val("");
        $("#applyDates4").val("");
        $("#applyDatee5").val("");
	});
	
	//刷新
     function doSearch() {
    	 var formArray=$("#search_from").serializeArray();
    	 formArray.push({"name":"flag","value":"true"});
		$('#tb_caseAppointment').ZTable("reload",formArray);
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