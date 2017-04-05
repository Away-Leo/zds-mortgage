<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 终端进件审批信息 -->
<form id="terminalCaseApprovalOpinion_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="id" name="terminalCaseApprovalOpinionVo.id" value="${terminalCaseApprovalOpinionVo.id }"> 
	<input type="hidden" id="caseApplyId" name="terminalCaseApprovalOpinionVo.caseApplyId" value="${terminalCaseApprovalOpinionVo.caseApplyId }"> 
	
	<div class="page-box">
					<div class="page-title">审批意见</div>
		    		<div class="p5">
						<table class="table-detail">
							<tr>
								<input type="hidden" id="callBackApprovalResult" value="${terminalCaseApprovalOpinionVo.approvalResult }"/>
								<input type="hidden" id="organizationCdValue"  value="${terminalCaseApprovalOpinionVo.organizationCd }"  />
					            <td class="td-title pct10"><b class="c-red mr5">*</b>操作</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" id="approvalResult" name="terminalCaseApprovalOpinionVo.approvalResult" value="${terminalCaseApprovalOpinionVo.approvalResult }"
												 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0070"
												 data-valuefield="fullcode" data-textfield="name" validate-type="Require"
												 data-callback="approvalOpinion" >
										</dd>
									</dl>
								</td>
								
								<!-- 这个拒绝原因等后面客户给 -->
					            <td class="td-title pct10 aa" style="display: none;"><b class="c-red mr5">*</b>拒绝原因</td>
								<td class="pct20 aa" style="display: none;">
									<dl class="form-item form-auto">
										<dd class="detail">
											<input class="zui-combobox zui-validatebox" type="hidden" name="terminalCaseApprovalOpinionVo.refuseReason" value="${terminalCaseApprovalOpinionVo.refuseReason }"
												 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0055"
												data-valuefield="fullcode" data-textfield="name" >
										</dd>
									</dl>
								</td>
								<td class="td-title pct10 aa" style="display: none;"></td>
					        	<td class="pct30 aa" style="display: none;"></td>
								
								
					            <td class="td-title pct10 bb" style="display: none;"><b class="c-red mr5">*</b>分配机构</td>
								<td class="pct20 bb" style="display: none;">
									 <dl class="form-item">
						                <dd class="detail" id="collectionOrgs">
						                    <label>
						                       	<input class="zui-input zui-validatebox" type="hidden" id="organizationCd" name="terminalCaseApprovalOpinionVo.organizationCd" value="${terminalCaseApprovalOpinionVo.organizationCd }">
		              							<input class="zui-input zui-validatebox" type="text" id="organizationName" name="terminalCaseApprovalOpinionVo.organizationName" value="${terminalCaseApprovalOpinionVo.organizationName }">
						                    </label>
						                </dd>
            						</dl>
								</td>
					            <td class="td-title pct10 bb" style="display: none;"><b class="c-red mr5">*</b>分配人</td>
								<td class="pct30 bb" style="display: none;">
									<dl class="form-item form-auto">
										<dd class="detail">
						            		<input class="zui-combobox zui-validatebox" type="hidden" id="channelManagerCd" name="terminalCaseApprovalOpinionVo.channelManagerCd" value="${terminalCaseApprovalOpinionVo.channelManagerCd}">
						            		
										</dd>
									</dl>
								</td>
								
								<td class="td-title pct10 cc"></td>
					        	<td class="pct20 cc" ></td>
								<td class="td-title pct10 cc"></td>
					        	<td class="pct30 cc" ></td>
								
				     		</tr>
				     		
							
							
							<tr>
					            <td class="td-title">备注</td>
					            <td colspan="5">
					                <label>
					                    <textarea class="zui-area row-width" placeholder="最多可以输入500个字符" id="mo" name="terminalCaseApprovalOpinionVo.mo" value="${terminalCaseApprovalOpinionVo.mo }" >${terminalCaseApprovalOpinionVo.mo }</textarea>
					                </label>
					            </td>
					        </tr>
							
						</table>
					</div>
				</div>
		

</form>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message', 'zd/completer'], 
		function($, CALLBACK) {
		
		// 下拉回调
 		CALLBACK.approvalOpinion = function(v,t){
			changeValue(v);
		};
		
		// 初始化值
		$(function(){
			var v = $('#callBackApprovalResult').val();
			changeValue(v);
		});
 		
 		function changeValue(v){
 			if("YWDM007002"==v){
				$(".aa").show();
				$(".bb").hide();
				$(".cc").hide();
				
			}else if("YWDM007001"==v){
				$(".aa").hide();
				$(".bb").show();
				$(".cc").hide();
			}else{
				$(".aa").hide();
				$(".bb").hide();
				$(".cc").show();
			}
 		}
 		
        var data = '${data}';
        //分配机构的模糊搜索
        $("#organizationName").completer({
            suggest: true,//默认false
            idField: 'code',//默认id,唯一标识字段
            nameField: 'name',//默认name,下拉列表展示数据的字段
            valueField: 'py',//默认value,根据值查询数据的字段
            source:data,
            writable: false,//默认false，是否可自定义输入
            placeObj:$("#collectionOrgs"),//悬浮框需要定位到的对象
            complete: function (data) {
                $('#organizationName').val(data.name);
                $('#organizationCd').val(data.code);

               getEmpsByOrg(data.code);
            },
            filter: function (val) {
                return val;//过滤输入的value值
            }
        });
        //初始化分配人下拉框
        $(function(){
        	var organizationCd = $("#organizationCdValue").val();
        	if(organizationCd){
        		getEmpsByOrg(organizationCd);
        	}
        });
        
        function getEmpsByOrg(v){
            $('#channelManagerCd').ZCombobox({
           	 	valueField: "empCd",
                textField: "empNm",
                url:"<z:ukey key='com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.channelManagerByOrgCd' context='admin'/>&jsoncallback=?&organizationCd="+v,
                onSelect:function(value,text,index,data){
				 }
           });
        }
		 
	 $.ZUI.initForms('#terminalCaseApprovalOpinion_form');

		
	});
</script>
</body>
</html>