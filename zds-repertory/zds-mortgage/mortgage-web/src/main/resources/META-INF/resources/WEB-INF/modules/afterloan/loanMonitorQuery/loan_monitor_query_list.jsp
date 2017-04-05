<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>贷后监控查询列表</title>
</head>
<body id="body">
<div class="page-box">
    <div id="search" class="p5">
        <form id="searchLoanMonitorQueryForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" type="text" id="caseApplyCode" name="caseApplyCode">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerName" type="text" name="customerName">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">产品分类:</dt>
                <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productParentId"
                                   data-width="94"
                                   name="productTypeId"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productChildId"
                                   name="productSubtypeId" data-width="94"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">机构:</dt>
                <dd class="detail">
                    <input class="zui-input" id="mechanismName" name="mechanismName">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">汇法信息:</dt>
	            <dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
            	    	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00182"
                       	data-valuefield="fullcode" data-textfield="name" name="huifa">
				</dd>   
            </dl>
            <dl class="form-item">
                <dt class="title">工商登记状态:</dt>
	            <dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden"
            	    	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00183"
                       	data-valuefield="fullcode" data-textfield="name" name="businessStatus">
				</dd>   
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" type="button" id="searchLoanMonitorQueryList">查询</button>
            <button class="btn-gray" type="button" id="resetLoanMonitorQueryList">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="p10">
        <div id="tb-LoanMonitorQueryList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.afterloan.loanMonitorQuery.getLoanMonitorQueryList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","isScroll":true,"toolbar":"#btn-function","isMergeCell":true,"mergeColField":"caseApplyCode","mergeCol":"collateralAddress"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:caseApplyCode,width:10%">案件号</th>
		            <th data-options="field:mechanismName,width:10%">机构</th>
		            <th data-options="field:customerName,width:5%">主借人</th>
		            <th data-options="field:phoneNumber,width:5%">联系方式</th>
		            <th data-options="field:productTypeName,width:5%">产品分类</th>
		            <th data-options="field:productSubtypeName,width:5%">子产品</th>
		            <th data-options="field:applyAmount,width:5%">申请金额(元)</th>
		            <th data-options="field:monitorDate,width:10%">导入时间</th>
		            <th data-options="field:LOANMONITORQUERYSTATUS,width:5%">汇法信息</th>
		            <th data-options="field:LOANMONITORQUERYSTATUS,width:5%">工商登记状态</th>
		            <th data-options="field:collateralAddress,width:10%">押品地址</th>
		            <th data-options="field:EVALUATINGPRICE,width:5%">上次评估价（元）</th>
		            <th data-options="field:LOANMONITORQUERYSTATUS,width:5%">本次评估价（元）</th>
		            <th data-options="field:ID,width:15%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ], function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		    	//产品子类查询
				var productUrl =  '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryIdAndOrgCd" context="admin"/>&jsoncallback=?';
		    	CALLBACK.productTypeChange = function(v,t){
					$("#productSubtypeId").ZCombobox({
		       		 	valueField: "id",
		                textField: "value",
		                url: productUrl+"&categoryId="+v
		       		});
				}
    			
		    	//查询回调
		        $('#searchLoanMonitorQueryList').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchLoanMonitorQueryForm'));
		        	if(flag){
		            	var formArray=$("#searchLoanMonitorQueryForm").serializeArray();
		            	$('#tb-LoanMonitorQueryList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetLoanMonitorQueryList').on('click',function(){
		        	 $("#productChildId").ZCombobox("setValue","");
					 $.ZUI.resetForms('#searchLoanMonitorQueryForm');//重置
		        	var flag=$.ZUI.validateForm($('#searchLoanMonitorQueryForm'));
		        	if(flag){
		            	var formArray=$("#searchLoanMonitorQueryForm").serializeArray();
		            	$('#tb-LoanMonitorQueryList').ZTable("reload", formArray);
		        	}
		        });
		        
		     	 //时间转换
		        CALLBACK.formatterApplyDate = function(row,value){
					return window.formatDate(row,value);
				};
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data ='<a href="javaScript:void(0)" onclick="viewLoanMonitorQuery"><button class="btn-blue">详情</button></a>';
		        	    data +='&nbsp;&nbsp<a href="javaScript:void(0)" onclick="eidtHandle"><button class="btn-blue">处理</button></a>';
		        	return data;
		        }
    			
		        //详情格式化
		        CALLBACK.viewLoanMonitorQuery=function(index,row){
            		var viewLoanMonitorQueryUrl = '<z:ukey key="com.zdsoft.finance.afterloan.loanMonitorQuery.viewLoanMonitorQuery" context="admin"/>&jsoncallback=?&caseApplyId='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('案件号详情','案件号详情',viewLoanMonitorQueryUrl + "&openMethod=tabs");
                }
    			
		        //处理格式化
		        CALLBACK.eidtHandle=function(index,row){
            		var eidtHandleUrl = '<z:ukey key="com.zdsoft.finance.afterloan.loanAfterHandle" context="admin"/>&jsoncallback=?&caseApplyId='+row.id;
            		ZDS_MESSAGE_CLIENT.openMenuLink('loanAfterHandle','处理',eidtHandleUrl + "&openMethod=tabs"); 
                }
		        
		        /**
	             * 下拉框联动
	             * */
	            CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
	                var parentId = index;
	                loadProductChildId(parentId);
	            };
	            /**
	             * 下拉数据
	             * @param cataId
	             */
	            function loadProductChildId(pId) {
	                var productChildId = $("#productChildId");
	                productChildId.ZCombobox({queryParams: {"parentId": pId}});
	            }
		        
                //初始化
                $.ZUI.init();
         });
</script>
</body>
</html>