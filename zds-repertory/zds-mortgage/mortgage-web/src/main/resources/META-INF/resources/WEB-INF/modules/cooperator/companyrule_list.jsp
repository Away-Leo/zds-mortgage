<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评估公司权重管理</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchProductForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">城市:</dt>
                <dd class="detail">
                    <!-- <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="cityName" name="cityName|LK|S" value="">
                    </label> -->
                    <div id="selectAddress" data-code="">
                        <input id="region" class="zui-input zui-validatebox"  type="text" readonly="true" style="width: 200px;" validate-type=""/>
                        <input id="cityName" type="hidden" value=""/>
                    </div>
                </dd>
            </dl>

			<dl class="form-item">
                <dt class="title">机构:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="orgName" name="orgName|LK|S" value="">
                    </label>
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
    <div class="page-title">评估公司权重列表</div>
    <div class="p10">
        <div id="tb-product" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.getEvaluateCompanyRule" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:city,width:10%">城市</th>
		            <th data-options="field:orgName,width:15%">机构</th>
		            <th data-options="field:orgId,width:5%">机构ID</th>
		            <th data-options="field:rule,width:40%">规则</th>
		            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
		    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addOtherCooperater"></a>
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
        <div id="selectAddress4" data-code="">
            <span id="cityName4" style="font-size: 14px;"></span>
        </div>
        <dl class="form-item block">
            <dt class="title"></dt>
            <dd class="detail">
                <div id="selectAddress4" data-code="">
                    <span id="cityName4" style="font-size: 14px;display:none;"></span>
                </div>
            </dd>
        </dl>
	</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.address', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	//查询回调
		        $('#searchProduct').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serializeArray();
		            	var city = $("#cityName").val().split(',');
		            	var provinceName = city[0];
		            	var cityName = city[1];
		            	var districtName = city[2];
		            	formArray += "&provinceName|E|S="+provinceName;
		            	formArray += "&cityName|E|S="+cityName;
		            	formArray += "&districtName|E|S="+districtName;
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetProduct').on('click',function(){
		        	$('#orgName').val('');
		        	$('#cityName').val('');
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serializeArray();
		            	$('#tb-product').ZTable("reload", formArray);
		        	}
		        });
		        
		        //城市格式化
		        CALLBACK.address=function(index, value){
		        	$("#selectAddress4").Address({
		        		showStreet:false,//不显示街道
		        		data:value,
		        		callback:function(infos,selected_ids) {
		                    var str = '';
		                    for(var i=0;i<infos.length;i++) {
		                        if(str==""){
		                            str = str+infos[i];
		                        }else{
		                            str = str+" - "+infos[i];
		                        }
		                    }
		                    $('#cityName4').html(str);
		                }
		            });
		        	var cityName = $('#cityName4').html();
		        	return cityName;
		   		};
    			
		   		//新增评估公司权重格式化
		        CALLBACK.addOtherCooperater=function(){
                	ZDS_MESSAGE_CLIENT.openMenuLink('customer_list_add', '新增评估公司权重', '<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.addEvaluateCompanyRule" context="admin"/>');
                }
		        
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a href="javaScript:void(0)" onclick="edit"><button class="btn-blue">编辑</button></a>&nbsp;&nbsp;'+
		        	'<a href="javaScript:void(0)" onclick="view"><button class="btn-blue">查看</button></a>&nbsp;&nbsp;'+
		        	'<a href="javaScript:void(0)" onclick="del"><button class="btn-blue">删除</button></a>';
		        	return data;
		        }
		    	
		    	//编辑评估公司权重格式化
		        CALLBACK.edit=function(index,row){
                	var editClientUrl = '<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.editEvaluateCompanyRule" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('编辑评估公司权重','编辑评估公司权重',editClientUrl + "&openMethod=tabs");
                }
		        
		    	//查看评估公司权重格式户
		        CALLBACK.view=function(index,row){
            		var editClientUrl = '<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.findEvaluateCompanyRuleById" context="admin"/>&jsoncallback=?&id='+row.id;
    	            ZDS_MESSAGE_CLIENT.openMenuLink('查看评估公司权重','查看评估公司权重',editClientUrl + "&openMethod=tabs");
                }
		        
		    	//删除评估公司权重
		        CALLBACK.del=function(index,row){
		        	$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.evaluateCompanyRule.delEvaluateCompanyRule" context="admin"/>',
	                    data: {id : row.id},
	                    dataType: 'json',
	                    success: function (data) {
	                        if (data.resultStatus == 0) {
	                        	 $.ZMessage.warning("提示", "删除成功", function () {
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
                }
		    	
		    	//导出评估公司权重列表
		        CALLBACK.exports=function(){
		        	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=评估公司权重列表导出文档";
                    var param=$("table").html();
					$("form").remove("#exportFrom");
                    $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
                    $("#exportFrom").submit();
                }
		        
                //初始化
                $.ZUI.init();
                
                //地址格式化
                $("#selectAddress").Address({
                    callback:function(infos,selected_ids) {
                    	showStreet:false,//不显示街道
                        cityUrl:"cityData.json",//获取城市数据源
                        var str = '';
                        var strCode = '';
                        for(var i=0;i<infos.length;i++) {
                            if(str==""){
                                str = str+infos[i];
                                strCode = strCode+selected_ids[i];
                            }else{
                                str = str+" / "+infos[i];
                                strCode = strCode+","+selected_ids[i];
                            }
                        }
                        $('#region').val(str);
                        $('#cityName').val(strCode);
                    }
                });
            });
</script>
</body>
</html>