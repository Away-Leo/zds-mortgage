<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>      
<form id="supervise_form" class="zui-form " method="post" enctype="multipart/form-data">
<div class="page-box">  
				<div class="page-title">案件信息</div>
				<div class="p5">       
				     <table class="table-detail">  
				      <input type="hidden" id="caseApplyId" value="${caseApplyId}" class="zui-input" name="caseApplyId"/>
				      <input type="hidden" id="id" value="${afterSuperviseVo.id }" class="zui-input" name="id"/>
				         
				           <tr>
				               <td class="td-title pct10"><b class="c-red mr5">*</b>督办类型</td>   
									<td class="pct30">
										<dl class="form-item form-auto">      
											<dd class="detail">
												<input class="zui-combobox zui-validatebox" type="hidden" name="superviseType" id="superviseType"   
													data-valuefield="fullcode" data-textfield="name" validate-type="Require">
											</dd>
										</dl>    
								</td>
				               <td class="td-title pct10"><b class="c-red mr5">*</b>督办子类型</td>
									<td class="pct30">   
										<dl class="form-item form-auto">  
											<dd class="detail">
												<input class="zui-combobox zui-validatebox" type="hidden" id="superviseChildType" name="superviseChildType" validate-type="Require">
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
				               <td class="td-title pct10"><b class="c-red mr5">*</b>督办接收人</td>
				               <td class="pct20">  
				                   <dl class="form-item form-auto">        
				                          <dd class="detail"><label>
				                          <input type="text" id="superviseReceiverName" value="${afterSuperviseVo.superviseReceiverName }" class="zui-input" name="superviseReceiverName"  readonly="readonly"/>
				                          <input type="hidden" id="superviseReceiverCode" value="${afterSuperviseVo.superviseReceiverCode }" class="zui-input" name="superviseReceiverCode"/>
				                          
				                          </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10">督办抄送人</td>
				               <td class="pct20">      
				                   <dl class="form-item form-auto">   
				                       <dd class="detail"><label>
				                       <input type="text" id="superviseCopyReceiverName" value="${afterSuperviseVo.superviseCopyReceiverName }" class="zui-input" name="superviseCopyReceiverName" readonly="readonly"/>
				                       <input type="hidden" id="superviseCopyReceiverCode"  name="superviseCopyReceiverCode" value="${afterSuperviseVo.superviseCopyReceiverCode }"/></label></dd>
				                       </label></dd>
				                   </dl>
				               </td>
				               <td class="td-title pct10"><b class="c-red mr5">*</b>处理时限</td>  
				               <td class="pct20">
				                   <dl class="form-item form-auto">      
				                       <dd class="detail f12">
				                       <label><input class="zui-date zui-validatebox" type="text" id="processingDateStr"  value="" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'processingDate',minDate: '%y-%M-%d'})" validate-type="Require"  readonly />
											<input type="hidden" id="processingDate"  name="processingDate" value="${afterSuperviseVo.processingDate }"/></label></dd>
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
				              <td class="td-title pct10"><b class="c-red mr5">*</b>督办说明 </td>            
				               <td colspan="5">         
				                   <dl class="form-item form-auto">   
				                          <dd class="detail f12"><label>   
				                          <textarea class="zui-area zui-validatebox row-width" name="remark" placeholder="最多可以输入512个字符" validate-type="Require,Length[0-512]"
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
		var firstMarkingUrl =  '<z:ukey key="com.cnfh.rms.casemanage.complianceReview.getMarkByParent" context="admin"/>&jsoncallback=?';
		//督办子类型  
		if('${afterSuperviseVo.superviseType }'){
			$("#superviseChildType").ZCombobox({       
				valueField: "fullCode",     
				textField: "name",         
				url: firstMarkingUrl+"&id=${afterSuperviseVo.superviseType }",
				value:'${afterSuperviseVo.superviseChildType}',
				onSelect: function(value, text, index, data) {
				}  
			});    
		}
		
		
			$("#superviseType").ZCombobox({         
				valueField: "fullcode", 
				textField: "name",         
				url: "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00132",
				value:"${afterSuperviseVo.superviseType }",  
				onSelect: function(value, text, index, data) {     
					$("#superviseChildType").ZCombobox({         
						valueField: "fullCode",
						textField: "name",         
						url: firstMarkingUrl+"&id="+value,
						value:""
						   
					});     
				}  
			});     
		//选择一级标示 
		/* CALLBACK.superviseTypeCall = function(value, text) {
			$("#superviseChildType").ZCombobox({         
				valueField: "fullCode",
				textField: "name",         
				url: firstMarkingUrl+"&id="+value,
				value:"",
				onSelect: function(value, text, index, data) {
				}  
			});     
			
		}; */
		if('${afterSuperviseVo.processingDate }'){   
			$("#processingDateStr").val(ZTOOLS.strToDate('${afterSuperviseVo.processingDate }'));
		}
		
		//督办接收人
		$("#superviseReceiverName").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: false,
	        onOk: function (data) {   
	        	var empCds=[];
	        	var empNms=[];
	        	if(data && data.length>0){   
		        	$('#empCode').val(data[0]._data.empCd);          
					$('#empName').val(data[0]._data.empNm);  
					if(data && data.length>0){
						for(var i=0;i<data.length;i++){
							empCds.push(data[i].empCd);
							empNms.push(data[i].empNm);
						}     
					}
	        	}
				$("#superviseReceiverName").val(empNms.join(","));     
				$("#superviseReceiverCode").val(empCds.join(","));     
	        //  alert(value+"/"+text);
	     },onBeforeOpen:function(){     
	         //重新获取列表中的值   
	            var arr =[$("#superviseReceiverCode").val(),$("#superviseReceiverName").val()];
	        $("#superviseReceiverName").Zseleter("setValue",arr);  
	         $("#zd-seleter-search")[0].reset();    
	         return true;
	     }});   
		//督办抄送人
		$("#superviseCopyReceiverName").commonSelect({rowHeight: "30px", height: 440,width: 750, type: "emp", singleSelect: true,
	        onOk: function (data) { 
	        	if(data && data.length>0){
		        	$('#superviseCopyReceiverCode').val(data[0].empCd);                  
					$('#superviseCopyReceiverName').val(data[0].empNm);  
	        	}else{
		        	$('#superviseCopyReceiverCode').val('');                  
					$('#superviseCopyReceiverName').val('');  
	        	}
	     },onBeforeOpen:function(){  
	         //重新获取列表中的值
	         var arr =[$("#superviseCopyReceiverCode").val(),$("#superviseCopyReceiverName").val()];
	         $("#superviseCopyReceiverName").Zseleter("setValue",arr);
	         $("#zd-seleter-search")[0].reset();   
	         return true;
	     }});
	
		$.ZUI.init();  
	});
</script>
