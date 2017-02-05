<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>额度分配</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			
			<input type="hidden" id="creditEntrustId" name="creditEntrustId" value="${creditEntrustVo.id }"/>
			<input type="hidden" id="caseApplyId" name="caseApplyId" value="${caseApplyVo.id }"/>
			<div class="page-box">
		        <div class="page-title">基本信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">案件号</td>
		                    <td class="pct20">${caseApplyVo.caseApplyCode }</td>
		                    <td class="td-title pct10">资金预计到账时间</td>
		                    <td class="pct20"></td>
		                    <td class="td-title pct10">到账日期</td>
		                    <td class="pct30"></td>
		                </tr>
		                <tr>
		                    <td class="td-title">抵押模式</td>
		                    <td></td>
		                    <td class="td-title">信托计划名称</td>
		                    <td>${creditEntrustVo.creditEntrustName }</td>
		                    <td class="td-title">申请额度</td>
		                    <td>${caseApplyVo.applyAmount }</td>
		                </tr>
		                <tr>
		                    <td class="td-title">回款银行</td>
		                    <td></td>
		                    <td class="td-title">回款账户名</td>
		                    <td></td>
		                    <td class="td-title">回款账号</td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <td class="td-title">申请信息</td>
		                    <td></td>
		                    <td class="td-title">取消信息</td>
		                    <td></td>
		                    <td class="td-title">是否获得备付资金分配</td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <td class="td-title">当前状态</td>
		                    <td>${caseApplyVo.caseApplyStatus }</td>
		                    <td class="td-title"></td>
		                    <td></td>
		                    <td class="td-title"></td>
		                    <td></td>
		                </tr>
		            </table>
		        </div>
	        </div>
	        <div class="page-box">
				<div class="page-title">资金计划分配</div>
				<dl class="form-item">
					<dt class="title">资金计划名称：</dt>
					<dd class="detail">
						<input class="zui-combobox zui-validatebox" id="creditEntrustNewId" name="creditEntrustNewId" type="hidden" value="${creditEntrustVo.id }"
                              data-url="<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustList" context="admin"/>&jsoncallback=?"
                              data-valuefield="id"  data-textfield="creditEntrustName" validate-type="Require">
                        <a id="confirmInTo" class="btn-blue" href="javascript:void(0);">确认转入</a>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title"></dt>
					<dd class="detail">
						<a id="quotaApplyBtn" class="btn-blue" href="javascript:void(0);">申请额度</a>
						<a id="quotaCancelBtn" class="btn-blue ml10" href="javascript:void(0);">取消额度</a>
						<a id="reserveFundBtn" class="btn-blue ml10" href="javascript:void(0);">分配备付金</a>
						<a id="reserveFundCancelBtn" class="btn-blue ml10" href="javascript:void(0);">取消备付金分配</a>
					</dd>
				</dl>
			</div>
		</form>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			// 初始化
			$.ZUI.init();
			
			$('#quotaApplyBtn').click(function(){
				var creditEntrustId = $('#creditEntrustId').val();
				var caseApplyId = $('#caseApplyId').val();
				
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.quotaApply" context="admin"/>',
	                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId},
	                dataType: 'json',
	                success: function (data) {
	                	if(data.resultStatus == 0){
	                		$.ZMessage.success('成功',data.msg);
	                	}else{
	                		$.ZMessage.error('错误',data.msg);
	                	}
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			});
			$('#quotaCancelBtn').click(function(){
				var creditEntrustId = $('#creditEntrustId').val();
				var caseApplyId = $('#caseApplyId').val();
				
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.quotaCancel" context="admin"/>',
	                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId},
	                dataType: 'json',
	                success: function (data) {
	                	if(data.resultStatus == 0){
	                		$.ZMessage.success('成功',data.msg);
	                	}else{
	                		$.ZMessage.error('错误',data.msg);
	                	}
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			});
			$('#reserveFundBtn').click(function(){
				var creditEntrustId = $('#creditEntrustId').val();
				var caseApplyId = $('#caseApplyId').val();
				
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.reserveFund" context="admin"/>',
	                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId},
	                dataType: 'json',
	                success: function (data) {
	                	if(data.resultStatus == 0){
	                		$.ZMessage.success('成功',data.msg);
	                	}else{
	                		$.ZMessage.error('错误',data.msg);
	                	}
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			});
			$('#reserveFundCancelBtn').click(function(){
				var creditEntrustId = $('#creditEntrustId').val();
				var caseApplyId = $('#caseApplyId').val();
				
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.reserveFundCancel" context="admin"/>',
	                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId},
	                dataType: 'json',
	                success: function (data) {
	                	if(data.resultStatus == 0){
	                		$.ZMessage.success('成功',data.msg);
	                	}else{
	                		$.ZMessage.error('错误',data.msg);
	                	}
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			});
			
			// 确认转入
			$('#confirmInTo').click(function(){
				var creditEntrustNewId = $('#creditEntrustNewId').ZCombobox('getValue');
				var creditEntrustId = $('#creditEntrustId').val();
				var caseApplyId = $('#caseApplyId').val();
				
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.capital.confirmInfo" context="admin"/>',
	                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId, 'creditEntrustNewId':creditEntrustNewId},
	                dataType: 'json',
	                success: function (data) {
	                	if(data.resultStatus == 0){
	                		$.ZMessage.success('成功',data.msg);
	                	}else{
	                		$.ZMessage.error('错误',data.msg);
	                	}
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            }); 
			});
			
		});
		
	</script>
</body>
</html>