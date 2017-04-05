<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>合同模版</title>
</head>
<body>
<div class="frm-content" id="contractForm">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">合同模版筛选条件</div>
		<div class="p10">
			<form id="contract_search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">合同名称：</dt>
					<dd class="detail">
						<label>
							<input class="zui-input zui-validatebox" type="text" name="c|contractName|LK|S"/>
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn-search-contract" value="查询" />
					<input type="button" class="btn-search-gray" id="btn-reset-contract" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">合同模版</div>
		<div class="p10">
			<div id="contract_ztoolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="contract_add"></a>
        	</div>
			<div id="contractTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.productContract.list" context="admin"/>&jsoncallback=?&pc|productId|E|S=${productId}","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#contract_ztoolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:CONTRACTNAME">合同名称</th>
            			<th data-options="field:ATTACHMENTNAME" formatter="formatAttrNm">附件</th>
            			<th data-options="field:CONTRACTTYPENAME">合同类型</th>
            			<th data-options="field:ID" formatter="contractFunction">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="contract_dialog_add" style="display: none">
	<div id="Zbothselecter">
		<div class="power-own" style="height: 292px;">
			<ul class="power-select-list">
			</ul>
		</div>
		<div class="power-use" style="height: 292px;">
			<ul class="power-select-list">
			</ul>
		</div>
		<div class="power-btn" style="margin-top: 0px;padding-top: 70px;">
			<a href="javascript:void(0);" class="power-btn-blue" id="contractAdd">添加<i class="icon-power-delete ml5"></i></a>
			<a href="javascript:void(0);" class="power-btn-gray" id="contractDel"><i class="icon-power-add mr5"></i>移除</a>
			<a href="javascript:void(0);" class="power-btn-blue" id="contractAddAll">全部添加<i class="icon-power-delete ml5"></i></a>
			<a href="javascript:void(0);" class="power-btn-gray" id="contractDelAll"><i class="icon-power-add mr5"></i>全部移除</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//编辑
	$("#contract_dialog_add").Zdialog({
        width: 550, height: 410, closed: true, title: "添加合同",
        buttons: [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	productContractSave();
                }
            },
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#contract_dialog_add").Zdialog("close");
                }
            }
        ]
    });
	//打开添加
	CALLBACK.contract_add = function(){
		$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.productContract.selectContract" context="admin"/>&jsoncallBack=?',
			data:{
				productId:"${productId}"
			},
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					Zbothselecter(rdata.selectData);
					$("#contract_dialog_add").Zdialog("open");
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	};
	//附件显示
	CALLBACK.formatAttrNm = function(row,value){
		return value == null? "":value;
	};
	//选中或不选中
	window.contractClick = function(_this){
		$(_this).toggleClass("selected");
	};
	//设置选择数据和事件
	function Zbothselecter(selectData){
		var selectData = eval(selectData);
		var list = $("#Zbothselecter .power-own .power-select-list");
		var noList = $("#Zbothselecter .power-use .power-select-list");
		list.children().remove();
		noList.children().remove();
		$(selectData).each(function(i,v){
			$("#Zbothselecter .power-own .power-select-list").append("<li data-id=" + this.ID + " onclick='contractClick(this)'>"+this.CONTRACTNAME+"</li>")
		});
		
		$("#contractAdd").unbind("click").click(function(){
			var selectRows = list.find('.selected');
            if (selectRows.length === 0) {
                $.ZMessage.info('提示', '请选择要添加的合同模版！')
            }
            $.each(selectRows, function (i, item) {
                var selVal = $(selectRows[i]).attr("data-id");
                var selText = $(selectRows[i]).text();
                var selObj = {};
                selObj[selVal] = selText;
                selectData.splice(-1, 0, selObj);
                noList.append("<li data-id=" + selVal + " onclick='contractClick(this)'>"+selText+"</li>");
            });
            selectRows.remove();
		});
		$("#contractDel").unbind("click").click(function(){
			var selectRows = noList.find('.selected');
            if (selectRows.length === 0) {
                $.ZMessage.info('提示', '请选择要移除的合同模版！')
            }
            $.each(selectRows, function (i, item) {
                var selVal = $(selectRows[i]).attr("data-id");
                var selText = $(selectRows[i]).text();
                var selObj = {};
                selObj[selVal] = selText;
                selectData.splice(-1, 0, selObj);
                list.append("<li data-id=" + selVal + " onclick='contractClick(this)'>"+selText+"</li>");
            });
            selectRows.remove();
		});
		$("#contractAddAll").unbind("click").click(function(){
			var selectRows = list.find('li');
            if (selectRows.length === 0) {
                $.ZMessage.info('提示', '没有合同模版数据')
            }
            $.each(selectRows, function (i, item) {
                var selVal = $(selectRows[i]).attr("data-id");
                var selText = $(selectRows[i]).text();
                var selObj = {};
                selObj[selVal] = selText;
                selectData.splice(-1, 0, selObj);
                noList.append("<li data-id=" + selVal + " onclick='contractClick(this)'>"+selText+"</li>");
            });
            selectRows.remove();
		});
		$("#contractDelAll").unbind("click").click(function(){
			var selectRows = noList.find('li');
            if (selectRows.length === 0) {
                $.ZMessage.info('提示', '没有合同模版数据')
            }
            $.each(selectRows, function (i, item) {
                var selVal = $(selectRows[i]).attr("data-id");
                var selText = $(selectRows[i]).text();
                var selObj = {};
                selObj[selVal] = selText;
                selectData.splice(-1, 0, selObj);
                list.append("<li data-id=" + selVal + " onclick='contractClick(this)'>"+selText+"</li>");
            });
            selectRows.remove();
		});
	}
	
	//保存
	function productContractSave(){
		var productId = "${productId}";
		var addRows = $("#Zbothselecter .power-use .power-select-list").find('li');
		var contractIds = [];
		$(addRows).each(function(i,v){
			contractIds.push($(this).attr("data-id"));
		});
    	$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.productContract.save" context="admin"/>&jsoncallBack=?',
			data:{
				productId : productId,
				contractIds : contractIds
			},
			type:"post",
			traditional:true,
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						$("#contract_dialog_add").Zdialog("close");
						$('#contractTable').ZTable("reload",{});
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
	}
	//操作
	CALLBACK.contractFunction = function(row,value){
		var str = "<a title='详情' class='btn-blue' onclick='contractView'>详情</a>" +
    	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='contractDel'>删除</a>";
		return str;
	};
	//查看
	CALLBACK.contractView = function(index,data){
		//TODO 需要合同提供查看界面
		alert("需要合同模块提供查看界面");
	};
	//删除
	CALLBACK.contractDel = function(index,data){
		$.ZMessage.question("警告", "确认删除？", function () {
			$(".zd-message").ZWindow("close");
			$.ajax({
				url:'<z:ukey key="com.zdsoft.finance.productContract.deleted" context="admin"/>&jsoncallBack=?',
				data:{
					id : data.ID
				},
				type:"post",
				dataType:"jsonp",
				success:function(rdata){
					if(rdata.status == 1){
						$.ZMessage.success("提示", rdata.msg, function () {
							var formArray=$("#contract_search_from").serializeArray();
							$('#contractTable').ZTable("reload",formArray);
		                });
					}else{
						$.ZMessage.error("错误", rdata.msg, function () {
		                });
					}
				}
			});
		});
	};
	//初始化页面
	$.ZUI.initForms('#contractForm');
	$.ZUI.initGrid("#contractForm");
	$.ZUI.initForms('#contract_dialog_add_form');
	//查询
	$('#btn-search-contract').click(function(){
		var formArray=$("#contract_search_from").serializeArray();
		$('#contractTable').ZTable("reload",formArray);
	});
	//重置
	$("#btn-reset-contract").click(function(){
		$("#contract_search_from")[0].reset();
	});
});
</script>
</html>