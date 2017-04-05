<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-------------客户信息------------------------------------>
<div class="page-box">
	<div class="page-title">客户信息</div>
	<div class="p5">
	     <table class="table-detail">
	           <tr>
	               <td class="td-title pct10">姓名</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                          <dd class="detail f12">
	                              <label>
	                              ${persCustomerVo.customerName }
	                              </label>
	                          </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">曾用名</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                           <label>
	                               ${persCustomerVo.formerName }
	                           </label>
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10"></td> 
	               <td class="pct20" rowspan="4"> 
	              	 <img id="mian-headPortraitPath" style="width:360px;height:123px; ${empty persCustomerVo.attachmentId?'display:none;':''}"  src="../upload/download?attachmentId=${persCustomerVo.attachmentId }">   
	               </td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">证件类型</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                              ${persCustomerVo.credentialTypeName }
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">证件号码</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                           <label>
	                           ${persCustomerVo.credentialNo }
	                           </label>
	                       </dd>
	                   </dl>  
	               </td>
	               <td class="td-title pct10"></td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">性别</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                         ${persCustomerVo.genderName }
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">出生日期</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                           <label>
	                              ${persCustomerVo.birthdayDateStr }
	                           </label>
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10"></td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">年龄</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
								${persCustomerVo.age }
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">婚况</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                            ${persCustomerVo.maritalStatusName }
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10"></td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">职业类型</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                       ${persCustomerVo.careerTypeName }
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">教育程度</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                      ${persCustomerVo.degreeName }
	                       </dd>
	                   </dl>
	               </td>
	
	               <td class="td-title pct10">居住年限</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                            ${persCustomerVo.liveAgeName }
	                       </dd>
	                   </dl>
	               </td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">邮箱地址</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                           <label>
	                           ${persCustomerVo.email }
	                           </label>
	                       </dd>
	                   </dl>
	               </td>
	               <td class="td-title pct10">是否实际用款人</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                              ${persCustomerVo.actualUsePersonName }
	                       </dd>
	                   </dl>
	               </td>
	
	               <td class="td-title pct10">参与类型</td>
	               <td class="pct20">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                          ${persCustomerVo.joinTypeName }
	                       </dd>
	                   </dl>  
	               </td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">户籍地址</td>
	               <td class="pct20" colspan="5">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">${beforeAddressVo.fullAddress }
	                       </dd>
	                   </dl>
	               </td>
	           </tr>
	           <tr>
	               <td class="td-title pct10">家庭地址</td>
	               <td class="pct20" colspan="5">
	                   <dl class="form-item form-auto">
	                       <dd class="detail f12">
	                       ${homeAddressVo.fullAddress }
	                       </dd>
	                   </dl>
	               </td>
	           </tr>
	     </table>
	</div>
	<!-------------配偶信息---------------------------------------->
	<div id ="client_spouse_form" style="display: none;">
		<div class="page-title _span">配偶信息</div>
		<div class="p5">
		    <table class="table-detail">
		        <tr>
		            <td class="td-title pct10">配偶姓名</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                      <dd class="detail f12">
		                          <label>
		                          ${persCustomerVo.spouseVo.customerName }
		                          </label>
		                      </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10">曾用名</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                    <dd class="detail f12">
		                        <label>
		                           ${persCustomerVo.spouseVo.formerName }
		                        </label>
		                    </dd>
		                </dl>
		            </td>   
		            <td class="td-title pct10"></td>
		            <td class="pct20" rowspan="4">
		            	 <img id="spouseVo-headPortraitPath" style="width:360px;height:123px; ${empty persCustomerVo.spouseVo.attachmentId?'display:none;':''}"  src="../upload/download?attachmentId=${persCustomerVo.spouseVo.attachmentId}">   
		            </td>
		        </tr>
		        <tr>
		            <td class="td-title pct10">证件类型</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                    <dd class="detail f12">
		                        ${persCustomerVo.spouseVo.credentialTypeName }
		                    </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10">证件号码</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                    <dd class="detail f12">
		                        <label>
		                        ${persCustomerVo.spouseVo.credentialNo }
		                        </label>
		                    </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10"></td>
		        </tr>
		        <tr>
		            <td class="td-title pct10">邮箱地址</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                    <dd class="detail f12">
		                        <label>
		                        ${persCustomerVo.spouseVo.email }
		                        </label>
		                    </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10">职业类型</td>
		            <td class="pct20">
		                <dl class="form-item form-auto">
		                    <dd class="detail f12">
		                           ${persCustomerVo.spouseVo.careerTypeName }
		                    </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10"></td>
		        </tr>
		        <tr>
		            <td class="td-title pct10">参与类型</td>
		            <td class="pct20">
		                <dl class="form-item form-auto"> 
		                    <dd class="detail f12">
		                       ${persCustomerVo.spouseVo.joinTypeName }
		                    </dd>
		                </dl>
		            </td>
		            <td class="td-title pct10"></td>
		            <td class="pct20">
		            </td>
		            <td class="td-title pct10"></td>
		        </tr>
		    </table>
		</div>
	</div>
</div>
    
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		//显示隐藏配偶
		var maritalStatus = "${persCustomerVo.maritalStatus }";
		if(maritalStatus){
			if('YWDM0011302'==maritalStatus || 'YWDM0011303'==maritalStatus || 'YWDM0011304'==maritalStatus || 'YWDM0011305'==maritalStatus ){
        		$("#client_spouse_form").show();
        	}else{
        		$("#client_spouse_form").hide();
        	}
		}
	});
</script>