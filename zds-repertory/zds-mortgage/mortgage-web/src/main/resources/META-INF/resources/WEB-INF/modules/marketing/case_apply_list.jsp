<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件查询列表</title>
</head>
<body id="body">
<div class="page-box">
    <div id="search" class="p5">
        <form id="searchCaseApplyListForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">案件号：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">主借人：</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
                </dd>
            </dl>
            <dl class="form-item form-auto">
       			<dt class="title">产品分类：</dt>
				<dd class="detail">  
					<input class="zui-combobox zui-validatebox" type="hidden" name="c|productTypeId|E|S" id ="productTypeId"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productTypeChange" data-id="isAgriculture" 
						data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">
					<input class=" zui-combobox zui-validatebox" type="hidden" id="productSubtypeId" name="c|productSubtypeId|E|S">
				</dd>
			</dl>
<!--             <dl class="form-item"> -->
<!--                 <dt class="title">当前处理人：</dt> -->
<!--                 <dd class="detail"> -->
<!--                     <input class="zui-input" id="customerId" type="text" name="c|creditMember|LK|S"> -->
<!--                 </dd> -->
<!--             </dl> -->
            <dl class="form-item">
                <dt class="title">资信员：</dt>
                <dd class="detail">
                    <input class="zui-input" id="creditMember" type="text" name="c|creditMember|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">申请时间：</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'applyDates'})">
                         <input type="hidden" id="applyDates" name="c|applyDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLocal\')}',realDateFmt:'yyyyMMdd',vel:'applyDatee'})">
                         <input type="hidden" id="applyDatee" name="c|applyDate|LE|S"/>
                     </label>
                 </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" type="button" id="searchCaseApplyList">查询</button>
            <button class="btn-gray" type="button" id="resetCaseApplyList">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="p10">
        <div id="tb-CaseApplyList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.marketing.getCaseApplyList" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:CASEAPPLYCODE,width:8%">案件号</th>
		            <th data-options="field:CUSTOMERNAME,width:8%">主借人</th>
		            <th data-options="field:CREDENTIALNO,width:14%">身份证号</th>
		            <th data-options="field:PRODUCTSUBTYPENAME,width:6%">子产品</th>
		            <th data-options="field:APPLYAMOUNT,width:4%" formatter="formatterAmount">申请金额(元)</th>
		            <th data-options="field:MAILINGADDRESS,width:17%">押品地址</th>
		            <th data-options="field:CREDITMEMBER,width:5%"  formatter="formatNull">资信员</th>
		            <th data-options="field:EVALUATINGPRICE,width:5%" formatter="formatterAmount">评估价(元)</th>
		            <th data-options="field:CURRENTTASKNAME,width:10%">当前节点</th>
		            <th data-options="field:CURRENTDEALEMPNAME,width:5%">当前处理人</th>
		            <th data-options="field:APPLYDATE,width:8%" formatter="formatterApplyDate">申请时间</th>
		            <th data-options="field:STAGE,width:5%">案件状态</th>
		            <th data-options="field:ID,width:5%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ], function ($, CALLBACK,ZTOOL) {
    	var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
    	CALLBACK.productTypeChange = function(v,t){
			$("#productSubtypeId").ZCombobox({
       		 	valueField: "id",
                textField: "value",
                url: productUrl+"&categoryId="+v
       		});
		}
    	//查询回调
        $('#searchCaseApplyList').on('click',function(){
        	var flag=$.ZUI.validateForm($('#searchCaseApplyListForm'));
        	if(flag){
            	var formArray=$("#searchCaseApplyListForm").serializeArray();
            	$('#tb-CaseApplyList').ZTable("reload", formArray);
        	}
        });
        
        //重置回调
        $('#resetCaseApplyList').on('click',function(){
        	$('#customerName').val('');
        	$('#caseApplyCode').val('');
        	$('#applyDates').val('');
        	$('#applyDatee').val('');
        	$('#productSubtypeId').ZCombobox('setValue', '');
    		$('#productTypeId').ZCombobox('setValue', '');
        	$("#searchCaseApplyListForm")[0].reset();
        	
        	var flag=$.ZUI.validateForm($('#searchCaseApplyListForm'));
        	if(flag){
            	var formArray=$("#searchCaseApplyListForm").serializeArray();
            	$('#tb-CaseApplyList').ZTable("reload", formArray);
        	}
        });
        
     	 //时间转换
        CALLBACK.formatterApplyDate = function(row,value){
        	return ZTOOL.strToDate(value);
		};
		 // 金额转换
        CALLBACK.formatterAmount = function(row,value){
			 if (value != null && value != "") {
	        	return ZTOOL.formatCurrency(value + "");
			 } 
			 return "";
		};
		// 格式化空值
		CALLBACK.formatNull=function(rowData,value){
			if (value == null) {
				return "";
			} else {
				return value;
			}
		}
    	//操作格式化
        CALLBACK.formatId=function(rowData,index){
        	var data='<a href="javaScript:void(0)" onclick="viewCaseApply"><button class="btn-blue">详情</button></a>';
        	return data;
        }
  			
        //查看案件信息格式化
        CALLBACK.viewCaseApply=function(index,row){
          		var editClientUrl = '<z:ukey key="com.zdsoft.finance.marketing.viewBeforehandApply" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
  	            ZDS_MESSAGE_CLIENT.openMenuLink('查看案件','查看案件',editClientUrl + "&openMethod=tabs");
              }
    	
    	//导出案件信息列表
        CALLBACK.exports=function(){
        	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=客户列表导出文档";
                  var param=$("table").html();
			$("form").remove("#exportFrom");
                  $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
                  $("#exportFrom").submit();
              }
        
         //初始化
         $.ZUI.init();
   });
</script>
</body>
</html>