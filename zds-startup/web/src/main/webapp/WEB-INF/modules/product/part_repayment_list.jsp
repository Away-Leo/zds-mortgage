<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>分段还款</title>
</head>
<body>
<div class="frm-content" id="partForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">分段还款筛选条件</div>
		<div class="p10">
			<form id="part_search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">时间：</dt>
					<dd class="detail">
						<label>
							<input class="zui-combobox zui-validatebox" type="hidden" id="timeSection" name="timeSection|E|S"/>
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">利率：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input nwidth2" id="rate" name="rate|RE|D">
						</label>
					</dd>
					<dd class="detail">
						<label> 
							<input class="zui-combobox zui-validatebox" data-width="94" id="rateUtil" name="rateUtil|E|S"
								data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
						    	data-valuefield="fullcode" data-textfield="name">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn-search-part" value="查询" />
					<input type="button" class="btn-search-gray" id="btn-reset-part" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">分段还款</div>
		<div class="p10">
			<div id="part_ztoolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="part_repayment_add"></a>
        	</div>
			<div id="partRepaymentTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.partRepayment.list" context="admin"/>&jsoncallback=?&productId|E|S=${productId}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#part_ztoolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:timeSectionName">时间</th>
            			<th data-options="field:rate" formatter="showRate">利率(%)</th>
            			<th data-options="field:id" formatter="partOperate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="part_dialog_add" style="display: none">
	<div id="part_dialog_add_form">
		<form class="zui-form" id="addFormPart">
			<input type="hidden" name="id" />
			<input type="hidden" name="productId" value="${productId}" />
			<dl class="form-item">
				<dt class="title">
					<b class="c-red mr5">*</b>
					时间：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="timeSection"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=timeType"
						    data-valuefield="fullcode" data-textfield="name" >
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title">
					<b class="c-red mr5">*</b>
					利率：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-input nwidth2" validate-type="Require" name="rate">
					</label>
				</dd>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" data-width="94" name="rateUtil"
								data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=rateUtil"
						    	data-valuefield="fullcode" data-textfield="name">
					</label>
				</dd>
			</dl>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//编辑
	$("#part_dialog_add").Zdialog({
        width: 700, height: 250, closed: true, title: "新增",
        buttons: [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	partSave();
                }
            },
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#part_dialog_add").Zdialog("close");
                }
            }
        ]
    });
	//打开添加
	CALLBACK.part_repayment_add = function(){
		$("#addFormPart input[name='id']").val("");
		$("#part_dialog_add").Zdialog("open");
	}
	
	//保存
	function partSave(){
		var validate = $.ZUI.validateForm($('#addFormPart'));
    	if (!validate) {
    		$.ZMessage.error("错误", "数据验证失败!", function () {
            });
            return false;
        }
    	var formData = $("#addFormPart").serializeArray();
    	$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.partRepayment.save" context="admin"/>&jsoncallBack=?',
			data:formData,
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						$("#part_dialog_add").Zdialog("close");
						var formArray=$("#part_search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						$('#partRepaymentTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	}
	//操作
	CALLBACK.partOperate = function(row,value){
		var html = "<a title='修改' class='handler-icon icon-btn22' onclick='partEdit'></a>";
		html += "<a title='删除' class='handler-icon icon-btn12' onclick='partDel'></a>";
		return html;
	};
	//数据
	CALLBACK.showRate = function(row,value){
		return row.rate + row.rateUtilName;
	};
	//编辑
	CALLBACK.partEdit = function(index,data){
		$("#addFormPart input[name='id']").val(data.id);
		$("#addFormPart input[name='rate']").val(data.rate);
		$("#addFormPart input[name='rateUtil']").ZCombobox("setValue",data.rateUtil);
		$("#addFormPart input[name='timeSection']").ZCombobox("setValue",data.timeSection);
		$("#part_dialog_add").Zdialog("open");
	};
	//删除
	CALLBACK.partDel = function(index,data){
		$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.partRepayment.deleted" context="admin"/>&jsoncallBack=?',
			data:{
				id : data.id
			},
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						var formArray=$("#part_search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						$('#partRepaymentTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	};
	//初始化页面
	$.ZUI.initForms('#partForm');
	$.ZUI.initGrid("#partForm");
	$.ZUI.initForms('#part_dialog_add_form');
	//时间拉
	$("#timeSection").ZCombobox({
		url:'<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=timeType',
 		valueField: "fullcode",            
		textField: "name",
		onSelect : function(value,text,index){
        	//选择事件
        }
	});
	//查询
	$('#btn-search-part').click(function(){
		var formArray=$("#part_search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		$('#partRepaymentTable').ZTable("reload",formArray);
	});
	//重置
	$("#btn-reset-part").click(function(){
		$("#part_search_from")[0].reset();
		$("#timeSection").ZCombobox("setValue","");
	});
});
</script>
</html>