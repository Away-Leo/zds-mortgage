<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchProductForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">姓名:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="customerName" name="customerName|LK|S" value="">
                    </label>
                </dd>
            </dl>

            <dl class="form-item">
                <dt class="title">性别:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="gender" type="hidden" name="gender|E|S" value=""
			               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=3030"
			               data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
            
            <dl class="form-item">
                <dt class="title">教育程度:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-15]" id="degree" type="hidden" name="degree|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
			                           data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="searchProduct">查询</button>
            <button class="btn-gray" id="resetProduct">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="page-title">客户列表</div>
    <div class="p10">
        <div id="tb-product" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.customer.getlatestCustomers" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:customerName,width:10%">姓名</th>
		            <th data-options="field:credentiaType,width:15%">证件类型</th>
		            <th data-options="field:credentialNo,width:15%">证件号码</th>
		            <th data-options="field:gender,width:5%">性别</th>
		            <th data-options="field:degree,width:10%">教育程度</th>
		            <th data-options="field:maritalStatus,width:10%">婚况</th>
		            <th data-options="field:careerType,width:10%">职业类型</th>
		            <th data-options="field:address,width:30%">家庭住址</th>
		            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
		    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addCustomer"></a>
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	//查询回调
		        $('#searchProduct').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serializeArray();
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetProduct').on('click',function(){
		        	$('#gender').ZCombobox('setValue','');
		        	$('#degree').ZCombobox('setValue','');
		        	$('#clientNm').val('');
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serializeArray();
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
    			
		        //新增客户格式化
		        CALLBACK.addCustomer=function(){
                	ZDS_MESSAGE_CLIENT.openMenuLink('customer_list_add', '新增客户', '<z:ukey key="com.zdsoft.finance.customer.addCustomer" context="admin"/>');
                }
		        
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a href="javaScript:void(0)" onclick="editCrm"><button class="btn-blue">编辑</button></a>&nbsp;&nbsp;'+
		        	'<a href="javaScript:void(0)" onclick="viewCrm"><button class="btn-blue">查看</button></a>';
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