<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>档案清单</title>
</head>
<body>
<div class="frm-content" id="archivesBillForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">档案清单筛选条件</div>
		<div class="p10">
			<form id="bill_search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">档案名称：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="archivesName" name="archivesName|LK|S">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn-search-bill" value="查询" />
					<input type="button" class="btn-search-gray" id="btn-reset-bill" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">档案清单</div>
		<div class="p10">
			<div id="bill_ztoolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="archives_bill_add"></a>
				<a class="zui-toolbar" iconCls="icon-btn22" text="批量设置档案" buttonCls="btn-blue" handler="archives_bill_sets"></a>
        	</div>
			<div id="archivesBillTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.productArchivesBill.archivesBillList" context="admin"/>&jsoncallback=?&productId|E|S=${productId}","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#bill_ztoolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:archivesName">档案名称</th>
            			<th data-options="field:archivesLevelName">档案等级</th>
            			<th data-options="field:archivesTypeName">原件/复印件/照片件</th>
            			<th data-options="field:id" formatter="billSave">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="bill_dialog_add" style="display: none">
	<div id="bill_dialog_add_form">
		<form class="zui-form" id="addFormBill">
			<input type="hidden" name="id" />
			<input type="hidden" name="productId" value="${productId}" />
			<dl class="form-item">
				<dt class="title">
					<b class="c-red mr5">*</b>
					档案名称：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-input zui-validatebox" validate-type="Require" name="archivesName">
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="sptitle">
					<b class="c-red mr5">*</b>
					档案等级：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="archivesLevel"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=archivesLevel"
						    data-valuefield="fullcode" data-textfield="name" >
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title sptitle">
					<b class="c-red mr5">*</b>
					原件/复印件/照片件：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="archivesType"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=archivesType"
						    data-valuefield="fullcode" data-textfield="name" >
					</label>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="bill_dialog_sets" style="display: none">
	<div id="bill_dialog_sets_form">
		<form class="zui-form" id="setsForm">
			<input type="hidden" name="ids" />
			<dl class="form-item">
				<dt class="title">
					<b class="c-red mr5">*</b>
					档案等级：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="archivesLevel"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=archivesLevel"
						    data-valuefield="fullcode" data-textfield="name" >
					</label>
				</dd>
			</dl>
			<dl class="form-item">
				<dt class="title sptitle">
					<b class="c-red mr5">*</b>
					原件/复印件/照片件：
				</dt>
				<dd class="detail">
					<label>
						<input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="archivesType"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=archivesType"
						    data-valuefield="fullcode" data-textfield="name" >
					</label>
				</dd>
			</dl>
			<div class="page-box">
				<table id="setsTable" class="table-index"></table>
			</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//编辑
	$("#bill_dialog_add").Zdialog({
        width: 700, height: 250, closed: true, title: "编辑",
        buttons: [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	billSave();
                }
            },
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#bill_dialog_add").Zdialog("close");
                }
            }
        ]
    });
	//打开编辑
	CALLBACK.archives_bill_add = function(){
		$("#addFormBill input[name='id']").val("");
		$("#bill_dialog_add").Zdialog("open");
	}
	//批量设置
	$("#bill_dialog_sets").Zdialog({
        width: 700, height: 250, closed: true, title: "编辑",
        buttons: [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
					setsSave();
                }
            },
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#bill_dialog_sets").Zdialog("close");
                }
            }
        ]
    });
	//打开批量设置
	CALLBACK.archives_bill_sets = function(){
		var select = $("#archivesBillTable").ZTable("getSelections");
		if(select.length == 0){
			$.ZMessage.error("错误", "请选择需要批量设置的数据", function () {
            });
			return;
		}
		var ids = "";
		var html = "<tr><th>档案名称</th><th>档案名称</th></tr>";
		var index = 0;
		$(select).each(function(i,v){
			ids += this.id + ",";
			index = i;
			if((i%2) != 0){
				html += "<td>" + this.archivesName + "</td></tr>";
			}else{
				html += "<tr><td>" + this.archivesName + "</td>";
			}
		});
		if((index%2) == 0){
			html += "<td></td></tr>";
		}
		$("#setsTable").children().remove();
		$("#setsTable").append(html);
		$("#setsForm input[name='ids']").val(ids);
		$("#bill_dialog_sets").Zdialog("open");
	};
	
	//批量设置
	function setsSave(){
		var validate = $.ZUI.validateForm($('#setsForm'));
    	if (!validate) {
    		$.ZMessage.error("错误", "数据验证失败!", function () {
            });
            return false;
        }
    	var formData = $("#setsForm").serializeArray();
    	$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.archivesBill.sets" context="admin"/>&jsoncallBack=?',
			data:formData,
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						$("#bill_dialog_sets").Zdialog("close");
						var formArray=$("#bill_search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						$('#archivesBillTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	}
	//保存
	function billSave(){
		var validate = $.ZUI.validateForm($('#addFormBill'));
    	if (!validate) {
    		$.ZMessage.error("错误", "数据验证失败!", function () {
            });
            return false;
        }
    	var formData = $("#addFormBill").serializeArray();
    	$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.archivesBill.save" context="admin"/>&jsoncallBack=?',
			data:formData,
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						$("#bill_dialog_add").Zdialog("close");
						var formArray=$("#bill_search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						$('#archivesBillTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	}
	//操作
	CALLBACK.billSave = function(row,value){
		var html = "<a title='修改' class='handler-icon icon-btn22' onclick='billEdit'></a>";
		html += "<a title='删除' class='handler-icon icon-btn12' onclick='billDel'></a>";
		return html;
	};
	//编辑
	CALLBACK.billEdit = function(index,data){
		$("#addFormBill input[name='id']").val(data.id);
		$("#addFormBill input[name='archivesName']").val(data.archivesName);
		$("#addFormBill input[name='archivesLevel']").ZCombobox("setValue",data.archivesLevel);
		$("#addFormBill input[name='archivesType']").ZCombobox("setValue",data.archivesType);
		$("#bill_dialog_add").Zdialog("open");
	};
	//删除
	CALLBACK.billDel = function(index,data){
		$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.archivesBill.deleted" context="admin"/>&jsoncallBack=?',
			data:{
				id : data.id
			},
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						var formArray=$("#bill_search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						$('#archivesBillTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	};
	
	//初始化页面
	$.ZUI.initForms("#archivesBillForm");
	$.ZUI.initGrid("#archivesBillForm");
	$.ZUI.initForms("#bill_dialog_add_form");
	$.ZUI.initForms("#bill_dialog_sets_form");
	//查询
	$('#btn-search-bill').click(function(){
		var formArray=$("#bill_search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		$('#archivesBillTable').ZTable("reload",formArray);
	});
	//重置
	$("#btn-reset-bill").click(function(){
		$("#bill_search_from")[0].reset();
		$("#archives_capital").ZCombobox("setValue","");
	});
});
</script>
</html>