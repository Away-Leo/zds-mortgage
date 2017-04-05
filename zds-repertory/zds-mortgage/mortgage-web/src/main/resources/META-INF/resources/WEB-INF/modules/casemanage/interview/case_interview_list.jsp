<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>面签管理</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">面签管理</div>
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
	            	<dt class="title">产品分类：</dt>
		                <dd class="detail">
		                	<input class="zui-combobox zui-validatebox" type="hidden" id="productTypeId"
		                              data-width="94"
		                              name="c|productTypeId|E|S" 
		                              data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
		                              data-callback="productParentIdChange"
		                              data-height="300"
		                              data-defaultvalue=""
		                              data-valuefield="id" data-textfield="text">
		                </dd>
		                <dd class="detail">
		                    <input class="zui-combobox zui-validatebox" type="hidden" id="productSubtype"
		                             name="c|productSubtypeId|E|S" data-width="94"   
		                              data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
		                              data-callback=""
		                              data-height="300"
		                              data-defaultvalue=""
		                              data-valuefield="id" data-textfield="text">
		                </dd>
           	    </dl>
				
				<dl class="form-item">
					<dt class="title">主借人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Length[0-32]" type="text" id="recipients" name="a|recipients|LK|S" >
						</label>
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
			         <input class="zui-combobox zui-validatebox" type="hidden" id="interviewStatus"
                              data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0067"
                                     id="interviewStatus" name="c|interviewStatus|E|S" data-valuefield="fullcode" 
                                     data-textfield="name" data-defaultvalue="YWDM0051077">
                                                       
                	</dd>
				</dl>
				
				<dl class="form-item">
	                <dt class="title">面签时间:</dt>
	                <dd class="detail">
	                     <label>
	                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDates',dateFmt:'yyyy-MM-dd'})">
	                         <input type="hidden" id="applyDates" name="i|actualInterviewDate|RE|S" />
	                     </label>
	                     <span class="word">至</span>
	                     <label>
	                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',readOnly:true,realDateFmt:'yyyyMMdd',vel:'applyDatee',dateFmt:'yyyy-MM-dd'})">
	                         <input type="hidden" id="applyDatee" name="i|actualInterviewDate|LE|S"/>
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
			<div id="tb_interview" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.cnfh.rms.casemanage.interview.findInterviewList" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index" , "toolbar":"#btn-applylist"}'>
				<table>
        			<tr>
            			<th data-options="field:CASEAPPLYCODE">案件号</th>
            			<th data-options="field:RECIPIENTS">主借人</th>
            			<th data-options="field:ACTUALAPPLYAMOUNT" formatter="formatterAmount">贷款金额（元）</th>
            			<th data-options="field:PRODUCTTYPENAME">产品分类</th>
            			<th data-options="field:PRODUCTSUBTYPENAME">子产品</th>
            			<th data-options="field:INTERVIEWDATE" formatter="dateFormatterShow">预约时间</th>
            			<th data-options="field:PHONENUMBER">联系电话</th>
            			<th data-options="field:ACTUALINTERVIEWDATE" formatter="dateFormatterShow">面签时间</th>
            			<th data-options="field:INTERVIEWSTATUS">状态</th>
            			<th data-options="field:ID" formatter="operate">操作</th>
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
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table'], function($, CALLBACK,ZTools) {

	  /**
     * 下拉框联动
     * */
    CALLBACK.productParentIdChange = function (index, rowData) {
        var parentId = index;
        loadProductChildId(parentId);
    };
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
		if(rowData.INTERVIEWSTATUS=='未面签'){
			var data = '<a href="javaScript:void(0)" onclick="doInterview"><button class="btn-blue">面签</button></a>&nbsp;&nbsp;';
		}else{
			var data = '<a href="javaScript:void(0)" onclick="viewDetails"><button class="btn-blue">详情</button></a>';
		}
		return data;
	};
	//面签格式化
	CALLBACK.doInterview = function(index,row){
		var doInterviewUrl = '<z:ukey key="com.cnfh.rms.casemanage.interview.interviewTotelEdit" context="admin"/>&projectId='+row.ID;
        ZDS_MESSAGE_CLIENT.openMenuLink('interviewTab','面签',doInterviewUrl + "&openMethod=tabs"); 
	};

	//详情格式化
	CALLBACK.viewDetails = function(index,row){
		var viewDetailsUrl = '<z:ukey key="com.cnfh.rms.casemanage.interview.interviewTotelView" context="admin"/>&projectId='+row.ID;
        ZDS_MESSAGE_CLIENT.openMenuLink('viewDetailsTab','面签详情',viewDetailsUrl + "&openMethod=tabs"); 
	};
	
	//导出
    CALLBACK.exports=function(){
    	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=案件面签列表导出文档";
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
			$("#tb_interview").ZTable("reload",formArray);
		}
	});
	//重置回调
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
		$("#productSubtype").ZCombobox("setValue","");
		$("#productTypeId").ZCombobox("setValue","");
		$("#interviewStatus").ZCombobox("setValue","YWDM0051077");
		$("#applyDates").val("");
		$("#applyDatee").val("");
	});
	
	//刷新
    function doSearch() {
		var formArray=$("#search_from").serializeArray();
		formArray.push({"name":"flag","value":"true"});
		$("#tb_interview").ZTable("reload",formArray);
	}; 
    //页面回调
    ZDS_MESSAGE_CLIENT.refreshThis=function(){
		doSearch();
    }; 
	
  
    /**
     * 下拉数据
     * @param cataId
     */
    function loadProductChildId(pId) {
        var productChildId = $("#productSubtype");
        productChildId.ZCombobox({queryParams: {"parentId": pId}});
    }
    
	//初始化页面
	$.ZUI.init();
});
</script>
</html>