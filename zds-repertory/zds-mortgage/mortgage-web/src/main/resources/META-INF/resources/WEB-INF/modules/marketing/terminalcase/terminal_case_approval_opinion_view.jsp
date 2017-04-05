<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 终端进件审批信息查看 -->
<form id="terminalCaseApprovalOpinion_form" class="zui-form " method="post" enctype="multipart/form-data">
	<input type="hidden" id="id"  value="${terminalCaseApprovalOpinionVo.id }"> 
	<input type="hidden" id="caseApplyId" value="${terminalCaseApprovalOpinionVo.caseApplyId }"> 
	
	<div class="page-box">
					<div class="page-title">审批意见</div>
		    		<div class="p5">
						<table class="table-detail">
							<tr>
								<input type="hidden" id="callBackApprovalResult" value="${terminalCaseApprovalOpinionVo.approvalResult }"/>
					            <td class="td-title pct10"><b class="c-red mr5">*</b>操作</td>
								<td class="pct20">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											${terminalCaseApprovalOpinionVo.approvalResult }
										</dd>
									</dl>
								</td>
								<!-- 这个拒绝原因等后面客户给 -->
					            <td class="td-title pct10  aa" style="display: none;"><b class="c-red mr5">*</b>拒绝原因</td>
								<td class="pct20 aa" style="display: none;">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											${terminalCaseApprovalOpinionVo.refuseReason }
										</dd>
									</dl>
								</td>
								<td class="td-title pct10 aa" style="display: none;"></td>
					        	<td class="pct30 aa" style="display: none;"></td>
								
								
								
					            <td class="td-title pct10 bb" style="display: none;"><b class="c-red mr5">*</b>分配机构</td>
								<td class="pct20 bb" style="display: none;">
									 <dl class="form-item">
						                <dd class="detail f12">
						                    <label>
						                        ${terminalCaseApprovalOpinionVo.organizationName }
						                    </label>
						                </dd>
            						</dl>
								</td>
					            <td class="td-title pct10 bb" style="display: none;"><b class="c-red mr5">*</b>分配人</td>
								<td class="pct30 bb" style="display: none;">
									<dl class="form-item form-auto">
										<dd class="detail f12">
											${terminalCaseApprovalOpinionVo.channelManagerName }
										</dd>
									</dl>
								</td>
								
								<td class="td-title pct10 cc"></td>
					        	<td class="pct20 cc"></td>
								<td class="td-title pct10 cc"></td>
					        	<td class="pct30 cc"></td>
				     		</tr>
							
							
							<tr>
					            <td class="td-title">备注</td>
					            <td colspan="5">
					                <label>
					                	${terminalCaseApprovalOpinionVo.mo }
					                </label>
					            </td>
					        </tr>
							
						</table>
					</div>
				</div>
		

</form>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message'], 
		function($, CALLBACK) {
		
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
		
	});
</script>
</body>
</html>