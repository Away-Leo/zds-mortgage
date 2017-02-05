<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-------------客户信息------------------------------------>
<form id="client_form" class="zui-form" method="post" enctype="multipart/form-data">
<input type="hidden" id="mainCustomerId" name="persCustomerVo.id" value="${persCustomerVo.id }"> 
<input type="hidden"  name="persCustomerVo.attachmentId" value="${persCustomerVo.attachmentId }"> 
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
               <td class="pct30" rowspan="4"> 
              	 <img id="mian-headPortraitPath" style="width:360px;height:123px; ${empty persCustomerVo.headPortraitPath?'display:none;':''}"  src="${persCustomerVo.headPortraitPath }">   
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
                           <label id="clientAge">
                           </label>
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
               <td class="pct30">
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
               <td class="pct30">
                   <dl class="form-item form-auto">
                       <dd class="detail f12">
                              主借人
                       </dd>
                   </dl>  
               </td>
           </tr>
           <tr>
               <td class="td-title pct10">户籍地址</td>
               <td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                       <dd class="detail f12">${beforeAddressVo.provinceName }/${beforeAddressVo.cityName }/${beforeAddressVo.districtName }
                       </dd>
                       <dd class="detail f12">
                       &nbsp;
                       &nbsp;
                       &nbsp;
                       ${beforeAddressVo.address }
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10">家庭地址</td>
               <td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                       <dd class="detail f12">
                       ${homeAddressVo.provinceName }/${homeAddressVo.cityName }/${homeAddressVo.districtName }
                       </dd>
                       <dd class="detail f12">
                        &nbsp;
                       &nbsp;
                       &nbsp;
                       ${homeAddressVo.address }
                       </dd>
                   </dl>
               </td>
           </tr>

       </table>
   </div>
    </form>
<!-------------配偶信息---------------------------------------->
<form id="client_spouse_form" class="zui-form" method="post" enctype="multipart/form-data" style="display: none;">
<input type="hidden" id="spouseId" name="persCustomerVo.spouseVo.id" value="${persCustomerVo.spouseVo.id }"> 
<input type="hidden"  name="persCustomerVo.spouseVo.attachmentId" value="${persCustomerVo.spouseVo.attachmentId }"> 
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
               <td class="pct30" rowspan="4">
               	 <img id="spouseVo-headPortraitPath" style="width:360px;height:123px; ${empty persCustomerVo.spouseVo.headPortraitPath?'display:none;':''}"  src="${persCustomerVo.spouseVo.headPortraitPath }">   
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
  
  </form>
		
    
<script type="text/javascript">
			
				
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		//显示隐藏配偶
		var maritalStatus = "${persCustomerVo.maritalStatus }";
		if(maritalStatus){
			if('x0303002'==maritalStatus || 'x0303003'==maritalStatus || 'x0303004'==maritalStatus || 'x0303005'==maritalStatus ){
        		$("#client_spouse_form").show();
        	}else{
        		$("#client_spouse_form").hide();
        	}
		}
			
			
		var _birthday = '${persCustomerVo.birthdayDate}';
		if(_birthday){  
		//获取年龄
		var myDate = new Date();
	        var month = myDate.getMonth() + 1;
	        var day = myDate.getDate();
	        var age = myDate.getFullYear() - _birthday.substring(0, 4) - 1;
	        if (_birthday.substring(4, 6) < month || _birthday.substring(4, 6) == month && _birthday.substring(6, 8) <= day) {
	            age++;
	        }  
	        $("#clientAge").html(age);
		}  
	});
</script>
