<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案件查询</title>
</head>
<body id="body">
<div class="page-box">
    <div id="search" class="p5">
        <form id="searchCaseApplyListForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">产品分类:</dt>
                <dd class="detail">
					<label> 
						<input class="zui-combobox" type="hidden" name="c|creditMember|LK|S" data-width="94"
												data-data="[{'id':'0','text':'未申请额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
												data-valuefield="id" data-textfield="text">
					</label>
				</dd>
				<dd class="detail">
					<label> 
						<input class="zui-combobox" type="hidden" name="c|creditMember|LK|S" data-width="94"
												data-data="[{'id':'0','text':'未申请额度'},{'id':'1','text':'已申请额度未分配资金'},{'id':'2','text':'已申请额度已分配资金'}]"
												data-valuefield="id" data-textfield="text">
					</label>
				</dd>
            </dl>
            <dl class="form-item">
                <dt class="title">当前处理人:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerId" type="text" name="c|creditMember|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">资信员:</dt>
                <dd class="detail">
                    <input class="zui-input" id="creditMember" type="text" name="c|creditMember|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">申请时间:</dt>
                <dd class="detail">
                     <label>
                         <input type="text" id="startTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMddHHmmss',vel:'applyDates'})">
                         <input type="hidden" id="applyDates" name="c|applyDate|RE|S" />
                     </label>
                     <span class="word">至</span>
                     <label>
                         <input type="text" id="endTimeLocal" class="zui-input width2-1" onclick="WdatePicker({realDateFmt:'yyyyMMddHHmmss',vel:'applyDatee'})">
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
        <div id="tb-CaseApplyList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.marketing.getCaseApplyList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:CASEAPPLYCODE,width:5%">案件号</th>
		            <th data-options="field:CUSTOMERNAME,width:10%">主借人</th>
		            <th data-options="field:CREDENTIALNO,width:10%">身份证号</th>
		            <th data-options="field:PRODUCTSUBTYPE,width:5%">子产品</th>
		            <th data-options="field:APPLYAMOUNT,width:5%">申请金额(元)</th>
		            <th data-options="field:MAILINGADDRESS,width:15%">押品地址</th>
		            <th data-options="field:CREDITMEMBER,width:5%">资信员</th>
		            <th data-options="field:EVALUATINGPRICE,width:5%">评估价(元)</th>
		            <th data-options="field:CASEAPPLYSTATUS,width:10%">当前节点</th>
		            <th data-options="field:CASEAPPLYSTATUS,width:5%">当前处理人</th>
		            <th data-options="field:APPLYDATE,width:5%" formatter="formatterApplyDate">申请时间</th>
		            <th data-options="field:CASEAPPLYSTATUS,width:5%" formatter="formatterStatus">案件状态</th>
		            <th data-options="field:ID,width:15%" formatter="formatId">操作</th>
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
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	//查询回调
		        $('#searchCaseApplyList').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchCaseApplyListForm'));
		        	if(flag){
		            	var formArray=$("#searchCaseApplyListForm").serialize();
		            	
		            	$('#tb-CaseApplyList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetCaseApplyList').on('click',function(){
		        	$('#customerId').val('');
		        	var flag=$.ZUI.validateForm($('#searchCaseApplyListForm'));
		        	if(flag){
		            	var formArray=$("#searchCaseApplyListForm").serialize();
		            	$('#tb-CaseApplyList').ZTable("reload", formArray);
		        	}
		        });
		        
		     	 //时间转换
		        CALLBACK.formatterApplyDate = function(row,value){
					return window.formatDate(row,value);
				};
		     	 //案件状态
		        CALLBACK.formatterStatus = function(row,value){
		     		 var caseApplyStatus ='正常';
		     		 if(value=='02'){
		     			caseApplyStatus = '退单';
		     		 }
					return caseApplyStatus;
				};
    			
		        //新增客户格式化
		        CALLBACK.addCaseApply=function(){
                	ZDS_MESSAGE_CLIENT.openMenuLink('CaseApply_list_add', '贷款申请', '<z:ukey key="com.zdsoft.finance.marketing.addCaseApply" context="admin"/>');
                }
		        
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a href="javaScript:void(0)" onclick="editCrm"><button class="btn-blue">详情</button></a>&nbsp;&nbsp;'+
		        	'<a href="javaScript:void(0)" onclick="viewCrm"><button class="btn-blue">再次申请</button></a>&nbsp;&nbsp;'
		        	return data;
		        }
		    	
		    	//编辑客户格式化
		        CALLBACK.editCrm=function(index,row){
                	var editClientUrl = '<z:ukey key="com.zdsoft.finance.customer.editCustomer" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('编辑客户','编辑客户',editClientUrl + "&openMethod=tabs");
                }
		        
		    	//查看客户格式化
		        CALLBACK.viewCrm=function(index,row){
            		var editClientUrl = '<z:ukey key="com.zdsoft.finance.customer.findCustomerById" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('查看客户','查看客户',editClientUrl + "&openMethod=tabs");
                }
		    	
		    	//导出客户列表
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