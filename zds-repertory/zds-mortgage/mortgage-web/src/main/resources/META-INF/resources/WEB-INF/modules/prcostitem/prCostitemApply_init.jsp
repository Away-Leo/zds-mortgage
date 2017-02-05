<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>收费支拥管理</title>
</head>
<body>
<div class="frm-content">
	<!-- 查询区域 -->
	<div class="page-box">
		<div class="page-title">收费支拥管理筛选条件</div>
		<div class="p10">
			<form id="search_from" class="zui-form form-search" method="post" enctype="multipart/form-data">
				<dl class="form-item">
					<dt class="title">申请时间：</dt>
					<dd class="detail">
						<label> 
							<input type="hidden" id="applyTimeS"/>
							<input id="d4311" type="text" class="zui-input width2-1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'applyTimeS'})">
						</label>
						<span class="word">至</span>
						<label>
							<input type="hidden" id="applyTimeE"/>
							<input id="d4312" type="text" class="zui-input width2-1" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'applyTimeE'})">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">申请人：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" name="applyEmpNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">状态：</dt>
					<dd class="detail">
						<label>
							<input class="zui-combobox zui-validatebox" id="status" name="status" type="hidden" data-multiple="false"
                               data-data="[{'id':'0','text':'草稿'},{'id':'1','text':'审批中'},{'id':'2','text':'暂停'},{'id':'3','text':'已作废'},{'id':'4','text':'审批通过'},{'id':'5','text':'审批不通过'}]"
                               data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input" id="orgNm" name="orgNm">
						</label>
					</dd>
				</dl>
				<dl class="form-btn">
					<input type="button" class="btn-search-blue" id="btn-search" value="查询" />
					<input type="button" class="btn-search-gray" id="btn-reset" value="重置" />
				</dl>
			</form>
		</div>
	</div>
	<!-- 列表区域 -->
	<div class="page-box">
		<div class="page-title">收费支拥</div>
		<div class="p10">
			<div id="ztoolbar">
				<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="add"></a>
        	</div>
			<div id="costitemTable" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.prCostitemApply.listData" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#ztoolbar"}'>
				<table>
        			<tr>
            			<th data-options="field:applyOrgNm">机构</th>
            			<th data-options="field:applyTime" formatter="formatterDate">申请时间</th>
            			<th data-options="field:applyEmpNm">申请人</th>
            			<th data-options="field:applyDepNm">申请人部门</th>
            			<th data-options="field:statusNm">状态</th>
            			<th data-options="field:id" formatter="operate">操作</th>
			        </tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
	//格式化时间
	CALLBACK.formatterDate = function(row,value){
		return window.formatDate(row,value);
	};
	//操作
	CALLBACK.operate = function(row,value){
		var html = "<a title='详情' class='handler-icon icon-btn31' onclick='detail'></a>";
		if(row.status == 0){
			html += "<a title='编辑' class='handler-icon icon-btn22' onclick='edit'></a>";
			html += "<a title='删除' class='handler-icon icon-btn12' onclick='del'></a>";
		}
		return html;
	};
	//刷新
	ZDS_MESSAGE_CLIENT.refreshThis=function(){
		var s = $("#applyTimeS").val();
		var e = $("#applyTimeE").val();
		var formArray=$("#search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		formArray += "&applyTime|BT|BT=" + s + "," + e;
		$('#costitemTable').ZTable("reload",formArray);
    };
    //详情
    CALLBACK.detail = function(index,row){
    	var editPrCostitem = '<z:ukey key="com.zdsoft.finance.prCostitemApply.detail" context="admin"/>&id=' + row.id;
        ZDS_MESSAGE_CLIENT.openMenuLink('费用支拥详情','费用支拥详情',editPrCostitem + "&openMethod=tabs");
    };
    //编辑
    CALLBACK.edit = function(index,row){
    	var editPrCostitem = '<z:ukey key="com.zdsoft.finance.prCostitemApply.add" context="admin"/>&id=' + row.id;
        ZDS_MESSAGE_CLIENT.openMenuLink('费用支拥编辑','费用支拥编辑',editPrCostitem + "&openMethod=tabs");
    };
    //删除
    CALLBACK.del = function(index,row){
    	$.ajax({
			url:'<z:ukey key="com.zdsoft.finance.prCostitemApply.del" context="admin"/>&jsoncallBack=?',
			data:{
				id:row.id
			},
			type:"post",
			dataType:"jsonp",
			success:function(rdata){
				if(rdata.status == 1){
					$.ZMessage.success("提示", rdata.msg, function () {
						var s = $("#applyTimeS").val();
						var e = $("#applyTimeE").val();
						var formArray=$("#search_from").serialize();
						formArray = decodeURIComponent(formArray, true);
						formArray += "&applyTime|BT|BT=" + s + "," + e;
						$('#costitemTable').ZTable("reload",formArray);
	                });
				}else{
					$.ZMessage.error("错误", rdata.msg, function () {
	                });
				}
			}
		});
    };
    
	//初始化页面
	$.ZUI.init();
	//新增
	CALLBACK.add = function(){
		var addPrCostitem = '<z:ukey key="com.zdsoft.finance.prCostitemApply.add" context="admin"/>';
        ZDS_MESSAGE_CLIENT.openMenuLink('费用支拥添加','费用支拥添加',addPrCostitem + "&openMethod=tabs");
	};
	
	//查询
	$('#btn-search').click(function(){
		var s = $("#applyTimeS").val();
		var e = $("#applyTimeE").val();
		var formArray=$("#search_from").serialize();
		formArray = decodeURIComponent(formArray, true);
		formArray += "&applyTime|BT|BT=" + s + "," + e;
		$('#costitemTable').ZTable("reload",formArray);
	});
	//重置
	$("#btn-reset").click(function(){
		$("#search_from")[0].reset();
	});
});
</script>
</html>