<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>其他合作单位管理</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchProductForm" class="zui-form mt15">
        	<input type="hidden" name="createBy|E|S" value="${empCd }">
            <dl class="form-item">
                <dt class="title">合作单位：</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="companyName" name="companyName|LK|S" value="">
                    </label>
                </dd>
            </dl>

            <dl class="form-item">
                <dt class="title">类别：</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="companyType" type="hidden" name="companyType|E|S" value=""
			               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00120"
			               data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
            
            <dl class="form-item">
                <dt class="title">是否停用：</dt>
                <dd class="detail">
		            <input class="zui-combobox zui-validatebox" validate-type="Length[0-20]" id="isStop" type="hidden" name="isStop|E|S" value=""
		               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		               data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" type="button" id="searchProduct">查询</button>
            <button class="btn-gray" type="button" id="resetProduct">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="page-title">其他合作单位列表</div>
    <div class="p10">
        <div id="tb-product" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.otherCooperater.getOtherCooperater" context="admin"/>&jsoncallback=?&createBy|E|S=${empCd }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:companyName">合作单位</th>
		            <th data-options="field:parentOrg">上级</th>
		            <th data-options="field:companyTypeName">类别</th>
		            <th data-options="field:telephone">联系电话</th>
		            <th data-options="field:isStopName">是否停用</th>
		            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
		    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addOtherCooperater"></a>
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
	    <div style="display:none" >
    	<table id="tableTemp"></table>
    </div>
	</div>
</div>
  <div style="display:none" >
    	<table id="tableTemp"></table>
    </div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	//查询回调
		        $('#searchProduct').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serialize();
		            	formArray=decodeURIComponent(formArray, true);
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetProduct').on('click',function(){
		        	$('#companyType').ZCombobox('setValue','');
		        	$('#isStop').ZCombobox('setValue','');
		        	$('#companyName').val('');
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serialize();
		            	formArray=decodeURIComponent(formArray, true);
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
    			
		        //新增合作单位格式化
		        CALLBACK.addOtherCooperater=function(){
                	ZDS_MESSAGE_CLIENT.openMenuLink('customer_list_add', '新增合作单位', '<z:ukey key="com.zdsoft.finance.otherCooperater.addOtherCooperater" context="admin"/>');
                };
		        
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a class="btn-blue" title="编辑" onclick="edit">编辑</a>&nbsp;&nbsp;'+
		        	'<a class="btn-blue" title="查看" onclick="view">查看</a>&nbsp;&nbsp;'+
		        	'<a class="btn-blue" title="删除" onclick="del">删除</a>';
		        	
		        	return data;
		        };
		    	
		    	//编辑合作单位格式化
		        CALLBACK.edit=function(index,row){
                	var editClientUrl = '<z:ukey key="com.zdsoft.finance.otherCooperater.editOthercooperater" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('编辑合作单位','编辑合作单位',editClientUrl + "&openMethod=tabs");
                };
		        
		    	//查看合作单位格式化
		        CALLBACK.view=function(index,row){
            		var editClientUrl = '<z:ukey key="com.zdsoft.finance.otherCooperater.findOtherCooperaterById" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('查看合作单位','查看合作单位',editClientUrl + "&openMethod=tabs");
                };
		        
		    	//删除合作单位
		        CALLBACK.del=function(index,row){
		        	$.ZMessage.question("确认", "确认删除", function (r) {
		                	$.ajax({
			                    type: 'post',
			                    url: '<z:ukey key="com.zdsoft.finance.otherCooperater.delOtherCooperater" context="admin"/>',
			                    data: {id : row.id},
			                    dataType: 'json',
			                    success: function (data) {
			                        if (data.resultStatus == 0) {
			                        	 $.ZMessage.success("提示", "删除成功", function () {
			                        		$("#tb-product").ZTable("reload",{});
			                          	 });
			                        }else{
			                          	$.ZMessage.error("错误", data.msg, function () {
					                    });
			                        }
			                    },
			                    error: function () {
			                      	$.ZMessage.error("错误", "删除系统异常，请联系管理员", function () {
				                    });
			                    }
			                });

		            });
                }
		    	
		    	//导出合作单位列表
		        CALLBACK.exports=function(){
					var rows = $('#tb-product').ZTable('getRows');
					if(rows.length ==0){
						$.ZMessage.warning("提示", "没有数据可供导出", function () {
	                    });
					}else{
						var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=合作单位列表导出文档";
			        	$('#tableTemp').html($('table:eq(0)').html());
			            $("body table:eq(1) td[field='datagrid-header-check']").remove();
			            $("body table:eq(1) th[field='id']").remove();
			            var params = $('body table:eq(1)').html();
			            $("form").remove("#exportFrom");
			            $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='" + url + "' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='" + params + "' /></form>");
			            $("#exportFrom").submit();	
					}
                }
		        
		    	
		    	ZDS_MESSAGE_CLIENT.refreshThis = function () {
		            $('#tb-product').ZTable('reload');
		        };
                //初始化
                $.ZUI.init();
           });
</script>
</body>
</html>