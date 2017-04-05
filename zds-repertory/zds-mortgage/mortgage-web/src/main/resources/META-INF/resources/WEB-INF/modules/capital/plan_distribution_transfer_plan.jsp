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
			<input type="hidden" id="creditEntrustId" value="${creditEntrustId }"/>
			<input type="hidden" id="caseApplyId" value="${caseApplyVo.id }"/>
			<input type="hidden" id="caseLimitApplyId" value="${caseLimitApplyId }"/>
			<div class="page-box">
		        <div class="page-title">基本信息</div>
		        <div class="p5">
		            <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">案件号</td>
		                    <td class="pct20">${caseApplyVo.caseApplyCode }</td>
		                    <td class="td-title pct10">资金预计到账时间</td>
		                    <td class="pct20">${expectDate }</td>
		                    <td class="td-title pct10">到账日期</td>
		                    <td class="pct30">${actualDate }</td>
		                </tr>
		                <tr>
		                    <td class="td-title">抵押模式</td>
		                    <td>${pledgeType }</td>
		                    <td class="td-title">资金计划</td>
		                    <td>${creditEntrustVo.creditEntrustName }</td>
		                    <td class="td-title">申请额度(元)</td>
		                    <td id="totalQuato">${totalQuato }</td>
		                </tr>
		                <tr>
		                    <td class="td-title">回款银行</td>
		                    <td>${creditEntrustVo.collectionAccountBank }</td>
		                    <td class="td-title">回款账户名</td>
		                    <td>${creditEntrustVo.collectionAccountName }</td>
		                    <td class="td-title">回款账号</td>
		                    <td>${creditEntrustVo.collectionAccountNo }</td>
		                </tr>
		                <tr>
		                    <td class="td-title">申请信息</td>
		                    <td>${applyEmpName }${applyDate }</td>
		                    <td class="td-title">取消信息</td>
		                    <td>${cancelEmpName }${cancelDate }</td>
		                    <td class="td-title">是否获得备付资金分配</td>
		                    <td>${isDistribution }</td>
		                </tr>
		                <tr>
		                    <td class="td-title">当前状态</td>
		                    <td>${effectiveStatus }</td>
		                    <td class="td-title"></td>
		                    <td></td>
		                    <td class="td-title"></td>
		                    <td></td>
		                </tr>
		            </table>
		        </div>
	        </div>
	        <dl class="form-item">
				<dt class="title">资金计划名称：</dt>
				<dd class="detail">
					<input class="zui-combobox zui-validatebox" id="creditEntrustNewId" name="creditEntrustNewId" type="hidden" value="${creditEntrustVo.id }"
                             data-url="<z:ukey key="com.zdsoft.finance.capital.getCreditEntrustListByCapitalistId" context="admin"/>&jsoncallback=?&capitalistId=${caseApplyVo.capitalSource}"
                             data-valuefield="id"  data-textfield="creditEntrustName" validate-type="Require">
				</dd>
			</dl>
	        <div class="form-btn">
               	<button id="cancelBtn" type="button" class="btn-gray">取消</button>
               	<button id="confirmInTo" type="button" class="btn-blue">转资金计划</button>
            </div>
		</form>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK, TOOL) {
			// 初始化
			$.ZUI.init();
			
			// 确认转入
			$('#confirmInTo').click(function(){
				var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
				if(isValidate){
					var creditEntrustNewId = $('#creditEntrustNewId').ZCombobox('getValue');
					var creditEntrustId = $('#creditEntrustId').val();
					var caseApplyId = $('#caseApplyId').val();
					var caseLimitApplyId = $('#caseLimitApplyId').val();
					$.ajax({
		                type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.capital.confirmInfo" context="admin"/>',
		                data: {'creditEntrustId' : creditEntrustId,'caseApplyId' :caseApplyId, 'creditEntrustNewId':creditEntrustNewId,'caseLimitApplyId':caseLimitApplyId},
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
				}else{
					$.ZMessage.error('错误','请选择转入的资金计划');
				}
			});
			// 取消按钮
	        $('#cancelBtn').click(function(){
	        	 ZDS_MESSAGE_CLIENT.closeSelf();
	        });
			
	        $(function(){
				var tempValue = TOOL.formatCurrency($('#totalQuato').html());
				$('#totalQuato').html(tempValue);
			});
		});
		
	</script>
</body>
</html>