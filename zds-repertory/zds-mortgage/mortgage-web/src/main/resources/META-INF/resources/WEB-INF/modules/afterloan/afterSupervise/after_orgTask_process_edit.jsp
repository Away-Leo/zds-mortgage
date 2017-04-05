<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>  
		<div class="frm-content">     
				<div class="page-box">
					<div class="p10">
						<!-- 案件信息end -->
						<!-- 案件信息begin -->    
						<%@ include file="after_caseApply_view.jsp"%>  
						<!-- 案件信息end -->  
						<!-- 案件信息end -->
						<!-- 联系人信息begin -->    
						<%@ include file="after_customer_list.jsp"%>  
						<!-- 联系人信息end -->  
						<!-- 最近跟催列表列表 -->    
						<!-- begin -->
						<%@ include file="../loanMiddleMonitor/lately_followInfo_list.jsp"%>  
						<!-- end -->  
						<!-- 跟催begin -->    
						<c:if test="${not empty afterSuperviseVo.followInfoId }"> 
							<%@ include file="after_followInfo_process_view.jsp"%> 
						</c:if>  
						<!-- 跟催end -->     
					<form id="supervise_form" class="zui-form " method="post" enctype="multipart/form-data">
			<div class="page-box">  
				<div class="page-title">督办事项</div>  
				<div class="p5">       
				     <table class="table-detail">  
				      <input type="hidden" id="caseApplyId" value="${caseApplyId}" class="zui-input" name="caseApplyId"/>
				      <input type="hidden" id="afterSuperviseId" value="${afterSuperviseVo.id }" class="zui-input" name="afterSuperviseId"/>
				         
				           <tr>
				               <td class="td-title pct10">督办类型</td>   
									<td class="pct30">
										<dl class="form-item form-auto">      
											<dd class="detail f12">
												${afterSuperviseVo.superviseTypeName }
											</dd>
										</dl>  
								</td>
				               <td class="td-title pct10">督办子类型</td>
									<td class="pct30">   
										<dl class="form-item form-auto">
											<dd class="detail f12">
												${afterSuperviseVo.superviseChildTypeName }
											</dd>
										</dl>
								</td>
				               <td class="td-title pct10"></td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail"><label></label></dd>
				                   </dl>
				               </td>
				           </tr>
				           <tr>
				               <td class="td-title pct10">督办接收人</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">     
				                          <dd class="detail f12"><label>
				                          	${afterSuperviseVo.superviseReceiverName }
				                          </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">督办抄送人</td>
				               <td class="pct20">      
				                   <dl class="form-item form-auto"> 
				                       <dd class="detail f12"><label>
				                     	  ${afterSuperviseVo.superviseCopyReceiverName }
				                       </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">处理时限</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">      
				                       <dd class="detail f12">
				                       <label id="processingDateStr">
				                       </label></dd>
				                   </dl>
				               </td>
				           </tr>
				           <tr>
				              <td class="td-title pct10">督办说明 </td>            
				               <td colspan="5">           
				                   <dl class="form-item form-auto">   
				                          <dd class="detail f12"><label>
				                          <textarea class="zui-area zui-validatebox row-width" readonly="readonly"
		                                      placeholder="">${afterSuperviseVo.remark }</textarea>
				                          </label></dd>
				                   </dl>   
			               </td>
				           </tr>
				           <tr>
				               <td class="td-title pct10"><b class="c-red mr5">*</b>派工</td>
				               <td class="pct20">   
				                   <dl class="form-item form-auto">     
				                           <dd class="detail"><label>  
				                          <input type="text" id="dispatchName" value="${afterDispatchVo.dispatchName }" class="zui-input zui-validatebox" name="dispatchName" readonly="readonly" validate-type="Require"/>
				                          <input type="hidden" id="dispatchCode" value="${afterDispatchVo.dispatchCode }" class="zui-input" name="dispatchCode"/>
				                          </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">所属部门</td>
				               <td class="pct20">      
				                   <dl class="form-item form-auto"> 
				                       <dd class="detail"><label>  
				                     	  <input type="text" id="dispatchDepartmentName" value="${afterDispatchVo.dispatchDepartmentName }" class="zui-input" name="dispatchDepartmentName" readonly="readonly"/>
				                     	  <input type="hidden" id="dispatchDepartmentCode" value="${afterDispatchVo.dispatchDepartmentCode }" class="zui-input" name="dispatchDepartmentCode" readonly="readonly"/>
				                       </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10"></td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">      
				                       <dd class="detail f12">
				                       <label id="processingDateStr">
				                       </label></dd>
				                   </dl>
				               </td>
				           </tr>
				     </table>
				</div>
			</div>    
			</form> 
						
					</div>
			</div>
		</div>
		<script>   
			seajs.use(['jquery', 'zd/tools', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.table', 'zd/jquery.zds.form'], function($, ZTOOL, CALLBACK) {
				if('${afterSuperviseVo.processingDate }'){   
					$("#processingDateStr").text(ZTOOL.strToDate('${afterSuperviseVo.processingDate }'));
				}
				//派工
				$("#dispatchName").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: true,
			        onOk: function (data) {   
			        	$('#dispatchCode').val(data[0].empCd);                    
						$('#dispatchName').val(data[0].empNm);  
						$('#dispatchDepartmentName').val(data[0]._data.orgNm);   
						$('#dispatchDepartmentCode').val(data[0]._data.orgCd);
			     },onBeforeOpen:function(){  
			         //重新获取列表中的值
			         var arr =[$("#dispatchCode").val(),$("#dispatchName").val()];
			         $("#dispatchName").Zseleter("setValue",arr);
			         $("#zd-seleter-search")[0].reset();   
			         return true;
			     }});
				//保存
			 	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {  
			 		var isValidateSuperviseForm = $.ZUI.validateForm($('#supervise_form'));
			 		//校验
					if(!isValidateSuperviseForm){ 
						$.ZMessage.info("提示", "请完善必填项信息！", function () {
	                    });	   
						return false;
					}
					
		            //校验
		            var params = "";
		            var args = JSON.parse(datas.args);
		            params += '&processInstanceId=' + args.processInstanceId;
		            params += '&taskInstanceId=' + args.taskInstanceId;
		            params += '&taskId=' + args.taskId;
		            params += '&taskName=' + args.taskName;
		            params += '&jobAppCd=' + args.jobAppCd;
		            params += '&'+$('#supervise_form').serialize();  
		            params += '&businessKey=${businessKey}';
		            $.ajax({    
		                url:'<z:ukey key="com.zdsoft.finance.afterloan.afterSupervise.saveOrUpdateOrgTask" context="admin"/>&jsoncallback=?',
		                data:params,
		                type:"post",
		                dataType:"json",
		                success:function(rdata){
		                    var msg="";
		                    if(rdata.resultStatus == 0){   
		                        //执行回调函数
		                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,rdata.msg);
		                    }else{
		                        //执行回调函数
		                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,rdata.msg);
		                    }
		                }
	            });
            //---------end------流程中有修改页面，需要提交业务数据操作------------------
			    };  
			    //提交方法
			    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
			});
		</script>


