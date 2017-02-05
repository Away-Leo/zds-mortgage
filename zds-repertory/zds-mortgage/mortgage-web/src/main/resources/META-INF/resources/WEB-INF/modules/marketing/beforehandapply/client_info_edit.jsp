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
               <td class="td-title pct10"><b class="c-red mr5">*</b>姓名</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" type="text" value="${persCustomerVo.customerName }" validate-type="Require" name="persCustomerVo.customerName" id="client_customerName">
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10">曾用名</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input" type="text" value="${persCustomerVo.formerName }" name="persCustomerVo.formerName">
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
               <td class="td-title pct10"><b class="c-red mr5">*</b>证件类型</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                              <input class="zui-combobox zui-validatebox" type="hidden"
                              data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=60600"
                                     id="client_credentialType" name="persCustomerVo.credentialType" data-valuefield="fullcode" data-defaultvalue="${persCustomerVo.credentialType}"
                                     data-textfield="name"
                                     validate-type="Require" data-callback="setClientGender">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>证件号码</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" value="${persCustomerVo.credentialNo }" validate-type="Require" id="clientCredentialNo"  name="persCustomerVo.credentialNo">
                           </label>
                       </dd>
                   </dl>  
               </td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>性别</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden"
                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=3030"
                                   id="client_gender" name="persCustomerVo.gender" data-valuefield="fullcode" data-defaultvalue="${persCustomerVo.gender }"
                                   data-textfield="name"
                                   validate-type="Require">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>出生日期</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                                <input class="zui-date toStrDate zui-validatebox" type="text" id="birthdayDate" value="" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'realBirthdayDate',dateFmt:'yyyy-MM-dd',onpicked:function(){WdateValidate(this);birthdayChange()}})"
                                      validate-type="Require" readonly />
                                <input   type="hidden" id="realBirthdayDate" name="persCustomerVo.birthdayDate" value="${persCustomerVo.birthdayDate }">
                                   
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
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-disabled" type="text"  readonly name="age" id="clientAge"/>
                           </label>
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>婚况</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden"
                                  data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=x030300"
                                   id="client_maritalStatus" name="persCustomerVo.maritalStatus" data-valuefield="fullcode" data-callback="maritalStatusChange"
                                   data-textfield="name" data-defaultvalue="${persCustomerVo.maritalStatus }"
                                   validate-type="Require">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>职业类型</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                             <input class="zui-combobox zui-validatebox" type="hidden" id="client_careerType" name="persCustomerVo.careerType"
                                    data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g050500"
                                	data-defaultvalue="${persCustomerVo.careerType }" data-valuefield="fullcode" data-textfield="name" validate-type="Require">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>教育程度</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                              <input class="zui-combobox zui-validatebox" type="hidden" id="client_degree" name="persCustomerVo.degree" 
                                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=e030300"
                                	data-defaultvalue="${persCustomerVo.degree }" data-valuefield="fullcode" data-textfield="name" validate-type="Require">
                       </dd>
                   </dl>
               </td>

               <td class="td-title pct10"><b class="c-red mr5">*</b>居住年限</td>
               <td class="pct30">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                             <input class="zui-combobox zui-validatebox" type="hidden"
                            	 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=y00100"
                                 data-defaultvalue="${persCustomerVo.liveAge }" id="client_liveAge" name="persCustomerVo.liveAge" data-valuefield="fullcode" data-textfield="name"
                                 validate-type="Require">
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>邮箱地址</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" name="persCustomerVo.email" value="${persCustomerVo.email }" validate-type="Require,Email">
                           </label>
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>是否实际用款人</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
                                      id="client_actualUsePerson" name="persCustomerVo.actualUsePerson" data-valuefield="fullcode"
                                      data-textfield="name" data-defaultvalue="${persCustomerVo.actualUsePerson }"
                                      validate-type="Require">
                       </dd>
                   </dl>
               </td>

               <td class="td-title pct10"><b class="c-red mr5">*</b>参与类型</td>
               <td class="pct30">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      data-url="<z:res resource='public.simplecode.selector'  isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0056"
                                      id="client_joinType" name="persCustomerVo.joinType" data-valuefield="fullcode"
                                      data-choose='disable' 
                                      data-textfield="name" data-defaultvalue="YWDM0051036"
                                      validate-type="Require">
                       </dd>
                   </dl>  
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>户籍地址</td>
               <td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <input type="hidden" id="liveProvince" name="beforeAddressVo.province" value="${beforeAddressVo.province }"/>
                           <input type="hidden" id="liveCity" name="beforeAddressVo.city" value="${beforeAddressVo.city }"/>
                           <input type="hidden" id="liveCounty" name="beforeAddressVo.district"  value="${beforeAddressVo.district }"/>
                           <div id="selectAddress_live" data-code="${beforeAddressVo.district }">
                               <input id="address_live_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;" validate-type="Require"/>
                              <!--  <input id="address_live_code" type="hidden" value=""/> -->
                           </div>
                       </dd>
                       <dd class="detail">
                           <input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" value="${beforeAddressVo.address }" id="liveAddr" name="beforeAddressVo.address" style="width: 645px;" validate-type="Require">
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>家庭地址</td>
               <td class="pct20" colspan="5">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <input type="hidden" id="homeProvince" name="homeAddressVo.province" value="${homeAddressVo.province }"/>
                           <input type="hidden" id="homeCity" name="homeAddressVo.city" value="${homeAddressVo.city }"/>
                           <input type="hidden" id="homeDistrict" name="homeAddressVo.district" value="${homeAddressVo.district }"/>
                           <div id="selectAddress_home" data-code="${homeAddressVo.district }">
                               <input id="address_home_text" class="zui-input zui-validatebox" type="text" readonly="true" style="width: 260px;" validate-type="Require"/>
                           </div>
                       </dd>
                       <dd class="detail">
                           <input class="zui-input zui-validatebox" validate-type="Require,Length[0-128]" value="${homeAddressVo.address }" id="homePlace" name="homeAddressVo.address" style="width: 455px;" validate-type="Require">
                       </dd>
                       <dd class="detail">
                       		<input class="zui-combobox" type="hidden"
                                   data-data="[{'id':'liveCounty','text':'复制户籍地址'}]"
                                   data-valuefield="id" data-callback="copyAddressValue"
                                   data-textfield="text" 
                             >
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
               <td class="td-title pct10"><b class="c-red mr5">*</b>配偶姓名</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                         <dd class="detail">
                             <label>
                                 <input class="zui-input zui-validatebox" type="text" value="${persCustomerVo.spouseVo.customerName }" validate-type="Require" id="spouse_customerName" name="persCustomerVo.spouseVo.customerName">
                             </label>
                         </dd>
                   </dl>
               </td>
               <td class="td-title pct10">曾用名</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input" type="text" value="${persCustomerVo.spouseVo.formerName }" name="persCustomerVo.spouseVo.formerName">
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
               <td class="td-title pct10"><b class="c-red mr5">*</b>证件类型</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                             <input class="zui-combobox zui-validatebox" type="hidden"
                                    data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=60600"
                                    id="spouse_credentialType" name="persCustomerVo.spouseVo.credentialType" data-valuefield="fullcode"
                                    data-textfield="name" data-defaultvalue="${persCustomerVo.spouseVo.credentialType }"  data-callback="spouseVoCredentialChange"
                                    validate-type="Require">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>证件号码</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" id="spouseVoCredentialNo" value="${persCustomerVo.spouseVo.credentialNo }" name="persCustomerVo.spouseVo.credentialNo" validate-type="Require">
                           </label>
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>邮箱地址</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" value="${persCustomerVo.spouseVo.email }" name="persCustomerVo.spouseVo.email" validate-type="Require,Email">
                           </label>
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>职业类型</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=g050500"
                                      id="spouse_careerType" name="persCustomerVo.spouseVo.careerType" data-valuefield="fullcode"
                                      data-textfield="name"  data-defaultvalue="${persCustomerVo.spouseVo.careerType }"
                                      validate-type="Require">
                       </dd>
                   </dl>
               </td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>参与类型</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                               <input class="zui-combobox zui-validatebox" type="hidden"
                                      id="spouse_joinType" name="persCustomerVo.spouseVo.joinType" data-valuefield="fullcode"
                                      data-textfield="name" data-defaultvalue="${persCustomerVo.spouseVo.joinType }"
                                      validate-type="Require">
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
			
			function birthdayChange(){
				var _birthday = $("#realBirthdayDate").val().replace(/-/g, "");
				//获取年龄
				var myDate = new Date();
       	        var month = myDate.getMonth() + 1;
       	        var day = myDate.getDate();
       	        var age = myDate.getFullYear() - _birthday.substring(0, 4) - 1;
       	        if (_birthday.substring(4, 6) < month || _birthday.substring(4, 6) == month && _birthday.substring(6, 8) <= day) {
       	            age++;
       	        }
       	        $("#clientAge").val(age);
			}
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter','zd/jquery.zds.address'], 
		function($, CALLBACK) {
		//配偶的参与类型 过滤主借人
		var spouseJoinTypes=[];
		 $.ajax({
                     async: false,
                     url: "<z:res resource='public.simplecode.selector'  isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0056",
                     dataType: "json",
                     success: function (data) {     
                    	 if(data){
                    		for(var i=0;i<data.length;i++){  
                    			if(data[i].fullcode!='YWDM0051036'){    
                    				spouseJoinTypes.push({'fullcode':data[i].fullcode,'name':data[i].name});
                    			}
                    		}   
                    		setTimeout(function(){
                    		$("#spouse_joinType").ZCombobox({
                 	            valueField: "fullcode",
                 	            textField: "name",
                 	            data:spouseJoinTypes   
                 	        });
                    		},200);  
                    	 }
                     },   
                     error: function () {
                     }
                 });
// 		 $("#client_careerType").ZCombobox({
// 	            valueField: "fullcode",
// 	            textField: "name",
// 	            url:'<z:res resource="ess.batch.simplecode.categoryCds" isDefault="true"/>&jsoncallback=?',//是否是批量下拉
// 	            queryParams:{"categoryCds":"g050500,e030300"},//批量下拉的参数
// 	            batchDrop:true,//批量下拉的url
// 	            batchDropCode:"g050500,e030300",//批量下拉的参数
// 	            batchDropField:'client_careerType,client_degree'//批量下拉的字段
// 	        });
		//显示隐藏配偶
		var maritalStatus = "${persCustomerVo.maritalStatus }";
			if('x0303002'==maritalStatus || 'x0303003'==maritalStatus || 'x0303004'==maritalStatus || 'x0303005'==maritalStatus ){
        		$("#client_spouse_form").show();
        	}else{
        		$("#client_spouse_form").hide();
        	}
		//回显生日，年龄
		var realBirthdayDate = $("#realBirthdayDate").val();
		if(realBirthdayDate){
			$("#birthdayDate").val(realBirthdayDate.substring(0,4)+"-"+realBirthdayDate.substring(4,6)+"-"+realBirthdayDate.substring(6,8));
			var myDate = new Date();
   	        var month = myDate.getMonth() + 1;
   	        var day = myDate.getDate();
   	        var _age = myDate.getFullYear() - realBirthdayDate.substring(0, 4) - 1;
   	        if (realBirthdayDate.substring(4, 6) < month || realBirthdayDate.substring(4, 6) == month && realBirthdayDate.substring(6, 8) <= day) {
   	        	_age++;
   	        }
   	        $("#clientAge").val(_age);
		}
		//初始选择器
        $("#selectAddress_live").Address({
            showStreet:false,//根据code取下级节点数据
            cityUrl:cityUrl,//真实数据源
            getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
            callback:function(infos,selected_ids) {
                var str = '';
                for(var i=0;i<infos.length;i++) {
                    if(str==""){
                        str = str+infos[i];
                    }else{
                        str = str+" / "+infos[i];
                    }
                }
                $('#address_live_text').val(str);
                $('#liveProvince').val(selected_ids[0]);
                $('#liveCity').val(selected_ids[1]);
                $('#liveCounty').val(selected_ids[2]);
            }
        });

        $("#selectAddress_home").Address({
            showStreet:false,//不显示街道
            cityUrl:cityUrl,//真实数据源
            getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
            callback:function(infos,selected_ids) {
                var str = '';
                for(var i=0;i<infos.length;i++) {
                    if(str==""){
                        str = str+infos[i];
                    }else{
                        str = str+" / "+infos[i];
                    }
                }
                $('#address_home_text').val(str);
                $('#homeProvince').val(selected_ids[0]);
                $('#homeCity').val(selected_ids[1]);
                $('#homeDistrict').val(selected_ids[2]);
            }
        });

        //地址复制
        CALLBACK.copyAddressValue=function(v,t){
        	//获取对象的值
        	var objValue=$('#'+v).val();
            if(null!=objValue&&objValue!=""){
            	//赋值街道详细地址
            	if(v=="liveCounty"){
            		$("#homePlace").val($('#liveAddr').val());
            	}else if(v=="homeDistrict"){
            		$("#homePlace").val($('#homePlace').val());
            	}
                //用这种方式复制选择项
                $("#selectAddress_home").setAddress({
                    showStreet:false,//不显示街道
                    cityUrl:cityUrl,//真实数据源
                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
                    data:objValue,
                    callback:function(infos,selected_ids) {
                        var str = '';
                        for(var i=0;i<infos.length;i++) {
                            if(str==""){
                                str = str+infos[i];
                            }else{
                                str = str+" / "+infos[i];
                            }
                        }
                        //显示中文
                        $('#address_home_text').val(str);
                        $('#homeProvince').val(selected_ids[0]);
                        $('#homeCity').val(selected_ids[1]);
                        $('#homeDistrict').val(selected_ids[2]);
                        
                    }
                });
            }
        }
        //婚况
        CALLBACK.maritalStatusChange = function(v,t){
        	if('x0303002'==v || 'x0303003'==v || 'x0303004'==v || 'x0303005'==v ){
        		$("#client_spouse_form").show();
        	}else{
        		$("#client_spouse_form").hide();
        	}
        }
        function  calculateAge(credentialType){
        	// 证件类型为身份证
        	if(credentialType == '6060001'){
        		var credentialNo=$("#clientCredentialNo").val();
        		// 根据身份证自动填充出生日期 和性别
        		if(credentialNo!=null && credentialNo!=""  && credentialNo.length==18){
        			// 根据身份证自动填充出生日期
        			$('#birthdayDate').val(credentialNo.substr(6, 4)+'-'+credentialNo.substr(10, 2)+'-'+credentialNo.substr(12, 2));
        			// 根据身份证获取性别
        			var sex = "";
        			if (parseInt(credentialNo.charAt(16) / 2) * 2 != credentialNo.charAt(16)){
        				sex = '男';
        				$("#client_gender").ZCombobox('setValue','303001');
        			}else{
        				 sex = '女';
        				 $("#client_gender").ZCombobox('setValue','303002');
        			}
        			
        			//获取年龄  
        	        var myDate = new Date();  
        	        var month = myDate.getMonth() + 1;
        	        var day = myDate.getDate();
        	        var age = myDate.getFullYear() - credentialNo.substring(6, 10) - 1;
        	        if (credentialNo.substring(10, 12) < month || credentialNo.substring(10, 12) == month && credentialNo.substring(12, 14) <= day) {
        	            age++;
        	        }
        	        $("#clientAge").val(age);
        		}
        		
        		$("#clientCredentialNo").attr("validate-type","Require,IDCard");
        	} else {
        		$("#clientCredentialNo").attr("validate-type","Require,Length[1-32]");
        	}
        }
        //后台获取客户数据
        function credentialChange(credentialType){
        	//类型
        	//证件号码
        	var credentialNo = $("#clientCredentialNo").val();
        	 if(credentialType && credentialNo){  
        		 $("input[name='persCustomerVo.attachmentId']").val("");
             	$("input[name='persCustomerVo.spouseVo.attachmentId']").val("");
        		 $.ajax({  
                     async: false,
                     url: '<z:ukey key="com.zdsoft.finance.beforePersonalCustomer.getLatestByCredentialNoAndType" context="admin"/>',
                     data:{credentialNo: credentialNo,credentialType:credentialType},  
                     dataType: "json",
                     success: function (data) {
                    	 if(data){
                    		var mainCustomerVo = data.mainCustomerVo;
                    		if(mainCustomerVo){  
                    			//姓名
                    			$("#client_customerName").val(mainCustomerVo.customerName);
                    			//曾用名	
                    			$("input[name='persCustomerVo.formerName']").val(mainCustomerVo.formerName);
                    			
                    			//婚况
                    			$('#client_maritalStatus').ZCombobox('setValue',mainCustomerVo.maritalStatus);
                    			//职业类型
                    			$('#client_careerType').ZCombobox('setValue',mainCustomerVo.careerType);
                    			//教育程度	
                    			$('#client_degree').ZCombobox('setValue',mainCustomerVo.degree);
                    			//居住年限
                    			$('#client_liveAge').ZCombobox('setValue',mainCustomerVo.liveAge);
                    			//邮箱地址
                    			$("input[name='persCustomerVo.email']").val(mainCustomerVo.email);
                    			//是否实际用款人
                    			$("#client_actualUsePerson").ZCombobox('setValue',mainCustomerVo.actualUsePerson);  
                    			//头像id
                    			$("input[name='persCustomerVo.attachmentId']").val(mainCustomerVo.attachmentId);
                    			//出生日期
                    			$("input[name='persCustomerVo.birthdayDate']").val(mainCustomerVo.birthdayDate);
                    			//性别
                    			$("#client_gender").ZCombobox('setValue',mainCustomerVo.gender);    
                    			var birthdayDate = mainCustomerVo.birthdayDate;
                    			if(birthdayDate){
                    				birthdayDate = birthdayDate+"";   
	                    			$("#birthdayDate").val(birthdayDate.substring(0,4)+"-"+birthdayDate.substring(4,6)+"-"+birthdayDate.substring(6,8));
	                    			birthdayChange(); 
                    			}    
                    			
                    			if(mainCustomerVo.headPortraitPath){
                    				$("#mian-headPortraitPath").show();
                    				$("#mian-headPortraitPath").attr("src",mainCustomerVo.headPortraitPath);  
                    			}else{
                    				$("#mian-headPortraitPath").hide();
                    			}
                    			var residenceAddress = data.residenceAddress;
                    			if(residenceAddress){
                    				//户籍地址
                    				$('#liveProvince').val(residenceAddress.province);
                    				$('#liveCity').val(residenceAddress.city);
                    				$('#liveCity').val(residenceAddress.district);
                    				$('#liveAddr').val(residenceAddress.address);
                        			$("#selectAddress_home").setAddress({
    				                    showStreet:false,//不显示街道   
    				                    cityUrl:cityUrl,//真实数据源
    				                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
    				                    data:residenceAddress.district,
    				                    callback:function(infos,selected_ids) {   
    				                        var str = '';
    				                        for(var i=0;i<infos.length;i++) {
    				                            if(str==""){
    				                                str = str+infos[i];
    				                            }else{
    				                                str = str+" / "+infos[i];
    				                            }
    				                        }
    				                        $("#selectAddress_live").attr("data-code",residenceAddress.district);
    				                        //显示中文
    				                        $('#address_live_text').val(str);
    				                        $('#liveProvince').val(selected_ids[0]);
    				                        $('#liveCity').val(selected_ids[1]);
    				                        $('#liveCounty').val(selected_ids[2]);
    				                        
    				                    }
    				                });
                    			}
                    			//家庭地址
                    			 var homeAddress = data.homeAddress;   
                    			 if(homeAddress){  
                    				$('#homeProvince').val(homeAddress.province);
                    				$('#homeCity').val(homeAddress.city);
                    				$('#homeDistrict').val(homeAddress.district);
                    				$('#homePlace').val(homeAddress.address);
                    				//户籍地址
                        			$("#selectAddress_home").setAddress({
    				                    showStreet:false,//不显示街道
    				                    cityUrl:cityUrl,//真实数据源
    				                    getDataCityUrl:getDataCityUrl,//根据子节点取同级及上级的数据
    				                    data:homeAddress.district,
    				                    callback:function(infos,selected_ids) {
    				                        var str = '';
    				                        for(var i=0;i<infos.length;i++) {
    				                            if(str==""){   
    				                                str = str+infos[i];
    				                            }else{
    				                                str = str+" / "+infos[i];
    				                            }
    				                        }
    				                        $("#selectAddress_home").attr("data-code",homeAddress.district);
    				                        //显示中文
    				                        $('#address_home_text').val(str);
    				                        $('#homeProvince').val(selected_ids[0]);
    				                        $('#homeCity').val(selected_ids[1]);
    				                        $('#homeDistrict').val(selected_ids[2]);
    				                        
    				                    }
    				                });
                    			} 
                    			
                    			
                    			
                    		}else{
                    			calculateAge(credentialNo);   
                    		}
                    		//配偶
                    		var spouseVo = data.spouseVo;
                    		if(spouseVo){
                    			//配偶姓名
                    			$("#spouse_customerName").val(spouseVo.customerName);
                    			//曾用名
                    			$("input[name='persCustomerVo.spouseVo.formerName']").val(spouseVo.formerName);
                    			//证件类型	
                    			$("#spouse_credentialType").ZCombobox('setValue',spouseVo.credentialType);  
                    			//证件号码
                    			$("input[name='persCustomerVo.spouseVo.credentialNo']").val(spouseVo.credentialNo);  
                    			//邮箱地址
                    			$("input[name='persCustomerVo.spouseVo.email']").val(spouseVo.email);
                    			//职业类型
                    			$("#spouse_careerType").ZCombobox('setValue',spouseVo.careerType);  
                    			//参与类型
                    			$("#spouse_joinType").ZCombobox('setValue',spouseVo.joinType);  
                    			//头像id
                    			$("input[name='persCustomerVo.spouseVo.attachmentId']").val(spouseVo.attachmentId);
                    			if(spouseVo.headPortraitPath){
                    				$("#spouseVo-headPortraitPath").show();
                    				$("#spouseVo-headPortraitPath").attr("src",spouseVo.headPortraitPath);  
                    			}else{
                    				$("#spouseVo-headPortraitPath").hide();
                    			}
                    		}
                    	 }else{
                    		 calculateAge(credentialNo);
                    		 }
                         $("#clientCredentialNo").val();
                     },  
                     error: function () {
                     }
                 });
        	}
        	
        	
        	// 证件类型为身份证
        	if(credentialType == '6060001'){
        		$("#clientCredentialNo").attr("validate-type","Require,IDCard");
        	} else {
        		$("#clientCredentialNo").attr("validate-type","Require,Length[1-32]");
        	}
        }  
        //根据证件类型取身份证
        CALLBACK.setClientGender=function(v,t){
        	credentialChange(v);
        	credentialChange($("#client_credentialType").val());  
        }
        
        
      /*   $("#clientCredentialNo").change(function(){    
        	credentialChange($("#client_credentialType").val());   
        });
         */
        //回调函数证件号码改变的时候
        CALLBACK.clientCredentialNoCallBack=function(){
        	 credentialChange($("#client_credentialType").val());  
        }
       //回调函数证件号码改变的时候 配偶
        CALLBACK.spouseVoCredentialNoCallBack=function(){
        	spouseVoCredentialChange($("#spouse_credentialType").val());  
        }
       function spouseVoCredentialChange(credentialType){
        	//类型
        	//证件号码  
        	var credentialNo = $("input[name='persCustomerVo.spouseVo.credentialNo']").val();
        	 if(credentialType && credentialNo){  
        		 $("input[name='persCustomerVo.spouseVo.attachmentId']").val("");  
        		 $.ajax({
                     async: false,
                     url: '<z:ukey key="com.zdsoft.finance.beforePersonalCustomer.getLatestByCredentialNoAndType" context="admin"/>',
                     data:{credentialNo: credentialNo,credentialType:credentialType},  
                     dataType: "json",
                     success: function (data) {  
                    	 if(data){
                    		var mainCustomerVo = data.mainCustomerVo;
                    		if(mainCustomerVo){    
                    			//配偶姓名
                    			$("#spouse_customerName").val(mainCustomerVo.customerName);
                    			//曾用名
                    			$("input[name='persCustomerVo.spouseVo.formerName']").val(mainCustomerVo.formerName);
                    			//邮箱地址
                    			$("input[name='persCustomerVo.spouseVo.email']").val(mainCustomerVo.email);
                    			//职业类型
                    			$("#spouse_careerType").ZCombobox('setValue',mainCustomerVo.careerType);  
                    			//参与类型
                    			$("#spouse_joinType").ZCombobox('setValue',mainCustomerVo.joinType);  
                    			//头像id
                    			$("input[name='persCustomerVo.spouseVo.attachmentId']").val(mainCustomerVo.attachmentId);
                    			if(mainCustomerVo.headPortraitPath){
                    				$("#spouseVo-headPortraitPath").show();
                    				$("#spouseVo-headPortraitPath").attr("src",mainCustomerVo.headPortraitPath);  
                    			}else{
                    				$("#spouseVo-headPortraitPath").hide();
                    			}
                    		}
                    	 }
                     },   
                     error: function () {
                     }
                 });
        	}
        }
        //配偶事件
        CALLBACK.spouseVoCredentialChange =function(v,t){
        	spouseVoCredentialChange(v)
        
        } 
	});
</script>
