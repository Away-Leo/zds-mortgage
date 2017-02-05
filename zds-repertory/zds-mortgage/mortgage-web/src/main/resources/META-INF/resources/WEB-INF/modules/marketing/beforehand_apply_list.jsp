<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营销登记</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchBeforehandApplyListForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">联系方式:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerId" type="text" name="customerId|E|S">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">产品分类:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="productTypeId" type="text" name="productTypeId|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
			                           data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">终端:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="productTypeId" type="text" name="productTypeId|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
			                           data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="searchBeforehandApplyList">查询</button>
            <button class="btn-gray" id="resetBeforehandApplyList">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="page-title">营销登记列表</div>
    <div class="p10">
        <div id="tb-BeforehandApplyList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.marketing.getBeforehandApplyList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:CUSTOMERNAME,width:5%">主借人</th>
		            <th data-options="field:APPLYAMOUNT,width:10%">申请金额（元）</th>
		            <th data-options="field:PRODUCTTYPENAME,width:5%">产品父类</th>
		            <th data-options="field:PRODUCTSUBTYPENAME,width:5%">子产品</th>
		            <th data-options="field:AREA,width:10%">面积</th>
		            <th data-options="field:MAILINGADDRESS,width:20%">押品地址</th>
		            <th data-options="field:EVALUATINGPRICE,width:10%">评估价</th>
		            <th data-options="field:TERMINALFULLNAME,width:15%">终端</th>
		            <th data-options="field:ID,width:20%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
		    <a class="zui-toolbar" id="btn-add" text="贷款申请" buttonCls="btn-blue" handler="addBeforehandApply"></a>
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	//查询回调
		        $('#searchBeforehandApplyList').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchBeforehandApplyListForm'));
		        	if(flag){
		            	var formArray=$("#searchBeforehandApplyListForm").serializeArray();
		            	$('#tb-BeforehandApplyList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetBeforehandApplyList').on('click',function(){
		        	$('#customerId').val('');
		        	var flag=$.ZUI.validateForm($('#searchBeforehandApplyListForm'));
		        	if(flag){
		            	var formArray=$("#searchBeforehandApplyListForm").serializeArray();
		            	$('#tb-BeforehandApplyList').ZTable("reload", formArray);
		        	}
		        });
    			
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data = '<a href="javaScript:void(0)" onclick="viewBeforehand"><button class="btn-blue">详情</button></a>&nbsp;&nbsp';
	        		data +=  '<a href="javaScript:void(0)" onclick="viewCrm"><button class="btn-blue">营销跟踪</button></a>&nbsp;&nbsp';
	        		if(rowData.STATUS='0'){
	        			data +=  '<a href="javaScript:void(0)" onclick="editorBeforehand"><button class="btn-blue">编辑</button></a>';
	        		}
	        	return data;
		        }
		        //新增贷款申请
		        CALLBACK.addBeforehandApply=function(){
                	ZDS_MESSAGE_CLIENT.openMenuLink('beforehandApply_list_add', '贷款申请', '<z:ukey key="com.zdsoft.finance.marketing.addBeforehandApply" context="admin"/>');
                }
		        //编辑
		        CALLBACK.editorBeforehand=function(index,row){
		        	ZDS_MESSAGE_CLIENT.openMenuLink('beforehandApply_list_add'+index, '贷款申请编辑', '<z:ukey key="com.zdsoft.finance.marketing.addBeforehandApply" context="admin"/>&caseApplyId='+row.ID);
			    }
		    	//详情
		        CALLBACK.viewBeforehand=function(index,row){  
                	var editClientUrl = '<z:ukey key="com.zdsoft.finance.marketing.viewBeforehandApply" context="admin"/>&caseApplyId='+row.ID;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('贷款申请详情'+index,'贷款申请详情',editClientUrl + "&openMethod=tabs");
                }
		         
		    	
		    	//导出
		        CALLBACK.exports=function(){
		        	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=客户列表导出文档";
                    var param=$("table").html();
					$("form").remove("#exportFrom");
                    $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
                    $("#exportFrom").submit();
                }
		      //刷新
                function doSearch() {
    				$('#tb-BeforehandApplyList').ZTable("reload",{});
    			};
                //页面回调
                ZDS_MESSAGE_CLIENT.refreshThis=function(){
            		doSearch();
                };
                //初始化
                $.ZUI.init();
            });
</script>
</body>
</html>