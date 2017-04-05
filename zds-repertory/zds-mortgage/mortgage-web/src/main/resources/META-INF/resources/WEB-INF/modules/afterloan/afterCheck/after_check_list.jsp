<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>贷后检查列表</title>
</head>
<body id="body">
<div class="page-box">
    <div id="search" class="p5">
        <form id="searchAfterCheckForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
                    </label>
                </dd>
            </dl>
            <dl class="form-item form-auto">
       			<dt class="title">产品分类:</dt>
				<dd class="detail">  
					<input class="zui-combobox zui-validatebox" type="hidden" name="c|productTypeId|E|S" id ="productTypeId"
						data-width="190"
						data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
						data-callback="productTypeChange" data-id="isAgriculture" 
						data-defaultvalue=""
						data-valuefield="id" data-textfield="text">
				</dd>
				<dd class="detail">
					<input class=" zui-combobox zui-validatebox" type="hidden" id="productSubtypeId" name="c|productSubtypeId|E|S"
						data-width="203">
				</dd>
			</dl>
             <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">案件状态:</dt>
	            <dd class="detail">
					<label> 
						<input class="zui-combobox zui-validatebox" id="terminalId" type="text" name="c|stage|E|S" value="" data-height="300"
						data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
                        data-valuefield="id" data-textfield="text">  
					</label>
				</dd>   
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" type="button" id="searchAfterCheckList">查询</button>
            <button class="btn-gray" type="button" id="resetAfterCheckList">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="p10">
        <div id="tb-AfterCheckList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.getAfterCheckList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","isScroll":true,"toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:CASEAPPLYCODE,width:10%">案件号</th>
		            <th data-options="field:MECHANISMNAME,width:10%">机构</th>
		            <th data-options="field:CUSTOMERNAME,width:5%">主借人</th>
		            <th data-options="field:STAGE,width:5%">案件状态</th>
		            <th data-options="field:TRACKERNAME,width:5%" formatter="formatterNull">指定催收人</th>
		            <th data-options="field:ACTIONSNAME,width:5%" formatter="formatterNull">最近一次检查动作</th>
		            <th data-options="field:CHECKEDDATE,width:10%" formatter="formatterDate">最近一次跟进日期</th>
		            <th data-options="field:APPLYDATE,width:10%" formatter="formatterDate">放款日期</th>
		            <th data-options="field:LOANAPPLYANOUNT,width:5%" formatter="formatterCurrency">已放款(元)</th>
		            <th data-options="field:,width:5%" formatter="formatterCurrency">收回本金(元)</th>
		            <th data-options="field:,width:5%">现逾期天数</th>
		            <th data-options="field:,width:5%" formatter="formatterCurrency">现逾期本金</th>
		            <th data-options="field:,width:5%" formatter="formatterCurrency">现逾期利息</th>
		            <th data-options="field:ID,width:10%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ], function ($, CALLBACK,ZTOOLS, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    			//产品子类查询
    			var productUrl =  '<z:ukey key="com.zdsoft.finance.authGrade.getProductByParentId" context="admin"/>&jsoncallback=?';
		    	CALLBACK.productTypeChange = function(v,t){
					$("#productSubtypeId").ZCombobox({
		       		 	valueField: "id",
		                textField: "text",
		                url: productUrl+"&parentId="+v
		       		});
				}
		    	
		    	//时间转换
		        CALLBACK.formatterDate = function(row,value){
		    		 if(value){
						return ZTOOLS.strToDate(value);

		    		 }else{
		    			 return "";
		    		 }
				};
				
				 //金额转换
		        CALLBACK.formatterCurrency = function(row,value){
		        	return ZTOOLS.formatCurrency(value+""); 
				};
				
		     	 //清楚null
		        CALLBACK.formatterNull = function(row,value){
		     		 var returnStr ='';
		     		 if(value==null){
		     			returnStr = '';
		     		 }else{
		     			returnStr= value;
		     		 }
					return returnStr;
				};
				
		    	//查询回调
		        $('#searchAfterCheckList').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchAfterCheckForm'));
		        	if(flag){
		            	var formArray=$("#searchAfterCheckForm").serializeArray();
		            	$('#tb-AfterCheckList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetAfterCheckList').on('click',function(){
		        	$('#productSubtypeId').ZCombobox('setValue', '');
		    		$('#productTypeId').ZCombobox('setValue', '');
		    		$('#terminalId').ZCombobox('setValue', '');
		        	$("#searchAfterCheckForm")[0].reset();
		        	var flag = $.ZUI.validateForm('#searchAfterCheckForm');
		        	if(flag){
		            	var formArray=$("#searchAfterCheckForm").serializeArray();
		            	$('#tb-AfterCheckList').ZTable("reload", formArray);
		        	}
		        });
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a href="javaScript:void(0)" onclick="eidtAfloanCheck"><button class="btn-blue">检查</button></a>';
		        	return data;
		        }
    			
		     	//检查格式化
		        CALLBACK.eidtAfloanCheck=function(index,row){
            		var eidtAfloanCheckUrl = '<z:ukey key="com.zdsoft.finance.afterloan.afterCheck.editAfterCheck" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('检查','检查',eidtAfloanCheckUrl + "&openMethod=tabs");
                }
		        
                //初始化
                $.ZUI.init();
         });
</script>
</body>
</html>