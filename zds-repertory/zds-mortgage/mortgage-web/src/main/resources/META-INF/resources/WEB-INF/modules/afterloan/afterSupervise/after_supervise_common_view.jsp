<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>      
<form id="supervise_form" class="zui-form " method="post" enctype="multipart/form-data">
<div class="page-box">  
				<div class="page-title">督办查看</div>  
				<div class="p5">       
				     <table class="table-detail">  
				      <input type="hidden" id="caseApplyId" value="${caseApplyId}" class="zui-input" name="caseApplyId"/>
				      <input type="hidden" id="id" value="${afterSuperviseVo.id }" class="zui-input" name="id"/>
				         
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
				                       <label id="processingDateStr"></label></dd>
				                   </dl>
				               </td>
				           </tr>  
				           <tr>
				               <td class="td-title pct10">督办人</td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                          <dd class="detail f12"><label>${empty empNm?afterSuperviseVo.superviserName:empNm }</label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">跟踪部门</td>  
				               <td class="pct20">      
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label>${empty departmentName?afterSuperviseVo.departmentName:departmentName }</label></dd>
				                   </dl>
				               </td>     
				               <td class="td-title pct10"></td>
				               <td class="pct20">
				                   <dl class="form-item form-auto">
				                       <dd class="detail f12"><label></label></dd>
				                   </dl>
				               </td>  
				           </tr>
				           <tr>
				              <td class="td-title pct10">督办说明 </td>            
				               <td colspan="5">         
				                   <dl class="form-item form-auto">   
				                          <dd class="detail f12"><label>     
				                          <textarea class="zui-area zui-validatebox row-width" name="remark" validate-type="Require,Length[0-3000]" readonly="readonly"
		                                      placeholder="">${afterSuperviseVo.remark }</textarea>
				                          </label></dd>
				                   </dl>   
			               </td>
				           </tr>
				          
				     </table>
				</div>
			</div>    
			</form>    
      
<script type="text/javascript">      
var uri_get_all_emp = '<z:res resource="essential.comm.employees.select" isDefault="true"/>' + "&jsoncallback=?";
var uri_get_all_post_select = '<z:res resource="ess.post.find-all-select" isDefault="true"/>' + "&jsonCallBack=?";  
var uri_all_org_tree = '<z:res resource="enssential.org.findOrgToTree" isDefault="true"/>' + "&jsoncallback=?";
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form',
           'zd/jquery.zds.table','zd/jquery.zds.seleter',
           'common/zds-common-selecter'], function($, CALLBACK,ZTOOLS) {
		  
		if('${afterSuperviseVo.processingDate }'){      
			$("#processingDateStr").text(ZTOOLS.strToDate('${afterSuperviseVo.processingDate }'));
		}
		
	
	
		$.ZUI.init();  
	});
</script>
  