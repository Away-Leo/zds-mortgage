<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申诉</title>
</head>
<body>
	<!-- 基本信息 -->
	<%@include file="common/case_apply_simple_view.jsp" %>
	<!-- 押品信息列表 -->
	<%@ include file="common/house_single_list.jsp"%>

	
<div id="div-detain" class="page-box">
		<div class="page-title" id="div-detain-form">其他信息</div>
	<div class="p5">
		<form id="evaluatedAppealForm" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="housePropertyId" name="housePropertyId" value="${housePropertyId}">
			<input type="hidden" id=evaluatedAppealVoId name="id" value="${evaluatedAppealVo.id}">
			<input type="hidden" id=sumitted name="sumitted" value="">
			<table class="table-detail">
				<tr>
					<td class="td-title">申诉人</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                       <input class="zui-input zui-validatebox" type="text" validate-type="Require" id = "appealName" name="appealName" value="${evaluatedAppealVo.appealName }" readOnly>
		                       <input type="hidden" id = "appealEmployeId" name="appealEmployeId" value="${evaluatedAppealVo.appealEmployeId }">
		                    </dd>
                    	</dl> 
					</td>
					<td class="td-title">申诉时间</td>
						<input class="zui-input zui-validatebox" type="text" validate-type="Require" id = "appealDate" name="appealDate" value="${evaluatedAppealVo.appealDate }" hidden>
					<td class="pct20" id="formateTime">
<!-- 						<dl class="form-item form-auto"> -->
<!-- 							<dd class="detail"> -->
<!-- 			                    <label>  -->
<%-- 			                    	<input class="zui-input zui-validatebox" type="text" validate-type="Require" id = "appealDate" name="appealDate" value="${evaluatedAppealVo.appealDate }" readOnly> --%>
<!-- 								</label> -->
<!-- 		              		</dd> -->
<!-- 	              		</dl> -->
						
						
					</td>
					<td class="td-title">有无特殊因素</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
		                        <input class="zui-combobox" validate-type="Require" id="isSpecificFactor" type="hidden" name="isSpecificFactor" value="${evaluatedAppealVo.isSpecificFactor }"
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0079"
			                           data-valuefield="fullcode" data-textfield="name" data-toggle="combobox">
		                    </dd>
	                 </dl>  
					</td>
				</tr>
				<tr>
					<td class="td-title">备注</td>
					<td colspan="5">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<textarea class="zui-area row-width" name="remark" 
													placeholder="最多可以输入500个字符">${evaluatedAppealVo.remark }</textarea>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-submit" class="btn-blue mr10">提交</button>
</div>	
</body>

<script type="text/javascript">
seajs.use([ 'jquery', 'zd/tools','zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox','zd/jquery.zds.button','zd/jquery.zds.message', 'zd/jquery.zds.table',
     		'zd/jquery.zds.form' ,'zd/jquery.zds.message','zd/jquery.zds.validate'], function($,ZTOOlS,CALLBACK,ZTable,ZUI_MESSAGE_CLIENT) {
	var appealDatea=$("#appealDate").val();
	
	$("#formateTime").html(ZTOOlS.strToTime(appealDatea));
	
	$("#btn-save").bind("click", function(){
		$('#sumitted').val('0');
		var params = $('#evaluatedAppealForm').serializeArray();
		save(params)
		
	});
	
	$("#btn-submit").bind("click", function(){
		$('#sumitted').val('1');
		var params = $('#evaluatedAppealForm').serializeArray();
		save(params)
		
	});
	
	function save(params){
		var validation = $.ZUI.validateForm($('#evaluatedAppealForm'));
		if(!validation){
			return false;
		}
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.casemanage.evaluated.saveAppeal" context="admin"/>',
            data: params,
            dataType: 'json',
            success: function (data) {
            	if (data.resultStatus == 0) {
            		$.ZMessage.success("提示", "保存成功", function () {
            			$('#evaluatedAppealVoId').val(data.optional.evaluatedAppealVoId);
            			if($('#sumitted').val() =='1'){
            				setTimeout(function(){
            					ZDS_MESSAGE_CLIENT.refreshOpenner();
            					ZDS_MESSAGE_CLIENT.closeSelf();
            		        },200);
            			}
                   	 });
            	}
            	else{
                   	$.ZMessage.error("错误", data.msg, function () {})
            	};
	                   
           	},
            error: function () {
              	$.ZMessage.error("错误", "评估价申诉系统异常，请联系管理员", function () {
                });
            }
            
            
        });
	}
	
	$.ZUI.init(); 
	
	
});

</script>
</html>