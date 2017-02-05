<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<!-------------客户信息------------------------------------>
<form id="client_form" class="zui-form" method="post" enctype="multipart/form-data">
<input type="hidden" id="mainCustomerId" name="persCustomerVo.id" value=""> 
   <div class="page-title">客户信息</div>
   <div class="p5">
       <table class="table-detail">
           <tr>
               <td class="td-title pct10"></b>姓名</td>
               <td class="pct20">${persCustomerVo.customerName}</td>
               <td class="td-title pct10">曾用名</td>
               <td class="pct20">${persCustomerVo.formerName }</td>
               <td class="td-title pct10"></td>
               <td class="pct30" rowspan="4">
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5"></b>证件类型</td>
               <td class="pct20">${persCustomerVo.credentialTypeName}</td>
               <td class="td-title pct10"><b class="c-red mr5"></b>证件号码</td>
               <td class="pct20">${persCustomerVo.credentialNo }</td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5"></b>性别</td>
               <td class="pct20">${persCustomerVo.genderName }</td>
               <td class="td-title pct10"><b class="c-red mr5"></b>出生日期</td>
               <td class="pct20"><span class="strToDate">${persCustomerVo.birthdayDate }</span></td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10">年龄</td>
               <td class="pct20"><span id="customerAge"></span></td>
               <td class="td-title pct10"><b class="c-red mr5"></b>婚况</td>
               <td class="pct20">${persCustomerVo.maritalStatusName}</td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5"></b>职业类型</td>
               <td class="pct20">${persCustomerVo.careerTypeName}</td>
               <td class="td-title pct10"><b class="c-red mr5"></b>教育程度</td>
               <td class="pct20">${persCustomerVo.degreeName }</td>

               <td class="td-title pct10"><b class="c-red mr5"></b>居住年限</td>
               <td class="pct30">${persCustomerVo.liveAgeName}</td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>邮箱地址</td>
               <td class="pct20">${persCustomerVo.email}</td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>是否实际用款人</td>
               <td class="pct20">${persCustomerVo.actualUsePersonName}</td>

               <td class="td-title pct10"><b class="c-red mr5">*</b>参与类型</td>
               <td class="pct30">${persCustomerVo.joinTypeName }</td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5"></b>户籍地址</td>
               <td class="pct20" colspan="5">${beforeAddressVo.fullAddress }
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>家庭地址</td>
               <td class="pct20" colspan="5">${beforeAddressVo.fullAddress }
               </td>
           </tr>

       </table>
   </div>
    </form>
<!-------------配偶信息---------------------------------------->
<form id="client_spouse_form" class="zui-form" method="post" enctype="multipart/form-data" style="display: none;">
<input type="hidden" id="spouseId" name="persCustomerVo.spouseVo.id" value=""> 
   <div class="page-title _span">配偶信息</div>
   <div class="p5">
       <table class="table-detail">
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>配偶姓名</td>
               <td class="pct20">${persCustomerVo.spouseVo.customerName }</td>
               <td class="td-title pct10">曾用名</td>
               <td class="pct20">${persCustomerVo.spouseVo.formerName }
               </td>
               <td class="td-title pct10"></td>
               <td class="pct30" rowspan="4">
               </td>
           </tr>
           <tr>
               <td class="td-title pct10">证件类型</td>
               <td class="pct20">${persCustomerVo.spouseVo.credentialTypeName }</td>
               <td class="td-title pct10">证件号码</td>
               <td class="pct20">${persCustomerVo.spouseVo.credentialNo }</td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10">邮箱地址</td>
               <td class="pct20">${persCustomerVo.spouseVo.email }</td>
               <td class="td-title pct10">职业类型</td>
               <td class="pct20">${persCustomerVo.spouseVo.careerTypeName }</td>
               <td class="td-title pct10"></td>
           </tr>
           <tr>
               <td class="td-title pct10">参与类型</td>
               <td class="pct20">${persCustomerVo.spouseVo.joinTypeName }</td>
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
		var maritalStatus = "${persCustomerVo.maritalStatus }";
		if('x0303002'==maritalStatus || 'x0303003'==maritalStatus || 'x0303004'==maritalStatus || 'x0303005'==maritalStatus ){
    		$("#client_spouse_form").show();
    	}else{
    		$("#client_spouse_form").hide();
    	}
		var age=jsGetAge("${persCustomerVo.birthdayDate}");
		$("#customerAge").html(age);
		/*根据出生日期算出年龄*/  
		function jsGetAge(strBirthday){         
		    var returnAge;  
		    var birthYear = strBirthday.substring(0, 4);
		    var birthMonth = strBirthday.substring(4, 6);
		    var birthDay = strBirthday.substring(6, 8);
		      
		    d = new Date();  
		    var nowYear = d.getFullYear();  
		    var nowMonth = d.getMonth() + 1;  
		    var nowDay = d.getDate();  
		      
		    if(nowYear == birthYear){  
		        returnAge = 0;//同年 则为0岁  
		    }  
		    else{  
		        var ageDiff = nowYear - birthYear ; //年之差  
		        if(ageDiff > 0){  
		            if(nowMonth == birthMonth) {  
		                var dayDiff = nowDay - birthDay;//日之差  
		                if(dayDiff < 0)  
		                {  
		                    returnAge = ageDiff - 1;  
		                }  
		                else  
		                {  
		                    returnAge = ageDiff ;  
		                }  
		            }  
		            else  
		            {  
		                var monthDiff = nowMonth - birthMonth;//月之差  
		                if(monthDiff < 0)  
		                {  
		                    returnAge = ageDiff - 1;  
		                }  
		                else  
		                {  
		                    returnAge = ageDiff ;  
		                }  
		            }  
		        }  
		        else  
		        {  
		            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天  
		        }  
		    }  
		      
		    return returnAge;//返回周岁年龄  
		      
		}

	});
</script>
