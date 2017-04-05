<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约台账</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">预约台账</div>
		<div id="search" class="p5">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">案件号：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="caseApplyCode"  name="caseApply|caseApplyCode|LK|S">
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
					<dt class="title">预约类型：</dt>
					<dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="bookingType" type="hidden" name="booking|bookingType|E|S" 
			                          data-data="[{'fullcode':'1',name:'面签'},{'fullcode':'2',name:'办理抵押'},{'fullcode':'3',name:'公证'},{'fullcode':'4',name:'委托'}]"
			                           data-valuefield="fullcode" data-textfield="name" data-defaultvalue="1" >
                	</dd>
				</dl>
				<dl class="form-item">
                <dt class="title">预约时间：</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input strToDate width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
                         <input   class="zui-input" type="hidden" id="applyDates" name="booking|interviewdate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input strToDate width2-1" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
                         <input class="zui-input" type="hidden" id="applyDatee" name="booking|interviewdate|LE|S"/>
                     </label>
                </dd>
            	</dl>
            	<dl class="form-item">
                <dt class="title">上午/下午：</dt>
               <dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="interviewamorpm" type="hidden" name="booking|interviewamorpm|E|S"
			               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0060"
			               data-valuefield="fullcode" data-textfield="name" >
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
			<div id="tb_caseAppointment" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.booking.ledgerBookingList" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index", "toolbar":"#btn-applylist"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">案件号</th>
            			<th data-options="field:CUSTOMERNAME">主借人</th>
            			<th data-options="field:APPLYAMOUNT">申请金额（元）</th>
            			<th data-options="field:PHONENUMBER">联系电话</th>
            			<th data-options="field:INTERVIEWDATE" formatter="dateFormatterShow">预约时间</th>
            			<th data-options="field:BOOKINGTYPE" formatter="bookingTypeFormatterShow">预约类型</th>
            			<th data-options="field:CASEAPPLYID" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
		<div id="btn-applylist">
               <a class="zui-toolbar" id="btn-add" text="导出" buttonCls="btn-blue"
                  handler="exports"></a>
        </div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//时间格式化
	CALLBACK.dateFormatterShow = function(index,value){
		if(value){
			return window.formatDate(index,value)+" ("+index.INTERVIEWAMORPM+")";
		}else{
			return '';
		}
	}; 
	
	CALLBACK.bookingTypeFormatterShow = function(index,value){
		if(value=='1'){
			return '面签';
		}else if(value=='2'){
			return '办理抵押';
		}else if(value=='3'){
			return '公证';
		}else if(value=='4'){
			return '委托';
		}
	}
	
	//操作
	CALLBACK.operate = function(rowData,value){
		var data = '<a href="javaScript:void(0)" onclick="viewDetails" class="btn-blue">打印合同</a>';
		return data;
	};
	
	//打印合同
	CALLBACK.viewDetails = function(index,row){
		var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.contract.caseContractPrint" context="admin"/>&jsoncallback=?&operationType=print&caseApplyId='+row.CASEAPPLYID;
        ZDS_MESSAGE_CLIENT.openMenuLink('appointTab','打印合同',addAppointmentUrl + "&openMethod=tabs"); 
	};
	
	//导出
    CALLBACK.exports=function(){
    	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=预约台账导出文档";
        var param=$("table").html();
		$("form").remove("#exportFrom");
        $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
        $("#exportFrom").submit();
    }
	
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
		$("#applyDatee").val("");
		$("#applyDates").val("");
		$("#interviewamorpm").ZCombobox("setValue","");
		$("#bookingType").ZCombobox("setValue","1");
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